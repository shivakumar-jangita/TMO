package com.ericsson.tmo.cc.segment.rules;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.dao.service.SdpMappingServiceImpl;
import com.ericsson.tmo.cc.segment.dao.service.SdpUtMappingServiceImpl;
import com.ericsson.tmo.cc.segment.dao.service.SegmentPamMappingServiceImpl;
import com.ericsson.tmo.cc.segment.dao.service.ServiceExpireMappingServiceImpl;
import com.ericsson.tmo.cc.segment.utils.SegmentUtils;

/**
 * 
 * @author eimmnro
 *
 */
public class AttributesComparison {

	private static final Logger logger = LoggerFactory.getLogger(AttributesComparison.class);
	static Map<String, String> offerIdStatusCodeMap = new HashMap<String, String>(3);
	static SdpUtMappingServiceImpl sdpUtMappingServiceImpl= new SdpUtMappingServiceImpl();
	static {
		offerIdStatusCodeMap.put("A", "Not Exist");
		offerIdStatusCodeMap.put("S", "214000");
		offerIdStatusCodeMap.put("C", "214001");
	}

	public static boolean checkOfferId(Object objSrcA, Object objSrcB, String segmentName) {
		System.out.println("checkOfferID method is start here :");
		// setParameter(mapping);
		boolean result = false;
		System.out.println(segmentName);
		System.out.println("Comparing... ObjA = " + objSrcA + "; ObjB = " + objSrcB);
		if (segmentName == null) {
			System.out.println("No \"segment=...\" property found in the property file.");
			System.out.println("Running default evaluation.");
			result = compareServiceOfferid(objSrcA, objSrcB);
		} else {
			switch (segmentName.toUpperCase()) {
			case SegmentConstants.METROPC_SEGMENT:
				System.out.println("Running evaluation for segment \"" + segmentName + "\".");
				result = compareServiceOfferid(objSrcA, objSrcB);
				break;
			}
		}
		System.out.println("checkOfferID method is end here :" + result);
		return result;
	}

	/**
	 * 
	 * @param objSrcA
	 * @param objSrcB
	 * @return
	 */
	public static boolean compareServiceOfferid(Object objSrcA, Object objSrcB) {
		System.out.println("compareServiceOfferid method is start here :");
		System.out.println("Comparing... ObjA=" + objSrcA + "; ObjB=" + objSrcB);
		boolean result = true;
		Map<String, String> offerIdMap = new HashMap<>();
		String leftValue = null;
		String rightValue = null;
		if (objSrcA == null && objSrcB == null) {
			result = true;
		} else if (objSrcA == null) {
			result = false;
		} else if (objSrcB == null) {
			result = false;
		} else {
			if (objSrcA.equals("")) {
				objSrcA = SegmentConstants.NODATA_PLAN;
			}

			if (objSrcA instanceof String && objSrcB instanceof String) {
				leftValue = (String) objSrcA;
				rightValue = (String) objSrcB;
			}
			if (leftValue.length() == 0 && rightValue.length() == 0) {
				// Probably Empty String
				System.out.println("Coming Here");
				return true;
			}
			String[] leftItems = leftValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			String[] rightItems = rightValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			fetchOfferidConfiguration(leftItems, offerIdMap);
			List<String> rightItemList = Arrays.asList(rightItems);
			for (int i = 0; i < leftItems.length; i++) {
				// String item = (String) objectMappings.get(leftitems[i]);
				String item = offerIdMap.get(leftItems[i]);
				if (item == null) {
					return false;
				}

				String[] entries = item.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
				for (int j = 0; j < entries.length; j++) {
					if (!rightItemList.contains(entries[j])) {
						System.out.println("B does not contains " + entries[j]);
						return false;
					}
				}
			}
		}
		System.out.println("compareServiceOfferid method is end here :" + result);
		return result;
	}

	/**
	 * 
	 * @param leftitems
	 * @param offerid_map
	 */
	private static void fetchOfferidConfiguration(String[] napServices, Map<String, String> offerIdMap) {

		System.out.println("fetchOfferidConfiguration method is end here :");
		String offerId = "";
		for (String serviceName : napServices) {
			List<String> offerIdList = SdpMappingServiceImpl.fetchListOfOfferIdByServiceName(serviceName);
			for (String offId : offerIdList) {
				offerId = offerId + SegmentConstants.SPLITER_PIPE + offId;
			}
			System.out.println("fetchOfferidConfiguration:End::" + serviceName + "offerid:::" + offerId);

			offerIdMap.put(serviceName, offerId);
		}
	}

	public static boolean checkProductID(Object objSrcA, Object objSrcB) {
		System.out.println("checkProductID method is start here :");
		System.out.println("Comparing... ObjA=" + objSrcA + "; ObjB=" + objSrcB);
		boolean result = false;
		String leftValue = null;
		String rightValue = null;
		if (objSrcA == null && objSrcB == null) {
			result = true;
		} else if (objSrcA == null) {
			result = false;
		} else if (objSrcB == null) {
			result = false;
		} else {
			if (objSrcA.equals("")) {
				objSrcA = SegmentConstants.NODATA_PLAN;
			}
			if (objSrcA instanceof String && objSrcB instanceof String) {
				leftValue = (String) objSrcA;
				rightValue = (String) objSrcB;
			}
			String[] leftitems = leftValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			String[] rightitems = rightValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			List<String> rightItemList = Arrays.asList(rightitems);
			List<String> leftItemList = Arrays.asList(leftitems);
			if (leftitems.length == 2 && rightitems.length == 2) {
				if (leftItemList.contains("MPCS-TRUNL") && leftItemList.contains("MPCS-TETHER")) {
					if (rightItemList.contains("MPCS-TRUNL") && rightItemList.contains("MPCS-TET-UN")) {
						return true;
					}
				}
			}
			int totalItemsFound = 0;
			HashSet<String> rightItemSet = new HashSet<>(rightItemList);
			for (int i = 0; i < leftitems.length; i++) {

				if (rightItemSet.contains(leftitems[i])) {
					totalItemsFound++;
				} else {
					System.out.println("B does not contains " + leftitems[i]);
					return false;
				}
			}
			if (totalItemsFound == leftitems.length && totalItemsFound == rightItemSet.size()) {
				result = true;
			} else {
				System.out.println("left dose not equal to right");
				result = false;
			}

		}
		System.out.println("checkProductID method is end here :" + result);
		return result;
	}

	public static boolean checkExpiryDate(Object objSrcA, Object objSrcB, String segmentName) {
		System.out.println("checkExpiryDate method is start here :");
		boolean result = false;
		System.out.println("Comparing... ObjA = " + objSrcA + "; ObjB = " + objSrcB);
		if (segmentName == null) {
			System.out.println("No \"segment=...\" property found in the property file.");
			System.out.println("Running default evaluation.");
			result = compareExpiryDateMetroPC(objSrcA, objSrcB);
		} else {
			switch (segmentName.toUpperCase()) {
			case SegmentConstants.METROPC_SEGMENT:
				System.out.println("Running evaluation for segment \"" + segmentName + "\".");
				result = compareExpiryDateMetroPC(objSrcA, objSrcB);
				break;
			}
		}
		System.out.println("checkExpiryDate method is end here :" + result);
		return result;
	}

	private static Map<Object, Object> formKeyValuePairData(String[] items, String source) {

		Map<Object, Object> itemsMap = new HashMap<Object, Object>();
		for (String item : items) {
			if (item != null) {
				String[] kvPair = item.split("\\^") != null ? item.split("\\^") : null;
				if (kvPair != null && kvPair.length == 2) {
					String key = kvPair[0];
					String value = kvPair[1];
					itemsMap.put(key, value);
				} else {
					System.out.println("Source " + source + " Wrong offerid and Expiry date Pair = " + kvPair);
				}
			} else {
				System.out.println("Source " + source + "  Wrong Entry = " + item);
			}
		}
		return itemsMap;
	}

	public static boolean compareExpiryDateMetroPC(Object objSrcA, Object objSrcB) {
		System.out.println("compareExpiryDateMetroPC method is start here :");
		System.out.println("Comparing... ObjA=" + objSrcA + "; ObjB=" + objSrcB);
		boolean result = false;
		String leftValue = null;
		String rightValue = null;
		if (objSrcA == null && objSrcB == null) {
			result = true;
		} else if (objSrcA == null) {
			result = false;
		} else if (objSrcB == null) {
			result = false;
		} else {
			if (objSrcA.equals("")) {
				objSrcA = SegmentConstants.NODATA_PLAN_NEVER;
			}
			if (objSrcA instanceof String && objSrcB instanceof String) {
				leftValue = (String) objSrcA;
				rightValue = (String) objSrcB;
			}
			if (leftValue.length() == 0 && rightValue.length() == 0) {
				// Probably Empty String
				System.out.println("Coming Here");
				return true;
			}
			String[] leftItems = leftValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			Map<Object, Object> leftMap = formKeyValuePairData(leftItems, "A");

			String[] rightItems = rightValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			Map<Object, Object> rightMap = formKeyValuePairData(rightItems, "B");

			Set<Entry<Object, Object>> set = leftMap.entrySet();
			Iterator<Entry<Object, Object>> iterator = set.iterator();
			while (iterator.hasNext()) {
				Entry<?, ?> entryA = (Entry<?, ?>) iterator.next();
				String keyA = (String) entryA.getKey();
				String valueA = (String) entryA.getValue();
				String offerID = ServiceExpireMappingServiceImpl.findSigleExpiryOfferIdByService(keyA);

				if (rightMap.containsKey(offerID)) {
					if (offerID.equalsIgnoreCase("240054")) {
						result = true;
					}
					String valueB = (String) rightMap.get(offerID);
					if (valueA != null && valueB != null) {
						if (valueA.equals(valueB)) {
							result = true;
						} else {
							System.out.println("Source AB not equal valueA and valueB = " + valueA + ", " + valueB);
							return false;
						}
					} else {
						System.out.println("Source AB Wrong valueA and valueB = " + valueA + ", " + valueB);
						return false;
					}
				} else {
					if (valueA.equals("99991231")) {
						result = true;
					} else {
						System.out.println("Source B not contain the offerID=" + offerID);
						return false;
					}
				}
			}
		}
		return result;
	}

	public static boolean checkStatusCode(Object objSrcA, Object objSrcB, String segmentName) {
		System.out.println("checkStatusCode method is start here :");
		boolean result = false;
		System.out.println("Comparing... ObjA = " + objSrcA + "; ObjB = " + objSrcB);
		if (segmentName == null) {
			System.out.println("No \"segment=...\" property found in the property file.");
			System.out.println("Running default evaluation.");
			result = compareStatusCode(objSrcA, objSrcB);
		} else {
			switch (segmentName.toUpperCase()) {
			case SegmentConstants.METROPC_SEGMENT:
				System.out.println("Running evaluation for segment \"" + segmentName + "\".");
				result = compareStatusCode(objSrcA, objSrcB);
				break;
			}
		}
		return result;
	}

	public static boolean compareStatusCode(Object objSrcA, Object objSrcB) {
		System.out.println("compareStatusCode method is start here :");
		boolean result = false;
		String leftValue = null;
		String rightValue = null;
		if (objSrcA == null && objSrcB == null) {
			result = true;
		} else if (objSrcB == null) {
			result = false;
		} else if (objSrcA == null) {
			result = false;
		} else {

			if (objSrcA instanceof String && objSrcB instanceof String) {
				leftValue = (String) objSrcA;
				rightValue = (String) objSrcB;
			}
			if (leftValue.length() == 0 && rightValue.length() == 0) {
				System.out.println("Coming Here");
				return true;
			}
			String[] rightItems = rightValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			List<String> rightItemList = new LinkedList<String>(Arrays.asList((rightItems)));

			Object value = offerIdStatusCodeMap.get(leftValue);
			if (rightItemList.contains(value)) {
				return true;
			}
			if (value != null && value.equals(SegmentConstants.NAPSTATUS_NOTEXIST)) {
				if (!rightItemList.contains("214001") && !rightItemList.contains("214000")) {
					System.out.println("compareStatusCode method rightItemList :" + result);
					return true;
				} else {
					return false;
				}
			} 
		}
		System.out.println("compareStatusCode method is end here :" + result);
		return result;
	}

	public static boolean checkUTID(Object objSrcA, Object objSrcB, String segmentName) {
		System.out.println("checkUTID method is start here :");
		boolean result = false;
		System.out.println("Comparing... ObjA = " + objSrcA + "; ObjB = " + objSrcB);
		if (segmentName == null) {
			System.out.println("No \"segment=...\" property found in the property file.");
			System.out.println("Running default evaluation.");
			result = compareUTID(objSrcA, objSrcB);
		} else {
			switch (segmentName.toUpperCase()) {
			case SegmentConstants.METROPC_SEGMENT:
				System.out.println("Running evaluation for segment \"" + segmentName + "\".");
				result = compareUTID(objSrcA, objSrcB);
				break;
			}
		}

		return result;
	}

	public static boolean compareUTID(Object objSrcA, Object objSrcB) {
		System.out.println("compareUTID method is start here :");
		System.out.println("Comparing... ObjA=" + objSrcA + "; ObjB=" + objSrcB);
		boolean result = true;
		Set<String> leftValue = null;
		String rightValue = null;
		if (objSrcA == null && objSrcB == null) {
			result = true;
		} else if (objSrcA == null) {
			result = false;
		} else if (objSrcB == null) {
			result = false;
		} else {
			if (objSrcA.equals("")) {
				objSrcA = SegmentConstants.NODATA_PLAN;
			}
			
			
				leftValue = (Set<String>) objSrcA;
				rightValue = (String) objSrcB;
			
			if (leftValue.size() == 0 && rightValue.length() == 0) {
			
				System.out.println("Coming Here"+leftValue.size()+"rightValue.length()"+rightValue.length());
				return true;
			
			}
			String[] rightitems = rightValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			List<String> rightItemList = Arrays.asList(rightitems);

			String listOfServices = SegmentUtils.combineListOfService(leftValue);
			List<String> utIdList = listOfServices != null
					? SdpUtMappingServiceImpl.fetchListOfUtIdByServiceName(listOfServices) : null;

			for (String utId : utIdList) {
				if (!rightItemList.contains(utId)) {
					System.out.println("B does not contains " + utId);
					return false;
				}
			}
			
		}
		System.out.println("result---"+result);
		return result;
	}

	/*
	 * else if (!AttributesComparison.checkBillCyclePeriod(billcyclePeriod,
	 * allscheduleID, "mapping_pamMetroPC_onlineComp.properties")) {
	 */

	public static boolean checkBillCyclePeriod(Object objSrcA, Object objSrcB, String segmentName) {
		boolean result = false;
		System.out.println("Comparing... ObjA = " + objSrcA + "; ObjB = " + objSrcB);
		if (segmentName == null) {
			System.out.println("No \"segment=...\" property found in the property file.");
			System.out.println("Running default evaluation.");
			result = compareBillCyclePeriod(objSrcA, objSrcB, segmentName);
		} else {
			switch (segmentName.toUpperCase()) {
			case SegmentConstants.METROPC_SEGMENT:
				System.out.println("Running evaluation for segment \"" + segmentName + "\".");
				result = compareBillCyclePeriod(objSrcA, objSrcB, segmentName);
				break;
			}
		}
		return result;
	}

	public static boolean compareBillCyclePeriod(Object objSrcA, Object objSrcB, String segmentName) {
		System.out.println("Comparing... ObjA = " + objSrcA + "; ObjB = " + objSrcB);
		boolean result = true;
		String leftValue = null;
		String rightValue = null;
		if (objSrcA == null && objSrcB == null) {
			result = true;
		} else if (objSrcB == null) {
			result = false;
		} else {
			if (objSrcA == null) {
				objSrcA = SegmentConstants.EMPTY_STRING;
			}
			if (objSrcA.equals("")) {
				objSrcA = SegmentConstants.EMPTY_STRING;
			}
			
			if (objSrcA instanceof String && objSrcB instanceof String) {
				leftValue = (String) objSrcA;
				rightValue = (String) objSrcB;
			}
			if (leftValue.length() == 0 && rightValue.length() == 0) {
				System.out.println("Coming Here");
				return true;
			}

			String value = SegmentPamMappingServiceImpl.fetchPamScheduleByBillCycle(segmentName, leftValue);
			String[] leftitems = new String[10];
			if (value != null) {
				leftitems = value.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			}
			String[] rightitems = rightValue.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
			List<String> rightItemList = Arrays.asList(rightitems);
			int totalItemsFound = 0;
			for (int i = 0; i < leftitems.length; i++) {
				if (leftitems[i] != null && leftitems[i].equalsIgnoreCase("20**")) {
					for (Integer j = 2001; j < 2032; j++) {
						if (rightItemList.contains(j.toString())) {
							totalItemsFound++;
							break;
						}
					}
				} else if (!rightItemList.contains(leftitems[i])) {
					System.out.println("B does not contains " + leftitems[i]);
					return false;
				} else {
					totalItemsFound++;
				}
			}
			if (totalItemsFound == leftitems.length && totalItemsFound == rightitems.length) {
				result = true;
			} else {
				System.out.println("Left dose not equal to Right");
				result = false;
			}
		}
		System.out.println(result);
		return result;
	}

}
