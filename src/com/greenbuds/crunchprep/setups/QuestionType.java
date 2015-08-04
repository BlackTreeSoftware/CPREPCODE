/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author rrajulapati
 * 
 */
public enum QuestionType {
	SINGLE_BLANK("Single Blank"), DOUBLE_BLANK("Double Blank"), TRIPLE_BLANK("Triple Blank"), SINGLE_ANSWER(
			"Single Answer"), MULTIPLE_ANSWER("Multiple Answer"), FILL_IN_SINGLE(
			"Fill In Single"), FILL_IN_DOUBLE("Fill In Double"), SELECT_IN(
			"Select In");
	private String questionType;
	

	/**
	 * @param questionType
	 */
	private QuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionType() {
		return questionType;
	}

	

}
