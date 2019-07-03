package com.ericsson.tmo.cc.segment.dao;


import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.ConsistencyRecords;

public interface ConsistencyDao {
	
	void createConsistencyDetails(ConsistencyRecords consistencyRecordsPojo);
	
	void deleteConsistencyDetails(String exestatus);
	
	void modifyConsistencyDetails(int segmentId);
	
	List<String> fetchInconsistencyDataById(int segmentId);
	
	


}
