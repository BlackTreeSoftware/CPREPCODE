/**
 * 
 */
package com.greenbuds.crunchprep.setups;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

import com.greenbuds.crunchprep.util.ArraysUtil;
import com.greenbuds.crunchprep.util.RandomNumber;

/**
 * @author rrajulapati
 * 
 */
public enum DifficultyAlgorithm {

	_2_2_2_2_1_4_4_3("2+2+2+2+1+4+4+3",1), _2_2_2_3_4_4_3("2+2+2+3+4+4+3",2), _2_2_2_3_4_4_1_2(
			"2+2+2+3+4+4+1+2",3), _2_2_2_2_5_4_3("2+2+2+2+5+4+3",4), _2_2_2_2_5_4_1_2(
			"2+2+2+2+5+4+1+2",5);
	private String pattern;
	private int patternNumber;
	private static final int BOUND = 5;
   
	private DifficultyAlgorithm(String pattern,int patternNumber) {
		this.patternNumber=patternNumber;
		this.pattern = pattern;
	}

	public String getPattern() {
		return pattern;
	}
	public int getPatternNumber(){
		return patternNumber;
	}

	public static DifficultyAlgorithm getRandomPattern() {
		//System.out.println(RandomNumber.getNextInt(BOUND));
		return getRandomPattern(RandomNumber.getNextInt(BOUND));
	}

	public static DifficultyAlgorithm getRandomPattern(int pattern) {
		if(pattern<1||pattern>5) pattern=RandomNumber.getNextInt(BOUND);
		switch (pattern) {
		
		case 1:
			return _2_2_2_2_1_4_4_3;
		case 2:
			return _2_2_2_3_4_4_3;
		case 3:
			return _2_2_2_3_4_4_1_2;
		case 4:
			return _2_2_2_2_5_4_3;
		case 5:
			return _2_2_2_2_5_4_1_2;
		}
		return getRandomPattern();
	}

	public static ArrayList<Integer> getModifiedRandomList(ArrayList<Integer> randomList) {
		return getModifiedRandomList(getRandomPattern(), randomList);
	}

	public static ArrayList<Integer> getModifiedRandomList(DifficultyAlgorithm algorithm,
			ArrayList<Integer> randomList) {
	//	//System.out.println("\n Pattern Generation for : "+algorithm.pattern+"\n");
    //	//System.out.println(randomList);
		java.util.Collections.sort(randomList.subList(0, 2));
		java.util.Collections.sort(randomList.subList(2, 4));
		java.util.Collections.sort(randomList.subList(4, 6));
		java.util.Collections.sort(randomList.subList(13, 17));
	//	//System.out.println("\n  Random List "+randomList);
		////System.out.println("\n  Dificulty "+randomList);
		////System.out.println(" List Size is: "+randomList.size());
		int average;
		switch (algorithm) {
		case _2_2_2_2_1_4_4_3:
		//	//System.out.println("\n\n7 to 14 Reading Compehession  "+randomList.subList(6, 13));
	   //		//System.out.println("Last 3 Reading Comprehession  "+randomList.subList(17, 20));
		
			average=ArraysUtil.getAverageOfArrays(randomList.subList(6, 8));
		//	//System.out.println("\n Average of : "+randomList.subList(6, 8)+" is : "+average);
			randomList.set(6,average);
			randomList.set(7,average);
			
			average=ArraysUtil.getAverageOfArrays(randomList.subList(9, 13));
	//		//System.out.println("\n Average is: of  "+randomList.subList(9, 13)+" is : "+average);
			randomList.set(9,average);
			randomList.set(10,average);
			randomList.set(11,average);
			randomList.set(12,average);
			
			average=ArraysUtil.getAverageOfArrays(randomList.subList(17, 20));
		//	//System.out.println("\n Average is: of  "+randomList.subList(17, 20)+" is : "+average);
			randomList.set(17,average);
			randomList.set(18,average);
			randomList.set(19,average);
			
			break;
		case _2_2_2_3_4_4_3:
			
		//	//System.out.println("\n\n7 to 14 Reading Compehession  "+randomList.subList(6, 13));
	   //	//System.out.println("Last 3 Reading Comprehession  "+randomList.subList(17, 20));
			average=ArraysUtil.getAverageOfArrays(randomList.subList(6, 9));
	//		//System.out.println("\n Average of : "+randomList.subList(6, 9)+" is : "+average);
			randomList.set(6,average);
			randomList.set(7,average);
			randomList.set(8,average);
			
			average=ArraysUtil.getAverageOfArrays(randomList.subList(9, 13));
 //			//System.out.println("\n Average is: of  "+randomList.subList(7, 11)+" is : "+average);
			randomList.set(9,average);
			randomList.set(10,average);
			randomList.set(11,average);
			randomList.set(12,average);
			
			average=ArraysUtil.getAverageOfArrays(randomList.subList(17, 20));
			//	//System.out.println("\n Average is: of  "+randomList.subList(17, 20)+" is : "+average);
				randomList.set(17,average);
				randomList.set(18,average);
				randomList.set(19,average);
			
			break;
		case _2_2_2_3_4_4_1_2:
			
		///	//System.out.println("\n\n7 to 14 Reading Compehession  "+randomList.subList(6, 13));
		//	//System.out.println("Last 3 Reading Comprehession  "+randomList.subList(18, 20));
			average=ArraysUtil.getAverageOfArrays(randomList.subList(6, 9));
			//		//System.out.println("\n Average of : "+randomList.subList(6, 9)+" is : "+average);
					randomList.set(6,average);
					randomList.set(7,average);
					randomList.set(8,average);
					
					average=ArraysUtil.getAverageOfArrays(randomList.subList(9, 13));
		 //			//System.out.println("\n Average is: of  "+randomList.subList(7, 11)+" is : "+average);
					randomList.set(9,average);
					randomList.set(10,average);
					randomList.set(11,average);
					randomList.set(12,average);
			average=ArraysUtil.getAverageOfArrays(randomList.subList(18, 20));
			//	//System.out.println("\n Average is: of  "+randomList.subList(17, 20)+" is : "+average);
				randomList.set(18,average);
				randomList.set(19,average);
			break;
		case _2_2_2_2_5_4_3:
			
			
			//System.out.println("\n\n7 to 14 Reading Compehession  "+randomList.subList(6, 13));
			//System.out.println("Last 3 Reading Comprehession  "+randomList.subList(17, 20));
			average=ArraysUtil.getAverageOfArrays(randomList.subList(6, 8));
			//System.out.println("\n Average of : "+randomList.subList(6, 8)+" is : "+average);
			randomList.set(6,average);
			randomList.set(7,average);
			average=ArraysUtil.getAverageOfArrays(randomList.subList(8, 13));
			//System.out.println("\n Average is: of  "+randomList.subList(8, 13)+" is : "+average);
			randomList.set(8,average);
			randomList.set(9,average);
			randomList.set(10,average);
			randomList.set(11,average);
			randomList.set(12,average);
			average=ArraysUtil.getAverageOfArrays(randomList.subList(17, 20));
			//	//System.out.println("\n Average is: of  "+randomList.subList(17, 20)+" is : "+average);
				randomList.set(17,average);
				randomList.set(18,average);
				randomList.set(19,average);
			
			break;
		case _2_2_2_2_5_4_1_2:
			
			//System.out.println("\n\n7 to 14 Reading Compehession  "+randomList.subList(6, 13));
			//System.out.println("Last 3 Reading Comprehession  "+randomList.subList(17, 20));
			average=ArraysUtil.getAverageOfArrays(randomList.subList(6, 8));
			//System.out.println("\n Average of : "+randomList.subList(6, 8)+" is : "+average);
			randomList.set(6,average);
			randomList.set(7,average);
			
			average=ArraysUtil.getAverageOfArrays(randomList.subList(8, 13));
			//System.out.println("\n Average is: of  "+randomList.subList(8, 13)+" is : "+average);
			randomList.set(8,average);
			randomList.set(9,average);
			randomList.set(10,average);
			randomList.set(11,average);
			randomList.set(12,average);
			
			average=ArraysUtil.getAverageOfArrays(randomList.subList(18, 20));
			//	//System.out.println("\n Average is: of  "+randomList.subList(17, 20)+" is : "+average);
				randomList.set(18,average);
				randomList.set(19,average);
			
			break;
		}
	//	//System.out.println("\n Random List After All Operations  "+randomList);
	//	//System.out.println("  List Size is: "+randomList.size());
		return randomList;

	}

	
}

