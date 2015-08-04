package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.greenbuds.crunchprep.controller.contentcreator.LessonController;
 
import com.greenbuds.crunchprep.exception.CPException;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.greenbuds.crunchprep.bo.contentcreator.LessonTreeModel;
import com.greenbuds.crunchprep.bo.contentcreator.SubLessonBO; 
import com.greenbuds.crunchprep.bo.common.SectionBO;
import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.controller.common.CommonsController;

public class SubLessonsAction extends ActionSupport implements ServletRequestAware{
	public List<SubLessonBO> getLessonStructure() {
		return lessonStructure;
	}
	public void setLessonStructure(List<SubLessonBO> lessonStructure) {
		this.lessonStructure = lessonStructure;
	}
	private List<SectionBO> sections=new ArrayList<SectionBO>(); 
	private HashMap<Integer,String> sectionBasedLessons=new HashMap<Integer,String>();
	private List<SubLessonBO> lessonStructure;
	private List<SubLessonBO> subLessons;
	private HttpServletRequest request;
	private Logger logger=Logger.getLogger(SubLessonsAction.class);
	private CommonsController commonsController;
	private LessonController subLessonController;
	private SubLessonBO subLessonBO;
	private List<SkillBO> skills;
	private int sub_lesson_id;
	private String successMsg;
	private String errorMsg;
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
	public int getSub_lesson_id() {
		return sub_lesson_id;
	}
	public void setSub_lesson_id(int sub_lesson_id) {
		this.sub_lesson_id = sub_lesson_id;
	}
	private int sublesson = 0;
	public SubLessonBO getSubLessonBO() {
		return subLessonBO;
	}
	public void setSubLessonBO(SubLessonBO subLessonBO) {
		this.subLessonBO = subLessonBO;
	}
	/*List<String> array = new ArrayList<String>();*/
	public List<SkillBO> getSkills() {
		return skills;
	}
	public void setSkills(List<SkillBO> skills) {
		this.skills = skills;
	}
	ActionContext act = ActionContext.getContext();
	HttpServletResponse response = (HttpServletResponse)act.get(org.apache.struts2.ServletActionContext.HTTP_RESPONSE);
	public List<SubLessonBO> getSubLessons() {
		return subLessons;
	}
	public void setSubLessons(List<SubLessonBO> subLessons) {
		this.subLessons = subLessons;
	}
	
 
	
public String execute()
{
	String result="";
	result="success";
	commonsController=commonsController.getInstance();
	subLessonController=subLessonController.getInstance();
	 try {
		sections=commonsController.getSectionsList();
		skills=commonsController.getSkillsList("-1");
		lessonStructure=subLessonController.getLessonStructure();
		subLessons=subLessonController.getSubLessons(-1); 
		for(SubLessonBO a:subLessons)
		 {  
			 boolean find=false;
			 String parent_name="";
			 String lesson_type="mainlesson";
			 int d=0,l=0;
			 for(SubLessonBO b:lessonStructure)
			 {
				 //System.out.println("Ids:"+b.getSubLessonIds());
				 if(b.getChild_id()==a.getSublesson_id())
				 { 
					 find=true;
					 d=b.getParent_id();
					 l=b.getId(); 
					 for(SubLessonBO c:subLessons)
						{
						  if(c.getSublesson_id()==d)
						  {
							  parent_name=c.getSub_lessonname();
						  }
						  lesson_type="sublesson";
						} 
			     } 
			 }
			 a.setParent_id(d);
			 a.setParent_lesson_name(parent_name);
			 a.setLesson_type(lesson_type);
			 a.setId(l); 
			// System.out.println("Lessonid:"+a.getSublesson_id()+"\t lesson type:"+a.getLesson_type());
		 } 
		  
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return result;
}
public List<SectionBO> getSections() {
	return sections;
}
public void setSections(List<SectionBO> sections) {
	this.sections = sections;
}
public void getSectionBasedLessons()
{
	subLessonController=subLessonController.getInstance();
	try {
		sectionBasedLessons=subLessonController.getLessonBasedonSections(Integer.parseInt(request.getParameter("sectionid").toString())); 
		String onlyOptions = "<option value=-1>--Select--</option>";
		boolean loopEntere = false;
		if (sectionBasedLessons != null) {
			for (int stateId : sectionBasedLessons.keySet()) {
				loopEntere = true;
				onlyOptions += "<option value=" + stateId + ">"
						+ sectionBasedLessons.get(stateId).toString() + "</option>";
			}
		}
		if (!loopEntere)
			onlyOptions += "<option></option>";
		try {
			response.getWriter().write(
					"{\"lessons\":\"" + onlyOptions + "\"}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void LessonHeirarchy()
{ 
	subLessonController=subLessonController.getInstance(); 
	try {
		List<LessonTreeModel> jsonlist = new ArrayList<LessonTreeModel>(); 
		List<Integer> totalParents=new ArrayList<Integer>();
		lessonStructure=subLessonController.getLessonStructure();
		subLessons=subLessonController.getSubLessons(Integer.parseInt(request.getParameter("lessonid").toString()));
	    List<SubLessonBO> relatedParents=new ArrayList<SubLessonBO>();
	 
	   for(int i=0;i<lessonStructure.size();i++)
		 {
			 SubLessonBO a = lessonStructure.get(i);
			 for(int j=0;j<subLessons.size();j++)
			 {
				 SubLessonBO b = subLessons.get(j);
				 if(a.getChild_id()==b.getSublesson_id())
				 {
					 b.setParent_id(a.getParent_id());
				 }
			 }
		 }
		 
		 List<Integer> parents = new ArrayList<Integer>();
		 for(int j=0;j<subLessons.size();j++)
		 {
			 SubLessonBO b = subLessons.get(j);
			 if(b.getParent_id()==0)
			 {
				 parents.add(b.getSublesson_id());
			 } 
		 }   
			  for(int m=0;m<parents.size();m++)
				{  
					LessonTreeModel lessonTreeModel=new LessonTreeModel(); 
					lessonTreeModel.setId(parents.get(m));
					for(int l=0;l<subLessons.size();l++)
					{  
						SubLessonBO d=subLessons.get(l);   
						if(parents.get(m)==d.getSublesson_id())
						{    
							lessonTreeModel.setText(d.getSub_lessonname());
							lessonTreeModel.setChildren(getChilds(lessonStructure,subLessons,d.getSublesson_id())) ; 
						}
					} 
					 jsonlist.add(lessonTreeModel); 
				}   
			String json = new net.sf.json.JSONArray().fromObject(jsonlist).toString();  
			getPrintWriter().write(json); 
			System.out.println("Json list:"+json); 
		
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
} 

public  List<LessonTreeModel> getChilds(List<SubLessonBO> st,List<SubLessonBO> lessons,int lessonid)
{
	//List<LessonTreeModel> childNodes=new ArrayList<LessonTreeModel>();
	List<LessonTreeModel> childNodes=new ArrayList<LessonTreeModel>();
	for(int i=0;i<st.size();i++)
	{ 
	 LessonTreeModel m=new LessonTreeModel();
	 SubLessonBO a=st.get(i);
	 if(a.getParent_id()==lessonid)
	 {
		 m.setId(a.getChild_id());
		// System.out.println("Childs:"+a.getChild_id());
		 for(int j=0;j<lessons.size();j++)
		 {
			 SubLessonBO b=lessons.get(j); 
			 if(b.getSublesson_id()==a.getChild_id())
			 {
				 m.setText(b.getSub_lessonname());   
			 }
		 }
		 childNodes.add(m);
	 }
	}  
	return childNodes;
} 
 
public String  saveSublessons(){
	String result="";
	//System.out.println(" Section Name:"+getSubLessonBO().getSectionId()+"\t Main Lessonid:"+subLessonBO.getLessonId()+"\tText:"+getSubLessonBO().getLesson_text()+"\tVideo:"+getSubLessonBO().getLesson_video()+"\tType:"+getSubLessonBO().getAccess_type()+"\t status:"+getSubLessonBO().getStatus());
	//System.out.println("parent_id:"+request.getParameter("parent_id")); 
	
	String less_text="",text=""; 
	less_text=request.getParameter("lessontext").toString();
	if(getSubLessonBO().getAccess_type()==null)
	{
		getSubLessonBO().setAccess_type("PAID");
	}
	if(less_text.contains("\\"))
	{
	 text=less_text.replace("\\", "\\\\"); 
	}
	else
	{
		text=request.getParameter("lessontext").toString();
	}    
	getSubLessonBO().setLesson_text(text);
	subLessonController=subLessonController.getInstance();
	try {
		if(this.getSub_lesson_id()==0)
		{
			
			this.getSubLessonBO().setChild_id(Integer.parseInt(request.getParameter("parent_id").toString())); 
			lessonStructure=subLessonController.getLessonStructure(); 
			if(subLessonController.saveSubLessons(getSubLessonBO()))
			{ 
				this.setSuccessMsg("Sublessons Added Successfully");
				result="success";
			}
			else
			{
				this.setErrorMsg("Sublesson with the same name exists");
				result="error";
			} 
			subLessons=subLessonController.getSubLessons(-1);  
			for(SubLessonBO a:subLessons)
			 {  
				 boolean find=false;
				 String parent_name="";
				 String lesson_type="mainlesson";
				 int d=0,l=0;
				 for(SubLessonBO b:lessonStructure)
				 {
					 //System.out.println("Ids:"+b.getSubLessonIds());
					 if(b.getChild_id()==a.getSublesson_id())
					 { 
						 find=true;
						 d=b.getParent_id();
						 l=b.getId(); 
						 for(SubLessonBO c:subLessons)
							{
							  if(c.getSublesson_id()==d)
							  {
								  parent_name=c.getSub_lessonname();
							  }
							}
						 lesson_type="sublesson";
				     } 
				 }
				 a.setParent_id(d);
				 a.setParent_lesson_name(parent_name);
				 a.setLesson_type(lesson_type);
				 a.setId(l); 
			 } 
		}
		else
		{ 
			
			this.getSubLessonBO().setSublesson_id(getSub_lesson_id()); 
			this.getSubLessonBO().setParent_id(Integer.parseInt(request.getParameter("parent_id").toString()));
			this.getSubLessonBO().setChild_id(this.getSub_lesson_id());
			if(subLessonController.updateSubLessons(getSubLessonBO()))
			{
				this.setSuccessMsg("Sublessons updated Successfully");
				result="success";
			}
			else
			{
				this.setErrorMsg("Error occured while updating!");
				result="error";
			}
			subLessons=subLessonController.getSubLessons(-1); 
			lessonStructure=subLessonController.getLessonStructure();
			for(SubLessonBO a:subLessons)
			 {  
				 boolean find=false;
				 String parent_name="";
				 String lesson_type="mainlesson";
				 int d=0,l=0;
				 for(SubLessonBO b:lessonStructure)
				 {
					 //System.out.println("Ids:"+b.getSubLessonIds());
					 if(b.getChild_id()==a.getSublesson_id())
					 { 
						 find=true;
						 d=b.getParent_id();
						 l=b.getId(); 
						 for(SubLessonBO c:subLessons)
							{
							  if(c.getSublesson_id()==d)
							  {
								  parent_name=c.getSub_lessonname();
							  }
							}
						 lesson_type="sublesson";
				     } 
				 }
				 a.setParent_id(d);
				 a.setParent_lesson_name(parent_name);
				 a.setLesson_type(lesson_type);
				 a.setId(l); 
			 }  
		}
		
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	result="success";
	return result;
}
public String deleteSublessons()
{
	String result=""; 
	subLessonController=subLessonController.getInstance();
	try {
		if(subLessonController.deleteSubLessons(this.getSubLessonBO().getRecordsToDelete()))
		{
			result="success";
			this.setSuccessMsg("Record Deleted Successfully!");
		}
		else
		{
			System.out.println("Record Deletion Failed");
			result="error";
			this.setErrorMsg("Childs Exists or Record is in Use!");
			System.out.println("Deletion failed");
		}
		subLessons=subLessonController.getSubLessons(-1);
		lessonStructure=subLessonController.getLessonStructure();
		for(SubLessonBO a:subLessons)
		 {  
			 boolean find=false;
			 String parent_name="";
			 String lesson_type="mainlesson";
			 int d=0,l=0;
			 for(SubLessonBO b:lessonStructure)
			 {
				 //System.out.println("Ids:"+b.getSubLessonIds());
				 if(b.getChild_id()==a.getSublesson_id())
				 { 
					 find=true;
					 d=b.getParent_id();
					 l=b.getId(); 
					 for(SubLessonBO c:subLessons)
						{
						  if(c.getSublesson_id()==d)
						  {
							  parent_name=c.getSub_lessonname();
						  }
						}
					 lesson_type="sublesson";
			     } 
			 }
			 a.setParent_id(d);
			 a.setParent_lesson_name(parent_name);
			 a.setLesson_type(lesson_type);
			 a.setId(l); 
		 }  
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	result="success";
	return result;
}
public static HttpServletResponse getResponse() {
	HttpServletResponse response = ServletActionContext.getResponse();
	response.setContentType("text/html;charset=UTF-8");
	return response;
}

public PrintWriter getPrintWriter() {
	PrintWriter pw = null;
	try {
		pw = getResponse().getWriter();
	} catch (IOException e) {
		e.printStackTrace();
	}
	return pw;
}
@Override
public void setServletRequest(HttpServletRequest request) {
	// TODO Auto-generated method stub
	this.request=request;
}
}
