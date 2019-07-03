package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.ServiceExpiryMapping;

public interface ServiceExpireMappingDao {

	void createServExpiryMapping(ServiceExpiryMapping segSevSdMappingPojo);

	void deleteServExpiryMapping(int segServSdpMapId);

	void deleteListOfServExpiryMappingByServId(int segServId);

	void modifyServExpiryMapping(ServiceExpiryMapping segSevSdMappingPojo);

	List<String> fetchListOfServExpiryMappingByServId(int segServId);

	List<String> fetchListOfServExpiryMappingBySegmentId(int segmentId);

	List<String> fetchListOfServExpiryMapping(int segmentId, int segServId);

	List<String> fetchOfferIdList(int segmentId);

	List<String> fetchExpiryOfferIdByServiceName(String serviceName);
	
	String findSigleExpiryOfferIdByService(String serviceName);
}
