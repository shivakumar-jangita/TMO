package com.ericsson.tmo.cc.segment.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ericsson.tmo.cc.segment.AnalyzerUtils;
import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.commons.NapDataPojo;
import com.ericsson.tmo.cc.segment.dao.service.SdpUtMappingServiceImpl;
import com.ericsson.tmo.cc.segment.interacton.ECCGWNodeInteractionHelper;
import com.ericsson.tmo.cc.segment.interacton.NAPNodeInteractionHelper;
import com.ericsson.tmo.cc.segment.interacton.SDPNodeInteractionHelper;
import com.ericsson.tmo.cc.segment.utils.AirThresholdVerificationUtil;
import com.ericsson.tmo.cc.segment.utils.CsvReportGeneratation;
import com.ericsson.tmo.cc.segment.utils.GenericUtils;
import com.ericsson.tmo.cc.segment.utils.PAMValidationUtils;
import com.ericsson.tmo.cc.segment.utils.SegmentReportGenerationUtils;
import com.ericsson.tmo.cc.segment.utils.SegmentUtils;

public class MetroPcSegmentHandlerUtils {

	private static final Logger logger = LoggerFactory.getLogger(MetroPcSegmentHandlerUtils.class);
	public static AnalyzerUtils analyzerUtils = null;

	/* To Check the mismatch case from dump data */
	public static List<String[]> mismatchExtraction(String segmentName, List<String> msisdnValues,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {
		logger.info("Controll reched to mismatchExtraction method:");
		String flag = "";
		String napResponse = null;
		String createReq = "";
		String correctedScenarios = "";
		String scenariosOfMismatch = "";
		Set<String> napServiceSet = null;
		Map<String, String> napServiceMap = null;
		NapDataPojo napDataPojo = new NapDataPojo();
		List<String[]> listForCSV = new ArrayList<String[]>();
		Map<String, String> napAttributes = new HashMap<String, String>();
		Map<String, String> sdpAttributes = new HashMap<String, String>();

		Map<String, String> expiryOfferIdMap = new HashMap<String, String>();
		Map<String, String> startTimeOfferIdMap = new HashMap<String, String>();
		Map<String, String> pamDataMap = new HashMap<String, String>();
		Set<String> producIdset = new HashSet<String>();
		Set<String> pamServiceIdSet = new HashSet<String>();
		List<String> correctionStatusList = new ArrayList<String>();
		List<String> southBoundNodeList = new ArrayList<String>();

		if (configPropertiesPojo.getCorrection() != null
				&& configPropertiesPojo.getCorrection().equalsIgnoreCase(SegmentConstants.ENABLE)) {
			for (String mismatchMsisdn : msisdnValues) {

				napServiceMap = new HashMap<String, String>();
				napServiceSet = new HashSet<String>();
				
				napResponse = NAPNodeInteractionHelper.queryNAP(mismatchMsisdn, configPropertiesPojo);
				//napResponse="<?xml version=\"1.0\" encoding=\"UTF-8\"?><CustomerTransactions><CustomerTransaction><TransactionInfo><RequestedAction>query</RequestedAction><msisdn>4252345078</msisdn><Version>1.4</Version><TransactionID>5330636453580642</TransactionID><VendorID>53</VendorID></TransactionInfo><AccountInfo><CustomerID>14252345078</CustomerID><msisdn>4252345078</msisdn><imsi>310310991000078</imsi><StatusCode>A</StatusCode><CustomerType>2</CustomerType><PAHmsisdn>4252345078</PAHmsisdn><Language>eng</Language><QosStatus><qosValue>1</qosValue><qosDescription>No session - No Alert</qosDescription><qosLastModifiedDate>2017-01-10T00:27:09Z</qosLastModifiedDate></QosStatus><OperatorID>4000</OperatorID><BillingSite>50</BillingSite></AccountInfo><ServiceInfo><VendorID>53</VendorID><Features><Feature><Name>MPCS-THR500MB</Name><ExpirationTime>2017-12-04T23:36:00Z</ExpirationTime></Feature><Feature><Name>MPCS-THR200MBM</Name><ExpirationTime>2017-12-04T23:36:00Z</ExpirationTime></Feature></Features></ServiceInfo><TransactionResults><TransactionID>5330636453580642</TransactionID><ActionSuccessful>true</ActionSuccessful><ResponseMessage>Transaction successful</ResponseMessage></TransactionResults></CustomerTransaction></CustomerTransactions>";
				if (napResponse == null) {
					logger.error("No NAP napResponse. Aborting Mismatch Correction...");
				} else {
					logger.info(" Else-NAP napResponse=" + napResponse);
					if (napResponse != null && napResponse.length() > 0) {
						Document napResDoc = GenericUtils.convertStringToDocument(napResponse);
						napResDoc.getDocumentElement().normalize();

						logger.info("Root element :" + napResDoc.getDocumentElement().getNodeName());
						NodeList napNodeReponseList = napResDoc
								.getElementsByTagName(SegmentConstants.CUSTOMER_TRANSACTIONS);
						Node action = napNodeReponseList.item(0);
						Element actionElement = (Element) action;
						flag = actionElement.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL).item(0)
								.getTextContent();
						if (flag.equalsIgnoreCase(SegmentConstants.TRUE)) {
							NodeList napReponseList = napResDoc
									.getElementsByTagName(SegmentConstants.CUSTOMER_TRANSACTIONS);
							for (int k = 0; k < napReponseList.getLength(); k++) {

								Node napNodeResp = napReponseList.item(k);

								logger.info("\n Current Element :" + napNodeResp.getNodeName());
								if (napNodeResp.getNodeType() == Node.ELEMENT_NODE) {
									Element elementResp = (Element) napNodeResp;

									// retrieve BILLCYCLE_PERIOD data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.BILLCYCLE_PERIOD, napAttributes);
									napDataPojo
											.setBillCyclePeriod(napAttributes.get(SegmentConstants.BILLCYCLE_PERIOD));

									// retrieve CUSTOMERID data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.CUSTOMERID, napAttributes);
									napDataPojo.setCustomerID(napAttributes.get(SegmentConstants.CUSTOMERID));

									// retrieve CUSTOMER_TYPE data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.CUSTOMER_TYPE, napAttributes);
									napDataPojo.setCustomerType(napAttributes.get(SegmentConstants.CUSTOMER_TYPE));

									// retrieve LANGUAGE data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.LANGUAGE, napAttributes);
									napDataPojo.setLanguage(napAttributes.get(SegmentConstants.LANGUAGE));

									// retrieve IMSI data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.IMSI, napAttributes);
									napDataPojo.setImsi(napAttributes.get(SegmentConstants.IMSI));

									// retrieve USAGE_LIMIT data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.USAGE_LIMIT, napAttributes);
									napDataPojo.setUsageLimit(napAttributes.get(SegmentConstants.USAGE_LIMIT));

									// retrieve STATUS_CODE data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.STATUS_CODE, napAttributes);
									napDataPojo.setStatusCode(napAttributes.get(SegmentConstants.STATUS_CODE));

									// retrieve OPERATORID data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.OPERATORID, napAttributes);
									napDataPojo.setOperatorID(napAttributes.get(SegmentConstants.OPERATORID));

									// retrieve VENDOR_ID data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.VENDOR_ID, napAttributes);
									napDataPojo.setVendorID(napAttributes.get(SegmentConstants.VENDOR_ID));

									// retrieve PAH_MSISDN data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.PAH_MSISDN, napAttributes);
									napDataPojo.setPahMSISDN(napAttributes.get(SegmentConstants.PAH_MSISDN));

									if (elementResp.getElementsByTagName(SegmentConstants.FEATURE) != null
											&& elementResp.getElementsByTagName(SegmentConstants.FEATURE)
													.item(0) != null) {

										NodeList featureList = elementResp
												.getElementsByTagName(SegmentConstants.FEATURE);
										for (int o = 0; o < featureList.getLength(); o++) {
											Node featureNode = featureList.item(o);
											Element feature = (Element) featureNode;
											String ServiceExpiryTime = null;
											String serviceName = null;
											if (feature.getElementsByTagName(SegmentConstants.FEATURENAME) != null
													&& feature.getElementsByTagName(SegmentConstants.FEATURENAME)
															.item(0) != null) {
												serviceName = feature.getElementsByTagName(SegmentConstants.FEATURENAME)
														.item(0).getTextContent();
											}
											if (feature.getElementsByTagName(SegmentConstants.EXPIRATION_TIME) != null
													&& feature.getElementsByTagName(SegmentConstants.EXPIRATION_TIME)
															.item(0) != null) {
												ServiceExpiryTime = feature
														.getElementsByTagName(SegmentConstants.EXPIRATION_TIME).item(0)
														.getTextContent();
											}

											napServiceMap.put(serviceName, ServiceExpiryTime);
											napServiceSet.add(serviceName);
										}

									}
									if (napServiceSet == null || napServiceSet.size() == 0) {
										napServiceSet = new HashSet<String>();
										napServiceSet.add(SegmentConstants.NODATA_PLAN);
										napServiceMap = new HashMap<String, String>();
										napServiceMap.put(SegmentConstants.NODATA_PLAN,
												SegmentConstants.NODATA_PLAN_VALUE);
									}

								}
							}

							// extract the full napResponse for create request
							int idx = napResponse.indexOf(SegmentConstants.CUSTOMER_TRANSACTIONS_START);
							String str1 = "";
							String str2 = "";
							if (idx > 0) {
								createReq = napResponse.substring(idx);
								if (createReq.indexOf(SegmentConstants.QOS_STATUS_START) > 0) {
									str1 = createReq.substring(0, createReq.indexOf(SegmentConstants.QOS_STATUS_START));
									str2 = createReq.substring(createReq.indexOf(SegmentConstants.QOS_STATUS_END)
											+ SegmentConstants.QOS_STATUS_END.length());

									createReq = str1 + str2;
								}

								str1 = createReq.substring(0,
										createReq.indexOf(SegmentConstants.TRANSACTION_RESULTS_START));
								str2 = createReq.substring(createReq.indexOf(SegmentConstants.TRANSACTION_RESULTS_END)
										+ SegmentConstants.TRANSACTION_RESULTS_END.length());

								createReq = str1 + str2;

								Random randNum = new Random();
								String transId = SegmentConstants.CC + randNum.nextInt(10000000);

								str1 = createReq.substring(0, createReq.indexOf(SegmentConstants.TRANSACTION_ID_START)
										+ SegmentConstants.TRANSACTION_ID_START.length());
								str2 = createReq.substring(createReq.indexOf(SegmentConstants.TRANSACTION_ID_END));
								createReq = str1 + transId + str2;

								logger.debug("createReq 1111 =  " + createReq);
							}
						}

						String responseAIR = SDPNodeInteractionHelper.getAccountDetails(mismatchMsisdn,
								configPropertiesPojo);
						
	//					String responseAIR ="<?xml version=\"1.0\" encoding=\"UTF-8\"?><methodResponse><params><param><value><struct><member><name>accountFlags</name><value><struct><member><name>activationStatusFlag</name><value><boolean>1</boolean></value></member><member><name>negativeBarringStatusFlag</name><value><boolean>0</boolean></value></member><member><name>serviceFeePeriodExpiryFlag</name><value><boolean>0</boolean></value></member><member><name>serviceFeePeriodWarningActiveFlag</name><value><boolean>0</boolean></value></member><member><name>supervisionPeriodExpiryFlag</name><value><boolean>0</boolean></value></member><member><name>supervisionPeriodWarningActiveFlag</name><value><boolean>0</boolean></value></member></struct></value></member><member><name>accountFlagsBefore</name><value><struct><member><name>activationStatusFlag</name><value><boolean>1</boolean></value></member><member><name>negativeBarringStatusFlag</name><value><boolean>0</boolean></value></member><member><name>serviceFeePeriodExpiryFlag</name><value><boolean>0</boolean></value></member><member><name>serviceFeePeriodWarningActiveFlag</name><value><boolean>0</boolean></value></member><member><name>supervisionPeriodExpiryFlag</name><value><boolean>0</boolean></value></member><member><name>supervisionPeriodWarningActiveFlag</name><value><boolean>0</boolean></value></member></struct></value></member><member><name>accountGroupID</name><value><i4>0</i4></value></member><member><name>accountTimeZone</name><value><string>PST8PDT</string></value></member><member><name>accountValue1</name><value><string>0</string></value></member><member><name>activationDate</name><value><dateTime.iso8601>20170420T12:00:00+0000</dateTime.iso8601></value></member><member><name>availableServerCapabilities</name><value><array><data><value><i4>2013594308</i4></value><value><i4>11</i4></value></data></array></value></member><member><name>creditClearanceDate</name><value><dateTime.iso8601>20371231T12:00:00+0000</dateTime.iso8601></value></member><member><name>creditClearancePeriod</name><value><i4>0</i4></value></member><member><name>currency1</name><value><string>USD</string></value></member><member><name>firstIVRCallFlag</name><value><boolean>1</boolean></value></member><member><name>languageIDCurrent</name><value><i4>1</i4></value></member><member><name>masterAccountNumber</name><value><string>4252345078</string></value></member><member><name>masterSubscriberFlag</name><value><boolean>1</boolean></value></member><member><name>maxServiceFeePeriod</name><value><i4>9500</i4></value></member><member><name>maxSupervisionPeriod</name><value><i4>9500</i4></value></member><member><name>negotiatedCapabilities</name><value><array><data><value><i4>2013594308</i4></value><value><i4>11</i4></value></data></array></value></member><member><name>offerInformationList</name><value><array><data><value><struct><member><name>expiryDateTime</name><value><dateTime.iso8601>20171204T15:36:00-0800</dateTime.iso8601></value></member><member><name>offerID</name><value><i4>240000</i4></value></member><member><name>offerState</name><value><i4>0</i4></value></member><member><name>offerType</name><value><i4>2</i4></value></member><member><name>startDateTime</name><value><dateTime.iso8601>20170420T04:25:04-0700</dateTime.iso8601></value></member></struct></value><value><struct><member><name>attributeInformationList</name><value><array><data><value><struct><member><name>attributeName</name><value><string>ProductID</string></value></member><member><name>attributeValueString</name><value><string>MPCS-THR200MBM</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>counterLastResetDate</string></value></member><member><name>attributeValueString</name><value><string>04202017:042504</string></value></member></struct></value></data></array></value></member><member><name>expiryDateTime</name><value><dateTime.iso8601>20171204T15:36:00-0800</dateTime.iso8601></value></member><member><name>offerID</name><value><i4>240051</i4></value></member><member><name>offerState</name><value><i4>0</i4></value></member><member><name>offerType</name><value><i4>2</i4></value></member><member><name>startDateTime</name><value><dateTime.iso8601>20170420T04:25:04-0700</dateTime.iso8601></value></member></struct></value><value><struct><member><name>attributeInformationList</name><value><array><data><value><struct><member><name>attributeName</name><value><string>AccountSubType</string></value></member><member><name>attributeSource</name><value><i4>1</i4></value></member><member><name>attributeValueString</name><value><string>Default</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>AccountType</string></value></member><member><name>attributeSource</name><value><i4>1</i4></value></member><member><name>attributeValueString</name><value><string>Default</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>CustomerID</string></value></member><member><name>attributeValueString</name><value><string>14252345078</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>CustomerType</string></value></member><member><name>attributeValueString</name><value><string>2</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>IMEI</string></value></member><member><name>attributeSource</name><value><i4>1</i4></value></member><member><name>attributeValueString</name><value><string>Default</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>PackageVolume</string></value></member><member><name>attributeSource</name><value><i4>1</i4></value></member><member><name>attributeValueDecimal</name><value><struct><member><name>attributeValueNumber</name><value><string>0</string></value></member><member><name>numberOfDecimals</name><value><i4>0</i4></value></member></struct></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>ParentMSISDN</string></value></member><member><name>attributeSource</name><value><i4>1</i4></value></member><member><name>attributeValueString</name><value><string>Default</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>ProductID</string></value></member><member><name>attributeValueString</name><value><string>MPCS-THR500MB</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>UsageLimit</string></value></member><member><name>attributeSource</name><value><i4>1</i4></value></member><member><name>attributeValueDecimal</name><value><struct><member><name>attributeValueNumber</name><value><string>0</string></value></member><member><name>numberOfDecimals</name><value><i4>0</i4></value></member></struct></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>counterLastResetDate</string></value></member><member><name>attributeValueString</name><value><string>04202017:042504</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>multiLineIndicator</string></value></member><member><name>attributeSource</name><value><i4>1</i4></value></member><member><name>attributeValueString</name><value><string>Default</string></value></member></struct></value><value><struct><member><name>attributeName</name><value><string>operatorID</string></value></member><member><name>attributeValueString</name><value><string>4000</string></value></member></struct></value></data></array></value></member><member><name>expiryDateTime</name><value><dateTime.iso8601>99991231T12:00:00+0000</dateTime.iso8601></value></member><member><name>offerID</name><value><i4>251100</i4></value></member><member><name>offerState</name><value><i4>0</i4></value></member><member><name>offerType</name><value><i4>2</i4></value></member><member><name>startDateTime</name><value><dateTime.iso8601>20170420T04:25:04-0700</dateTime.iso8601></value></member></struct></value></data></array></value></member><member><name>originTransactionID</name><value><string>4972698617329384</string></value></member><member><name>pamInformationList</name><value><array><data><value><struct><member><name>currentPamPeriod</name><value><string>2017-04-21</string></value></member><member><name>lastEvaluationDate</name><value><dateTime.iso8601>20170421T12:00:00+0000</dateTime.iso8601></value></member><member><name>pamClassID</name><value><i4>10</i4></value></member><member><name>pamServiceID</name><value><i4>10</i4></value></member><member><name>pamServicePriority</name><value><i4>1</i4></value></member><member><name>scheduleID</name><value><i4>2032</i4></value></member></struct></value></data></array></value></member><member><name>responseCode</name><value><i4>0</i4></value></member><member><name>serviceClassCurrent</name><value><i4>300</i4></value></member><member><name>serviceFeeExpiryDate</name><value><dateTime.iso8601>20371231T12:00:00+0000</dateTime.iso8601></value></member><member><name>serviceFeePeriod</name><value><i4>7559</i4></value></member><member><name>serviceOfferings</name><value><array><data><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>1</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>2</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>3</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>4</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>5</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>6</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>7</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>8</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>9</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>10</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>11</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>12</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>13</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>14</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>15</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>16</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>17</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>18</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>19</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>20</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>21</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>22</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>23</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>24</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>25</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>26</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>27</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>28</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>29</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>30</i4></value></member></struct></value><value><struct><member><name>serviceOfferingActiveFlag</name><value><boolean>0</boolean></value></member><member><name>serviceOfferingID</name><value><i4>31</i4></value></member></struct></value></data></array></value></member><member><name>serviceRemovalDate</name><value><dateTime.iso8601>20371231T12:00:00+0000</dateTime.iso8601></value></member><member><name>serviceRemovalPeriod</name><value><i4>0</i4></value></member><member><name>supervisionExpiryDate</name><value><dateTime.iso8601>20371231T12:00:00+0000</dateTime.iso8601></value></member><member><name>supervisionPeriod</name><value><i4>7559</i4></value></member><member><name>ussdEndOfCallNotificationID</name><value><i4>7</i4></value></member></struct></value></param></params></methodResponse>";
						logger.info("responseAIR :: " + responseAIR);

						HashMap<String, String> thresholdSourceMap = new HashMap<String, String>();
						List<String> sdpUsageIDLists = SDPNodeInteractionHelper.getUsageIDLists(mismatchMsisdn,
								thresholdSourceMap, configPropertiesPojo);

						String allOfferId = "";
						String allScheduleId = "";
						String allProductId = "";
						Document sdpDocument = GenericUtils.convertStringToDocument(responseAIR);
						sdpDocument.getDocumentElement().normalize();

						logger.info("sdpDocument :: " + sdpDocument);

						boolean subscriberExistsFlag = checkSubscriberExists(sdpDocument);
						if (subscriberExistsFlag && flag.equalsIgnoreCase(SegmentConstants.TRUE)) {

							logger.info("111-subscriberExistsFlag :: " + subscriberExistsFlag);

							NodeList listOfMembers = sdpDocument.getElementsByTagName(SegmentConstants.MEMBER);
							for (int i = 0; i < listOfMembers.getLength(); i++) {
								Node node = listOfMembers.item(i);
								Element element = (Element) node;
								String attribute = element.getElementsByTagName(SegmentConstants.NAME).item(0)
										.getTextContent();
								if (attribute.equals(SegmentConstants.ATTRIBUTE_NAME)) {
									String value = element.getElementsByTagName(SegmentConstants.STRING).item(0)
											.getTextContent();

									switch (value) {
									case SegmentConstants.CUSTOMERID:
										Node CustomerIDnode = element.getNextSibling();
										Element CustomerIDelement = (Element) CustomerIDnode;
										String customerID = CustomerIDelement
												.getElementsByTagName(SegmentConstants.VALUE).item(0).getTextContent();
										sdpAttributes.put(SegmentConstants.CUSTOMERID, customerID);
										logger.info("CustomerID is = " + customerID);
										break;
									case SegmentConstants.CUSTOMER_TYPE:
										Node CustomerTypenode = element.getNextSibling();
										Element CustomerTypeelement = (Element) CustomerTypenode;
										String customerTypes = CustomerTypeelement
												.getElementsByTagName(SegmentConstants.VALUE).item(0).getTextContent();
										sdpAttributes.put(SegmentConstants.CUSTOMER_TYPE, customerTypes);
										logger.info("CustomerType is = " + customerTypes);
										break;
									case SegmentConstants.PRODUCT_ID:
										Node ProductIDnode = element.getNextSibling();
										Element ProductIDelement = (Element) ProductIDnode;
										String ProductID = ProductIDelement.getElementsByTagName(SegmentConstants.VALUE)
												.item(0).getTextContent();
										allProductId = allProductId + SegmentConstants.SPLITER_PIPE + ProductID;
										producIdset.add(ProductID);
										logger.info("ProductID is = " + ProductID);
										break;
									case SegmentConstants.OPERATORID:
										Node operatorIDnode = element.getNextSibling();
										Element operatorIDelement = (Element) operatorIDnode;
										String operatorID = operatorIDelement
												.getElementsByTagName(SegmentConstants.VALUE).item(0).getTextContent();
										logger.info("operatorID is = " + operatorID);
										sdpAttributes.put(SegmentConstants.OPERATORID, operatorID);
										break;

									}
								}
								if (attribute.equalsIgnoreCase(SegmentConstants.EXPIRYDATE_TIME)) {
									String expiryDate = element.getElementsByTagName(SegmentConstants.VALUE).item(0)
											.getTextContent();
									Node offerIDnode = element.getNextSibling();
									Element offerIDelement = (Element) offerIDnode;
									String offerID = offerIDelement.getElementsByTagName(SegmentConstants.VALUE).item(0)
											.getTextContent();
									allOfferId = allOfferId + SegmentConstants.SPLITER_PIPE + offerID;

									logger.info("offerID is = " + offerID);
									expiryOfferIdMap.put(offerID, expiryDate);
									logger.info("expirydate is = " + expiryDate);
								}
								if (attribute.equalsIgnoreCase(SegmentConstants.STARTDATE_TIME)) {
									String startdate = element.getElementsByTagName(SegmentConstants.VALUE).item(0)
											.getTextContent();
									Node offerIDnode = element.getPreviousSibling().getPreviousSibling()
											.getPreviousSibling();
									Element offerIDelement = (Element) offerIDnode;
									String offerID = offerIDelement.getElementsByTagName(SegmentConstants.VALUE).item(0)
											.getTextContent();

									logger.info("offerID is = " + offerID);
									startTimeOfferIdMap.put(offerID, startdate);
									logger.info("startdatep is = " + startdate);
								}
								if (attribute.equalsIgnoreCase(SegmentConstants.PAMSERVICE_ID)) {
									String pamServiceID = element.getElementsByTagName(SegmentConstants.VALUE).item(0)
											.getTextContent();
									Node scheduleIDnode = element.getNextSibling().getNextSibling();
									Element scheduleIDelement = (Element) scheduleIDnode;
									String scheduleID = scheduleIDelement.getElementsByTagName(SegmentConstants.VALUE)
											.item(0).getTextContent();
									if (null != pamServiceID) {
										if (pamServiceID.equals(SegmentConstants.PAM_SERVICEID_VALUE_10)
												|| pamServiceID.equals(SegmentConstants.PAM_SERVICEID_VALUE_11)) {
											allScheduleId = allScheduleId + SegmentConstants.SPLITER_PIPE + scheduleID;
											pamDataMap.put(pamServiceID, scheduleID);
											pamServiceIdSet.add(pamServiceID);
											logger.info("pamServiceID is = " + pamServiceID);
											logger.info("scheduleID is = " + scheduleID);
										}
									}

								}
							}
							if (allOfferId.length() >= 1) {
								allOfferId = allOfferId.substring(1);
							}
							if (allScheduleId.length() >= 1) {
								allScheduleId = allScheduleId.substring(1);
							}
							if (allProductId.length() >= 1) {
								allProductId = allProductId.substring(1);
							}
							boolean mrcMetroService = true;
							List<String> allAddonPassServiceList = SegmentUtils.getAddonPassServiceList(segmentName);
							for (String napServices : napServiceSet) {
								if (!allAddonPassServiceList.contains(napServices)) {
									mrcMetroService = false;
									break;
								}
							}
							if (mrcMetroService) {
								napServiceSet.add(SegmentConstants.NODATA_PLAN);
								napServiceMap.put(SegmentConstants.NODATA_PLAN, SegmentConstants.NODATA_PLAN_VALUE);
							}
							Map<String, Boolean> mismatchscenaro = null;
							mismatchscenaro = SegmentUtils.getMismatchscenrioMap(segmentName, napServiceSet,
									napServiceMap, napAttributes, sdpAttributes, sdpUsageIDLists, allProductId,
									allOfferId, allScheduleId, expiryOfferIdMap);

							logger.info("all offer IDs ++ " + allOfferId);
							boolean fullSubscriberMismatch = SegmentUtils.checkSDPFullsubscriber(allOfferId);

							if (!fullSubscriberMismatch && mismatchscenaro.get(SegmentConstants.OFFER_ID)) {

								scenariosOfMismatch = SegmentConstants.FULLSUBSCRIBER;

								// Update totalCount
								SegmentReportGenerationUtils.getInstance().countMismatchInOfferID.incrementCountTotal();

								// If correction is enabled, then proceed with
								// correction proceed with correction

								if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getFullSubscriberMismatch(),
										configPropertiesPojo.getCorrection())) {
									// Update Product ID
									String updateresponseOfferID = ECCGWNodeInteractionHelper
											.updateOfferIdOverECCGW(createReq, configPropertiesPojo);

									// Update counters
									if (updateresponseOfferID != null) {
										Document updateresponse = GenericUtils
												.convertStringToDocument(updateresponseOfferID);
										updateresponse.getDocumentElement().normalize();
										NodeList update = updateresponse
												.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
										String flagUpdate = update.item(0).getTextContent();
										SegmentReportGenerationUtils.getInstance().countMismatchInOfferID.increment(
												flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE), mismatchMsisdn);

										if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)) {
											correctionStatusList.add(SegmentConstants.SUCCESSFUL);
											southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
											correctedScenarios = "FullSubscriber";
										} else {
											correctionStatusList.add(SegmentConstants.FAILED);
											southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
										}
									}
								}

								String[] napSdpAttributes = CsvReportGeneratation.consolidateDataCsvMetro(
										mismatchMsisdn, correctionStatusList, southBoundNodeList, scenariosOfMismatch,
										correctedScenarios, napAttributes, napServiceSet, napServiceMap, null, null,
										null, null, null, null, null, null);

								listForCSV.add(napSdpAttributes);

							} else {
								if (mismatchscenaro.get(SegmentConstants.PRODUCT_ID)) {

									// Update totalCount
									SegmentReportGenerationUtils.getInstance().countMismatchInProductID
											.incrementCountTotal();

									// If correction is enabled, then proceed
									// with
									// correction proceed with correction

									if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getProductIdMismatch(),
											configPropertiesPojo.getCorrection())) {

										// Update Product ID
										String updateresponseProduct = ECCGWNodeInteractionHelper.updateProductIDCC(
												napServiceSet, napServiceMap, createReq, configPropertiesPojo);

										// Update counters
										if (updateresponseProduct != null) {
											Document updateresponseProductID = GenericUtils
													.convertStringToDocument(updateresponseProduct);
											updateresponseProductID.getDocumentElement().normalize();
											NodeList update = updateresponseProductID
													.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
											NodeList updateStatus = updateresponseProductID
													.getElementsByTagName(SegmentConstants.RESPONSE_MESSAGE);
											String flagUpdateStatus = updateStatus.item(0).getTextContent();
											String flagUpdate = update.item(0).getTextContent();
											SegmentReportGenerationUtils.getInstance().countMismatchInProductID
													.increment(
															flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)
																	&& flagUpdateStatus.equalsIgnoreCase(
																			SegmentConstants.TRANSACTION_SUCCESSFUL),
															mismatchMsisdn);

											if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)
													&& flagUpdateStatus.equalsIgnoreCase("Transaction successful")) {
												correctionStatusList.add(SegmentConstants.SUCCESSFUL);
												southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
												correctedScenarios = correctedScenarios + "|" + "ProductID";
											} else {
												correctionStatusList.add(SegmentConstants.FAILED);
												southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
											}
										}
									}

								}
								if (!mismatchscenaro.get(SegmentConstants.PRODUCT_ID)
										&& mismatchscenaro.get(SegmentConstants.EXPIRY_DATE)) {

									// Update totalCount
									SegmentReportGenerationUtils.getInstance().countMismatchInExpiryDate
											.incrementCountTotal();

									// If correction is enabled, then proceed
									// with correction proceed with correction

									if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getOfferexpiryDate(),
											configPropertiesPojo.getCorrection())) {

										// Update expiry date
										String updateresponseExpiry = ECCGWNodeInteractionHelper.updatExpiryDateCC(
												mismatchMsisdn, napServiceMap, napServiceSet, expiryOfferIdMap,
												createReq, configPropertiesPojo);

										// Update counters
										if (updateresponseExpiry != null) {
											Document updateresponseExpiryDate = GenericUtils
													.convertStringToDocument(updateresponseExpiry);
											updateresponseExpiryDate.getDocumentElement().normalize();
											NodeList update = updateresponseExpiryDate
													.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
											NodeList updateStatus = updateresponseExpiryDate
													.getElementsByTagName(SegmentConstants.RESPONSE_MESSAGE);
											String flagUpdateStatus = updateStatus.item(0).getTextContent();
											String flagUpdate = update.item(0).getTextContent();
											SegmentReportGenerationUtils.getInstance().countMismatchInExpiryDate
													.increment(
															flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)
																	&& flagUpdateStatus.equalsIgnoreCase(
																			SegmentConstants.TRANSACTION_SUCCESSFUL),
															mismatchMsisdn);

											if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE) && flagUpdateStatus
													.equalsIgnoreCase(SegmentConstants.TRANSACTION_SUCCESSFUL)) {
												correctionStatusList.add(SegmentConstants.SUCCESSFUL);
												southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
												correctedScenarios = correctedScenarios + SegmentConstants.SPLITER_PIPE
														+ SegmentConstants.EXPIRY_DATE;
											} else {
												correctionStatusList.add(SegmentConstants.FAILED);
												southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
											}
										}
									}
								}
								if (mismatchscenaro.get(SegmentConstants.STATUS_CODE)) {

									// Update totalCount
									SegmentReportGenerationUtils.getInstance().countMismatchInSubscriberStatus
											.incrementCountTotal();

									// If correction is enabled, then proceed
									// with
									// correction proceed with correction

									if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getSubStatusMismatch(),
											configPropertiesPojo.getCorrection())) {

										// Update status code
										String updateRespStatus = ECCGWNodeInteractionHelper
												.updateStatusCodeECC(mismatchMsisdn, napDataPojo, configPropertiesPojo);

										// Update counters
										if (updateRespStatus != null) {
											Document updateresponseStatusCodeECC = GenericUtils
													.convertStringToDocument(updateRespStatus);
											updateresponseStatusCodeECC.getDocumentElement().normalize();
											NodeList update = updateresponseStatusCodeECC
													.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
											String flagUpdate = update.item(0).getTextContent();
											SegmentReportGenerationUtils.getInstance().countMismatchInSubscriberStatus
													.increment(flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE),
															mismatchMsisdn);

											if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)) {
												correctionStatusList.add(SegmentConstants.SUCCESSFUL);
												southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
												correctedScenarios = correctedScenarios + SegmentConstants.SPLITER_PIPE
														+ SegmentConstants.STATUS_CODE;
											} else {
												correctionStatusList.add(SegmentConstants.FAILED);
												southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
											}
										}
									}
								}
								if (mismatchscenaro.get(SegmentConstants.BILLCYCLE_PERIOD)) {
									String[] schedulelistSDP = null;

									// PRODUCT ID MISMATCH
									if (allScheduleId != null && !allScheduleId.equals("")) {
										schedulelistSDP = allScheduleId.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);
									} else {
										schedulelistSDP = null;
									}

									// Update totalCount
									SegmentReportGenerationUtils.getInstance().countMismatchInPAM.incrementCountTotal();

									// If correction is enabled, then proceed
									// with
									// correction proceed with correction

									if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getPamMismatch(),
											configPropertiesPojo.getCorrection())) {

										// Update PAM
										String updateRespBillCycle = null;
										if (napDataPojo.getBillCyclePeriod() != null
												&& !napDataPojo.getBillCyclePeriod().isEmpty()) {

											updateRespBillCycle = PAMValidationUtils.updatePam(mismatchMsisdn,
													schedulelistSDP, startTimeOfferIdMap, napServiceSet,
													pamServiceIdSet, napDataPojo, configPropertiesPojo);
										} else {
											SegmentReportGenerationUtils.getInstance().countMismatchInPAM
													.incrementMissingPAMCount();
											SegmentReportGenerationUtils.getInstance().countMismatchInPAM
													.getMissingPAMMsisdn().add(mismatchMsisdn);
											if (scenariosOfMismatch.isEmpty())
												scenariosOfMismatch = "BillCyclePeriod^NotExecuted";
											else
												scenariosOfMismatch = scenariosOfMismatch + "|"
														+ "BillCyclePeriod^NotExecuted";
										}

										// Update counters
										if (updateRespBillCycle != null) {
											Document updateRespBillCycleDoc = GenericUtils
													.convertStringToDocument(updateRespBillCycle);
											updateRespBillCycleDoc.getDocumentElement().normalize();

											NodeList update = updateRespBillCycleDoc
													.getElementsByTagName(SegmentConstants.METHOD_RESPONSE);
											if (update == null) {
												update = updateRespBillCycleDoc
														.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
											}
											String flagUpdate = null;
											if (update != null && update.item(0) != null) {
												flagUpdate = update.item(0).getTextContent();
											}
											boolean faultCode = SegmentUtils.checkAirResponse(updateRespBillCycleDoc);
											SegmentReportGenerationUtils.getInstance().countMismatchInPAM
													.increment(((flagUpdate != null) && faultCode), mismatchMsisdn);

											if ((flagUpdate != null) && faultCode) {
												correctionStatusList.add(SegmentConstants.SUCCESSFUL);
												southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
												correctedScenarios = correctedScenarios + SegmentConstants.SPLITER_PIPE
														+ SegmentConstants.BILLCYCLE_PERIOD;
											} else {
												correctionStatusList.add(SegmentConstants.FAILED);
												southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
											}
										}
									}
								}
								if (mismatchscenaro.get(SegmentConstants.USAGE_THRESHOLD_ID)) {
									// Update totalCount
									SegmentReportGenerationUtils.getInstance().countMismatchInUsageThreshold
											.incrementCountTotal();

									// If correction is enabled, then proceed
									// with correction proceed with correction

									if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getUtMismatch(),
											configPropertiesPojo.getCorrection())) {

										try {
											String serviceList = SegmentUtils.combineListOfService(napServiceSet);
											List<String> thresholdIdList = SdpUtMappingServiceImpl
													.fetchListOfUtIdByServiceName(serviceList);

											if (thresholdIdList != null) {
												// Update threshold
												Map<String, Object> updateThresholdReqInputMap = new HashMap<>();
												updateThresholdReqInputMap.put(SegmentConstants.MSISDN, mismatchMsisdn);
												updateThresholdReqInputMap.put(SegmentConstants.THRESHOLDID_ARRAY,
														thresholdIdList);
												updateThresholdReqInputMap.put(SegmentConstants.SDPUSAGEID_LIST,
														sdpUsageIDLists);
												updateThresholdReqInputMap.put(SegmentConstants.REPORT,
														configPropertiesPojo.getReportName());
												updateThresholdReqInputMap.put(SegmentConstants.SERVICE_SET,
														napServiceSet);
												updateThresholdReqInputMap.put(SegmentConstants.THRESHOLD_SOURCEMAP,
														thresholdSourceMap);
												configPropertiesPojo.setSegmentName(segmentName);

												String updateResponseThreshold = AirThresholdVerificationUtil
														.updateThreshold(updateThresholdReqInputMap, napDataPojo,
																configPropertiesPojo);

												// Update counters
												if (updateResponseThreshold != null) {
													Document updateresponseThresholdDOc = GenericUtils
															.convertStringToDocument(updateResponseThreshold);
													updateresponseThresholdDOc.getDocumentElement().normalize();
													NodeList update = updateresponseThresholdDOc
															.getElementsByTagName(SegmentConstants.METHOD_RESPONSE);
													String flagUpdate = update.item(0).getTextContent();
													boolean faultCode = SegmentUtils
															.checkAirResponse(updateresponseThresholdDOc);
													SegmentReportGenerationUtils
															.getInstance().countMismatchInUsageThreshold.increment(
																	((flagUpdate != null) && faultCode),
																	mismatchMsisdn);

													if ((flagUpdate != null) && faultCode) {
														correctionStatusList.add(SegmentConstants.SUCCESSFUL);
														southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
														correctedScenarios = correctedScenarios
																+ SegmentConstants.SPLITER_PIPE + SegmentConstants.UT;
													} else {
														correctionStatusList.add(SegmentConstants.FAILED);
														southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
													}
												}

												updateThresholdReqInputMap = null;
											}

										} catch (Exception exception) {

											logger.error("exception :" + exception.getMessage());
											exception.printStackTrace();
										}

									}

								}
							}

							List<String> scenariolist = executedSenariosList();
							for (String scenario : scenariolist) {
								if (mismatchscenaro.get(scenario)) {

									if (scenariosOfMismatch.isEmpty()) {
										scenariosOfMismatch = scenario;
										
									} else {
										scenariosOfMismatch = scenariosOfMismatch + SegmentConstants.SPLITER_PIPE
												+ scenario;
									
									}
								}
							}
							String[] napSdpAttributes = CsvReportGeneratation.consolidateDataCsvMetro(mismatchMsisdn,
									correctionStatusList, southBoundNodeList, scenariosOfMismatch, correctedScenarios,
									napAttributes, napServiceSet, napServiceMap, sdpAttributes, producIdset,
									expiryOfferIdMap, allOfferId, allScheduleId, null, sdpUsageIDLists, null);

							listForCSV.add(napSdpAttributes);

						}
						// unique in SDP so need to call Create
						else if (flag.equalsIgnoreCase(SegmentConstants.TRUE) && subscriberExistsFlag) {
							logger.info(" Create :::: subscriberExistsFlag"+subscriberExistsFlag);
							if (configPropertiesPojo.getCreateSdp() != null
									&& configPropertiesPojo.getCreateSdp().equalsIgnoreCase(SegmentConstants.TRUE)) {

								String responseCreateECC = ECCGWNodeInteractionHelper.createSDPSubscriptionOverECC(
										mismatchMsisdn, napServiceSet, napServiceMap, napDataPojo,
										configPropertiesPojo);

								// Update counters
								if (responseCreateECC != null) {
									Document responseCreateECCDOC = GenericUtils
											.convertStringToDocument(responseCreateECC);
									responseCreateECCDOC.getDocumentElement().normalize();
									NodeList update = responseCreateECCDOC
											.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
									String flagUpdate = update.item(0).getTextContent();
									SegmentReportGenerationUtils.getInstance().countSubscriberOnlyExistsInBQR.increment(
											flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE), mismatchMsisdn);
									if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)) {
										correctionStatusList.add(SegmentConstants.SUCCESSFUL);
										southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
										correctedScenarios = correctedScenarios + SegmentConstants.SPLITER_PIPE
												+ SegmentConstants.CREATE_SUB;
									} else {
										correctionStatusList.add(SegmentConstants.FAILED);
										southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
									}

								}
							}

							scenariosOfMismatch = SegmentConstants.NOT_IN_SDP;
							String[] napSdpAttributes = CsvReportGeneratation.consolidateDataCsvMetro(mismatchMsisdn,
									correctionStatusList, southBoundNodeList, scenariosOfMismatch, correctedScenarios,
									napAttributes, napServiceSet, napServiceMap, null, null, null, null, null, null,
									null, null);

							listForCSV.add(napSdpAttributes);

						} else if ((!flag.equalsIgnoreCase(SegmentConstants.TRUE))
								&& napResponse.indexOf(SegmentConstants.ERROR_CUSTOMER_DOESNT_EXIST) > 0
								&& subscriberExistsFlag) {

							logger.info("Controll reached in ERROR_CUSTOMER_DOESNT_EXIST :SUBSCRIBER_EXIST ");

							if (configPropertiesPojo.getDeleteSdp() != null
									&& configPropertiesPojo.getDeleteSdp().equalsIgnoreCase(SegmentConstants.TRUE)) {
								// unique in SDP so need to call delete
								String responseDeleteECC = ECCGWNodeInteractionHelper.deleteSdpSubscriptionOverECC(
										mismatchMsisdn, napDataPojo, configPropertiesPojo);
								// Update counters
								if (responseDeleteECC != null) {
									Document responseDeleteECCDOC = GenericUtils
											.convertStringToDocument(responseDeleteECC);
									responseDeleteECCDOC.getDocumentElement().normalize();
									NodeList update = responseDeleteECCDOC
											.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
									String flagUpdate = update.item(0).getTextContent();
									SegmentReportGenerationUtils.getInstance().countSubscriberOnlyExistsInSDP.increment(
											flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE), mismatchMsisdn);
									if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)) {
										correctionStatusList.add(SegmentConstants.SUCCESSFUL);
										southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
										correctedScenarios = correctedScenarios + SegmentConstants.SPLITER_PIPE
												+ SegmentConstants.DELETE_SUB;
									} else {
										correctionStatusList.add(SegmentConstants.FAILED);
										southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
									}
								}
							}
							scenariosOfMismatch = SegmentConstants.NOT_IN_NAP;
							String[] napSdpAttributes = CsvReportGeneratation.consolidateDataCsvMetro(mismatchMsisdn,
									correctionStatusList, southBoundNodeList, scenariosOfMismatch, correctedScenarios,
									null, null, null, sdpAttributes, producIdset, expiryOfferIdMap, allOfferId,
									allScheduleId, null, null, null);
							listForCSV.add(napSdpAttributes);

						}
					}
				}
			}
		}
		return listForCSV;

	}

	private static List<String> executedSenariosList() {
		List<String> scenarioList = new ArrayList<String>();
		scenarioList.add(SegmentConstants.STATUS_CODE);
		scenarioList.add(SegmentConstants.PRODUCT_ID);
		scenarioList.add(SegmentConstants.OFFER_ID);
		scenarioList.add(SegmentConstants.USAGE_THRESHOLD_ID);
		//scenarioList.add(SegmentConstants.BILLCYCLE_PERIOD);
		scenarioList.add(SegmentConstants.EXPIRY_DATE);

		return scenarioList;
	}

	private static boolean checkSubscriberExists(Document sdpDocument) {

		logger.info("Controll reached in checkSubscriberExists");
		boolean subscriberExistsFlag = false;
		NodeList listOfMembers = sdpDocument.getElementsByTagName(SegmentConstants.MEMBER);
		for (int i = 0; i < listOfMembers.getLength(); i++) {

			logger.info("listOfMembers.getLength() :: " + listOfMembers.getLength());
			Node node = listOfMembers.item(i);
			Element element = (Element) node;
			String attribute = element.getElementsByTagName(SegmentConstants.NAME).item(0).getTextContent();
			if (attribute.equals(SegmentConstants.RESPONSE_CODE)) {
				String value = element.getElementsByTagName(SegmentConstants.VALUE).item(0).getTextContent();

				if (value.equals(SegmentConstants.SUBSCRIBER_VALUE_0))
					subscriberExistsFlag = true;
				else if (value.equals(SegmentConstants.SUBSCRIBER_VALUE_102))
					subscriberExistsFlag = false;
			}
		}
		logger.info("checkSubscriberExists :subscriberExistsFlag :: " + subscriberExistsFlag);
		return subscriberExistsFlag;
	}

	/* To uniqueRecord After data correction */
	public static List<String[]> uniqueRecordExtraction(String segmentName, Set<String> uniqueList2,
			ConfigPropertiesPojo configPropertiesPojo, List<String[]> listForCSV) throws Exception {

		for (String unique : uniqueList2) {

			String[] uniqueEtry = unique.split(SegmentConstants.SPLITER_PIPE_WITHSLASH);

			logger.debug("\nunique msisdn :" + uniqueEtry[0]);

			String msisdn = uniqueEtry[0];
			String referencedump = uniqueEtry[1];
			NapDataPojo napDataPojo = new NapDataPojo();
			String scenariosOfMismatch = "";
			String correctedScenarios = "";
			List<String> correctionStatusList = new ArrayList<String>();
			List<String> southBoundNodeList = new ArrayList<String>();
			Map<String, String> napAttributes = new HashMap<>();
			Map<String, String> sdpAttributes = new HashMap<>();
			if (referencedump.equalsIgnoreCase(SegmentConstants.A0)) {

				// Update totalCount
				SegmentReportGenerationUtils.getInstance().countSubscriberOnlyExistsInBQR.incrementCountTotal();

				// If correction is enabled, then proceed with correction
				// proceed with correction
				if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getCreateSdp(),
						configPropertiesPojo.getCorrection())) {

					// unique in NAP so need to call create
					String pahMsisdn = null;
					Map<String, String> napServiceMap = new HashMap<String, String>();
					Set<String> napServiceSet = new HashSet<String>();
					String napResponse = NAPNodeInteractionHelper.queryNAPTest(pahMsisdn);
					if (napResponse == null) {
						logger.info("No NAP napResponse for Unique entry. Aborting Mismatch Correction...");
					} else {
						Document napResDoc = GenericUtils.convertStringToDocument(napResponse);
						napResDoc.getDocumentElement().normalize();

						logger.info("Root element :" + napResDoc.getDocumentElement().getNodeName());
						NodeList napreponswList1 = napResDoc
								.getElementsByTagName(SegmentConstants.CUSTOMER_TRANSACTIONS);
						Node action = napreponswList1.item(0);
						Element actionele = (Element) action;
						String flag = actionele.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL).item(0)
								.getTextContent();
						if (flag.equalsIgnoreCase(SegmentConstants.TRUE)) {
							NodeList napReponseList = napResDoc
									.getElementsByTagName(SegmentConstants.CUSTOMER_TRANSACTIONS);
							for (int k = 0; k < napReponseList.getLength(); k++) {

								Node napNodeResp = napReponseList.item(k);

								logger.debug("\nCurrent Element :" + napNodeResp.getNodeName());
								if (napNodeResp.getNodeType() == Node.ELEMENT_NODE) {
									Element elementResp = (Element) napNodeResp;

									// retrieve BILLCYCLE_PERIOD data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.BILLCYCLE_PERIOD, napAttributes);
									napDataPojo
											.setBillCyclePeriod(napAttributes.get(SegmentConstants.BILLCYCLE_PERIOD));

									// retrieve CUSTOMERID data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.CUSTOMERID, napAttributes);
									napDataPojo.setCustomerID(napAttributes.get(SegmentConstants.CUSTOMERID));

									// retrieve CUSTOMER_TYPE data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.CUSTOMER_TYPE, napAttributes);
									napDataPojo.setCustomerType(napAttributes.get(SegmentConstants.CUSTOMER_TYPE));

									// retrieve LANGUAGE data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.LANGUAGE, napAttributes);
									napDataPojo.setLanguage(napAttributes.get(SegmentConstants.LANGUAGE));

									// retrieve IMSI data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.IMSI, napAttributes);
									napDataPojo.setImsi(napAttributes.get(SegmentConstants.IMSI));

									// retrieve USAGE_LIMIT data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.USAGE_LIMIT, napAttributes);
									napDataPojo.setUsageLimit(napAttributes.get(SegmentConstants.USAGE_LIMIT));

									// retrieve STATUS_CODE data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.STATUS_CODE, napAttributes);
									napDataPojo.setStatusCode(napAttributes.get(SegmentConstants.STATUS_CODE));

									// retrieve OPERATORID data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.OPERATORID, napAttributes);
									napDataPojo.setOperatorID(napAttributes.get(SegmentConstants.OPERATORID));

									// retrieve VENDOR_ID data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.VENDOR_ID, napAttributes);
									napDataPojo.setVendorID(napAttributes.get(SegmentConstants.VENDOR_ID));

									// retrieve PAH_MSISDN data
									napAttributes = GenericUtils.fetchNapElementByName(elementResp,
											SegmentConstants.PAH_MSISDN, napAttributes);
									napDataPojo.setPahMSISDN(napAttributes.get(SegmentConstants.PAH_MSISDN));

									if (elementResp.getElementsByTagName(SegmentConstants.FEATURE) != null
											&& elementResp.getElementsByTagName(SegmentConstants.FEATURE)
													.item(0) != null) {
										NodeList featureList = elementResp
												.getElementsByTagName(SegmentConstants.FEATURE);
										for (int o = 0; o < featureList.getLength(); o++) {
											Node featureNode = featureList.item(o);
											Element feature = (Element) featureNode;
											String ServiceExpiryTime = null;
											String serviceName = null;
											if (feature.getElementsByTagName(SegmentConstants.NAME) != null && feature
													.getElementsByTagName(SegmentConstants.NAME).item(0) != null) {
												serviceName = feature.getElementsByTagName(SegmentConstants.NAME)
														.item(0).getTextContent();
											}
											if (feature.getElementsByTagName(SegmentConstants.EXPIRATION_TIME) != null
													&& feature.getElementsByTagName(SegmentConstants.EXPIRATION_TIME)
															.item(0) != null) {
												ServiceExpiryTime = feature
														.getElementsByTagName(SegmentConstants.EXPIRATION_TIME).item(0)
														.getTextContent();
											}

											napServiceMap.put(serviceName, ServiceExpiryTime);
											napServiceSet.add(serviceName);
										}

									}
									if (napServiceSet == null || napServiceSet.size() == 0) {
										napServiceSet = new HashSet<>();
										napServiceSet.add(SegmentConstants.NODATA_PLAN);
										napServiceMap = new HashMap<>();
										napServiceMap.put(SegmentConstants.NODATA_PLAN,
												SegmentConstants.NODATA_PLAN_VALUE);
									}

								}
							}
						}
						// If correction is enabled, then proceed with
						// correction proceed with correction
						if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getCreateSdp(),
								configPropertiesPojo.getCorrection())) {

							String responseCreateECC = ECCGWNodeInteractionHelper.createSDPSubscriptionOverECC(msisdn,
									napServiceSet, napServiceMap, napDataPojo, configPropertiesPojo);

							// Update counters
							if (responseCreateECC != null) {
								Document responseCreateECCDOC = GenericUtils.convertStringToDocument(responseCreateECC);
								responseCreateECCDOC.getDocumentElement().normalize();
								NodeList update = responseCreateECCDOC
										.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
								String flagUpdate = update.item(0).getTextContent();
								SegmentReportGenerationUtils.getInstance().countSubscriberOnlyExistsInBQR
										.increment(flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE), msisdn);

								if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)) {
									correctionStatusList.add(SegmentConstants.SUCCESSFUL);
									southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
									correctedScenarios = correctedScenarios + SegmentConstants.SPLITER_PIPE
											+ SegmentConstants.CREATE_SUB;
								} else {
									correctionStatusList.add(SegmentConstants.FAILED);
									southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
								}

								scenariosOfMismatch = SegmentConstants.NOT_IN_SDP;
								String[] napSdpAttributes = CsvReportGeneratation.consolidateDataCsvMetro(msisdn,
										correctionStatusList, southBoundNodeList, scenariosOfMismatch,
										correctedScenarios, napAttributes, napServiceSet, napServiceMap, null, null,
										null, null, null, null, null, null);

								listForCSV.add(napSdpAttributes);

							}
						}

					}

				}
			} else if (referencedump.equalsIgnoreCase(SegmentConstants.A1)) {

				// Update totalCount
				SegmentReportGenerationUtils.getInstance().countSubscriberOnlyExistsInSDP.incrementCountTotal();
				String responseAIR = SDPNodeInteractionHelper.getAccountDetails(msisdn, configPropertiesPojo);
				Document sdpDocument = GenericUtils.convertStringToDocument(responseAIR);
				sdpDocument.getDocumentElement().normalize();
				NodeList listOfMembers = sdpDocument.getElementsByTagName(SegmentConstants.MEMBER);
				String customerID = "";
				String operatorID = "";
				for (int i = 0; i < listOfMembers.getLength(); i++) {
					Node node = listOfMembers.item(i);
					Element element = (Element) node;
					String attribute = element.getElementsByTagName(SegmentConstants.NAME).item(0).getTextContent();
					if (attribute.equals(SegmentConstants.ATTRIBUTE_NAME)) {
						String value = element.getElementsByTagName(SegmentConstants.STRING).item(0).getTextContent();

						switch (value) {
						case SegmentConstants.CUSTOMERID:
							Node CustomerIDnode = element.getNextSibling();
							Element CustomerIDelement = (Element) CustomerIDnode;
							customerID = CustomerIDelement.getElementsByTagName(SegmentConstants.VALUE).item(0)
									.getTextContent();
							sdpAttributes.put(SegmentConstants.CUSTOMERID, customerID);
							logger.debug("CustomerID is = " + customerID);
							break;

						case SegmentConstants.OPERATORID:
							Node operatorIDnode = element.getNextSibling();
							Element operatorIDelement = (Element) operatorIDnode;
							operatorID = operatorIDelement.getElementsByTagName(SegmentConstants.VALUE).item(0)
									.getTextContent();
							logger.debug("operatorID is = " + operatorID);
							sdpAttributes.put(SegmentConstants.OPERATORID, operatorID);
							break;

						}
					}

				}

				// If correction is enabled, then proceed with correction
				// proceed with correction
				if (SegmentUtils.useCaseIsEnabled(configPropertiesPojo.getDeleteSdp(),
						configPropertiesPojo.getCorrection())) {

					String responseDeleteECC = ECCGWNodeInteractionHelper.deleteSdpSubscriptionOverECC(msisdn,
							napDataPojo, configPropertiesPojo);

					// Update counters
					if (responseDeleteECC != null) {
						Document responseDeleteECCDOC = GenericUtils.convertStringToDocument(responseDeleteECC);
						responseDeleteECCDOC.getDocumentElement().normalize();
						NodeList update = responseDeleteECCDOC.getElementsByTagName(SegmentConstants.ACTION_SUCCESSFUL);
						String flagUpdate = update.item(0).getTextContent();
						SegmentReportGenerationUtils.getInstance().countSubscriberOnlyExistsInSDP
								.increment(flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE), msisdn);

						if (flagUpdate.equalsIgnoreCase(SegmentConstants.TRUE)) {
							correctionStatusList.add(SegmentConstants.SUCCESSFUL);
							southBoundNodeList.add(SegmentConstants.ECCGW_SUCCESSFUL);
							correctedScenarios = correctedScenarios + SegmentConstants.SPLITER_PIPE
									+ SegmentConstants.DELETE_SUB;
						} else {
							correctionStatusList.add(SegmentConstants.FAILED);
							southBoundNodeList.add(SegmentConstants.ECCGW_FAILURE);
						}

						/*
						 * String[] napSdpAttributes =
						 * MakeCsvDataMagenta.consolidateDataCsv(msisdn,
						 * correction_status, southbound_nodes,
						 * scenarios_Of_mismatch, corrected_scenarios, null,
						 * null, null, sdpAttributes, producIdset,
						 * expiry_offeridMap, allofferId, allscheduleID, null,
						 * null, null); listForCSV.add(napSdpAttributes);
						 */

						scenariosOfMismatch = SegmentConstants.NOT_IN_NAP;
						String[] napSdpAttributes = CsvReportGeneratation.consolidateDataCsvMetro(msisdn,
								correctionStatusList, southBoundNodeList, scenariosOfMismatch, correctedScenarios, null,
								null, null, sdpAttributes, null, null, null, null, null, null, null);
						listForCSV.add(napSdpAttributes);
					}
				}
			}

		}
		return listForCSV;
	}

}
