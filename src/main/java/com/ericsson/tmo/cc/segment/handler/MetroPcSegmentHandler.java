package com.ericsson.tmo.cc.segment.handler;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.commons.ConfigPropertiesPojo;
import com.ericsson.tmo.cc.segment.process.AbstractSegmentHandler;
import com.ericsson.tmo.cc.segment.utils.CsvReportGeneratation;
import com.ericsson.tmo.cc.segment.utils.SegmentReportGenerationUtils;
import com.ericsson.tmo.cc.segment.utils.SegmentUtils;

public class MetroPcSegmentHandler extends AbstractSegmentHandler {

	private static final Logger logger = LoggerFactory.getLogger(MetroPcSegmentHandler.class);
	private static MetroPcSegmentHandler metroPcHandler = null;
	private static PrintWriter txtFileWriter = null;
	private static Set<String> uniqueList = new HashSet<String>();
	private static String maxMismatch = null;

	/**
	 * This is singleton method which will be used for getting an single object.
	 */
	public static MetroPcSegmentHandler getInstance() {
		if (metroPcHandler == null) {
			metroPcHandler = new MetroPcSegmentHandler();
		}
		return metroPcHandler;
	}

	public String execute(String segmentName, List<String> msisdnList, ConfigPropertiesPojo configPropertiesPojo)
			throws Exception {
		logger.debug("Controll reached in MetroPcSegmentHandler:execute() -- starts here ");
		correction(segmentName, msisdnList, configPropertiesPojo);
		logger.debug("Controll reached in MetroPcSegmentHandler:execute() -- ends here ");
		return "";
	}

	public static void correction(String segmentName, List<String> mismatchList,
			ConfigPropertiesPojo configPropertiesPojo) throws Exception {

		logger.info("Controll reached in MetroPcSegmentHandler:correction() -- starts here");
		List<String[]> listForCSV = null;

		// Update mismatch entries in report
		if (configPropertiesPojo.getDirectMismatchToExternalFile().equals(SegmentConstants.TRUE)) {
			logger.info("Update mismatch entries in report");
			try {
				if (!mismatchList.isEmpty()) {
					txtFileWriter = SegmentUtils.textFileReportWriter(SegmentConstants.METROPC_REPORTNAME);
					for (String msisdn : mismatchList) {
						txtFileWriter.println(msisdn);
					}
				}
			} catch (FileNotFoundException e) {
				logger.error("Inside correction method ERROR" + e);
			} catch (UnsupportedEncodingException e) {
				logger.error("Inside correction method ERROR" + e);
			} catch (Exception e) {
				logger.error("Inside correction method ERROR" + e);
			}

			finally {
				if (txtFileWriter != null)
					txtFileWriter.close();
			}
		}

		maxMismatch = configPropertiesPojo.getMaxMismatchCount();
		Integer maxMismatchCount = null;
		if (maxMismatch == null || maxMismatch.isEmpty()) {
			maxMismatchCount = 500;
		} else {
			maxMismatchCount = Integer.parseInt(maxMismatch);
		}
		if (mismatchList.size() <= maxMismatchCount) {
			listForCSV = MetroPcSegmentHandlerUtils.mismatchExtraction(segmentName, mismatchList, configPropertiesPojo);

			listForCSV = MetroPcSegmentHandlerUtils.uniqueRecordExtraction(segmentName, uniqueList,
					configPropertiesPojo, listForCSV);
		} else {
			logger.info("Max Mismatch value Crossed with count " + mismatchList.size());
		}

		// Generate report
		if (listForCSV != null && listForCSV.size() > 0) {
			try {
				System.out.println("csv genaration started");
				CsvReportGeneratation.writeToCsv(listForCSV, segmentName);
				System.out.println("csv genaration complete ");
			} catch (Exception e) {
				System.out.println("inside catch blocks");
				mismatchList.clear();
				uniqueList.clear();
			}
		}
		int mismatchCount = mismatchList.size() + uniqueList.size() > 0 ? mismatchList.size() + uniqueList.size() : 0;
		if (mismatchCount > 0) {
			SegmentReportGenerationUtils.getInstance().generateReport(mismatchCount,
					SegmentConstants.METROPC_REPORTNAME);
			SegmentReportGenerationUtils.getInstance().resetCounters();
			mismatchList.clear();
			uniqueList.clear();
		}

		logger.debug("Controll reached in MetroPcSegmentHandler:correction() -- ends here");
	}

}
