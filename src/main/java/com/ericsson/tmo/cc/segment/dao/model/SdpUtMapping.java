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
@Table(name = "t_cc_sdp_ut_mapping")
public class SdpUtMapping implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633415090390966715L;
	@Id
	@Column(name = "sdp_UtMap_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int sdpUtMapId;
	@Column(name = "segment_id")
	private int segmentId;
	@Column(name = "service_id")
	private int serviceId;
	@Column(name = "sdp_Da_id")
	private int sdpDaId;
	@Column(name = "sdp_UtAttribute_name")
	private String sdpUtAttributeName;
	@Column(name = "sdp_UtAttribute_id")
	private int sdpUtAttributeId;
	@Column(name = "sdp_UtAttribute_value")
	private int sdpUtAttributeValue;
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

	public long getSdpUtMapId() {
		return sdpUtMapId;
	}

	public void setSdpUtMapId(int sdpUtMapId) {
		this.sdpUtMapId = sdpUtMapId;
	}

	public int getSegmentId() {
		return segmentId;
	}

	public void setSegmentId(int segmentId) {
		this.segmentId = segmentId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public int getSdpDaId() {
		return sdpDaId;
	}

	public void setSdpDaId(int sdpDaId) {
		this.sdpDaId = sdpDaId;
	}

	public String getSdpUtAttributeName() {
		return sdpUtAttributeName;
	}

	public void setSdpUtAttributeName(String sdpUtAttributeName) {
		this.sdpUtAttributeName = sdpUtAttributeName;
	}

	public int getSdpUtAttributeId() {
		return sdpUtAttributeId;
	}

	public void setSdpUtAttributeId(int sdpUtAttributeId) {
		this.sdpUtAttributeId = sdpUtAttributeId;
	}

	public long getSdpUtAttributeValue() {
		return sdpUtAttributeValue;
	}

	public void setSdpUtAttributeValue(int sdpUtAttributeValue) {
		this.sdpUtAttributeValue = sdpUtAttributeValue;
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
