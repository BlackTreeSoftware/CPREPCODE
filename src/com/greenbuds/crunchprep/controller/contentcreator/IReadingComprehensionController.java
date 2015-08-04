package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionBO;
import com.greenbuds.crunchprep.bo.contentcreator.ReadingComprehensionQuestionBo;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.ConnectionException;

public interface IReadingComprehensionController {
	public String saveReadingComprehension(ReadingComprehensionBO ReadingComprehensionBO) throws CPException;
	public List<ReadingComprehensionBO> getReadingComprehension() throws CPException;
	public String editReadingComprehension(ReadingComprehensionBO ReadingComprehensionBO) throws CPException;
	public String deleteReadingComprehension(String[] deleterecords ) throws CPException;
	
	
	/**
	 * Reading Comprehension Question Related Controller Methods
	 */
	
	/**
	 * saveNewReadingcmpQuestion 
	 * @return true/false
	 * @throws ConnectionException
	 */
	public boolean saveNewReadingcmpQuestion(ReadingComprehensionQuestionBo bo) throws ConnectionException;
	
	/**
	 * getAlltheReadingComprehensionQuestions 
	 * @return RCQuestionBO's List
	 * @throws ConnectionException
	 */
	public List<ReadingComprehensionQuestionBo> getAlltheReadingComprehensionQuestions() throws ConnectionException;
	
	/**
	 * getPassagesBasedonType 
	 * @return RCBO's List
	 * @throws ConnectionException
	 */
	public List<ReadingComprehensionBO> getPassagesBasedonType(String TYPE) throws ConnectionException;
	
	/**
	 * gettingParticularRCquestion 
	 * @return RCQuestion BO
	 * @throws ConnectionException
	 */
	public ReadingComprehensionQuestionBo gettingParticularRCquestion(int questionId) throws ConnectionException;
	
	/**
	 * updateReadingcmpQuestionn 
	 * @return true/false
	 * @throws ConnectionException
	 */
	public boolean updateReadingcmpQuestion(ReadingComprehensionQuestionBo bo) throws ConnectionException;

}
