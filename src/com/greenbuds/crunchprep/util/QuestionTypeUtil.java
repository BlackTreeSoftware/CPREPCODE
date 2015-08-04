/**
 * 
 */
package com.greenbuds.crunchprep.util;

import com.greenbuds.crunchprep.setups.Categories;
import com.greenbuds.crunchprep.setups.QuestionType;

/**
 * @author rrajulapati
 *
 */
public class QuestionTypeUtil {
	
	
	public static int getQuestionTypeID(Categories categories,
			QuestionType questionType) {
		 int questionTypeID=0;
		switch (categories) {
		case TEXT_COMPLETION:
			if (questionType ==QuestionType.SINGLE_BLANK)
				questionTypeID = 1;
			else if (questionType ==QuestionType. DOUBLE_BLANK)
				questionTypeID = 2;
			else if (questionType ==QuestionType. TRIPLE_BLANK)
				questionTypeID = 3;
			break;
		case SENTENCE_EQUIVALENCE:
			if (questionType ==QuestionType. MULTIPLE_ANSWER)
				questionTypeID = 6;
			break;
		case READING_COMPREHENSION:
			if (questionType ==QuestionType. MULTIPLE_ANSWER)
				questionTypeID = 5;
			else if (questionType ==QuestionType. SINGLE_ANSWER)
				questionTypeID = 4;
			else if (questionType ==QuestionType. SELECT_IN)
				questionTypeID = 14;
			break;
		case QUNTITATIVE_COMPREHENSION:
			if (questionType ==QuestionType. SINGLE_ANSWER)
				questionTypeID = 7;
			break;
		case DATA_INTERPRETATION:
			if (questionType ==QuestionType. MULTIPLE_ANSWER)
				questionTypeID = 12;
			else if (questionType ==QuestionType. SINGLE_ANSWER)
				questionTypeID = 11;
			break;
		case PROBLEM_SOLVING:
			if (questionType ==QuestionType.FILL_IN_DOUBLE)
				questionTypeID = 13;
			else if (questionType ==QuestionType. FILL_IN_SINGLE)
				questionTypeID = 10;
			else if (questionType ==QuestionType. MULTIPLE_ANSWER)
				questionTypeID = 9;
			else if (questionType ==QuestionType. SINGLE_ANSWER)
				questionTypeID = 8;
			break;
		case CRITICAL_REASONING :
			if (questionType ==QuestionType.SINGLE_ANSWER)
				questionTypeID = 15;
			break;
		}
		return questionTypeID;
	}
	
	public static int getQuestionTypeID(Categories categories,
			String questionType) {
		 int questionTypeID=0;
		switch (categories) {
		case TEXT_COMPLETION:
			if (questionType .equalsIgnoreCase(QuestionType.SINGLE_BLANK.getQuestionType()))
				questionTypeID = 1;
			else if (questionType .equalsIgnoreCase(QuestionType. DOUBLE_BLANK.getQuestionType()))
				questionTypeID = 2;
			else if (questionType .equalsIgnoreCase(QuestionType. TRIPLE_BLANK.getQuestionType()))
				questionTypeID = 3;
			break;
		case SENTENCE_EQUIVALENCE:
			if (questionType .equalsIgnoreCase(QuestionType. MULTIPLE_ANSWER.getQuestionType()))
				questionTypeID = 6;
			break;
		case READING_COMPREHENSION:
			if (questionType .equalsIgnoreCase(QuestionType. MULTIPLE_ANSWER.getQuestionType()))
				questionTypeID = 5;
			else if (questionType .equalsIgnoreCase(QuestionType. SINGLE_ANSWER.getQuestionType()))
				questionTypeID = 4;
			else if (questionType .equalsIgnoreCase(QuestionType. SELECT_IN.getQuestionType()))
				questionTypeID = 14;
			break;
		case QUNTITATIVE_COMPREHENSION:
			if (questionType .equalsIgnoreCase(QuestionType. SINGLE_ANSWER.getQuestionType()))
				questionTypeID = 7;
			break;
		case DATA_INTERPRETATION:
			if (questionType .equalsIgnoreCase(QuestionType. MULTIPLE_ANSWER.getQuestionType()))
				questionTypeID = 12;
			else if (questionType .equalsIgnoreCase(QuestionType. SINGLE_ANSWER.getQuestionType()))
				questionTypeID = 11;
			break;
		case PROBLEM_SOLVING:
			if (questionType .equalsIgnoreCase(QuestionType.FILL_IN_DOUBLE.getQuestionType()))
				questionTypeID = 13;
			else if (questionType .equalsIgnoreCase(QuestionType. FILL_IN_SINGLE.getQuestionType()))
				questionTypeID = 10;
			else if (questionType .equalsIgnoreCase(QuestionType. MULTIPLE_ANSWER.getQuestionType()))
				questionTypeID = 9;
			else if (questionType .equalsIgnoreCase(QuestionType. SINGLE_ANSWER.getQuestionType()))
				questionTypeID = 8;
			break;
		case CRITICAL_REASONING :
			if (questionType .equalsIgnoreCase(QuestionType.SINGLE_ANSWER.getQuestionType()))
				questionTypeID = 15;
			break;
		}
		return questionTypeID;
	}
}
