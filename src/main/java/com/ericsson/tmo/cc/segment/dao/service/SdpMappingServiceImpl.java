package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.SdpMappingDao;
import com.ericsson.tmo.cc.segment.dao.SdpMappingDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.ServiceSdpMapping;

public class SdpMappingServiceImpl implements SegmentService {

	static SdpMappingDao sdpMappingDao = new SdpMappingDaoImpl();

	public SdpMappingServiceImpl() {
		sdpMappingDao = new SdpMappingDaoImpl();

	}
	public void createSegServSdpMapping(ServiceSdpMapping segSevSdMappingPojo) {
		sdpMappingDao.createSegServSdpMapping(segSevSdMappingPojo);

	}

	public void deleteSegServSdpMapping(int segServSdpMapId) {
		sdpMappingDao.deleteSegServSdpMapping(segServSdpMapId);

	}

	public void deleteListOfServSdpMappingByServId(int segServId) {
		sdpMappingDao.deleteListOfServSdpMappingByServId(segServId);

	}

	public void modifySegServSdpMapping(ServiceSdpMapping segSevSdMappingPojo) {
		sdpMappingDao.modifySegServSdpMapping(segSevSdMappingPojo);

	}

	public List<String> fetchListOfServSdpMappingByServId(int segServId) {
		return sdpMappingDao.fetchListOfServSdpMappingByServId(segServId);
		 
		

	}

	public List<String> fetchListOfServSdpMappingBySegmentId(int segmentId) {
		return sdpMappingDao.fetchListOfServSdpMappingBySegmentId(segmentId);
		 
		

	}

	public List<String> fetchListOfSegServSdpMapping(int segmentId, int segServId) {
		return sdpMappingDao.fetchListOfSegServSdpMapping(segmentId, segServId);
		 
		

	}
	public static List<String>  fetchListOfOfferIdByServiceName(String serviceName) {
		return sdpMappingDao.fetchListOfOfferIdByServiceName(serviceName);
		 
		

	}
}