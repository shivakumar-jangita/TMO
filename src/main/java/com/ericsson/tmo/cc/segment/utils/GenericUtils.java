package com.ericsson.tmo.cc.segment.utils;

import java.io.StringReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.TimeZone;

//import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

import com.ericsson.tmo.cc.segment.SegmentConstants;

public class GenericUtils {

	private static final Logger logger = LoggerFactory.getLogger(GenericUtils.class);

	private static SimpleDateFormat originTimestamp = null;

	public static Map<String, String> fetchConfigDataByResouceBundle(String filepath) {
		logger.debug("Controll reached in GeneralUtils:fetchConfigDataByResouceBundle() -- starts here ");
		Map<String, String> configDataMap = new HashMap<String, String>();
		try {
			
			ResourceBundle configResourceBundle = ResourceBundle.getBundle(filepath);
			Enumeration<String> configDataKeys = configResourceBundle.getKeys();
			while (configDataKeys.hasMoreElements()) {
				String configDataKey = configDataKeys.nextElement();
				String configDataValue = configResourceBundle.getString(configDataKey);
				configDataMap.put(configDataKey, configDataValue);
			}
		} catch (Exception e) {
			logger.error("Error retrieving properties file: " + e);
		}
		logger.debug("Controll reached in GeneralUtils:fetchConfigDataByResouceBundle() -- starts here ");
		return configDataMap;
	}

	public static int comparePropertiesBean(Properties prop1, Properties prop2) {
		// 0:No change, 1: Modified
		if (prop1 == null || prop2 == null) {
			return 1;
		}
		if (prop1.size() != prop2.size()) {
			return 1;
		}
		Enumeration<Object> enum1 = prop1.keys();
		while (enum1.hasMoreElements()) {
			String key = (String) enum1.nextElement();
			if (!(prop1.get(key).equals(prop2.get(key)))) {
				return 1;
			}

		}
		return 0;
	}

	public static String getCurrentTimeStamp() {
		SimpleDateFormat sdfDate = new SimpleDateFormat(SegmentConstants.CURRENT_TIMESTAMP);// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getCurrentTimeStampMilli() {
		SimpleDateFormat sdfDate = new SimpleDateFormat(SegmentConstants.CURRENT_TIMESTAMP_MILLI);// dd/MM/yyyy
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getCurrentTimeStampTZ() {
		SimpleDateFormat sdfDate = new SimpleDateFormat(SegmentConstants.CURRENT_TIMESTAMP_TIMEZONE);// with
																										// TimeZone
																										// ,
																										// e.g.
																										// 20160907T11:08:40-0700
		Date now = new Date();
		String strDate = sdfDate.format(now);
		return strDate;
	}

	public static String getTimestamp() {
		DateFormat df = getDateFormat();
		return df.format(new Date(System.currentTimeMillis()));
	}

	public static DateFormat getDateFormat() {

		// "yyyy-MM-dd\\THH:mm:ss\\Z" (e.g. 2006-05-12T19:35:12Z), where Z
		// means UTC
		DateFormat dateFormat = new SimpleDateFormat(SegmentConstants.CURRENT_DATE_FORMAT);
		dateFormat.setTimeZone(TimeZone.getTimeZone(SegmentConstants.TIMEZONE));
		return dateFormat;
	}

	public static String fetchOriginTransactionId() {
		double index = Math.random();
		String origin = String.valueOf(index);
		String originTransactionId = origin.substring(2);
		return originTransactionId;
	}
	
	public static Map<String, String> fetchNapElementByName(Element elementResp, String elementName,
			Map<String, String> napAttributes) {
		try {
			if (elementResp.getElementsByTagName(elementName) != null
					&& elementResp.getElementsByTagName(elementName).item(0) != null) {
				napAttributes.put(elementName, elementResp.getElementsByTagName(elementName).item(0).getTextContent());
			}
		} catch (Exception exception) {
			napAttributes.put(elementName, null);
		}
		return napAttributes;
	}

	public static Document convertStringToDocument(String xmlStringData) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		try {
			builder = factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource(new StringReader(xmlStringData)));
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String toShaHexString(String plaintext) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance(SegmentConstants.MESSAGE_DIGEST_FORMAT);
			sha1.update(plaintext.getBytes());
			byte[] hash = sha1.digest();
			String hexString = new String(bytesToHex(hash)).toUpperCase();
			return hexString;
		} catch (NoSuchAlgorithmException ex) {
			throw new IllegalStateException("Unable to get SHA-1 algorithm: " + ex.toString());
		}
	}

	public static String bytesToHex(byte input[]) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < input.length; i++) {
			int b = input[i];
			b &= 0xff;
			if ((byte) 0x10 > b)
				buffer.append('0');
			buffer.append(Integer.toHexString(b));
		}
		return buffer.toString();
	}

	public static boolean compareDate(String expiryDateNAP, String expiryDateSDP) {

		SimpleDateFormat sdpTimeStamp = new SimpleDateFormat(SegmentConstants.DATE_FORMAT_1);
		SimpleDateFormat napTimeStamp = new SimpleDateFormat(SegmentConstants.DATE_FORMAT_2);
		try {
			Date sdp = sdpTimeStamp.parse(expiryDateSDP);
			Date nap = napTimeStamp.parse(expiryDateNAP);
			Date currentDate = new Date();
			if (currentDate.compareTo(nap) > 0) {
				return false;
			}
			if (nap.compareTo(sdp) > 0 || nap.compareTo(sdp) < 0) {
				return true;
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;
	}

	public static String getOriginTimestamp() {
		DateFormat df = getOriginDateFormat();
		return df.format(new Date(System.currentTimeMillis()));
	}

	public static DateFormat getOriginDateFormat() {
		if (originTimestamp == null) {
			// means UTC
			originTimestamp = new SimpleDateFormat(SegmentConstants.DATE_FORMAT_3);
			originTimestamp.setTimeZone(TimeZone.getTimeZone(SegmentConstants.TIMEZONE_PDT));
		}
		return originTimestamp;
	}

}
