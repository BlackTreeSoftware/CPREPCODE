package com.greenbuds.crunchprep.dao.skilldata;

import java.sql.SQLException;
import java.util.List;

import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;
import com.greenbuds.crunchprep.bo.lessons.LessonsmasterBO;
import com.greenbuds.crunchprep.bo.skilldata.SkillDataBO;
import com.greenbuds.crunchprep.exception.CPException;
import com.greenbuds.crunchprep.exception.DBException;

// TODO: Auto-generated Javadoc
/**
 * The Interface ISkillDataDAO.
 */
public interface ISkillDataDAO {
	
	/**
	 * Gets the skill data.
	 *
	 * @param userid the userid
	 * @return the skill data
	 * @throws CPException the CP exception
	 */
	public List<SkillDataBO> getSkillData(int userid)throws CPException;
	public List<LessonsmasterBO> displyLeastLessonsBasedOnSkillData(LessonsmasterBO lessonsmasterBO) throws DBException, SQLException;
	public List<ScoreBO> userSocregraphData(int userid)throws CPException;
}
