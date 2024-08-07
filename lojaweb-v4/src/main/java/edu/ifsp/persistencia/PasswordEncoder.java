package edu.ifsp.persistencia;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class PasswordEncoder {
	public static String encode(String plainPassword, String salt) {
		if (salt == null) {
		   getSalt();
		}
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException(e);
		}
		
		md.update(plainPassword.getBytes());
		md.update(salt.getBytes());
		byte[] digest = md.digest();
		
		BigInteger bi = new BigInteger(1, digest);
		String hash = bi.toString(16);
		
		return hash;
	}
	
	public static String getSalt() {
     return UUID.randomUUID().toString();
   }
}
