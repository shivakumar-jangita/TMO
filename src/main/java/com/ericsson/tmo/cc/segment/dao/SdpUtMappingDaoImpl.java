/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.SegmentConstants;
import com.ericsson.tmo.cc.segment.dao.model.SdpUtMapping;

/**
 * @author esivjan
 *
 */
public class SdpUtMappingDaoImpl extends AbstractDao implements SdpUtMappingDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	static SdpUtMappingDaoImpl sdpUtMappingDaoImpl = new SdpUtMappingDaoImpl();
	private static final Logger logger = LoggerFactory.getLogger(SdpUtMappingDaoImpl.class);

	public void createSegServSdpUtMapping(SdpUtMapping segSevSdMappingPojo) {
		logger.info("createSegServSdpUtMapping method is calling");
		try {
			SdpUtMapping segmentsEntity = new SdpUtMapping();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(segmentsEntity);
			logger.debug("Segment Created Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.debug("ERROR" + hbe);
		} 
	}

	public void deleteSegServSdpUtMapping(int segServSdpMapId) {
		logger.info("deleteSegServSdpUtMapping method is calling");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SdpUtMapping segment = (SdpUtMapping) session.load(SdpUtMapping.class, segServSdpMapId);
			session.delete(segment);
			logger.debug("SegmentId= " + segServSdpMapId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void deleteListOfServSdpUtMappingByServId(int segServId) {
		logger.info("deleteListOfServSdpUtMappingByServId method is calling");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SdpUtMapping segment = (SdpUtMapping) session.load(SdpUtMapping.class, segServId);
			session.delete(segment);
			logger.debug("SegmentId= " + segServId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void modifySegServSdpUtMapping(SdpUtMapping segSevSdMappingPojo) {
		logger.info("modifySegServSdpUtMapping method is calling");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SdpUtMapping segmentsEntity = (SdpUtMapping) session.get(SdpUtMapping.class, segSevSdMappingPojo);
			logger.debug("SegmentId= " + segSevSdMappingPojo + " Updated Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 

	}

	public List<String> fetchListOfServSdpUtMappingByServId(int segServId) {
		logger.info("inside fetchListOfServSdpUtMappingByServId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfServSdpUtMappingByServIdQuery = "SELECT * FROM t_cc_sdp_ut_mapping WHERE service_id='"
					+ segServId + "'";
			SQLQuery query = session.createSQLQuery(fetchListOfServSdpUtMappingByServIdQuery);
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

	public List<String> fetchListOfServSdpUtMappingBySegmentId(int segmId) {
		logger.info("inside fetchListOfServSdpUtMappingBySegmentId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfServSdpUtMappingBySegmentIdQuery = "SELECT * FROM t_cc_sdp_ut_mapping WHERE segment_id='"
					+ segmId + "'";
			SQLQuery query = session.createSQLQuery(fetchListOfServSdpUtMappingBySegmentIdQuery);
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

	public List<String> fetchListOfSegServSdpUTMapping(int segmentId, int segServId) {
		logger.info("inside fetchListOfSegServSdpUTMapping Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfSegServSdpUTMappingQuery = "SELECT * FROM t_cc_sdp_ut_mapping WHERE segment_id="
					+ segmentId + " AND service_id=" + segServId + " AND deletion_status='N'";
			SQLQuery query = session.createSQLQuery(fetchListOfSegServSdpUTMappingQuery);
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

	public List<String> fetchThresholdValueList(int segmentId) {
		logger.info("inside fetchThresholdValueList Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchThresholdValueListQuery = "SELECT sdp_UtAttribute_value FROM t_cc_sdp_ut_mapping WHERE segment_id='"
					+ segmentId + "'";
			SQLQuery query = session.createSQLQuery(fetchThresholdValueListQuery);
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

	
	public String fetchListOfServSdpUtMappingByService(String serviceName) {
		logger.info("inside fetchListOfServSdpUtMappingByService Method");
		StringBuilder utIdWithValueData = new StringBuilder();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfUTDataQuery = "SELECT sum.sdp_UtAttribute_id, sum.sdp_UtAttribute_value FROM "
												+ "t_cc_segment_services ss, t_cc_sdp_ut_mapping sum WHERE "
												+ "ss.segment_id = sum.segment_id AND ss.service_id = sum.service_id "
												+ "AND ss.service_name= '"+serviceName+"' AND ss.deletion_status='N' AND sum.deletion_status = 'N';";
			
			SQLQuery query = session.createSQLQuery(fetchListOfUTDataQuery);
			List<Object> utIdAndValueList = query.list() != null ? query.list() : null;
			Iterator<Object> iterator = utIdAndValueList != null ? utIdAndValueList.iterator() : null;
			Object[] results = null;
			while (iterator != null && iterator.hasNext()) {
				results = (Object[]) iterator.next();
				utIdWithValueData.append(results[0].toString()+SegmentConstants.SPLITER_PIPE+results[1].toString());
				utIdWithValueData.append(SegmentConstants.SIGN_COMA);
			}
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		}
		logger.info("results: " + utIdWithValueData);
		return utIdWithValueData.toString();
	}
	
	
	
	public List<String> fetchListOfUtIdByServiceName(String serviceName) {
		logger.info("inside fetchArrayOfUtIdByServiceName Method");
		List<String> utIdList = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfUTDataQuery = "SELECT distinct sum.sdp_UtAttribute_id FROM t_cc_segment_services ss, t_cc_sdp_ut_mapping sum WHERE "
												+ "ss.segment_id = sum.segment_id AND ss.service_id = sum.service_id "
												+ "AND ss.service_name IN ("+serviceName+")  AND ss.deletion_status='N' AND sum.deletion_status = 'N';";
			
			SQLQuery query = session.createSQLQuery(fetchListOfUTDataQuery);
			 utIdList = query.list() != null ? query.list() : null;
			logger.info("utIdList" + utIdList);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		}
		return utIdList;
	}
	
	
	public String fetchAllThresholdBySegmentName(String segmentName) {
		logger.info("inside fetchAllThresholdBySegmentName Method");
		
		String allUtIdData = "";
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfUTDataQuery = "SELECT distinct sum.sdp_UtAttribute_id FROM t_cc_segments s, t_cc_sdp_ut_mapping sum "
											+ "WHERE s.segment_id = sum.segment_id  AND s.segment_name='"+segmentName+"'"
											+ " AND s.deletion_status='N' AND sum.deletion_status = 'N';";
			
			SQLQuery query = session.createSQLQuery(fetchListOfUTDataQuery);
			List<String> utIdAndValueList = query.list() != null ? query.list() : null;
			for(String utId : utIdAndValueList){
				if(allUtIdData != null && !allUtIdData.equals(""))
					allUtIdData = allUtIdData+SegmentConstants.SPLITER_PIPE+utId;
				else
					allUtIdData = utId;
			}
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		}
		logger.info("allUtIdData: " + allUtIdData);
		return allUtIdData;
	}


}
