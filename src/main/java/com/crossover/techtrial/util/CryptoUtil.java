package com.crossover.techtrial.util;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

public class CryptoUtil {
	
	
	private static final String CRYPTO_PASSWORD = "jasyptPassword";
	private static final String CRYPTO_ALGORITHM = "PBEWithMD5AndDES";

	public static  String encrypto(String text){
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(CRYPTO_PASSWORD);                
		encryptor.setAlgorithm(CRYPTO_ALGORITHM);   
		return encryptor.encrypt(text);
	}
	
	public static  String decrypto(String text){
		StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		encryptor.setPassword(CRYPTO_PASSWORD);                
		encryptor.setAlgorithm(CRYPTO_ALGORITHM);   
		return encryptor.decrypt(text);
	}
	
	
	public static void main(String[] args) {
		System.out.println(encrypto("jasyptPassword"));
	}

}