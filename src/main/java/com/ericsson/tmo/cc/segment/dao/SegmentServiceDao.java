package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.SegmentServices;

public interface SegmentServiceDao {

	void createSegmentServices(SegmentServices segmentServicespojo);

	void deleteSegmentService(int segmentId);

	void modifySegmentService(int segmentId);

	List<String> fetchSegmentServiceById(int segmentId);

	List<String> fetchSegmentServDtlsByName(String segment);
	
	List<String> fetchSegmentServiceList(int segmentId);
	
	List<String> fetchAddonServiceListBySegment(String segment);
	
	List<String>  fetchMrcAddOnOfferListBySegment(String segment);
	
}
