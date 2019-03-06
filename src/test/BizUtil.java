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
 * ?��무�? �??�� ?�� Utility ?��?��?��
 * </p>
 * @author 2016. 08. 01., TA
 */
public class BizUtil {
	
	private static final Logger logger = LoggerFactory.getLogger(BizUtil.class);

	/**
	* 만나?�� 계산
	* @param birth : ?��?��?��?�� (?��: "19800101")
	* @return int 만나?��
	*/
	public static int calculateManAge(String birth){

		if(birth.length() < 8){ // 8?���? 보다 ?��?���? 
			return 0;
		}
	
		String today = ""; 		//?��?�� ?���?
		int manAge = 0; 		//�? ?��?��
		
		today = DateUtil.getToday("yyyyMMdd");
	
		//today yyyyMMdd
		int todayYear = Integer.parseInt( today.substring(0, 4) );
		int todayMonth = Integer.parseInt( today.substring(4, 6) );
		int todayDay = Integer.parseInt( today.substring(6, 8) );
	
		int ssnYear = Integer.parseInt( birth.substring(0, 4) );
		int ssnMonth = Integer.parseInt( birth.substring(4, 6) );
		int ssnDay = Integer.parseInt( birth.substring(6, 8) );
	
		manAge = todayYear - ssnYear;
	
		if( todayMonth < ssnMonth ){ //?��?��?��?�� "?��"?�� �??��?���? 체크
			manAge--;
		}else if( todayMonth == ssnMonth ){ //?��?��?��?�� "?��"?�� �??��?���? 체크
			if( todayDay < ssnDay ){
				manAge--; //?��?�� ?���??��?���? (만나?�� - 1)
			}
		}
	
		return manAge;
	}
	

	/**
	* ?��???�� ?��?�� 체크
	* @param moblNo
	* @return 
	*/
	public static boolean isValidMoblFormat(String moblNo) {
		
		
		if(moblNo.length() != 10 && moblNo.length() != 11) {
			logger.error("?��???��번호 ?��?�� ?���? ?��?��?��.(?��리수 ?��맞음)");
			return false;
		}
		
		if(!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "010") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "011") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "016") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "017") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "018") &&
				!StringUtils.equals(StringUtils.substring(moblNo, 0, 3), "019")) {
			logger.error("?��???��번호 ?��?�� ?���? ?��?��?��.(?��?��번호 ?��맞음)");
			return false;
		}
		
		if(moblNo.indexOf("010") == 0) {
			if(moblNo.length() == 10) {
				logger.error("?��???��번호 ?��?�� ?���? ?��?��?��.(010?��?�� ?��리수 ?��맞음)");
				return false;
			}
		}
		
		return true;
	}
	
	/**
	* ?��???�� 번호 ?��?��?���?
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
	* ?��???�� 번호 ?��맷팅
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
	* ?��?��?��번호 ?��?��?�� 체크
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
	* ?��메일 ?��?��?�� 체크
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
	* ?��메일 ?��?��?�� 체크
	* @param email
	* @return 
	*/
	public static String getCommaNum(String amt) {
		
		double dbAmt = Double.parseDouble(amt);
		DecimalFormat df = new DecimalFormat("#,###");
		
		return String.valueOf(df.format(dbAmt));
		
	}
	
	/**
	* ?��?��?�� 값을 ?��?��?�� ?��리수만큼 리턴
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
	* 비�?번호 ?��?��?�� 체크
	* @param pwd
	* @return false(?��?��)
	*/
	public static boolean pwdValidCheck(String pwd) {
		/*--------------------------------------------------------------------*/
		/* 중복?�� 3?�� ?��?��?�� 문자 ?��?�� ?��?�� ?��?��불�?
		/* ?��?��?��?�� ?��?�� ?��?���? ?��?��??�? 3?��?��?�� ?��?��?��?�� 비�?번호?�� ?��?��불�?
		/*---------------------------------------------------------------------*/
		// 중복?�� 3?�� ?��?��?�� 문자 ?��?�� ?��?�� ?��?��불�?
		int p = pwd.length();
		byte[] b = pwd.getBytes();
		for (int i = 0; i < ((p * 2) / 3); i++) {
			int b1 = b[i + 1];
			int b2 = b[i + 2];

			if ((b[i] == b1) && (b[i] == b2)) {
				logger.debug("중복 3?���? ?��?�� 존재?��.");
				return true;
			} else {
				continue;
			}
		}
		
		// ?��?��?��?�� ?��?�� ?��?���? ?��?��??�? 3?��?��?�� ?��?��?��?�� 비�?번호?�� ?��?��불�?
		byte[] g = pwd.getBytes();
		int pp = pwd.length();

		// ?��?��?�� 3개의 문자 ?��?��불�? (?��름차?��)
		for (int i = 0; i < ((pp * 2) / 3); i++) {
			int b1 = g[i] + 1;
			int b2 = g[i + 1];
			int b3 = g[i + 1] + 1;
			int b4 = g[i + 2];

			if ((b1 == b2) && (b3 == b4)) {
				logger.debug("?��?�� 3?���? ?��름차?�� ?��?�� 존재?��.");
				return true;
			} else {
				continue;
			}
		}
		// ?��?��?�� 3개의 문자 ?��?��불�? (?��림차?��)
		for (int i = 0; i < ((pp * 2) / 3); i++) {
			int b1 = g[i + 1] + 1;
			int b2 = g[i + 2] + 2;

			if ((g[i] == b1) && (g[i] == b2)) {
				logger.debug("?��?�� 3?���? ?��림차?�� ?��?�� 존재?��.");
				return true;
			} else {
				continue;
			}
		}
		
		return false;
	}
	
	/**
	* 비�?번호 ?��?��?�� 체크 - 비교???�� 문자?��?�� ?��?��?��?��?���? 체크?��?��.
	* @param pwd
	* @param compVal
	* @return false(?��?��)
	*/
	public static boolean pwdInfoCheck(String pwd, String compVal) {
		// 비�?번호�? ?��?�� 문자?��?�� ?��?��?���? ?��?�� 경우 
		if(compVal.indexOf(pwd) > -1) {
			return true;
		} 
		
		return false;
	}
	
	/**
	* ?��문숫?�� ?��?��?��?��
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
	* byte array �?�?
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
