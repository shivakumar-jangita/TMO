package com.ericsson.tmo.cc.segment;

import java.util.ArrayList;
import java.util.List;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.process.SegmentController;

public class ECCGateWayAnalyzer {
	public static ConfigPropertiesPojo configPropertiesPojo = null;

	public static void main(String[] args) throws Exception {

		// Initialise configuration data
		// AnalyzerUtils.initConfigData();

		List<String> msisdnList = new ArrayList<String>();

		System.out.println("msisdn: " + args[0]);
		msisdnList.add(args[0]);
		String segment = SegmentConstants.MEGENTA;
		SegmentController.process(segment, msisdnList, configPropertiesPojo);

	}

}
