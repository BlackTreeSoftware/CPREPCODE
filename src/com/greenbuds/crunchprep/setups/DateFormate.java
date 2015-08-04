package com.greenbuds.crunchprep.setups;

public enum DateFormate {
	MYSQL("yyyy-MM-dd"),JAVADATE("yyyy-MM-dd HH:mm:ss"),DD_MM_YYY_HH_MM("dd-MM-YYYY HH:mm"),
	MMMM_dd_YYY_HH_MM("EEEE MMMM dd, yyyy, h:mm a"),MMMM_dd_YYY("EEEE MMMM dd, yyyy");
	
	private String FORMATE;

	private DateFormate(String fORMATE) {
		FORMATE = fORMATE;
	}

	public String getFormate() {
		return FORMATE;
	}

}
