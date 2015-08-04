package com.greenbuds.crunchprep.controller.lessons;

import java.sql.SQLException;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.exception.DBException;

/**
 * The Interface ILessonsController.
 */
public interface ILessonsController {
	
	/**
	 * Adds the confidence level.
	 *
	 * @param lessonsmasterBO the lessonsmaster bo
	 * @return the string
	 * @throws DBException the DB exception
	 * @throws SQLException the SQL exception
	 */
	public String addConfidenceLevel(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException;
	
	/**
	 * Update confidence level.
	 *
	 * @param lessonsmasterBO the lessonsmaster bo
	 * @return the string
	 * @throws DBException the DB exception
	 * @throws SQLException the SQL exception
	 */
	public String updateConfidenceLevel(LessonsmasterBO lessonsmasterBO) throws  DBException,SQLException;
	
	/**
	 * Gets the confidence levels.
	 *
	 * @param lessonsmasterBO the lessonsmaster bo
	 * @return the confidence levels
	 * @throws DBException the DB exception
	 * @throws SQLException the SQL exception
	 */
	public List<LessonsmasterBO> getConfidenceLevels(LessonsmasterBO lessonsmasterBO)throws DBException,SQLException;

	
	public String addBookMarks(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException;
	
	public String addNotes(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException;
	
	
	
	public boolean levelChecking(LessonsmasterBO lessonsmasterBO) throws SQLException;
	public LessonsmasterBO getLessonDetails(LessonsmasterBO lessonsmasterBO)throws DBException,SQLException;
	public String lessonsTaken(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException;
	public String lessonsSession(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException;


	public void getLessonsHierachyData(JSONObject object) throws JSONException;

	/**
	 * @author rrajulapati
	 * @param destination
	 * @throws JSONException
	 * @throws DBException 
	 * @throws ConnectionException 
	 */
	public void lessonsHierarchyForCustomization(JSONObject destination)
			throws JSONException, ConnectionException, DBException;


}
