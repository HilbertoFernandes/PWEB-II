package br.edu.ifpb.bibliotecaweb.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordUtil {

	public static String encryptMD5(String senha) {
		MessageDigest md = null;

		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			new RuntimeException("Algoritmo de criptografia não encontrado");
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : md.digest(senha.getBytes())) {
			sb.append(toHexChars(b));
		}
		return sb.toString();
	}
	
	private static char[] toHexChars(byte b) {
		char digitos[] = new char[2];
		byte alto = (byte) ((b & 0xF0) / 16);
		byte baixo = (byte) (b & 0x0F);
		digitos[0] = Integer.toHexString((int) alto).charAt(0);
		digitos[1] = Integer.toHexString((int) baixo).charAt(0);
		return digitos;
	}
	
	 public static boolean validatePassword(final String password){ 
		  Pattern p = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[#$%&]).{6,}$"); 
		  Matcher m = p.matcher(password); 
		  return m.matches(); 
	  } 

}
