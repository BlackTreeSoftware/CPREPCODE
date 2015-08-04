package com.greenbuds.crunchprep.action.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.directwebremoting.util.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.adminbo.MentorBO;
import com.greenbuds.crunchprep.controller.admin.IAssignMentorController;
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class AssignMentorAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private  final static Logger LOGGER = Logger.getLogger(AssignMentorAction.class);
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private List<MentorBO> mentorlist;
	private List<MentorBO> studentList;
	private List<MentorBO> mentorStudentList;
	private List<MentorBO> finalList;
	private int[] userid; 
	private String successMsg;
	private String errorMsg;
	
	


	private MentorBO mentorBO;
	
	@Autowired
	private IAssignMentorController assignMentorController;
	
	public void getMentors1(){
		
		try {
			mentorlist=assignMentorController.getMentors(Integer.parseInt(request.getParameter("mantortypeId").toString()));
		} catch (NumberFormatException | CPException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			LOGGER.debug("Error Raised  "+e1);
		}
		System.out.println("Mentor LIst size\t"+mentorlist.size());
		String onlyOptions = "<option value=-1>--Select--</option>";
		boolean loopEntere = false;
		ActionContext act = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse)act.get(org.apache.struts2.ServletActionContext.HTTP_RESPONSE);
		if (mentorlist != null) {
			for (MentorBO mentorBO : mentorlist) {
				loopEntere = true;
				onlyOptions += "<option value=" + mentorBO.getMentorId() + ">"
						+ mentorBO.getMentorName() + "</option>";
			}
		}
		if (!loopEntere)
			onlyOptions += "<option></option>";
		try {
			response.getWriter().write(
					"{\"mentors\":\"" + onlyOptions + "\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("Error Raised  "+e);
		} 
	}
	
	public String getMentors2(){
		System.out.println("in getMentorList method");
		
		try {
			mentorlist=assignMentorController.getMentors(1);
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String getMentors(){
		System.out.println("in getMentorList method");
		String mantortypeId;
		if(request.getParameter("mantortypeId")==null){
			mantortypeId="1";
		}else{
			mantortypeId=request.getParameter("mantortypeId").toString();
		}
		try {
			mentorlist=assignMentorController.getMentors(Integer.parseInt(mantortypeId));
		
		studentList=assignMentorController.getStdents();
		mentorStudentList=assignMentorController.getMentorStdents(Integer.parseInt(mantortypeId));
		
		finalList=new ArrayList<MentorBO>();
		
		
		for(MentorBO s:studentList){
			MentorBO bo=new MentorBO();
			 boolean find=false;
			for(MentorBO m:mentorStudentList){
				
				if(s.getUser_id()==m.getUser_id()){
					//System.out.println("Mentor Name\t"+m.getMentorName()+""+m.getUser_id());
					bo.setUser_id(m.getUser_id());
					bo.setStudentName(m.getStudentName());
					bo.setEmail(m.getEmail());
					bo.setMentorName(m.getMentorName());
					//System.out.println("Mentor Name\t"+m.getMentorName());
					find=true;
				}
				
			}if(!find){
				
				bo.setUser_id(s.getUser_id());
				bo.setStudentName(s.getStudentName());
				bo.setEmail(s.getEmail());
				bo.setMentorName("");
				
			}
			
			finalList.add(bo);
			
		}
		
		} catch (NumberFormatException | CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("Error Raised  "+e);
		}
		
		return SUCCESS;
	}
	
	
	public String assignMentor(){
		
		
		System.out.println("Asign List Length"+userid.length);
		System.out.println("Mentor Id\t"+this.mentorBO.getMentorId());
		
		int sectionId;
		try {
			sectionId = assignMentorController.getSectionId(this.getMentorBO());
			String type="";
			if(sectionId==1){
				type="Quant";
			}else if(sectionId==2){
				type="Verbal";
			}
		
		
		if(((assignMentorController.checkMentorAvilability(getMentorBO(), userid,sectionId)).size())>=1){
			this.setErrorMsg("Already "+type+" Mentor is Avilable");
			studentList=assignMentorController.getStdents();
			mentorStudentList=assignMentorController.getMentorStdents(sectionId);
			
			finalList=new ArrayList<MentorBO>();
			
			
			for(MentorBO s:studentList){
				 MentorBO bo=new MentorBO();
				 boolean find=false;
				 for(MentorBO m:mentorStudentList){
					
					if(s.getUser_id()==m.getUser_id()){
						//System.out.println("Mentor Name\t"+m.getMentorName()+""+m.getUser_id());
						bo.setUser_id(m.getUser_id());
						bo.setStudentName(m.getStudentName());
						bo.setEmail(m.getEmail());
						bo.setMentorName(m.getMentorName());
						//System.out.println("Mentor Name\t"+m.getMentorName());
						find=true;
					}
					
				  }if(!find){
					
					bo.setUser_id(s.getUser_id());
					bo.setStudentName(s.getStudentName());
					bo.setEmail(s.getEmail());
					bo.setMentorName("");
					
				}
				
				finalList.add(bo);
			    }
			
			
			
			
		      }else{
		
		assignMentorController.addMentors(this.getMentorBO(), userid,sectionId);
		this.setSuccessMsg("Mentor Assigned Successfully");
		studentList=assignMentorController.getStdents();
		mentorStudentList=assignMentorController.getMentorStdents(sectionId);		
		finalList=new ArrayList<MentorBO>();
		
		
		for(MentorBO s:studentList){
			MentorBO bo=new MentorBO();
			 boolean find=false;
			for(MentorBO m:mentorStudentList){
				
				if(s.getUser_id()==m.getUser_id()){
					//System.out.println("Mentor Name\t"+m.getMentorName()+""+m.getUser_id());
					bo.setUser_id(m.getUser_id());
					bo.setStudentName(m.getStudentName());
					bo.setEmail(m.getEmail());
					bo.setMentorName(m.getMentorName());
					//System.out.println("Mentor Name\t"+m.getMentorName());
					find=true;
				}
				
			}if(!find){
				
				bo.setUser_id(s.getUser_id());
				bo.setStudentName(s.getStudentName());
				bo.setEmail(s.getEmail());
				bo.setMentorName("");
				
			}
			
			finalList.add(bo);
		}
		
		}
		
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("Error Raised  "+e);
		}
		return SUCCESS;
	}
	
	
	
	public String updateMentors(){
		

		System.out.println("Asign List Length"+userid.length);
		System.out.println("Mentor Id\t"+this.mentorBO.getMentorId());
		try{
		int sectionId=assignMentorController.getSectionId(this.getMentorBO());
		
		if(((assignMentorController.checkMentorAvilability(getMentorBO(), userid,sectionId)).size())==0){
			this.setErrorMsg("Please Assign The Mentor For Updation");
			studentList=assignMentorController.getStdents();
			mentorStudentList=assignMentorController.getMentorStdents(sectionId);
			
			finalList=new ArrayList<MentorBO>();
			
			
			for(MentorBO s:studentList){
				 MentorBO bo=new MentorBO();
				 boolean find=false;
				 for(MentorBO m:mentorStudentList){
					
					if(s.getUser_id()==m.getUser_id()){
						//System.out.println("Mentor Name\t"+m.getMentorName()+""+m.getUser_id());
						bo.setUser_id(m.getUser_id());
						bo.setStudentName(m.getStudentName());
						bo.setEmail(m.getEmail());
						bo.setMentorName(m.getMentorName());
						//System.out.println("Mentor Name\t"+m.getMentorName());
						find=true;
					}
					
				  }if(!find){
					
					bo.setUser_id(s.getUser_id());
					bo.setStudentName(s.getStudentName());
					bo.setEmail(s.getEmail());
					bo.setMentorName("");
					
				}
				
				finalList.add(bo);
			    }
			
			
			
			
		      }else{
		
		assignMentorController.updateMentors(this.getMentorBO(), userid);
		this.setSuccessMsg("Mentor Updated Successfully");
		studentList=assignMentorController.getStdents();
		mentorStudentList=assignMentorController.getMentorStdents(sectionId);		
		finalList=new ArrayList<MentorBO>();
		
		
		for(MentorBO s:studentList){
			MentorBO bo=new MentorBO();
			 boolean find=false;
			for(MentorBO m:mentorStudentList){
				
				if(s.getUser_id()==m.getUser_id()){
					//System.out.println("Mentor Name\t"+m.getMentorName()+""+m.getUser_id());
					bo.setUser_id(m.getUser_id());
					bo.setStudentName(m.getStudentName());
					bo.setEmail(m.getEmail());
					bo.setMentorName(m.getMentorName());
					//System.out.println("Mentor Name\t"+m.getMentorName());
					find=true;
				}
				
			}if(!find){
				
				bo.setUser_id(s.getUser_id());
				bo.setStudentName(s.getStudentName());
				bo.setEmail(s.getEmail());
				bo.setMentorName("");
				
			}
			
			finalList.add(bo);
		}
		
		}
		
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("Error Raised  "+e);
		}
		return SUCCESS;
	}
	
	
	public String deleteMentor(){
		try{
		int sectionId=assignMentorController.getSectionId(this.getMentorBO());
		
		if(assignMentorController.deleteMentors(this.getMentorBO(), userid).equalsIgnoreCase("success")){
		this.setSuccessMsg("Mentors Deleted Successfully");
		}else{
			this.setErrorMsg("No Mentor To Delete");
		}
		studentList=assignMentorController.getStdents();
		mentorStudentList=assignMentorController.getMentorStdents(sectionId);
		
		finalList=new ArrayList<MentorBO>();
		
		
		for(MentorBO s:studentList){
			 MentorBO bo=new MentorBO();
			 boolean find=false;
			 for(MentorBO m:mentorStudentList){
				
				if(s.getUser_id()==m.getUser_id()){
					//System.out.println("Mentor Name\t"+m.getMentorName()+""+m.getUser_id());
					bo.setUser_id(m.getUser_id());
					bo.setStudentName(m.getStudentName());
					bo.setEmail(m.getEmail());
					bo.setMentorName(m.getMentorName());
					//System.out.println("Mentor Name\t"+m.getMentorName());
					find=true;
				}
				
			  }if(!find){
				
				bo.setUser_id(s.getUser_id());
				bo.setStudentName(s.getStudentName());
				bo.setEmail(s.getEmail());
				bo.setMentorName("");
				
			}
			
			finalList.add(bo);
		    }
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug("Error Raised  "+e);
		}
		return SUCCESS;
	}
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		
	}
	
	
	public List<MentorBO> getMentorlist() {
		return mentorlist;
	}

	public void setMentorlist(List<MentorBO> mentorlist) {
		this.mentorlist = mentorlist;
	}

	public MentorBO getMentorBO() {
		return mentorBO;
	}

	public void setMentorBO(MentorBO mentorBO) {
		this.mentorBO = mentorBO;
	}
	
	
	public List<MentorBO> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<MentorBO> studentList) {
		this.studentList = studentList;
	}

	public List<MentorBO> getMentorStudentList() {
		return mentorStudentList;
	}

	public void setMentorStudentList(List<MentorBO> mentorStudentList) {
		this.mentorStudentList = mentorStudentList;
	}
	

	public List<MentorBO> getFinalList() {
		return finalList;
	}

	public void setFinalList(List<MentorBO> finalList) {
		this.finalList = finalList;
	}
	

	public int[] getUserid() {
		return userid;
	}

	public void setUserid(int[] userid) {
		this.userid = userid;
	}
	

	public String getSuccessMsg() {
		return successMsg;
	}

	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}




}
