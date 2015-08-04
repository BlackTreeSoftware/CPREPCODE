/*
 * 
 */
package com.greenbuds.crunchprep.controller.skilldata;

import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.bo.skilldata.SkillDataBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;

public interface ISkillDataController {
	
	/**
	 * Gets the skill data.
	 *
	 * @param userid the userid
	 * @return the skill data
	 * @throws CPException the CP exception
	 */
	public List<SkillDataBO> getSkillData(int userid)throws CPException;
	public List<LessonsmasterBO> displyLeastLessonsBasedOnSkillData(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException;
}
