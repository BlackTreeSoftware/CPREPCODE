/**
 * 
 */
package com.greenbuds.crunchprep.setups;

import com.greenbuds.crunchprep.util.RandomNumber;

/**
 * @author rrajulapati
 * 
 */
public enum QuantComparssion {
	_2_2_1_1_1(1){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, _2_1_1_1_2(2){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, _2_1_1_2_1(3){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, _2_1_2_1_1(4){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, SEVEN_QUESTIONS(-1) {
		private int bound = 4;

		public QuantComparssion getRandomPattern() {
			return getRandomPattern(RandomNumber.getNextInt(bound));
		}
		public QuantComparssion getRandomPattern(int pattern) {
			if (pattern < 1 || pattern >bound)
				pattern = RandomNumber.getNextInt(bound);
			switch (pattern) {

			case 1:
				return _2_2_1_1_1;
			case 2:
				return  _2_1_1_1_2;
			case 3:
				return  _2_1_1_2_1;
			case 4:
				return _2_1_2_1_1;
			}
			return getRandomPattern();
			
		}

	},
	_2_2_2_1_1(1){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, _2_2_1_2_1(2){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, _2_2_1_1_2(3){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, _2_1_2_1_2(4){
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, _2_1_1_2_2(5) {
		@Override
		public QuantComparssion getRandomPattern() {
			// TODO Auto-generated method stub
			return this;
		}

		@Override
		public QuantComparssion getRandomPattern(int pattern) {
			// TODO Auto-generated method stub
			return this;
		}
	}, EIGHT_QUESTIONS(-1) {
		private int bound = 5;

		public QuantComparssion getRandomPattern() {
			return getRandomPattern(RandomNumber.getNextInt(bound));
		}
		public QuantComparssion getRandomPattern(int pattern) {
			if (pattern < 1 || pattern > 5)
				pattern = RandomNumber.getNextInt(bound);
			switch (pattern) {

			case 1:
				return _2_2_2_1_1;
			case 2:
				return  _2_2_1_2_1;
			case 3:
				return _2_2_1_1_2;
			case 4:
				return _2_1_2_1_2;
			case 5:
				return _2_1_1_2_2;
			}
			return getRandomPattern();
			
		}
	};
	private int patternNumber;
	public abstract QuantComparssion getRandomPattern();
	public abstract QuantComparssion getRandomPattern(int pattern);

	
	
	private QuantComparssion(int patternNumber) {
		this.patternNumber=patternNumber;
	}


	public int getPatternNumber() {
		return patternNumber;
	}

	
	
}
