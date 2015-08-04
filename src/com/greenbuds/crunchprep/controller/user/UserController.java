package com.greenbuds.crunchprep.controller.user;

import org.json.JSONArray;

import com.greenbuds.crunchprep.dao.user.IUserDAO;
import com.greenbuds.crunchprep.dao.user.UserDAO;
import com.greenbuds.crunchprep.exception.ConnectionException;


public class UserController implements IUserController {
	/** The UserController */
	private static UserController userController;
	private UserController(){
		
	}
	/**
	 * Gets the single instance of LessonsController.
	 *
	 * @return single instance of LessonsController.
	 */
	public static synchronized UserController getInstance(){
		if(userController==null)
			userController=new UserController();
		return userController;
	}
	private static IUserDAO userDao;
	@Override
	public JSONArray getTop3Skillsdata(int userId, int testId) throws ConnectionException {
		 userDao=UserDAO.getInstance();
		return userDao.getTop3Skillsdata(userId, testId);
	}
}
