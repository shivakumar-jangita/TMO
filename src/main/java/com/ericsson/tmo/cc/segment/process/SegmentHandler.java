package com.ericsson.tmo.cc.segment.process;

import java.util.List;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;

public interface SegmentHandler {
	
	public abstract String execute(String segment,List<String> msisdnList, ConfigPropertiesPojo configPropertiesPojo)throws Exception;

}
