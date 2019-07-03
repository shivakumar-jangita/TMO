package com.ericsson.tmo.cc.segment.dao;


import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.Segment;

public interface SegmentDao {
	
	void createSegment(Segment segmentpojo);
	
	void deleteSegment(int segmentId);
	
	void modifySegment(Segment segmentpojo);
	
	List<String> fetchSegmentDetailsById(int segmentId);
	
	String fetchSegmentDetailsByName(int segmentId);


}
