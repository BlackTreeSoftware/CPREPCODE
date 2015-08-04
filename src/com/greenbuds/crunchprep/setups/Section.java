/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author rrajulapati
 *
 */
public enum Section {
	QUANTITATIVE(1,"Quantitative"),VERBAL(2,"Verbal"),AWA(3,"AWA");
	private int sectionId;
	private String sectionName;
	private Section(int sectionId, String sectionName) {
		this.sectionId = sectionId;
		this.sectionName = sectionName;
	}
	public int getSectionId() {
		return sectionId;
	}
	public String getSectionName() {
		return sectionName;
	}
	
	
	

}
