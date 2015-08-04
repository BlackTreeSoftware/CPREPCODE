/***************************************************************************
 * Copyright (c) 2014 , sganji , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.bo.registration;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginUserBO.
 */
public class LoginUserBO {

	/** The email_id. */
	private String email_id;
	
	/** The password. */
	private String password;
	
	/** The first_name. */
	private String first_name;
	
	/** The last_name. */
	private String last_name;
	
	/** The role. */
	private String role;
	
	/** The activation_date. */
	private String activation_date;
	
	/** The account_satus. */
	private String subscription_status;
	private String avatar_path;
	
	private int quant_questions;
	private int verbal_question;
	
	
	

	public int getQuant_questions() {
		return quant_questions;
	}

	public void setQuant_questions(int quant_questions) {
		this.quant_questions = quant_questions;
	}

	public int getVerbal_question() {
		return verbal_question;
	}

	public void setVerbal_question(int verbal_question) {
		this.verbal_question = verbal_question;
	}

	public String getSubscription_status() {
		return subscription_status;
	}

	public void setSubscription_status(String subscription_status) {
		this.subscription_status = subscription_status;
	}

	/** The admin_name. */
	private String admin_name;
	
	/** The check.is Scoial user / normal user */
	private String check;
	
	/** The message. */
	private String message;
	
	/** The login history id. */
	private int loginHistoryId;
	
	/** The operation status. */
	private boolean operationStatus;
	
	public String getAvatar_path() {
		return avatar_path;
	}

	public void setAvatar_path(String avatar_path) {
		this.avatar_path = avatar_path;
	}

	/** The role_id. */
	private int role_id;
	
	/** The account_id. */
	private int account_id;
	
	/** The subscription_id. */
	private int subscription_id;
	
	/** The reg_date. */
	private String reg_date;
	
	/** The start_date. */
	private String start_date;
	
	/** The end_date. */
	private String end_date;
	
	/** The account_status. */
	private String account_status;
	
	/** The token_status. */
	private String token_status;
	
	/** The user_id. */
	private int user_id;
	
	/** The login_time. */
	private String login_time;
	
	/** The ipaddress. */
	private String ipaddress;
	
	/** The status.Return page type */
	private String status;
	private int activity_id;

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	/**
	 * Gets the admin_name.
	 *
	 * @return the admin_name
	 */
	public String getAdmin_name() {
		return admin_name;
	}

	/**
	 * Sets the admin_name.
	 *
	 * @param admin_name the new admin_name
	 */
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	/**
	 * Gets the activation_date.
	 *
	 * @return the activation_date
	 */
	public String getActivation_date() {
		return activation_date;
	}

	/**
	 * Sets the activation_date.
	 *
	 * @param activation_date the new activation_date
	 */
	public void setActivation_date(String activation_date) {
		this.activation_date = activation_date;
	}

	/**
	 * Gets the account_satus.
	 *
	 * @return the account_satus
	 */
	

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * Gets the email_id.
	 *
	 * @return the email_id
	 */
	public String getEmail_id() {
		return email_id;
	}

	/**
	 * Sets the email_id.
	 *
	 * @param email_id the new email_id
	 */
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	/**
	 * Gets the first_name.
	 *
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * Sets the first_name.
	 *
	 * @param first_name the new first_name
	 */
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	/**
	 * Gets the last_name.
	 *
	 * @return the last_name
	 */
	public String getLast_name() {
		return last_name;
	}

	/**
	 * Sets the last_name.
	 *
	 * @param last_name the new last_name
	 */
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the ipaddress.
	 *
	 * @return the ipaddress
	 */
	public String getIpaddress() {
		return ipaddress;
	}

	/**
	 * Sets the ipaddress.
	 *
	 * @param ipaddress the new ipaddress
	 */
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}

	/**
	 * Gets the login_time.
	 *
	 * @return the login_time
	 */
	public String getLogin_time() {
		return login_time;
	}

	/**
	 * Sets the login_time.
	 *
	 * @param login_time the new login_time
	 */
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
	}

	/**
	 * Gets the user_id.
	 *
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}

	/**
	 * Sets the user_id.
	 *
	 * @param user_id the new user_id
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	/**
	 * Gets the role_id.
	 *
	 * @return the role_id
	 */
	public int getRole_id() {
		return role_id;
	}

	/**
	 * Sets the role_id.
	 *
	 * @param role_id the new role_id
	 */
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	/**
	 * Gets the account_id.
	 *
	 * @return the account_id
	 */
	public int getAccount_id() {
		return account_id;
	}

	/**
	 * Sets the account_id.
	 *
	 * @param account_id the new account_id
	 */
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

	/**
	 * Gets the subscription_id.
	 *
	 * @return the subscription_id
	 */
	public int getSubscription_id() {
		return subscription_id;
	}

	/**
	 * Sets the subscription_id.
	 *
	 * @param subscription_id the new subscription_id
	 */
	public void setSubscription_id(int subscription_id) {
		this.subscription_id = subscription_id;
	}

	/**
	 * Gets the reg_date.
	 *
	 * @return the reg_date
	 */
	public String getReg_date() {
		return reg_date;
	}

	/**
	 * Sets the reg_date.
	 *
	 * @param reg_date the new reg_date
	 */
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	/**
	 * Gets the start_date.
	 *
	 * @return the start_date
	 */
	public String getStart_date() {
		return start_date;
	}

	/**
	 * Sets the start_date.
	 *
	 * @param start_date the new start_date
	 */
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}

	/**
	 * Gets the end_date.
	 *
	 * @return the end_date
	 */
	public String getEnd_date() {
		return end_date;
	}

	/**
	 * Sets the end_date.
	 *
	 * @param end_date the new end_date
	 */
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}

	/**
	 * Gets the account_status.
	 *
	 * @return the account_status
	 */
	public String getAccount_status() {
		return account_status;
	}

	/**
	 * Sets the account_status.
	 *
	 * @param account_status the new account_status
	 */
	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	/**
	 * Gets the token_status.
	 *
	 * @return the token_status
	 */
	public String getToken_status() {
		return token_status;
	}

	/**
	 * Sets the token_status.
	 *
	 * @param token_status the new token_status
	 */
	public void setToken_status(String token_status) {
		this.token_status = token_status;
	}

	/**
	 * Gets the check.
	 *
	 * @return the check
	 */
	public String getCheck() {
		return check;
	}

	/**
	 * Sets the check.
	 *
	 * @param check the new check
	 */
	public void setCheck(String check) {
		this.check = check;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the login history id.
	 *
	 * @return the login history id
	 */
	public int getLoginHistoryId() {
		return loginHistoryId;
	}

	/**
	 * Sets the login history id.
	 *
	 * @param loginHistoryId the new login history id
	 */
	public void setLoginHistoryId(int loginHistoryId) {
		this.loginHistoryId = loginHistoryId;
	}

	/**
	 * Checks if is operation status.
	 *
	 * @return true, if is operation status
	 */
	public boolean isOperationStatus() {
		return operationStatus;
	}

	/**
	 * Sets the operation status.
	 *
	 * @param operationStatus the new operation status
	 */
	public void setOperationStatus(boolean operationStatus) {
		this.operationStatus = operationStatus;
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
	
	
	public String getProfile_status() {
		return profile_status;
	}

	public void setProfile_status(String profile_status) {
		this.profile_status = profile_status;
	}

	private String profile_status;

}
