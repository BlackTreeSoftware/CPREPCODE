package com.greenbuds.crunchprep.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.greenbuds.crunchprep.bo.fulllengthtest.ScoreBO;




public class DifficultyLevelScore {
	
	private final static InputStream inputStream=DifficultyLevelScore.class.getResourceAsStream("/score-prediction.properties");
	private static  Properties properties =null;
	private static ScoreBO levelValues = new ScoreBO();
	
	public static ScoreBO propertiesValues() throws IOException{
		
		 intializePropertiesFile();
		 levelValues.setLevel1(properties.getProperty("difficulty.level1"));
		 levelValues.setLevel2(properties.getProperty("difficulty.level2"));
		 levelValues.setLevel3(properties.getProperty("difficulty.level3"));
		 levelValues.setLevel4(properties.getProperty("difficulty.level4"));
		 levelValues.setLevel5(properties.getProperty("difficulty.level5"));
		 levelValues.setQuantMaximum(properties.getProperty("quantscore.maximum"));
		 levelValues.setQuantMinimum(properties.getProperty("quantscore.minimum"));
		 levelValues.setVerbalMaximum(properties.getProperty("verbalscore.maximum"));
		 levelValues.setVerbalMaximum(properties.getProperty("verbalscore.minimum"));
		 
		 return levelValues;
	}
	
	 
	


	private static Properties intializePropertiesFile() throws IOException
	{
		if(properties==null)
		{
			properties=new Properties();
			properties.load(inputStream);	
		}
		return properties;
	}
	
}
