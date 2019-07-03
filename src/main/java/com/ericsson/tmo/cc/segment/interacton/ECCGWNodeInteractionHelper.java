package com.ericsson.tmo.cc.segment.interacton;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.commons.NapDataPojo;
import com.ericsson.tmo.cc.segment.dao.service.ServiceExpireMappingServiceImpl;
import com.ericsson.tmo.cc.segment.utils.GenericUtils;
import com.ericsson.tmo.cc.segment.utils.SegmentUtils;

public class ECCGWNodeInteractionHelper {

	public static ECCGWNodeInteraction eccNodeInteraction = new ECCGWNodeInteraction();

	private static final Logger logger = LoggerFactory.getLogger(ECCGWNodeInteractionHelper.class);

	public static String updateProductIDCC(Set<String> setOfServices, Map<String, String> serviceMap, String createReq,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		logger.info("Controll entered in ECCGWNodeInteractionHelper:updateProductIDCC() -- starts here ");
		String response = null;
		String xmlString = null;
		if (setOfServices.contains(SegmentConstants.MPCS_TRUNL)
				&& setOfServices.contains(SegmentConstants.MPCS_TETHER)) {
			setOfServices.remove(SegmentConstants.MPCS_TETHER);
			setOfServices.add(SegmentConstants.MPCS_TET_UN);
			serviceMap.put(SegmentConstants.MPCS_TET_UN, serviceMap.get(SegmentConstants.MPCS_TETHER));
		}

		createReq = createReq.replace(NodeRequestConstants.REQUEST_ACTION_START,
				NodeRequestConstants.REQUEST_ACTION_END);
		xmlString = new StringBuffer().append(NodeRequestConstants.XML_HEADER).append(createReq).toString();
		logger.info(xmlString);

		String timestamp = GenericUtils.getTimestamp();
		logger.info("timestamp is: " + timestamp);
		response = eccNodeInteraction.queryRequest(createReq, configPropertiesPojo);

		logger.info("Controll entered in ECCGWNodeInteractionHelper:updateProductIDCC() -- starts here ");
		return response;

	}

	public static String updatExpiryDateCC(String msisdn, Map<String, String> serviceMap, Set<String> serviceset,
			Map<String, String> expiryOfferIdMap, String createReq, ConfigPropertiesPojo configPropertiesPojo)
					throws Exception {

		String response = null;
		String expirydateNAP = null;
		String expirydateSDP = null;
		boolean expiryEnabledFlag = false;
		List<String> listOfExpiryOfferId = null;
		
		Map<String, String> expiryDateSet = expiryOfferIdMap != null ? expiryOfferIdMap : null;
		String listOfService = SegmentUtils.combineListOfService(serviceset);
		if(listOfService != null)
			listOfExpiryOfferId = ServiceExpireMappingServiceImpl.fetchExpiryOfferIdByServiceName(listOfService);
		
		if(listOfExpiryOfferId != null && listOfExpiryOfferId.size() > 0){
			listOfExpiryOfferId.remove("251100");
		}
		if (listOfExpiryOfferId != null && expiryDateSet != null) {
			for (String service : serviceset) {
				expirydateNAP = serviceMap.get(service);
				for (String offer : listOfExpiryOfferId) {
					if (expiryDateSet.get(offer) != null && !expiryDateSet.get(offer).isEmpty()) {
						expirydateSDP = expiryDateSet != null ? expiryDateSet.get(offer) : null;

						if (expirydateNAP != null && expirydateSDP != null) {
							expiryEnabledFlag = GenericUtils.compareDate(expirydateNAP, expirydateSDP);
							break;
						} else {
							expiryEnabledFlag = true;
						}
					}
				}
			}
		}
		if (expiryEnabledFlag) {
			String xmlString = null;
			createReq = createReq.replace(NodeRequestConstants.REQUEST_ACTION_START,
					NodeRequestConstants.REQUEST_ACTION_END);
			xmlString = new StringBuffer().append(NodeRequestConstants.XML_HEADER).append(createReq).toString();
			logger.info(xmlString);

			response = eccNodeInteraction.queryRequest(createReq, configPropertiesPojo);
		}

		logger.info("response: " + response);
		return response;

	}

	public static String updateStatusCodeECC(String msisdn, NapDataPojo napDataPojo,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		String response = null;
		String updateStatusCodeECCReq = new StringBuffer().append(NodeRequestConstants.XML_HEADER)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.TRANSACTION_INFO_START).append(NodeRequestConstants.REQUEST_ACTION_UPDATE)
				.append(NodeRequestConstants.FIELD_MODIFIED_START).append(SegmentConstants.ACCOUNT_STATUS)
				.append(NodeRequestConstants.FIELD_MODIFIED_END)
				.append(NodeRequestConstants.CUSTOMER_ID_START + napDataPojo.getCustomerID()
						+ NodeRequestConstants.CUSTOMER_ID_END)
				.append(NodeRequestConstants.MSISDN_START + msisdn + NodeRequestConstants.MSISDN_END)
				.append(NodeRequestConstants.TRANSACTION_ID_START + SegmentConstants.ORIGINOPERATORID_DATA
						+ NodeRequestConstants.TRANSACTION_ID_END)
				.append(NodeRequestConstants.VENDOR_ID_START + napDataPojo.getVendorID()
						+ NodeRequestConstants.VENDOR_ID_END)
				.append(NodeRequestConstants.TRANSACTION_INFO_END).append(NodeRequestConstants.ACCOUNT_INFO_START)
				.append(NodeRequestConstants.STATUS_CODE_START + napDataPojo.getStatusCode()
						+ NodeRequestConstants.STATUS_CODE_END)
				.append(NodeRequestConstants.OPERATOR_ID_START + napDataPojo.getOperatorID()
						+ NodeRequestConstants.OPERATOR_ID_END)

		.append(NodeRequestConstants.ACCOUNT_INFO_END).append(NodeRequestConstants.CUSTOMER_TRANSACTION_END)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_END).toString();

		logger.info(updateStatusCodeECCReq);

		response = eccNodeInteraction.queryRequest(updateStatusCodeECCReq, configPropertiesPojo);

		return response;
	}

	public static String updateOfferIdOverECCGW(String createReq, ConfigPropertiesPojo configPropertiesPojo)
			throws Exception {

		String response = null;
		String updateOfferIdReq = createReq.replace(NodeRequestConstants.REQUEST_ACTION_START,
				NodeRequestConstants.REQUEST_ACTION_CREATE);
		String offerIdUpdateRequest = new StringBuffer().append(NodeRequestConstants.XML_HEADER)
				.append(updateOfferIdReq).toString();
		logger.info(offerIdUpdateRequest);
		response = eccNodeInteraction.queryRequest(offerIdUpdateRequest, configPropertiesPojo);

		logger.info("response: " + response);
		return response;
	}

	public static String callUpdatePAMECC(String msisdn, String report, NapDataPojo napDataPojo,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		String response = null;
		String callUpdatePAMECCRequest = new StringBuffer().append(NodeRequestConstants.XML_HEADER)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTIONS_START)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.TRANSACTION_INFO_START).append(NodeRequestConstants.REQUEST_ACTION_UPDATE)
				.append(NodeRequestConstants.FIELD_MODIFIED_START).append(SegmentConstants.BILL_CYCLE_CLOSEDAY)
				.append(NodeRequestConstants.FIELD_MODIFIED_END)
				.append(NodeRequestConstants.CUSTOMER_ID_START + napDataPojo.getCustomerID()
						+ NodeRequestConstants.CUSTOMER_ID_END)
				.append(NodeRequestConstants.MSISDN_START + msisdn + NodeRequestConstants.MSISDN_END)
				.append(NodeRequestConstants.TRANSACTION_ID_START + SegmentConstants.ORIGINOPERATORID_DATA
						+ NodeRequestConstants.TRANSACTION_ID_END)
				.append(NodeRequestConstants.VENDOR_ID_START + napDataPojo.getVendorID()
						+ NodeRequestConstants.VENDOR_ID_END)
				.append(NodeRequestConstants.TRANSACTION_INFO_END).append(NodeRequestConstants.ACCOUNT_INFO_START)
				.append(NodeRequestConstants.STATUS_CODE_START + napDataPojo.getStatusCode()
						+ NodeRequestConstants.STATUS_CODE_END)
				.append(NodeRequestConstants.OPERATOR_ID_START + napDataPojo.getOperatorID()
						+ NodeRequestConstants.OPERATOR_ID_END)

		.append(NodeRequestConstants.ACCOUNT_INFO_END).append(NodeRequestConstants.CUSTOMER_TRANSACTION_END)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_END).toString();

		logger.info(callUpdatePAMECCRequest);
		if (configPropertiesPojo.getVerify().equalsIgnoreCase(SegmentConstants.ENABLE)) {
			response = eccNodeInteraction.queryRequest(callUpdatePAMECCRequest, configPropertiesPojo);
		} else
			SegmentUtils.saveRequestPayload(callUpdatePAMECCRequest, report);
		logger.info("response: " + response);

		return response;
	}

	public static String createSDPSubscriptionOverECC(String msisdn, Set<String> setOfServices,
			Map<String, String> serviceMap, NapDataPojo napDataPojo, ConfigPropertiesPojo configPropertiesPojo)
					throws Exception {
		logger.info("createSDPSubscriptionOverECC--->starts");
		double index = Math.random();
		String origin = String.valueOf(index);
		String transactionID = origin.substring(2);
		String response = null;

		String xmlString = new StringBuffer().append(NodeRequestConstants.XML_HEADER)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.TRANSACTION_INFO_START).append(NodeRequestConstants.REQUEST_ACTION_CREATE)
				.append(NodeRequestConstants.MSISDN_START + msisdn + NodeRequestConstants.MSISDN_END)
				.append(NodeRequestConstants.CUSTOMER_ID_START + napDataPojo.getCustomerID()
						+ NodeRequestConstants.CUSTOMER_ID_END)

		.append(NodeRequestConstants.TRANSACTION_ID_START + transactionID + NodeRequestConstants.TRANSACTION_ID_END)
				.append(NodeRequestConstants.VENDOR_ID_START + napDataPojo.getVendorID()
						+ NodeRequestConstants.VENDOR_ID_END)
				.append(NodeRequestConstants.TRANSACTION_INFO_START).append(NodeRequestConstants.ACCOUNT_INFO_START)
				.append(NodeRequestConstants.CUSTOMER_TYPE_START + napDataPojo.getCustomerType()
						+ NodeRequestConstants.CUSTOMER_TYPE_END)
				.append(NodeRequestConstants.OPERATOR_ID_START + napDataPojo.getOperatorID()
						+ NodeRequestConstants.OPERATOR_ID_END)
				.append(NodeRequestConstants.IMSI_START + napDataPojo.getImsi() + NodeRequestConstants.IMSI_END)
				.toString();
		if (napDataPojo.getBillCyclePeriod() != null && !napDataPojo.getBillCyclePeriod().isEmpty()) {
			xmlString = new StringBuffer(xmlString)
					.append(NodeRequestConstants.BILLCYCLE_PERIOD_START + napDataPojo.getBillCyclePeriod()
							+ NodeRequestConstants.BILLCYCLE_PERIOD_END)
					.append(NodeRequestConstants.LANGUAGE_START + napDataPojo.getLanguage()
							+ NodeRequestConstants.LANGUAGE_END)
					.append(NodeRequestConstants.CUSTOMER_ID_START + napDataPojo.getCustomerID()
							+ NodeRequestConstants.CUSTOMER_ID_END)
					.append(NodeRequestConstants.MSISDN_START + msisdn + NodeRequestConstants.MSISDN_END)
					.append(NodeRequestConstants.STATUS_CODE_START + napDataPojo.getStatusCode()
							+ NodeRequestConstants.STATUS_CODE_END)
					.append(NodeRequestConstants.PAH_MSISDN_START + napDataPojo.getPahMSISDN()
							+ NodeRequestConstants.PAH_MSISDN_END)
					.append(NodeRequestConstants.ACCOUNT_INFO_END)
					.append(NodeRequestConstants.SERVICE_INFO).append(NodeRequestConstants.VENDOR_ID_START
							+ napDataPojo.getVendorID() + NodeRequestConstants.VENDOR_ID_END)
					.append(NodeRequestConstants.FEATURE_START).toString();
		} else {
			xmlString = new StringBuffer(xmlString)
					.append(NodeRequestConstants.LANGUAGE_START + napDataPojo.getLanguage()
							+ NodeRequestConstants.LANGUAGE_END)
					.append(NodeRequestConstants.CUSTOMER_ID_START + napDataPojo.getCustomerID()
							+ NodeRequestConstants.CUSTOMER_ID_END)
					.append(NodeRequestConstants.MSISDN_START + msisdn + NodeRequestConstants.MSISDN_END)
					.append(NodeRequestConstants.STATUS_CODE_START + napDataPojo.getStatusCode()
							+ NodeRequestConstants.STATUS_CODE_END)
					.append(NodeRequestConstants.PAH_MSISDN_START + napDataPojo.getPahMSISDN()
							+ NodeRequestConstants.PAH_MSISDN_END)
					.append(NodeRequestConstants.ACCOUNT_INFO_END)
					.append(NodeRequestConstants.SERVICE_INFO).append(NodeRequestConstants.VENDOR_ID_START
							+ napDataPojo.getVendorID() + NodeRequestConstants.VENDOR_ID_END)
					.append(NodeRequestConstants.FEATURE_START).toString();
		}
		for (String service : setOfServices) {
			String expirydate = serviceMap.get(setOfServices);
			if (expirydate != null && !expirydate.isEmpty()) {
				xmlString = new StringBuffer(xmlString).append(NodeRequestConstants.FEATURE_START)
						.append(NodeRequestConstants.NAME_START + service + NodeRequestConstants.NAME_END)
						.append(NodeRequestConstants.EXPIRATION_TIME_START + expirydate
								+ NodeRequestConstants.EXPIRATION_TIME_END)
						.append(NodeRequestConstants.FEATURE_END).toString();
			} else {
				xmlString = new StringBuffer(xmlString).append(NodeRequestConstants.FEATURE_START)
						.append(NodeRequestConstants.NAME_START + service + NodeRequestConstants.NAME_END)
						.append(NodeRequestConstants.FEATURE_END).toString();
			}

		}

		xmlString = new StringBuffer(xmlString).append(NodeRequestConstants.FEATURE_END)
				.append(NodeRequestConstants.SERVICE_INFO_END).append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_END).toString();

		logger.info("Create xmlString="+xmlString);
		response = eccNodeInteraction.createRequest(xmlString, configPropertiesPojo);
		logger.info("response: " + response);
		return response;

	}

	public static String deleteSdpSubscriptionOverECC(String msisdn, NapDataPojo napDataPojo,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		String response = null;

		String xmlString = new StringBuffer().append(NodeRequestConstants.XML_HEADER)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_START)
				.append(NodeRequestConstants.TRANSACTION_INFO_START).append(NodeRequestConstants.REQUEST_ACTION_DELETE)
				.append(NodeRequestConstants.CUSTOMER_ID_START + napDataPojo.getCustomerID()
						+ NodeRequestConstants.CUSTOMER_ID_END)
				.append(NodeRequestConstants.MSISDN_START + msisdn + NodeRequestConstants.MSISDN_END)
				.append(NodeRequestConstants.TRANSACTION_ID_START + NodeRequestConstants.ONE
						+ NodeRequestConstants.TRANSACTION_ID_END)
				.append(NodeRequestConstants.VENDOR_ID_START + NodeRequestConstants.VENDORID_VALUE
						+ NodeRequestConstants.VENDOR_ID_END)
				.append(NodeRequestConstants.TRANSACTION_INFO_END).append(NodeRequestConstants.ACCOUNT_INFO_START)
				.append(NodeRequestConstants.OPERATOR_ID_START + napDataPojo.getOperatorID()
						+ NodeRequestConstants.OPERATOR_ID_END)
				.append(NodeRequestConstants.ACCOUNT_INFO_END).append(NodeRequestConstants.CUSTOMER_TRANSACTION_END)
				.append(NodeRequestConstants.CUSTOMER_TRANSACTION_END).toString();

		logger.info("xmlString==="+xmlString);
		response = eccNodeInteraction.queryRequest(xmlString, configPropertiesPojo);
		logger.info("response=: " + response);
		return response;

	}

}
