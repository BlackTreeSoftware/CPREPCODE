/**
 * 
 */
package com.greenbuds.crunchprep.setups;

/**
 * @author rrajulapati
 *
 */
public enum Categories {
	TEXT_COMPLETION(1,Section.VERBAL,"Text Completion"),
	SENTENCE_EQUIVALENCE(2,Section.VERBAL,"Sentence Equivalence"),
	READING_COMPREHENSION(3,Section.VERBAL,"Reading Comprehension"),
	QUNTITATIVE_COMPREHENSION(4,Section.QUANTITATIVE,"Quantitative Comparision"),
	DATA_INTERPRETATION(5,Section.QUANTITATIVE,"Data Interpretation"),
	PROBLEM_SOLVING(6,Section.QUANTITATIVE,"Problem Solving"),
	ANALYZE_AN_ISSUE(7,Section.AWA,"Analyze an Issue"),
	ANALYZE_AN_ARGUMENT(8,Section.AWA,"Analyze an argument"),
	CRITICAL_REASONING(9,Section.VERBAL,"Critical Reasoning");
	
	private int categorieId;
	private Section section;
	private String categorieName;
	
	private Categories(int categorieId, Section section, String sectionName) {
		this.categorieId = categorieId;
		this.section = section;
		this.categorieName = sectionName;
	}
	public int getCategorieId() {
		return categorieId;
	}
	public Section getSection() {
		return section;
	}
	public String getCategorieName() {
		return categorieName;
	}
	
	
	
}
