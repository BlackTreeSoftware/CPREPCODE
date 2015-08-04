/**
 * 
 */
package com.greenbuds.crunchprep.util;

import java.util.ArrayList;

import com.greenbuds.crunchprep.setups.DifficultyAlgorithm;

/**
 * @author rrajulapati
 * 
 */
public interface Algorithm<Bean> {
	
	public Bean getRandomPattern();
    public Bean getRandomPattern(int pattern);
	public ArrayList<Integer> getModifiedRandomList(ArrayList<Integer> randomList);
	public ArrayList<Integer> getModifiedRandomList(Bean Bean,ArrayList<Integer> randomList);
	
}
