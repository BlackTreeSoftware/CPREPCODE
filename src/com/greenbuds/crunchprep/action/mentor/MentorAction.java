package com.greenbuds.crunchprep.action.mentor;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.bo.mentor.MentorBO;
import com.greenbuds.crunchprep.bo.mentor.UserProgressBO;
import com.greenbuds.crunchprep.bo.practicesession.ResultsBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.skilldata.SkillDataBO;
import com.greenbuds.crunchprep.controller.mentor.IMentorController;
import com.greenbuds.crunchprep.controller.practicesession.IPracticeSessionController;
import com.greenbuds.crunchprep.controller.practicesession.PracticeSessionController;
import com.greenbuds.crunchprep.controller.skilldata.SkillDataController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.util.MailUtil;
import com.opensymphony.xwork2.ActionSupport;

public class MentorAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware,RequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = Logger.getLogger(MentorAction.class);

	private Map<String, Object> sessionMap;
	
    private SkillDataController skillDataController;
    private List<SkillDataBO> skillData=new ArrayList<SkillDataBO>();
    private List<SkillDataBO> quantSkillData=new ArrayList<SkillDataBO>();
    private List<SkillDataBO> verbalSkillData=new ArrayList<SkillDataBO>();
    private List<String> quantList=new ArrayList<String>();
    private List<String> verbalList=new ArrayList<String>();
    private List<UserProgressBO> lessonsProgress=new ArrayList<UserProgressBO>();
    private List<UserProgressBO> testProgress=new ArrayList<UserProgressBO>();
    private String skillDataList;
    private String quantSkillDataList;
    private String verbalSkillDataList;
    private String quantSkills;
    private String verbalSkills;
    private UserProgressBO userProgressBO;
    private String questionTypes="";
    private String correctQuestions="";
    private String wrongQuestions="";
	
	public UserProgressBO getUserProgressBO() {
		return userProgressBO;
	}

	public void setUserProgressBO(UserProgressBO userProgressBO) {
		this.userProgressBO = userProgressBO;
	}



	@Autowired
	private IMentorController mentorController;

	public MentorBO getMentorBO() {
		return mentorBO;
	}

	public void setMentorBO(MentorBO mentorBO) {
		this.mentorBO = mentorBO;
	}



	MentorBO mentorBO;
	List<MentorBO>studentList;
	List<MentorBO>studentProgressBarList;
	
	
	public List<MentorBO> getStudentProgressBarList() {
		return studentProgressBarList;
	}

	public void setStudentProgressBarList(List<MentorBO> studentProgressBarList) {
		this.studentProgressBarList = studentProgressBarList;
	}

	public List<MentorBO> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<MentorBO> studentList) {
		this.studentList = studentList;
	}




	public String getMentorAssignedStudents(){
		
		
		  try {
			  
			   LoginUserBO bo =(LoginUserBO)sessionMap.get("user");
			   System.out.println("MentorId:\t\t"+bo.getUser_id());
			 // mentorBO.setMentor_id(bo.getUser_id());
			 studentList= mentorController.mentorAssignedStudentList(bo.getUser_id());
		} catch (DBException e) {
			LOGGER.debug(e);
		} catch (SQLException e) {
			LOGGER.debug(e);
		}
		
		
		
		return SUCCESS;
		
	}
	
	
	
	
	public String getStudentProgressBars(){
		
		 LoginUserBO bo =(LoginUserBO)sessionMap.get("user");
		   System.out.println("MentorId:\t\t"+bo.getUser_id());
		   try {
			studentProgressBarList=mentorController.studentProgressList(bo.getUser_id());
		} catch (DBException e) {
			 LOGGER.debug(e);
		} catch (SQLException e) {
			 LOGGER.debug(e);
		}
		
		return SUCCESS;
		
	}
	
	
	
private List<MentorBO> studentLogsList;

private HttpServletRequest request;
	
	
	
	
	
	public String getMentorStudentLogs(){
		String result="";
		LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
		System.out.println("MentorId \t"+bo.getUser_id());
		
		try {
			studentLogsList=mentorController.getMentorStudentLogs(bo.getUser_id());
			result="success";
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug(""+e);
			result="error";
		}
		System.out.println("List Size is\t"+studentLogsList.size());
		
		return result;
	}
	
	public String viewProgress(){
		
		System.out.println(mentorBO.getUser_id());
		int userid=mentorBO.getUser_id();
		try {
		     this.mentorBO=mentorController.getStudentDetails(mentorBO.getUser_id());
		     mentorBO.setUser_id(userid);
		     LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
			 skillDataController=skillDataController.getInstance();
			 skillData=skillDataController.getSkillData(userid); 
			 for(int i=0;i<skillData.size();i++)
				{
					SkillDataBO skillDataBO=skillData.get(i);
					if(skillDataBO.getSection_name().equals("Quantitative"))
					{
						quantSkillData.add(skillDataBO);
						quantList.add(skillDataBO.getSkill()); 
					}
					if(skillDataBO.getSection_name().equals("Verbal"))
					{
						
						verbalSkillData.add(skillDataBO); 
						verbalList.add(skillDataBO.getSkill()); 
					}
				}
			 
			    skillDataList = new net.sf.json.JSONArray().fromObject(skillData).toString();
				quantSkillDataList= new net.sf.json.JSONArray().fromObject(quantSkillData).toString();
				verbalSkillDataList= new net.sf.json.JSONArray().fromObject(verbalSkillData).toString();
				quantSkills=new net.sf.json.JSONArray().fromObject(quantList).toString();
				verbalSkills=new net.sf.json.JSONArray().fromObject(verbalList).toString();
				
				request.setAttribute("quantSkills", quantSkills);
				request.setAttribute("verbalSkills", verbalSkills);
				request.setAttribute("quantSkillData", quantSkillDataList);
				request.setAttribute("verbalSkillData", verbalSkillDataList);
				
				
				
				/*String imagepath = request.getSession().getServletContext()
						.getInitParameter("userimagepath");
				String filePath = ServletActionContext.getServletContext()
						.getRealPath(imagepath);*/
				String imagepath = request.getSession().getServletContext()
							.getInitParameter("userimagepath");
					String filePath = ServletActionContext.getServletContext()
							.getRealPath("/");
					
					String temppath = request.getSession().getServletContext()
							.getInitParameter("DefaultImage");

				  File fileToCreate = new File(filePath+imagepath + mentorBO.getUser_id()	+ ".jpg");
				  String userimagepath=imagepath + mentorBO.getUser_id()	+ ".jpg";
				
				  
					filePath += userBO.getUser_id() + ".jpg";
					if (fileToCreate.exists()) {
						userBO.setAvatar_path(userimagepath);
					} else {
					
					    userBO.setAvatar_path(temppath);
					}

				//filePath = filePath + "image";
			//	System.out.println("the file path is.................................. "+ filePath);
				//System.out.println("After userid\t"+mentorBO.getUser_id());
			
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug(""+e);
		}
		
		return SUCCESS;
	}
	
	public String viewStudentProfile(){
		
		System.out.println("View Student Profile is calling@@@@@@@@@@@@@");
		
		 LoginUserBO bo =(LoginUserBO)sessionMap.get("user");
		System.out.println(bo.getUser_id());
		int userid=bo.getUser_id();
		try {
		     this.mentorBO=mentorController.getStudentDetails(bo.getUser_id());
		      LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
		     mentorBO.setUser_id(userid);
			 skillDataController=skillDataController.getInstance();
			 skillData=skillDataController.getSkillData(userid); 
			 for(int i=0;i<skillData.size();i++)
				{
					SkillDataBO skillDataBO=skillData.get(i);
					if(skillDataBO.getSection_name().equals("Quantitative"))
					{
						quantSkillData.add(skillDataBO);
						quantList.add(skillDataBO.getSkill()); 
					}
					if(skillDataBO.getSection_name().equals("Verbal"))
					{
						
						verbalSkillData.add(skillDataBO); 
						verbalList.add(skillDataBO.getSkill()); 
					}
				}
			 
			    skillDataList = new net.sf.json.JSONArray().fromObject(skillData).toString();
				quantSkillDataList= new net.sf.json.JSONArray().fromObject(quantSkillData).toString();
				verbalSkillDataList= new net.sf.json.JSONArray().fromObject(verbalSkillData).toString();
				quantSkills=new net.sf.json.JSONArray().fromObject(quantList).toString();
				verbalSkills=new net.sf.json.JSONArray().fromObject(verbalList).toString();
				
				request.setAttribute("quantSkills", quantSkills);
				request.setAttribute("verbalSkills", verbalSkills);
				request.setAttribute("quantSkillData", quantSkillDataList);
				request.setAttribute("verbalSkillData", verbalSkillDataList);
				
				
				
				String imagepath = request.getSession().getServletContext()
						.getInitParameter("userimagepath");
				String filePath = ServletActionContext.getServletContext()
						.getRealPath("/");
				
				String temppath = request.getSession().getServletContext()
						.getInitParameter("DefaultImage");
			
			  File fileToCreate = new File(filePath+imagepath + File.separator + mentorBO.getUser_id()	+ ".jpg");
			  String userImagePath=imagepath+mentorBO.getUser_id()+".jpg";
				
				if (fileToCreate.exists()) {
                  
					userBO.setAvatar_path(userImagePath);
				} else {
				
		         userBO.setAvatar_path(temppath);
				}

				
				//System.out.println("the file path is.................................. "+ filePath);
				//System.out.println("After userid\t"+mentorBO.getUser_id());
			
		} catch (CPException e) {
			
			e.printStackTrace();
			LOGGER.debug(""+e);
		}
		
		return SUCCESS;
	}
	
	
	
	
	public String mentorStudentList() throws SQLException{
		String result="";
		LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
		System.out.println("MentorId \t"+bo.getUser_id());
		try {
			studentProgressBarList=mentorController.mentorStudentList(bo.getUser_id()); 
			result="success";
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug(""+e);
			result="error";
		}
		return result;
	}
	
	
	public void mentorSendingMail() throws JSONException, IOException{
		System.out.println("UserId\t"+mentorBO.getUser_id());
		String email="";
		JSONObject obj = new JSONObject();
		
		try {
			 email=mentorController.getUsermail(mentorBO.getUser_id());
			 MailUtil.sendMailToOne("Hai", "hai", email);
			 obj.put("message", "Mail Sent Successfully");
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug(""+e);
		}
		
		response.getWriter().write(obj.toString());
		
	}
	
	private List<MentorBO> LogsList;
	
	

	public String mentorStudentLogsList(){
		String result="";
		try {
			LogsList=mentorController.mentorStudenLogstList(mentorBO.getUser_id());
			result="success";
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="error";
			LOGGER.debug("Exception"+e);
		}
		return result;
	}
	
	
	public String viewSectionProgress()
	{
		String result="success";
		int userid=mentorBO.getUser_id();
		System.out.println("User id:"+userid);
		try {
			List<String> qtypes_quant=new ArrayList<String>();
			List<Integer> correctList_quant=new ArrayList<Integer>();
			List<Integer> wrongList_quant=new ArrayList<Integer>();
			
			List<String> qtypes_verbal=new ArrayList<String>();
			List<Integer> correctList_verbal=new ArrayList<Integer>();
			List<Integer> wrongList_verbal=new ArrayList<Integer>();
			
			
			lessonsProgress=mentorController.getStudentProgress(userid); 
			testProgress=mentorController.getStudentTestProgress(userid);
			for(UserProgressBO userProgressBO:testProgress)
			{
				if(userProgressBO.getSection_id().equals("1"))
				{	
				qtypes_quant.add(userProgressBO.getQuestionType());
				correctList_quant.add(Integer.parseInt(userProgressBO.getCorrectCount()));
				wrongList_quant.add(Integer.parseInt(userProgressBO.getWrongCont()));
				}
				if(userProgressBO.getSection_id().equals("2"))
				{	
				qtypes_verbal.add(userProgressBO.getQuestionType());
				correctList_verbal.add(Integer.parseInt(userProgressBO.getCorrectCount()));
				wrongList_verbal.add(Integer.parseInt(userProgressBO.getWrongCont()));
				}
			} 
			this.setUserProgressBO(lessonsProgress.get(0));
			request.setAttribute("questionTypes_quant", new net.sf.json.JSONArray().fromObject(qtypes_quant).toString());
			request.setAttribute("correctCount_quant", new net.sf.json.JSONArray().fromObject(correctList_quant).toString());
			request.setAttribute("wrongCount_quant", new net.sf.json.JSONArray().fromObject(wrongList_quant).toString());
			
			request.setAttribute("questionTypes_verbal", new net.sf.json.JSONArray().fromObject(qtypes_verbal).toString());
			request.setAttribute("correctCount_verbal", new net.sf.json.JSONArray().fromObject(correctList_verbal).toString());
			request.setAttribute("wrongCount_verbal", new net.sf.json.JSONArray().fromObject(wrongList_verbal).toString());
			
		} catch (DBException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			LOGGER.debug(""+e);
		}
		return result;
	} 
	
	
	
	private List<ResultsBO> practiceSummaryTable_List;
	private List<MentorBO> lessonsList;

	

	public String getPracticeSummaryChartData() {
		System.out.println("in mentor calling summaryChart");
		QuestionsUploadBO questionUploadBO = new QuestionsUploadBO();
		IPracticeSessionController controller = PracticeSessionController
				.getInstance();
		LoginUserBO userBO = (LoginUserBO) sessionMap.get("user");
		
			
		if(userBO.getRole_id()!=4) questionUploadBO.setUser_id(mentorBO.getUser_id());
		else questionUploadBO.setUser_id(userBO.getUser_id());
		try {
			
			lessonsList=mentorController.mentorStudentLessonData(mentorBO.getUser_id());
			practiceSummaryTable_List = controller.practiseSessionHistoryDetails(questionUploadBO);
		 
			 
			JSONArray xAxisData = new JSONArray();
			JSONArray Originaldata = new JSONArray();
			 

		
				for (int i = 0; i < practiceSummaryTable_List.size(); i++) {

					// Originaldata.put(10*i);
					// xAxisData.put("2014-Jun-0"+i);
					//System.out.println(practiceSummaryTable_List.get(i)
							//.getTest_date());
					xAxisData.put(practiceSummaryTable_List.get(i)
							.getTest_date()+"-"+practiceSummaryTable_List.get(i).getPractiseSessioName());
					Originaldata.put(Double
							.parseDouble(practiceSummaryTable_List.get(i)
									.getSessionCorrectPercentage())); 
					//sessionNames.put(practiceSummaryTable_List.get(i - 1).getTest_date(),practiceSummaryTable_List.get(i-1).getPractiseSessioName());
					//System.out.println("session Name : "+practiceSummaryTable_List.get(i-1).getPractiseSessioName());
					if (i ==9)
						break;

				}			 	
				//System.out.println(xAxisData + "\t" + Originaldata);
				//jsonData.put("xAxisData", xAxisData);
				//jsonData.put("Originaldata", Originaldata);
				//jsonData.put("sessionNames", sessionNames);
				//response.getWriter().write(jsonData.toString());
				
				
				requestMap.put("xAxisData", xAxisData);
				requestMap.put("Originaldata", Originaldata);
			} catch (JSONException e) {
				e.printStackTrace();

		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	
	
	public List<MentorBO> getStudentLogsList() {
		return studentLogsList;
	}


	public void setStudentLogsList(List<MentorBO> studentLogsList) {
		this.studentLogsList = studentLogsList;
	}

	
	
	
	
	@Override
	public void setSession(Map<String, Object> sessionMap) {
		  this.sessionMap = sessionMap;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response=response;
		
	}
	private HttpServletResponse response;

	private Map<String, Object> requestMap;
	public List<MentorBO> getLogsList() {
		return LogsList;
	}

	public void setLogsList(List<MentorBO> logsList) {
		LogsList = logsList;
	}

	@Override
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap=requestMap;
		
	}
	
	public List<ResultsBO> getPracticeSummaryTable_List() {
		return practiceSummaryTable_List;
	}

	public void setPracticeSummaryTable_List(
			List<ResultsBO> practiceSummaryTable_List) {
		this.practiceSummaryTable_List = practiceSummaryTable_List;
	}

	public List<MentorBO> getLessonsList() {
		return lessonsList;
	}

	public void setLessonsList(List<MentorBO> lessonsList) {
		this.lessonsList = lessonsList;
	}

	

}
