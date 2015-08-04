/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author rrajulapati
 *
 */
public enum UserType {
	Admin(1,"admin"),Mentor(2,"mentor"),Content_Creator(3,"content creator"),User(4,"user");
	private int ROLE_ID;
	private String ROLE;
	private UserType(int ROLE_ID,String ROLE)
	{
		this.ROLE_ID=ROLE_ID;
		this.ROLE=ROLE;
	}
	public int getROLE_ID() {
		return ROLE_ID;
	}
	public String getROLE()
	{
		return ROLE;
	}
	
}
