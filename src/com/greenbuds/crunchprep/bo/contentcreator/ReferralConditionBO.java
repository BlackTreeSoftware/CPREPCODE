package com.greenbuds.crunchprep.bo.contentcreator;

public class ReferralConditionBO {
	private int condition_id;
	/**
	 * @return the condition_id
	 */
	public int getCondition_id() {
		return condition_id;
	}
	/**
	 * @param condition_id the condition_id to set
	 */
	public void setCondition_id(int condition_id) {
		this.condition_id = condition_id;
	}
	/**
	 * @return the condition_title
	 */
	public String getCondition_title() {
		return condition_title;
	}
	/**
	 * @param condition_title the condition_title to set
	 */
	public void setCondition_title(String condition_title) {
		this.condition_title = condition_title;
	}
	/**
	 * @return the condition
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * @param condition the condition to set
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	private String condition_title;
	private String condition;
	private String status;
	private String comments;
	
	

}
