package com.greenbuds.crunchprep.bo.fulllengthtest;

import java.text.SimpleDateFormat;

public class OverallPerformanceBO {
	
	private String testName;
	private String testDate;
	private short quantScore;
	private short verbalScore;
	private double correctPercentage;
	private double incorrectPercentage;
	private short testId;
	private String testno;
	
	
	
	public short getTestId() {
		return testId;
	}
	public void setTestId(short testId) {
		this.testId = testId;
	}
	public String getTestName() {
		return testName;
	}
	public void setTestName(String testName) {
		this.testName = testName;
	}
	public String getTestDate() {
		
		return testDate;
	}
	public void setTestDate(String testDate) {
		this.testDate = testDate;
	}
	public short getQuantScore() {
		return quantScore;
	}
	public void setQuantScore(short quantScore) {
		this.quantScore = quantScore;
	}
	public short getVerbalScore() {
		return verbalScore;
	}
	public void setVerbalScore(short verbalScore) {
		this.verbalScore = verbalScore;
	}
	public double getCorrectPercentage() {
		return correctPercentage;
	}
	public void setCorrectPercentage(double correctPercentage) {
		this.correctPercentage = correctPercentage;
	}
	public double getIncorrectPercentage() {
		return incorrectPercentage;
	}
	public void setIncorrectPercentage(double incorrectPercentage) {
		this.incorrectPercentage = incorrectPercentage;
	}
	public String getTestno() {
		return testno;
	}
	public void setTestno(String testno) {
		this.testno = testno;
	}
	
	
	

}
