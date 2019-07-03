package com.ericsson.tmo.cc.segment.commons;

public class SdpDedicatedAccountDataPojo implements SegmentCommonPojo {

	private static final long serialVersionUID = -197110907974794935L;
	private String accountID = null;
	private String dedicatedAccID = null;
	
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
	 * @return the dedicatedAccID
	 */
	public String getDedicatedAccID() {
		return dedicatedAccID;
	}

	/**
	 * @param dedicatedAccID
	 *            the dedicatedAccID to set
	 */
	public void setDedicatedAccID(String dedicatedAccID) {
		this.dedicatedAccID = dedicatedAccID;
	}

}
