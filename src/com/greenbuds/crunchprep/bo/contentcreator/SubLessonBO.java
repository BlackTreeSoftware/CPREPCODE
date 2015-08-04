/*
 * 
 */
package com.greenbuds.crunchprep.bo.contentcreator;

// TODO: Auto-generated Javadoc
/**
 * The Class SubLessonBO.
 */
public class SubLessonBO extends LessonsBo{

/** The parent_id. */
private int parent_id;

/** The child_id. */
private int child_id;

/** The lesson_type. */
private String lesson_type;



private int main_lesson_id;
private String main_lesson_name;

public String getMain_lesson_name() {
	return main_lesson_name;
}

public void setMain_lesson_name(String main_lesson_name) {
	this.main_lesson_name = main_lesson_name;
}

public int getMain_lesson_id() {
	return main_lesson_id;
}

public void setMain_lesson_id(int main_lesson_id) {
	this.main_lesson_id = main_lesson_id;
}

 


public String getLesson_type() {
	return lesson_type;
}

public void setLesson_type(String lesson_type) {
	this.lesson_type = lesson_type;
}

/** The id. */
private int id;
 
/**
 * Gets the id.
 *
 * @return the id
 */

private String parent_lesson_name;

/**
 * Gets the parent_lesson_name.
 *
 * @return the parent_lesson_name
 */
public String getParent_lesson_name() {
	return parent_lesson_name;
}

/**
 * Sets the parent_lesson_name.
 *
 * @param parent_lesson_name the new parent_lesson_name
 */
public void setParent_lesson_name(String parent_lesson_name) {
	this.parent_lesson_name = parent_lesson_name;
}

/**
 * Gets the id.
 *
 * @return the id
 */
public int getId() {
	return id;
}

/** The skills_required. */
private String[] skills_required;

/**
 * Gets the skills_required.
 *
 * @return the skills_required
 */
public String[] getSkills_required() {
	return skills_required;
}

/**
 * Sets the skills_required.
 *
 * @param skills_required the new skills_required
 */
public void setSkills_required(String[] skills_required) {
	this.skills_required = skills_required;
}

/**
 * Sets the id.
 *
 * @param id the new id
 */
public void setId(int id) {
	this.id = id;
}

/**
 * Gets the parent_id.
 *
 * @return the parent_id
 */
public int getParent_id() {
	return parent_id;
}

/** The Sub lesson ids. */
private String SubLessonIds="";

/** The level. */
private int level;

/**
 * Gets the level.
 *
 * @return the level
 */
public int getLevel() {
	return level;
}

/**
 * Sets the level.
 *
 * @param level the new level
 */
public void setLevel(int level) {
	this.level = level;
}

/**
 * Gets the sub lesson ids.
 *
 * @return the sub lesson ids
 */
public String getSubLessonIds() {
	return SubLessonIds;
}

/**
 * Sets the sub lesson ids.
 *
 * @param subLessonIds the new sub lesson ids
 */
public void setSubLessonIds(String subLessonIds) {
	SubLessonIds = subLessonIds;
}

/** The sublesson_id. */
private int sublesson_id; 

/** The sub_lessonname. */
private String sub_lessonname;

/** The lesson_text. */
private String lesson_text;

/** The records to delete. */
private String[] recordsToDelete;

/**
 * Gets the records to delete.
 *
 * @return the records to delete
 */
public String[] getRecordsToDelete() {
	return recordsToDelete;
}

/**
 * Sets the records to delete.
 *
 * @param recordsToDelete the new records to delete
 */
public void setRecordsToDelete(String[] recordsToDelete) {
	this.recordsToDelete = recordsToDelete;
}

/**
 * Gets the skillid1.
 *
 * @return the skillid1
 */
public int getSkillid1() {
	return skillid1;
}

/**
 * Sets the skillid1.
 *
 * @param skillid1 the new skillid1
 */
public void setSkillid1(int skillid1) {
	this.skillid1 = skillid1;
}

/**
 * Gets the skillid2.
 *
 * @return the skillid2
 */
public int getSkillid2() {
	return skillid2;
}


/**
 * Sets the skillid2.
 *
 * @param skillid2 the new skillid2
 */
public void setSkillid2(int skillid2) {
	this.skillid2 = skillid2;
}

/**
 * Gets the skillid3.
 *
 * @return the skillid3
 */
public int getSkillid3() {
	return skillid3;
}

/**
 * Sets the skillid3.
 *
 * @param skillid3 the new skillid3
 */
public void setSkillid3(int skillid3) {
	this.skillid3 = skillid3;
}

/**
 * Gets the skillname1.
 *
 * @return the skillname1
 */
public String getSkillname1() {
	return skillname1;
}

/**
 * Sets the skillname1.
 *
 * @param skillname1 the new skillname1
 */
public void setSkillname1(String skillname1) {
	this.skillname1 = skillname1;
}

/**
 * Gets the skillname2.
 *
 * @return the skillname2
 */
public String getSkillname2() {
	return skillname2;
}

/**
 * Sets the skillname2.
 *
 * @param skillname2 the new skillname2
 */
public void setSkillname2(String skillname2) {
	this.skillname2 = skillname2;
}

/**
 * Gets the skillname3.
 *
 * @return the skillname3
 */
public String getSkillname3() {
	return skillname3;
}

/**
 * Sets the skillname3.
 *
 * @param skillname3 the new skillname3
 */
public void setSkillname3(String skillname3) {
	this.skillname3 = skillname3;
}

/** The lesson_video. */
private String lesson_video;

/** The access_type. */
private String access_type;

/**
 * Gets the access_type.
 *
 * @return the access_type
 */
public String getAccess_type() {
	return access_type;
}

/**
 * Sets the access_type.
 *
 * @param access_type the new access_type
 */
public void setAccess_type(String access_type) {
	this.access_type = access_type;
}

/** The skillid1. */
private int skillid1;

/** The skillid2. */
private int skillid2;

/** The skillid3. */
private int skillid3;

/** The skillname1. */
private String skillname1;

/** The skillname2. */
private String skillname2;

/** The skillname3. */
private String skillname3;

/** The status. */
private String status;

/**
 * Gets the status.
 *
 * @return the status
 */
public String getStatus() {
	return status;
}

/**
 * Sets the status.
 *
 * @param status the new status
 */
public void setStatus(String status) {
	this.status = status;
}

/**
 * Gets the parent_id.
 *
 * @return the parent_id
 */
public int lessonTreeModel() {
	return parent_id;
}

/**
 * Sets the parent_id.
 *
 * @param parent_id the new parent_id
 */
public void setParent_id(int parent_id) {
	this.parent_id = parent_id;
}

/**
 * Gets the child_id.
 *
 * @return the child_id
 */
public int getChild_id() {
	return child_id;
}

/**
 * Sets the child_id.
 *
 * @param child_id the new child_id
 */
public void setChild_id(int child_id) {
	this.child_id = child_id;
}

 

/**
 * Gets the sublesson_id.
 *
 * @return the sublesson_id
 */
public int getSublesson_id() {
	return sublesson_id;
}

/**
 * Sets the sublesson_id.
 *
 * @param sublesson_id the new sublesson_id
 */
public void setSublesson_id(int sublesson_id) {
	this.sublesson_id = sublesson_id;
}

 
/**
 * Gets the sub_lessonname.
 *
 * @return the sub_lessonname
 */
public String getSub_lessonname() {
	return sub_lessonname;
}

/**
 * Sets the sub_lessonname.
 *
 * @param sub_lessonname the new sub_lessonname
 */
public void setSub_lessonname(String sub_lessonname) {
	this.sub_lessonname = sub_lessonname;
}

/**
 * Gets the lesson_text.
 *
 * @return the lesson_text
 */
public String getLesson_text() {
	return lesson_text;
}

/**
 * Sets the lesson_text.
 *
 * @param lesson_text the new lesson_text
 */
public void setLesson_text(String lesson_text) {
	this.lesson_text = lesson_text;
}

/**
 * Gets the lesson_video.
 *
 * @return the lesson_video
 */
public String getLesson_video() {
	return lesson_video;
}

/**
 * Sets the lesson_video.
 *
 * @param lesson_video the new lesson_video
 */
public void setLesson_video(String lesson_video) {
	this.lesson_video = lesson_video;
}


}
