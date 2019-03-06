package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MapMessageTransform {
	
	private static final Logger logger = LoggerFactory.getLogger(MapMessageTransform.class);
	
	public Map parse(byte[] source, ElementInfo[] elements) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream(source);
		return (Map) parse(bais, elements);
	}
	
	public TAData parse(InputStream source, ElementInfo[] elements) throws Exception {
		
//		Transform message = (Transform) clazz.newInstance();
//		ElementInfo[] elements = message.getElements();
		TAData result = new TAData();
		
		for (int i = 0; i < elements.length; ++i) {
			ElementInfo element = elements[i];

			byte[] buff = null;
			switch (element.type) {
				case 1:
					buff = readSource(source, element.length);
					result.put(element.name, StringUtils.trimToEmpty(new String(buff)));
					break;
				
				case 2:
					buff = readSource(source, element.length);
					result.put(element.name, Integer.valueOf(NumberUtils.toInt(new String(buff))));
					break;
				
				case 3:
					buff = readSource(source, element.length);
					result.put(element.name, Long.valueOf(NumberUtils.toLong(new String(buff))));
					break;
				
				case 4:
					buff = readSource(source, element.length);
					result.put(element.name, Float.valueOf(NumberUtils.toFloat(new String(buff))));
					break;	
				
				case 5:
					buff = readSource(source, element.length);
					result.put(element.name, Double.valueOf(NumberUtils.toDouble(new String(buff))));
					break;
					
				case 6:
//					buff = readSource(source, element.length);
//					result.put(element.name, buff);
					
					buff = readSource(source, element.length);
					result.put(element.name, StringUtil.convertByteToString(buff, "KSC5601").trim());
					break;
				
				case 7:
					int arrNum = 0;
					if (1 == element.refType) {
						for (int j = 0; j < elements.length; ++j) {
							if ((8 != elements[j].type) || (!(StringUtils.equals(element.name, elements[j].arrayName))))
								continue;
							arrNum = ((Integer) result.get(elements[j].name)).intValue();
							break;
						}
	
					} else if (2 == element.refType) {
						arrNum = element.length;
					} else if (3 == element.refType) {
						arrNum = Integer.parseInt(result.get(element.arrayCntName).toString());
					}
					
					List<TAData> array = new ArrayList<TAData>();
					for (int j = 0; j < arrNum; ++j) {
						array.add(parse(source, element.element));
					}
					
					result.put(element.name, array);
					break;
				case 8:
					buff = readSource(source, element.length);
					result.put(element.name, Integer.valueOf(NumberUtils.toInt(new String(buff))));
					break;
			}
		}

		return result;
	}
	
	private byte[] readSource(InputStream source, int length)
			throws IOException {
		byte[] buff = new byte[length];
		int readLength = source.read(buff);
		if (readLength != length) {
			throw new IOException("읽어야할 데이타의 길이가 같지 않습니다.");
		}
		return buff;
	}
	
//	public byte[] toByteArray(ElementInfo[] element, TAData map) throws Exception {
//		Class<?> clazz = Class.forName(className);
//		return toByteArray(element, map);
//	}

//	public byte[] toByteArray(Class<?> clazz, TAData map) throws Exception {
//		Transform transform = (Transform) clazz.newInstance();
//		return toByteArray(transform, map);
//	}
	
	public String toStringArray(Class<?> clazz, TAData map) throws Exception {
		Transform transform = (Transform) clazz.newInstance();
		return toStringArray(transform, map);
	}

	public byte[] toByteArray(ElementInfo[] elements, TAData map) throws Exception {
		if (elements == null) {
			return new byte[0];
		}
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		try {
			for (int i = 0; i < elements.length; ++i) {
				ElementInfo element = elements[i];

				Object value = map.get(element.name);
				byte[] buff = null;
				switch (element.type) {
				case 1:
					buff = new byte[element.length];
					for (int j = 0; j < buff.length; ++j) {
						buff[j] = 32;
					}
					if (value != null) {
						String strValue = (String) value;
						byte[] buff2 = strValue.getBytes();
						if (buff.length < buff2.length)
							System.arraycopy(buff2, 0, buff, 0, buff.length);
						else
							System.arraycopy(buff2, 0, buff, 0, buff2.length);
					}
					result.write(buff);
					break;
				case 6:
					if (value == null) {
						buff = new byte[element.length];
						for (int len = 0; len < buff.length; ++len)
							buff[len] = 32;
					} else {
						buff = new byte[element.length];
						System.arraycopy(StringUtil.convertByteToByte(value.toString(), element.length, "UTF-8", "KSC5601"), 0, buff, 0, buff.length);
//						buff = StringUtil.convertByteToByte(value.toString(), element.length, "UTF-8", "KSC5601");
					}
					result.write(buff);
					break;
				case 2:
					int intValue = 0;
					if (value != null)
						intValue = ((Number) value).intValue();
					buff = StringUtils.leftPad(Integer.toString(intValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 3:
					long longValue = 0L;
					if (value != null)
						longValue = ((Number) value).longValue();
					buff = StringUtils.leftPad(Long.toString(longValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 4:
					float floatValue = 0.0F;
					if (value != null)
						floatValue = ((Number) value).floatValue();
					buff = StringUtils.leftPad(Float.toString(floatValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 5:
					double doubleValue = 0.0D;
					if (value != null)
						doubleValue = ((Number) value).doubleValue();
					buff = StringUtils.leftPad(Double.toString(doubleValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 8:
					int infoValue = 0;
					for (int j = 0; j < elements.length; ++j) {
						if (StringUtils.equals(element.arrayName,
								elements[j].name)) {
							List<?> array = (List<?>) map.get(elements[j].name);
							if (array == null)
								break;
							infoValue = array.size();
							break;
						}
					}

					buff = StringUtils.leftPad(Long.toString(infoValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 7:
					int arrNum = 0;
					List<?> array = null;
					if (value != null) {
						array = (List<?>) value;
					} else {
						array = new ArrayList<Object>();
					}
					if (1 == element.refType) {
						arrNum = array.size();
					} else if (2 == element.refType) {
						arrNum = element.length;
					} else if (3 == element.refType) {
						arrNum = Integer.parseInt(map.get(element.arrayCntName).toString());
					}
					
					for (int j = 0; j < arrNum; ++j)
						result.write(toByteArray(element.element, (TAData) array.get(j)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toByteArray();
	}
	
	public String toStringArray(Transform source, TAData map) throws Exception {
		if (source == null) {
			return "";
		}
		ByteArrayOutputStream result = new ByteArrayOutputStream();
		ElementInfo[] elements = source.getElements();
		try {
			for (int i = 0; i < elements.length; ++i) {
				ElementInfo element = elements[i];

				Object value = map.get(element.name);
				byte[] buff = null;
				switch (element.type) {
				case 1:
					buff = new byte[element.length];
					for (int j = 0; j < buff.length; ++j) {
						buff[j] = 32;
					}
					if (value != null) {
						String strValue = (String) value;
						byte[] buff2 = strValue.getBytes();
						if (buff.length < buff2.length)
							System.arraycopy(buff2, 0, buff, 0, buff.length);
						else
							System.arraycopy(buff2, 0, buff, 0, buff2.length);
					}
					result.write(buff);
					
					
					
//					if (value != null) {
//						bufStr = StringUtils.rightPad((String) value, element.length, ' ');
//					}
//					else{
//						bufStr = StringUtils.rightPad("", element.length, ' ');
//					}
//					result.write(bufStr.getBytes());
					
					
					break;
				case 6:
					if (value == null) {
						buff = new byte[element.length];
						for (int len = 0; len < buff.length; ++len)
							buff[len] = 32;
					} else {
						buff = (byte[]) (byte[]) value;
					}
					result.write(buff);
					break;
				case 2:
					int intValue = 0;
					if (value != null)
						intValue = ((Number) value).intValue();
					buff = StringUtils.leftPad(Integer.toString(intValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 3:
					long longValue = 0L;
					if (value != null)
						longValue = ((Number) value).longValue();
					buff = StringUtils.leftPad(Long.toString(longValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 4:
					float floatValue = 0.0F;
					if (value != null)
						floatValue = ((Number) value).floatValue();
					buff = StringUtils.leftPad(Float.toString(floatValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 5:
					double doubleValue = 0.0D;
					if (value != null)
						doubleValue = ((Number) value).doubleValue();
					buff = StringUtils.leftPad(Double.toString(doubleValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 8:
					int infoValue = 0;
					for (int j = 0; j < elements.length; ++j) {
						if (StringUtils.equals(element.arrayName,
								elements[j].name)) {
							List<?> array = (List<?>) map.get(elements[j].name);
							if (array == null)
								break;
							infoValue = array.size();
							break;
						}
					}

					buff = StringUtils.leftPad(Long.toString(infoValue),
							element.length, '0').getBytes();
					result.write(buff);
					break;
				case 7:
					int arrNum = 0;
					List<?> array = null;
					if (value != null) {
						array = (List<?>) value;
					} else {
						array = new ArrayList<Object>();
					}
					if (1 == element.refType) {
						arrNum = array.size();
					} else if (2 == element.refType) {
						arrNum = element.length;
					}
					for (int j = 0; j < arrNum; ++j)
						result.write(toByteArray(element.element, (TAData) array.get(j)));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result.toString();
	}
	
	
}
