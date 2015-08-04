package com.greenbuds.crunchprep.bo.registration;

import java.io.File;
import java.util.Map;

public class ChangePasswordBo {
	
	private String password;
	//private String newpassword;
	private String confpassword;

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	/*public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}*/
	public String getConfpassword() {
		return confpassword;
	}
	public void setConfpassword(String confpassword) {
		this.confpassword = confpassword;
	}
	
/*image upload BO   */	
	private File userimage;
	private String userimageContentType;
	private String userimageFileName;
	
	public File getUserimage() {
		return userimage;
	}
	public void setUserimage(File userimage) {
		this.userimage = userimage;
	}
	

	
    /*user settings BO*/	
	
	public String getUserimageContentType() {
		return userimageContentType;
	}
	public void setUserimageContentType(String userimageContentType) {
		this.userimageContentType = userimageContentType;
	}
	public String getUserimageFileName() {
		return userimageFileName;
	}
	public void setUserimageFileName(String userimageFileName) {
		this.userimageFileName = userimageFileName;
	}

	private int settingsid;
	private String testperformance;
	private String others;
	private String userid;
	
	
	public String getTestperformance() {
		return testperformance;
	}
	public void setTestperformance(String testperformance) {
		this.testperformance = testperformance;
	}
	public int getSettingsid() {
		return settingsid;
	}
	public void setSettingsid(int settingsid) {
		this.settingsid = settingsid;
	}
	public String getOthers() {
		return others;
	}
	public void setOthers(String others) {
		this.others = others;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	
	}
