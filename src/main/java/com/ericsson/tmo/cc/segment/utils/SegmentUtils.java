package com.ericsson.tmo.cc.segment.utils;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.dao.service.SegmentServiceImpl;
import com.ericsson.tmo.cc.segment.rules.AttributesComparison;

public class SegmentUtils {

	private static final Logger logger = LoggerFactory.getLogger(SegmentUtils.class);

	public static PrintWriter textFileReportWriter(String segmentReportName) throws FileNotFoundException, IOException {

		logger.debug("Controll reached in SegmentUtils:textFileReportWriter() -- starts here ");
		PrintWriter writer = new PrintWriter(
				SegmentConstants.CSVMISMATCH_PATH + segmentReportName + SegmentConstants.TXT_FILE_EXTENSION,
				SegmentConstants.FILE_FORMAT);
		writer.println("=========================================================");
		writer.println("*************** Mismatch MSISDN Values *******************");
		writer.println("=========================================================");

		logger.debug("Controll exits in SegmentUtils:textFileReportWriter() -- ends here ");
		return writer;
	}

	public static void saveRequestPayload(String xmlString) {
		logger.debug("saveRequestPayload method calling");

		BufferedWriter bufferWriter = null;
		try {
			bufferWriter = new BufferedWriter(
					new FileWriter(SegmentConstants.INPUT_TXT + SegmentConstants.REPORT + SegmentConstants.TEXT, true));
			bufferWriter.newLine();
			bufferWriter.write(xmlString);
			bufferWriter.newLine();
			bufferWriter.flush();
		} catch (IOException ioe) {
			logger.debug("ERROR:" + ioe);
		} finally {
			if (bufferWriter != null)
				try {
					bufferWriter.close();
				} catch (IOException ioe2) {
					logger.debug("ERROR:" + ioe2);
				}
		}
	}

	public static void saveRequestPayload(String xmlString, String report) {
		BufferedWriter bufferWriter = null;
		try {
			// APPEND MODE SET HERE
			bufferWriter = new BufferedWriter(
					new FileWriter(SegmentConstants.INPUT_TXT + report + SegmentConstants.TEXT, true));
			bufferWriter.newLine();
			bufferWriter.write(xmlString);
			bufferWriter.newLine();
			bufferWriter.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally { // always close the file
			if (bufferWriter != null)
				try {
					bufferWriter.close();
				} catch (IOException ioe2) {
					// just ignore it
				}
		}
	}
	
	
	public static String combineListOfService(Set<String> napServiceSet){
		logger.info("combineListOfService method  start-->");
		String serviceList   = null;
		if (napServiceSet != null) {
			for (String service : napServiceSet) {
				if(serviceList != null )
					serviceList = serviceList+SegmentConstants.SIGN_COMA 
						+SegmentConstants.SIGN_SINGLE_CODE+service+SegmentConstants.SIGN_SINGLE_CODE;
				else
					serviceList = SegmentConstants.SIGN_SINGLE_CODE+service+SegmentConstants.SIGN_SINGLE_CODE;
				
			}
		}
		logger.info("combineListOfService method  end-->"+serviceList);
		return serviceList;
	}

	public static List<String> getAddonPassServiceList(String segmentName) {
		logger.debug("getAddonPassServiceList method");
		List<String> serviceList = new ArrayList<>();
		try {
			serviceList = SegmentServiceImpl.fetchAddonServiceListBySegment(segmentName);
		} catch (Exception e) {
			logger.debug("ERROR" + e);

		}
		logger.info("serviceList=" + serviceList);
		return serviceList;
	}

	public static List<String> fetchMrcAddOnOfferListBySegment(String segmentName) {
		logger.debug("getAddonPassServiceList method");
		List<String> offerlist = new ArrayList<>();
		try {
			offerlist = SegmentServiceImpl.fetchMrcAddOnOfferListBySegment(segmentName);

		} catch (Exception e) {

			e.printStackTrace();
		}
		logger.info("offerlist=" + offerlist);
		return offerlist;
	}

	public static boolean checkSDPFullsubscriber(String allofferId) {
		List<String> offerIdList = new LinkedList<String>(
				Arrays.asList(allofferId.split(SegmentConstants.SPLITER_PIPE_WITHSLASH)));
		if (offerIdList.contains("251100")) {
			return true;
		}
		return false;
	}

	public static boolean useCaseIsEnabled(String useCaseName, String correctionFlag) {
		if (useCaseName == null || useCaseName.equals(SegmentConstants.SPACE)) {
			return false;
		}
		return (useCaseName.equalsIgnoreCase(SegmentConstants.TRUE)
				&& correctionFlag.equalsIgnoreCase(SegmentConstants.ENABLE));
	}
	
	private static Map<String, Boolean> setDefualtMismatchScenaroStatus(Map<String, Boolean> mismatchscenaro){
		
		mismatchscenaro.put(SegmentConstants.STATUS_CODE, false);
		mismatchscenaro.put(SegmentConstants.CUSTOMERID, false);
		mismatchscenaro.put(SegmentConstants.PRODUCT_ID, false);
		mismatchscenaro.put(SegmentConstants.OFFER_ID, false);
		mismatchscenaro.put(SegmentConstants.USAGE_THRESHOLD_ID, false);
		mismatchscenaro.put(SegmentConstants.DEDICATED_ACCID, false);
		mismatchscenaro.put(SegmentConstants.BILLCYCLE_PERIOD, false);
		mismatchscenaro.put(SegmentConstants.EXPIRY_DATE, false);
		
		return mismatchscenaro;
	}
	
	
	private static String retrieveSdpExpiryDate(List<String> offerIdList,
			Map<String, String> expiryOfferIdMap, String segmentName) {
		String sdpExpiryDate = "";
		List<String> alteredList = offerIdList;
		List<String> mrcAddonOfferList = fetchMrcAddOnOfferListBySegment(segmentName);
		alteredList.retainAll(mrcAddonOfferList);
		for (String offerId : alteredList) {
			String serviceExpiryDate = expiryOfferIdMap.get(offerId);
			if (serviceExpiryDate != null) {
				serviceExpiryDate = serviceExpiryDate.substring(0, 8);
				sdpExpiryDate = sdpExpiryDate + SegmentConstants.SPLITER_PIPE + offerId + SegmentConstants.SPLITER_CAP
						+ serviceExpiryDate;
			}
		}
		return sdpExpiryDate;
	}
	

	public static Map<String, Boolean> getMismatchscenrioMap(String segmentName, Set<String> napServiceSet,
			Map<String, String> napservicesMAp, Map<String, String> napAttributes, Map<String, String> sdpAttributes,
			List<String> sdpUsageIDLists, String allproductID, String allOfferId, String allScheduleId,
			Map<String, String> expiryOfferIdMap) {

		String allServices = "";
		String napExpiryDate = "";
		String sdpExpiryDate = "";
		String sdpUTIDs = "";
		Map<String, Boolean> mismatchscenaro = new HashMap<>();
		for (String service : napServiceSet) {
			allServices = allServices + SegmentConstants.SPLITER_PIPE + service;
			String serviceExpiryDate = napservicesMAp.get(service);
			if (serviceExpiryDate == null) {

				serviceExpiryDate = SegmentConstants.DEFINED_EXPIRY_DATE;
			}
			serviceExpiryDate = serviceExpiryDate.substring(0, 4) + serviceExpiryDate.substring(5, 7)
					+ serviceExpiryDate.substring(8, 10);
			napExpiryDate = napExpiryDate + SegmentConstants.SPLITER_PIPE + service + SegmentConstants.SPLITER_CAP
					+ serviceExpiryDate;
		}
		
		List<String> offerIdList = new LinkedList<String>(
				Arrays.asList(allOfferId.split(SegmentConstants.SPLITER_PIPE_WITHSLASH)));
		
		sdpExpiryDate = retrieveSdpExpiryDate(offerIdList,expiryOfferIdMap,segmentName);
		
		for (String sdpUTID : sdpUsageIDLists) {
			sdpUTIDs = sdpUTIDs + SegmentConstants.SPLITER_PIPE + sdpUTID;
		}
		if (napExpiryDate.length() >= 1) {
			napExpiryDate = napExpiryDate.substring(1);
		}
		if (allServices.length() >= 1) {
			allServices = allServices.substring(1);
		}
		if (sdpExpiryDate.length() >= 1) {
			sdpExpiryDate = sdpExpiryDate.substring(1);
		}
		if (sdpUTIDs.length() >= 1) {
			sdpUTIDs = sdpUTIDs.substring(1);
		}
		
		mismatchscenaro = setDefualtMismatchScenaroStatus(mismatchscenaro);

		if (!AttributesComparison.checkOfferId(allServices, allOfferId,segmentName)) {
			mismatchscenaro.put(SegmentConstants.OFFER_ID, true);
		}
		if (!AttributesComparison.checkProductID(allServices, allproductID)) {
			mismatchscenaro.put(SegmentConstants.PRODUCT_ID, true);
		}
		
		if (!AttributesComparison.checkExpiryDate(napExpiryDate, sdpExpiryDate,	segmentName)) {
			mismatchscenaro.put(SegmentConstants.EXPIRY_DATE, true);
		}
		
		String napStatus = napAttributes.get(SegmentConstants.STATUS_CODE);
		if (!AttributesComparison.checkStatusCode(napStatus, allOfferId,segmentName)) {
			mismatchscenaro.put(SegmentConstants.STATUS_CODE, true);
		}
		
		if (!AttributesComparison.checkUTID(napServiceSet, sdpUTIDs,segmentName)) {
			mismatchscenaro.put(SegmentConstants.USAGE_THRESHOLD_ID, true);
		}
		
		String billcyclePeriod = napAttributes.get(SegmentConstants.BILLCYCLE_PERIOD);
		if (billcyclePeriod == null || billcyclePeriod.isEmpty()) {
			mismatchscenaro.put(SegmentConstants.BILLCYCLE_PERIOD, true);
		} else if (!AttributesComparison.checkBillCyclePeriod(billcyclePeriod, allScheduleId,segmentName)) {
			mismatchscenaro.put(SegmentConstants.BILLCYCLE_PERIOD, true);
		}

		return mismatchscenaro;
	}
	

	public String fetchExpiryTimeUtcResult(Set<String> productIdSet, Map<String, String> expiryOfferIdMap) {

		String offerId = null;
		String expiryTimeUtcResult = null;
		for (String productId : productIdSet) {
			String serviceList   = combineListOfService(productIdSet);
			if (offerId != null && offerId.equals("")) {
				String offerid2 = offerId.substring(7, 13);
				if (expiryOfferIdMap.get(offerid2) != null) {
					String expiryTimeUtc = changeToUTC(expiryOfferIdMap.get(offerid2));
					if (expiryTimeUtc == null) {

						expiryTimeUtcResult = expiryTimeUtcResult + SegmentConstants.EMPTY;
					} else {
						expiryTimeUtcResult = expiryTimeUtcResult + expiryTimeUtc;
					}
				}
			}
		}
		return expiryTimeUtcResult;

	}
	


	public String fetchExpiryTimeUtcResult(HashSet<String> productIdSet,
			HashMap<String, String> expiryOfferIdMap) {

		String offerIdList = null;
		String offerIdArray[] = null;
		String expiryTimeUtcResult = null;
		String offerId = null;
		for (String productId : productIdSet) {
			
//			String listOfService = 
			// String offerIdList = this.propertiesMappings.getProperty(str);
//			System.out.println(" evaluate : offerIdList =" + offerIdList);
			if (offerIdList != null && offerIdList.equals(SegmentConstants.SPACE)) {
				offerIdArray = offerIdList.split(SegmentConstants.SPLITER_PIPE);
				offerId = offerIdArray[1] != null ? offerIdArray[1] : null;
				System.out.println(" evaluateMagenta : offerId =" + offerId);
				if (expiryOfferIdMap.get(offerId) != null) {
					String expiryTimeUtc = changeToUTC(expiryOfferIdMap.get(offerId));
					if (expiryTimeUtc != null) {
						expiryTimeUtcResult = expiryTimeUtcResult + expiryTimeUtc;
					} else {
						expiryTimeUtcResult = expiryTimeUtcResult + SegmentConstants.EMPTY;
					}
					System.out.println(" evaluateMagenta : expirytimeUtcresult =" + expiryTimeUtcResult);
				}
			}
		}
		return expiryTimeUtcResult;
	}

	public static String changeToUTC(String expiryDate) {
		String session = expiryDate.substring(9, 11);
		int intSession = Integer.parseInt(session);
		if (intSession < 12) {
			session = SegmentConstants.AM_TIME;
		} else {
			session = SegmentConstants.PM_TIME;
		}
		return expiryDate.substring(4, 6) + SegmentConstants.FRONT_SLASH + expiryDate.substring(6, 8)
				+ SegmentConstants.FRONT_SLASH + expiryDate.substring(0, 4) + "  " + expiryDate.substring(9, 17) + " "
				+ session;
	}

	public String getExpectedUT(HashSet<String> producIdSet, Map<String, String> utMap) {
		String result = null;
		for (String product : producIdSet) {
			if (result != null && !result.isEmpty()) {
				result = result + SegmentConstants.SPLITER_PIPE + utMap.get(product) != null ? utMap.get(product)
						: SegmentConstants.SPACE;
			} else {
				result = utMap.get(product) != null ? utMap.get(product) : SegmentConstants.SPACE;

			}
		}
		return result;
	}

	public String fetchPAMData(String billCyclePeriod, Map<String, String> pamDataMap) {

		String result = null;
		if (billCyclePeriod == null || billCyclePeriod.equals(SegmentConstants.SPACE)) {
			billCyclePeriod = SegmentConstants.EMPTY_STRING;
		}
		result = pamDataMap.get(billCyclePeriod);
		System.out.println(result);
		return result;
	}

	public static boolean checkAirResponse(Document updateRespDADoc) {

		boolean check = true;
		NodeList airRespNodeList = updateRespDADoc.getElementsByTagName(SegmentConstants.MEMBER);
		// String ThresholdID = null;
		for (int k = 0; k < airRespNodeList.getLength(); k++) {

			Node napNoderes = airRespNodeList.item(k);
			if (napNoderes.getNodeType() == Node.ELEMENT_NODE) {
				Element eElementres = (Element) napNoderes;
				eElementres.getElementsByTagName(SegmentConstants.NAME).item(0).getTextContent();
				String name = eElementres.getElementsByTagName(SegmentConstants.NAME).item(0).getTextContent();
				if (name.equalsIgnoreCase(SegmentConstants.FAULT_CODE)) {
					return false;
				}
			}
		}
		return check;
	}

}
