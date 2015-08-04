package com.greenbuds.crunchprep.action.lessons;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONException;

import org.apache.log4j.Logger;
import org.apache.struts2.dispatcher.RequestMap;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO; 
import com.greenbuds.crunchprep.bo.practicesession.CustomizationBO;
import com.greenbuds.crunchprep.bo.registration.LoginUserBO;
import com.greenbuds.crunchprep.bo.server.ServerProperties;
import com.greenbuds.crunchprep.controller.lessons.LessonsController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;
import com.opensymphony.xwork2.ActionSupport;

/**
 * The Class LessonsAction.
 */
public class LessonsAction extends ActionSupport implements SessionAware,ServletResponseAware,ServletRequestAware,RequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Map<String, Object> sessionMap;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private final static Logger LOGGER = Logger.getLogger(LessonsAction.class);
	private String result=null;
	private LessonsmasterBO lessonsmasterBO=null;
	private String successMsg;
	private String errorMsg;
	private List<LessonsmasterBO> lessonList=null;
	private String name;
	
	
	
	
	public String getSuccessMsg() {
		return successMsg;
	}


	public List<LessonsmasterBO> getLessonList() {
		return lessonList;
	}


	public void setLessonList(List<LessonsmasterBO> lessonList) {
		this.lessonList = lessonList;
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

	@Autowired
	LessonsController lessonsController ;
	private Map<String, Object> requestMap;
	
	
	
	
/**
 * Adds the new confidence.
 *
 * @return the string
 * @throws org.json.JSONException 
 */
	public void addNewConfidence() throws JSONException, IOException, org.json.JSONException{
		
		
		//   System.out.println("Add New Confidence Level Action is Calling");
		  
		    result = "Error".toLowerCase();
			JSONObject responseData = new JSONObject();
	         lessonsController=LessonsController.getInstance();
			  try {
				  lessonsmasterBO.setSublesson_id(Integer.parseInt(sessionMap.get("sublesson_id").toString()));
				if(lessonsController.levelChecking(lessonsmasterBO)==false){  	       
					if(lessonsController.addConfidenceLevel(lessonsmasterBO).equalsIgnoreCase("success")){	      
						   this. setSuccessMsg("Add Confidence Level Succesfully");
						    result ="success";
						    responseData.put("result",result);
					 }else{	 
						  this. setSuccessMsg("Add Confidence Level Failed");
						  result ="error";
						  responseData.put("result",result);
						 
					 }
			
	}
				 else{
					 try {
						 lessonsmasterBO.setSublesson_id(Integer.parseInt(sessionMap.get("sublesson_id").toString()));
						if(lessonsController.updateConfidenceLevel(lessonsmasterBO).equalsIgnoreCase("success")){
						     confidenceLevelList();
						     this. setSuccessMsg("Update Confidence Level Succesfully");
							 
							    result ="success";
							    responseData.put("result",result);
							 
						 }else{
							 
							      confidenceLevelList();
							      this. setSuccessMsg("Update Confidence Level Failed");
								 
								    result ="error";
							    
								   responseData.put("result",result);
							 
						 }
					} catch (DBException e) {
					     LOGGER.debug(e);
					} catch (SQLException e) {
						LOGGER.debug(e);
					}
		   
				
				 }
			} catch (SQLException e) {
				  LOGGER.debug(e);
			} catch (DBException e) {
				LOGGER.debug(e);
			}
	 	     
	 		
	 		
	 		
	 		response.getWriter().write(responseData.toString());
	 	 	 
	     }


 /**
  * Confidence level list.
  *
  * @return the string
  */
 public String confidenceLevelList(){
	//  System.out.println("Confidence list is calling@@@@@@@@@@@@@@@@@@@@@@@@@");
	  result ="Error".toLowerCase();

	 
	    try {
			lessonsController.getConfidenceLevels(lessonsmasterBO);
			 result ="success";
		} catch (DBException e) {
			 LOGGER.debug(e);
		} catch (SQLException e) {
			LOGGER.debug(e);
		}
	 
	 
	 return result;
 }


 public String getLessonDetails() throws CPException, SQLException, org.json.JSONException {
	 
	 System.out.println("In Lesson details........"+sessionMap.get("session_id").toString());
     LoginUserBO bo = (LoginUserBO) sessionMap.get("user");
     lessonsmasterBO.setUser_id(bo.getUser_id());
     lessonsmasterBO.setActivity_id(Integer.parseInt(sessionMap.get("session_id").toString()));
	 this.setLessonsmasterBO(lessonsController.getLessonDetails(lessonsmasterBO));	
	 
	 sessionMap.put("sublesson_id",lessonsmasterBO.getSublesson_id());
	 result="success";
	 
	 return result;
}
 
 
 
/*public String getGoToLessonDetails() throws CPException, SQLException, org.json.JSONException {
	 
	
     LoginUserBO bo = (LoginUserBO) sessionMap.get("user");  
	 lessonsController=LessonsController.getInstance(); 
    
     lessonsmasterBO.setUser_id(bo.getUser_id());
    
		  try { 	    
				   lessonsController.lessonsSession(lessonsmasterBO);
				     if(lessonsmasterBO!=null)  
				     sessionMap.put("session_id",lessonsmasterBO.getActivity_id());
					 sessionMap.put("last_lesson",lessonsmasterBO.getLast_lesson());
				     this.setLessonsmasterBO(lessonsController.getLessonDetails(lessonsmasterBO));	
				     sessionMap.put("sublesson_id",lessonsmasterBO.getSublesson_id());
				
				 result="success";
		} catch (SQLException e) {
			e.printStackTrace();
			  LOGGER.debug(e);
		} catch (DBException e) {
			e.printStackTrace();
			LOGGER.debug(e);
		} 
	 
	 return result;
}
 */
 
 public void addNotes() throws IOException, JSONException
 {	 
	 String result="";
	 try {
		 lessonsmasterBO =new LessonsmasterBO();
		 LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
	     lessonsmasterBO.setUser_id(bo.getUser_id());   
	     lessonsmasterBO.setSublesson_id(Integer.parseInt(sessionMap.get("sublesson_id").toString()));
		 lessonsmasterBO.setLesson_note_name(request.getParameter("lesson_note_name").toString());		 
		 result = lessonsController.addNotes(lessonsmasterBO);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}  
 }
 
 
 
 public void addBookMarks() throws IOException, JSONException
 { 
	 try {
		 LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
		 lessonsmasterBO= new LessonsmasterBO();
	     lessonsmasterBO.setUser_id(bo.getUser_id());   
	     lessonsmasterBO.setSublesson_id(Integer.parseInt(sessionMap.get("sublesson_id").toString()));
	     lessonsmasterBO.setLesson_bookmark(request.getParameter("lesson_bookmark"));
		 result = lessonsController.addBookMarks(lessonsmasterBO);
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	} 
 }
 
 
  public void lessonsTaken() throws JSONException, IOException, org.json.JSONException{	  
	    result = "Error".toLowerCase();
		JSONObject responseData = new JSONObject();
        lessonsController=LessonsController.getInstance();
        LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
        lessonsmasterBO.setUser_id(bo.getUser_id());
        lessonsmasterBO.setSublesson_id(Integer.parseInt(sessionMap.get("sublesson_id").toString()));
		  try { 	    
				if(lessonsController.lessonsTaken(lessonsmasterBO).equalsIgnoreCase("success")){	      
					   this. setSuccessMsg("Lesson Read Successfully");
					    result ="success"+Integer.parseInt(sessionMap.get("sublesson_id").toString());
					    responseData.put("result",result);
				 }else{		 
					  this. setSuccessMsg("Read Again !!!!");
					  result ="error";
					  responseData.put("result",result);			 
				 }
		} catch (SQLException e) {
			  LOGGER.debug(e);
		} catch (DBException e) {
			LOGGER.debug(e);
		}
		response.getWriter().write(responseData.toString());	 	 
   }

  public String lessonsSession() throws JSONException, IOException, org.json.JSONException{	  
	  
	   if(this.getLessonsmasterBO()!=null)
	   {
		   System.out.println("\n Lession Masstee BO  Inialized and subLession id is: "+this.getLessonsmasterBO().getSublesson_id());
		   requestMap.put("lessionId", this.getLessonsmasterBO().getSublesson_id());
		   
		   
		   
	   }
	   else{
		   System.out.println("\n Lession Masstee BO Not Inialized ");
	   }
	   //System.out.println("\n\n\n\t\t\t Pages is ; "+request.getParameter("pagesIs"));
	   requestMap.put("pagesIs", request.getParameter("pagesIs"));
	   System.out.println("\n");
		JSONObject responseData = new JSONObject();
        lessonsController=LessonsController.getInstance();
        LoginUserBO bo =(LoginUserBO) sessionMap.get("user");
        lessonsmasterBO=new LessonsmasterBO();
        lessonsmasterBO.setUser_id(bo.getUser_id());       	
		  try { 	    
				     lessonsController.lessonsSession(lessonsmasterBO);
				     if(lessonsmasterBO!=null) 
				     sessionMap.put("session_id",lessonsmasterBO.getActivity_id());
				     
					 sessionMap.put("last_lesson",lessonsmasterBO.getLast_lesson());
					 sessionMap.put("last_lessonid", lessonsmasterBO.getSublesson_id());
					 result ="success";
				 
		} catch (SQLException e) {
			e.printStackTrace();
			  LOGGER.debug(e);
		} catch (DBException e) {
			e.printStackTrace();
			LOGGER.debug(e);
		}
	return result; 	 
 }
  
  public String loadPractiseSessionCustomization(){
	   result ="Error".toLowerCase();
	   CustomizationBO customizationBO = new CustomizationBO();
	   customizationBO.setQuestions_count(ServerProperties.getInistance().getQuestions_count());
	   customizationBO.setQuestion_pool(ServerProperties.getInistance().getQuestion_pool());
	   customizationBO.setQuestions_mode(ServerProperties.getInistance().getQuestions_mode());
	   customizationBO.setQuestions_time_limit(ServerProperties.getInistance().getQuestions_time_limit());
	   customizationBO.setDiff_id(ServerProperties.getInistance().getDiff_id());
	   customizationBO.setLesson_id(lessonsmasterBO.getLesson_id());
	   customizationBO.setSublesson_id(lessonsmasterBO.getSublesson_id());
	   customizationBO.setSection_id(lessonsmasterBO.getSection_id());
	  
	  return result;
	  
  }
 
  
	public LessonsmasterBO getLessonsmasterBO() {
	return lessonsmasterBO;
}


public void setLessonsmasterBO(LessonsmasterBO lessonsmasterBO) {
	this.lessonsmasterBO = lessonsmasterBO;
}


	public LessonsmasterBO getLessonBO() {
		return lessonsmasterBO;
	}

	public void setLessonBO(LessonsmasterBO lessonsmasterBO) {
		this.lessonsmasterBO = lessonsmasterBO;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		
		this.request=request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		
		this.response=response;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap=sessionMap;
		
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public void setRequest(Map<String, Object> requestMap) {
	this.requestMap=requestMap;
		
	}
	
	

}
