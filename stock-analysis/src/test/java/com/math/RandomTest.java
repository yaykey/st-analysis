package com.math;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class RandomTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
//		Random random = new Random();
//		
//		for (int i=0; i<10; i++) {
//			System.out.println(random.nextInt(5));
//		}
		
		
//		double f = 0.1;
//		
//		System.out.println("f == 0->" + ((int)f == 0));
		
//		Calendar cal = Calendar.getInstance();
//		
//		cal.setTime(new Date());
//		
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
//		
//		cal.add(Calendar.DAY_OF_MONTH, -1);
//
//		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
		DecimalFormat decf = new DecimalFormat(".##");
		decf.format(new Double(4.0065634));
		
		System.out.println(decf.format(new Double(4.0065634)));
	}

}
