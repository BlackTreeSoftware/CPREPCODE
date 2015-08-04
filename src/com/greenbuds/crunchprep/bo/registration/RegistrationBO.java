 
/***************************************************************************
 * Copyright (c) 2014 , gpathan , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
 
package com.greenbuds.crunchprep.bo.registration;

// TODO: Auto-generated Javadoc
/**
 * The Class RegistrationBO.
 */
public class RegistrationBO {
	
	/** The user id. */
	private int userId;
	/** The city. */
	private String city;
	
	
	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/** The address. */
	private String address;
	
	/**
	 * Gets the address.
	 *
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/** The country. */
	private String country;
	
	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country the new country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/** The first_name. */
	private String first_name;
	
	/** The last_name. */
	private String last_name;
	
	/** The test_date. */
	private String test_date;
	
	/** The target_score. */
	private int target_score_math;
	
	/**
	 * Gets the target_score_math.
	 *
	 * @return the target_score_math
	 */
	public int getTarget_score_math() {
		return target_score_math;
	}

	/**
	 * Sets the target_score_math.
	 *
	 * @param target_score_math the new target_score_math
	 */
	public void setTarget_score_math(int target_score_math) {
		this.target_score_math = target_score_math;
	}

	/**
	 * Gets the target_score_verbal.
	 *
	 * @return the target_score_verbal
	 */
	public int getTarget_score_verbal() {
		return target_score_verbal;
	}

	/**
	 * Sets the target_score_verbal.
	 *
	 * @param target_score_verbal the new target_score_verbal
	 */
	public void setTarget_score_verbal(int target_score_verbal) {
		this.target_score_verbal = target_score_verbal;
	}

	/** The target_score_verbal. */
	private int target_score_verbal;
	
	/** The actual_score. */
	private int actual_score;
	
	/** The phone_number. */
	private String phone_number;
	
	/** The where_listened. */
	private String[] where_listened;

	/**
	 * Gets the first_name.
	 *
	 * @return the first_name
	 */
	public String getFirst_name() {
		return first_name;
	}

	/**
	 * Gets the where_listened.
	 *
	 * @return the where_listened
	 */
	public String[] getWhere_listened() {
		return where_listened;
	}

	/**
	 * Sets the where_listened.
	 *
	 * @param where_listened the new where_listened
	 */
	public void setWhere_listened(String[] where_listened) {
		this.where_listened = where_listened;
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
	 * Gets the test_date.
	 *
	 * @return the test_date
	 */
	public String getTest_date() {
		return test_date;
	}

	/**
	 * Sets the test_date.
	 *
	 * @param test_date the new test_date
	 */
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}

	 

	/**
	 * Gets the actual_score.
	 *
	 * @return the actual_score
	 */
	public int getActual_score() {
		return actual_score;
	}

	/**
	 * Sets the actual_score.
	 *
	 * @param actual_score the new actual_score
	 */
	public void setActual_score(int actual_score) {
		this.actual_score = actual_score;
	}

	 

	/**
	 * Gets the phone_number.
	 *
	 * @return the phone_number
	 */
	public String getPhone_number() {
		return phone_number;
	}

	/**
	 * Sets the phone_number.
	 *
	 * @param phone_number the new phone_number
	 */
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}

	 

	/** The email_id. */
	private String email_id;
	
	/** The password. */
	private String password;
	
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
	
	/** The token. */
	private String token;
	
	private int noDays;
	
	
	
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
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	private int user_id;
	
	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	private String profile;
	private String testperformance;

	public String getTestperformance() {
		return testperformance;
	}

	public void setTestperformance(String testperformance) {
		this.testperformance = testperformance;
	}

	public int getNoDays() {
		return noDays;
	}

	public void setNoDays(int noDays) {
		this.noDays = noDays;
	}
	
	private String referralCode;

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}
	
	
	private int quant_questions;
	private int verbal_questions;


	public int getQuant_questions() {
		return quant_questions;
	}

	public void setQuant_questions(int quant_questions) {
		this.quant_questions = quant_questions;
	}

	public int getVerbal_questions() {
		return verbal_questions;
	}

	public void setVerbal_questions(int verbal_questions) {
		this.verbal_questions = verbal_questions;
	}
	
	private int refer_limit;


	public int getRefer_limit() {
		return refer_limit;
	}

	public void setRefer_limit(int refer_limit) {
		this.refer_limit = refer_limit;
	}
	
	
}
