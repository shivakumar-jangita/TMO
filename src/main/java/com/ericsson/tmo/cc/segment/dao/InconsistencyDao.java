package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.model.InconsistencyRecords;

public interface InconsistencyDao {

	void createInconsistencyDetails(InconsistencyRecords inconsistencyRecordsPojo);

	void deleteInconsistencyDetails(String exestatus);

	void modifyInconsistencyDetails(int segmentId);

	List<String> fetchInconsistencyMsisdnData(int segmentId);

	List<Integer> fetchUniqueSegmentIdList();

}
