package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.CriticalReasoningBo;
import com.greenbuds.crunchprep.dao.contentcreator.CriticalReasoningDAO;
import com.greenbuds.crunchprep.dao.contentcreator.ICriticalReasoningDAO;
import com.greenbuds.crunchprep.exception.ConnectionException;

public class CriticalReasoningController implements ICriticalReasoningController {
	/** The LessonController */
	private static CriticalReasoningController criticalReasoningController;
	private CriticalReasoningController(){
		
	}
	/**
	 * Gets the single instance of LessonsController.
	 *
	 * @return single instance of LessonsController.
	 */
	public static synchronized CriticalReasoningController getInstance(){
		if(criticalReasoningController==null)
			criticalReasoningController=new CriticalReasoningController();
		return criticalReasoningController;
	}
  private ICriticalReasoningDAO criticalReasoningDao;
	@Override
	public boolean saveNewCriticalReasoning(CriticalReasoningBo bo)
			throws ConnectionException {
		criticalReasoningDao=CriticalReasoningDAO.getInstance();
		return criticalReasoningDao.saveNewCriticalReasoning(bo);
	}
	@Override
	public List<CriticalReasoningBo> getAlltheCriticalReasoningQuestions()
			throws ConnectionException {
		criticalReasoningDao=CriticalReasoningDAO.getInstance();
		return criticalReasoningDao.getAlltheCriticalReasoningQuestions();
	}
	@Override
	public boolean deleteSelectedCriticalQuestions(String questionId)
			throws ConnectionException {
		criticalReasoningDao=CriticalReasoningDAO.getInstance();
		return criticalReasoningDao.deleteSelectedCriticalQuestions(questionId);
	}
	@Override
	public CriticalReasoningBo getSingleCriticalQuestion(int questionId)
			throws ConnectionException {
		criticalReasoningDao=CriticalReasoningDAO.getInstance();
		return criticalReasoningDao.getSingleCriticalQuestion(questionId);
	}
	@Override
	public boolean updateCriticalReasoning(CriticalReasoningBo bo)
			throws ConnectionException {
		criticalReasoningDao=CriticalReasoningDAO.getInstance();
		return criticalReasoningDao.updateCriticalReasoning(bo);
	}
}
