package com.greenbuds.crunchprep.bo.fulllengthtest;

import com.greenbuds.crunchprep.setups.ReviewReason;

public class QuestionAnalysisBO {
	
private short questionid;
private short sectionid;
private String result;
private String flag;
private String guess;
private String user_time;
private String fastSlowQues;
private String graphsdata;
private String reason;
private String testDate;
private String retryButton;
private String questionTitle;
private String typeId;
private String testNo;
private String testMasterId;
private String correctPercentage;
private String flaggedPercentage;
private String guessIncorrectPercentage;
private String historyData;


public String getTestMasterId() {
	return testMasterId;
}
public void setTestMasterId(String testMasterId) {
	this.testMasterId = testMasterId;
}
public String getTypeId() {
	return typeId;
}
public void setTypeId(String typeId) {
	this.typeId = typeId;
}
public String getTestNo() {
	return testNo;
}
public void setTestNo(String testNo) {
	this.testNo = testNo;
}
public String getRetryButton() {
	return retryButton;
}
public void setRetryButton(String retryButton) {
	this.retryButton = retryButton;
}
public String getTestDate() {
	return testDate;
}
public void setTestDate(String testDate) {
	this.testDate = testDate;
}
public String getQuestionTitle() {
	return questionTitle;
}
public void setQuestionTitle(String questionTitle) {
	this.questionTitle = questionTitle;
}

public String getReason() {
	return reason;
}
public void setReason(String reason) {
	this.reason = ReviewReason.getMyReason(reason);
}
public short getQuestionid() {
	return questionid;
}
public void setQuestionid(short questionid) {
	this.questionid = questionid;
}
public short getSectionid() {
	return sectionid;
}
public void setSectionid(short sectionid) {
	this.sectionid = sectionid;
}
public String getResult() {
	return result;
}
public void setResult(String result) {
	this.result = result;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public String getGuess() {
	return guess;
}
public void setGuess(String guess) {
	this.guess = guess;
}
public String getUser_time() {
	return user_time;
}
public void setUser_time(String user_time) {
	this.user_time = user_time;
}
public String getFastSlowQues() {
	return fastSlowQues;
}
public void setFastSlowQues(String fastSlowQues) {
	this.fastSlowQues = fastSlowQues;
}
public String getGraphsdata() {
	return graphsdata;
}
public void setGraphsdata(String graphsdata) {
	this.graphsdata = graphsdata;
}



}
