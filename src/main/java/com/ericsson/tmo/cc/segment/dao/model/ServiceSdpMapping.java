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
@Table(name = "t_cc_service_sdp_mapping")
public class ServiceSdpMapping implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8633415090390966715L;
	@Id
	@Column(name = "service_sdpmap_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int servSdpMapId;
	@Column(name = "segment_id")
	private int segmentId;
	@Column(name = "service_id")
	private int serviceId;
	@Column(name = "offer_priority")
	private int offerPriority;
	@Column(name = "sdp_lco")
	private int sdpLco;
	@Column(name = "sdp_mrc")
	private int sdpMrc;
	@Column(name = "sdp_addon")
	private int sdpAddon;
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

	public int getServSdpMapId() {
		return servSdpMapId;
	}

	public void setServSdpMapId(int servSdpMapId) {
		this.servSdpMapId = servSdpMapId;
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

	public int getOfferPriority() {
		return offerPriority;
	}

	public void setOfferPriority(int offerPriority) {
		this.offerPriority = offerPriority;
	}

	public int getSdpLco() {
		return sdpLco;
	}

	public void setSdpLco(int sdpLco) {
		this.sdpLco = sdpLco;
	}

	public int getSdpMrc() {
		return sdpMrc;
	}

	public void setSdpMrc(int sdpMrc) {
		this.sdpMrc = sdpMrc;
	}

	public int getSdpAddon() {
		return sdpAddon;
	}

	public void setSdpAddon(int sdpAddon) {
		this.sdpAddon = sdpAddon;
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
