package com.ericsson.tmo.cc.segment.process;

import java.util.List;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;

public abstract class AbstractSegmentHandler implements SegmentHandler {

	public abstract String execute(String segment, List<String> msisdn,ConfigPropertiesPojo configPropertiesPojo ) throws Exception ;
	
	
}
