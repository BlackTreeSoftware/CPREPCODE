/*
 * @(#)ReadingComprehensionQuestion.java
 * Copyright (c) 2014 Greenbuds Software Technologies.
 */
package com.greenbuds.crunchprep.action.contentcreator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.common.DifficultyBO;
import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.bo.common.TestsBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionQuestionBo;
import com.greenbuds.crunchprep.controller.common.CommonsController;
import com.greenbuds.crunchprep.controller.common.ICommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.CriticalReasoningController;
import com.greenbuds.crunchprep.controller.contentcreator.ICriticalReasoningController;
import com.greenbuds.crunchprep.controller.contentcreator.IReadingComprehensionController;
import com.greenbuds.crunchprep.controller.contentcreator.ReadingComprehensionController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;
import com.greenbuds.crunchprep.setups.AccountStatus;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

/**
* This class contains all the action methods required for the Reading Comprehension in Verbal Section
*
* @version 1.0
* @author Sahitya Mittapalli
*/
public class ReadingComprehensionQuestion implements ModelDriven<ReadingComprehensionQuestionBo>,Preparable,ServletResponseAware,ServletRequestAware {
/*Class Implementation Model Driven Interface for Making ReadingComprehensionQuestionBo available in the action class,
 * and implements preparable for initializing the RC bean before calling Action method,
 * and ServletResponseAware and ServletRequestAware for getting response and request Objects*/

	/** The Constant LOGGER. */
private final static Logger LOGGER = Logger.getLogger(ReadingComprehensionQuestion.class );

/*List of Tests*/
private List<TestsBO> testsList;

/*Passages List*/
private List<ReadingComprehensionBO> passagesList;

/*List of Skills*/
private List<SkillBO> skillsList;

/*List of Difficulties*/
private List<DifficultyBO> difficultiesList;

/*All the ReadingComprehensions Questions List*/
private List<ReadingComprehensionQuestionBo> rcQuestionsList;

/*Used to maintain Question Id's while deleting*/
private String deleteIds;

/*Used to maintain Passage Type[SMALL,MEDIUM,LARGE] for getting passages based on type*/
private String questionType;

/*Controller Objects Used in this Action Class*/
private ICommonsController commonsController;
private IReadingComprehensionController readingCompController;
private ReadingComprehensionQuestionBo readingcmpQuestionBo;

/*Request and Response Objects*/
private HttpServletResponse response;
private HttpServletRequest request;

/*No-Argument Constructor*/
public ReadingComprehensionQuestion(){
	
}

/**
 * this is used to get All the Tests List,Passages List,Skills List and difficultiesList for  Reading Comprehension Question 
 * @return "success" when all the data loaded
 */  
public String prepareInitialData()
{
	commonsController=CommonsController.getInstance();
	readingCompController=ReadingComprehensionController.getInstance();
	try {
		setTestsList(commonsController.getTestsList());
		setPassagesList(readingCompController.getReadingComprehension());
		//setSkillsList(commonsController.getSkillsList(request.getParameter("sectionId").toString()));
		setSkillsList(commonsController.getSkillsList("2"));
		setDifficultiesList(commonsController.getDifficultiesList());
	} catch (ConnectionException e) {
		LOGGER.error(Exceptions.CONNECTION_COMMIT_FAILED);
		e.printStackTrace();
	} catch (DBException e) {
		LOGGER.error(Exceptions.CONNECTION_COMMIT_FAILED);
		e.printStackTrace();
	} catch (CPException e) {
		LOGGER.error(Exceptions.CONNECTION_COMMIT_FAILED);
		e.printStackTrace();
	}
	 
	return "success";
}

/**
 * this is used to Inserting New Record for RC(Reading Comprehension) Question and Updating Already Existed RC question.
 * @return "success" when New RC saved
 *         "update"  when Already Existed RC Updated
 *         "error"   when Record Insertion/Updating Failed
 */ 
public void  saveNewReadingComprehensionQuestion()
{
	 JSONObject jsonObject=new JSONObject();
	 String result="error";
	readingCompController=ReadingComprehensionController.getInstance();
	try {
		if(readingcmpQuestionBo.getAccessType()!=null && readingcmpQuestionBo.getAccessType().equalsIgnoreCase("Free"))
		{
			readingcmpQuestionBo.setAccessType(AccountStatus.FREE.name());
		}
		else
			readingcmpQuestionBo.setAccessType(AccountStatus.PAID.name());
		if(readingcmpQuestionBo.getStatus().equalsIgnoreCase("ACTIVE"))
		{
			readingcmpQuestionBo.setStatus(AccountStatus.ACTIVE.name());
		}
		else
		{
			readingcmpQuestionBo.setStatus(AccountStatus.INACTIVE.name());
		}
		 
		if(readingcmpQuestionBo.getReferral()!=null && readingcmpQuestionBo.getReferral().equalsIgnoreCase("YES"))
		{
			readingcmpQuestionBo.setReferral("YES");
		}
		else
		{
			readingcmpQuestionBo.setReferral("NO");
		}
	//	System.out.println("Calling from rc action class");
		// String skills[]=readingcmpQuestionBo.getSkills().split(",");	
		// System.out.println(skills.length);//+"\t one : "+skills[0]+"\t two : "+skills[1]+"\t three : "+skills[2]);
		 
		if(readingcmpQuestionBo.getQuestionId()==0)
		{
			boolean b=readingCompController.saveNewReadingcmpQuestion(readingcmpQuestionBo);
			if(b)result="success";
			readingcmpQuestionBo.setQuestionId(0);
			
		}
		else{
			//System.out.println(readingcmpQuestionBo.getQuestionId());
			boolean b=readingCompController.updateReadingcmpQuestion(readingcmpQuestionBo);
			if(b)result="update";readingcmpQuestionBo.setQuestionId(0);
			//System.out.println(result);
		}
		  
	} catch (ConnectionException e) {		 
		e.printStackTrace();
	}
	try {
		jsonObject.put("result",result);
		response.getWriter().write(jsonObject.toString());
	} catch (JSONException e) {		 
		e.printStackTrace();
		LOGGER.error("JSON Exception");
	} catch (IOException e) {
		LOGGER.error("IO Exception");
		e.printStackTrace();
	}

}

/**
 * this is used to get All the Reading Comprehension Questions in the QUestion Pool
 * @return "success" by default
 */ 
public String getAlltheRCQuestions()
{ 
	readingCompController=ReadingComprehensionController.getInstance();
	try {
		setRcQuestionsList(readingCompController.getAlltheReadingComprehensionQuestions());
	} catch (ConnectionException e) {	
		LOGGER.error(Exceptions.CONNECTION_COMMIT_FAILED);
		e.printStackTrace();
	} 
	return "success";
}

/**
 * this is used to Delete One or More number of RC questions which has been selected by the user
 * @return "success" when Question/Question's Deleted Successfully 
 *         "error"   when Record failed to delete
 */ 
public String deleteSelectedRcQuestions() {
	String result="error";
	ICriticalReasoningController controller=CriticalReasoningController.getInstance();
	try {
	boolean b=controller.deleteSelectedCriticalQuestions(deleteIds);
	if(b){
		result="success";
		setRcQuestionsList(ReadingComprehensionController.getInstance().getAlltheReadingComprehensionQuestions());
	}
	} catch (ConnectionException e) {
		LOGGER.error(Exceptions.CONNECTION_COMMIT_FAILED);
		e.printStackTrace();
	}
	return result;
}

/**
 * this is used to get Passages list based on Selected Passage Type
 * Put Passages list in Json Object
 */ 
public void getPassagesBasedonType()
{
	JSONObject jsonObject=new JSONObject();
	List<ReadingComprehensionBO> data=new ArrayList<ReadingComprehensionBO>();
	readingCompController=ReadingComprehensionController.getInstance();
	try {
	setQuestionType((String)request.getParameter("questionType"));
	  data=readingCompController.getPassagesBasedonType(getQuestionType());
	} catch (ConnectionException e) {	 
		e.printStackTrace();
	}
	try {
		jsonObject.put("result",data);
		response.getWriter().write(jsonObject.toString());
	} catch (JSONException e) {		
		LOGGER.error("JSON Exception");
		e.printStackTrace();
	} catch (IOException e) {
		LOGGER.error("IO Exception");
		e.printStackTrace();
	}

}

/**
 * this method is used to get Reading COmprehension BO based on the selected Question while edit
 * returns "success" if we get the details of the selected question
 *          "error" if we don't get the details of the selected question
 */
public String obtainEditRCquestion()
{
	String result="error";
	try {
		 readingCompController=ReadingComprehensionController.getInstance();
		 String questionId1=(String)request.getParameter("questionId");//System.out.println("questionId : "+questionId1);
		 setReadingcmpQuestionBo(readingCompController.gettingParticularRCquestion(Integer.parseInt(questionId1)));
		 setRcQuestionsList(readingCompController.getAlltheReadingComprehensionQuestions());
		 if(getReadingcmpQuestionBo()!=null)
		 {
			 result="success";
			 //System.out.println("ANswers : "+getReadingcmpQuestionBo().getAnswers()[0]);
		 }
	} catch (NumberFormatException e) {
		 result="error";
		e.printStackTrace();
	} catch (ConnectionException e) {
		 result="error";
		 LOGGER.error(Exceptions.CONNECTION_COMMIT_FAILED);
		e.printStackTrace();
	}
	
	return result;
}


/**
* Setters and Getters for the Action
*/
public ReadingComprehensionQuestionBo getReadingcmpQuestionBo() {
	return readingcmpQuestionBo;
}
public void setReadingcmpQuestionBo(
		ReadingComprehensionQuestionBo readingcmpQuestionBo) {
	this.readingcmpQuestionBo = readingcmpQuestionBo;
}
public List<TestsBO> getTestsList() {
	return testsList;
}
public void setTestsList(List<TestsBO> testsList) {
	this.testsList = testsList;
}
public List<ReadingComprehensionBO> getPassagesList() {
	return passagesList;
}
public void setPassagesList(List<ReadingComprehensionBO> passagesList) {
	this.passagesList = passagesList;
}
public List<SkillBO> getSkillsList() {
	return skillsList;
}
public void setSkillsList(List<SkillBO> skillsList) {
	this.skillsList = skillsList;
}
public List<DifficultyBO> getDifficultiesList() {
	return difficultiesList;
}
public void setDifficultiesList(List<DifficultyBO> difficultiesList) {
	this.difficultiesList = difficultiesList;
}
public String getQuestionType() {
	return questionType;
}
public void setQuestionType(String questionType) {
	//System.out.println("Question TYpe setting");
	this.questionType = questionType;
} 
public String getDeleteIds() {
	return deleteIds;
}
public void setDeleteIds(String deleteIds) {
	this.deleteIds = deleteIds;
}
public List<ReadingComprehensionQuestionBo> getRcQuestionsList() {
	return rcQuestionsList;
}
public void setRcQuestionsList(
		List<ReadingComprehensionQuestionBo> rcQuestionsList) {
	this.rcQuestionsList = rcQuestionsList;
}
 

/**
 * Overridden Methods
 */
@Override
public void setServletRequest(HttpServletRequest arg0) {
	request=arg0;
	
}
@Override
public void prepare() throws Exception {
	 readingcmpQuestionBo=new ReadingComprehensionQuestionBo();	
}
@Override
public ReadingComprehensionQuestionBo getModel() {	
	return readingcmpQuestionBo;
}
@Override
public void setServletResponse(HttpServletResponse arg0) {
	response=arg0; 
}
}
