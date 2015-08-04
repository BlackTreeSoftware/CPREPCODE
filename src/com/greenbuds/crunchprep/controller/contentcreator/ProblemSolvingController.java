package com.greenbuds.crunchprep.controller.contentcreator;

import java.util.List;

import com.greenbuds.crunchprep.bo.contentcreator.QuestionsUploadBO;
import com.greenbuds.crunchprep.dao.contentcreator.ProblemSolvingDAO;
import com.greenbuds.crunchprep.exception.CPException;

// TODO: Auto-generated Javadoc
/**
 * The Class ProblemSolvingController.
 */
public class ProblemSolvingController implements IProblemSolvingController{
	
	/** The problem solving controller. */
	private static ProblemSolvingController problemSolvingController;
	
	/**
	 * Gets the single instance of ProblemSolvingController.
	 *
	 * @return single instance of ProblemSolvingController
	 */
	public static synchronized ProblemSolvingController getInstance(){
		if(problemSolvingController==null)
			problemSolvingController=new ProblemSolvingController();
		return problemSolvingController;
	}
    ProblemSolvingDAO problemSolvingDAO;
    
	@Override
	public boolean saveProblemSolvingQuestion(
			QuestionsUploadBO questionsUploadBO) throws CPException {
		// TODO Auto-generated method stub
		problemSolvingDAO=problemSolvingDAO.getInstance();
		return problemSolvingDAO.saveProblemSolvingQuestion(questionsUploadBO);
	}

	@Override
	public List<QuestionsUploadBO> getProblemSolvingQuesions()
			throws CPException {
		// TODO Auto-generated method stub
		problemSolvingDAO=problemSolvingDAO.getInstance();
		return problemSolvingDAO.getProblemSolvingQuesions();
	}
}
