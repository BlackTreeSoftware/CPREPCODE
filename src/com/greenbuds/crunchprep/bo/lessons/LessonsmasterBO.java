package com.greenbuds.crunchprep.bo.lessons;

/**
 * The Class LessonBO.
 */
public class LessonsmasterBO {
	private int lesson_confidence_id;
	private int user_id;
	private int sublesson_id;
	private String lesson_confidence;
	private int lesson_id;
	private String lesson_name;
	private int section_id;
	private String section_name;
	private String sublesson_name;
	private String lesson_text;
	private String lesson_video;
	private String average_time;
	private String access_type;
	private String lesson_status;
	private String lesson_master;
	private String checkPage;
	
	public String getCheckPage() {
		return checkPage;
	}
	public void setCheckPage(String checkPage) {
		this.checkPage = checkPage;
	}
	public String getLesson_master() {
		return lesson_master;
	}
	public void setLesson_master(String lesson_master) {
		this.lesson_master = lesson_master;
	}
	//for bookmarks
	private int bookmark_id;
	private String lesson_bookmark;
	
	
	private String lesson_date;
	private String time_spent;
	private int[] skill_id;
	private String[] skill_name;
	private int hierarchy_id;
	private int parent_id;
	private int child_id;
	
	//for Lession Notes
	private int notes_id;
	private String lesson_note_name;
		
	private String action_type;
	private String action_desc;
	private String action_date;
	private String email;
	private String action_title;
	
	//session handling
	
	private int activity_id;
	private String activity_status;
	private String last_lesson;
	
	
    public String getLast_lesson() {
		return last_lesson;
	}
	public void setLast_lesson(String last_lesson) {
		this.last_lesson = last_lesson;
	}
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public String getActivity_status() {
		return activity_status;
	}
	public void setActivity_status(String activity_status) {
		this.activity_status = activity_status;
	}
	public String getAction_title() {
		return action_title;
	}
	public void setAction_title(String action_title) {
		this.action_title = action_title;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
	public String getAction_desc() {
		return action_desc;
	}
	public void setAction_desc(String action_desc) {
		this.action_desc = action_desc;
	}
	public String getAction_date() {
		return action_date;
	}
	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}
	public int getLesson_id() {
		return lesson_id;
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
	public int getSection_id() {
		return section_id;
	}
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public String getSublesson_name() {
		return sublesson_name;
	}
	public void setSublesson_name(String sublesson_name) {
		this.sublesson_name = sublesson_name;
	}
	public String getLesson_text() {
		return lesson_text;
	}
	public void setLesson_text(String lesson_text) {
		this.lesson_text = lesson_text;
	}
	public String getLesson_video() {
		return lesson_video;
	}
	public void setLesson_video(String lesson_video) {
		this.lesson_video = lesson_video;
	}
	public String getAverage_time() {
		return average_time;
	}
	public void setAverage_time(String average_time) {
		this.average_time = average_time;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	public String getLesson_status() {
		return lesson_status;
	}
	public void setLesson_status(String lesson_status) {
		this.lesson_status = lesson_status;
	}
	public int getBookmark_id() {
		return bookmark_id;
	}
	public void setBookmark_id(int bookmark_id) {
		this.bookmark_id = bookmark_id;
	}
	public String getLesson_bookmark() {
		return lesson_bookmark;
	}
	public void setLesson_bookmark(String lesson_bookmark) {
		this.lesson_bookmark = lesson_bookmark;
	}
	public String getLesson_date() {
		return lesson_date;
	}
	public void setLesson_date(String lesson_date) {
		this.lesson_date = lesson_date;
	}
	public String getTime_spent() {
		return time_spent;
	}
	public void setTime_spent(String time_spent) {
		this.time_spent = time_spent;
	}
	public int[] getSkill_id() {
		return skill_id;
	}
	public void setSkill_id(int[] skill_id) {
		this.skill_id = skill_id;
	}
	public String[] getSkill_name() {
		return skill_name;
	}
	public void setSkill_name(String[] skill_name) {
		this.skill_name = skill_name;
	}
	public int getHierarchy_id() {
		return hierarchy_id;
	}
	public void setHierarchy_id(int hierarchy_id) {
		this.hierarchy_id = hierarchy_id;
	}
	public int getParent_id() {
		return parent_id;
	}
	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}
	public int getChild_id() {
		return child_id;
	}
	public void setChild_id(int child_id) {
		this.child_id = child_id;
	}
	public int getNotes_id() {
		return notes_id;
	}
	public void setNotes_id(int notes_id) {
		this.notes_id = notes_id;
	}
 
	public int getLesson_confidence_id() {
		return lesson_confidence_id;
	}
	public void setLesson_confidence_id(int lesson_confidence_id) {
		this.lesson_confidence_id = lesson_confidence_id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getSublesson_id() {
		return sublesson_id;
	}
	public void setSublesson_id(int sublesson_id) {
		this.sublesson_id = sublesson_id;
	}
	public String getLesson_confidence() {
		return lesson_confidence;
	}
	public void setLesson_confidence(String lesson_confidence) {
		this.lesson_confidence = lesson_confidence;
	}
	public String getLesson_note_name() {
		return lesson_note_name;
	}
	public void setLesson_note_name(String lesson_note_name) {
		this.lesson_note_name = lesson_note_name;
	}
	
    
}
