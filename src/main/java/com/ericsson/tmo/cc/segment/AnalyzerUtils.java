package com.ericsson.tmo.cc.segment;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.utils.GenericUtils;

public class AnalyzerUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(AnalyzerUtils.class);
	
	public static ConfigPropertiesPojo configPropertiesPojo = new ConfigPropertiesPojo();

	
	
	/*#Wed Mar 22 15:47:24 IST 2017
	DA_ID_Mismatch=true
	Correction=enabled
	ECC_URL=http\://10.169.71.207\:8081/cwf/esp/query
	direct_mismatch_to_external_file=true
	originOperatorID=CC
	SubStatus_Mismatch=true
	DeleteSDP=true
	max_mismatch_count=500
	NAP_URL=http\://10.178.69.1\:8002/PPListener/partnerpublisher
	PAM_Mismatch=true
	OfferExpiryDate_Mismatch=true
	CustomerID_Mismatch=true
	AIR_URL=http\://10.169.71.57\:10011/Air
	UT_Mismatch=true
	NAP_PartnerID=53
	NAP_Password=MBC@test
	CreateSDP=true
	Full_Subscriber_Mismatch=true
	Verify=enabled
	ECC_Password=mykey
	ProductID_Mismatch=true
*/

	public static ConfigPropertiesPojo initConfigData() {
		
		logger.info("Controll reached in initConfigData() -- starts here");
		Map<String, String> configPropertiesMap = null;
		try{
		configPropertiesMap = GenericUtils
				.fetchConfigDataByResouceBundle(SegmentConstants.NODE_CONFIGDATA_PATH);
		}catch(Exception exception){
			logger.error("exception :"+exception);
		}
		
		if (configPropertiesMap != null && configPropertiesMap.size() > 0) {
			
			if (configPropertiesMap.get(SegmentConstants.DIRECT_MISMATCH_TO_EXTERNAL_FILE) != null) {
				configPropertiesPojo.setDirectMismatchToExternalFile(
						configPropertiesMap.get(SegmentConstants.DIRECT_MISMATCH_TO_EXTERNAL_FILE));
			}
			if (configPropertiesMap.get(SegmentConstants.CORRECTION) != null)
				configPropertiesPojo.setCorrection(configPropertiesMap.get(SegmentConstants.CORRECTION));

			if (configPropertiesMap.get(SegmentConstants.ENABLE) != null)
				configPropertiesPojo.setEnable(configPropertiesMap.get(SegmentConstants.ENABLE));
			
			if (configPropertiesMap.get(SegmentConstants.PARTNERID) != null)
				configPropertiesPojo.setPartnerId(configPropertiesMap.get(SegmentConstants.PARTNERID));

			if (configPropertiesMap.get(SegmentConstants.ORIGINOPERATORID) != null)
				configPropertiesPojo.setOriginOperatorId(configPropertiesMap.get(SegmentConstants.ORIGINOPERATORID));

			if (configPropertiesMap.get(SegmentConstants.CC) != null)
				configPropertiesPojo.setCc(configPropertiesMap.get(SegmentConstants.CC));

			if (configPropertiesMap.get(SegmentConstants.CREATE_SDP) != null)
				configPropertiesPojo.setCreateSdp(configPropertiesMap.get(SegmentConstants.CREATE_SDP));

			if (configPropertiesMap.get(SegmentConstants.PRODUCTID_MISMATCH) != null)
				configPropertiesPojo.setProductIdMismatch(configPropertiesMap.get(SegmentConstants.PRODUCTID_MISMATCH));

			if (configPropertiesMap.get(SegmentConstants.OFFEREXPIRYDATE) != null)
				configPropertiesPojo.setOfferexpiryDate(configPropertiesMap.get(SegmentConstants.OFFEREXPIRYDATE));

			if (configPropertiesMap.get(SegmentConstants.SUBSTATUS_MISMATCH) != null)
				configPropertiesPojo.setSubStatusMismatch(configPropertiesMap.get(SegmentConstants.SUBSTATUS_MISMATCH));
			if (configPropertiesMap.get(SegmentConstants.CUSTOMERID_MISMATCH) != null)
				configPropertiesPojo
						.setCustomerIdMismatch(configPropertiesMap.get(SegmentConstants.CUSTOMERID_MISMATCH));

			if (configPropertiesMap.get(SegmentConstants.PAM_MISMATCH) != null)
				configPropertiesPojo.setPamMismatch(configPropertiesMap.get(SegmentConstants.PAM_MISMATCH));
			
			if (configPropertiesMap.get(SegmentConstants.UT_MISMATCH) != null)
				configPropertiesPojo.setUtMismatch(configPropertiesMap.get(SegmentConstants.UT_MISMATCH));
			
			if (configPropertiesMap.get(SegmentConstants.DELETESDP) != null)
				configPropertiesPojo.setDeleteSdp(configPropertiesMap.get(SegmentConstants.DELETESDP));

			if (configPropertiesMap.get(SegmentConstants.ECC_URL) != null)
				configPropertiesPojo.setEccUrl(configPropertiesMap.get(SegmentConstants.ECC_URL));

			if (configPropertiesMap.get(SegmentConstants.ECC_PASSWORD) != null)
				configPropertiesPojo.setEccPassword(configPropertiesMap.get(SegmentConstants.ECC_PASSWORD));

			if (configPropertiesMap.get(SegmentConstants.NAP_URL) != null)
				configPropertiesPojo.setNapUrl(configPropertiesMap.get(SegmentConstants.NAP_URL));

			if (configPropertiesMap.get(SegmentConstants.NAP_PASSWORD) != null)
				configPropertiesPojo.setNapPassword(configPropertiesMap.get(SegmentConstants.NAP_PASSWORD));

			if (configPropertiesMap.get(SegmentConstants.NAP_PARTNERID) != null)
				configPropertiesPojo.setNapPartnerId(configPropertiesMap.get(SegmentConstants.NAP_PARTNERID));

			if (configPropertiesMap.get(SegmentConstants.AIR_URL) != null)
				configPropertiesPojo.setAirUrl(configPropertiesMap.get(SegmentConstants.AIR_URL));
			
			if (configPropertiesMap.get(SegmentConstants.FULL_SUBSCRIBER_MISMATCH) != null)
				configPropertiesPojo
						.setFullSubscriberMismatch(configPropertiesMap.get(SegmentConstants.FULL_SUBSCRIBER_MISMATCH));

			if (configPropertiesMap.get(SegmentConstants.DA_ID_MISMATCH) != null)
				configPropertiesPojo.setDaIDMismatch(configPropertiesMap.get(SegmentConstants.DA_ID_MISMATCH));
			
			if (configPropertiesMap.get(SegmentConstants.VERIFY) != null)
				configPropertiesPojo.setVerify(configPropertiesMap.get(SegmentConstants.VERIFY));
			
			if (configPropertiesMap.get(SegmentConstants.MAX_MISMATCH_COUNT) != null)
				configPropertiesPojo.setMaxMismatchCount(configPropertiesMap.get(SegmentConstants.MAX_MISMATCH_COUNT));

			if (configPropertiesMap.get(SegmentConstants.OFFEREXPIRYDATE) != null)
				configPropertiesPojo.setOfferexpiryDate(configPropertiesMap.get(SegmentConstants.OFFEREXPIRYDATE));
			
			
		}
		logger.info("Controll reached in initConfigData() -- ends here");
		return configPropertiesPojo;
	}

	

}
