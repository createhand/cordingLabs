package cordingTest;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.apache.mina.core.buffer.BufferDataException;
import org.apache.tika.parser.txt.CharsetDetector;

public class FileEncoding {
	
	public static void main(String args[]) {
		String path = "D:\\workspace\\git\\hmf-svr-core-pt-ibs\\src\\main\\webapp\\WEB-INF\\views";
//		changeFileEncoding(path);
		fileReadAndWrite(path);
	}

	public static void fileReadAndWrite(String path) {
		String[][] patterns = {
//				{"<%@ include file=\"/include/aceiCommon.jsp\"%>","<%//@ include file=\"/include/aceiCommon.jsp\"%>"},
//				{"<%@ taglib prefix=\"u\"      uri=\"/WEB-INF/tld/utaglib.tld\" %>",""},
//				{"<%@ taglib prefix=\"tabWeb\" uri=\"/WEB-INF/tld/ACEI_WEBTag.tld\" %>",""},
//				{"<%=aceiRequest.getPageCode()%>","<%//=aceiRequest.getPageCode()%>"},
//				{"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">", ""},
//				{"<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ko\" lang=\"ko\">", "<html lang=\"ko\">"},
//				{"systemNavigation", "webSiteNavigation"},
//				{"gotoSystemPageNaviAN", "webSiteNavigation"},
//				{"<%@ include file=\"/include/help.jsp\"%>", ""},
				{"<%@ include file=\"/error/acei_error.jsp\"%>", "<%//@ include file=\"/error/acei_error.jsp\"%>"},
				{"<%@ page language=\"java\" contentType=\"text/html; charset=utf-8\" %>", "<%@ page language=\"java\" contentType=\"text/html;charset=utf-8\" %>"}
			};
		
		//경로
		File dir = new File(path);
		if(!dir.exists()) {
    		dir.mkdirs();
    	}
		
		File[] files = dir.listFiles();
		for(File file : files) {
			
			try {
			
				String absolutePath = file.getAbsolutePath();
				
				if(file.isDirectory()) {
					fileReadAndWrite(absolutePath);
				}
				
				if(file.getName().indexOf(".jsp") > -1) {
					
					// Read the content from file
					FileInputStream fis = new FileInputStream(absolutePath);
					BufferedInputStream bis = new BufferedInputStream(fis);
					Reader rd = new InputStreamReader(bis);
					BufferedReader bufferedReader = new BufferedReader(rd);
					
					CharsetDetector detector = new CharsetDetector();
					detector.setText(bis);
					
					System.out.println("absolutePath : "+absolutePath);
					
					StringBuffer fileLines = new StringBuffer();
						
					try {
					    String line = bufferedReader.readLine();
					    
					    while(line != null) {
							for(int i=0;i<patterns.length;i++) {
								String pattern = patterns[i][0];
								String replace = patterns[i][1];
//								    System.out.println("line:"+line);
								if(line.indexOf(pattern) > -1) {
						    		line = StringUtils.replace(line, pattern, replace);
					    		}
							}	
							fileLines.append(line + System.lineSeparator());
							line = bufferedReader.readLine();
					    }
					} catch (Exception e) {
					    e.printStackTrace();
					}
					
				    FileOutputStream fos = new FileOutputStream(absolutePath);
				    OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				    String encodedString = new String(fileLines.toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
				    
				    osw.write(encodedString);
				    osw.flush();
				    osw.close();
				}
			
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	public static void changeFileEncoding(String path) {
		
		String[][] patterns = {
				{"EUC-KR","utf-8"},
				{"euc-kr","utf-8"}
			};
		
		//경로
		File dir = new File(path);
		if(!dir.exists()) {
    		dir.mkdirs();
    	}
		
		File[] files = dir.listFiles();
		for(File file : files) {
			
			try {
			
				String absolutePath = file.getAbsolutePath();
				
				if(file.isDirectory()) {
					changeFileEncoding(absolutePath);
				}
				
				if(file.getName().indexOf(".jsp") > -1) {
					
					// Read the content from file
					FileInputStream fis = new FileInputStream(absolutePath);
					BufferedInputStream bis = new BufferedInputStream(fis);
					Reader rd = new InputStreamReader(bis, "euc-kr");
//					InputStreamReader isr = new InputStreamReader(fis);
					BufferedReader bufferedReader = new BufferedReader(rd);
					
					CharsetDetector detector = new CharsetDetector();
					detector.setText(bis);
					String fileEncoding = detector.detect().getName();
					
					System.out.println("absolutePath : "+absolutePath);
					System.out.println("encoding : "+fileEncoding);

					StringBuffer fileLines = new StringBuffer();
						
					try {
					    String line = bufferedReader.readLine();
					    
					    while(line != null) {
							for(int i=0;i<patterns.length;i++) {
								String pattern = patterns[i][0];
								String replace = patterns[i][1];
//							    System.out.println("line:"+line);
								if(line.indexOf(pattern) > -1) {
						    		line = StringUtils.replace(line, pattern, replace);
					    		}
							}	
							fileLines.append(line + System.lineSeparator());
							line = bufferedReader.readLine();
					    }
					} catch (Exception e) {
					    e.printStackTrace();
					}
					
					
				    if(!StringUtils.equals(fileEncoding, StandardCharsets.UTF_8.name())) {
				    	
					    FileOutputStream fos = new FileOutputStream(absolutePath);
					    OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
					    String encodedString = new String(fileLines.toString().getBytes(StandardCharsets.UTF_8), StandardCharsets.UTF_8);
					    
					    osw.write(encodedString);
					    osw.flush();
					    osw.close();
					    
					    System.out.println(file.getName()+" changed!");
				    }
				    
				}
			
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
		
	
}