/**
 * Copyright (c) 2016 KBCARD, Inc.
 * All right reserved..
 */
package test;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * ?λ¬΄μ? κ΄?? ¨ ? Utility ?΄??€
 * </p>
 * @author 2016. 08. 01., TA
 */
public class BizUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(BizUtil.class);

	/**
	* λ§λ?΄ κ³μ°
	* @param birth : ????Ό (?: "19800101")
	* @return int λ§λ?΄
	*/
	public static int calculateManAge(String birth){

		if(birth.length() < 8){ // 8?λ¦? λ³΄λ€ ??Όλ©? 
			return 0;
		}
	
		String today = ""; 		//?€? ? μ§?
		int manAge = 0; 		//λ§? ??΄
		
		today = DateUtil.getToday("yyyyMMdd");
	
		//today yyyyMMdd
		int todayYear = Integer.parseInt( today.substring(0, 4) );
		int todayMonth = Integer.parseInt( today.substring(4, 6) );
		int todayDay = Integer.parseInt( today.substring(6, 8) );
	
		int ssnYear = Integer.parseInt( birth.substring(0, 4) );
		int ssnMonth = Integer.parseInt( birth.substring(4, 6) );
		int ssnDay = Integer.parseInt( birth.substring(6, 8) );
	
		manAge = todayYear - ssnYear;
	
		if( todayMonth < ssnMonth ){ //????Ό "?"?΄ μ§??¬?μ§? μ²΄ν¬
			manAge--;
		}else if( todayMonth == ssnMonth ){ //????Ό "?Ό"?΄ μ§??¬?μ§? μ²΄ν¬
			if( todayDay < ssnDay ){
				manAge--; //??Ό ?μ§??¬?Όλ©? (λ§λ?΄ - 1)
			}
		}
	
		return manAge;
	}
	

	/**
	* ?΄???° ?? μ²΄ν¬
	* @param moblNo
	* @return 
	*/
	public static boolean isValidMoblFormat(String moblNo) {
		
		
		if(moblNo.length() != 10 && moblNo.length() != 11) {
			logger.error("?΄???°λ²νΈ ?? ?€λ₯? ???€.(?λ¦¬μ ?λ§μ)");
			return false;
		}
		
		if(!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "010") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "011") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "016") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "017") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "018") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "019")) {
			logger.error("?΄???°λ²νΈ ?? ?€λ₯? ???€.(?΅? λ²νΈ ?λ§μ)");
			return false;
		}
		
		if(moblNo.indexOf("010") == 0) {
			if(moblNo.length() == 10) {
				logger.error("?΄???°λ²νΈ ?? ?€λ₯? ???€.(010?Έ?° ?λ¦¬μ ?λ§μ)");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	* ?΄???° λ²νΈ ??Ό?΄κΈ?
	* @param moblNo
	* @return 
	*/
	public static String[] sliceMoblNo(String moblNo) {
		String sMoblNo[] = new String[3];
		if(moblNo.length() == 10) {
			sMoblNo[0] = StringUtils.substring(moblNo, 0, 3);
			sMoblNo[1] = StringUtils.substring(moblNo, 3, 6);
			sMoblNo[2] = StringUtils.substring(moblNo, 6, 10);
		}
		
		if(moblNo.length() == 11) {
			sMoblNo[0] = StringUtils.substring(moblNo, 0, 3);
			sMoblNo[1] = StringUtils.substring(moblNo, 3, 7);
			sMoblNo[2] = StringUtils.substring(moblNo, 7, 11);
		}
		
		return sMoblNo;
	}
	
	/**
	* ?΄???° λ²νΈ ?¬λ§·ν
	* @param moblNo
	* @return 
	*/
	public static String formattingMoblNo(String moblNo) {		
		String sMoblNo[] = sliceMoblNo(moblNo);		
		if(sMoblNo.length !=3) return moblNo;		
		String fmtMoblNo = sMoblNo[0]+"-"+sMoblNo[1]+"-"+sMoblNo[2];		
		return fmtMoblNo;
	}
	
	/**
	* ?¬??λ²νΈ ? ?¨?± μ²΄ν¬
	* @param bizNo
	* @return 
	*/
	public static boolean isValidBizNoFormat(String bizNo) {
		
		if(StringUtils.isEmpty(bizNo)) return false;
		
		bizNo = bizNo.trim();
		if(bizNo.length() != 10) {
			return false;
		}
		
		int suma = 0;
		int sumb = 0;
		int sumc = 0;
		int sumd = 0;
		int sume = 0;
		int sumf = 0;
		int osub= 0;
		int s = 0;
		int lastval = 0;
		
		for(int i = 0; i < 8; i++) {
			if(i%3 == 1) s = 3;
			else if(i%3 == 2) s = 7;
			else if(i%3 == 0) s = 1;
			
			suma = suma + (Integer.parseInt(StringUtils.substring(bizNo, i, i+1))*s);
			sumf = sumf + Integer.parseInt(StringUtils.substring(bizNo, i, i+1));
		}
		if(sumf == 0) return false;
		
		osub = Integer.parseInt(StringUtils.substring(bizNo, 9));
		sumb = (Integer.parseInt(StringUtils.substring(bizNo, 8, 9))*5) % 10;
		sumc = (int)(Integer.parseInt(StringUtils.substring(bizNo, 8, 9))*5) / 10;
		lastval = (10 - ((suma + sumb + sumc) % 10)) % 10;
		
		if(osub != lastval) return false;
		
		return true;
	
	}

	
	/**
	* ?΄λ©μΌ ? ?¨?± μ²΄ν¬
	* @param email
	* @return 
	*/
	public static boolean isValidEmailFormat(String email) {
		
		if(email.length() == 0) {
	        return false;
	    }
	 
	    String pttn = "^\\D.+@.+\\.[a-z]+";
	    Pattern p = Pattern.compile(pttn);
	    Matcher m = p.matcher(email);
	 
	    if(m.matches()) {
	        return true;
	    }
	 
	    return false;
	}
	
	/**
	* ?΄λ©μΌ ? ?¨?± μ²΄ν¬
	* @param email
	* @return 
	*/
	public static String getCommaNum(String amt) {
		
		double dbAmt = Double.parseDouble(amt);
		DecimalFormat df = new DecimalFormat("#,###");
		
		return String.valueOf(df.format(dbAmt));
		
	}
	
	/**
	* ?? ₯? κ°μ ?? ₯? ?λ¦¬μλ§νΌ λ¦¬ν΄
	* @param val, cnt
	* @return 
	*/
	public static String getSettingVal(String val, int cnt) {		
		return StringUtils.leftPad("", cnt, val);
	}

	public static String getUniqueNo(int digit) {
		String s = "";
		Random r = new Random();
		r.setSeed(new Date().getTime());
		
		for (int i = 1; i <= digit; i++) {
		    s = s + Math.abs(r.nextInt()%10);
		}
		return s;
	}
	
	/**
	* λΉλ?λ²νΈ ? ?¨?± μ²΄ν¬
	* @param pwd
	* @return false(? ?)
	*/
	public static boolean pwdValidCheck(String pwd) {
		/*--------------------------------------------------------------------*/
		/* μ€λ³΅? 3? ?΄?? λ¬Έμ ?? ?«? ?¬?©λΆκ?
		/* ?Ό? ¨?«? ?? ??λ²? ????λ‘? 3??΄? ?¬?©?? λΉλ?λ²νΈ? ?¬?©λΆκ?
		/*---------------------------------------------------------------------*/
		// μ€λ³΅? 3? ?΄?? λ¬Έμ ?? ?«? ?¬?©λΆκ?
		int p = pwd.length();
		byte[] b = pwd.getBytes();
		for (int i = 0; i < ((p * 2) / 3); i++) {
			int b1 = b[i + 1];
			int b2 = b[i + 2];

			if ((b[i] == b1) && (b[i] == b2)) {
				logger.debug("μ€λ³΅ 3?λ¦? ?«? μ‘΄μ¬?¨.");
				return true;
			} else {
				continue;
			}
		}
		
		// ?Ό? ¨?«? ?? ??λ²? ????λ‘? 3??΄? ?¬?©?? λΉλ?λ²νΈ? ?¬?©λΆκ?
		byte[] g = pwd.getBytes();
		int pp = pwd.length();

		// ?°?? 3κ°μ λ¬Έμ ?¬?©λΆκ? (?€λ¦μ°¨?)
		for (int i = 0; i < ((pp * 2) / 3); i++) {
			int b1 = g[i] + 1;
			int b2 = g[i + 1];
			int b3 = g[i + 1] + 1;
			int b4 = g[i + 2];

			if ((b1 == b2) && (b3 == b4)) {
				logger.debug("?°? 3?λ¦? ?€λ¦μ°¨? ?«? μ‘΄μ¬?¨.");
				return true;
			} else {
				continue;
			}
		}
		// ?°?? 3κ°μ λ¬Έμ ?¬?©λΆκ? (?΄λ¦Όμ°¨?)
		for (int i = 0; i < ((pp * 2) / 3); i++) {
			int b1 = g[i + 1] + 1;
			int b2 = g[i + 2] + 2;

			if ((g[i] == b1) && (g[i] == b2)) {
				logger.debug("?°? 3?λ¦? ?΄λ¦Όμ°¨? ?«? μ‘΄μ¬?¨.");
				return true;
			} else {
				continue;
			}
		}
		
		return false;
	}
	
	/**
	* λΉλ?λ²νΈ ? ?¨?± μ²΄ν¬ - λΉκ΅??? λ¬Έμ?΄?΄ ?¬?¨???μ§? μ²΄ν¬??€.
	* @param pwd
	* @param compVal
	* @return false(? ?)
	*/
	public static boolean pwdInfoCheck(String pwd, String compVal) {
		// λΉλ?λ²νΈκ°? ?Ή?  λ¬Έμ?΄? ?¬?¨?κ³? ?? κ²½μ° 
		if(compVal.indexOf(pwd) > -1) {
			return true;
		} 
		
		return false;
	}
	
	/**
	* ?λ¬Έμ«? ??€??±
	* @param length
	* @return String
	*/
	public static String getUniqueString(int length) {
		
		String SALTCHARS = "ABC1D2E3F4G5H6I7J8K9L0MNOPQRSTUVWXYZ";		
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		rnd.setSeed(new Date().getTime());
		
		while(salt.length() < length) {
			int index = (int)(rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		
		String saltStr = salt.toString();
		
		return saltStr;
	}
	
	/**
	* byte array λ³?κ²?
	* @param source
	* @param replace
	* @param startPoint
	* @return replaced
	*/	
	public static byte[] replaceByteArray(byte[] source, byte[] replace, int startPoint) {
		
		int endPoint = startPoint + replace.length;
	
		byte[] replacedBytes = new byte[source.length - (endPoint - startPoint) + replace.length];
		
		System.arraycopy(source, 0, replacedBytes, 0, startPoint);
		System.arraycopy(replace, 0, replacedBytes, startPoint, replace.length);
		System.arraycopy(source, endPoint, replacedBytes, startPoint + replace.length, source.length - endPoint);
		
		return replacedBytes;
	}
}
