package com.ericsson.tmo.cc.segment.handler;

import java.util.List;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.process.AbstractSegmentHandler;

public class GoSmartSegmentHandler extends AbstractSegmentHandler {

	private static GoSmartSegmentHandler goSmartSegmentHandler = null;

	/**
	 * This is singleton method which will be used for getting an single object.
	 */
	public static GoSmartSegmentHandler getInstance() {
		if (goSmartSegmentHandler == null) {
			goSmartSegmentHandler = new GoSmartSegmentHandler();
		}
		return goSmartSegmentHandler;
	}

	
	
	@Override
	public String execute(String segment, List<String> msisdn,ConfigPropertiesPojo configPropertiesPojo ) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
