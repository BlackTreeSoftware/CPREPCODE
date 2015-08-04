/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author rrajulapati
 *
 */
public enum TestMaster {
PRACTICE_SESSION(1,"Practice Session"),PRACTICE_TEST(2,"Practice Test"),DIAGOSTIC_TEST(3,"Diagnostic Test");
private int testMasterId;
private String testMasterName;
/**
 * @param testMasterId
 * @param testMasterName
 */
private TestMaster(int testMasterId, String testMasterName) {
	this.testMasterId = testMasterId;
	this.testMasterName = testMasterName;
}
public int getTestMasterId() {
	return testMasterId;
}
public String getTestMasterName() {
	return testMasterName;
}



}
