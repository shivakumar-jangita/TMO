package com.ericsson.tmo.cc.segment.handler;

import java.util.List;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.process.AbstractSegmentHandler;

public class CombinedSegmentsHandler extends AbstractSegmentHandler {

	private static CombinedSegmentsHandler combinedSegmentsHandler = null;

	/**
	 * This is singleton method which will be used for getting an single object.
	 */
	public static CombinedSegmentsHandler getInstance() {
		if (combinedSegmentsHandler == null) {
			combinedSegmentsHandler = new CombinedSegmentsHandler();
		}
		return combinedSegmentsHandler;
	}

	@Override
	public String execute(String segment, List<String> msisdnList,ConfigPropertiesPojo configPropertiesPojo) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
