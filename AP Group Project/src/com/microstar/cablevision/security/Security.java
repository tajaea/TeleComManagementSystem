package com.microstar.cablevision.security;

import java.security.MessageDigest;
import javax.xml.bind.DatatypeConverter;

public class Security {
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
			
		}
		return hashedValue;
	}

}
