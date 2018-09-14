package com.crossover.techtrial.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.crossover.techtrial.config.Constants;

public class DateUtil {
	
	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Constants.DATE_LONG_FORMAT);
	
	public static LocalDateTime getDateLocalTime(String strDate) {
		return LocalDateTime.parse(strDate, formatter);
	}
	
	public static String getStrFromLocalDateTime(LocalDateTime localDateTime) {
		return formatter.format(localDateTime);
	}
	
}
