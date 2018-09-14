package com.crossover.techtrial.util;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class DateUtilTest {
	
	@Test
	public void testGetDateLocalTimeAndGetStrFromLocalDateTime() {
		String testData = "2018-08-08T10:10:10";
		LocalDateTime localDate= DateUtil.getDateLocalTime(testData);
		String expected = DateUtil.getStrFromLocalDateTime(localDate);
		Assert.assertEquals(expected, testData);
	}

}
