package test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.thoughtworks.xstream.XStream;

import cordingTest.EncryptUtils.HMACSHA256;
import net.sf.json.JSONObject;

public class cordingLabs {
	
	public static void main(String args[]) throws Exception {
		
		System.out.println(System.currentTimeMillis());
		
	    double doubleMath = Math.random() * 10.0D + 1.0D;
	    int intRandom = (int)(doubleMath * 1000.0D);
	    
	    System.out.println(Integer.toString(intRandom).substring(0, 4));
	    System.out.println("http://12cm-image.s3.amazonaws.com/esp2/template/fsc/default/index.html".length());
	    
		
		/********* 암복호화 *********/
		/*
		StringBuffer sb = new StringBuffer("");
		sb.append("{ \"ERR_CD\" : \"ER92\",");
		sb.append("\"id\" : \"ER91\",");
		sb.append("\"name\" : \"kdkdf\",");
		sb.append("\"email\" : \"asdfasf\",");
		sb.append("\"cell\" : 99203931, ");
		sb.append("\"hobbit\" : [ ");
		sb.append("	{ ");
		sb.append("		\"name\" : \"aaaa\","); 
		sb.append("		\"time\" : 345345,");
		sb.append("		\"ord\" : [");
		sb.append("			{\"ord_nm\" : \"name1\", \"ord_no\" : 11},");
		sb.append("			{\"ord_nm\" : \"name2\", \"ord_no\" : 22},");
		sb.append("			{\"ord_nm\" : \"name3\", \"ord_no\" : 33}");
		sb.append("		]");
		sb.append("	},");
		sb.append("	{	\"time\" : 345345,"); 
		sb.append("		\"ord\" : [");
		sb.append("			{\"ord_nm\" : \"name4\", \"ord_no\" : 44},");
		sb.append("			{\"ord_nm\" : \"name5\", \"ord_no\" : 55},");
		sb.append("			{\"ord_nm\" : \"name6\", \"ord_no\" : 66}");
		sb.append("		]");
		sb.append("	}");
		sb.append("] }");
		String val = sb.toString();
		String key = RandomStringUtils.randomAlphanumeric(64);
//		String key = "hzn0CSVeS0ECU5ixITCtl75arRckJzVA";
		System.out.println("key : "+key);
		System.out.println("plan : "+val);
		String encVal = EncUtils.AES256.encrypt(val, key);
		System.out.println("encVal : "+encVal+"\nlength:"+encVal.getBytes().length);
		String decVal = EncUtils.AES256.decrypt(encVal, key);
		System.out.println("decVal : "+decVal);
		*/
	    String salt = "9a2a2519a27a4066a5720510fe3e4cb5c4";
	    String Timestamp = String.valueOf(System.currentTimeMillis());
	    String hmacValue = HMACSHA256.generate(Timestamp, salt);
	    System.out.println("Timestamp : "+Timestamp);
	    System.out.println("hmacValue : "+hmacValue);
		String authorization = new String(Base64.getEncoder().encode(("A002:"+hmacValue).getBytes()), "UTF-8");
		System.out.println("authorization : "+authorization);
		byte[] de_authorization = Base64.getDecoder().decode(authorization);
		System.out.println("de authorization : "+new String(de_authorization, "UTF-8"));
		// */		
		
//		boolean next = true;
//		int drwNo = 1;
//		List<JSONObject> list = new ArrayList<JSONObject>();
//		
//		while(next) {
//			if(drwNo > 10) next = false;
//			JSONObject obj = getLottoNumbers(drwNo);
//			list.add(obj);
//			drwNo++;
//			System.out.println(obj.toString());
//		}
		
//		System.out.println(list.size());
		
		
//		String l = String.valueOf(RandomUtils.nextLong());
//		System.out.println(l.length());
//		
//		System.out.println(RandomUtils.nextLong());
//		System.out.println(RandomUtils.nextInt(999999999));
//		System.out.println(_getFixedSizeRandomStringNumber(7,'_'));
//		System.out.println(_getFixedSizeRandomStringNumber(5,'_'));
		
		String str = getUniqueServiceId();
		System.out.println("str:["+str+"]"+"["+str.length()+"]");
		System.out.println("nano time:"+System.nanoTime());
		String time = DateFormatUtils.format(System.nanoTime(), "yyyyMMddHHmmssSSS");
		System.out.println("nano time:"+time);
		
		String guid = "20180711core-mnh9916214339411803";
		System.out.println("increaseGUID:"+increaseGUID(guid));
		System.out.println("GUID:"+guid.length());
		
		
//		    // Create raw data.
//		    Map<Integer, String> data = new HashMap<Integer, String>();
//		    data.put(1, "hello");
//		    data.put(2, "world");
//		    System.out.println(data.toString());
//
//		    // Convert Map to byte array
//		    ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
//		    ObjectOutputStream out = new ObjectOutputStream(byteOut);
//		    out.writeObject(data);
//
//		    // Parse byte array to Map
//		    ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
//		    ObjectInputStream in = new ObjectInputStream(byteIn);
//		    byte[] reads = readByteArray(byteIn);
//		    System.out.println("read bytes:"+new String(reads));
//		    Map<Integer, String> data2 = (Map<Integer, String>) in.readObject();
//		    System.out.println(data2.toString());
//		    
//		    long currMili = System.currentTimeMillis();
		    
//		    for(int i=0;i<10;i++) {
//		    	Thread.sleep(2000);
//		    }
		    
//		    long aftMili = System.currentTimeMillis();
//		    
//		    System.out.println("currMili:"+currMili);
//		    System.out.println("aftMili:"+aftMili);
//		    System.out.println("diff:"+(aftMili-currMili));
		    
		    
		    
			// TODO Auto-generated method stub
			StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
			pbeEnc.setAlgorithm("PBEWithMD5AndDES");
			pbeEnc.setPassword("jasyptKey"); //2번 설정의 암호화 키를 입력
			
			String enc = pbeEnc.encrypt("jdbc:mysql://104.155.229.140:3306/mnh?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=false&useTimezone=true&serverTimezone=UTC"); //암호화 할 내용
			System.out.println("url = " + enc); //암호화 한 내용을 출력
			
			//테스트용 복호화
			String des = pbeEnc.decrypt(enc);
			System.out.println("des = " + des);
			
			enc = pbeEnc.encrypt("gln"); //암호화 할 내용
			System.out.println("user = " + enc); //암호화 한 내용을 출력
			
			//테스트용 복호화
			des = pbeEnc.decrypt(enc);
			System.out.println("des = " + des);
			
			enc = pbeEnc.encrypt("gln1234"); //암호화 할 내용
			System.out.println("pass = " + enc); //암호화 한 내용을 출력
			
			//테스트용 복호화
			des = pbeEnc.decrypt(enc);
			System.out.println("des = " + des);
			
		    
	}
	
	public static String increaseGUID(String guid) {
		String seq = StringUtils.substring(guid, 30);
		seq = StringUtils.leftPad(String.valueOf(Integer.parseInt(seq) + 1), 2, '0');
		return StringUtils.substring(guid, 0, 30) + seq;
	}

	private static String getUniqueServiceId() {
		StringBuffer serviceId = new StringBuffer();

		AtomicInteger seq = new AtomicInteger();
		int nextVal = seq.incrementAndGet();
		System.out.println(nextVal);
		String formatTime = DateUtil.getCurrentDate();
		String hostName = null;
		serviceId.append(formatTime);
		try {
			hostName = InetAddress.getLocalHost().getHostName();
		}
		catch (UnknownHostException e) {
			hostName = "NONEHOST";
			e.printStackTrace();
		} finally {
			hostName = hostName.length() > 8 ? hostName.substring(0, 8) : hostName; 
		}
		
		serviceId.append(hostName);
		serviceId.append(_getFixedSizeRandomStringNumber(7, '0'));
		serviceId.append(_getFixedSizeRandomStringNumber(7, '0'));
		serviceId.append("00");

		return serviceId.toString();
	}

	private static String _getFixedSizeRandomStringNumber(int size,
			char paddingChar) {
		Random r = new Random();
		r.setSeed(r.nextLong());
		long randomNum = Math.abs(r.nextLong()%1000000000);
		
		return _lPadding("" + randomNum, size, paddingChar);
	}

	private static String _lPadding(String s, int size, char paddingChar) {
		int gap = size - s.length();
		if (gap == 0) {
			return s;
		}
		if (gap < 0) {
			return s.substring(0, size);
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < gap; i++) {
			sb.append(paddingChar);
		}

		sb.append(s);
		return sb.toString();
	}
	
	public static JSONObject getLottoNumbers(int drwNo) throws IOException {
		
		String resStr = "";
		URL targetUrl = new URL("http", "www.nlotto.co.kr", 80, "/common.do?method=getLottoNumber&drwNo="+drwNo);
		
		HttpURLConnection conn = null;
		conn = (HttpURLConnection)targetUrl.openConnection();
		
		System.out.println(targetUrl.toString());
		
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setReadTimeout(5000);
		conn.setRequestProperty("Content-Type", "application/json");
		
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write("");
		wr.flush();
		
	    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
	    String line = null;  
	    while ((line = br.readLine()) != null) {
	    	resStr += line;  
	    }
	    br.close();
	    
		return JSONObject.fromObject(resStr);
	}
	
	public static String getLunch() {
		
		String menu = null;
		
		String[] menuList = {"�깮�꽑?��?�씠","�닚��?���?","吏쒖?��/吏щ퐬/蹂띠?��諛�","?��곸뼱?���?/留뚮몢援�?","?�����李?���?","�씪硫�/洹쒕�?","�꽕�쟻�깢","移�??��/�슦�룞/�룉源뚯?��","�굾吏�/�삤吏뺤뼱蹂?��?��","源�移섏컡媛�?/�젣�쑁","�닚�몢?���?","�닔�젣踰꾧�?","�냼?�좉린援�諛�?","	��?����?��","	?��꾩떇吏�"};
		
		menu = menuList[RandomUtils.nextInt(menuList.length)];
		
		System.out.println(menu);
		
		return menu;
	}
	
	public static String parseQuery(TAData map) throws UnsupportedEncodingException {
    	String query = "";
    	for(String key : map.keySet()) {
    		query += key+"="+map.get(key)+"&";
    	}
    	query = query.substring(0, query.length()-1);
    	
    	return query;
    }
	
	public static byte[] mapToByte(TAData map) throws Exception {
		byte[] rtn = null;
		
		//read element from cache
//		ElementInfo[] element = new ElementInfo[elementCnt];
		
		//�쟾?��명빆紐⑹?��
		int elementCnt = 5;
		
		MapMessageTransform msgTrans = new MapMessageTransform();
		ElementInfo[] element = new ElementInfo[elementCnt];
		ElementInfo info = null;
		
		//�쟾?��명빆紐⑸?? �꽭�똿
		info = new ElementInfo("USER_ID", 10, 1);
		element[0] = info;
		
		info = new ElementInfo("TRX_AMT", 10, 3);
		element[1] = info;
		
		info = new ElementInfo("TRX_DT", 10, 1);
		element[2] = info;
		
		ElementInfo[] arryEl = new ElementInfo[2];
		arryEl[0] = new ElementInfo("NO", 10, 3);
		arryEl[1] = new ElementInfo("NAME", 5, 1);
		info = new ElementInfo("TRX_LIST", 2, 7, arryEl, 2);
		element[3] = info;
		
		arryEl = new ElementInfo[2];
		arryEl[0] = new ElementInfo("ET_NO", 3, 3);
		arryEl[1] = new ElementInfo("ET_NM", 5, 1);
		info = new ElementInfo("ETC_LIST", 1, 7, arryEl, 2);
		element[4] = info;
		
		
		rtn = msgTrans.toByteArray(element, map);
		return rtn;
	}
	
	public static TAData byteToMap(byte[] req) throws Exception {
		
//		String msgTxt = "createhand0000011500"+DateUtil.getToday()+"0000000001MYNAM0000000098KIMNA999PARKS";
		String msgDef = "USER_ID@10@1;TRX_AMT@10@3;TRX_DT@14@1;TRX_LIST@2@7&NO#10#3&NAME#5#1;ETC_LIST@1@7&ET_NO#3#3&ET_NM#5#1";
		//String msgDef = "USER_ID@10@1;TRX_AMT@10@3;TRX_DT@14@1";
		
		String[] params = msgDef.split(";");
		
		ElementInfo[] elements = new ElementInfo[params.length];
		setElement(elements, params, "@");
		
		MapMessageTransform msgTrans = new MapMessageTransform();
		TAData rtn = new TAData(msgTrans.parse(req, elements));
		
		List<TAData> list = (List<TAData>)rtn.get("TRX_LIST");
		
		return rtn;
	}
	
	public static TAData jsonToMap(String req) throws Exception {
		TAData rtn = new TAData();
		
		return rtn;
	}
	
	public static String mapToJson(String req) throws Exception {
		String rtn = null;
		
		return rtn;
	}
	
	public static TAData xmlToMap(String req) throws Exception {
		TAData rtn = new TAData();
		
		return rtn;
	}
	
	public static String mapToXml(String req) throws Exception {
		String rtn = null;
		
		return rtn;
	}
	
	/**
	* Convert object to byte array
	* @param object
	* @return
	*/
	public static byte[] fromJavaToByteArray(Serializable object) {
		return SerializationUtils.serialize(object);
	}

	/**
	* Convert byte array to object
	* @param bytes
	* @return
	*/
	public static Object fromByteArrayToJava(byte[] bytes) {
		return SerializationUtils.deserialize(bytes);
	}
	
	/**
	* Convert object to JSON String 
	* @param object
	* @return
	* @throws JsonGenerationException
	* @throws JsonMappingException
	* @throws IOException
	*/
	public static String fromJavaToJson(Serializable object)
		throws JsonGenerationException, JsonMappingException, IOException {
	    ObjectMapper jsonMapper = new ObjectMapper();
	    return jsonMapper.writeValueAsString(object);
	}

	/**
	* Convert a JSON string to an object
	* @param json
	* @return
	* @throws JsonParseException
	* @throws JsonMappingException
	* @throws IOException
	*/
	public static Object fromJsonToJava(String json, Class type) throws JsonParseException,
			JsonMappingException, IOException {
	     ObjectMapper jsonMapper = new ObjectMapper();
	     return jsonMapper.readValue(json, type);
	}
	
	/**
	* Convert a java object to XML
	* @param object
	* @return
	*/
	public static String fromJavaToXML(Serializable object) {
		XStream xs = new XStream();
		return xs.toXML(object);
	}
		
	/**
	* Convert from XML to object
	* @param xml
	* @return
	*/
	public static Object fromXMLToJava(String xml){
		XStream xs = new XStream();
		return xs.fromXML(xml);
	}
	
	public static void setElement(ElementInfo[] elements, String[] params, String delimiter) {
		
		ElementInfo a = null;
		
		for(int i=0;i<params.length;i++) {
			
			String paramsDetail[] = params[i].split(delimiter);
			
			if(paramsDetail[2].startsWith(String.valueOf(ElementInfo.TYPE_ARRAY))) {
				
				paramsDetail[2] = paramsDetail[2].substring(2);
				String[] arrays = paramsDetail[2].split("&");
				ElementInfo[] eleArry = new ElementInfo[arrays.length]; 
				for(int j=0;j<arrays.length;j++) {
					a = getElement(arrays[j], "#");
					eleArry[j] = a;
				}				
				a = new ElementInfo(paramsDetail[0], Integer.parseInt(paramsDetail[1]), 7, eleArry, 2);
				
			} else {
				
				a = new ElementInfo(paramsDetail[0], Integer.parseInt(paramsDetail[1]), Integer.parseInt(paramsDetail[2]));
			}			
			elements[i] = a;
		}
	}
	
	public static ElementInfo getElement(String params, String delimiter) {
		String paramsDetail[] = params.split(delimiter);
		return new ElementInfo(paramsDetail[0], Integer.parseInt(paramsDetail[1]), Integer.parseInt(paramsDetail[2]));	
	}
	
	public static String getBizDate(String paramDate, int nextCnt) {
		List<TAData> bizHldayList = new ArrayList<TAData>();
		TAData temp = new TAData();
		temp.set("DT", "20160528");
		bizHldayList.add(0, temp);
		temp = new TAData();
		temp.set("DT", "20160529");
		bizHldayList.add(1, temp);
		temp = new TAData();
		temp.set("DT", "20160604");
		bizHldayList.add(2, temp);
		temp = new TAData();
		temp.set("DT", "20160605");
		bizHldayList.add(3, temp);
		temp = new TAData();
		temp.set("DT", "20160606");
		bizHldayList.add(4, temp);
		temp = new TAData();
		temp.set("DT", "20160611");
		bizHldayList.add(5, temp);
		temp = new TAData();
		temp.set("DT", "20160612");
		bizHldayList.add(6, temp);
		temp = new TAData();
		temp.set("DT", "20160618");
		bizHldayList.add(7, temp);
		temp = new TAData();
		temp.set("DT", "20160619");
		bizHldayList.add(8, temp);
		temp = new TAData();
		temp.set("DT", "20160625");
		bizHldayList.add(9, temp);
		temp = new TAData();
		temp.set("DT", "20160626");
		bizHldayList.add(10, temp);
		temp = new TAData();
		temp.set("DT", "20160402");
		temp = new TAData();
		bizHldayList.add(11, temp);
		temp.set("DT", "20160403");
		temp = new TAData();
		bizHldayList.add(12, temp);
		temp.set("DT", "20160409");
		temp = new TAData();
		bizHldayList.add(13, temp);
		temp.set("DT", "20160410");
		temp = new TAData();
		bizHldayList.add(14, temp);
		temp.set("DT", "20160416");
		temp = new TAData();
		bizHldayList.add(15, temp);
		temp.set("DT", "20160417");
		temp = new TAData();
		bizHldayList.add(16, temp);
		temp.set("DT", "20160423");
		temp = new TAData();
		bizHldayList.add(17, temp);
		temp.set("DT", "20160402");
		temp = new TAData();
		bizHldayList.add(18, temp);
		temp.set("DT", "20160430");
		temp = new TAData();
		bizHldayList.add(19, temp);
		temp.set("DT", "20160501");
		temp = new TAData();
		bizHldayList.add(20, temp);
		
		
		boolean thisday = false;
		TAData bizHldayInfo = null;
		int loopCnt = 0;
		int loopVal = 1;
				
		if(nextCnt == 0) {
			while(true) {
				//�떦�씪�씠 ?��꾩쁺�뾽�씪�씤吏� 泥댄�?
				TAData queryParam = new TAData();
				queryParam.set("DT", paramDate);
				thisday = bizHldayList.contains(queryParam);
				
				if(!thisday) {
					return paramDate;
				}
				paramDate = DateUtil.addDay(paramDate, 1);
			}
		}
		
		if(nextCnt < 0) {
			loopVal = loopVal * -1;
		}
		
		while(Math.abs(nextCnt) > loopCnt) {
			
			paramDate = DateUtil.addDay(paramDate, loopVal);
			
			//�떦�씪�씠 ?��꾩쁺�뾽�씪�씤吏� 泥댄�?
			TAData queryParam = new TAData();
			queryParam.set("DT", paramDate);
			thisday = bizHldayList.contains(queryParam);
			
			if(!thisday) {
				loopCnt++;
			}
			if(nextCnt == loopCnt) return paramDate;
		}
		return paramDate;
	}
	
	
	public static byte[] readByteArray(InputStream inputStream) {
		BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			boolean isFirst = true;
			int telgmLength = 0;
			int readCnt = 0;
			int totalCnt = 0;

			byte[] readBuffer = new byte['?'];
			while ((readCnt = bufferedInputStream.read(readBuffer)) != -1) {
				byteArrayOutputStream.write(readBuffer, 0, readCnt);

//				String data = new String(byteArrayOutputStream.toByteArray());
//
//				totalCnt += readCnt;
//				if ((isFirst) && (readCnt >= 13)) {
//					telgmLength = Integer.parseInt(data.substring(4, 13));
//					isFirst = false;
//				}
//				if (totalCnt == telgmLength) {
//					byte[] checkZZ = byteArrayOutputStream.toByteArray();
//					if ((char) checkZZ[(checkZZ.length - 1)] == 'Z') {
//						break;
//					}
//				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return byteArrayOutputStream.toByteArray();
	}
}
