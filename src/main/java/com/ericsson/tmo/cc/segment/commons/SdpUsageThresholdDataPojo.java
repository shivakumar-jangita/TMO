package com.ericsson.tmo.cc.segment.commons;

public class SdpUsageThresholdDataPojo implements SegmentCommonPojo{

	private static final long serialVersionUID = -8358408129061740116L;
	private String accountID = null;
	private String usageThresholdID = null;
	
	

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * @return the accountID
	 */
	public String getAccountID() {
		return accountID;
	}

	/**
	 * @param accountID
	 *            the accountID to set
	 */
	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	/**
	 * @return the usageThresholdID
	 */
	public String getUsageThresholdID() {
		return usageThresholdID;
	}

	/**
	 * @param usageThresholdID
	 *            the usageThresholdID to set
	 */
	public void setUsageThresholdID(String usageThresholdID) {
		this.usageThresholdID = usageThresholdID;
	}

}
