/**
 * 
 */
package com.ericsson.tmo.cc.segment.commons;

import com.ericsson.tmo.cc.segment.SegmentConstants;


public class ConfigPropertiesPojo implements SegmentCommonPojo {

	private static final long serialVersionUID = 4330943984218690270L;
	private String directMismatchToExternalFile = null;
	private String maxMismatchCount = null;
	private String correction = null;
	private String verify = null;
	private String enable = null;
	private String originOperatorId = null;
	private String cc = null;
	private String createSdp = null;
	private String fullSubscriberMismatch = null;
	private String productIdMismatch = null;
	private String subStatusMismatch = null;
	private String offerexpiryDate = null;
	private String customerIdMismatch = null;
	private String pamMismatch = null;
	private String utMismatch = null;
	
	private String deleteSdp = null;
	private String napUrl = null;
	private String napPartnerId = null;
	private String napPassword = null;
	private String partnerId = null;

	private String airUrl = null;
	private String eccUrl = null;
	private String eccPassword = null;
	private String daIDMismatch = null;

	private String reportName = null;
	private String segmentName = null;

	public String getVerify() {
		return verify;
	}

	public void setVerify(String verify) {
		this.verify = verify;
	}

	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getOriginOperatorId() {
		return originOperatorId;
	}

	public void setOriginOperatorId(String originOperatorId) {
		this.originOperatorId = originOperatorId;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getCreateSdp() {
		return createSdp;
	}

	public void setCreateSdp(String createSdp) {
		this.createSdp = createSdp;
	}

	public String getProductIdMismatch() {
		return productIdMismatch;
	}

	public void setProductIdMismatch(String productIdMismatch) {
		this.productIdMismatch = productIdMismatch;
	}

	public String getSubStatusMismatch() {
		return subStatusMismatch;
	}

	public void setSubStatusMismatch(String subStatusMismatch) {
		this.subStatusMismatch = subStatusMismatch;
	}

	public String getOfferexpiryDate() {
		return offerexpiryDate;
	}

	public void setOfferexpiryDate(String offerexpiryDate) {
		this.offerexpiryDate = offerexpiryDate;
	}

	public String getCustomerIdMismatch() {
		return customerIdMismatch;
	}

	public void setCustomerIdMismatch(String customerIdMismatch) {
		this.customerIdMismatch = customerIdMismatch;
	}

	public String getPamMismatch() {
		return pamMismatch;
	}

	public void setPamMismatch(String pamMismatch) {
		this.pamMismatch = pamMismatch;
	}
	
	
	public String getUtMismatch() {
		return utMismatch;
	}

	public void setUtMismatch(String utMismatch) {
		this.utMismatch = utMismatch;
	}

	public String getDeleteSdp() {
		return deleteSdp;
	}

	public void setDeleteSdp(String deleteSdp) {
		this.deleteSdp = deleteSdp;
	}

	public String getNapUrl() {
		return napUrl;
	}

	public void setNapUrl(String napUrl) {
		this.napUrl = napUrl;
	}

	public String getNapPartnerId() {
		return napPartnerId;
	}

	public void setNapPartnerId(String napPartnerId) {
		this.napPartnerId = napPartnerId;
	}

	public String getNapPassword() {
		return napPassword;
	}

	public void setNapPassword(String napPassword) {
		this.napPassword = napPassword;
	}

	public String getAirUrl() {
		return airUrl;
	}

	public void setAirUrl(String airUrl) {
		this.airUrl = airUrl;
	}

	public String getEccUrl() {
		return eccUrl;
	}

	public void setEccUrl(String eccUrl) {
		this.eccUrl = eccUrl;
	}

	public String getEccPassword() {
		return eccPassword;
	}

	public void setEccPassword(String eccPassword) {
		this.eccPassword = eccPassword;
	}

	public String getDaIDMismatch() {
		return daIDMismatch;
	}

	public void setDaIDMismatch(String daIDMismatch) {
		this.daIDMismatch = daIDMismatch;
	}

	public String getFullSubscriberMismatch() {
		return fullSubscriberMismatch;
	}

	public void setFullSubscriberMismatch(String fullSubscriberMismatch) {
		this.fullSubscriberMismatch = fullSubscriberMismatch;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCorrection() {
		return correction;
	}

	public void setCorrection(String correction) {
		this.correction = correction;
	}

	public String getDirectMismatchToExternalFile() {
		return directMismatchToExternalFile;
	}

	public void setDirectMismatchToExternalFile(String directMismatchToExternalFile) {
		if (directMismatchToExternalFile != null)
			this.directMismatchToExternalFile = directMismatchToExternalFile;
		else
			this.directMismatchToExternalFile = SegmentConstants.TRUE;
	}

	public String getMaxMismatchCount() {
		return maxMismatchCount;
	}

	public void setMaxMismatchCount(String maxMismatchCount) {
		this.maxMismatchCount = maxMismatchCount;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public String getSegmentName() {
		return segmentName;
	}

	public void setSegmentName(String segmentName) {
		this.segmentName = segmentName;
	}
	

}
