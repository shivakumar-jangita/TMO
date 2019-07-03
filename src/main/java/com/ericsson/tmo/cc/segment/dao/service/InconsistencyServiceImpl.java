package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.InconsistencyDao;
import com.ericsson.tmo.cc.segment.dao.InconsistencyDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.InconsistencyRecords;

public class InconsistencyServiceImpl implements SegmentService {

	public static InconsistencyDao inconsistencyDao;

	public InconsistencyServiceImpl() {
		inconsistencyDao = new InconsistencyDaoImpl();

	}

	public static void createInconsistencyDetails(InconsistencyRecords inconsistencyRecordsPojo) {

		inconsistencyDao.createInconsistencyDetails(inconsistencyRecordsPojo);

	}

	public void deleteInconsistencyDetails(String status) {
		inconsistencyDao.deleteInconsistencyDetails(status);

	}

	public void modifyInconsistencyDetails(int segmentId) {

		inconsistencyDao.modifyInconsistencyDetails(segmentId);

	}

	public List<String> fetchInconsistencyMsisdnData(int segmentId) {
		return inconsistencyDao.fetchInconsistencyMsisdnData(segmentId);

	}

	/*public String fetchSegmentDetailsById(int segmentId) {
		return inconsistencyDao.fetchSegmentDetailsByName(segmentId);

	}*/

	public List<Integer> fetchUniqueSegmentIdList() {
		return inconsistencyDao.fetchUniqueSegmentIdList();

	}

}