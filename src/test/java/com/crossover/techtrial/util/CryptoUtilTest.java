package com.crossover.techtrial.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class CryptoUtilTest {
	
	@Test
	public void testCryptoAndDecrypto() {
		String tmpPassword = "testPassword";
		String crypto = CryptoUtil.encrypto(tmpPassword);
		String decryto = CryptoUtil.decrypto(crypto);
		Assert.assertEquals(decryto, tmpPassword);
	}
	
}
