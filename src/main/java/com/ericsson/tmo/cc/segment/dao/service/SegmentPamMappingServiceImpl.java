package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import com.ericsson.tmo.cc.segment.dao.SegmentPamMappingDao;
import com.ericsson.tmo.cc.segment.dao.SegmentPamMappingDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.SegmentPamMapping;

public class SegmentPamMappingServiceImpl implements SegmentService {

	static SegmentPamMappingDao segmentPamMappingDao = new SegmentPamMappingDaoImpl();

	public SegmentPamMappingServiceImpl() {
			if(segmentPamMappingDao == null)
			segmentPamMappingDao = new SegmentPamMappingDaoImpl();

	}

	public static void createSegmentPamMapping(SegmentPamMapping segmentPamMapping) {
		segmentPamMappingDao.createSegmentPamMapping(segmentPamMapping);
	}

	public static void deleteSegmentPamMapping(int sevExpryMapId) {
		segmentPamMappingDao.deleteSegmentPamMapping(sevExpryMapId);
	}

	public static void modifySegmentPamMapping(SegmentPamMapping segmentPamMapping) {
		segmentPamMappingDao.modifySegmentPamMapping(segmentPamMapping);
	}

	public static List<String> fetchSegmentPamMappingBySegmentId(int segmentId, int billCyclePeriod) {
		return segmentPamMappingDao.fetchSegmentPamMappingBySegmentId(segmentId, billCyclePeriod);
	}

	public static List<String> fetchSegmentPamMapping(int segmentId, String periodDefinedStatus) {
		return segmentPamMappingDao.fetchSegmentPamMapping(segmentId, periodDefinedStatus);
	}
	
	public static String fetchPamScheduleByBillCycle(String segmentName, String napBillCyclePeriod) {
		return segmentPamMappingDao.fetchPamScheduleByBillCycle(segmentName,  napBillCyclePeriod);
	}

}