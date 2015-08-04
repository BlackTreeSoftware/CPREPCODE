package com.greenbuds.crunchprep.action.admin;




import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.directwebremoting.util.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.adminbo.AdminBO;
import com.greenbuds.crunchprep.bo.mentor.MentorBO;

import com.greenbuds.crunchprep.controller.admin.IAdminController;
import com.greenbuds.crunchprep.controller.contentcreator.QuestionUploadController;
import com.greenbuds.crunchprep.controller.mentor.IMentorController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements SessionAware,ServletRequestAware,ServletResponseAware,RequestAware{
private  final static Logger LOGGER = Logger.getLogger(AdminAction.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AdminBO adminBO;

	private List<AdminBO> questionsList;
	private List<AdminBO> lessonsList;
	private List<AdminBO> userList;
	private List<AdminBO> mentorList;
	
	public List<AdminBO> getMentorList() {
		return mentorList;
	}
	public void setMentorList(List<AdminBO> mentorList) {
		this.mentorList = mentorList;
	}
	public List<AdminBO> getUserList() {
		return userList;
	}
	public void setUserList(List<AdminBO> userList) {
		this.userList = userList;
	}

	private List<AdminBO> logsList;
	public List<AdminBO> getQuestionsList() {
		return questionsList;
	}
	public List<AdminBO> getLessonsList() {
		return lessonsList;
	}
	public void setLessonsList(List<AdminBO> lessonsList) {
		this.lessonsList = lessonsList;
	}
	public void setQuestionsList(List<AdminBO> questionsList) {
		this.questionsList = questionsList;
	}


	private List<AdminBO> ipList;

	@Autowired
	private IAdminController adminController;

	private Map<String, Object> sessionMap;
	private HttpServletRequest request;
	private HttpServletResponse response;
   @Autowired
   private QuestionUploadController questionUploadController;
   private Map<String, Object> requestMap;
	
	public String getViewLogs(){
			
			System.out.println("in getViewLogs()");
			try {
				logsList=adminController.getViewLogs();
			} catch (CPException e) {
				// TODO Auto-generated catch block
				LOGGER.debug("Error Raised  "+e);
				e.printStackTrace();
			}
			System.out.println("List Size is\t"+logsList.size());
			return SUCCESS;
	}

	public String ipAddresses(){
		try {
			ipList=adminController.ipAddresses();
		} catch (CPException e) {
			// TODO Auto-generated catch block
			LOGGER.debug("Error Raised  "+e);
			e.printStackTrace();
		}
		System.out.println("List Size is\t"+ipList.size());
		return SUCCESS;
	}
	
	public String adminMentorStudents(){
		String result="";
		try {
			adminMentorStudents=adminController.adminMentorStudents();
			result="success";
		} catch (CPException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public List<AdminBO> adminMentorStudents;
	
	public List<AdminBO> getAdminMentorStudents() {
		return adminMentorStudents;
	}
	public void setAdminMentorStudents(List<AdminBO> adminMentorStudents) {
		this.adminMentorStudents = adminMentorStudents;
	}
	public List<AdminBO> getIpList() {
		return ipList;
	}
	public void setIpList(List<AdminBO> ipList) {
		this.ipList = ipList;
	}
	

	public AdminBO getAdminBO() {
		return adminBO;
	}
	public void setAdminBO(AdminBO adminBO) {
		this.adminBO = adminBO;
	}
	public List<AdminBO> getLogsList() {
		return logsList;
	}
	public void setLogsList(List<AdminBO> logsList) {
		this.logsList = logsList;
	}


 public String noofQuestionsPerDay(){
	
		try {
			questionsList = adminController.noofQuestions_per_Day(adminBO);
			 
		} catch (DBException e) {
			LOGGER.debug("DB Ecxepotion is raised"+e);
			
		} catch (SQLException e) {
			LOGGER.debug("SQL Exception is raised"+e);
			
		}
	
	 return SUCCESS;
	
	
}

	
	
	
public String noofLessonsPerDay(){
	
	try {
		lessonsList = adminController.noofLessons_per_Day(adminBO);
		
		
	} catch (DBException e) {
		LOGGER.debug("DB Exception is raised:"+e);
		
	} catch (SQLException e) {
		LOGGER.debug("SQL Exception is raised:"+e);
		
	}


 return SUCCESS;

}

public void noofUsersGraph() throws IOException, JSONException{
	JSONObject  object = new JSONObject();
	  try {
	
		adminController.noofUserGraph(object);	
		
	} catch (JSONException e) {
		LOGGER.debug("JSON Exception is raised:"+e);
	}
	//System.out.println("\n\n Json Object is; "+object);
	response.getWriter().write(object.toString());
}


public String getMentorAccounts(){
	String result="";
	try {
		mentorList=adminController.getMentorAccounts();
		result="success";
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		result="error";
	}
	return result;
}

@Autowired
private IMentorController mentorController;
private List<MentorBO> mentorStudentList;
public String getMentorStudentAccounts() throws SQLException{
	String result="success";
	System.out.println("in getMentorStudentAccounts");
	System.out.println(adminBO.getId());
	int mentorid=this.getAdminBO().getId();
	try {
		mentorStudentList=mentorController.getMentorStudentLogs(mentorid);
		System.out.println("List size\t"+mentorStudentList.size());
		result="success";
	} catch (CPException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		result="error";
	}
	return result;
	
}



public List<MentorBO> getMentorStudentList() {
	return mentorStudentList;
}
public void setMentorStudentList(List<MentorBO> mentorStudentList) {
	this.mentorStudentList = mentorStudentList;
}
@Override
public void setSession(Map<String, Object> sessionMap) {
     this.sessionMap = sessionMap;
	
}
@Override
public void setServletRequest(HttpServletRequest request) {
	 this.request=request;
	
}
@Override
public void setServletResponse(HttpServletResponse response) {
	 this.response = response;
	
}
@Override
public void setRequest(Map<String, Object> requestMap) {
	this.requestMap=requestMap;
	
}





}
