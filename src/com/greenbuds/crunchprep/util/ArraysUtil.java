/**
 * 
 */
package com.greenbuds.crunchprep.util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author rrajulapati
 *
 */
public class ArraysUtil {

	
	public static int getAverageOfArrays(List<Integer> array)
	{
		int count=0;
		for(int i:array)
		{
			 count+=i;
		}
		if(array.size()==0) return array.size();
		else return Math.round(count/array.size());
		
	}
	public static boolean isListHasDuplicates(List<Integer>list)
	{
		
		for(int i:list)
		{
			//System.out.println("\n Check Duplicates "+i+" duplicates Time "+Collections.frequency(list, i));
			if(Collections.frequency(list, i)>1) return true;
		}
		return false;
	}
	public static String getArrayListAsSet(List<Integer>list)
	{
			return list.toString().replace("[", "(").replace("]", ")");
	}

}
