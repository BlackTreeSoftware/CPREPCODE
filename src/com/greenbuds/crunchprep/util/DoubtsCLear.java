/**
 * 
 */
package com.greenbuds.crunchprep.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

import com.greenbuds.crunchprep.bo.lessons.LessonsHierarchy;
import com.greenbuds.crunchprep.bo.server.ServerProperties;
import com.greenbuds.crunchprep.setups.DifficultyAlgorithm;
import com.greenbuds.crunchprep.setups.DifficultyAlgorithm;
import com.greenbuds.crunchprep.setups.MathAlgorithm;

/**
 * @author rrajulapati
 * 
 */
public class DoubtsCLear {
	private final int DIFFICULTY_LEVEL_LIMIT = 5;

	public static void main(String[] args) throws JSONException {
		JSONObject object = new JSONObject();
		// object.put("limit",ServerProperties.getInistance().getDifficulty());
		// object.put("pattern",ServerProperties.getInistance().getPattern());
		DifficultyAlgorithm algorithm = DifficultyAlgorithm._2_2_2_2_1_4_4_3;
		object.put("limit", 3.0);
		object.put("pattern", algorithm);

		new DoubtsCLear().alogirithmPreparation(object);
	}

	int counter = 0;

	public ArrayList<Integer> alogirithmPreparation(JSONObject object)
			throws JSONException {

		double limit = object.getDouble("limit");

		// System.out.println(" Limit is; " + limit);
		/*
		 * ArrayList<Integer> randomList = cLear.getRandomPattern(limit);
		 * System.out.println("\n\n Random List is: " + randomList);
		 * 
		 * 
		 * for (int i : randomList) { if (i == 1) _1++; if (i == 2) _2++; if (i
		 * == 3) _3++; if (i == 4) _4++; if (i == 5) _5++; _total+=i; }
		 * 
		 * 
		 * System.out.println("\n 1 are : " + _1 + "\n 2 are : " + _2 +
		 * "\n 3 are : " + _3 + "\n 4 are : " + _4 + "\n 5 are : " + _5+"\n" +
		 * " Overal Total is: \n"+_total);
		 */
		boolean conditionSatisfied = false;
		int loop = 0;
		int sum = 0;
		byte _1 = 0;
		byte _2 = 0;
		byte _3 = 0;
		byte _4 = 0;
		byte _5 = 0;
		double _total = 0;

		ArrayList<Integer> randomList = null;
		ArrayList<Integer> randomList2 = null;
		if (object.get("pattern") instanceof DifficultyAlgorithm) {
			DifficultyAlgorithm algorithm = (DifficultyAlgorithm) object
					.get("pattern");
			int pattern = algorithm.getPatternNumber();// object.getInt("pattern");
			counter = 0;
			/*
			 * System.out.println("\n Starting Time is; " +
			 * Calendar.getInstance().getTime());
			 */
			do {
				loop++;
				randomList = getRandomPattern(limit);
				randomList2 = (ArrayList<Integer>) randomList.clone();

				// System.out.println("\n\n Random List is: " +
				// randomList+"\n Loop is; "+loop);
				algorithm = DifficultyAlgorithm.getRandomPattern(pattern);

				DifficultyAlgorithm
						.getModifiedRandomList(algorithm, randomList);
				// System.out.println("\n\n After Modifie Random List is: " +
				// randomList);
				conditionSatisfied = getSumOfArrays(randomList, limit);
			} while (conditionSatisfied);
		} else {
			MathAlgorithm algorithm = (MathAlgorithm) object.get("pattern");

			int pattern = algorithm.getPatternNumber();// object.getInt("pattern");

			counter = 0;
			/*
			 * System.out.println("\n Starting Time is; " +
			 * Calendar.getInstance().getTime());
			 */
			do {
				loop++;
				randomList = getRandomPattern(limit);
				randomList2 = (ArrayList<Integer>) randomList.clone();

				// System.out.println("\n\n Random List is: " +
				// randomList+"\n Loop is; "+loop);
				//algorithm = MathAlgorithm.getRandomPattern(pattern);

				MathAlgorithm.getModifiedRandomList(algorithm, randomList);
				// System.out.println("\n\n After Modifie Random List is: " +
				// randomList);
				conditionSatisfied = getSumOfArrays(randomList, limit);
			} while (conditionSatisfied);

		}
		/*
		 * System.out.println("\n Finished at Time is; " +
		 * Calendar.getInstance().getTime());
		 * System.out.println("\n Patter is : " + algorithm +
		 * " and it's Serise is: " + algorithm.getPatternNumber());
		 * System.out.println("\n GLobal COunter is; " + counter);
		 */
		System.out.println("\n Original Pattern is: " + randomList2);
		/*
		 * _1 = 0; _2 = 0; _3 = 0; _4 = 0; _5 = 0; _total = 0;
		 * 
		 * for (int i : randomList2) { if (i == 1) _1++; if (i == 2) _2++; if (i
		 * == 3) _3++; if (i == 4) _4++; if (i == 5) _5++; _total += i; }
		 * System.out.println("\n 1 are : " + _1 + "\n 2 are : " + _2 +
		 * "\n 3 are : " + _3 + "\n 4 are : " + _4 + "\n 5 are : " + _5 + "\n" +
		 * "  Overal Total is: " + _total + " and Difficulty is : " + _total /
		 * 20 + "" + "\n Round Figure is: " + Math.round(_total / 20));
		 */
		System.out.println("\n After Modification is; " + randomList);
		return randomList;
		/*
		 * _1 = 0; _2 = 0; _3 = 0; _4 = 0; _5 = 0; _total = 0;
		 * 
		 * for (int i : randomList) { if (i == 1) _1++; if (i == 2) _2++; if (i
		 * == 3) _3++; if (i == 4) _4++; if (i == 5) _5++; _total += i; }
		 * System.out.println("\n\t\t Number of Iterations for New Pattern \t: "
		 * + loop);
		 * 
		 * System.out.println("\n 1 are : " + _1 + "\n 2 are : " + _2 +
		 * "\n 3 are : " + _3 + "\n 4 are : " + _4 + "\n 5 are : " + _5 + "\n" +
		 * "  Overal Total is: " + _total + " and Difficulty is : " + _total /
		 * 20 + "" + "\n Round Figure is: " + Math.round(_total / 20));
		 */

		/*
		 * BufferedReader br = new BufferedReader(new
		 * InputStreamReader(System.in));
		 * 
		 * byte total = 0; try {
		 * 
		 * System.out.println("\n\n1s are : " + _1);
		 * System.out.println(" Plese Enter Correct Answers\n\t"); String s =
		 * br.readLine(); total += Integer.parseInt(s) * 1;
		 * System.out.println(" 2s are : " + _2);
		 * System.out.println("Plese Enter Correct Answers\n\t"); s =
		 * br.readLine(); total += Integer.parseInt(s) * 2;
		 * System.out.println("3s are : " + _3);
		 * System.out.println("Plese Enter Correct Answers\n\t"); s =
		 * br.readLine(); total += Integer.parseInt(s) * 3;
		 * System.out.println("4s are : " + _4);
		 * System.out.println("Plese Enter Correct Answers\n\t"); s =
		 * br.readLine(); total += Integer.parseInt(s) * 4;
		 * System.out.println("5s are : " + _5);
		 * System.out.println("Plese Enter Correct Answers\n\t"); s =
		 * br.readLine(); total += Integer.parseInt(s) * 5;
		 * 
		 * } catch (IOException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } System.out.print(" Total : " + total); try {
		 * limit = cLear.getNextDifficultyLevel(total);
		 * System.out.println("\n Next Difficulty Level is : " + limit);
		 * randomList = cLear.getRandomPattern(limit);
		 * 
		 * System.out.println("\n\n Random List is: " + randomList); _1 = 0; _2
		 * = 0; _3 = 0; _4 = 0; _5 = 0; for (int i : randomList) { if (i == 1)
		 * _1++; if (i == 2) _2++; if (i == 3) _3++; if (i == 4) _4++; if (i ==
		 * 5) _5++; } System.out.println("\n 1 are : " + _1 + "\n 2 are : " + _2
		 * + "\n 3 are : " + _3 + "\n 4 are : " + _4 + "\n 5 are : " + _5);
		 * 
		 * } catch (Exception e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	private ArrayList<Integer> getRandomPattern(double limit) {
		limit *= 20;
		/*
		 * byte loop=100; int _1s=0; int _2s=0; int _3s=0; int _4s=0; int _5s=0;
		 */

		byte _1 = 0;
		byte _2 = 0;
		byte _3 = 0;
		byte _4 = 0;
		byte _5 = 0;

		ArrayList<Integer> randomsList = new ArrayList<Integer>((int) limit);

		/* for(byte index=1;index<=loop;index++){ */
		boolean gatePass = true;

		start: while (gatePass) {
			counter++;
			randomsList.clear();
			do {
				randomsList
						.add(RandomNumber.getNextInt(DIFFICULTY_LEVEL_LIMIT));
			} while (randomsList.size() < 20);
			int sum = 0;
			_1 = 0;
			_2 = 0;
			_3 = 0;
			_4 = 0;
			_5 = 0;
			for (int i : randomsList) {
				sum += i;
				if (limit <= 40 && i == 5)
					continue start;
				if (limit >= 80 && i == 1)
					continue start;
				if (i == 1)
					_1++;
				if (i == 2)
					_2++;
				if (i == 3)
					_3++;
				if (i == 4)
					_4++;
				if (i == 5)
					_5++;
			}
			gatePass = !(sum == limit);
			// Limiting the number of difficulties to 3,4,5 when limit is 60.
			if (limit == 60) {
				if (_1 <= 2 || _1 >= 6 || _2 <= 2 || _2 >= 6 || _3 <= 2
						|| _3 >= 6 || _4 <= 2 || _4 >= 6 || _5 <= 2 || _5 >= 6) {
					gatePass = true;
				}
			}
		}
		/*
		 * System.out.println(" \n Counter is : " +
		 * counter+"\n\tList is: "+randomsList.toString().replace("[",
		 * "(").replace("]", ")").replace(",","+"));
		 */
		/*
		 * _1s+=_1; _2s+=_2; _3s+=_3; _4s+=_4; _5s+=_5;
		 */
		// System.out.println("\n\t\t\t"+index+" Time ");

		// System.out.println("\n 1 are : "+_1+"\n 2 are : "+_2+"\n 3 are : "+_3+"\n 4 are : "+_4+"\n 5 are : "+_5);
		return randomsList;
		/*
		 * } System.out.println("\n\n\t\t Sum of  Difficulties in "+loop+
		 * " times for : "+(limit/20)+"\n");
		 * System.out.println(" 1s are : "+_1s+
		 * "\n 2s are : "+_2s+"\n 3s are : "+
		 * _3s+"\n 4s are : "+_4s+"\n 5s are : "+_5s+"");
		 * System.out.println("\n\t\t Averages \n");
		 * System.out.println(" 1s Average "
		 * +_1s/loop+"\n 2s Average is : "+_2s/loop
		 * +"\n 3s Average is : "+_3s/loop
		 * +"\n 4s As/loop+rage is : "+_4s/loop+"\n 5s Average is : "+_5s/loop);
		 */
	}

	public static double getNextDifficultyLevel(byte difficulty) {

		if (difficulty == 0 || difficulty == 1)
			return 1.6;
		else if (difficulty == 2 || difficulty == 3)
			return 1.7;
		else if (difficulty == 4 || difficulty == 5)
			return 1.8;
		else if (difficulty == 6 || difficulty == 7)
			return 1.9;
		else if (difficulty == 8 || difficulty == 9)
			return 2.0;
		else if (difficulty == 10 || difficulty == 11)
			return 2.1;
		else if (difficulty == 12 || difficulty == 13)
			return 2.2;
		else if (difficulty == 14 || difficulty == 15)
			return 2.3;
		else if (difficulty == 16 || difficulty == 17)
			return 2.4;
		else if (difficulty == 18 || difficulty == 19)
			return 2.5;
		else if (difficulty == 20 || difficulty == 21)
			return 2.6;
		else if (difficulty == 22 || difficulty == 23)
			return 2.7;
		else if (difficulty == 24 || difficulty == 25)
			return 2.8;
		else if (difficulty == 26 || difficulty == 27)
			return 2.9;
		else if (difficulty == 28 || difficulty == 29)
			return 3.0;
		else if (difficulty >= 30 && difficulty <= 32)
			return 3.1;
		else if (difficulty == 33 || difficulty == 34)
			return 3.2;
		else if (difficulty == 35 || difficulty == 36)
			return 3.3;
		else if (difficulty == 37 || difficulty == 38)
			return 3.4;
		else if (difficulty == 39 || difficulty == 40)
			return 3.5;
		else if (difficulty == 41 || difficulty == 42)
			return 3.6;
		else if (difficulty >= 43 && difficulty <= 45)
			return 3.7;
		else if (difficulty == 46 || difficulty == 47)
			return 3.8;
		else if (difficulty == 48 || difficulty == 49)
			return 3.9;
		else if (difficulty == 50 || difficulty == 51)
			return 4.0;
		else if (difficulty == 52 || difficulty == 53)
			return 4.1;
		else if (difficulty >= 54 && difficulty <= 56)
			return 4.2;
		else if (difficulty == 57 || difficulty == 58)
			return 4.3;
		else if (difficulty == 59 || difficulty == 60)
			return 4.4;
		else
			return 3.0;
	}

	private boolean getSumOfArrays(List<Integer> randomList, double limit) {

		int count = 0;
		for (int i : randomList) {
			count += i;
		}
		// System.out.println("\n Total Count is: "+count+" \n limit is : "+limit+" Limit *20 is : "+limit*20);
		return (limit * 20) != count;

	}
}
