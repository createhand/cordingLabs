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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.DecoderException;
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
	
	public static boolean caseTest(String status) {
		switch (status) {
		case "30" :
			return false;
		default :
			return true;
		}
	}
	
	public static String getSHA256(String src) {
		String SHA = "";
		try{			
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(src.getBytes());

			byte[] mdResult = md.digest();

			StringBuffer sb = new StringBuffer();
			for(int i=0; i < mdResult.length; i++){
				sb.append(Integer.toString((mdResult[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		} catch(Exception e) {
			return src;			
		}	
		return SHA;
	}
	
	public static void main(String[] args) {
		
		String[] foodlist = getFoodList();
		for(int i=0;i<foodlist.length;i++) {
			System.out.println("["+(i+1)+"]"+foodlist[i]);
		}
		
		String srt = "상호저축은행#067*1*3*215302#김*수#1원#"; 
		try {
			srt = new String(srt.getBytes("UTF-8"), "EUC-KR");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		System.out.println(srt);
		System.out.println(getSHA256(srt).toUpperCase());
	}
	
	public static String[] getFoodList() {
		return new String[]{
		"남도맛집", 
		"호프집 돈까스",
		"오징어풍경",
		"향설주랑 짜파게티",
		"장독대 김치찌개",
		"장칼국수",
		"광화문수제비",
		"쿵푸마라탕",
		"북어국집",
		"한스델리",
		"이화수육개장",
		"보름 떡볶이",
		"맷돌로만",
		"맥도날드",
		"아오지라멘",
		"청키면가",
		"피슈마라탕",
		"짬뽕지존",
		"종로분식",
		"미진 돈까스",
		"종로수제비",
		"바스버거 수제버거",
		"채선당",
		"장혁민부대찌개",
		"노브랜드버거",
		"북어국집",
		"우육면관",
		"죠스떡볶이",
		"한식뷔페1",
		"장군보쌈",
		"다동화로",
		"코코이찌방 카레",
		"한식뷔페2",
		"고랭지김치찌개",
		"후니도니 돈까스",
		"아비꼬 카레",
		"맘스터치",
		"시래기집",
		"싸이공 쌀국수",
		"샤오바오우육면"
		};
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
			
		} catch (InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException | DecoderException e) {
			e.printStackTrace();
		}
	}
	
}
