package com.greenbuds.crunchprep.action.questions;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.controller.common.ICommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.IQuestionUploadController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.QuestionType;
import com.greenbuds.crunchprep.setups.Section;
import com.greenbuds.crunchprep.setups.TestMaster;
import com.greenbuds.crunchprep.util.QuestionTypeUtil;

/**
 * The Class AwaAction.
 */
public class AwaAction implements SessionAware, ServletRequestAware,
		ServletResponseAware, RequestAware {
	@Autowired
	IQuestionUploadController questionUploadController;
	@Autowired
	private ICommonsController commonsController;
	private QuestionsUploadBO uploadBO;
	private Map<String, Object> sessionMap;
	private HttpServletResponse response;
	private HttpServletRequest request;
	private Map<String, Object> requestMap; 
	
	private List<QuestionsUploadBO> awalist;

//	public void addAwaDetails() throws JSONException, IOException {
//		System.out.println("in awa adding details.....");
//		CPException exception = null;
//		JSONObject object = new JSONObject();
//		try {
//			this.getUploadBO().setTest_id(TestMaster.PRACTICE_SESSION.getTestMasterId());
//			this.getUploadBO().setSection_id(Section.AWA.getSectionId());
//			questionUploadController.saveAwaQuestions(this.getUploadBO());
//		} catch (DBException | ConnectionException e) {
//			// TODO Auto-generated catch block
//			exception = e;
//			e.printStackTrace();
//		}
//		System.out.println("The fidndefe mesg s---------------------"+exception.getMessage());
//
//		object.put("exceptionRaised", (exception != null));
//		if (exception != null)
//			object.put("exceptionIs", exception.getMessage());
//		object.put("operationStatus", this.getUploadBO().isOperationSuccess());
//		response.getWriter().write(object.toString());
//	}

	
	public void addAwaDetails() throws JSONException, IOException {
		System.out.println("in awa adding details.....");
		CPException exception = null;
		JSONObject object = new JSONObject();
		String msg="";
		try {
			this.getUploadBO().setTest_id(TestMaster.PRACTICE_SESSION.getTestMasterId());
			this.getUploadBO().setSection_id(Section.AWA.getSectionId());
			this.getUploadBO().setQuestion_directions("");
			
			if(this.getUploadBO().getQuestion_id()==0){
			questionUploadController.saveAwaQuestions(this.getUploadBO());
			System.out.println(this.getUploadBO().isOperationSuccess());
			if(this.getUploadBO().isOperationSuccess())
			 msg="Added SuccessFully";
			}else{
				questionUploadController.saveAwaQuestions(this.getUploadBO());
				System.out.println(this.getUploadBO().isOperationSuccess());
				if(this.getUploadBO().isOperationSuccess())
				 msg="Updated SuccessFully";
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//exception = e;
		     msg="Please try again";
		//	e.printStackTrace();
			if(e.getMessage().contains("Duplicate entry"))
				msg="Question Type Exsists";
			
			object.put("caughtexception", true);
			object.put("exception", msg);
		}
		object.put("message", msg);
		
		response.getWriter().write(object.toString());
	}
	
public String awadetails() throws JSONException, IOException {
	JSONObject jsonObject=new JSONObject();
	CPException cpException=null;
//		this.getUploadBO().setTest_id(TestMaster.PRACTICE_SESSION.getTestMasterId());
//		this.getUploadBO().setSection_id(Section.AWA.getSectionId());
	    try {
			questionUploadController.getAwaQuestionsDetails(jsonObject);
		} catch (ConnectionException | DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n  FInal Input data is: "+jsonObject.toString());
		requestMap.put("awatableData", jsonObject.toString());
		return "success";
	}

public String deleteawadetails() throws JSONException, IOException 
{
	//System.out.println("IN AWA Deleting details.....");
	CPException exception = null;
	JSONObject object = new JSONObject();
	String status = "error";
			try {

		status=questionUploadController.deleteAwaQuestions(request.getParameter("deltelist"));
	} catch (DBException | ConnectionException e) {
		// TODO Auto-generated catch block
		exception = e;
		e.printStackTrace();
	}
			
			if(status.equalsIgnoreCase("true"))
				status="success";
			return status;
			
//	object.put("exceptionRaised", (exception != null));
//	if (exception != null)
//		object.put("exceptionIs", exception.getMessage());
//	object.put("operationStatus", status);
//	response.getWriter().write(object.toString());
		
	
}




public String getupdatepagedetails()
{try
{
	String k=request.getParameter("questionid");
	setUploadBO(new QuestionsUploadBO());
	System.out.println("The values are"+request.getParameter("questionid")+"topic"+request.getParameter("questiotopic")+"directions"+request.getParameter("questiodirec")+""+request.getParameter("status")+"categpryid"+request.getParameter("testid")+""+request.getParameter("accestype"));
	this.getUploadBO().setQuestion_id(Integer.parseInt(k));
	this.getUploadBO().setQuestion_type(request.getParameter("questiotopic"));
//	this.getUploadBO().setQuestion_directions(request.getParameter("questiodirec"));
	this.getUploadBO().setQuestion_directions("");
	this.getUploadBO().setStatus(request.getParameter("status"));
	this.getUploadBO().setAccess_type(request.getParameter("accestype"));
	this.getUploadBO().setCategory_id(Integer.parseInt((String)request.getParameter("testid")));
}
catch(Exception e)
{
	e.printStackTrace();
}
	return "success";
}



	public QuestionsUploadBO getUploadBO() {
		return uploadBO;
	}

	public void setUploadBO(QuestionsUploadBO uploadBO) {
		this.uploadBO = uploadBO;
	}

	@Override
	public void setSession(Map<String, Object> sessionMap) {
		
		this.sessionMap = sessionMap;

	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;

	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;

	}

	@Override
	public void setRequest(Map<String, Object> requestMap) {
		this.requestMap = requestMap;

	}


	public List<QuestionsUploadBO> getAwalist() {
		return awalist;
	}


	public void setAwalist(List<QuestionsUploadBO> awalist) {
		this.awalist = awalist;
	}
	

}
