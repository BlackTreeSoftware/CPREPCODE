package com.greenbuds.crunchprep.bo.registration;

public class VrplBO {

	private	int vrpl_id;
	private String fb_like;
	private int user_id;
	private String fb_share;
	private String g_like;
	private String g_share;
	private String g_follow;
	private String tw_tweet;
	private String tw_follow;
	private int votes ;
	private String actiontype;
	
	private int quant_questions;
	private int verbal_question;
	
	public int getVrpl_id() {
		return vrpl_id;
	}
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
	public void setVrpl_id(int vrpl_id) {
		this.vrpl_id = vrpl_id;
	}
	public String getFb_like() {
		return fb_like;
	}
	public void setFb_like(String fb_like) {
		this.fb_like = fb_like;
	}
	public String getFb_share() {
		return fb_share;
	}
	public void setFb_share(String fb_share) {
		this.fb_share = fb_share;
	}
	public String getG_like() {
		return g_like;
	}
	public void setG_like(String g_like) {
		this.g_like = g_like;
	}
	public String getG_share() {
		return g_share;
	}
	public void setG_share(String g_share) {
		this.g_share = g_share;
	}
	public String getG_follow() {
		return g_follow;
	}
	public void setG_follow(String g_follow) {
		this.g_follow = g_follow;
	}
	public String getTw_tweet() {
		return tw_tweet;
	}
	public void setTw_tweet(String tw_tweet) {
		this.tw_tweet = tw_tweet;
	}
	public String getTw_follow() {
		return tw_follow;
	}
	public void setTw_follow(String tw_follow) {
		this.tw_follow = tw_follow;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getActiontype() {
		return actiontype;
	}
	public void setActiontype(String actiontype) {
		this.actiontype = actiontype;
	}
    	

}
