package com.ericsson.tmo.cc.segment.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * @author Siva Kumar Jangita
 *
 */
@Entity
@Table(name = "t_cc_consistency_records")
public class ConsistencyRecords implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633415090390966715L;
	@Id
	@Column(name = "consistency_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long consistencyId;
	@Column(name = "msisdn")
	private long msisdn;
	@Column(name = "correction_status")
	private String correctionStatus;
	@Column(name = "southbound_nodes")
	private String southboundNodes;
	@Column(name = "scenariosof_mismatch ")
	private String scenariosOfMismatch;
	@Column(name = "corrected_scenarios")
	private String correctedScenarios;
	@Column(name = "lco_expected")
	private String lcoExpected;
	@Column(name = "nap_customer_id")
	private String napCustomerId;
	@Column(name = "nap_customer_type")
	private String napCustomerType;
	@Column(name = "nap_sunscriber_name")
	private String napSubscriberName;
	@Column(name = "nap_statusCode")
	private int napStatusCode;
	@Column(name = "nap_billCycle_period")
	private int napBillCyclePeriod;
	@Column(name = "nap_service")
	private String napService;
	@Column(name = "nap_service_experiation")
	private String napServiceExpiration;
	@Column(name = "nap_paired_msisdn")
	private int napPairedMsisdn;
	@Column(name = "nap_acc_type")
	private int napAccType;
	@Column(name = "nap_acc_subType")
	private int napAccSubType;
	@Column(name = "nap_ban")
	private int napBan;
	@Column(name = "sdp_customeri_id")
	private int sdpCustomerId;
	@Column(name = "sdp_customer_type")
	private String sdpCustomerType;
	@Column(name = "sdp_operator_id")
	private int sdpOperatorId;
	@Column(name = "sdp_status")
	private String sdpStatus;
	@Column(name = "sdp_schedule_id")
	private int sdpScheduleId;
	@Column(name = "sdp_expected_pamSchedule_id")
	private int sdpExpectedPamScheduleId;
	@Column(name = "sdp_product_id")
	private int sdpProductId;
	@Column(name = "sdp_product_expirationUtc")
	private String sdpProductExpirationUtc;
	@Column(name = "SDP_CONSUME")
	private int sdpConsume;
	@Column(name = "sdp_acc_type")
	private int sdpAccType;
	@Column(name = "sdp_acc_subType")
	private int sdpAccSubType;
	@Column(name = "sdp_ban")
	private int sdpBan;
	@Column(name = "sdp_data_daId")
	private int sdpDataDaId;
	@Column(name = "sdp_data_Da_unitBalance")
	private int sdpDataDaUnitBalance;
	@Column(name = "sdp_data_Da_expiration")
	private int sdpDataDaExpiration;
	@Column(name = "expected_daId")
	private int expectedDaId;
	@Column(name = "sdp_utId")
	private int sdpUtId;
	@Column(name = "expected_utId")
	private int expectedUtId;
	@Column(name = "sdp_ut_value")
	private int sdpUtValue;
	@Column(name = "exception")
	private String exception;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_user")
	private String createdUser;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@Column(name = "modified_user")
	private String modifiedUser;

	/**
	 * @return the consistencyId
	 */
	public long getConsistencyId() {
		return consistencyId;
	}

	/**
	 * @param consistencyId
	 *            the consistencyId to set
	 */
	public void setConsistencyId(long consistencyId) {
		this.consistencyId = consistencyId;
	}

	/**
	 * @return the msisdn
	 */
	public long getMsisdn() {
		return msisdn;
	}

	/**
	 * @param msisdn
	 *            the msisdn to set
	 */
	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
	}

	/**
	 * @return the lcoExpected
	 */
	public String getLcoExpected() {
		return lcoExpected;
	}

	/**
	 * @param lcoExpected
	 *            the lcoExpected to set
	 */
	public void setLcoExpected(String lcoExpected) {
		this.lcoExpected = lcoExpected;
	}

	/**
	 * @return the napCustomerId
	 */
	public String getNapCustomerId() {
		return napCustomerId;
	}

	/**
	 * @param napCustomerId
	 *            the napCustomerId to set
	 */
	public void setNapCustomerId(String napCustomerId) {
		this.napCustomerId = napCustomerId;
	}

	/**
	 * @return the napCustomerType
	 */
	public String getNapCustomerType() {
		return napCustomerType;
	}

	/**
	 * @param napCustomerType
	 *            the napCustomerType to set
	 */
	public void setNapCustomerType(String napCustomerType) {
		this.napCustomerType = napCustomerType;
	}

	/**
	 * @return the napSubscriberName
	 */
	public String getNapSubscriberName() {
		return napSubscriberName;
	}

	/**
	 * @param napSubscriberName
	 *            the napSubscriberName to set
	 */
	public void setNapSubscriberName(String napSubscriberName) {
		this.napSubscriberName = napSubscriberName;
	}

	/**
	 * @return the napStatusCode
	 */
	public int getNapStatusCode() {
		return napStatusCode;
	}

	/**
	 * @param napStatusCode
	 *            the napStatusCode to set
	 */
	public void setNapStatusCode(int napStatusCode) {
		this.napStatusCode = napStatusCode;
	}

	/**
	 * @return the napBillCyclePeriod
	 */
	public int getNapBillCyclePeriod() {
		return napBillCyclePeriod;
	}

	/**
	 * @param napBillCyclePeriod
	 *            the napBillCyclePeriod to set
	 */
	public void setNapBillCyclePeriod(int napBillCyclePeriod) {
		this.napBillCyclePeriod = napBillCyclePeriod;
	}

	/**
	 * @return the napService
	 */
	public String getNapService() {
		return napService;
	}

	/**
	 * @param napService
	 *            the napService to set
	 */
	public void setNapService(String napService) {
		this.napService = napService;
	}

	/**
	 * @return the napServiceExpiration
	 */
	public String getNapServiceExpiration() {
		return napServiceExpiration;
	}

	/**
	 * @param napServiceExpiration
	 *            the napServiceExpiration to set
	 */
	public void setNapServiceExpiration(String napServiceExpiration) {
		this.napServiceExpiration = napServiceExpiration;
	}

	/**
	 * @return the sdpCustomerId
	 */
	public int getSdpCustomerId() {
		return sdpCustomerId;
	}

	/**
	 * @param sdpCustomerId
	 *            the sdpCustomerId to set
	 */
	public void setSdpCustomerId(int sdpCustomerId) {
		this.sdpCustomerId = sdpCustomerId;
	}

	/**
	 * @return the sdpCustomerType
	 */
	public String getSdpCustomerType() {
		return sdpCustomerType;
	}

	/**
	 * @param sdpCustomerType
	 *            the sdpCustomerType to set
	 */
	public void setSdpCustomerType(String sdpCustomerType) {
		this.sdpCustomerType = sdpCustomerType;
	}

	/**
	 * @return the sdpOperatorId
	 */
	public int getSdpOperatorId() {
		return sdpOperatorId;
	}

	/**
	 * @param sdpOperatorId
	 *            the sdpOperatorId to set
	 */
	public void setSdpOperatorId(int sdpOperatorId) {
		this.sdpOperatorId = sdpOperatorId;
	}

	/**
	 * @return the sdpStatus
	 */
	public String getSdpStatus() {
		return sdpStatus;
	}

	/**
	 * @param sdpStatus
	 *            the sdpStatus to set
	 */
	public void setSdpStatus(String sdpStatus) {
		this.sdpStatus = sdpStatus;
	}

	/**
	 * @return the sdpScheduleId
	 */
	public int getSdpScheduleId() {
		return sdpScheduleId;
	}

	/**
	 * @param sdpScheduleId
	 *            the sdpScheduleId to set
	 */
	public void setSdpScheduleId(int sdpScheduleId) {
		this.sdpScheduleId = sdpScheduleId;
	}

	/**
	 * @return the sdpExpectedPamScheduleId
	 */
	public int getSdpExpectedPamScheduleId() {
		return sdpExpectedPamScheduleId;
	}

	/**
	 * @param sdpExpectedPamScheduleId
	 *            the sdpExpectedPamScheduleId to set
	 */
	public void setSdpExpectedPamScheduleId(int sdpExpectedPamScheduleId) {
		this.sdpExpectedPamScheduleId = sdpExpectedPamScheduleId;
	}

	/**
	 * @return the sdpProductId
	 */
	public int getSdpProductId() {
		return sdpProductId;
	}

	/**
	 * @param sdpProductId
	 *            the sdpProductId to set
	 */
	public void setSdpProductId(int sdpProductId) {
		this.sdpProductId = sdpProductId;
	}

	/**
	 * @return the sdpProductExpirationUtc
	 */
	public String getSdpProductExpirationUtc() {
		return sdpProductExpirationUtc;
	}

	/**
	 * @param sdpProductExpirationUtc
	 *            the sdpProductExpirationUtc to set
	 */
	public void setSdpProductExpirationUtc(String sdpProductExpirationUtc) {
		this.sdpProductExpirationUtc = sdpProductExpirationUtc;
	}

	public String getException() {
		return exception;
	}

	/**
	 * @param exception
	 *            the exception to set
	 */
	public void setException(String exception) {
		this.exception = exception;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate
	 *            the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @param createdUser
	 *            the createdUser to set
	 */
	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate
	 *            the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getCorrectionStatus() {
		return correctionStatus;
	}

	public void setCorrectionStatus(String correctionStatus) {
		this.correctionStatus = correctionStatus;
	}

	public String getSouthboundNodes() {
		return southboundNodes;
	}

	public void setSouthboundNodes(String southboundNodes) {
		this.southboundNodes = southboundNodes;
	}

	public String getScenariosOfMismatch() {
		return scenariosOfMismatch;
	}

	public void setScenariosOfMismatch(String scenariosOfMismatch) {
		this.scenariosOfMismatch = scenariosOfMismatch;
	}

	public String getCorrectedScenarios() {
		return correctedScenarios;
	}

	public void setCorrectedScenarios(String correctedScenarios) {
		this.correctedScenarios = correctedScenarios;
	}

	public int getNapPairedMsisdn() {
		return napPairedMsisdn;
	}

	public void setNapPairedMsisdn(int napPairedMsisdn) {
		this.napPairedMsisdn = napPairedMsisdn;
	}

	public int getNapAccType() {
		return napAccType;
	}

	public void setNapAccType(int napAccType) {
		this.napAccType = napAccType;
	}

	public int getNapAccSubType() {
		return napAccSubType;
	}

	public void setNapAccSubType(int napAccSubType) {
		this.napAccSubType = napAccSubType;
	}

	public int getNapBan() {
		return napBan;
	}

	public void setNapBan(int napBan) {
		this.napBan = napBan;
	}

	public int getSdpConsume() {
		return sdpConsume;
	}

	public void setSdpConsume(int sdpConsume) {
		this.sdpConsume = sdpConsume;
	}

	public int getSdpAccType() {
		return sdpAccType;
	}

	public void setSdpAccType(int sdpAccType) {
		this.sdpAccType = sdpAccType;
	}

	public int getSdpAccSubType() {
		return sdpAccSubType;
	}

	public void setSdpAccSubType(int sdpAccSubType) {
		this.sdpAccSubType = sdpAccSubType;
	}

	public int getSdpBan() {
		return sdpBan;
	}

	public void setSdpBan(int sdpBan) {
		this.sdpBan = sdpBan;
	}

	public int getSdpDataDaId() {
		return sdpDataDaId;
	}

	public void setSdpDataDaId(int sdpDataDaId) {
		this.sdpDataDaId = sdpDataDaId;
	}

	public int getSdpDataDaUnitBalance() {
		return sdpDataDaUnitBalance;
	}

	public void setSdpDataDaUnitBalance(int sdpDataDaUnitBalance) {
		this.sdpDataDaUnitBalance = sdpDataDaUnitBalance;
	}

	public int getSdpDataDaExpiration() {
		return sdpDataDaExpiration;
	}

	public void setSdpDataDaExpiration(int sdpDataDaExpiration) {
		this.sdpDataDaExpiration = sdpDataDaExpiration;
	}

	public int getExpectedDaId() {
		return expectedDaId;
	}

	public void setExpectedDaId(int expectedDaId) {
		this.expectedDaId = expectedDaId;
	}

	public int getSdpUtId() {
		return sdpUtId;
	}

	public void setSdpUtId(int sdpUtId) {
		this.sdpUtId = sdpUtId;
	}

	public int getExpectedUtId() {
		return expectedUtId;
	}

	public void setExpectedUtId(int expectedUtId) {
		this.expectedUtId = expectedUtId;
	}

	public int getSdpUtValue() {
		return sdpUtValue;
	}

	public void setSdpUtValue(int sdpUtValue) {
		this.sdpUtValue = sdpUtValue;
	}

	/**
	 * @return the modifiedUser
	 */
	public String getModifiedUser() {
		return modifiedUser;
	}

	/**
	 * @param modifiedUser
	 *            the modifiedUser to set
	 */
	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}
	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
