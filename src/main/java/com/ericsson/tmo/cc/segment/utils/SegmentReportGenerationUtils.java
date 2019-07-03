package com.ericsson.tmo.cc.segment.utils;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SegmentReportGenerationUtils {

	// Singleton helper class to hold instance of SegmentReportGenerationUtils
	private static class SingletonHelper {
		private static final SegmentReportGenerationUtils INSTANCE = new SegmentReportGenerationUtils();
	}

	// static method to be used when accessing this Singleton (thread-safe):
	// SegmentReportGenerationUtils.getInstance()
	public static SegmentReportGenerationUtils getInstance() {
		return SingletonHelper.INSTANCE;
	}

	// Counters
	public Counter countSubscriberOnlyExistsInBQR;
	public Counter countSubscriberOnlyExistsInSDP;
	public Counter countMismatchInProductID;
	public Counter countMismatchInSubscriberStatus;
	public Counter countMismatchInCustomerID;
	public Counter countMismatchInPAM;
	public Counter countMismatchInUsageThreshold;
	public Counter countMismatchInExpiryDate;
	public Counter countMismatchInOfferID;
	public Counter countMismatchInDAID;
	public Counter countMismatchInBAN;
	public Counter countMismatchInACCTYPE;
	public Counter countMismatchInACCSUBTYPE;
	public Counter countMismatchInPAIREDMSISDN;

	// private constructor to prevent other to instantiate the class (Singleton
	// pattern)
	public SegmentReportGenerationUtils() {
		this.resetCounters();
	}

	/*
	 * Reset the counters.
	 */
	public void resetCounters() {
		this.countSubscriberOnlyExistsInBQR = new Counter();
		this.countSubscriberOnlyExistsInSDP = new Counter();
		this.countMismatchInProductID = new Counter();
		this.countMismatchInSubscriberStatus = new Counter();
		this.countMismatchInCustomerID = new Counter();
		this.countMismatchInPAM = new Counter();
		this.countMismatchInUsageThreshold = new Counter();
		this.countMismatchInExpiryDate = new Counter();
		this.countMismatchInOfferID = new Counter();
		this.countMismatchInDAID = new Counter();
		this.countMismatchInBAN = new Counter();
		this.countMismatchInACCTYPE = new Counter();
		this.countMismatchInACCSUBTYPE = new Counter();
		this.countMismatchInPAIREDMSISDN = new Counter();

	}

	/*
	 * Generate the report
	 */
	public void generateReport(int mismatchCount, String reportName) {
		try {
			PrintWriter writer = new PrintWriter(
					"/opt/ConsistencyChecker/var/reports/CorrectionReport" + reportName + ".txt", "UTF-8");
			printReportToWriter(writer, mismatchCount);
			writer.close();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("done");
	}

	private void printReportToWriter(PrintWriter writer, int mismatchCount) {
		writer.println("=========================================================");
		writer.println("*************** Correction Report Log *******************");
		writer.println("=========================================================");
		writer.println("");
		writer.println("Total number of mismatch records :" + mismatchCount);
		writer.println("");
		writer.println("==========================================================");
		writer.println("Scenarios:");
		writer.println("==========");
		writer.println("");
		writer.println("Subscribers only exist in BQR and does not exist in SDP");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countSubscriberOnlyExistsInBQR.getCountTotal());
		writer.println("corrected             : " + countSubscriberOnlyExistsInBQR.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countSubscriberOnlyExistsInBQR.getCorrectedMsisdn().toString()
				.substring(1, countSubscriberOnlyExistsInBQR.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countSubscriberOnlyExistsInBQR.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countSubscriberOnlyExistsInBQR.getFailedMsisdn().toString()
				.substring(1, countSubscriberOnlyExistsInBQR.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Mismatches in product ID");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInProductID.getCountTotal());
		writer.println("corrected             : " + countMismatchInProductID.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInProductID.getCorrectedMsisdn().toString()
				.substring(1, countMismatchInProductID.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInProductID.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInProductID.getFailedMsisdn().toString().substring(1,
				countMismatchInProductID.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Mismatches in DA ID");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInDAID.getCountTotal());
		writer.println("corrected             : " + countMismatchInDAID.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInDAID.getCorrectedMsisdn().toString().substring(1,
				countMismatchInDAID.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInDAID.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInDAID.getFailedMsisdn().toString().substring(1,
				countMismatchInDAID.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Full Subscriber Mismatch");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInOfferID.getCountTotal());
		writer.println("corrected             : " + countMismatchInOfferID.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInOfferID.getCorrectedMsisdn().toString().substring(1,
				countMismatchInOfferID.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInOfferID.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInOfferID.getFailedMsisdn().toString().substring(1,
				countMismatchInOfferID.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Mismatch in subscriber status");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInSubscriberStatus.getCountTotal());
		writer.println("corrected             : " + countMismatchInSubscriberStatus.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInSubscriberStatus.getCorrectedMsisdn().toString()
				.substring(1, countMismatchInSubscriberStatus.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInSubscriberStatus.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInSubscriberStatus.getFailedMsisdn().toString()
				.substring(1, countMismatchInSubscriberStatus.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Mismatch in Customer ID");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInCustomerID.getCountTotal());
		writer.println("corrected             : " + countMismatchInCustomerID.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInCustomerID.getCorrectedMsisdn().toString()
				.substring(1, countMismatchInCustomerID.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInCustomerID.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInCustomerID.getFailedMsisdn().toString().substring(1,
				countMismatchInCustomerID.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Mismatch in PAM");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInPAM.getCountTotal());
		writer.println("Number of Mismatch for which MSISDN's having missing PAM value from NAP    : "
				+ countMismatchInPAM.getCountMissingPam());
		writer.println("MSISDN's with missing PAM value in NAP    : " + countMismatchInPAM.getMissingPAMMsisdn()
				.toString().substring(1, countMismatchInPAM.getMissingPAMMsisdn().toString().length() - 1));
		writer.println("corrected             : " + countMismatchInPAM.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInPAM.getCorrectedMsisdn().toString().substring(1,
				countMismatchInPAM.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInPAM.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInPAM.getFailedMsisdn().toString().substring(1,
				countMismatchInPAM.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Mismatch in usage threshold");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInUsageThreshold.getCountTotal());
		writer.println("corrected             : " + countMismatchInUsageThreshold.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInUsageThreshold.getCorrectedMsisdn().toString()
				.substring(1, countMismatchInUsageThreshold.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInUsageThreshold.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInUsageThreshold.getFailedMsisdn().toString()
				.substring(1, countMismatchInUsageThreshold.getFailedMsisdn().toString().length() - 1));
		writer.println("");

		writer.println("Mismatch in BAN");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInBAN.getCountTotal());
		writer.println("corrected             : " + countMismatchInBAN.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInBAN.getCorrectedMsisdn().toString().substring(1,
				countMismatchInBAN.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInBAN.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInBAN.getFailedMsisdn().toString().substring(1,
				countMismatchInBAN.getFailedMsisdn().toString().length() - 1));
		writer.println("");

		writer.println("Mismatch in Account Type");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInACCTYPE.getCountTotal());
		writer.println("corrected             : " + countMismatchInACCTYPE.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInACCTYPE.getCorrectedMsisdn().toString().substring(1,
				countMismatchInACCTYPE.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInACCTYPE.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInACCTYPE.getFailedMsisdn().toString().substring(1,
				countMismatchInACCTYPE.getFailedMsisdn().toString().length() - 1));
		writer.println("");

		writer.println("Mismatch in Account Sub Type");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInACCSUBTYPE.getCountTotal());
		writer.println("corrected             : " + countMismatchInACCSUBTYPE.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInACCSUBTYPE.getCorrectedMsisdn().toString()
				.substring(1, countMismatchInACCSUBTYPE.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInACCSUBTYPE.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInACCSUBTYPE.getFailedMsisdn().toString().substring(1,
				countMismatchInACCSUBTYPE.getFailedMsisdn().toString().length() - 1));
		writer.println("");

		writer.println("Mismatch in Paired Msisdn");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInPAIREDMSISDN.getCountTotal());
		writer.println("corrected             : " + countMismatchInPAIREDMSISDN.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInPAIREDMSISDN.getCorrectedMsisdn().toString()
				.substring(1, countMismatchInPAIREDMSISDN.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInPAIREDMSISDN.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInPAIREDMSISDN.getFailedMsisdn().toString()
				.substring(1, countMismatchInPAIREDMSISDN.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Mismatch in ExpiryDate");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countMismatchInExpiryDate.getCountTotal());
		writer.println("Number of Mismatch for MSISDN's has Date on SDP greater than NAP    : "
				+ countMismatchInExpiryDate.getCountGreaterExpiry());
		writer.println("MISISDN's with greater expiry date in SDP    : "
				+ countMismatchInExpiryDate.getGretearExpiryMsisdn().toString().substring(1,
						countMismatchInExpiryDate.getGretearExpiryMsisdn().toString().length() - 1));
		writer.println("corrected             : " + countMismatchInExpiryDate.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countMismatchInExpiryDate.getCorrectedMsisdn().toString()
				.substring(1, countMismatchInExpiryDate.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countMismatchInExpiryDate.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countMismatchInExpiryDate.getFailedMsisdn().toString().substring(1,
				countMismatchInExpiryDate.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("Subscriber exists in SDP and does not not exist in BQR");
		writer.println("********************************************************");
		writer.println("Number of mismatch    : " + countSubscriberOnlyExistsInSDP.getCountTotal());
		writer.println("corrected             : " + countSubscriberOnlyExistsInSDP.getCountCorrectionSuccessfull());
		writer.println("corrected MSISDN's    : " + countSubscriberOnlyExistsInSDP.getCorrectedMsisdn().toString()
				.substring(1, countSubscriberOnlyExistsInSDP.getCorrectedMsisdn().toString().length() - 1));
		writer.println("Failed                : " + countSubscriberOnlyExistsInSDP.getCountCorrectionFailed());
		writer.println("Failed MSISDN's       : " + countSubscriberOnlyExistsInSDP.getFailedMsisdn().toString()
				.substring(1, countSubscriberOnlyExistsInSDP.getFailedMsisdn().toString().length() - 1));
		writer.println("");
		writer.println("======================= End of Report ==================");
		writer.println("");
	}

	/**
	 * Nested class to be used to hold counters to be reported.
	 */
	public class Counter {

		// Class attributes

		private int countTotal;
		private int countGreaterExpiry;
		private int countMissingPam;

		private int countCorrectionSuccessfull;
		private int countCorrectionFailed;
		private List<String> correctedMsisdn;
		private List<String> failedMsisdn;
		private List<String> gretearExpiryMsisdn;
		private List<String> missingPAMMsisdn;

		// Default constructor
		public Counter() {
			this.countTotal = 0;
			this.countGreaterExpiry = 0;
			this.countMissingPam = 0;
			this.countCorrectionSuccessfull = 0;
			this.countCorrectionFailed = 0;
			this.correctedMsisdn = new ArrayList<>();
			this.failedMsisdn = new ArrayList<>();
			this.gretearExpiryMsisdn = new ArrayList<>();
			this.missingPAMMsisdn = new ArrayList<>();
		}

		// Getters

		public int getCountTotal() {
			return countTotal;
		}

		public int getCountGreaterExpiry() {
			return countGreaterExpiry;
		}

		public int getCountCorrectionSuccessfull() {
			return countCorrectionSuccessfull;
		}

		public int getCountCorrectionFailed() {
			return countCorrectionFailed;
		}

		public List<String> getCorrectedMsisdn() {
			return correctedMsisdn;
		}

		public List<String> getFailedMsisdn() {
			return failedMsisdn;
		}

		public List<String> getGretearExpiryMsisdn() {
			return gretearExpiryMsisdn;
		}

		public int getCountMissingPam() {
			return countMissingPam;
		}

		public List<String> getMissingPAMMsisdn() {
			return missingPAMMsisdn;
		}
		// Other methods

		/*
		 * Increments either countCorrectionSuccessfull or countCorrectionFailed
		 * and MSISDN list. Note: this will NOT update countTotal!!!
		 */
		public void increment(boolean correctionSuccessfull, String msisdn) {
			if (correctionSuccessfull) {
				incrementCountCorrectionSuccessfull(msisdn);
			} else {
				incrementCountCorrectionFailed(msisdn);
			}
		}

		/*
		 * Only increments countTotal.
		 */
		public void incrementCountTotal() {
			this.countTotal++;
		}

		/*
		 * Only increments countTotal.
		 */
		public void incrementGreaterExpiryCount() {
			this.countGreaterExpiry++;
		}

		/*
		 * Only increments countTotal.
		 */
		public void incrementMissingPAMCount() {
			this.countMissingPam++;
		}

		/*
		 * Only increments countCorrectionSuccessfull and MSISDN list.
		 */
		public void incrementCountCorrectionSuccessfull(String msisdn) {
			this.countCorrectionSuccessfull++;
			this.correctedMsisdn.add(msisdn);
		}

		/*
		 * Only increments countCorrectionFailed and MSISDN list.
		 */
		public void incrementCountCorrectionFailed(String msisdn) {
			this.countCorrectionFailed++;
			this.failedMsisdn.add(msisdn);
		}

	}

}
