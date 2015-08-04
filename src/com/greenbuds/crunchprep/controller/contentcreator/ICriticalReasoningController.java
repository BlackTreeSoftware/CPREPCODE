package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.CriticalReasoningBo;
import com.greenbuds.crunchprep.exception.ConnectionException;

public interface ICriticalReasoningController {
	/**
	 * Saving New Critical Reasoning Question
	 * @return true/false
	 * @throws ConnectionException
	 */
	public boolean saveNewCriticalReasoning(CriticalReasoningBo bo) throws ConnectionException;
	
	/**
	 * Getting All the critical Reasoning Question from the Question pool
	 * @return List of questions
	 * @throws ConnectionException
	 */
	public List<CriticalReasoningBo> getAlltheCriticalReasoningQuestions() throws ConnectionException;
	
	/**
	 * Deleting Selected Critical Reasoning Questions 
	 * @return true/false
	 * @throws ConnectionException
	 */
	public boolean deleteSelectedCriticalQuestions(String questionIds) throws ConnectionException;
	
	/**
	 * gettting selected Critical REasoning Question while Updating
	 * @return critical Reasoning Question bean
	 * @throws ConnectionException
	 */
	public CriticalReasoningBo getSingleCriticalQuestion(int questionId) throws ConnectionException;
	
	/**
	 * Updating Existed Critical Reasoning Question 
	 * @return true/false
	 * @throws ConnectionException
	 */
	public boolean updateCriticalReasoning(CriticalReasoningBo bo) throws ConnectionException;
	
}
