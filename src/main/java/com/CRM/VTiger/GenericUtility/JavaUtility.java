package com.CRM.VTiger.GenericUtility;

import java.util.Date;
import java.util.Random;

public class JavaUtility {
	/**
	 * This method helps us to generate random number
	 * 
	 * @return
	 */
	public int getRandom() {
		Random r=new Random();
		return r.nextInt(1000);
		
	}
	public String sysDateAndTime() {
		Date dt=new Date();
		String currentDate = dt.toString();
		return currentDate;
	}
	/**
	 * This method helps us to modify date and time
	 */
	public String modifiedDate() {
		Date dt=new Date();
		String[] cell = dt.toString().split(" ");
		String time = cell[3].replace(":", "-");
		String date = cell[2];
		String month = cell[1];
		String year = cell[5];
		String currentDate = time + " " + date + " " + month + " " + year;
		return currentDate;
		
	}
	
}
