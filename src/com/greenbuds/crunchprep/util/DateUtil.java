/***************************************************************************
 * Copyright (c) 2014 , rrajulapati , All rights reserved.   * Product Name : crunchprep
 * Version 1.0
 *  
 * 
 */
package com.greenbuds.crunchprep.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.greenbuds.crunchprep.setups.DateFormate;

// TODO: Auto-generated Javadoc
/**
 * The Class DateUtil.
 */
public class DateUtil {

	 
	/** The Constant DATE_FORMAT. */
	private static final java.text.SimpleDateFormat DATE_FORMAT=new java.text.SimpleDateFormat();
	
	/** The Constant FORMAT. */
	private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
	
	/** The Constant SQLFORMAT. */
	private static final SimpleDateFormat SQLFORMAT = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * Gets the date.
	 *
	 * @param dateFormate the date formate
	 * @return the date
	 */
	public static String getDate(DateFormate dateFormate)
	{
		DATE_FORMAT.applyPattern(dateFormate.getFormate());
        return DATE_FORMAT.format(Calendar.getInstance().getTime());
    }
	
	/**
	 * Gets the date.
	 *
	 * @param inputDate the input date
	 * @param dateFormate the date formate
	 * @return the date
	 */
	public static String getDate(String inputDate,DateFormate dateFormate) 
	{
		if(inputDate==null||inputDate.isEmpty())return "";
		DATE_FORMAT.applyPattern(dateFormate.getFormate());
        try {
       	return DATE_FORMAT.format(FORMAT.parse(inputDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			try {
				return DATE_FORMAT.format(SQLFORMAT.parse(inputDate));
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				//e1.printStackTrace();
				return inputDate;
			}
		}
	}
}
