package com.greenbuds.crunchprep.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.greenbuds.crunchprep.bo.adminbo.MentorBO;
import com.greenbuds.crunchprep.dao.admin.IAssignMentorDAO;
import com.greenbuds.crunchprep.exception.CPException;

public class AssignMentorController implements IAssignMentorController {
	@Autowired
	private IAssignMentorDAO assignMentorDAO;
	
	public List<MentorBO> getMentors(int sectionid)throws CPException{
		return assignMentorDAO.getMentors(sectionid);
	}
	
	public List<MentorBO> getStdents()throws CPException{
		return assignMentorDAO.getStdents();
	}
	
	public List<MentorBO> getMentorStdents(int sectionid)throws CPException{
		return assignMentorDAO.getMentorStdents(sectionid);
	}
	
	public String addMentors(MentorBO bo,int userid[],int sectionId)throws CPException{
		return assignMentorDAO.addMentors(bo, userid,sectionId);
	}
	
	public List<MentorBO> checkMentorAvilability(MentorBO bo,int[] userid,int sectionId)throws CPException{
		return assignMentorDAO.checkMentorAvilability(bo, userid,sectionId);
	}
	
	public int getSectionId(MentorBO bo)throws CPException{
		return assignMentorDAO.getSectionId(bo);
	}
	public String deleteMentors(MentorBO bo,int userid[])throws CPException{
		return assignMentorDAO.deleteMentors(bo, userid);
	}
	
	public String updateMentors(MentorBO bo,int userid[])throws CPException{
		return assignMentorDAO.updateMentors(bo, userid);
	}
	
}
