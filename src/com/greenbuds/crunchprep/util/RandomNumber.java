/**
 * 
 */
package com.greenbuds.crunchprep.util;

import java.util.Random;

/**
 * @author rrajulapati
 *
 */
public final class RandomNumber {

	 private static Random random=new Random();
	 public static int getNextInt(int bound)
	 {
		 return random.nextInt(bound) + 1;
	 }

}
