package com.ericsson.tmo.cc.segment.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.commons.NapDataPojo;
import com.ericsson.tmo.cc.segment.dao.service.SdpUtMappingServiceImpl;
import com.ericsson.tmo.cc.segment.interacton.NodeRequestConstants;
import com.ericsson.tmo.cc.segment.interacton.SDPNodeInteractionHelper;

public class AirThresholdVerificationUtil {
	private static final Logger logger = LoggerFactory.getLogger(AirThresholdVerificationUtil.class);

	private static Map<String, String> fetchListOfUtIdWithValues(Set<String> serviceSet, String usageLimit) {
		Map<String, String> utIdMap = new HashMap<>();

		for (String service : serviceSet) {
			if (service.equalsIgnoreCase(SegmentConstants.SERVICE_MPCSTOPUP)) {
				// String MPCS
				// MPCS-TOPUP=500014|0.9*UsageLimit,500016|UsageLimit
				Integer usageLimitValue = null;
				if (usageLimit == null || usageLimit.isEmpty()) {
					usageLimitValue = 0;
				} else {
					usageLimitValue = Integer.parseInt(usageLimit);
				}
				int value1 = (int) Math.ceil((usageLimitValue * 0.9) * 1024) * 1024;
				int value2 = (int) Math.ceil((usageLimitValue * 1) * 1024) * 1024;

				utIdMap.put(SegmentConstants.METROPC_UTID_1, Integer.toString(value1));
				utIdMap.put(SegmentConstants.METROPC_UTID_2, Integer.toString(value2));
				// NODATAPLAN=100116|41943040,100411|999999999999999,100815|10485760

			} else {
				String utIdWithValues = SdpUtMappingServiceImpl.fetchListOfServSdpUtMappingByService(service);
				if (utIdWithValues != null) {
					String[] utIdWithValuesArray = utIdWithValues.split(SegmentConstants.SIGN_COMA);
					for (String utId : utIdWithValuesArray) {
						String[] utIdArray = utId.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);

						if (utIdMap.get(utIdArray[0]) == null) {
							utIdMap.put(utIdArray[0], utIdArray[1]);
						} else {
							Integer value1 = Integer.parseInt(utIdMap.get(utIdArray[0]));
							Integer value2 = Integer.parseInt(utIdArray[1]);
							Integer combineValue = value1 + value2;
							utIdMap.put(utIdArray[0], combineValue.toString());
						}
					}
				}
			}
		}
		return utIdMap;
	}

	public static String updateThreshold(Map<String, Object> updateThresholdReqInputMap, NapDataPojo napDataPojo,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		String responseBody = null;
		StringBuilder payload = new StringBuilder();
		List<String> idForUpdate = new ArrayList<>();
		String msisdn = (String) updateThresholdReqInputMap.get(SegmentConstants.MSISDN);
		String usageLimit = napDataPojo.getUsageLimit();
		List<String> thresholdIdList = (List<String>) updateThresholdReqInputMap.get(SegmentConstants.THRESHOLDID_ARRAY);
		List<String> airNodeUsageIdList = (List<String>) updateThresholdReqInputMap
				.get(SegmentConstants.SDPUSAGEID_LIST);
		Map<String, String> thresholdSourceMap = (Map<String, String>) updateThresholdReqInputMap
				.get(SegmentConstants.THRESHOLD_SOURCEMAP);
		Set<String> serviceSet = (Set<String>) updateThresholdReqInputMap.get(SegmentConstants.SERVICE_SET);
		Map<String, String> utIdMap = fetchListOfUtIdWithValues(serviceSet, usageLimit);

		for (String mappingId : thresholdIdList) {

			if (!airNodeUsageIdList.contains(mappingId)) {
				idForUpdate.add(mappingId);
			}
		}
		String allMetroUtIds = SdpUtMappingServiceImpl
				.fetchAllThresholdBySegmentName(configPropertiesPojo.getSegmentName());
		List<String> idFordelete = new ArrayList<>();
		StringBuilder payloadDelete = new StringBuilder();
		String[] allMetroUTArray = allMetroUtIds.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
		List<String> allMetroUTArrayLIST = Arrays.asList(allMetroUTArray);
		for (String utAIR : airNodeUsageIdList) {
			if (!thresholdIdList.contains(utAIR)) {
				if (allMetroUTArrayLIST.contains(utAIR)) {
					String sourceValue = thresholdSourceMap.get(utAIR);
					if (sourceValue != null && !sourceValue.equalsIgnoreCase("3")) {
						idFordelete.add(utAIR);
					}
				}
			}
		}

		if (idFordelete.size() >= 1) {
			payloadDelete.append(NodeRequestConstants.DELETE_USAGETHRESHOLDS_PAY_LOAD_START);
			for (String deleteID : idFordelete) {
				payloadDelete.append(NodeRequestConstants.DELETE_USAGETHRESHOLDS_PAY_LOAD_APPEND_PART1);
				payloadDelete.append(deleteID + NodeRequestConstants.DELETE_USAGETHRESHOLDS_PAY_LOAD_APPEND_PART2);
			}

			payloadDelete.append(NodeRequestConstants.DELETE_USAGETHRESHOLDS_PAY_LOAD_END);
		}
		if (payloadDelete != null) {
			SDPNodeInteractionHelper.callDeleteUTDetails(payloadDelete.toString(), configPropertiesPojo);
		}

		logger.info("UT  for update");
		if (idForUpdate.size() >= 1) {

			payload.append(NodeRequestConstants.UPDATE_USAGETHRESHOLDS_PAY_LOAD_START);
			String updateIDValue = "";
			for (String updateID : idForUpdate) {
				updateIDValue = utIdMap.get(updateID);
				if (updateIDValue == null || updateIDValue.isEmpty()) {
					updateIDValue = SegmentConstants.DEFAULT_UTID_VALUE;
				}
				payload.append(NodeRequestConstants.UPDATE_USAGETHRESHOLDS_PAYLOAD_APPEND_PART1);
				payload.append(updateID);
				payload.append(NodeRequestConstants.UPDATE_USAGETHRESHOLDS_PAYLOAD_APPEND_PART2);
				payload.append(updateIDValue);
				payload.append(NodeRequestConstants.UPDATE_USAGETHRESHOLDS_PAYLOAD_APPEND_PART2);
			}
			payload.append(NodeRequestConstants.UPDATE_USAGETHRESHOLDS_PAY_LOAD_END);

			responseBody = SDPNodeInteractionHelper.callUpdateUTDetails(msisdn, payload.toString(),
					configPropertiesPojo);
		}
		return responseBody;
	}

}
