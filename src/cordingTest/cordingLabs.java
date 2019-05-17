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
import org.apache.commons.lang3.RandomStringUtils;
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
		String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImtpZCI6Ik0wRkNkVPQSJ9.eyJwaWIiOnsibWFzdGVyX2FwcF9pZCI6IkpiOEU0UGxiaGRCMmFOWURRMEE4NFVyQVQ0TmRqRjA4Iiwic2Vzc2lvbl9pZCI6IjI3MTQ0WHhYX2NkYzVkNzE0LWQ1MGEtNDAzMy1lOGM2LWNjMGZiNDU5Njc3NCIsImFsbG93X2F1dG9fb9naW4iOiJ0cnVlIiwiYXBjIjoiOSIsImN0YyI6IkQifSwiaXNzIjoiaHR0cHM6Ly9hdXRoLmludC5hY2vdW50cy5kb3dqb25lcy5jb20vIiwic3ViIjoiYXV0aDB8Vk5KUUVQSlYzUUJEMk5QWSIsImF1ZCI6IkpiOEU0UGxiaGRCMmFOWURRMEE4NFVyQVQ0TmRqRjA4IiiZXhwIjoxNTAzODQ2NTU3LCJpYXQiOjE1MDM1ODczNTcsImF6cCI6IkpiOEU0UGxiaGRCMmFOWURRMEE4NFVyQVQ0TmRqRjA4In0.AdGy4iNtRnB1sEUOdx8iqWaCiJS0MkGOrRCt6SsDl3HyxLa5SoNczb2rCu9x7fYbyDnjKUn0ZkLHDS_DDyio6JrJ5qXF9p07IGhKhouDW1ouX6GEZ_LyTsJ7gFK0830N_VjBMFJcDiTOQ89Pz8QwaNlrkKgjq11bEVOxSsiWFzjDAhB23fUiIN6Fn8ABezySZhDzWOM87H7fG2t8gOlC0aPRwAHGZvyrUopApyK2G7v6ODyvD6S5ghqAmqB_BsgAyr4urvGg2euH5MNCCepclK09BMgb9KqoNoFQe0Q34H9wzjFlu1FPWP-GZm3cgJZYqhx7G4ih12FVOMA";
		System.out.println(new String(Base64.getDecoder().decode(token)));
//		String sessionId = "S09FWEtSOkMxMzUxQzlGQ0U3NDg3ODg5N0NCMzhEMzE0OUFEQjRFRDc2MTY0NzRBQUEwQjE5QTg2M0JBQTFFMTBGMTY2NDY=";
//		String value = new String(Base64.getDecoder().decode(sessionId));
//		System.out.println("value:"+value);
		for(int i=0;i<1000;i++) {
			System.out.println(RandomStringUtils.randomNumeric(6));
		}
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
