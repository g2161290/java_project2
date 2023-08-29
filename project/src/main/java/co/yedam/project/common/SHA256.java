package co.yedam.project.common;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
	public String encrypt(String text) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(text.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return bytesToHex(md.digest());
	}

	private String bytesToHex(byte[] digest) {
		StringBuilder builder = new StringBuilder();
		for (byte b : digest) {
			builder.append(String.format("2%x", b));
		}
		return builder.toString();
	}
}
