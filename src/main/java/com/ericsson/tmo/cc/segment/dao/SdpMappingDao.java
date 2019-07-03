package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.ServiceSdpMapping;

public interface SdpMappingDao {

	void createSegServSdpMapping(ServiceSdpMapping segSevSdMappingPojo);

	void deleteSegServSdpMapping(int segServSdpMapId);

	void deleteListOfServSdpMappingByServId(int segServId);

	void modifySegServSdpMapping(ServiceSdpMapping segSevSdMappingPojo);

	List<String> fetchListOfServSdpMappingByServId(int segServId);

	List<String> fetchListOfServSdpMappingBySegmentId(int segmentId);

	List<String> fetchListOfSegServSdpMapping(int segmentId, int segServId);

	List<String> fetchListOfOfferIdByServiceName(String serviceName);

}
