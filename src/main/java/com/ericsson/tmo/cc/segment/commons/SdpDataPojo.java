package com.ericsson.tmo.cc.segment.commons;

import java.util.ArrayList;
import java.util.HashMap;

public class SdpDataPojo implements SegmentCommonPojo {

	private static final long serialVersionUID = -401347341769859985L;
	
	private String msisdn = null;
	private String customerID = null;
	private String customerType = null;
	private String operatorID = null;
	private ArrayList<String> productID = null;
	private String lco = "54000";
	private String providerOfferID = "94003";
	private String billCycleOfferID = "94500";
	
	//private String expiryDate = null;
	//private String scheduleID = null;
	//private String usageThresholdID = null;
	//private String dedicatedAccID = null;
	private SdpPamDataPojo pamDataPojo;
	private SdpOfferDataPojo offerDataPojo;
	private SdpDedicatedAccountDataPojo dedicatedAccountDataPojo;
	private SdpOfferArttributeDataPojo offerArttributeDataPojo;
	private SdpUsageThresholdDataPojo usageThresholdDataPojo;
	
	private HashMap<String,String> thresholdID = null;
	private HashMap<String,String> offerExpiryDate = null;
	private HashMap<String,String> offerStartDate = null;
	
	private Boolean subExists = false;
	private String accountSubType = null;
	private String accountType = null;
	private String ban = null;
	private String consumer = null;
	//private String offerProviderID = null;
	private ArrayList<String> pamID = null;
	private ArrayList<String> scheduleID = null;

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
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
	public ArrayList<String> getProductID() {
		return productID;
	}

	/**
	 * @param productID
	 *            the productID to set
	 */
	public void addProductID(String id) {
		if(productID == null)
			productID = new ArrayList<String>();
		
		this.productID.add(id);
	}
	
	public ArrayList<String> getPamID() {
		return pamID;
	}

	public void addPamID(String id) {
		if(pamID == null)
			pamID = new ArrayList<String>();
		
		this.pamID.add(id);
	}
	
	public ArrayList<String> getScheduleID() {
		return scheduleID;
	}

	public void addScheduleID(String id) {
		if(scheduleID == null)
			scheduleID = new ArrayList<String>();
		
		this.scheduleID.add(id);
	}

	/**
	 * @return the pamDataPojo
	 */
	public SdpPamDataPojo getPamDataPojo() {
		return pamDataPojo;
	}

	/**
	 * @param pamDataPojo
	 *            the pamDataPojo to set
	 */
	public void setPamDataPojo(SdpPamDataPojo pamDataPojo) {
		this.pamDataPojo = pamDataPojo;
	}

	/**
	 * @return the offerDataPojo
	 */
	public SdpOfferDataPojo getOfferDataPojo() {
		return offerDataPojo;
	}

	/**
	 * @param offerDataPojo
	 *            the offerDataPojo to set
	 */
	public void setOfferDataPojo(SdpOfferDataPojo offerDataPojo) {
		this.offerDataPojo = offerDataPojo;
	}

	/**
	 * @return the dedicatedAccountDataPojo
	 */
	public SdpDedicatedAccountDataPojo getDedicatedAccountDataPojo() {
		return dedicatedAccountDataPojo;
	}

	/**
	 * @param dedicatedAccountDataPojo
	 *            the dedicatedAccountDataPojo to set
	 */
	public void setDedicatedAccountDataPojo(SdpDedicatedAccountDataPojo dedicatedAccountDataPojo) {
		this.dedicatedAccountDataPojo = dedicatedAccountDataPojo;
	}

	/**
	 * @return the offerArttributeDataPojo
	 */
	public SdpOfferArttributeDataPojo getOfferArttributeDataPojo() {
		return offerArttributeDataPojo;
	}

	/**
	 * @param offerArttributeDataPojo
	 *            the offerArttributeDataPojo to set
	 */
	public void setOfferArttributeDataPojo(SdpOfferArttributeDataPojo offerArttributeDataPojo) {
		this.offerArttributeDataPojo = offerArttributeDataPojo;
	}

	/**
	 * @return the usageThresholdDataPojo
	 */
	public SdpUsageThresholdDataPojo getUsageThresholdDataPojo() {
		return usageThresholdDataPojo;
	}

	/**
	 * @param usageThresholdDataPojo
	 *            the usageThresholdDataPojo to set
	 */
	public void setUsageThresholdDataPojo(SdpUsageThresholdDataPojo usageThresholdDataPojo) {
		this.usageThresholdDataPojo = usageThresholdDataPojo;
	}
	
	public void setSubExists(Boolean b)
	{
		this.subExists = b;
	}

	public Boolean getSubExists()
	{
		return this.subExists;
	}
	
	public void setAccountSubType(String type)
	{
		this.accountSubType = type;
	}

	public String getAccountSubType()
	{
		return this.accountSubType;
	}
	
	public void setAccountType(String type)
	{
		this.accountType = type;
	}

	public String getAccountType()
	{
		return this.accountType;
	}
	
	public void setBan(String ban)
	{
		this.ban = ban;
	}

	public String getBan()
	{
		return this.ban;
	}
	
	public void setConsumer(String con)
	{
		this.consumer = con;
	}

	public String getConsumer()
	{
		return this.consumer;
	}
	
	public void setProviderOfferID(String id)
	{
		this.providerOfferID = id;
	}

	public String getLcoID()
	{
		return this.lco;
	}
	public void setLcoID(String id)
	{
		this.lco = id;
	}

	public String getProviderOfferID()
	{
		return this.providerOfferID;
	}
	
	public String getBillCycleOffer()
	{
		return this.billCycleOfferID;
	}
	public void setbillCycleOfferID(String id)
	{
		this.billCycleOfferID = id;
	}	
	
	public void addThresholdID(String id, String value)
	{
		if(this.thresholdID == null)
			thresholdID = new HashMap<String, String>();
		
		this.thresholdID.put(id, value);
	}
	
	public HashMap<String,String> getThresholdID()
	{
		return this.thresholdID;
	}
	
	public void addOfferExpiryDate(String id, String value)
	{
		if(this.offerExpiryDate == null)
			offerExpiryDate = new HashMap<String, String>();
		
		this.offerExpiryDate.put(id, value);
	}
	
	public HashMap<String,String> getOfferExpiryDate()
	{
		return this.offerExpiryDate;
	}
	
	public void addOfferStartDate(String id, String value)
	{
		if(this.offerStartDate == null)
			offerStartDate = new HashMap<String, String>();
		
		this.offerStartDate.put(id, value);
	}
	
	public HashMap<String,String> getOfferStartDate()
	{
		return this.offerStartDate;
	}
}
