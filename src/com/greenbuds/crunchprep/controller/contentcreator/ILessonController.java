package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.HashMap;
import java.util.List;

import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;
import com.greenbuds.crunchprep.bo.contentcreator.LessonsBo;
import com.greenbuds.crunchprep.bo.contentcreator.SubLessonBO;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISubLessonController.
 */
public interface ILessonController {
 
	
	/**
	 * Gets the lesson basedon sections.
	 *
	 * @param sectionid the sectionid
	 * @return the lesson basedon sections
	 * @throws CPException the CP exception
	 */
	public  HashMap<Integer,String> getLessonBasedonSections(int sectionid)throws CPException;
	
	/**
	 * Gets the lesson structure.
	 *
	 * @return the lesson structure
	 * @throws CPException the CP exception
	 */
	public List<SubLessonBO> getLessonStructure()throws CPException;
	
	/**
	 * Gets the lesson structure.
	 *
	 * @return the sub lessons
	 * @throws CPException the CP exception
	 */
	public List<SubLessonBO> getSubLessons(int lessonid)throws CPException;
	
	/**
	 * Adding new Lesson
	 * @return "success"/"error"/"exist"
	 * @throws ConnectioException
	 */
	
	public String saveNewLesson(LessonsBo bo) throws ConnectionException;
	
	/**
	 * Updating Existed Lesson
	 * @return "true"/"false"
	 * @throws ConnectioException
	 */
	public boolean editLesson(LessonsBo bo) throws ConnectionException;
	
	/**
	 * Deleting number of Lessons
	 * @return  "true"/"false"
	 * @throws ConnectioException
	 */
	public boolean deleteLesson(String lessonid) throws ConnectionException;
	
	/**
	 * get all the lessons Data
	 * @return  list of lessons
	 * @throws ConnectioException
	 */
	public List<LessonsBo> getLessonsData()throws ConnectionException;
 
	
	/**
	 * saves sub lessons.
	 *
	 * @return the sub lessons
	 * @throws CPException the CP exception
	 */
	public boolean saveSubLessons(SubLessonBO subLessonBOs)throws CPException;
	
	
	/**
	 * updates sub lessons.
	 *
	 * @return true if updated successfully
	 * @throws CPException the CP exception
	 */
	public boolean updateSubLessons(SubLessonBO subLessonBOs)throws CPException;
	
	/**
	 * deletes sub lessons.
	 *
	 * @return true if deleted successfully
	 * @throws CPException the CP exception
	 */
	public boolean deleteSubLessons(String[] records)throws CPException; 
	
	
	/**
	 * gets main lessons. 
	 * @return Main Lessons
	 * @throws CPException the CP exception
	 */
	public List<SubLessonBO> getMainLessons()throws CPException; 
	
	
 
}
