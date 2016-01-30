package org.domain.sisescolar.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AutenticacaoUtil {
	
	public static String criptografarSenha(String senha) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			
			byte[] b = md5.digest();
			
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		System.out.println(criptografarSenha("diogo"));
	}
}
