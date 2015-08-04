/*
 * @(#)LessonsMaster.java
 * Copyright (c) 2014 Greenbuds Software Technologies.
 */
package com.greenbuds.crunchprep.action.contentcreator;

import java.util.List;

import org.apache.log4j.Logger;

import com.greenbuds.crunchprep.bo.common.SectionBO;
import com.greenbuds.crunchprep.bo.contentcreator.LessonsBo;
import com.greenbuds.crunchprep.controller.common.CommonsController;
import com.greenbuds.crunchprep.controller.common.ICommonsController;
import com.greenbuds.crunchprep.controller.contentcreator.ILessonController;
import com.greenbuds.crunchprep.controller.contentcreator.LessonController;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.setups.Exceptions;
import com.opensymphony.xwork2.Preparable;
/*java API's*/
/*other third Party API's*/
/*Project API's*/

/**
* This class contains all the action methods required for the Add lessons frame in Content Creator
*
* @version 1.0
* @author Sahitya Mittapalli
*/
public class LessonsMaster  {
	/*Class Implementation Comments*/
	
	/** The Constant LOGGER. */
	private final static Logger LOGGER = Logger.getLogger( LessonsMaster.class );
	
	/*Lessons java bean*/
	private LessonsBo lessonsbo;
	
	/*List of Sections*/
	private List<SectionBO> sections_list;
	
	/*List of All the lessons in the database*/
	private List<LessonsBo> lessons_list;
	
	/*particular lesson id*/
	private String lessonIds;
	
	/*Controller for using common Dao's*/
	private ICommonsController commonController;
	
	/*Controller object for using lessons controller*/
	private ILessonController lessonController;
	
	/*Action Messages*/
	private String succesMsg,errorMsg;
	
	public String getSuccesMsg() {
		return succesMsg;
	}

	public void setSuccesMsg(String succesMsg) {
		this.succesMsg = succesMsg;
	}

	/*No-Argument Constructor*/
	public LessonsMaster(){
		
	}
	
	/**
	 * this is used to get All the sections which will be displayed in Add lessons frame
	 * @return "success" when all the sections are get
	 */
	public String getSections()
	{
		 
		 commonController=CommonsController.getInstance();	
		lessonController=LessonController.getInstance();
		 try {
			setSections_list(commonController.getSectionsList());
			setLessons_list(lessonController.getLessonsData());
		} catch (CPException e) {
			 LOGGER.error("It may be either Connection Exception or SQL Exception");
			e.printStackTrace();
		}
	 
		return "success";
	}
	
	/**
	 * this action method is used to save the new lesson and updating the existed lesson entered by the user in the lessons frame 
	 * @return "success"           if new lesson saved successfully
	 *         "error"             if new lesson not saved successfully/not updated successfully
	 *         "exist"             if new lesson name already existed in database
	 *         "edit-success"      if lesson updated successfully
	 */
	public String saveNewLesson()
	{ 
		//System.out.println(lessonsbo.getLessonId());
		//JSONObject object=new JSONObject();
		String result="error";
		 
		 lessonController=LessonController.getInstance();
		 try {
			 if(lessonsbo.getLessonId()==0)
			 {
			 result=lessonController.saveNewLesson(lessonsbo);
			 setLessons_list(lessonController.getLessonsData());
			 if(result.equalsIgnoreCase("success"))setSuccesMsg("Lesson Name Saved Successfully");
			 else if(result.equalsIgnoreCase("exist"))setErrorMsg("Entered Lesson Name Already Existed");
			 }
			 else
			 {
			if(lessonController.editLesson(lessonsbo)){
				result="edit-success";
				setLessons_list(lessonController.getLessonsData());
				 
				 setSuccesMsg("Lesson Name Updated Successfully");
			 }
			 
			 }
			 
			 
		} catch (ConnectionException e) {
		 LOGGER.error(Exceptions.CONNECTION_REFUSED);
			e.printStackTrace();
		}
		 
		 if(result.equalsIgnoreCase("error"))
		 {
			 try {
				setLessons_list(lessonController.getLessonsData());
			} catch (ConnectionException e) {
				LOGGER.error(Exceptions.CONNECTION_REFUSED);
				e.printStackTrace(); 
			} 
		 }
		 
		 return result; 
	}
	 
	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * this action method is used to delete the selected number of lessons by the user
	 * @return "success" if all the records selected by the user deleted successfully
	 *         "error"   if all the records selected by the user not deleted successfully
	 */
	public String deleteLesson()
	{ 
		String result="error";
		 lessonController=LessonController.getInstance();
		 try {
			 if(lessonController.deleteLesson(getLessonIds()))
			 {
				 result="success";
				 setLessons_list(lessonController.getLessonsData());
				 setSuccesMsg("Selected Lesson Deleted Successfully");
			 }
			 else
			 { 
				 setErrorMsg("You can't Delete This Lesson,There might be some sublesson's for this lesson");
			 }
			 
		} catch (ConnectionException e) {
		 LOGGER.error(Exceptions.CONNECTION_REFUSED);
			e.printStackTrace();
		}
		 if(result.equalsIgnoreCase("error"))
		 {
			 try {
				setLessons_list(lessonController.getLessonsData());
			} catch (ConnectionException e) {
				LOGGER.error(Exceptions.CONNECTION_REFUSED);
				e.printStackTrace(); 
			} 
		 }
		 
	 return result;
	}
	 
 /**
  * Setters and Getters for the Action
  */
	public String getLessonIds() {
		return lessonIds;
	}
	public void setLessonIds(String lessonIds) {
		this.lessonIds = lessonIds;
	}
	public List<LessonsBo> getLessons_list() {
		return lessons_list;
	}
	public void setLessons_list(List<LessonsBo> lessons_list) {
		this.lessons_list = lessons_list;
	}
	public List<SectionBO> getSections_list() {
		return sections_list;
	}
	public void setSections_list(List<SectionBO> sections_list) {
		this.sections_list = sections_list;
	}
	public LessonsBo getLessonsbo() {
		return lessonsbo;
	}
	public void setLessonsbo(LessonsBo lessonsbo) {
		this.lessonsbo = lessonsbo;
	} 
}
