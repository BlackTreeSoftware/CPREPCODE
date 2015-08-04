package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.HashMap;
import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.LessonsBo;
import com.greenbuds.crunchprep.bo.contentcreator.SubLessonBO;
import com.greenbuds.crunchprep.dao.common.CommonsDAO;
import com.greenbuds.crunchprep.dao.contentcreator.LessonsDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;

public class LessonController implements ILessonController{
	/** The LessonController */
	private static LessonController lessonController;
	private LessonController(){
		
	}
	/**
	 * Gets the single instance of LessonsController.
	 *
	 * @return single instance of LessonsController.
	 */
	public static synchronized LessonController getInstance(){
		if(lessonController==null)
			lessonController=new LessonController();
		return lessonController;
	}
    LessonsDAO subLessonsDAO;
	 
	@Override
	public HashMap<Integer, String> getLessonBasedonSections(int sectionid)
			throws CPException {
		 
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.getLessonBasedonSections(sectionid);
	}
	@Override
	public List<SubLessonBO> getLessonStructure() throws CPException {
		 
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.getLessonStructure();
	}
	@Override
	public List<SubLessonBO> getSubLessons(int lessonid) throws CPException {
	 
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.getSubLessons(lessonid);
	}
	@Override
	public String saveNewLesson(LessonsBo bo) throws ConnectionException {
		 
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.saveNewLesson(bo);
	}
	@Override
	public boolean editLesson(LessonsBo bo) throws ConnectionException {
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.editLesson(bo);
	}
	@Override
	public boolean deleteLesson(String lessonid) throws ConnectionException {
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.deleteLesson(lessonid);
	}
	@Override
	public List<LessonsBo> getLessonsData() throws ConnectionException {
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.getLessonsData();
	}
	@Override
	public boolean saveSubLessons(SubLessonBO subLessonBOs) throws CPException {
		// TODO Auto-generated method stub
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.saveSubLessons(subLessonBOs);
	}
	@Override
	public boolean updateSubLessons(SubLessonBO subLessonBOs)
			throws CPException {
		// TODO Auto-generated method stub
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.updateSubLessons(subLessonBOs);
		 
	}
	@Override
	public boolean deleteSubLessons(String[] records) throws CPException {
		// TODO Auto-generated method stub
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.deleteSubLessons(records);
	}
	@Override
	public List<SubLessonBO> getMainLessons() throws CPException {
		// TODO Auto-generated method stub
		subLessonsDAO=LessonsDAO.getInstance();
		return subLessonsDAO.getMainLessons();
	}

}
