package com.ericsson.tmo.cc.segment.process;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;

public class SegmentController {

	private static final Logger logger = LoggerFactory.getLogger(SegmentController.class);

	public static SegmentHandler segmentHandler = null;

	public static String process(String segment, List<String> msisdnList, ConfigPropertiesPojo configPropertiesPojo)
			throws Exception {
		
		logger.debug("Control reached in SegmentController:process() -- starts here");
		segmentHandler = SegmentHandlerFactory.getSegmentHandlerInstance(segment);
		String segmentResponse = segmentHandler.execute(segment, msisdnList, configPropertiesPojo);
		logger.debug("Control reached in SegmentController:process() -- ends here");
		
		return segmentResponse;

	}

}
