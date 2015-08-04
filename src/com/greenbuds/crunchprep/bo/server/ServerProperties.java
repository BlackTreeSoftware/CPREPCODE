/**
 * 
 */
package com.greenbuds.crunchprep.bo.server;

/**
 * @author rrajulapati
 *
 */
public  class ServerProperties {

	
	private static ServerProperties inistance;
	private String projectName;
	private String ipaddress;
	private int questions_count;
	private double difficulty;
	private int pattern;
	private int refer_limit;
    public int getQuestions_count() {
		return questions_count;
	}

	public void setQuestions_count(int questions_count) {
		this.questions_count = questions_count;
	}

	public int getDiff_id() {
		return diff_id;
	}

	public int getRefer_limit() {
		return refer_limit;
	}

	public void setRefer_limit(int refer_limit) {
		this.refer_limit = refer_limit;
	}

	public void setDiff_id(int diff_id) {
		this.diff_id = diff_id;
	}

	public String getQuestion_pool() {
		return question_pool;
	}

	public void setQuestion_pool(String question_pool) {
		this.question_pool = question_pool;
	}

	public int getQuestions_time_limit() {
		return questions_time_limit;
	}

	public void setQuestions_time_limit(int questions_time_limit) {
		this.questions_time_limit = questions_time_limit;
	}

	public String getQuestions_mode() {
		return questions_mode;
	}

	public void setQuestions_mode(String questions_mode) {
		this.questions_mode = questions_mode;
	}
	private int diff_id;
    private String question_pool;
	private int questions_time_limit;
	private String questions_mode;
	
	private ServerProperties(){
		
	}
	
	public static ServerProperties getInistance() {
		if(inistance==null)
		{
			inistance=new ServerProperties();
		}
		return inistance;
	}
	
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	

	public double getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(double difficulty) {
		this.difficulty = difficulty;
	}

	public int getPattern() {
		return pattern;
	}

	public void setPattern(int pattern) {
		this.pattern = pattern;
	}
	
	
}