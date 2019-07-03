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
@Table(name = "t_cc_service_expiry_mapping")
public class ServiceExpiryMapping implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633415090390966715L;
	@Id
	@Column(name = "serv_expiryMap_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int servExpiryMapId;
	@Column(name = "segment_id")
	private int segmentId;
	@Column(name = "service_id")
	private int serviceId;
	@Column(name = "offer_id")
	private int offerId;
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
	
	public long getServExpiryMapId() {
		return servExpiryMapId;
	}

	public void setServExpiryMapId(int servExpiryMapId) {
		this.servExpiryMapId = servExpiryMapId;
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

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
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
