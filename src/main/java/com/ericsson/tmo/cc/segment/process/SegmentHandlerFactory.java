package com.ericsson.tmo.cc.segment.process;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.handler.CombinedSegmentsHandler;
import com.ericsson.tmo.cc.segment.handler.GoSmartSegmentHandler;
import com.ericsson.tmo.cc.segment.handler.MetroPcSegmentHandler;

public class SegmentHandlerFactory {

	public static SegmentHandler getSegmentHandlerInstance(String segment) {

		if (segment.equalsIgnoreCase(SegmentConstants.METROPC)) {

			return MetroPcSegmentHandler.getInstance();

		} else if (segment.equalsIgnoreCase(SegmentConstants.GOSMART)) {

			return GoSmartSegmentHandler.getInstance();

		} else if (segment.equalsIgnoreCase(SegmentConstants.MEGENTA)) {

			System.out.println("getting Magenta handler instance");
			//return MegentaSegmentHandler.getInstance();

		} else {
			return CombinedSegmentsHandler.getInstance();
		}
		return null;

	}

}
