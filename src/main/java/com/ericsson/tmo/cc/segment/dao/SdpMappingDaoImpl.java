/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.model.ServiceSdpMapping;

/**
 * @author esivjan
 *
 */
public class SdpMappingDaoImpl extends AbstractDao implements SdpMappingDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final Logger logger = LoggerFactory.getLogger(SdpMappingDaoImpl.class);

	public void createSegServSdpMapping(ServiceSdpMapping segSevSdMappingPojo) {
		logger.info("createSegServSdpMapping method is calling:");
		try {
			ServiceSdpMapping segmentsEntity = new ServiceSdpMapping();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(segmentsEntity);
			logger.info("ServiceSdpMapping Created Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR:" + hbe);
		} 
	}

	public void deleteSegServSdpMapping(int segServSdpMapId) {
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ServiceSdpMapping segment = (ServiceSdpMapping) session.load(ServiceSdpMapping.class, segServSdpMapId);
			session.delete(segment);
			logger.debug("segServSdpMapId= " + segServSdpMapId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void deleteListOfServSdpMappingByServId(int segServId) {
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ServiceSdpMapping segment = (ServiceSdpMapping) session.load(ServiceSdpMapping.class, segServId);
			session.delete(segment);
			logger.debug("segServId= " + segServId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void modifySegServSdpMapping(ServiceSdpMapping segSevSdMappingPojo) {
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ServiceSdpMapping segmentsEntity = (ServiceSdpMapping) session.get(ServiceSdpMapping.class,
					segSevSdMappingPojo);
			logger.debug("segSevSdMappingPojo= " + segSevSdMappingPojo + " Updated Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 

	}

	public List<String> fetchListOfServSdpMappingByServId(int servId) {
		logger.info("inside fetchListOfServSdpMappingByServId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfServiceIdQuery = "SELECT * FROM t_cc_service_sdp_mapping WHERE service_id='" + servId
					+ "'";
			SQLQuery query = session.createSQLQuery(fetchListOfServiceIdQuery);
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

	public List<String> fetchListOfServSdpMappingBySegmentId(int segmentId) {
		logger.info("inside fetchListOfServSdpMappingBySegmentId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfSegmentIdQuery = "SELECT * FROM t_cc_service_sdp_mapping WHERE segment_id='" + segmentId
					+ "'";
			SQLQuery query = session.createSQLQuery(fetchListOfSegmentIdQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		}
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;

	}

	public List<String> fetchListOfSegServSdpMapping(int segmentId, int segServId) {
		logger.info("inside fetchListOfSegServSdpMapping Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfSegServIdQuery = "SELECT * FROM t_cc_service_sdp_mapping WHERE segment_id=" + segmentId
					+ " AND service_id='" + segServId + "'";
			SQLQuery query = session.createSQLQuery(fetchListOfSegServIdQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		} 
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;
	}

	public List<String> fetchListOfOfferIdByServiceName(String serviceName) {
		logger.info("inside fetchListOfSegServSdpMappingByOfferId Method");
		List<String> finalResult = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfSegServIdQuery = "SELECT sm.sdp_lco, sm.sdp_mrc, sm.sdp_addon FROM  "
											+ "t_cc_segment_services ss, t_cc_service_sdp_mapping sm "
											+ "WHERE ss.service_id= sm.service_id AND ss.service_name = '" + serviceName
											+ "' AND sm.deletion_status='N' AND ss.deletion_status = 'N'";
			SQLQuery query = session.createSQLQuery(fetchListOfSegServIdQuery);
			List<Object> offerIdList = query.list() != null ? query.list() : null;
			Iterator<Object> iterator = offerIdList != null ? offerIdList.iterator() : null;
			Object[] results = null;
			while (iterator != null && iterator.hasNext()) {
				results = (Object[]) iterator.next();
				for (int index = 0; index <= results.length - 1; index++) {
					if(results[index]!= null)
					finalResult.add(results[index].toString());
				}
			}
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		} 
		logger.info("results: " + Arrays.asList(finalResult.size()));
		return finalResult;
	}
	
	
	public List<String> fetchListOfOfferForExpiryUtcTime(String serviceName) {
		logger.info("inside fetchListOfSegServSdpMappingByOfferId Method");
		List<String> finalResult = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfOfferIdQuery = "SELECT sm.sdp_mrc, sm.sdp_addon FROM  "
											+ "t_cc_segment_services ss, t_cc_service_sdp_mapping sm "
											+ "WHERE ss.service_id= sm.service_id AND ss.service_name IN('"+serviceName+"')"
											+ "AND sm.deletion_status='N' AND ss.deletion_status = 'N'";
			SQLQuery query = session.createSQLQuery(fetchListOfOfferIdQuery);
			List<Object> offerIdList = query.list() != null ? query.list() : null;
			Iterator<Object> iterator = offerIdList != null ? offerIdList.iterator() : null;
			Object[] results = null;
			while (iterator != null && iterator.hasNext()) {
				results = (Object[]) iterator.next();
				for (int index = 0; index <= results.length - 1; index++) {
					if(results[index]!= null)
					finalResult.add(results[index].toString());
				}
			}
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		} 
		logger.info("results: " + Arrays.asList(finalResult.size()));
		return finalResult;
	}


}
