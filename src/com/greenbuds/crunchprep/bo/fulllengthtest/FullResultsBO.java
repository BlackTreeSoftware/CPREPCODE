package com.greenbuds.crunchprep.bo.fulllengthtest;

 

//TODO: Auto-generated Javadoc
/**
* The Class ResultsBO.
*
* @author skaveti
*/
public class FullResultsBO {
	

	private String category_select;
	/** The row_id. */
	private  int row_id;
	
	/** The userid. */
	private  int userid;
	
	/** The section_id. */
	private  int section_id;
	
	/** The category_id. */
	private  int category_id;
	
	/** The type_id. */
	private  int type_id;
	
	/** The sublession_id. */
	private  int sublession_id; 
	
	/** The test_master_id. */
	private  int test_master_id;   
	
	/** The question_id. */
	private  int question_id;   
	
	/** The diff_id. */
	private  int diff_id;   
	
	/** The skill_id1. */
	private  int skill_id1;
	
	/** The skill_id2. */
	private  int skill_id2;   
	
	/** The skill_id3. */
	private  int skill_id3;   
	
	/** The test_date. */
	private  String test_date;  
	
	/** The user_time. */
	private  String user_time;
	
	/** The result. */
	private  String result; 
	
	/** The question_no. */
	private  String question_no;
	
	/** The user_answer. */
	private  String user_answer;
	
	/** The status. */
	private  String status;
	
	/** The flagstatus. */
	private  String flagstatus;
	
	/** The test_no. */
	private  int test_no;
	
	/** The question_number. */
	private  int question_number;   
	
	/** The comments. */
	private  String comments;
	
	private String percentageFlagged;
	private String percentageGuessed;
	private String flaggedIncorrect;
	
	private String reason;
	
	

	public String getStack_correct() {
		return stack_correct;
	}


	public void setStack_correct(String stack_correct) {
		this.stack_correct = stack_correct;
	}


	public String getStack_wrong() {
		return stack_wrong;
	}


	public void setStack_wrong(String stack_wrong) {
		this.stack_wrong = stack_wrong;
	}


	private String stack_correct;
	private String stack_wrong;
	
	public String getFlaggedIncorrect() {
		return flaggedIncorrect;
	}


	public void setFlaggedIncorrect(String flaggedIncorrect) {
		this.flaggedIncorrect = flaggedIncorrect;
	}


	//********** TOTAL RESULT PAGE DEATILS --- NOT IN DB
	private int total_qsns;
	private int correct;
	public String getPercentageFlagged() {
		return percentageFlagged;
	}


	public void setPercentageFlagged(String percentageFlagged) {
		this.percentageFlagged = percentageFlagged;
	}


	public String getPercentageGuessed() {
		return percentageGuessed;
	}


	public void setPercentageGuessed(String percentageGuessed) {
		this.percentageGuessed = percentageGuessed;
	}


	private int wrong;

	private String avg_userpace;
	private String avg_otherspace;
	private String total_userpace;
	private String PractiseSessioName;
	private String sessionCorrectPercentage;

	



	/**
	 * @return the avg_userpace
	 */
	public String getAvg_userpace() {
		return avg_userpace;
	}


	/**
	 * @param avg_userpace the avg_userpace to set
	 */
	public void setAvg_userpace(String avg_userpace) {
		this.avg_userpace = avg_userpace;
	}


	/**
	 * @return the avg_otherspace
	 */
	public String getAvg_otherspace() {
		return avg_otherspace;
	}


	/**
	 * @param avg_otherspace the avg_otherspace to set
	 */
	public void setAvg_otherspace(String avg_otherspace) {
		this.avg_otherspace = avg_otherspace;
	}


	/**
	 * @return the total_userpace
	 */
	public String getTotal_userpace() {
		return total_userpace;
	}


	/**
	 * @param total_userpace the total_userpace to set
	 */
	public void setTotal_userpace(String total_userpace) {
		this.total_userpace = total_userpace;
	}


	/**
	 * @return the total_otherspace
	 */
	public String getTotal_otherspace() {
		return total_otherspace;
	}


	/**
	 * @param total_otherspace the total_otherspace to set
	 */
	public void setTotal_otherspace(String total_otherspace) {
		this.total_otherspace = total_otherspace;
	}


	private String total_otherspace;
	private int avg_percentcorrect;
	
	private String result_page;
	
	/**
	 * @return the total_qsns
	 */
	public int getTotal_qsns() {
		return total_qsns;
	}


	/**
	 * @param total_qsns the total_qsns to set
	 */
	public void setTotal_qsns(int total_qsns) {
		this.total_qsns = total_qsns;
	}


	/**
	 * @return the correct
	 */
	public int getCorrect() {
		return correct;
	}


	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(int correct) {
		this.correct = correct;
	}


	/**
	 * @return the wrong
	 */
	public int getWrong() {
		return wrong;
	}


	/**
	 * @param wrong the wrong to set
	 */
	public void setWrong(int wrong) {
		this.wrong = wrong;
	}


	 
	/**
	 * @return the result_page
	 */
	public String getResult_page() {
		return result_page;
	}


	/**
	 * @param result_page the result_page to set
	 */
	public void setResult_page(String result_page) {
		this.result_page = result_page;
	}


	//********** TOTAL RESULT PAGE DEATILS --- NOT IN DB
	/**
	 * @return the row_id
	 */
	public int getRow_id() {
		return row_id;
	}


	/**
	 * @param row_id the row_id to set
	 */
	public void setRow_id(int row_id) {
		this.row_id = row_id;
	}


	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}


	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}


	/**
	 * @return the section_id
	 */
	public int getSection_id() {
		return section_id;
	}


	/**
	 * @param section_id the section_id to set
	 */
	public void setSection_id(int section_id) {
		this.section_id = section_id;
	}


	/**
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}


	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}


	/**
	 * @return the type_id
	 */
	public int getType_id() {
		return type_id;
	}


	/**
	 * @param type_id the type_id to set
	 */
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}


	/**
	 * @return the sublession_id
	 */
	public int getSublession_id() {
		return sublession_id;
	}


	/**
	 * @param sublession_id the sublession_id to set
	 */
	public void setSublession_id(int sublession_id) {
		this.sublession_id = sublession_id;
	}


	/**
	 * @return the test_master_id
	 */
	public int getTest_master_id() {
		return test_master_id;
	}


	/**
	 * @param test_master_id the test_master_id to set
	 */
	public void setTest_master_id(int test_master_id) {
		this.test_master_id = test_master_id;
	}


	/**
	 * @return the question_id
	 */
	public int getQuestion_id() {
		return question_id;
	}


	/**
	 * @param question_id the question_id to set
	 */
	public void setQuestion_id(int question_id) {
		this.question_id = question_id;
	}


	/**
	 * @return the diff_id
	 */
	public int getDiff_id() {
		return diff_id;
	}


	/**
	 * @param diff_id the diff_id to set
	 */
	public void setDiff_id(int diff_id) {
		this.diff_id = diff_id;
	}


	/**
	 * @return the skill_id1
	 */
	public int getSkill_id1() {
		return skill_id1;
	}


	/**
	 * @param skill_id1 the skill_id1 to set
	 */
	public void setSkill_id1(int skill_id1) {
		this.skill_id1 = skill_id1;
	}


	/**
	 * @return the skill_id2
	 */
	public int getSkill_id2() {
		return skill_id2;
	}


	/**
	 * @param skill_id2 the skill_id2 to set
	 */
	public void setSkill_id2(int skill_id2) {
		this.skill_id2 = skill_id2;
	}


	/**
	 * @return the skill_id3
	 */
	public int getSkill_id3() {
		return skill_id3;
	}


	/**
	 * @param skill_id3 the skill_id3 to set
	 */
	public void setSkill_id3(int skill_id3) {
		this.skill_id3 = skill_id3;
	}


	/**
	 * @return the test_date
	 */
	public String getTest_date() {
		return test_date;
	}


	/**
	 * @param test_date the test_date to set
	 */
	public void setTest_date(String test_date) {
		this.test_date = test_date;
	}


	/**
	 * @return the user_time
	 */
	public String getUser_time() {
		return user_time;
	}


	/**
	 * @param user_time the user_time to set
	 */
	public void setUser_time(String user_time) {
		this.user_time = user_time;
	}


	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}


	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}


	/**
	 * @return the question_no
	 */
	public String getQuestion_no() {
		return question_no;
	}


	/**
	 * @param question_no the question_no to set
	 */
	public void setQuestion_no(String question_no) {
		this.question_no = question_no;
	}


	/**
	 * @return the user_answer
	 */
	public String getUser_answer() {
		return user_answer;
	}


	/**
	 * @param user_answer the user_answer to set
	 */
	public void setUser_answer(String user_answer) {
		this.user_answer = user_answer;
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


	/**
	 * @return the flagstatus
	 */
	public String getFlagstatus() {
		return flagstatus;
	}


	/**
	 * @param flagstatus the flagstatus to set
	 */
	public void setFlagstatus(String flagstatus) {
		this.flagstatus = flagstatus;
	}


	/**
	 * @return the test_no
	 */
	public int getTest_no() {
		return test_no;
	}


	/**
	 * @param test_no the test_no to set
	 */
	public void setTest_no(int test_no) {
		this.test_no = test_no;
	}


	/**
	 * @return the question_number
	 */
	public int getQuestion_number() {
		return question_number;
	}


	/**
	 * @param question_number the question_number to set
	 */
	public void setQuestion_number(int question_number) {
		this.question_number = question_number;
	}


	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}


	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}


	public int getAvg_percentcorrect() {
		return avg_percentcorrect;
	}


	public void setAvg_percentcorrect(int avg_percentcorrect) {
		this.avg_percentcorrect = avg_percentcorrect;
	}

	public String getPractiseSessioName() {
		return PractiseSessioName;
	}


	public void setPractiseSessioName(String practiseSessioName) {
		PractiseSessioName = practiseSessioName;
	}
	 
	 

	public String getSessionCorrectPercentage() {
		return sessionCorrectPercentage;
	}


	public void setSessionCorrectPercentage(String sessionCorrectPercentage) {
		this.sessionCorrectPercentage = sessionCorrectPercentage;
	}


	public String getReason() {
		return reason;
	}


	public void setReason(String reason) {
		this.reason = reason;
	}


	public String getCategory_select() {
		return category_select;
	}


	public void setCategory_select(String category_select) {
		this.category_select = category_select;
	}


}
