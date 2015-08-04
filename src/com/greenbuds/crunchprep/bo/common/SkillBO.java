/***************************************************************************
 * Copyright (c) 2014 , bnandigama , All rights reserved.  
 * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.bo.common;

// TODO: Auto-generated Javadoc
/**
 * The Class Skill.
 */
public class SkillBO {

	/** The skill_id. */
	private int skill_id;
	
	/** The skill_name. */
	private String skill_name;
	
	
	private int sectionId;
	private String sectionName;
	
	public int getSectionId() {
		return sectionId;
	}

	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	/**
	 * Gets the skill_id.
	 *
	 * @return the skill_id
	 */
	public int getSkill_id() {
		return skill_id;
	}
	
	/**
	 * Sets the skill_id.
	 *
	 * @param skill_id the new skill_id
	 */
	public void setSkill_id(int skill_id) {
		this.skill_id = skill_id;
	}
	
	/**
	 * Gets the skill_name.
	 *
	 * @return the skill_name
	 */
	public String getSkill_name() {
		return skill_name;
	}
	
	/**
	 * Sets the skill_name.
	 *
	 * @param skill_name the new skill_name
	 */
	public void setSkill_name(String skill_name) {
		this.skill_name = skill_name;
	}
	
	
}
