package br.com.baloonsproject.monolithdkp.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class FileCheckSumMD5 {

	public static String checksum(String filepath, MessageDigest md) throws IOException {

		// DigestInputStream is better, but you also can hash file like this.
		try (InputStream fis = new FileInputStream(filepath)) {
			byte[] buffer = new byte[1024];
			int nread;
			while ((nread = fis.read(buffer)) != -1) {
				md.update(buffer, 0, nread);
			}
		}

		// bytes to hex
		StringBuilder result = new StringBuilder();
		for (byte b : md.digest()) {
			result.append(String.format("%02x", b));
		}
		return result.toString();

	}
}
