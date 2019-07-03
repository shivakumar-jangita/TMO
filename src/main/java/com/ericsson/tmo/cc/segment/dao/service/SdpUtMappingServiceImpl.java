package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.SdpUtMappingDao;
import com.ericsson.tmo.cc.segment.dao.SdpUtMappingDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.SdpUtMapping;

public class SdpUtMappingServiceImpl implements SegmentService {

	static SdpUtMappingDao sdpUtMappingDao= new SdpUtMappingDaoImpl() ;

	public SdpUtMappingServiceImpl() {
		sdpUtMappingDao = new SdpUtMappingDaoImpl();

	}

	public static void createSegServSdpUtMapping(SdpUtMapping segSevSdMappingPojo) {
		sdpUtMappingDao.createSegServSdpUtMapping(segSevSdMappingPojo);
	}

	public static void deleteSegServSdpUtMapping(int segServSdpMapId) {
		sdpUtMappingDao.deleteSegServSdpUtMapping(segServSdpMapId);
	}

	public static void deleteListOfServSdpUtMappingByServId(int segServId) {
		sdpUtMappingDao.deleteListOfServSdpUtMappingByServId(segServId);
	}

	public static void modifySegServSdpUtMapping(SdpUtMapping segSevSdMappingPojo) {
		sdpUtMappingDao.modifySegServSdpUtMapping(segSevSdMappingPojo);
	}

	public static List<String> fetchListOfServSdpUtMappingByServId(int segServId) {
		return sdpUtMappingDao.fetchListOfServSdpUtMappingByServId(segServId);
	}

	public static List<String> fetchListOfServSdpUtMappingBySegmentId(int segmentId) {
		return sdpUtMappingDao.fetchListOfServSdpUtMappingBySegmentId(segmentId);

	}

	public static List<String> fetchListOfSegServSdpUTMapping(int segmentId, int segServId) {
		return sdpUtMappingDao.fetchListOfSegServSdpUTMapping(segmentId, segmentId);
	}

	public static List<String> fetchThresholdValueList(int segmentId) {
		return sdpUtMappingDao.fetchThresholdValueList(segmentId);
	}
	
/*	public List<String> fetchThresholdValueListBySegment(String segmentName) {
		return sdpUtMappingDao.fetchThresholdValueListBySegment(segmentName);
	}*/
	
	public static String fetchAllThresholdBySegmentName(String segmentName) {
		return sdpUtMappingDao.fetchAllThresholdBySegmentName(segmentName);
	}

	public static String fetchListOfServSdpUtMappingByService(String service) {
		return sdpUtMappingDao.fetchListOfServSdpUtMappingByService(service);
	}
	
	public static List<String> fetchListOfUtIdByServiceName(String service) {
		return sdpUtMappingDao.fetchListOfUtIdByServiceName(service);
	}
	
}