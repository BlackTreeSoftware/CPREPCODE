package com.greenbuds.crunchprep.controller.lessons;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;


import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;


import com.greenbuds.crunchprep.dao.lessons.LessonsDAO;

import com.greenbuds.crunchprep.dao.lessons.ILessonsDAO;

import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

/**
 * The Class LessonsController.
 */
public class LessonsController implements ILessonsController{
	
	private static LessonsController lessonsController;

	

	
	@Autowired
	private ILessonsDAO lessonsDAO=null;

	 
 	/**
 	 * Instantiates a new lessons controller.
 	 */
	 
 	/**
 	 * Gets the single instance of LessonsController.
 	 *
 	 * @return single instance of LessonsController
 	 */
 	public static synchronized LessonsController getInstance(){
		
		 if(lessonsController==null) lessonsController = new LessonsController();
			 
		   return lessonsController;
	 }
	
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.lessons.ILessonsController#addConfidenceLevel(com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	public String addConfidenceLevel(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{
		
	    lessonsDAO = LessonsDAO.getInstance();
		
		return lessonsDAO.addConfidenceLevel(lessonsmasterBO);
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.lessons.ILessonsController#updateConfidenceLevel(com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	public String updateConfidenceLevel(LessonsmasterBO lessonsmasterBO) throws  DBException,SQLException{
		
		
	lessonsDAO = LessonsDAO.getInstance();
		
		return lessonsDAO.updateConfidenceLevel(lessonsmasterBO);
		
	}
	
	/* (non-Javadoc)
	 * @see com.greenbuds.crunchprep.controller.lessons.ILessonsController#getConfidenceLevels(com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO)
	 */
	public List<LessonsmasterBO> getConfidenceLevels(LessonsmasterBO lessonsmasterBO)throws DBException,SQLException{
		
		
		lessonsDAO = LessonsDAO.getInstance();
		
		return lessonsDAO.getConfidenceLevels(lessonsmasterBO);
		
	}


	@Override
	public String addBookMarks(LessonsmasterBO lessonsmasterBO)
			throws DBException, SQLException {
		// TODO Auto-generated method stublessonsDAO = LessonsDAO.getInstance();
		String result = lessonsDAO.addBookMarks(lessonsmasterBO);
		return result;
	}

	@Override
	public String addNotes(LessonsmasterBO lessonsmasterBO) throws DBException,
			SQLException {
	
		lessonsDAO = LessonsDAO.getInstance();
		String result = lessonsDAO.addNotes(lessonsmasterBO);
		return result;
	}

 
	 


	public LessonsmasterBO getLessonDetails(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException {
		// TODO Auto-generated method stub
		return lessonsDAO.getLessonDetails(lessonsmasterBO);
	}

    public boolean levelChecking(LessonsmasterBO lessonsmasterBO) throws SQLException{
	   lessonsDAO = LessonsDAO.getInstance();
     		
		return lessonsDAO.levelChecking(lessonsmasterBO);
		
	}
    
    public String lessonsTaken(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{
    	lessonsDAO = LessonsDAO.getInstance();
    	return lessonsDAO.lessonsTaken(lessonsmasterBO);
    	
    }


    public String lessonsSession(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException{
    	lessonsDAO = LessonsDAO.getInstance();
    	return lessonsDAO.lessonsSession(lessonsmasterBO);
    	
    }


	@Override
	public void getLessonsHierachyData(JSONObject object) throws JSONException {
		  lessonsDAO = LessonsDAO.getInstance();
   		
			 lessonsDAO.lessonsHierarchyData(object);
	}
	
	/**
	    * @author rrajulapati
	 * @throws DBException 
	 * @throws ConnectionException 
	    */
	   	@Override
	   	public void lessonsHierarchyForCustomization(JSONObject destination) throws JSONException, ConnectionException, DBException {
	   	 lessonsDAO = LessonsDAO.getInstance();
	     lessonsDAO.lessonsHierarchyForCustomization(destination);
	     
	   	}

}
