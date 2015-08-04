/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author rrajulapati
 *
 */
public enum QuantQuestions {
	INTRODUCTION(1),GENERAL_STRATEGIES(2),FDP(3),ARITHMETIC_NUMBER_PROPERTIES(4),ALGEBRA(5),GEOMETRY(6),COORDINATE_GEOMETRY(7),WORD_PROBLEMS(8),
	STATISTICS_COUNTING_PROBABILITY(9),DATA_INTERPRETATION(10);
    private int lessionId;
    
	/**
	 * @param name
	 */
	private QuantQuestions(int ordinal) {
		this.lessionId=ordinal;
		
	}
	public int getLessionId() {
		return lessionId;
	}
	public void setLessionId(int lessionId) {
		this.lessionId = lessionId;
	}
	
    
}
