package com.greenbuds.crunchprep.bo.contentcreator;

public class DataInterpretionBO {

	private String graph_title;
	private String graph;
	private int difficulty;
	private String difficulty_name;
	private String access_type;
	private int graph_id;
	public int getGraph_id() {
		return graph_id;
	}
	public void setGraph_id(int graph_id) {
		this.graph_id = graph_id;
	}
	public String getDifficulty_name() {
		return difficulty_name;
	}
	public void setDifficulty_name(String difficulty_name) {
		this.difficulty_name = difficulty_name;
	}
	public String getGraph_title() {
		return graph_title;
	}
	public void setGraph_title(String graph_title) {
		this.graph_title = graph_title;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public String getAccess_type() {
		return access_type;
	}
	public void setAccess_type(String access_type) {
		this.access_type = access_type;
	}
	public String getGraph_status() {
		return graph_status;
	}
	public void setGraph_status(String graph_status) {
		this.graph_status = graph_status;
	}
	private String graph_status;
	
	
	
}
