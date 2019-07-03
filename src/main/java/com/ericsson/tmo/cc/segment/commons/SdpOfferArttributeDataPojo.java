package com.ericsson.tmo.cc.segment.commons;

public class SdpOfferArttributeDataPojo implements SegmentCommonPojo{

	private static final long serialVersionUID = -5102743288198440078L;
	private String accountID = null;
	private String customerID = null;
	private String customerType = null;
	private String operatorID = null;
	private String productID = null;
	private String accountType = null;
	private String accountSubType = null;
	private String ban = null;
	

	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}

	/**
	 * @param accountType
	 *            the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	/**
	 * @return the accountSubType
	 */
	public String getAccountSubType() {
		return accountSubType;
	}

	/**
	 * @param accountSubType
	 *            the accountSubType to set
	 */
	public void setAccountSubType(String accountSubType) {
		this.accountSubType = accountSubType;
	}

	/**
	 * @return the ban
	 */
	public String getBan() {
		return ban;
	}

	/**
	 * @param ban
	 *            the ban to set
	 */
	public void setBan(String ban) {
		this.ban = ban;
	}

	/**
	 * @return the pairedMSISDN
	 */
	public String getPairedMSISDN() {
		return pairedMSISDN;
	}

	/**
	 * @param pairedMSISDN
	 *            the pairedMSISDN to set
	 */
	public void setPairedMSISDN(String pairedMSISDN) {
		this.pairedMSISDN = pairedMSISDN;
	}

	private String pairedMSISDN = null;

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
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @param customerID
	 *            the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @return the customerType
	 */
	public String getCustomerType() {
		return customerType;
	}

	/**
	 * @param customerType
	 *            the customerType to set
	 */
	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	/**
	 * @return the operatorID
	 */
	public String getOperatorID() {
		return operatorID;
	}

	/**
	 * @param operatorID
	 *            the operatorID to set
	 */
	public void setOperatorID(String operatorID) {
		this.operatorID = operatorID;
	}

	/**
	 * @return the productID
	 */
	public String getProductID() {
		return productID;
	}

	/**
	 * @param productID
	 *            the productID to set
	 */
	public void setProductID(String productID) {
		this.productID = productID;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
