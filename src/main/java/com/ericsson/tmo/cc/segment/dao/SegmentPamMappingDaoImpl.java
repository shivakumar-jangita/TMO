/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.dao.model.SegmentPamMapping;

/**
 * @author esivjan
 *
 */
public class SegmentPamMappingDaoImpl extends AbstractDao implements SegmentPamMappingDao {
	private static final Logger logger = LoggerFactory.getLogger(SegmentPamMappingDaoImpl.class);
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void createSegmentPamMapping(SegmentPamMapping segmentPamMapping) {
		logger.info("inside createSegmentPamMapping Method");
		try {
			SegmentPamMapping segmentsEntity = new SegmentPamMapping();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(segmentsEntity);
			logger.debug("SegmentPam Created Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.debug("ERROR" + hbe);
		} 
	}

	public void deleteSegmentPamMapping(int sevExpryMapId) {
		logger.info("inside deleteSegmentPamMapping Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SegmentPamMapping segment = (SegmentPamMapping) session.load(SegmentPamMapping.class, sevExpryMapId);
			session.delete(segment);
			logger.debug("sevExpryMapId= " + sevExpryMapId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void modifySegmentPamMapping(SegmentPamMapping segmentPamMapping) {
		logger.info("inside modifySegmentPamMapping Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SegmentPamMapping segmentsEntity = (SegmentPamMapping) session.get(SegmentPamMapping.class,
					segmentPamMapping);
			logger.debug("SegmentPamMapping= " + segmentPamMapping + " Updated Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public List<String> fetchSegmentPamMappingBySegmentId(int segmentId, int billCyclePeriod) {
		logger.info("inside fetchSegmentPamMappingBySegmentId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchSegmentPamMappingBySegmentIdQuery = "SELECT * FROM  t_cc_segment_pam_mapping WHERE segment_id=" + segmentId
					+ " AND nap_billcycle_period='" + billCyclePeriod + "'";
			SQLQuery query = session.createSQLQuery(fetchSegmentPamMappingBySegmentIdQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		}
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;

	}

	public List<String> fetchSegmentPamMapping(int segmentId, String periodDefinedStatus) {
		logger.info("inside fetchSegmentPamMapping Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchSegmentPamMappingQuery = "SELECT * FROM t_cc_segment_pam_mapping WHERE segment_id=" + segmentId
					+ " AND  isperiod_defined='" + periodDefinedStatus + "'";
			SQLQuery query = session.createSQLQuery(fetchSegmentPamMappingQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;
	}
	
	public  String fetchPamScheduleByBillCycle(String segmentName, String napBillCyclePeriod ) {
        logger.info("inside fetchPamScheduleByBillCycle Method");
        StringBuilder pamScheduleValueData = new StringBuilder();
        try {
               Session session = sessionFactory.openSession();
               session.beginTransaction();
               StringBuilder fetchPamScheduleByBillCycle = new StringBuilder();
               fetchPamScheduleByBillCycle.append("SELECT  spm.monthly_Pam_scheduleId, spm.daily_Pam_scheduleId ");
               fetchPamScheduleByBillCycle.append("FROM t_cc_segments s, t_cc_segment_pam_mapping spm");
               fetchPamScheduleByBillCycle.append("WHERE spm.nap_BillCycle_period = "+napBillCyclePeriod+"");
            	if(segmentName != null && !segmentName.equals("")){	   
            		fetchPamScheduleByBillCycle.append(" AND s.segment_name ='"+segmentName+"'");
            	}
            	fetchPamScheduleByBillCycle.append(" AND s.segment_id = spm.segment_id");
             
               SQLQuery query = session.createSQLQuery(fetchPamScheduleByBillCycle.toString());
               List<Object> utIdAndValueList = query.list() != null ? query.list() : null;
               Iterator<Object> iterator = utIdAndValueList != null ? utIdAndValueList.iterator() : null;
               Object[] results = null;
               while (iterator != null && iterator.hasNext()) {
                     results = (Object[]) iterator.next();
                     pamScheduleValueData.append(results[0].toString()+SegmentConstants.SPLITER_PIPE+results[1].toString());
                     pamScheduleValueData.append(SegmentConstants.SIGN_COMA);
               }
               logger.info("result" + results);
               session.getTransaction().commit();
               session.close();
        } catch (Exception hbe) {
               logger.error("ERROR" + hbe);
        }
        logger.info("results: " + pamScheduleValueData);
        return pamScheduleValueData.toString();
 }


}
