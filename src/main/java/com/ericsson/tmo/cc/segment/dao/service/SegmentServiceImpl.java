package com.ericsson.tmo.cc.segment.dao.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.SegmentServiceDao;
import com.ericsson.tmo.cc.segment.dao.SegmentServiceDaoImpl;
import com.ericsson.tmo.cc.segment.dao.model.SegmentServices;

public class SegmentServiceImpl implements SegmentService {
	private static final Logger logger = LoggerFactory.getLogger(SegmentServiceImpl.class);
	static SegmentServiceDao segmentServiceDao = new SegmentServiceDaoImpl();

	public SegmentServiceImpl() {
		segmentServiceDao = new SegmentServiceDaoImpl();

	}

	public static void createSegmentServices(SegmentServices segmentServicespojo) {
		logger.debug("createSegment from SegmentServiceImpl");
		segmentServiceDao.createSegmentServices(segmentServicespojo);

	}

	public void deleteSegmentService(int segmentId) {
		segmentServiceDao.deleteSegmentService(segmentId);
		logger.debug("deleteSegment from SegmentServiceImpl");

	}

	public void modifySegmentService(int segmentId) {
		logger.debug(" modifySegment from SegmentServiceImpl");
		segmentServiceDao.modifySegmentService(segmentId);

	}

	public List<String> fetchSegmentServiceById(int segmentId) {
		logger.debug(" fetchSegmentDetailsById  from SegmentServiceImpl");
		return segmentServiceDao.fetchSegmentServiceById(segmentId);
		
	}

	public List<String> fetchSegmentServDtlsByName(String segment) {
		logger.debug(" fetchSegmentDetailsByName from SegmentServiceImpl");
		return segmentServiceDao.fetchSegmentServDtlsByName(segment);
		
	}

	public List<String> fetchSegmentServiceList(int segmentId) {
		return segmentServiceDao.fetchSegmentServiceList(segmentId);

	}
	
	
	public static List<String> fetchAddonServiceListBySegment(String segment) {
		logger.debug(" fetchAddonServiceListBySegment from SegmentServiceImpl");
		return segmentServiceDao.fetchAddonServiceListBySegment(segment);
		
	}
	public static List<String> fetchMrcAddOnOfferListBySegment(String segment) {
		logger.debug(" fetchMrcAddOnOfferListBySegment from SegmentServiceImpl");
		return segmentServiceDao.fetchMrcAddOnOfferListBySegment(segment);
		
	}
	

}