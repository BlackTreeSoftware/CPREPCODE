/*
 * 
 */
package com.greenbuds.crunchprep.bo.skilldata;

// TODO: Auto-generated Javadoc
/**
 * The Class SkillDataBO.
 */
public class SkillDataBO {

	/** The skill. */
	private String skill;
	
	/** The total answered. */
	private String totalAnswered;
	private int skillId;
	
	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public String getTotalAnswered() {
		return totalAnswered;
	}

	public void setTotalAnswered(String totalAnswered) {
		this.totalAnswered = totalAnswered;
	}

	/** The correct counnt. */
	private int correctCounnt;
	
	/** The wrong count. */
	private int wrongCount;
	
	/** The questions answered. */
	private String questionsAnswered;

	
	private String section_name;
	
	private String timeInSeconds;
	public String getTimeInSeconds() {
		return timeInSeconds;
	}

	public void setTimeInSeconds(String timeInSeconds) {
		this.timeInSeconds = timeInSeconds;
	}

	public String getSection_name() {
		return section_name;
	}

	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}

	/**
	 * Gets the questions answered.
	 *
	 * @return the questions answered
	 */
	public String getQuestionsAnswered() {
		return questionsAnswered;
	}

	/**
	 * Sets the questions answered.
	 *
	 * @param questionsAnswered the new questions answered
	 */
	public void setQuestionsAnswered(String questionsAnswered) {
		this.questionsAnswered = questionsAnswered;
	}

	/**
	 * Gets the correct counnt.
	 *
	 * @return the correct counnt
	 */
	public int getCorrectCounnt() {
		return correctCounnt;
	}

	/**
	 * Sets the correct counnt.
	 *
	 * @param correctCounnt the new correct counnt
	 */
	public void setCorrectCounnt(int correctCounnt) {
		this.correctCounnt = correctCounnt;
	}

	/**
	 * Gets the wrong count.
	 *
	 * @return the wrong count
	 */
	public int getWrongCount() {
		return wrongCount;
	}

	/**
	 * Sets the wrong count.
	 *
	 * @param wrongCount the new wrong count
	 */
	public void setWrongCount(int wrongCount) {
		this.wrongCount = wrongCount;
	}

	/** The accuracy. */
	private  String accuracy;
	
	/**
	 * Gets the skill.
	 *
	 * @return the skill
	 */
	public String getSkill() {
		return skill;
	}
	
	/**
	 * Sets the skill.
	 *.
	 * @param skill the new skill
	 */
	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	/**
	 * Gets the accuracy.
	 *
	 * @return the accuracy
	 */
	public String getAccuracy() {
		return accuracy;
	}

	/**
	 * Sets the accuracy.
	 *
	 * @param accuracy the new accuracy
	 */
	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	 
	
	 
	
	/**
	 * Gets the average time.
	 *
	 * @return the average time
	 */
	public String getAverageTime() {
		return averageTime;
	}
	
	/**
	 * Sets the average time.
	 *
	 * @param averageTime the new average time
	 */
	public void setAverageTime(String averageTime) {
		this.averageTime = averageTime;
	}
	
	/** The average time. */
	private String averageTime;
	private String progress;

	public String getProgress() {
		return progress;
	}

	public void setProgress(String progress) {
		this.progress = progress;
	}
	private int section_id;

	public int getSection_id() {
		return section_id;
	}

	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	
}
