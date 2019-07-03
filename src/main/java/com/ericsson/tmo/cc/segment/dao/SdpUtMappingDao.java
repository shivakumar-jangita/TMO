package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.SdpUtMapping;

public interface SdpUtMappingDao {

	void createSegServSdpUtMapping(SdpUtMapping segSevSdMappingPojo);

	void deleteSegServSdpUtMapping(int segServSdpMapId);

	void deleteListOfServSdpUtMappingByServId(int segServId);

	void modifySegServSdpUtMapping(SdpUtMapping segSevSdMappingPojo);

	List<String> fetchListOfServSdpUtMappingByServId(int segServId);

	List<String> fetchListOfServSdpUtMappingBySegmentId(int segmentId);

	List<String> fetchListOfSegServSdpUTMapping(int segmentId, int segServId);
	
	List<String> fetchThresholdValueList(int segmentId);
	
//	List<String> fetchThresholdValueListBySegment(String segmentName);

	String fetchListOfServSdpUtMappingByService(String service);

	String fetchAllThresholdBySegmentName(String segmentName);
	
	List<String> fetchListOfUtIdByServiceName(String service);
}
