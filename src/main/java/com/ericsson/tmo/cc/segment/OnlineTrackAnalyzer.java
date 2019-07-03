package com.ericsson.tmo.cc.segment;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.dao.InconsistencyDao;
import com.ericsson.tmo.cc.segment.dao.service.InconsistencyServiceImpl;
import com.ericsson.tmo.cc.segment.dao.service.SegmentImpl;
import com.ericsson.tmo.cc.segment.process.SegmentController;
import com.ericsson.tmo.cc.segment.utils.CsvReaderUtil;

public class OnlineTrackAnalyzer {

	private static final Logger logger = LoggerFactory.getLogger(OnlineTrackAnalyzer.class);
	public static ConfigPropertiesPojo configPropertiesPojo = null;
	public static SegmentController segmentController = null;
	static InconsistencyDao inconsistencyDao;

	public static void main(String[] args) {

		try {
			logger.info("OnlineAnalyzer part execution starts from main() -- starts here");

			String reportName = "MetropcAnalysis";
			InconsistencyServiceImpl inconsistencyServiceImpl = new InconsistencyServiceImpl();
			SegmentImpl segmentImpl = new SegmentImpl();
			List<Integer> segmentIdList = inconsistencyServiceImpl.fetchUniqueSegmentIdList();
			for (Integer segmentId : segmentIdList) {
				String segmentName = segmentImpl.fetchSegmentDetailsByName(segmentId);
				// List<String> msisdnList = inconsistencyServiceImpl.fetchInconsistencyMsisdnData(segmentId);
				List<String> msisdnList = CsvReaderUtil.getInconsistencyMsisdnData(segmentId);

				if (msisdnList != null && msisdnList.size() > 0) {
					logger.info("SegmentName=" + segmentName + " : List of Mismatched MSISDN count is : "
							+ msisdnList.size());
					configPropertiesPojo = AnalyzerUtils.initConfigData();
					configPropertiesPojo.setReportName(reportName);
					SegmentController.process(segmentName, msisdnList, configPropertiesPojo);
				}
				logger.info("OnlineAnalyzer part execution completion from main() -- ends here");
			}

		} catch (Exception exp) {
			exp.printStackTrace();
			logger.error("OnlineAnalyzer ERROR " + exp);
		}

	}
}
