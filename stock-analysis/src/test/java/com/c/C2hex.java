package com.c;

import java.io.UnsupportedEncodingException;

public class C2hex {

	/**
	 * @param args
	 * @throws UnsupportedEncodingException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		
		String str1 = "买盘";
		
		String str2 = "卖盘";
		
		String str3 = "中性盘";
		
		byte [] temp = str1.getBytes();
		
		for (byte b : temp) {
			System.out.println(b + ":" + b);
		}
		System.out.println();
		temp = str2.getBytes();
		
		for (byte b : temp) {
			System.out.println(b + ":" + b);
		}
		System.out.println();
		temp = str3.getBytes();
		
		for (byte b : temp) {
			System.out.println(b + ":" + b);
		}
		
		System.out.println("c2:" + Integer.parseInt("c2",16));

	}

}
