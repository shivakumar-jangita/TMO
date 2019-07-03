/**
 * 
 */
package com.ericsson.tmo.cc.segment.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author esivjan
 *
 */
public class CsvReaderUtil {

	private static final Logger logger = LoggerFactory.getLogger(CsvReaderUtil.class);

	public static List<String> getInconsistencyMsisdnData(int segmentId) {

		List<String> msisdnList = new ArrayList<>();
		String csvFile = "/opt/ConsistencyChecker/var/reports/extract/MetroPc_Result1.csv";
		String line = "";
		String cvsSplitBy = ",";
		String[] msisdnArray = null;
		List<String> dataList = null;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
			while ((line = br.readLine()) != null) {
				// use comma as separator
				msisdnArray = line.split(cvsSplitBy);
				dataList = Arrays.asList(msisdnArray[1]);
				msisdnList.addAll(dataList);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		logger.info("size" + msisdnList.size());

		return msisdnList;
	}

}