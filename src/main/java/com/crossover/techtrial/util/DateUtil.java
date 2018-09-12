package com.crossover.techtrial.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
	
	public static LocalDateTime getDateLocalTime(String strDate) {
		return LocalDateTime.parse(strDate, formatter);
	}
	
	public static String getStrFromLocalDateTime(LocalDateTime localDateTime) {
		return formatter.format(localDateTime);
	}
	
}
