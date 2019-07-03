package com.ericsson.tmo.cc.segment.commons;

import java.util.HashMap;

public class NapDataPojo implements SegmentCommonPojo {

	private static final long serialVersionUID = 733445420782257516L;
	private String customerID = null;
	private String customerType = null;
	private String subscriberName = null;
	private String msisdn = null;
	private String statusCode = null;
	private String language = null;
	private String billCyclePeriod = "";
	private String services = "";
	private String expiryDate = null;
	private String accountType = null;
	private String accountSubType = null;
	private String ban = null;
	private String pairedMSISDN = null;
	private String imsi = null;
	private String vendorID = null;
	private String operatorID = null;
	private String usageLimit = null;
	private String pahMSISDN = null;
	private HashMap<String,String> features;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	 * @return the subscriberName
	 */
	public String getSubscriberName() {
		return subscriberName;
	}

	/**
	 * @param subscriberName
	 *            the subscriberName to set
	 */
	public void setSubscriberName(String subscriberName) {
		this.subscriberName = subscriberName;
	}

	/**
	 * @return the msisdn
	 */
	public String getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn
	 *            the msisdn to set
	 */
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 *            the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @param language
	 *            the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}

	/**
	 * @return the billCyclePeriod
	 */
	public String getBillCyclePeriod() {
		return billCyclePeriod;
	}

	/**
	 * @param billCyclePeriod
	 *            the billCyclePeriod to set
	 */
	public void setBillCyclePeriod(String billCyclePeriod) {
		this.billCyclePeriod = billCyclePeriod;
	}

	/**
	 * @return the services
	 */
	public String getServices() {
		return services;
	}

	/**
	 * @param services
	 *            the services to set
	 */
	public void setServices(String services) {
		this.services = services;
	}

	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * @param expiryDate
	 *            the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

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
	 * @return the bAN
	 */
	public String getBan() {
		return ban;
	}

	/**
	 * @param bAN
	 *            the bAN to set
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

	public void setImsi(String imsi)
	{
		this.imsi = imsi;
	}
	
	public String getImsi()
	{
		return this.imsi;
	}
	
	public void setVendorID(String id)
	{
		this.vendorID = id;
	}
	
	public String getVendorID()
	{
		return this.vendorID;
	}
	
	public void setOperatorID(String id)
	{
		this.operatorID = id;
	}
	
	public String getOperatorID()
	{
		return this.operatorID;
	}
	
	public void setUsageLimit(String limit)
	{
		this.usageLimit = limit;
	}
	
	public String getUsageLimit()
	{
		return this.usageLimit;
	}
	
	public void setPahMSISDN(String msisdn)
	{
		this.pahMSISDN = msisdn;
	}
	
	public String getPahMSISDN()
	{
		return this.pahMSISDN;
	}
	
	public void setFeatures(String service, String expiryDate)
	{
		if(this.features == null)
			features = new HashMap<String, String>();
		
		this.features.put(service, expiryDate);
	}
	
	public HashMap<String,String> getFeatures()
	{
		return this.features;
	}
	
}
