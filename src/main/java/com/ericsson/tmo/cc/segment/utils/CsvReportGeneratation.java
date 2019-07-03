package com.ericsson.tmo.cc.segment.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.dao.service.SdpUtMappingServiceImpl;
import com.ericsson.tmo.cc.segment.dao.service.SegmentPamMappingServiceImpl;
import com.opencsv.CSVWriter;



public class CsvReportGeneratation {
	
	static String[] METROPC_REPORT_HEADER = new String[] { "MSISDN", "correctionStatus", "Southbound_Nodes", "Scenarios_Of_Mismatch","corrected_scenarios",
			"LCO_EXPECTED", "NAP_CUSTOMER_ID", "NAP_CUSTOMER_TYPE", "NAP_SUBSCRIBER_NAME", "NAP_STATUS_CODE",
			"NAP_BILL_CYCLE_PERIOD", "NAP_SERVICE", "NAP_SERVICE_EXPIRATION", "NAP_PairedMSISDN",
			"NAP_ACC_TYPE", "NAP_ACC_SUB_TYPE", "NAP_BAN", "SDP_CUSTOMER_ID", "SDP_CUSTOMER_TYPE",
			"SDP_OPERATOR_ID", "SDP_STATUS", "SDP_SCHEDULE_ID", "SDP_EXPECTED_PAM_SCHEDULE_ID",
			"SDP_PRODUCT_ID", "SDP_PRODUCT_EXPIRATION_UTC", "SDP_CONSUMER", "SDP_ACC_TYPE", "SDP_ACC_SUB_TYPE",
			"SDP_BAN", "SDP_DATA_DA_ID", "SDP_DATA_DA_UNIT_BALANCE", "SDP_DATA_DA_EXPIRATION", "EXPECTED_DA_ID",
			"SDP_UT_ID", "EXPECTED_UT_ID", "SDP_UT_VALUE" };
	
	
	
	public static void writeToCsv(List<String[]> listForCSV, String report) {
		
		String csv = SegmentConstants.REPORT_DESTINATION_DIRECTORY + report + SegmentConstants.CSV_FILE_EXTENSION;
		CSVWriter writer = null;
		try {
			writer = new CSVWriter(new FileWriter(csv));
			writer.writeNext(METROPC_REPORT_HEADER);
			writer.writeAll(listForCSV);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	private static String getCorrectionStatusString(List<String> correctionStatusNodeList, String correctionStatus) {
		if (correctionStatusNodeList != null) {
			if (correctionStatusNodeList.contains(SegmentConstants.SUCCESSFUL) && !correctionStatusNodeList.contains(SegmentConstants.FAILED)) {
				correctionStatus = SegmentConstants.SUCCESSFUL;
			} else if (correctionStatusNodeList.contains(SegmentConstants.FAILED) && !correctionStatusNodeList.contains(SegmentConstants.SUCCESSFUL)) {
				correctionStatus = SegmentConstants.FAILED;
			} else if (correctionStatusNodeList.contains(SegmentConstants.FAILED) && correctionStatusNodeList.contains(SegmentConstants.SUCCESSFUL)) {
				correctionStatus = SegmentConstants.PARTIAL;
			}
		}
		return correctionStatus;
	}

	private static String getSouthBondString(List<String> southBoundNodeList, String southboundNodes) {
		if (southBoundNodeList != null) {
			if (southBoundNodeList.contains(SegmentConstants.AIR_FAILURE)) {
				southboundNodes = SegmentConstants.AIR_FAILURE + SegmentConstants.SPLITER_PIPE;
			}
			if (southBoundNodeList.contains(SegmentConstants.ECCGW_FAILURE)) {
				southboundNodes = southboundNodes + SegmentConstants.ECCGW_FAILURE + SegmentConstants.SPLITER_PIPE;
			}
			if (southBoundNodeList.contains(SegmentConstants.ECCGW_SUCCESSFUL) && !southBoundNodeList.contains(SegmentConstants.ECCGW_FAILURE)) {
				southboundNodes = southboundNodes + SegmentConstants.ECCGW_SUCCESSFUL + SegmentConstants.SPLITER_PIPE;
			}
			if (southBoundNodeList.contains(SegmentConstants.AIR_SUCCESSFUL) && !southBoundNodeList.contains(SegmentConstants.AIR_FAILURE)) {
				southboundNodes = southboundNodes + SegmentConstants.AIR_SUCCESSFUL + SegmentConstants.SPLITER_PIPE;
			}
		}
		return southboundNodes;
	}

	
	public static String getExpectedPamScheduleId(String billCyclePeriod , String segmentName) {

		String result = null;
		if (billCyclePeriod == null) {
			billCyclePeriod = SegmentConstants.EMPTY_STRING;
		} else if (billCyclePeriod.equals("")) {
			billCyclePeriod = SegmentConstants.EMPTY_STRING;
		} else {
			result = SegmentPamMappingServiceImpl.fetchPamScheduleByBillCycle(segmentName, billCyclePeriod);
		}
		System.out.println("getExpectedPamScheduleId="+result);
		return result;
	}
	
	private static String getExpectedUtId(Set<String> producIdset){
		
		String listOfExpectedUtId = "";
		String  listOfServices = SegmentUtils.combineListOfService(producIdset);
		List<String> utIdList = listOfServices != null ?
				SdpUtMappingServiceImpl.fetchListOfUtIdByServiceName(listOfServices) :null;
				
		for(String utId : utIdList){
			if(listOfExpectedUtId != null && !listOfExpectedUtId.equals(""))
				listOfExpectedUtId = listOfExpectedUtId+SegmentConstants.SPLITER_PIPE+utId;
			else
				listOfExpectedUtId = utId;
		}
		return listOfExpectedUtId;
	}
	
	
	

	public static String[] consolidateDataCsvMetro(String mismatchmsisdn,
			List<String> correctionStatusList,
			List<String> southbound_nodes, String scenarios_Of_mismatch,
			String corrected_scenarios, Map<String, String> napAttributes,
			Set<String> napserviceSet,
			Map<String, String> napservicesMAp,
			Map<String, String> sdpAttributes, Set<String> producIdset,
			Map<String, String> expiryOfferidMap, String allofferId,
			String allscheduleID, String[] dADATA,
			List<String> sdpUsageIDLists, List<String> sdpUTValueList) {
		
		
		String correctionStatus = "";
		String Southbound_Nodes = "";
		String LCO_EXPECTED = "251100";
		String NAP_SERVICE = "";
		String NAP_SERVICE_EXPIRATION = "";
		String NAP_CUSTOMER_ID = "";
		String NAP_CUSTOMER_TYPE = "";
		String NAP_SUBSCRIBER_NAME = "";
		String NAP_STATUS_CODE = "";
		String NAP_BILL_CYCLE_PERIOD = "";
		if (napAttributes != null) {
			NAP_CUSTOMER_ID =(NAP_CUSTOMER_ID != null) ? napAttributes.get("CustomerID") : "";
			NAP_CUSTOMER_TYPE = (NAP_CUSTOMER_TYPE != null) ?napAttributes.get("CustomerType") : "";
			NAP_SUBSCRIBER_NAME = (NAP_SUBSCRIBER_NAME != null) ?napAttributes.get("OperatorID") : "";
			NAP_STATUS_CODE = (NAP_STATUS_CODE != null) ?napAttributes.get("StatusCode") : "";
			NAP_BILL_CYCLE_PERIOD = (NAP_BILL_CYCLE_PERIOD != null) ?napAttributes.get("BillCyclePeriod") : "";
		}
		String NAP_PairedMSISDN = "";
		String NAP_ACC_TYPE = "";
		String NAP_ACC_SUB_TYPE = "";
		String NAP_BAN = "";
		
		correctionStatus = getCorrectionStatusString(correctionStatusList, correctionStatus);
		Southbound_Nodes = getSouthBondString(southbound_nodes, Southbound_Nodes);
		String SDP_EXPECTED_PAM_SCHEDULE_ID = getExpectedPamScheduleId(NAP_BILL_CYCLE_PERIOD, null);
		
		if (napservicesMAp != null) {
			for (String service : napservicesMAp.keySet()) {
				NAP_SERVICE = NAP_SERVICE + service + SegmentConstants.SPLITER_PIPE;
				if (napservicesMAp.get(service) != null) {
					NAP_SERVICE_EXPIRATION = NAP_SERVICE_EXPIRATION + napservicesMAp.get(service) + SegmentConstants.SPLITER_PIPE;
				} else {
					NAP_SERVICE_EXPIRATION = NAP_SERVICE_EXPIRATION + "Never" + SegmentConstants.SPLITER_PIPE;
				}

			}
		}
		String SDP_CUSTOMER_ID = "";

		String SDP_CUSTOMER_TYPE = "";

		String SDP_OPERATOR_ID = "";

		if (sdpAttributes != null) {
			SDP_CUSTOMER_ID = sdpAttributes.get("CustomerID");

			SDP_CUSTOMER_TYPE = sdpAttributes.get("CustomerType");

			SDP_OPERATOR_ID = sdpAttributes.get("OperatorID");
		}

		String SDP_STATUS = "";
		if (allofferId != null) {
			List<String> offerIdList = new LinkedList(Arrays.asList(allofferId.split("\\|")));
			if (offerIdList.contains("214000")) {
				SDP_STATUS = "S";
			} else if (offerIdList.contains("214001")) {
				SDP_STATUS = "C";
			} else {
				SDP_STATUS = "A";
			}
		}
		String SDP_SCHEDULE_ID = allscheduleID;

		String SDP_PRODUCT_ID = "";
		String SDP_PRODUCT_EXPIRATION_UTC = "";
		String EXPECTED_UT_ID = "";
		if (producIdset != null && expiryOfferidMap != null) {
			for (String product : producIdset) {
				SDP_PRODUCT_ID = SDP_PRODUCT_ID + product + SegmentConstants.SPLITER_PIPE;
				System.out.println("expiryOfferidMap---->"+expiryOfferidMap);
			}
			expiryOfferidMap.remove("251100");
			expiryOfferidMap.remove("250100");
			Collection expiryDateTime=expiryOfferidMap.values();
			if(expiryDateTime !=null){
				String SDP_PRODUCT_EXPIRATION_UTC_TEMP = StringUtils.join(expiryDateTime, '|'); // "val1,val2"
				System.out.println("SDP_PRODUCT_EXPIRATION_UTC"+SDP_PRODUCT_EXPIRATION_UTC_TEMP);
				SDP_PRODUCT_EXPIRATION_UTC=SDP_PRODUCT_EXPIRATION_UTC_TEMP;
				EXPECTED_UT_ID = getExpectedUtId(producIdset);
			
				
		}	
		}

		String SDP_DATA_DA_ID = "";
		String SDP_DATA_DA_UNIT_BALANCE = "";
		String SDP_DATA_DA_EXPIRATION = "";
		String EXPECTED_DA_ID = "";

		
		String SDP_UT_ID = "";
		if (sdpUsageIDLists != null) {
			for (String utid : sdpUsageIDLists) {

				SDP_UT_ID = SDP_UT_ID + utid + SegmentConstants.SPLITER_PIPE;
			}
		}

		String SDP_UT_VALUE = "";
		if (sdpUTValueList != null) {
			for (String utvalue : sdpUTValueList) {
				SDP_UT_VALUE = SDP_UT_VALUE + utvalue + SegmentConstants.SPLITER_PIPE;
			}
		}

		String SDP_CONSUMER = "";
		String SDP_ACC_TYPE = "";
		String SDP_ACC_SUB_TYPE = "";
		String SDP_BAN = "";

		
		if (producIdset != null && expiryOfferidMap != null) {	
			expiryOfferidMap.remove("251100");
			expiryOfferidMap.remove("250100");
			Collection expiryDateTime=expiryOfferidMap.values();
			if(expiryDateTime !=null){
				String SDP_PRODUCT_EXPIRATION_UTC_TEMP = StringUtils.join(expiryDateTime, '|'); 
				System.out.println("SDP_PRODUCT_EXPIRATION_UTC="+SDP_PRODUCT_EXPIRATION_UTC_TEMP);
				SDP_PRODUCT_EXPIRATION_UTC=SDP_PRODUCT_EXPIRATION_UTC_TEMP;
					
			}
				
		
		}
		String[] allAttributes = new String[] { mismatchmsisdn, correctionStatus, Southbound_Nodes,
				scenarios_Of_mismatch,corrected_scenarios, LCO_EXPECTED, NAP_CUSTOMER_ID, NAP_CUSTOMER_TYPE, NAP_SUBSCRIBER_NAME,
				NAP_STATUS_CODE, NAP_BILL_CYCLE_PERIOD, NAP_SERVICE, NAP_SERVICE_EXPIRATION, NAP_PairedMSISDN,
				NAP_ACC_TYPE, NAP_ACC_SUB_TYPE, NAP_BAN, SDP_CUSTOMER_ID, SDP_CUSTOMER_TYPE, SDP_OPERATOR_ID,
				SDP_STATUS, SDP_SCHEDULE_ID, SDP_EXPECTED_PAM_SCHEDULE_ID, SDP_PRODUCT_ID, SDP_PRODUCT_EXPIRATION_UTC,
				SDP_CONSUMER, SDP_ACC_TYPE, SDP_ACC_SUB_TYPE, SDP_BAN, SDP_DATA_DA_ID, SDP_DATA_DA_UNIT_BALANCE,
				SDP_DATA_DA_EXPIRATION, EXPECTED_DA_ID, SDP_UT_ID, EXPECTED_UT_ID, SDP_UT_VALUE };
	
		return allAttributes;
		
	}


}
