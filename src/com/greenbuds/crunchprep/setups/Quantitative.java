/**
 * 
 */
package com.greenbuds.crunchprep.setups;

import com.greenbuds.crunchprep.util.RandomNumber;

/**
 * @author rrajulapati
 */
public class Quantitative {

	public enum SEVEN_QUESTIONS {

		_2_2_1_1_1(1), _1_2_1_1_2(2), _1_2_1_2_1(3), _1_2_2_1_1(4);
		private int patternNumber;
		private static int bound = 4;

		private SEVEN_QUESTIONS(int patternNumber) {
			this.patternNumber = patternNumber;
		}

		public int getPatternNumber() {
			return patternNumber;
		}

		public static SEVEN_QUESTIONS getRandomPattern() {
			return getRandomPattern(RandomNumber.getNextInt(bound));
		}

		public static SEVEN_QUESTIONS getRandomPattern(int pattern) {
			if (pattern < 1 || pattern > 4)
				pattern = RandomNumber.getNextInt(bound);
			switch (pattern) {

			case 1:
				return _2_2_1_1_1;
			case 2:
				return _1_2_1_1_2;
			case 3:
				return _1_2_1_2_1;
			case 4:
				return _1_2_2_1_1;
			}
			return getRandomPattern();

		}

	}

	public enum EIGHT_QUESTIONS {

		_2_2_2_1_1(1), _2_2_1_2_1(2), _2_2_1_1_2(3), _1_2_2_1_2(4), _1_2_1_2_2(
				5), _1_2_2_2_1(6);
		private int patternNumber;
		private static int bound = 6;

		private EIGHT_QUESTIONS(int patternNumber) {
			this.patternNumber = patternNumber;
		}

		public int getPatternNumber() {
			return patternNumber;
		}

		public static EIGHT_QUESTIONS getRandomPattern() {
			return getRandomPattern(RandomNumber.getNextInt(bound));
		}

		public static EIGHT_QUESTIONS getRandomPattern(int pattern) {
			if (pattern < 1 || pattern > 6)
				pattern = RandomNumber.getNextInt(bound);
			switch (pattern) {

			case 1:
				return _2_2_2_1_1;
			case 2:
				return _2_2_1_2_1;
			case 3:
				return _2_2_1_1_2;
			case 4:
				return _1_2_2_1_2;
			case 5:
				return _1_2_1_2_2;
			case 6:
				return _1_2_2_2_1;
			}
			return getRandomPattern();

		}

	}

}
