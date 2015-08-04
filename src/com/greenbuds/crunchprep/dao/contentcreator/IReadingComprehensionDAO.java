package com.greenbuds.crunchprep.dao.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionQuestionBo;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;

public interface IReadingComprehensionDAO {
	public String saveReadingComprehension(ReadingComprehensionBO ReadingComprehensionBO) throws CPException;
	public List<ReadingComprehensionBO> getReadingComprehension() throws CPException;
	public String editReadingComprehension(ReadingComprehensionBO ReadingComprehensionBO) throws CPException;
	public String deleteReadingComprehension(String[] deleterecords ) throws CPException;
	/**
	 * Reading Comprehension DAO
	 */
	public boolean saveNewReadingcmpQuestion(ReadingComprehensionQuestionBo bo) throws ConnectionException;
	public List<ReadingComprehensionQuestionBo> getAlltheReadingComprehensionQuestions() throws ConnectionException;
	public List<ReadingComprehensionBO> getPassagesBasedonType(String TYPE) throws ConnectionException;
	public ReadingComprehensionQuestionBo gettingParticularRCquestion(int questionId) throws ConnectionException;
	public boolean updateReadingcmpQuestion(ReadingComprehensionQuestionBo bo) throws ConnectionException;
}
