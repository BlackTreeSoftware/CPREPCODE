package com.greenbuds.crunchprep.controller.mentor;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.mentor.MentorBO;
import com.greenbuds.crunchprep.bo.mentor.UserProgressBO;
import com.greenbuds.crunchprep.dao.mentor.IMentorDAO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;

public class MentorController implements IMentorController {
	
	@Autowired
	private IMentorDAO mentorDAO;
	
	
	public List<MentorBO> mentorAssignedStudentList(int mentorId) throws DBException, SQLException{
		
		return mentorDAO.mentorAssignedStudentList(mentorId);
		
	}
	public List<MentorBO> studentProgressList(int mentorId) throws DBException, SQLException{
		 return mentorDAO.studentProgressList(mentorId);
		
	}
	
	public List<MentorBO> getMentorStudentLogs(int mentorId) throws CPException{
		return mentorDAO.getMentorStudentLogs(mentorId);
	}
	
	public MentorBO getStudentDetails(int userid) throws CPException{
		return mentorDAO.getStudentDetails(userid);
	}
	public List<MentorBO> mentorStudentList(int mentorId)throws DBException, SQLException{
		return mentorDAO.mentorStudentList(mentorId);
	}
	 
	public List<UserProgressBO> getStudentProgress(int userid)
			throws DBException, SQLException {
		// TODO Auto-generated method stub
		return mentorDAO.getStudentProgress(userid);
	}
	@Override
	public List<UserProgressBO> getStudentTestProgress(int userid)
			throws DBException, SQLException {
		// TODO Auto-generated method stub
		return mentorDAO.getStudentTestProgress(userid);
	}
	public String getUsermail(int userid)throws CPException{
		return mentorDAO.getUsermail(userid);
	}
	public List<MentorBO> mentorStudenLogstList(int mentorId)throws CPException{
	return mentorDAO.mentorStudenLogstList(mentorId);
	}
	public List<MentorBO> mentorStudentLessonData(int userid)throws CPException{
		return mentorDAO.mentorStudentLessonData(userid);
	}

}
