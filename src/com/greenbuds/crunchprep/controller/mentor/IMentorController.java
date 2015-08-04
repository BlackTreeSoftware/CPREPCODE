package com.greenbuds.crunchprep.controller.mentor;

import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.mentor.MentorBO;
import com.greenbuds.crunchprep.bo.mentor.UserProgressBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;

public interface IMentorController {
	public List<MentorBO> mentorAssignedStudentList(int mentorId) throws DBException, SQLException;
	public List<MentorBO> studentProgressList(int mentorId) throws DBException, SQLException;
	public List<MentorBO> getMentorStudentLogs(int mentorId) throws CPException;
	public MentorBO getStudentDetails(int userid) throws CPException;
	public List<MentorBO> mentorStudentList(int mentorId) throws DBException, SQLException;
	public List<UserProgressBO> getStudentProgress(int userid) throws DBException, SQLException;
	public List<UserProgressBO> getStudentTestProgress(int userid) throws DBException, SQLException;
	public String getUsermail(int userid)throws CPException;
	public List<MentorBO> mentorStudenLogstList(int mentorId)throws CPException;
	public List<MentorBO> mentorStudentLessonData(int userid)throws CPException;
	
}
