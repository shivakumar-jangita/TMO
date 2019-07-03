package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.SegmentPamMapping;

public interface SegmentPamMappingDao {

	void createSegmentPamMapping(SegmentPamMapping segmentPamMapping);

	void deleteSegmentPamMapping (int sevExpryMapId) ;

	void modifySegmentPamMapping (SegmentPamMapping segmentPamMapping);

	List<String> fetchSegmentPamMappingBySegmentId(int segmentId, int billCyclePeriod) ;

	List<String> fetchSegmentPamMapping(int segmentId, String periodDefinedStatus) ;
	
	String fetchPamScheduleByBillCycle(String segmentName, String napBillCyclePeriod);
	
	
	

}
