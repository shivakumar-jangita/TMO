package com.ericsson.tmo.cc.segment.interacton;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.utils.SegmentUtils;

public class SDPNodeInteractionHelper {

	private static final Logger logger = LoggerFactory.getLogger(SDPNodeInteractionHelper.class);
	public static SDPNodeInteraction sdpInteraction = new SDPNodeInteraction();

	public static String getAccountDetails(String mismatchMsisdn, ConfigPropertiesPojo configPropertiesPojo) {

		logger.info("Controll entered in SDPNodeInteractionHelper:getAccountDetails() -- starts here ");
		String getAccountDtlsRequest = NodeRequestConstants.GET_ACCOUNT_DTLS_REQ_PAY_LOAD
				.replace(SegmentConstants.MISMATCH_MSISDN, mismatchMsisdn);
		String accountDtlsResp = null;
		try {
			accountDtlsResp = sdpInteraction.queryRequest(getAccountDtlsRequest, configPropertiesPojo);
		} catch (Exception e) {

			logger.error("exception occured in getAccountDetails() ::" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("accountDtlsResp ::" + accountDtlsResp);
		logger.info("Controll exists in SDPNodeInteractionHelper:getAccountDetails() -- end here ");

		return accountDtlsResp;
	}
	
	
	public static void callDeleteUTDetails(String payload,ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		logger.info("callDeleteUTDetails : delete UT details");
		
			logger.info("Request" + payload);
			if (configPropertiesPojo.getVerify() != null && 
					!configPropertiesPojo.getVerify().equalsIgnoreCase(SegmentConstants.ENABLE)) {
				
				sdpInteraction.deleteRequest(payload, configPropertiesPojo);
			} else {
				SegmentUtils.saveRequestPayload(payload, configPropertiesPojo.getReportName());
			}
	}
	
	
	public static String callUpdateUTDetails(String msisdn,String payload,ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		logger.info("callUpdateUTDetails : update UT details");
			String updateUTResp = null;
			logger.info("Request" + payload);
			if (configPropertiesPojo.getVerify() != null && 
					!configPropertiesPojo.getVerify().equalsIgnoreCase(SegmentConstants.ENABLE)) {
				
				payload.replace(SegmentConstants.ORIGINOPERATORID_DATA, configPropertiesPojo.getOriginOperatorId());
				payload.replace(SegmentConstants.MSISDN_VALUE, msisdn);
				
				updateUTResp = sdpInteraction.modifyRequest(payload, configPropertiesPojo);
			} else {
				SegmentUtils.saveRequestPayload(payload, configPropertiesPojo.getReportName());
			}
			return updateUTResp;
	}

	public static List<String> getUsageIDLists(String msisdn, HashMap<String, String> thresholdSourceMap,
			ConfigPropertiesPojo configPropertiesPojo) {

		logger.info("Controll entered in SDPNodeInteractionHelper:getUsageIDLists() -- starts here ");

		String getUsageIDListsResp = null;
		List<String> ThresholdIdList = new ArrayList<String>();

		String getUsageIDListsRequest = NodeRequestConstants.GET_USAGETHRESHOLDS_AND_COUNTERS_PAY_LOAD
				.replace(SegmentConstants.MISMATCH_MSISDN, msisdn);

		getUsageIDListsRequest = getUsageIDListsRequest.replace(SegmentConstants.ORIGINOPERATORID_DATA,
				configPropertiesPojo.getOriginOperatorId());
		
	//getUsageIDListsResp="<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><struct><member><name>availableServerCapabilities</name><value><array><data><value><i4>2013594308</i4></value><value><i4>11</i4></value></data></array></value></member><member><name>negotiatedCapabilities</name><value><array><data><value><i4>0</i4></value></data></array></value></member><member><name>originOperatorID</name><value><string>CC</string></value></member><member><name>originTransactionID</name><value><string>5019868304859979</string></value></member><member><name>responseCode</name><value><i4>0</i4></value></member><member><name>usageCounterUsageThresholdInformation</name><value><array><data><value><struct><member><name>usageCounterID</name><value><i4>5006</i4></value></member><member><name>usageCounterValue</name><value><string>0</string></value></member><member><name>usageThresholdInformation</name><value><array><data><value><struct><member><name>usageThresholdID</name><value><i4>50061</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>50062</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>50063</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>50064</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>167772160</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>50065</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>50066</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>209715200</string></value></member></struct></value></data></array></value></member></struct></value><value><struct><member><name>usageCounterID</name><value><i4>10011</i4></value></member><member><name>usageCounterValue</name><value><string>0</string></value></member><member><name>usageThresholdInformation</name><value><array><data><value><struct><member><name>usageThresholdID</name><value><i4>100111</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100112</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100113</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100114</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100115</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100116</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value></data></array></value></member></struct></value><value><struct><member><name>usageCounterID</name><value><i4>10041</i4></value></member><member><name>usageCounterValue</name><value><string>0</string></value></member><member><name>usageThresholdInformation</name><value><array><data><value><struct><member><name>usageThresholdID</name><value><i4>100411</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100412</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100413</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100414</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>1932734464</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100415</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>2147483648</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100416</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value></data></array></value></member></struct></value><value><struct><member><name>usageCounterID</name><value><i4>10071</i4></value></member><member><name>usageCounterValue</name><value><string>0</string></value></member><member><name>usageThresholdInformation</name><value><array><data><value><struct><member><name>usageThresholdID</name><value><i4>100711</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100712</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100713</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100714</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100715</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>100716</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value></data></array></value></member></struct></value><value><struct><member><name>usageCounterID</name><value><i4>13001</i4></value></member><member><name>usageCounterValue</name><value><string>0</string></value></member><member><name>usageThresholdInformation</name><value><array><data><value><struct><member><name>usageThresholdID</name><value><i4>130011</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130012</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>30064771072</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130013</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>32212254720</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130014</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130015</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130016</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value></data></array></value></member></struct></value><value><struct><member><name>usageCounterID</name><value><i4>13021</i4></value></member><member><name>usageCounterValue</name><value><string>0</string></value></member><member><name>usageThresholdInformation</name><value><array><data><value><struct><member><name>usageThresholdID</name><value><i4>130211</i4></value></member><member><name>usageThresholdSource</name><value><i4>1</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130212</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130213</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130214</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130215</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value><value><struct><member><name>usageThresholdID</name><value><i4>130216</i4></value></member><member><name>usageThresholdSource</name><value><i4>3</i4></value></member><member><name>usageThresholdValue</name><value><string>999999999999999</string></value></member></struct></value></data></array></value></member></struct></value></data></array></value></member></struct></value></param></params></methodResponse>";

		try {
			getUsageIDListsResp = sdpInteraction.queryRequest(getUsageIDListsRequest, configPropertiesPojo);
		} catch (Exception e) {
			logger.error("exception occured in getUsageIDLists() ::" + e.getMessage());
			e.printStackTrace();
		}
		logger.info("getUsageIDListsResp ::" + getUsageIDListsResp);

		try {
			// Use String reader
			Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
					.parse(new InputSource(new StringReader(getUsageIDListsResp)));

			NodeList getUsageIDNodeListsResp = document.getElementsByTagName(SegmentConstants.MEMBER);
			String thresholdId = null;

			for (int i = 0; i < getUsageIDNodeListsResp.getLength(); i++) {

				Node napNoderes = getUsageIDNodeListsResp.item(i);

				logger.info("\nCurrent Element :" + napNoderes.getNodeName());
				if (napNoderes.getNodeType() == Node.ELEMENT_NODE) {
					Element eElementres = (Element) napNoderes;
					eElementres.getElementsByTagName(SegmentConstants.NAME).item(0).getTextContent();
					String name = eElementres.getElementsByTagName(SegmentConstants.NAME).item(0).getTextContent();
					if (name.equalsIgnoreCase(SegmentConstants.USAGE_THRESHOLD_ID)) {
						thresholdId = eElementres.getElementsByTagName(SegmentConstants.VALUE).item(0).getTextContent();

						Node usageThresholdIDSource = eElementres.getNextSibling();
						Element usageThresholdIDSourceElement = (Element) usageThresholdIDSource;
						String sourceValue = usageThresholdIDSourceElement.getElementsByTagName(SegmentConstants.VALUE)
								.item(0).getTextContent();

						ThresholdIdList.add(thresholdId);
						thresholdSourceMap.put(thresholdId, sourceValue);
					}

				}
			}

		} catch (Exception e) {
			logger.error("ERROR:" + e);
			e.printStackTrace();
		}
		logger.info("Controll exists in SDPNodeInteractionHelper:getUsageIDLists() -- end here ");
		return ThresholdIdList;
	}
	
	
	public static String addPamDetails(String payload,ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		logger.info("addPamDetails : Add PAM details");
			String updateUTResp = null;
			logger.info("Request" + payload);
			if (configPropertiesPojo.getVerify() != null && 
					!configPropertiesPojo.getVerify().equalsIgnoreCase(SegmentConstants.ENABLE)) {
				
				updateUTResp = sdpInteraction.createRequest(payload, configPropertiesPojo);
			} else {
				SegmentUtils.saveRequestPayload(payload, configPropertiesPojo.getReportName());
			}
			return updateUTResp;
	}

	
	

}
