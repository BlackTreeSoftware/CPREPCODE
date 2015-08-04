package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface IProblemSolvingDAO {

	/**
	 * Save problem solving question.
	 *
	 * @param questionsUploadBO the questions upload bo
	 * @return true, if successful
	 */
	public boolean saveProblemSolvingQuestion(QuestionsUploadBO questionsUploadBO) throws CPException;
	
	

	/**
	 * Gets the problem solving quesions.
	 *
	 * @return the problem solving quesions
	 * @throws CPException the CP exception
	 */
	public List<QuestionsUploadBO> getProblemSolvingQuesions() throws CPException;
}
