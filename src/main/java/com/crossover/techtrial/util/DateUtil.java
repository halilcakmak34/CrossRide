package com.crossover.techtrial.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	public static LocalDateTime getDateLocalTime(String strDate) {
		return LocalDateTime.parse(strDate, formatter);
	}
	
	public static void main(String[] args) {
		LocalDateTime one = getDateLocalTime("2018-01-11T01:00:00"),two=getDateLocalTime("2018-01-11T01:00:01");
		
		if(one.compareTo(two)<0) {
			System.out.println("One büyük");
		}
		else {
			System.out.println("Two büyük");
		}
	}
}
