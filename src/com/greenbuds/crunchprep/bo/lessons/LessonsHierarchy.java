package com.greenbuds.crunchprep.bo.lessons;

public class LessonsHierarchy {

	private String mainLessonId;
	private String mainLessonName;
	private String sectionId;
	private String subLessonId;
	private String subLessonName;
	private String[] childs;
	private String bookMark;
	private String confidence;
	private String notes;
	private String perncentage;
	

	public String getMainLessonId() {
		return mainLessonId;
	}

	public void setMainLessonId(String mainLessonId) {
		this.mainLessonId = mainLessonId;
	}

	public String getMainLessonName() {
		return mainLessonName;
	}

	public void setMainLessonName(String mainLessonName) {
		this.mainLessonName = mainLessonName;
	}

	public String getSectionId() {
		return sectionId;
	}

	public void setSectionId(String sectionId) {
		this.sectionId = sectionId;
	}

	public String getSubLessonId() {
		return subLessonId;
	}

	public void setSubLessonId(String subLessonId) {
		this.subLessonId = subLessonId;
	}

	public String getSubLessonName() {
		return subLessonName;
	}

	public void setSubLessonName(String subLessonName) {
		this.subLessonName = subLessonName;
	}

	public String[] getChilds() {
		return childs;
	}

	public void setChilds(String[] childs) {
		this.childs = childs;
	}

	public String getBookMark() {
		return bookMark;
	}

	public void setBookMark(String bookMark) {
		this.bookMark = bookMark;
	}

	public String getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getPerncentage() {
		return perncentage;
	}

	public void setPerncentage(String perncentage) {
		this.perncentage = perncentage;
	}
  
}
