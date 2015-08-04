package com.greenbuds.crunchprep.dao.admin;

import java.util.List;

import com.greenbuds.crunchprep.bo.adminbo.MentorBO;
import com.greenbuds.crunchprep.exception.CPException;

public interface IAssignMentorDAO {
	
	public List<MentorBO> getMentors(int sectionid)throws CPException;
	public List<MentorBO> getStdents()throws CPException;
	public List<MentorBO> getMentorStdents(int sectionid)throws CPException;
	public String addMentors(MentorBO bo,int userid[],int sectionId)throws CPException;
	public List<MentorBO> checkMentorAvilability(MentorBO bo,int[] userid,int sectionId)throws CPException;
	public int getSectionId(MentorBO bo)throws CPException;
	public String deleteMentors(MentorBO bo,int userid[])throws CPException;
	public String updateMentors(MentorBO bo,int userid[])throws CPException;

}
