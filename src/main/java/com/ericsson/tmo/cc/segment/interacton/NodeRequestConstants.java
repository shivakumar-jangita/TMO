package com.ericsson.tmo.cc.segment.interacton;

import com.ericsson.tmo.cc.segment.SegmentConstants;

public class NodeRequestConstants {

	public static final String REQUEST_ACTION_DELETE = "<RequestedAction>delete</RequestedAction>";
	public static final String REQUEST_ACTION_START = "<RequestedAction>query</RequestedAction>";
	public static final String REQUEST_ACTION_CREATE = "<RequestedAction>create</RequestedAction>";
	public static final String REQUEST_ACTION_UPDATE = "<RequestedAction>update</RequestedAction>";
	public static final String REQUEST_ACTION_END = "<RequestedAction>update</RequestedAction><FieldsModified>ServiceInfo</FieldsModified>";
	public static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>";
	public static final String CUSTOMER_TRANSACTIONS_START = "<CustomerTransactions>";
	public static final String CUSTOMER_TRANSACTIONS_END = "</CustomerTransactions>";

	public static final String CUSTOMER_TRANSACTION_START = "<CustomerTransactions>";
	public static final String CUSTOMER_TRANSACTION_END = "</CustomerTransactions>";

	public static final String TRANSACTION_INFO_START = "<TransactionInfo>";
	public static final String TRANSACTION_INFO_END = "</TransactionInfo>";
	public static final String FIELD_MODIFIED_START = "<FieldsModified>";
	public static final String FIELD_MODIFIED_END = "</FieldsModified>";
	public static final String CUSTOMER_ID_START = "<CustomerID>";
	public static final String CUSTOMER_ID_END = "</CustomerID>";
	public static final String MSISDN_START = "<msisdn>";
	public static final String MSISDN_END = "</msisdn>";
	public static final String TRANSACTION_ID_START = "<TransactionID>";
	public static final String TRANSACTION_ID_END = "</TransactionID>";
	public static final String VENDOR_ID_START = "<VendorID>";
	public static final String VENDOR_ID_END = "</VendorID>";
	public static final String ACCOUNT_INFO_START = "<AccountInfo>";
	public static final String ACCOUNT_INFO_END = "</AccountInfo>";
	public static final String STATUS_CODE_START = "<StatusCode>";
	public static final String STATUS_CODE_END = "</StatusCode>";
	public static final String OPERATOR_ID_START = "<OperatorID>";
	public static final String OPERATOR_ID_END = "</OperatorID>";
	public static final String CUSTOMER_TYPE_START = "<CustomerType>";
	public static final String CUSTOMER_TYPE_END = "</CustomerType>";
	public static final String ONE = "1";
	public static final String VENDORID_VALUE = "53";
	public static final String IMSI_START = "<imsi>";
	public static final String IMSI_END = "</imsi>";
	public static final String BILLCYCLE_PERIOD_START = "<billCyclePeriod>";
	public static final String BILLCYCLE_PERIOD_END = "</billCyclePeriod>";
	public static final String LANGUAGE_START = "<Language>";
	public static final String LANGUAGE_END = "</Language>";
	public static final String PAH_MSISDN_START = "<PAHmsisdn>";
	public static final String PAH_MSISDN_END = "</PAHmsisdn>";
	public static final String SERVICE_INFO = "<ServiceInfo>";
	public static final String SERVICE_INFO_END = "</ServiceInfo>";
	public static final String FEATURE_START = " <Feature>";
	public static final String FEATURE_END = " </Feature>";
	public static final String EXPIRATION_TIME_START = "<ExpirationTime>";
	public static final String EXPIRATION_TIME_END = "</ExpirationTime>";
	public static final String NAME_START = "<Name>";
	public static final String NAME_END = "</Name>";

	public static final String QOS_STATUS_START = "<QosStatus>";
	public static final String QOS_STATUS_END = "</QosStatus>";
	public static final String TRANSACTION_RESULTS_START = "<TransactionResults>";
	public static final String TRANSACTION_RESULTS_END = "</TransactionResults>";

	public static final String ACCEPT = "Accept";
	public static final String TEXT_XML = "text/xml";
	public static final String CONNECTION = "Connection";
	public static final String KEEP_ALIVE = "keep-alive";
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String HOST = "Host";
	public static final String PAG = "PAG";
	public static final String USER_AGENT = "User-Agent";
	public static final String UGW_SERVER = "UGw Server/5.0/1.0";
	public static final String AUTHORIZATION = "Authorization";
	public static final String BASIC_PWD = "Basic dXNlcjp1c2Vy";

	public static String NAP_QUERY_REQUEST = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
			+ "<CustomerTransactions><CustomerTransaction><TransactionInfo><RequestedAction>query</RequestedAction>"
			+ "<msisdn>" + SegmentConstants.MSISDN_VALUE + "</msisdn><Version>1.4</Version><TransactionID>"
			+ SegmentConstants.TRANS_ID_DATA + "</TransactionID><VendorID>" + SegmentConstants.VENDOR_ID
			+ "</VendorID></TransactionInfo></CustomerTransaction></CustomerTransactions>";

	public static String GET_ACCOUNT_DTLS_REQ_PAY_LOAD = "<?xml version=\"1.0\"?><methodCall><methodName>GetAccountDetails</methodName><params><param><value><struct><member><name>originNodeType</name>"
			+ "<value><string>EXT</string></value></member><member><name>originHostName</name><value><string>EXT</string></value></member><member><name>originTransactionID</name><value><string>"
			+ SegmentConstants.ORIGIN_TRANSACTION_ID_DATA
			+ "</string></value></member><member><name>originTimeStamp</name><value><dateTime.iso8601>"
			+ SegmentConstants.TRANS_DATETIME + "</dateTime.iso8601></value></member>"
			+ "<member><name>subscriberNumber</name><value><string>" + SegmentConstants.MISMATCH_MSISDN
			+ "</string></value></member><member><name>requestPamInformationFlag</name><value><boolean>1</boolean></value></member>"
			+ "<member><name>requestedInformationFlags</name><value><struct><member><name>requestMasterAccountBalanceFlag</name><value><boolean>1</boolean></value></member></struct></value></member>"
			+ "<member><name>requestActiveOffersFlag</name><value><boolean>1</boolean></value></member><member><name>requestAttributesFlag</name><value><boolean>1</boolean></value></member>"
			+ "<member><name>negotiatedCapabilities</name><value><array><data><value><i4>2013594308</i4></value><value><i4>11</i4></value></data></array></value></member></struct></value></param></params></methodCall>";

	public static String GET_USAGETHRESHOLDS_AND_COUNTERS_PAY_LOAD = "<?xml version=\"1.0\"?><methodCall><methodName>GetUsageThresholdsAndCounters</methodName><params><param><value><struct><member><name>originNodeType</name>"
			+ "<value><string>EXT</string></value></member><member><name>originHostName</name><value><string>EXT</string></value></member>"
			+ "<member><name>originTransactionID</name><value><string>" + SegmentConstants.ORIGIN_TRANSACTION_ID_DATA
			+ "</string></value></member><member><name>originTimeStamp</name>" + "<value><dateTime.iso8601>"
			+ SegmentConstants.TRANS_DATETIME
			+ "</dateTime.iso8601></value></member><member><name>originOperatorID</name><value><string>"
			+ SegmentConstants.ORIGINOPERATORID_DATA
			+ "</string></value></member><member><name>subscriberNumber</name><value><string>"
			+ SegmentConstants.MISMATCH_MSISDN
			+ "</string></value></member><member><name>subscriberNumberNAI</name><value><i4>2</i4></value></member></struct></value></param></params></methodCall>";

	public static String DELETE_USAGETHRESHOLDS_PAY_LOAD_START = "<?xml version=\"1.0\"?><methodCall><methodName>DeleteUsageThresholds</methodName>"
			+ "<params><param> <value><struct><member> <name>originNodeType</name>" + "<value><string>EXT</string>"
			+ "</value></member>" + " <member><name>originHostName</name>" + "<value><string>vxToolbox</string>"
			+ "</value></member><member>" + "<name>originTransactionID</name>" + "<value><string>"
			+ SegmentConstants.ORIGIN_TRANSACTION_ID + "</string>" + "</value></member><member>"
			+ "<name>originTimeStamp</name>" + "<value><dateTime.iso8601>" + SegmentConstants.TRANS_DATETIME
			+ "+0000</dateTime.iso8601>" + "</value></member>" + "<member><name>subscriberNumber</name>"
			+ "<value><string>" + "</string>" + "</value></member>" + "<member><name>usageThresholds</name>"
			+ "<value><array><data>";

	public static String DELETE_USAGETHRESHOLDS_PAY_LOAD_APPEND_PART1 = "<value><struct><member><name>usageThresholdID</name>"
			+ "<value><int>";
	public static String DELETE_USAGETHRESHOLDS_PAY_LOAD_APPEND_PART2 = "</int></value></member>" + "</struct></value>";

	public static String DELETE_USAGETHRESHOLDS_PAY_LOAD_END = "</data>"
			+ "</array></value></member></struct></value></param></params></methodCall>";

	public static String UPDATE_USAGETHRESHOLDS_PAY_LOAD_START = "<?xml version=\"1.0\"?><methodCall><methodName>UpdateUsageThresholdsAndCounters</methodName>"
			+ "<params><param><value><struct><member><name>originNodeType</name><value><string>EXT</string></value></member>"
			+ "<member><name>originHostName</name><value><string>EXT</string></value></member><member><name>originTransactionID</name><value><string>"
			+ SegmentConstants.ORIGIN_TRANSACTION_ID
			+ "</string></value></member><member><name>originTimeStamp</name><value><dateTime.iso8601>"
			+ SegmentConstants.TRANS_DATETIME
			+ "+0000</dateTime.iso8601></value></member><member><name>originOperatorID</name><value><string>"
			+ SegmentConstants.ORIGINOPERATORID_DATA
			+ "</string></value></member><member><name>subscriberNumber</name><value><string>"
			+ SegmentConstants.MSISDN_VALUE
			+ "</string></value></member><member><name>usageThresholdUpdateInformation</name><value><array><data>";

	public static String UPDATE_USAGETHRESHOLDS_PAYLOAD_APPEND_PART1 = "<value><struct><member><name>usageThresholdID</name><value><i4>";
	public static String UPDATE_USAGETHRESHOLDS_PAYLOAD_APPEND_PART2 = "</i4></value></member><member><name>usageThresholdValueNew</name><value><string>";
	public static String UPDATE_USAGETHRESHOLDS_PAYLOAD_APPEND_PART3 = "</string></value></member></struct></value>";

	public static String UPDATE_USAGETHRESHOLDS_PAY_LOAD_END = "</data>"
			+ "</array></value></member></struct></value></param></params></methodCall>";
	
	
	
	public static String ADDPAM_REQUEST_PAYLOAD = "<?xml version=\"1.0\"?><methodCall>"
												+ "<methodName>AddPeriodicAccountManagementData</methodName><params><param><value>"
												+ "<struct><member><name>originHostName</name><value><string>emasa</string></value></member>"
												+ "<member><name>originNodeType</name><value><string>AIR</string></value></member><member><name>originTimeStamp</name>"
												+ "<value><dateTime.iso8601>"+ SegmentConstants.TRANS_DATETIME + "+0000</dateTime.iso8601>"
												+ "</value></member><member><name>originTransactionID</name><value><string>"
												+ SegmentConstants.ORIGIN_TRANSACTION_ID + "</string>"
												+ "</value></member><member><name>pamInformationList</name><value><array>"
												+ "<data><value><struct><member><name>pamServiceID</name><value><i4>10</i4>"
												+ "</value></member><member><name>pamClassID</name><value><i4>10</i4></value>"
												+ "</member><member><name>scheduleID</name><value><i4>" + SegmentConstants.SCHEDULEID_VALUE + "</i4>"
												+ "</value></member><member><name>pamServicePriority</name><value><i4>2</i4>"
												+ "</value></member></struct></value></data></array></value></member><member>"
												+ "<name>subscriberNumber</name><value><string>" + SegmentConstants.MSISDN_VALUE + "</string>	</value>"
												+ "</member></struct></value></param></params></methodCall>";
	
	
	


}
