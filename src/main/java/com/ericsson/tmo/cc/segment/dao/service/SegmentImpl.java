package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.SegmentDao;
import com.ericsson.tmo.cc.segment.dao.SegmentDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.Segment;

public class SegmentImpl implements SegmentService {
	private static final Logger logger = LoggerFactory.getLogger(SegmentImpl.class);
	public static SegmentDao segmentDao;

	public SegmentImpl() {
		segmentDao = new SegmentDaoImpl();

	}

	public static void createSegment(Segment segmentpojo) {
		logger.debug("createSegment from ServiceIml");
		segmentDao.createSegment(segmentpojo);

	}

	public void deleteSegment(int segmentId) {
		segmentDao.deleteSegment(segmentId);
		logger.debug("deleteSegment from ServiceIml");

	}

	public void modifySegment(Segment segmentpojo) {
		logger.debug(" modifySegment from ServiceIml");
		segmentDao.modifySegment(segmentpojo);

	}

	public List<String> fetchSegmentDetailsById(int segmentId) {
		logger.debug(" fetchSegmentDetailsById  from ServiceIml");
		return segmentDao.fetchSegmentDetailsById(segmentId);
		 
	}

	public String fetchSegmentDetailsByName(int segment) {
		logger.debug(" fetchSegmentDetailsByName from ServiceIml");
		return segmentDao.fetchSegmentDetailsByName(segment);
		 
	}

}