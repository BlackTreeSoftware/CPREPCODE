package com.greenbuds.crunchprep.bo.practicesession;

public class ResultLessionBO {
	
	private int lesson_id;
	private String lesson_name;
	private int attemp_lessons;
	private int lessons_correct;
	private int lessons_incorrect;
	private int user_id;
	private int test_no;
	private double maxValue;
	private String lessonAccurate;
	private String lessonResult;
	private int section_id;
	
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public String getLessonResult() {
		return lessonResult;
	}
	public void setLessonResult(String lessonResult) {
		this.lessonResult = lessonResult;
	}
	public ResultLessionBO() {
		super();
		this.setLessonAccurate("  ");
		// TODO Auto-generated constructor stub
	}
	public double getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(double maxValue) {
		this.maxValue = maxValue;
	}
	private double percentageOfLessons;
	public double getPercentageOfLessons() {
		return percentageOfLessons;
	}
	public void setPercentageOfLessons(double percentageOfLessons) {
		this.percentageOfLessons = percentageOfLessons;
	}
	public int getLesson_id() {
		return lesson_id;
	}
	public int getTest_no() {
		return test_no;
	}
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public void setLesson_id(int lesson_id) {
		this.lesson_id = lesson_id;
	}
	public String getLesson_name() {
		return lesson_name;
	}
	public void setLesson_name(String lesson_name) {
		this.lesson_name = lesson_name;
	}
	public int getAttemp_lessons() {
		return attemp_lessons;
	}
	public void setAttemp_lessons(int attemp_lessons) {
		this.attemp_lessons = attemp_lessons;
	}
	public int getLessons_correct() {
		return lessons_correct;
	}
	public void setLessons_correct(int lessons_correct) {
		this.lessons_correct = lessons_correct;
	}
	public int getLessons_incorrect() {
		return lessons_incorrect;
	}
	public void setLessons_incorrect(int lessons_incorrect) {
		this.lessons_incorrect = lessons_incorrect;
	}
	public String getLessonAccurate() {
		return lessonAccurate;
	}
	public void setLessonAccurate(String lessonAccurate) {
		this.lessonAccurate = lessonAccurate;
	}
	
	
 	

}
