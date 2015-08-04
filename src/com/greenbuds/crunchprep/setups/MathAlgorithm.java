/**
 * 
 */
package com.greenbuds.crunchprep.setups;

import java.util.ArrayList;

import com.greenbuds.crunchprep.util.ArraysUtil;
import com.greenbuds.crunchprep.util.RandomNumber;
import com.sun.org.apache.bcel.internal.generic.ALOAD;

/**
 * @author rrajulapati
 * 
 */
public enum MathAlgorithm {

	_7_4_1_1_3_2_1_1("7+4+1+1+3+2+1+1", 1), _7_4_1_1_4_2_1("7+4+1+1+4+2+1", 2), _8_4_1_4_2_1(
			"8+4+1+4+2+1", 3), _8_4_1_1_3_2_1("8+4+1+1+3+2+1", 4);
	private String pattern;
	private int patternNumber;
	private static final int BOUND = 4;

	/**
	 * @param pattern
	 * @param patternId
	 */
	private MathAlgorithm(String pattern, int patternId) {

		this.pattern = pattern;
		this.patternNumber = patternId;
	}

	public String getPattern() {
		return pattern;
	}

	public int getPatternNumber() {
		return patternNumber;
	}

	public static MathAlgorithm getRandomPattern() {
		// System.out.println(RandomNumber.getNextInt(BOUND));
		return getRandomPattern(RandomNumber.getNextInt(BOUND));
	}

	public static MathAlgorithm getRandomPattern(int pattern) {
		if (pattern < 1 || pattern > BOUND)
			pattern = RandomNumber.getNextInt(BOUND);
		switch (pattern) {

		case 1:
			return _7_4_1_1_3_2_1_1;
		case 2:
			return _7_4_1_1_4_2_1;
		case 3:
			return _8_4_1_4_2_1;
		case 4:
			return _8_4_1_1_3_2_1;
		}
		return getRandomPattern();
	}

	public static ArrayList<Integer> getModifiedRandomList(
			ArrayList<Integer> randomList) {
		return getModifiedRandomList(getRandomPattern(), randomList);
	}

	public static ArrayList<Integer> getModifiedRandomList(
			MathAlgorithm algorithm, ArrayList<Integer> randomList) {
		int average;
		switch (algorithm) {

		case _7_4_1_1_3_2_1_1:
			java.util.Collections.sort(randomList.subList(0, 7));
			java.util.Collections.sort(randomList.subList(7, 11));
			average = ArraysUtil.getAverageOfArrays(randomList.subList(13, 16));
			randomList.set(13, average);
			randomList.set(14, average);
			randomList.set(15, average);
			java.util.Collections.sort(randomList.subList(16, 18));
			return randomList;

		case _7_4_1_1_4_2_1:
			java.util.Collections.sort(randomList.subList(0, 7));
			java.util.Collections.sort(randomList.subList(7, 11));
			average = ArraysUtil.getAverageOfArrays(randomList.subList(13, 17));
			randomList.set(13, average);
			randomList.set(14, average);
			randomList.set(15, average);
			randomList.set(16, average);
			java.util.Collections.sort(randomList.subList(17, 19));
			return randomList;

		case _8_4_1_4_2_1:
			java.util.Collections.sort(randomList.subList(0, 8));
			java.util.Collections.sort(randomList.subList(9, 13));
			average = ArraysUtil.getAverageOfArrays(randomList.subList(13, 17));
			randomList.set(13, average);
			randomList.set(14, average);
			randomList.set(15, average);
			randomList.set(16, average);
			java.util.Collections.sort(randomList.subList(17, 19));
			return randomList;
		case _8_4_1_1_3_2_1:
			java.util.Collections.sort(randomList.subList(0, 8));
			java.util.Collections.sort(randomList.subList(7, 11));
			average = ArraysUtil.getAverageOfArrays(randomList.subList(14, 17));
			randomList.set(14, average);
			randomList.set(15, average);
			randomList.set(16, average);
			java.util.Collections.sort(randomList.subList(17, 19));
			return randomList;
		}
		return randomList;

	}

}
