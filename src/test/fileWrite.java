package test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class fileWrite {
	
	public static void main(String args[]) {
		fileWrite();
	}
	
	public static void fileWrite() {
	try {
		
		//경로
		String fileName = "c:\\ATTS_upload";
		
		//거래건수
		int cnt = 500000;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat sdfymd = new SimpleDateFormat("yyyyMMdd");
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Asia/Seoul"));
		String toDate = sdfymd.format(cal.getTime());
		
		File targetFile = new File(fileName);
		targetFile.createNewFile();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile.getPath()), "UTF-8"));
		
		String header = "H"+toDate+"                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   ";		
		String footer = "T000000013500000000000000000000000000                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ";
		out.write(header);
		for(int i=0;i<cnt;i++) {
			cal.add(Calendar.SECOND, 1);    // 1초씩 증가
	        toDate = sdf.format(cal.getTime());
	        String body = "D00000000001010000617115936    "+toDate+"택시거래                                                                                            0000001610                                                                        303200026           인천31바5605                                                                                        법인택시                                                                                            +0000000000+00000039600000023840                                                                                                    201506130015090000003960                                                                000000000020150614               ";
			out.write(body);
		}
		
		out.write(footer);
		out.close();
		
	    } catch (IOException e) {
	        System.err.println(e); // 에러가 있다면 메시지 출력
	        System.exit(1);
	    }
	}
		
}
