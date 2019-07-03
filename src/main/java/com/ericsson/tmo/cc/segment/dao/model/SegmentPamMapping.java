package com.ericsson.tmo.cc.segment.dao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "t_cc_segment_pam_mapping")
public class SegmentPamMapping implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633415090390966715L;
	@Id
	@Column(name = "pamMap_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pamMapId;
	@Column(name = "segment_id")
	private int segmentId;
	@Column(name = "nap_BillCycle_period")
	private int napBillCyclePeriod;
	@Column(name = "monthly_Pam_scheduleId")
	private int monthlyPamScheduleId;
	@Column(name = "daily_Pam_scheduleId")
	private int dailyPamScheduleId;
	@Column(name = "isPeriod_defined")
	private String isPeriodDefined;
	@Column(name = "service_id")
	private int serviceId;
	@Column(name = "description")
	private String description;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_user")
	private String createdUser;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@Column(name = "modified_user")
	private String modifiedUser;
	@Column(name = "deletion_status")
	private String deletionStatus;

	public long getPamMapId() {
		return pamMapId;
	}

	public void PamMapId(int pamMapId) {
		this.pamMapId = pamMapId;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public int getNapBillCyclePeriod() {
		return napBillCyclePeriod;
	}

	public void setNapBillCyclePeriod(int napBillCyclePeriod) {
		this.napBillCyclePeriod = napBillCyclePeriod;
	}

	public int getMonthlyPamScheduleId() {
		return monthlyPamScheduleId;
	}

	public void setMonthlyPamScheduleId(int monthlyPamScheduleId) {
		this.monthlyPamScheduleId = monthlyPamScheduleId;
	}

	public int getDailyPamScheduleId() {
		return dailyPamScheduleId;
	}

	public void setDailyPamScheduleId(int dailyPamScheduleId) {
		this.dailyPamScheduleId = dailyPamScheduleId;
	}

	public String getIsPeriodDefined() {
		return isPeriodDefined;
	}

	public void setIsPeriodDefined(String isPeriodDefined) {
		this.isPeriodDefined = isPeriodDefined;
	}
	
	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedUser() {
		return createdUser;
	}

	public void setCreatedUser(String createdUser) {
		this.createdUser = createdUser;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedUser() {
		return modifiedUser;
	}

	public void setModifiedUser(String modifiedUser) {
		this.modifiedUser = modifiedUser;
	}

	public String getDeletionStatus() {
		return deletionStatus;
	}

	public void setDeletionStatus(String deletionStatus) {
		this.deletionStatus = deletionStatus;
	}

	


}
