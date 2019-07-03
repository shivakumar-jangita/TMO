package com.ericsson.tmo.cc.segment;

public class SegmentConstants {

	/** NAP & SDP nodes respective connectivity details -- starts here **/

	// sample, filepath = "crunchify.com.tutorials.crunchify - below mentioned "
	public static final String NODE_CONFIGDATA_PATH = "com.ericsson.tmo.cc.segment.configuration";

	public static final String NAP_URL = "NAP_URL";
	public static final String ECC_URL = "ECC_URL";
	public static final String AIR_URL = "AIR_URL";

	public static final String NAP_PASSWORD = "NAP_Password";
	public static final String ECC_PASSWORD = "ECC_Password";
	public static final String NAP_PARTNERID = "NAP_PartnerID";
	public static final String DELETESDP = "DeleteSDP";

	public static final String ACCEPT = "Accept";
	public static final String ACCEPT_VALUE = "text/xml";
	public static final String CONNECTION_STATUS = "keep-alive";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String CONTENT_TYPE_VALUE = "text/xml";
	public static final String HOST = "Host";
	public static final String HOST_VALUE = "PAG";
	public static final String USER_AGENT = "User-Agent";
	public static final String USER_AGENT_VALUE = "UGw Server/5.0/1.0";
	

	public static final String AUTHORIZATION = "Authorization";
	public static final String AUTHORIZATION_VALUE = "Basic dXNlcjp1c2Vy";

	public static final String PASSWORD = "Password";
	public static final String TIMESTAMP = "Timestamp";
	public static final String PARTNERID = "partnerID";
	public static final String XMLPAYLOAD = "xmlPayload";

	public static final String FILE_FORMAT = "UTF-8";
	public static final String TXT_FILE_EXTENSION = ".txt";
	public static final String CSV_FILE_EXTENSION = ".csv";
	public static final String EMPTY = "empty";
	public static final String EMPTY_STRING = "EmptyString";
	public static final String AM_TIME = "AM";
	public static final String PM_TIME = "PM";

	public static final String DATE_FORMAT_1 = "yyyyMMdd";
	public static final String DATE_FORMAT_2 = "yyyy-MM-dd";
	public static final String DATE_FORMAT_3 = "yyyyMMdd'T'HH:mm:ss";

	public static final String DEFINED_EXPIRY_DATE = "9999-12-31T12:00:00z";

	public static final String FRONT_SLASH = "/";
	public static final String SPACE = "";
	public static final String SIGN_COMA = ",";
	public static final String SIGN_SINGLE_CODE = "'";
	public static final String SPLITER_PIPE = "|";
	public static final String SPLITER_PIPE_WITHSLASH = "\\|";
	public static final String SPLITER_CAP = "^";

	/** NAP & SDP nodes respective connectivity details -- end here **/

	public static final String MESSAGE_DIGEST_FORMAT = "SHA-1";
	public static final String ECCGW_CONFIGDATA_PROPERTIES = "eccgw_configuration.properties";
	public static final String DESTINATION_DIRECTORY = "/opt/ConsistencyChecker/var/reports/check";
	public static final String REPORT_DESTINATION_DIRECTORY = "/opt/ConsistencyChecker/var/reports/";

	public static final String CORRECTION = "Correction";
	public static final String CREATE_SDP = "CreateSDP";
	public static final String PRODUCTID_MISMATCH = "ProductID_Mismatch";
	public static final String SUBSTATUS_MISMATCH = "SubStatus_Mismatch";
	public static final String OFFEREXPIRYDATE = "OfferExpiryDate_Mismatch";
	public static final String CUSTOMERID_MISMATCH = "CustomerID_Mismatch";
	public static final String PAM_MISMATCH = "PAM_Mismatch";
	public static final String UT_MISMATCH = "UT_Mismatch";
	public static final String VERIFY = "Verify";
	
	

	public static final String DIRECT_MISMATCH_TO_EXTERNAL_FILE = "direct_mismatch_to_external_file";
	public static final String FULL_SUBSCRIBER_MISMATCH = "Full_Subscriber_Mismatch";
	public static final String DA_ID_MISMATCH = "DA_ID_Mismatch";
	public static final String ORIGINOPERATORID = "originOperatorID";
	public static final String MAX_MISMATCH_COUNT = "max_mismatch_count";
	public static final String MISMATCH_MSISDN = "mismatchmsisdn";
	public static final String ORIGIN_TRANSACTION_ID = "originTransactionID";
	public static final String ORIGIN_DATETIME = "originTransactionID";

	public static final String ORIGINOPERATORID_DATA = "[originOperatorID]";
	public static final String TRANS_DATETIME = "[TRANS_DATETIME]";
	public static final String TRANS_ID_DATA = "[TRANS_ID]";
	public static final String ORIGIN_TRANSACTION_ID_DATA = "[originTransactionID]";
	
	public static final String ORIGIN_TIMESTAMP ="originTimestamp";
	public static final String DELETE_ID="deleteID";
	public static final String UPDATE_ID="updateID";
	public static final String UPDATE_ID_VALUE="UpdateIDValue";
	
	
	public static final String SCHEDULEID_VALUE = "scheduleId_value";
	public static final String MSISDN_VALUE = "msisdn_value";
	public static final String VENDOR_ID = "53";
	public static final String BILL_CYCLE_CLOSEDAY = "BillCycleCloseDay";
	public static final String ACCOUNT_STATUS = "AccountStatus";

	
	
	public static final String SUCCESSFUL = "Successful";
	public static final String FAILED = "Failed";
	public static final String PARTIAL = "Partial";
	
	public static final String AIR_FAILURE = "AIR:F";
	public static final String AIR_SUCCESSFUL = "AIR:S";
	
	public static final String ECCGW_FAILURE = "ECC-GW:F";
	public static final String ECCGW_SUCCESSFUL = "ECC-GW:S";
	
	public static final String	NAPSTATUS_NOTEXIST = "Not Exist";
	
	public static final String	FULLSUBSCRIBER =  "FullSubscriber";
	
	public static final String NOT_IN_SDP ="NOT_IN_SDP";
	public static final String NOT_IN_NAP ="NOT_IN_NAP";
	public static final String  DELETE_SUB="DeleteSub";
	
	/** List of segments -- starts here **/

	public static final String METROPC = "MetroPC";
	public static final String GOSMART = "GoSMART";
	public static final String MEGENTA = "Megenta";
	public static final String TRACFONE = "Tracfone";
	public static final String M2M = "M2M";
	public static final String WALMART = "Walmart";
	public static final String FAM_ALLOWANCES = "Fam_Allowances";
	public static final String PLINTRON = "Plintron";
	public static final String SIMPLE = "Simple";
	public static final String CINTEX = "Cintex";
	public static final String LYCAMOBILEPLUS = "LycaMobilePlus";
	public static final String SCNC = "SCNC";
	public static final String PROJECT_FI = "Project_Fi";
	public static final String LETO = "Leto";
	public static final String ULTRA_UVM = "Ultra_UVM";
	public static final String ARGOS = "Argos";
	public static final String ASPIDER = "Aspider";
	public static final String VODAFONE = "Vodafone";
	public static final String ROCK_ISLAND = "Rock_Island ";
	public static final String REPUBLIC_WIRELESS = "Republic_Wireless";

	/** List of segments -- ends here **/

	public static final String METROPC_SEGMENT = "METROPC";

	public static final String METROPC_ALL_UTIDS = "METROPC_ALLUTIDS";
	public static final String SERVICE_MPCSTOPUP = "MPCS-TOPUP";

	public static final String THRESHOLDID_ARRAY = "THRESHOLDID_ARRAY";
	public static final String SDPUSAGEID_LIST = "SDPUSAGEID_LIST";
	public static final String MSISDN = "MSISDN";
	public static final String SERVICE_SET = "SERVICE_SET";
	public static final String ALLMETRO_UTIDS = "ALLMETRO_UTIDS";
	public static final String THRESHOLD_SOURCEMAP = "THRESHOLD_SOURCEMAP";
	public static final String USAGELIMIT = "USAGELIMIT";
	public static final String CREATE_SUB ="CreateSub";
	public static final String RESPONSE_CODE ="responseCode";
	
	
	

	public static final String METRO_SERVICES = "Metro_services";
	public static final String METRO_OFFERIDS = "Metro_offerids";
	public static final String ENABLE = "enabled";
	public static final String TRUE = "true";
	public static final String FALSE = "false";
	public static final String NAPURL = "http://10.178.69.1:8002/PPListener/partnerpublisher";
	public static final String ECCURL = "http://10.169.71.207:8081/cwf/esp/query";
	public static final String AIRURL = "http://10.169.71.57:10011/Air";
	public static final String NAPPWD = "MBC@test";
	public static final String NAPPARTNERID_VAL = "53";
	public static final String ECCPWD = "mykey";
	public static final String MISMATCHMAX_COUNT = "500";
	public static final String METROPC_REPORTNAME = "MetroPcAnalysis";
	public static final String CSVMISMATCH_PATH = "/opt/ConsistencyChecker/var/reports/mismatch_";
	public static final String INPUT_TXT = "/opt/ConsistencyChecker/var/reports/RequestsLog";
	public static final String CORRECTION_REPORT_PATH = "/opt/ConsistencyChecker/var/reports/CorrectionReport";
	public static final String SPECS_DIRECTORY = "/opt/ConsistencyChecker/var/reports/check/";
	public static final String CORRECTION_PROPERTY_FILENAME = "correctionPropertiesFileName";
	public static final String MPCS_TRUNL = "MPCS-TRUNL";
	public static final String MPCS_TETHER = "MPCS-TETHER";
	public static final String MPCS_TET_UN = "MPCS-TET-UN";
	public static final String MPCS_TOPUP = "MPCS-TOPUP";
	public static final String CORRECTION_PROPERTIES_PATH = "/opt/ConsistencyChecker/conf";
	public static final String CC = "cc";
	public static final String UT= "UT";
	public static final String SUBSCRIBER_NOT_EXIST="subscriberNotexist";
	public static final String SUBSCRIBER_EXIST="subscriberExist";
	public static final String ERROR_CUSTOMER_DOESNT_EXIST="ERROR_CUSTOMER_DOESNT_EXIST";
	
	public static final String BILLCYCLE_PERIOD = "BillCyclePeriod";
	public static final String CUSTOMER_TRANSACTIONS = "CustomerTransactions";
	public static final String ACTION_SUCCESSFUL = "ActionSuccessful";
	public static final String CUSTOMERID = "CustomerID";
	public static final String CUSTOMER_TYPE = "CustomerType";
	public static final String LANGUAGE = "Language";
	public static final String IMSI = "imsi";
	public static final String STATUS_CODE = "StatusCode";
	public static final String OPERATORID = "OperatorID";
	public static final String VENDORID = "VendorID";
	public static final String PAH_MSISDN = "PAHmsisdn";
	public static final String FEATURE = "Feature";
	public static final String USAGE_LIMIT = "UsageLimit";
	public static final String NAME = "name";
	public static final String FEATURENAME = "Name";
	public static final String EXPIRATION_TIME = "ExpirationTime";
	public static final String MEMBER = "member";
	public static final String ATTRIBUTE_NAME = "attributeName";
	public static final String VALUE = "value";
	public static final String PRODUCT_ID = "ProductID";

	public static final String EXPIRYDATE_TIME = "expiryDateTime";
	public static final String STARTDATE_TIME = "startDateTime";
	public static final String PAMSERVICE_ID = "pamServiceID";
	public static final String OFFER_ID = "OfferID";
	public static final String SUBSCRIBER_VALUE_0 ="0";
	public static final String SUBSCRIBER_VALUE_102 ="102";
	public static final String PAM_SERVICEID_VALUE_10 = "10";
	public static final String PAM_SERVICEID_VALUE_11 = "11";
	public static final String DEFAUT_PAM_SCHEDUE_VALUE = "20";
	public static final String PAM_SCHEDUE_VALUE_200 = "200";
	public static final String PAM_SCHEDUE_VALUE_2032 = "2032";
	public static final String THIRTY_NINE = "39";
	public static final String THREE = "3";
	public static final String RESPONSE_MESSAGE = "ResponseMessage";
	public static final String A1 = "A1";
	public static final String A0 = "A0";
	public static final String DEDICATED_ACCID = "DedicatedAccID";
	public static final String USAGE_THRESHOLD_ID = "UsageThresholdID";
	public static final String TRANSACTION_SUCCESSFUL = "Transaction successful";
	public static final String STRING = "string";
	public static final String NODATA_PLAN = "NODATAPLAN";
	public static final String NODATA_PLAN_NEVER = "NODATAPLAN^never";
	public static final String NODATA_PLAN_VALUE = "9999-12-31T00:00:00Z";
	public static final String CUSTOMER_TRANSACTIONS_START = "<CustomerTransactions>";
	public static final String QOS_STATUS_START = "<QosStatus>";
	public static final String QOS_STATUS_END = "</QosStatus>";
	public static final String TRANSACTION_RESULTS_START = "<TransactionResults>";
	public static final String TRANSACTION_RESULTS_END = "</TransactionResults>";
	public static final String TRANSACTION_ID_START = "<TransactionID>";
	public static final String TRANSACTION_ID_END = "</TransactionID>";
	public static final String EXPIRY_DATE = "ExpiryDate";
	public static final String METHOD_RESPONSE = "methodResponse";
	public static final String REPORT = "report";
	public static final String TEXT = ".txt";
	public static final String FAULT_CODE = "faultCode";
	public static final String CURRENT_TIMESTAMP = "yyyy-MM-dd HH:mm:ss";
	public static final String CURRENT_TIMESTAMP_MILLI = "yyyy-MM-dd HH:mm:ss:SSS";
	public static final String CURRENT_TIMESTAMP_TIMEZONE = "yyyyMMdd'T'HH:mm:ssZ";
	public static final String CURRENT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final String ORIGEN_DATE_FORMAT = "yyyyMMdd'T'HH:mm:ss";
	public static final String TIMEZONE = "GMT";
	public static final String TIMEZONE_PDT = "PDT";
	
	
	public static final String METROPC_UTID_1 = "500014";
	public static final String METROPC_UTID_2 = "500016";
	
	public static final String DEFAULT_UTID_VALUE = "999999999999999";
}
