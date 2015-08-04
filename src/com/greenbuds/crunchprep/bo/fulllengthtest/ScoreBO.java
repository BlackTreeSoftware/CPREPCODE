package com.greenbuds.crunchprep.bo.fulllengthtest;

public class ScoreBO {
	
	private String level1;
	private String level2;
	private String level3;
	private String level4;
	private String level5;
	private String quantMaximum;
	private String quantMinimum;
	private String verbalMaximum;
	private String verbalMinimum;
	private double quantScaledScoreRange;
	private double verbalScaledScoreRange;
	private double scaledScoreRange;
	
	private double quantRawScore;	
	private double verbalRawScore;
	
	private int testno;
	private int userid;
	
	
	
	public int getTestno() {
		return testno;
	}
	public void setTestno(int testno) {
		this.testno = testno;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public double getQuantRawScore() {
		return quantRawScore;
	}
	public void setQuantRawScore(double quantRawScore) {
		this.quantRawScore = quantRawScore;
	}
	public double getVerbalRawScore() {
		return verbalRawScore;
	}
	public void setVerbalRawScore(double verbalRawScore) {
		this.verbalRawScore = verbalRawScore;
	}
	public int getQuantPercentile() {
		return quantPercentile;
	}
	public void setQuantPercentile(int quantPercentile) {
		this.quantPercentile = quantPercentile;
	}
	 
	public int getTotalVerbalScaledScore() {
		return totalVerbalScaledScore;
	}
	public void setTotalVerbalScaledScore(int totalVerbalScaledScore) {
		this.totalVerbalScaledScore = totalVerbalScaledScore;
	}
	public int getTotalQuantScaledScore() {
		return totalQuantScaledScore;
	}
	public void setTotalQuantScaledScore(int totalQuantScaledScore) {
		this.totalQuantScaledScore = totalQuantScaledScore;
	}
	public int getVerbalPercentile() {
		return verbalPercentile;
	}
	public void setVerbalPercentile(int verbalPercentile) {
		this.verbalPercentile = verbalPercentile;
	}
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
 
	private int quantPercentile;
	private int totalVerbalScaledScore;
	private int verbalPercentile;
	private int totalScore;
	private int totalQuantScaledScore;
	
	/*private double quantPercentile;
	private double */
	
    public double getScaledScoreRange() {
		return scaledScoreRange;
	}
	public void setScaledScoreRange(double scaledScoreRange) {
		this.scaledScoreRange = scaledScoreRange;
	}
	public double getQuantScaledScoreRange() {
		return quantScaledScoreRange;
	}
	public void setQuantScaledScoreRange(double quantScaledScoreRange) {
		this.quantScaledScoreRange = quantScaledScoreRange;
	}
	public double getVerbalScaledScoreRange() {
		return verbalScaledScoreRange;
	}
	public void setVerbalScaledScoreRange(double verbalScaledScoreRange) {
		this.verbalScaledScoreRange = verbalScaledScoreRange;
	}
	private int sectionId; 
	
	public String getLevel1() {
		return level1;
	}
	public void setLevel1(String level1) {
		this.level1 = level1;
	}
	 
	public int getSectionId() {
		return sectionId;
	}
	public void setSectionId(int sectionId) {
		this.sectionId = sectionId;
	}
	public String getLevel2() {
		return level2;
	}
	public void setLevel2(String level2) {
		this.level2 = level2;
	}
	public String getLevel3() {
		return level3;
	}
	public void setLevel3(String level3) {
		this.level3 = level3;
	}
	public String getLevel4() {
		return level4;
	}
	public void setLevel4(String level4) {
		this.level4 = level4;
	}
	public String getLevel5() {
		return level5;
	}
	public void setLevel5(String level5) {
		this.level5 = level5;
	}
	public String getQuantMaximum() {
		return quantMaximum;
	}
	public void setQuantMaximum(String quantMaximum) {
		this.quantMaximum = quantMaximum;
	}
	public String getQuantMinimum() {
		return quantMinimum;
	}
	public void setQuantMinimum(String quantMinimum) {
		this.quantMinimum = quantMinimum;
	}
	public String getVerbalMaximum() {
		return verbalMaximum;
	}
	public void setVerbalMaximum(String verbalMaximum) {
		this.verbalMaximum = verbalMaximum;
	}
	public String getVerbalMinimum() {
		return verbalMinimum;
	}
	public void setVerbalMinimum(String verbalMinimum) {
		this.verbalMinimum = verbalMinimum;
	}
	
	

}
