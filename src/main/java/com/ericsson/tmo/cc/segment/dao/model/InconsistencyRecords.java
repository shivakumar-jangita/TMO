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
@Table(name = "t_cc_inconsistency_records")
public class InconsistencyRecords implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633415090390966715L;
	@Id
	@Column(name = "inconsistency_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inconsistencyId;
	@Column(name = "segment_id")
	private int segmentId;
	@Column(name = "msisdn")
	private long msisdn;
	@Column(name = "execution_status")
	private String executionStatus;
	@Column(name = "created_date")
	private Date createdDate;
	@Column(name = "created_user")
	private String createdUser;
	@Column(name = "modified_date")
	private Date modifiedDate;
	@Column(name = "modified_user")
	private String modifiedUser;

	public long getInconsistencyId() {
		return inconsistencyId;
	}

	public void setInconsistencyId(int inconsistencyId) {
		this.inconsistencyId = inconsistencyId;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(long msisdn) {
		this.msisdn = msisdn;
	}

	public String getExecutionStatus() {
		return executionStatus;
	}

	public void setExecutionStatus(String executionStatus) {
		this.executionStatus = executionStatus;
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

}
