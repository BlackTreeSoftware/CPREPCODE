package com.greenbuds.crunchprep.bo.contentcreator;

public class MailmasterTemplateBO {
	
	public int template_id;
	private String template_title;
	private String template_subject;
	private String template_body;
	private String template_status;
	
	
	
	public int getTemplate_id() {
		return template_id;
	}
	public void setTemplate_id(int template_id) {
		this.template_id = template_id;
	}
	public String getTemplate_title() {
		return template_title;
	}
	public void setTemplate_title(String template_title) {
		this.template_title = template_title;
	}
	public String getTemplate_subject() {
		return template_subject;
	}
	public void setTemplate_subject(String template_subject) {
		this.template_subject = template_subject;
	}
	public String getTemplate_body() {
		return template_body;
	}
	public void setTemplate_body(String template_body) {
		this.template_body = template_body;
	}
	public String getTemplate_status() {
		return template_status;
	}
	public void setTemplate_status(String template_status) {
		this.template_status = template_status;
	}

}
