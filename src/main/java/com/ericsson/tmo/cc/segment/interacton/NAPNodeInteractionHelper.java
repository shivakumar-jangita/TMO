/**
 * 
 */
package com.ericsson.tmo.cc.segment.interacton;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;

/**
 * @author esivjan
 *
 */
public class NAPNodeInteractionHelper {

	private static String correctionPropertiesFileName = "/opt/ConsistencyChecker/conf/configuration_metro.properties";
	private static final String CORRECTION = "Correction";
	private static final String CREATESDP = "CreateSDP";
	private static final String PRODUCTID_MISMATCH = "ProductID_Mismatch";
	private static final String SUBSTATUS_MISMATCH = "SubStatus_Mismatch";
	private static final String OFFEREXPIRYDATE = "OfferExpiryDate_Mismatch";
	private static final String CUSTOMERID_MISMATCH = "CustomerID_Mismatch";
	private static final String PAM_MISMATCH = "PAM_Mismatch";
	private static final String UT_MISMATCH = "UT_Mismatch";
	private static final String DELETESDP = "DeleteSDP";
	private static final String NAP_URL = "NAP_URL";
	private static final String ECC_URL = "ECC_URL";
	private static final String AIR_URL = "AIR_URL";
	private static final String VERIFY = "Verify";
	private static final String NAP_PASSWORD = "NAP_Password";
	private static final String ECC_PASSWORD = "ECC_Password";
	private static final String NAP_PARTNERID = "NAP_PartnerID";
	private static final String Direct_Mismatch_To_External_File = "direct_mismatch_to_external_file";
	private static final String FULL_SUBSCRIBER_MISMATCH = "Full_Subscriber_Mismatch";
	private static final String DA_ID_MISMATCH = "DA_ID_Mismatch";
	private static final String ORIGINOPERATORID = "originOperatorID";
	private static final String MAX_MISMATCH_COUNT = "max_mismatch_count";
	private static final String METRO_SERVICES = "Metro_services";
	private static final String METRO_OFFERIDS = "Metro_offerids";
	
	private static final Logger logger = LoggerFactory.getLogger(NAPNodeInteractionHelper.class);
	static NAPNodeInteraction napInteraction = new NAPNodeInteraction();
	private static Properties properties;
	private static SimpleDateFormat dateFormat = null;

	public static String queryNAP(String msisdn, ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		logger.info("Controll reached in NAPXmlRequestUtils:queryNAP() -- starts here");
		String response = null;
		String modifiedQueryRequest = NodeRequestConstants.NAP_QUERY_REQUEST.replace(SegmentConstants.MSISDN_VALUE,
				msisdn);
		response = napInteraction.queryRequest(modifiedQueryRequest, configPropertiesPojo);

		logger.info("response: " + response);
		logger.info("Controll reached in NAPXmlRequestUtils:queryNAP() -- ends here");
		return response;
	}

	private static void readProperties(String correctionPropertiesFilePath) {
		properties = new Properties();
		InputStream input = null;
		correctionPropertiesFilePath = "/opt/ConsistencyChecker/conf";
		// correctionPropertiesFilePath = "/home/mininet/CC/conf";
		String correctionPropertiesFile = correctionPropertiesFilePath + File.separator + correctionPropertiesFileName;
		try {
			input = new FileInputStream(correctionPropertiesFile);
			System.out.println("Reading service class ids in " + correctionPropertiesFile);
			properties.load(input);
			if (properties.getProperty(VERIFY) == null) {
				properties.setProperty(VERIFY, "enabled");
			}
			if (properties.getProperty(ORIGINOPERATORID) == null) {
				properties.setProperty(ORIGINOPERATORID, "CC");
			}
		} catch (FileNotFoundException e) {
			System.out.println("File " + correctionPropertiesFile + " not found...");
			generateDefaultProperties(correctionPropertiesFile);
		} catch (IOException e) {
			System.out.println("IOException loading " + correctionPropertiesFile);
			e.printStackTrace();
		}
	}
	private static void generateDefaultProperties(String configFilePath) {
		properties = new Properties();
		OutputStream output = null;

		System.out.println("Generating default " + configFilePath);
		try {
			output = new FileOutputStream(configFilePath);
			properties.setProperty(CORRECTION, "enabled");
			properties.setProperty(CREATESDP, "true");
			properties.setProperty(PRODUCTID_MISMATCH, "true");
			properties.setProperty(SUBSTATUS_MISMATCH, "true");
			properties.setProperty(OFFEREXPIRYDATE, "true");
			properties.setProperty(CUSTOMERID_MISMATCH, "true");
			properties.setProperty(PAM_MISMATCH, "true");
			properties.setProperty(UT_MISMATCH, "true");
			properties.setProperty(DELETESDP, "true");
			properties.setProperty(NAP_URL, "http://10.178.69.1:8002/PPListener/partnerpublisher");
			properties.setProperty(ECC_URL, "http://10.169.71.207:8081/cwf/esp/query");
			properties.setProperty(AIR_URL, "http://10.169.71.57:10011/Air");
			properties.setProperty(VERIFY, "enabled");
			properties.setProperty(NAP_PASSWORD, "MBC@test");
			properties.setProperty(NAP_PARTNERID, "53");
			properties.setProperty(ECC_PASSWORD, "mykey");
			properties.setProperty(Direct_Mismatch_To_External_File, "true");
			properties.setProperty(FULL_SUBSCRIBER_MISMATCH, "true");
			properties.setProperty(DA_ID_MISMATCH, "true");
			properties.setProperty(ORIGINOPERATORID, "CC");
			properties.setProperty(MAX_MISMATCH_COUNT, "500");
			properties.store(output, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not save " + configFilePath);
			e.printStackTrace();
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	public static String queryNAPTest(String msisdn) {
		generateDefaultProperties(correctionPropertiesFileName);
		String url = properties.getProperty(NAP_URL);
		String partnerID = properties.getProperty(NAP_PARTNERID);
		String Passwrod = properties.getProperty(NAP_PASSWORD);
		System.out.println("url="+url);
		System.out.println("partnerID="+partnerID);
		System.out.println("Passwrod="+Passwrod);
		String response = null;
		CloseableHttpClient client = HttpClients.createDefault();
		double index = Math.random();
		String origin = String.valueOf(index);
		String originTransactionID = origin.substring(2);
		try {
			HttpPost httpPost = new HttpPost(url);

			String xmlString = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><CustomerTransactions><CustomerTransaction><TransactionInfo><RequestedAction>query</RequestedAction>"
					+ "<msisdn>" + msisdn + "</msisdn><Version>1.4</Version><TransactionID>" + originTransactionID
					+ "</TransactionID><VendorID>53</VendorID></TransactionInfo></CustomerTransaction></CustomerTransactions>";

			System.out.println(xmlString);
			String timestamp = getTimestamp();
			System.out.println("timestamp is: " + timestamp);
			String hexString = toShaHexString(Passwrod + timestamp);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("Password", hexString));
			params.add(new BasicNameValuePair("Timestamp", timestamp));
			params.add(new BasicNameValuePair("partnerID", partnerID));
			params.add(new BasicNameValuePair("xmlPayload", xmlString));
			httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			CloseableHttpResponse httpResponse = client.execute(httpPost);
			try {

				HttpEntity httpEntity = httpResponse.getEntity();
				response = EntityUtils.toString(httpEntity).toString();
			} catch (Exception e) {
				System.out.println("exception occured");
				e.printStackTrace();
			} finally {
				httpResponse.close();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				client.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("response: " + response);
		return response;
	}

	public static String getTimestamp() {
		DateFormat df = getDateFormat();
		return df.format(new Date(System.currentTimeMillis()));
	}
	public static DateFormat getDateFormat() {
		if (dateFormat == null) {
			// "yyyy-MM-dd\\THH:mm:ss\\Z" (e.g. 2006-05-12T19:35:12Z), where Z
			// means UTC
			dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		}
		return dateFormat;
	}


	public static String toShaHexString(String plaintext) {
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
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


	public static String createRequestFromNap(String response) {
		String createReq = "";
		int idx = response.indexOf(NodeRequestConstants.CUSTOMER_TRANSACTION_START);
		String str1 = "";
		String str2 = "";
		if (idx > 0) {
			createReq = response.substring(idx);
			if (createReq.indexOf(NodeRequestConstants.QOS_STATUS_START) > 0) {
				str1 = createReq.substring(0, createReq.indexOf(NodeRequestConstants.QOS_STATUS_START));
				str2 = createReq.substring(createReq.indexOf(NodeRequestConstants.QOS_STATUS_END)
						+ NodeRequestConstants.QOS_STATUS_END.length());

				createReq = str1 + str2;
			}

			str1 = createReq.substring(0, createReq.indexOf(NodeRequestConstants.TRANSACTION_RESULTS_START));
			str2 = createReq.substring(createReq.indexOf(NodeRequestConstants.TRANSACTION_RESULTS_END)
					+ NodeRequestConstants.TRANSACTION_RESULTS_END.length());

			createReq = str1 + str2;
			// Random randNum = new Random();
			// String transId = "CC" + randNum.nextInt(10000000);

			str1 = createReq.substring(0, createReq.indexOf(NodeRequestConstants.TRANSACTION_ID_START)
					+ NodeRequestConstants.TRANSACTION_ID_START.length());
			str2 = createReq.substring(createReq.indexOf(NodeRequestConstants.TRANSACTION_ID_END));
			createReq = str1 + SegmentConstants.TRANS_ID_DATA + str2;
			System.out.println("createReq from NAP =  " + createReq);
		}
		return createReq;
	}

}
