package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionQuestionBo;
import com.greenbuds.crunchprep.dao.contentcreator.IReadingComprehensionDAO;
import com.greenbuds.crunchprep.dao.contentcreator.ReadingComprehensionDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;

public class ReadingComprehensionController implements IReadingComprehensionController {
	/** The LessonController */
	private static ReadingComprehensionController readingComprehensionController;
	private ReadingComprehensionController(){
		
	}
	/**
	 * Gets the single instance of LessonsController.
	 *
	 * @return single instance of LessonsController.
	 */
	public static synchronized ReadingComprehensionController getInstance(){
		if(readingComprehensionController==null)
			readingComprehensionController=new ReadingComprehensionController();
		return readingComprehensionController;
	}

	private IReadingComprehensionDAO readingComprehensionDAO;
	
	
	@Override
	public String saveReadingComprehension(ReadingComprehensionBO readingComprehensionBO) throws CPException {
		// TODO Auto-generated method stub
		
		
		readingComprehensionDAO =ReadingComprehensionDAO.getInstance();
		String result = readingComprehensionDAO.saveReadingComprehension(readingComprehensionBO);
		
		return result;
	}

	@Override
	public List<ReadingComprehensionBO> getReadingComprehension()
			throws CPException {
		readingComprehensionDAO =ReadingComprehensionDAO.getInstance();
		List<ReadingComprehensionBO> readingComprehensionBOs = readingComprehensionDAO.getReadingComprehension();
		// TODO Auto-generated method stub
		return readingComprehensionBOs;
	}

	@Override
	public String editReadingComprehension(	ReadingComprehensionBO readingComprehensionBO) throws CPException {
		// TODO Auto-generated method stub

		readingComprehensionDAO =ReadingComprehensionDAO.getInstance();
		String result = readingComprehensionDAO.editReadingComprehension(readingComprehensionBO);
		
		return result;
	}

	@Override
	public String deleteReadingComprehension(String[] deleterecords)
			throws CPException {
		// TODO Auto-generated method stub

		readingComprehensionDAO =ReadingComprehensionDAO.getInstance();
		String result = readingComprehensionDAO.deleteReadingComprehension(deleterecords);		
		return result;
	}
	@Override
	public boolean saveNewReadingcmpQuestion(ReadingComprehensionQuestionBo bo)
			throws ConnectionException {
		 readingComprehensionDAO=ReadingComprehensionDAO.getInstance();
		return readingComprehensionDAO.saveNewReadingcmpQuestion(bo);
	}
	@Override
	public List<ReadingComprehensionQuestionBo> getAlltheReadingComprehensionQuestions()
			throws ConnectionException {
		readingComprehensionDAO=ReadingComprehensionDAO.getInstance();
		return readingComprehensionDAO.getAlltheReadingComprehensionQuestions();
	}
	@Override
	public List<ReadingComprehensionBO> getPassagesBasedonType(String TYPE)
			throws ConnectionException {
		readingComprehensionDAO=ReadingComprehensionDAO.getInstance();
		return readingComprehensionDAO.getPassagesBasedonType(TYPE);
	}
	@Override
	public ReadingComprehensionQuestionBo gettingParticularRCquestion(
			int questionId) throws ConnectionException {
		readingComprehensionDAO=ReadingComprehensionDAO.getInstance();
		return readingComprehensionDAO.gettingParticularRCquestion(questionId);
	}
	@Override
	public boolean updateReadingcmpQuestion(ReadingComprehensionQuestionBo bo)
			throws ConnectionException {
		readingComprehensionDAO=ReadingComprehensionDAO.getInstance();
		return readingComprehensionDAO.updateReadingcmpQuestion(bo);
	}

}
