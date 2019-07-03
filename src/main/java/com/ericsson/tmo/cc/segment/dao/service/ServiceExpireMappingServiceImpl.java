package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.ServiceExpireMappingDao;
import com.ericsson.tmo.cc.segment.dao.ServiceExpireMappingDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.ServiceExpiryMapping;

public class ServiceExpireMappingServiceImpl implements SegmentService {

	static ServiceExpireMappingDao serviceExpireMappingDao = new ServiceExpireMappingDaoImpl();

	public ServiceExpireMappingServiceImpl() {
		if(serviceExpireMappingDao == null)
			serviceExpireMappingDao = new ServiceExpireMappingDaoImpl();
	}

	public static void createServExpiryMapping(ServiceExpiryMapping segSevSdMappingPojo) {
		serviceExpireMappingDao.createServExpiryMapping(segSevSdMappingPojo);
	}

	public static void deleteServExpiryMapping(int segServSdpMapId) {
		serviceExpireMappingDao.deleteServExpiryMapping(segServSdpMapId);
	}

	public static void deleteListOfServExpiryMappingByServId(int segServId) {
		serviceExpireMappingDao.deleteListOfServExpiryMappingByServId(segServId);
	}

	public static void modifyServExpiryMapping(ServiceExpiryMapping segSevSdMappingPojo) {
		serviceExpireMappingDao.modifyServExpiryMapping(segSevSdMappingPojo);
	}

	public static List<String> fetchListOfServExpiryMappingByServId(int segServId) {
		return serviceExpireMappingDao.fetchListOfServExpiryMappingByServId(segServId);
	}

	public static List<String> fetchListOfServExpiryMappingBySegmentId(int segmentId) {
		return serviceExpireMappingDao.fetchListOfServExpiryMappingBySegmentId(segmentId);
	}

	public static List<String> fetchListOfServExpiryMapping(int segmentId, int segServId) {
		return serviceExpireMappingDao.fetchListOfServExpiryMapping(segmentId, segServId);
	}
	public static List<String> fetchOfferIdList(int segmentId) {
		return serviceExpireMappingDao.fetchOfferIdList(segmentId);
	}
	
	public static List<String> fetchExpiryOfferIdByServiceName(String serviceName) {
		return serviceExpireMappingDao.fetchExpiryOfferIdByServiceName(serviceName);
	}
	
	public static String findSigleExpiryOfferIdByService(String serviceName) {
		return serviceExpireMappingDao.findSigleExpiryOfferIdByService(serviceName);
	}
	
	
	
}