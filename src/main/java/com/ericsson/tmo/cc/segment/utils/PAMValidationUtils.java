package com.ericsson.tmo.cc.segment.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.commons.NapDataPojo;
import com.ericsson.tmo.cc.segment.dao.service.ServiceExpireMappingServiceImpl;
import com.ericsson.tmo.cc.segment.interacton.ECCGWNodeInteractionHelper;
import com.ericsson.tmo.cc.segment.interacton.NodeRequestConstants;
import com.ericsson.tmo.cc.segment.interacton.SDPNodeInteractionHelper;

public class PAMValidationUtils {
	private static final Logger logger = LoggerFactory.getLogger(PAMValidationUtils.class);

	public static String updatePam(String msisdn, String[] schedulelistSDP, Map<String, String> startTimeOfferIdMap,
			Set<String> serviceSet, Set<String> pamServiceIdset, NapDataPojo napDataPojo,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		String reponse = null;
		String startDateTime = "";
		String offerMonth = "";
		List<String> offerIDSet = fetchSetOfExpiryOfferId(serviceSet);
		offerIDSet.remove("240050");
		offerIDSet.remove("240051");
		offerIDSet.remove("240052");
		offerIDSet.remove("240053");
		offerIDSet.remove("240054");
		offerIDSet.remove("251100");
		

		for (String offer : offerIDSet) {
			startDateTime = startTimeOfferIdMap.get(offer);
		}

		if (startDateTime != null && startDateTime.length() >= 9) {
			offerMonth = startDateTime.substring(6, 8);
		}
		if (pamServiceIdset.contains(SegmentConstants.PAM_SERVICEID_VALUE_10)
				&& pamServiceIdset.contains(SegmentConstants.PAM_SERVICEID_VALUE_11)) {

			if (napDataPojo.getBillCyclePeriod() == null || napDataPojo.getBillCyclePeriod().isEmpty()) {
				napDataPojo.setBillCyclePeriod(offerMonth);
				String monthScheduleID = SegmentConstants.DEFAUT_PAM_SCHEDUE_VALUE + offerMonth;

				if (schedulelistSDP != null && schedulelistSDP.length > 0) {

					if (!schedulelistSDP[0].contains(monthScheduleID)
							&& !schedulelistSDP[1].contains(monthScheduleID)) {
						reponse = ECCGWNodeInteractionHelper.callUpdatePAMECC(msisdn,
								configPropertiesPojo.getReportName(), napDataPojo, configPropertiesPojo);
					}
				}

			} else {
				reponse = ECCGWNodeInteractionHelper.callUpdatePAMECC(msisdn, configPropertiesPojo.getReportName(),
						napDataPojo, configPropertiesPojo);
			}
		} else {
			reponse = addPAMRequest(msisdn, napDataPojo, configPropertiesPojo, offerMonth, pamServiceIdset);
		}
		return reponse;
	}

	private static List<String> fetchSetOfExpiryOfferId(Set<String> serviceSet) {

		logger.info("calling UT update for services :getOfferIDSet() ");
		List<String> offerList = new ArrayList<>();
		try {
			String serviceList = SegmentUtils.combineListOfService(serviceSet);
			offerList = ServiceExpireMappingServiceImpl.fetchExpiryOfferIdByServiceName(serviceList);
		} catch (Exception e) {
			logger.debug("ERROR" + e);
		}
		return offerList;
	}

	private static String addPAMRequest(String msisdn, NapDataPojo napDataPojo,
			ConfigPropertiesPojo configPropertiesPojo, String offerMonth, Set<String> pamServiceIDset) {

		logger.info("calling update PAM");
		String responseBody = null;
		String scheduleId1 = SegmentConstants.DEFAUT_PAM_SCHEDUE_VALUE + offerMonth;

		if (napDataPojo.getBillCyclePeriod() == null || napDataPojo.getBillCyclePeriod().isEmpty()) {
			scheduleId1 = SegmentConstants.DEFAUT_PAM_SCHEDUE_VALUE + offerMonth;
		} else {
			if (napDataPojo.getBillCyclePeriod().length() == 2) {
				scheduleId1 = SegmentConstants.DEFAUT_PAM_SCHEDUE_VALUE + napDataPojo.getBillCyclePeriod();
			} else if (napDataPojo.getBillCyclePeriod().length() == 1) {
				scheduleId1 = SegmentConstants.PAM_SCHEDUE_VALUE_200 + napDataPojo.getBillCyclePeriod();
			}

		}
		String scheduleId2 = SegmentConstants.PAM_SCHEDUE_VALUE_2032;

		try {
			String payload = "";

			if (!pamServiceIDset.contains(SegmentConstants.PAM_SERVICEID_VALUE_10)) {
				payload = NodeRequestConstants.ADDPAM_REQUEST_PAYLOAD;
				payload = payload.replace(SegmentConstants.SCHEDULEID_VALUE, scheduleId1);

			} else if (!pamServiceIDset.contains(SegmentConstants.PAM_SERVICEID_VALUE_11)) {
				payload = NodeRequestConstants.ADDPAM_REQUEST_PAYLOAD;
				payload = payload.replace(SegmentConstants.SCHEDULEID_VALUE, scheduleId2);
			}

			payload = payload.replace(SegmentConstants.TRANS_DATETIME, GenericUtils.getOriginTimestamp());
			payload = payload.replace(SegmentConstants.TRANS_DATETIME, GenericUtils.fetchOriginTransactionId());

			responseBody = SDPNodeInteractionHelper.addPamDetails(payload, configPropertiesPojo);

			logger.info("ResponseBody" + responseBody);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return responseBody;
	}
}
