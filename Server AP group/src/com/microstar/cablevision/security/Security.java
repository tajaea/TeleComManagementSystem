package com.microstar.cablevision.security;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Security {
	public static final Logger logger=LogManager.getLogger(Security.class);
	static String ALGORITHM = "SHA-1";
	public static String hashPassword(byte[] inputbytes) {
		String hashedValue = "";
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(ALGORITHM);
			messageDigest.update(inputbytes);
			byte[]digestedBytes = messageDigest.digest();
			hashedValue = DatatypeConverter.printHexBinary(digestedBytes).toLowerCase();
		}
		catch(Exception e){
			System.out.println("An error occurred while fetching your credentials. Please try again later");	
			logger.error("An exception was caught in the hashPassword method in the Security class");
		}
		return hashedValue;
	}

}