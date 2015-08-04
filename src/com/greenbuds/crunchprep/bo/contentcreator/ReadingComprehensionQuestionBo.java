package com.greenbuds.crunchprep.bo.contentcreator;

public class ReadingComprehensionQuestionBo {

	private int questionId;
	private String accessType;
	private String referral;
	private int testId;
	private String questionTitle;
	private String directions;
	private String passageType;
	private String questionTypeName;	
	private int passageId;	
	private String question;
	private String questionType;
	private String choices[];
	private String answers[];
	private String skills;
	private int difficultyId;
	private String solutionText;
	private String solutionVideo;
	private String status;
	private String passageName;
	private String difficultyName;
	private String skillsName;
	 
	 public ReadingComprehensionQuestionBo()
	 {
		 
	 }
	public String getDifficultyName() {
		return difficultyName;
	}
	public void setDifficultyName(String difficultyName) {
		this.difficultyName = difficultyName;
	}
	public String getSkillsName() {
		return skillsName;
	}
	public void setSkillsName(String skillsName) {
		this.skillsName = skillsName;
	}
	public String getPassageName() {
		return passageName;
	}
	public void setPassageName(String passageName) {
		this.passageName = passageName;
	}
	public String getAccessType() {
		return accessType;
	}
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	public String getReferral() {
		return referral;
	}
	public void setReferral(String referral) {
		this.referral = referral;
	}
	public int getTestId() {
		return testId;
	}
	public void setTestId(int testId) {
		this.testId = testId;
	}
	public String getQuestionTitle() {
		return questionTitle;
	}
	public void setQuestionTitle(String questionTitle) {
		this.questionTitle = questionTitle;
	}
	public String getDirections() {
		return directions;
	}
	public void setDirections(String directions) {
		this.directions = directions;
	}
	public String getPassageType() {
		return passageType;
	}
	public void setPassageType(String passageType) {
		this.passageType = passageType;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getQuestionType() {
		return questionType;
	}
	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}
	public String[] getChoices() {
		return choices;
	}
	public void setChoices(String[] choices) {
		this.choices = choices;
	}
	public String[] getAnswers() {
		return answers;
	}
	public void setAnswers(String[] answers) {
		this.answers = answers;
	}
	public String getSkills() {
		return skills;
	}
	public void setSkills(String skills) {
		this.skills = skills;
	}
	public int getDifficultyId() {
		return difficultyId;
	}
	public void setDifficultyId(int difficultyId) {
		this.difficultyId = difficultyId;
	}
	public String getSolutionText() {
		return solutionText;
	}
	public void setSolutionText(String solutionText) {
		this.solutionText = solutionText;
	}
	public String getSolutionVideo() {
		return solutionVideo;
	}
	public void setSolutionVideo(String solutionVideo) {
		this.solutionVideo = solutionVideo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getPassageId() {
		return passageId;
	}
	public void setPassageId(int passageId) {
		this.passageId = passageId;
	}
	public String getQuestionTypeName() {
		return questionTypeName;
	}
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
}
