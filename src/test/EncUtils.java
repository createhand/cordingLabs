package test;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Base64;

public class EncUtils {

	public static String getHashingSHA256(String source, String salt) {
		String result = "";

		try {
			byte[] a = source.getBytes();
			byte[] b = salt.getBytes();

			byte[] bytes = new byte[a.length + b.length];

			System.arraycopy(a, 0, bytes, 0, a.length);
			System.arraycopy(b, 0, bytes, a.length, b.length);

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(bytes);

			byte[] byteData = md.digest();

			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < byteData.length; ++i) {
				sb.append(Integer.toString((byteData[i] & 0xFF) + 256, 16).substring(1));
			}

			result = sb.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static class HexaString {
		public static String encode(byte[] in)	{
			return DatatypeConverter.printHexBinary(in);
		}
		public static byte[] decode(String in)	{
			return DatatypeConverter.parseHexBinary(in);
		}
	}
	
	public static class B64	{
		public static String encode(byte[] in)	{
			return new String(Base64.encodeBase64(in));
		}
		public static byte[] decode(String in)	{
			try	{
				return Base64.decodeBase64(in.getBytes("UTF-8"));
			} catch (Exception e)	{
				e.printStackTrace();
				return null;
			}
		}
	}
	
	public static class AES128	{
		final private	static String ALGORITHM = "AES";
		final private	static String OPMODE = "AES/CBC/PKCS5PADDING";
		
		public static byte[] encrypt(byte[] in, String key)
		throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException
		{
			SecretKeySpec keySpec = new SecretKeySpec(HexaString.decode(key), ALGORITHM);
			Cipher	cipher = Cipher.getInstance(OPMODE);
			cipher.init(Cipher.ENCRYPT_MODE, keySpec);
			
			return cipher.doFinal(in);
		}
		public static byte[] decrypt(byte[] in, String key)
		throws NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException, NoSuchPaddingException, InvalidAlgorithmParameterException, BadPaddingException
		{
			SecretKeySpec keySpec = new SecretKeySpec(HexaString.decode(key), ALGORITHM);
			Cipher	cipher = Cipher.getInstance(OPMODE);
			cipher.init(Cipher.DECRYPT_MODE, keySpec);
			
			return cipher.doFinal(in);
		}
	}
	
	public static class AES256 {
	    
	    final private	static String ALGORITHM = "AES";
		final private	static String OPMODE = "AES/CBC/PKCS5PADDING";
	    
	    // 암호화
	    public static String encrypt(String str, String key) throws java.io.UnsupportedEncodingException, 
	                                                    NoSuchAlgorithmException, 
	                                                    NoSuchPaddingException, 
	                                                    InvalidKeyException, 
	                                                    InvalidAlgorithmParameterException, 
	                                                    IllegalBlockSizeException, 
	                                                    BadPaddingException {
	    	String iv = key.substring(0, 16);
	        
	        byte[] keyBytes = new byte[16];
	        byte[] b = key.getBytes("UTF-8");
	        int len = b.length;
	        if (len > keyBytes.length) {
	            len = keyBytes.length;
	        }
	        System.arraycopy(b, 0, keyBytes, 0, len);
	        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
	        
	        Cipher c = Cipher.getInstance(OPMODE);
	        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes()));
	 
	        byte[] encrypted = c.doFinal(str.getBytes("UTF-8"));
	        String enStr = new String(Base64.encodeBase64(encrypted));
	 
	        return enStr;
	    }
	 
	    //복호화
	    public static String decrypt(String str, String key) throws java.io.UnsupportedEncodingException,
	                                                        NoSuchAlgorithmException,
	                                                        NoSuchPaddingException, 
	                                                        InvalidKeyException, 
	                                                        InvalidAlgorithmParameterException,
	                                                        IllegalBlockSizeException, 
	                                                        BadPaddingException {
	    	String iv = key.substring(0, 16);
	        
	        byte[] keyBytes = new byte[16];
	        byte[] b = key.getBytes("UTF-8");
	        int len = b.length;
	        if (len > keyBytes.length) {
	            len = keyBytes.length;
	        }
	        System.arraycopy(b, 0, keyBytes, 0, len);
	        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, ALGORITHM);
	        
	        Cipher c = Cipher.getInstance(OPMODE);
	        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv.getBytes("UTF-8")));
	 
	        byte[] byteStr = Base64.decodeBase64(str.getBytes());
	 
	        return new String(c.doFinal(byteStr),"UTF-8");
	    }
	 
	}

	
	public static class HMACSHA256	{
		final private static String ALGORITHM = "HmacSHA256";
		
		public static String generate(String in, String apikey)	
		throws NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException
		{
			byte[]	key = HexaString.decode(apikey);
			SecretKeySpec keySpec = new SecretKeySpec(key, ALGORITHM);
			Mac		mac = Mac.getInstance(ALGORITHM);
			mac.init(keySpec);
			
			byte[] out = mac.doFinal(in.getBytes("UTF-8"));
			return HexaString.encode(out);
		}
	}
	
}
