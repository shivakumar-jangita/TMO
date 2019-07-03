package com.ericsson.tmo.cc.segment.commons;

public class SdpPamDataPojo implements SegmentCommonPojo {

	private static final long serialVersionUID = 3708855425907678279L;
	private String accountID = null;
	private String scheduleID = null;

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
	 * @return the scheduleID
	 */
	public String getScheduleID() {
		return scheduleID;
	}

	/**
	 * @param scheduleID
	 *            the scheduleID to set
	 */
	public void setScheduleID(String scheduleID) {
		this.scheduleID = scheduleID;
	}

}
