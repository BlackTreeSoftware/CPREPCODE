package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.CriticalReasoningBo;
import com.greenbuds.crunchprep.exception.ConnectionException;

public interface ICriticalReasoningDAO {
	public boolean saveNewCriticalReasoning(CriticalReasoningBo bo) throws ConnectionException;
	public List<CriticalReasoningBo> getAlltheCriticalReasoningQuestions() throws ConnectionException;
	public boolean deleteSelectedCriticalQuestions(String questionIds) throws ConnectionException;
	public CriticalReasoningBo getSingleCriticalQuestion(int questionId) throws ConnectionException;
	public boolean updateCriticalReasoning(CriticalReasoningBo bo) throws ConnectionException;
}
