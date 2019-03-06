package cordingTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.thoughtworks.xstream.XStream;

import cordingTest.EncryptUtils.HMACSHA256;
import net.sf.json.JSONObject;

public class cordingLabs {
	
	public String getSequence(String str) {
		AtomicInteger counter = new AtomicInteger();
		return str+counter.updateAndGet(n -> (n >= 60) ? 1 : n + 1);
	}
	
	public static void main(String args[]) throws Exception {
//		long seqNo = 1;
//		long seqLen = 2;
//		double max = Math.pow(10d, seqLen);
//		
//		while(seqNo < 100000) {
//			if(seqNo >= max) {
//				double a = seqNo%max;
//				System.out.println("bigger than max:"+((long)a));
//			}
//			System.out.println(seqNo);
//			seqNo++;
//			
//		}
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("aaa", "111");
		info.put("bbb", "222");
		TAData data = (TAData)info;
		System.out.println(data);
		
		System.out.println(System.currentTimeMillis()/1000);
		System.out.println(System.currentTimeMillis());
		
		setHttpheader("KOEXKR", "51637266396143464B707758724C7A774843336248474970634E644B624F3633");
		
		ReentrantLock lock = new ReentrantLock();
		
		String a = "eyJhbGciOiJSUzUxMiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiYWRtaW4iOnRydWUsImlhdCI6MTUxNjIzOTAyMn0.MejLezWY6hjGgbIXkq6Qbvx_-q5vWaTR6qPiNHphvla-XaZD3up1DN6Ib5AEOVtuB3fC9l-0L36noK4qQA79lhpSK3gozXO6XPIcCp4C8MU_ACzGtYe7IwGnnK3Emr6IHQE0bpGinHX1Ak1pAuwJNawaQ6Nvmz2ozZPsyxmiwoo";
		System.out.println("length:"+a.getBytes().length);
		
//		long prev = System.currentTimeMillis();
//		boolean isEndTime = false;
//		while(!isEndTime) {
//			long now = System.currentTimeMillis();
//			long diff = now - prev;
//			if(diff > 10000) {
//				isEndTime = true;
//			}
//			System.out.println("diff:"+(diff/1000)+"\nnow:"+new Date(now));
//		}
		
		String uri = "/api/v1/members/login/insertLocalGlnPreLogin";
		System.out.println(uri.replace("v1", "v1"));
		
		
		System.out.println("getAsInt:"+new SecureRandom().nextInt(99));
		boolean catchOne = false;
		int i = 0;
		while(catchOne) {
			i++;
			long num = new SecureRandom().nextLong();
			System.out.println("num["+i+"]:"+num);
			if(num == 1) catchOne = false;
		}
		
		
		DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS", Locale.CANADA);

        // Get date and time information in milliseconds

        // Create a calendar object that will convert the date and time value
        // in milliseconds to date. We use the setTimeInMillis() method of the
        // Calendar object.
		System.out.println(System.currentTimeMillis());
		System.out.println(LocalDateTime.now(ZoneId.of("Europe/London")));
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/London"));
//        calendar.setTimeInMillis(now);
        long now = calendar.getTimeInMillis();
        System.out.println(now + " = " + formatter.format(calendar.getTimeInMillis()));
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        System.out.println("GMT:"+cal.getTimeInMillis());
        System.out.println("NOW:"+System.currentTimeMillis());
        /*
        reg time:1537342569000
        cur time:1537342582740
        cur time:1537342805374
        now time:1537342600741
        times:13740
        */
        Calendar calendar2 = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//        calendar2.setTimeInMillis(now);
        now = calendar2.getTimeInMillis();
        System.out.println(now + " = " + formatter.format(calendar2.getTimeInMillis()));
		
//		while(true)
//			System.out.println(""+new cordingLabs().getSequence("GCOIN"+System.currentTimeMillis()));

		
//		System.out.println(TimeZone.getAvailableIDs()[0]);
//		
//		for(String timeZoneId : TimeZone.getAvailableIDs()) {
//			System.out.println("timeZoneId : "+TimeZone.getTimeZone(timeZoneId));
//		}
//		System.out.println(ZoneOffset.of("-08:00").normalized().getId());
//		System.out.println(TimeZone.getTimeZone("GMT-08:00"));
		
//		System.out.println(UUIDUtils.generateUUID().length());
//		String sp[] = "/api/v1/members/info".split("/");
//		System.out.println(sp[3]);
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
		
		String[] menuList = {"�깮�꽑援ъ씠","�닚��援�","吏쒖옣/吏щ퐬/蹂띠쓬諛�","遺곸뼱援�/留뚮몢援�","遺���李뚭컻","�씪硫�/洹쒕룞","�꽕�쟻�깢","移대젅/�슦�룞/�룉源뚯뒪","�굾吏�/�삤吏뺤뼱蹂띠쓬","源�移섏컡媛�/�젣�쑁","�닚�몢遺�","�닔�젣踰꾧굅","�냼怨좉린援�諛�","	��援��닔","	遺꾩떇吏�"};
		
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
		
		//�쟾臾명빆紐⑹닔
		int elementCnt = 5;
		
		MapMessageTransform msgTrans = new MapMessageTransform();
		ElementInfo[] element = new ElementInfo[elementCnt];
		ElementInfo info = null;
		
		//�쟾臾명빆紐⑸퀎 �꽭�똿
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
				//�떦�씪�씠 鍮꾩쁺�뾽�씪�씤吏� 泥댄겕
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
			
			//�떦�씪�씠 鍮꾩쁺�뾽�씪�씤吏� 泥댄겕
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
	
	public static void setHttpheader(String authId, String authSecret) {
		try {
			//header 1.
			//set httpheader name : Timestamp
			String timestamp = String.valueOf(System.currentTimeMillis());
			
			//header 2.
			//set httpheader name : Authorization
			String hashValue = HMACSHA256.generate(timestamp, authSecret);
			String authorization = authId + ":" + hashValue;
			authorization = "Basic " + Base64.getEncoder().encodeToString(authorization.getBytes());
			
			//header 3.
			//set httpheader name : X-Auth-Type = GLN
			//header 4.
			//set httpheader name : Accept = application/json
			//header 5.
			//set httpheader name : Content-Type = application/json
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
}
