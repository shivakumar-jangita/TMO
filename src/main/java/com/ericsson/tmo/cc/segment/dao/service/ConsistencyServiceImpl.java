package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.ConsistencyDao;
import com.ericsson.tmo.cc.segment.dao.ConsistencyDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.ConsistencyRecords;

public class ConsistencyServiceImpl implements SegmentService {

	static ConsistencyDao consistencyDao;

	public ConsistencyServiceImpl() {
		
		consistencyDao= new ConsistencyDaoImpl();
	}
	public void createConsistencyDetails(ConsistencyRecords consistencyRecordsPojo) {

	}

	public void deleteConsistencyDetails(String exestatus) {

	}

	public void modifyConsistencyDetails(int segmentId) {

	}

	public List<ConsistencyServiceImpl> fetchInconsistencyDataById(int segmentId) {

		return null;
	}
	
}