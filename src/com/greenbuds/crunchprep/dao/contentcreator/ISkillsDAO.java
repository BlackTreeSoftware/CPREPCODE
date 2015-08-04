package com.greenbuds.crunchprep.dao.contentcreator;

import java.sql.SQLException;

import com.greenbuds.crunchprep.bo.common.SkillBO;
import com.greenbuds.crunchprep.exception.CPException;

/**
 * @author kkatikala
 *
 */
public interface ISkillsDAO {
	
	
	/**
	 * @return
	 * @throws CPException
	 */
	public String saveSkills(SkillBO skillbo) throws CPException;
	
	/**
	 * @param skillbo
	 * @return
	 * @throws CPException
	 */
	public String updateSkills(SkillBO skillbo) throws CPException;
	
	/**
	 * @param skillbo
	 * @return
	 * @throws CPException
	 * @throws SQLException 
	 */
	public String deleteSkills(SkillBO skillbo) throws CPException, SQLException;

}
