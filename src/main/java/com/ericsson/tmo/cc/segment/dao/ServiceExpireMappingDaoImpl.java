/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.model.ServiceExpiryMapping;

public class ServiceExpireMappingDaoImpl extends AbstractDao implements ServiceExpireMappingDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final Logger logger = LoggerFactory.getLogger(ServiceExpireMappingDaoImpl.class);

	public void createServExpiryMapping(ServiceExpiryMapping segSevSdMappingPojo) {
		logger.info("inside createServExpiryMapping Method");
		try {
			ServiceExpiryMapping segmentsEntity = new ServiceExpiryMapping();
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

	public void deleteServExpiryMapping(int segServSdpMapId) {
		logger.info("inside deleteServExpiryMapping Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ServiceExpiryMapping segment = (ServiceExpiryMapping) session.load(ServiceExpiryMapping.class,
					segServSdpMapId);
			session.delete(segment);
			logger.debug("SegmentId= " + segServSdpMapId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void deleteListOfServExpiryMappingByServId(int segServId) {
		logger.info("inside deleteListOfServExpiryMappingByServId Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ServiceExpiryMapping segment = (ServiceExpiryMapping) session.load(ServiceExpiryMapping.class, segServId);
			session.delete(segment);
			logger.debug("SegmentId= " + segServId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void modifyServExpiryMapping(ServiceExpiryMapping segSevSdMappingPojo) {
		logger.info("inside modifyServExpiryMapping Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			ServiceExpiryMapping segmentsEntity = (ServiceExpiryMapping) session.get(ServiceExpiryMapping.class,
					segSevSdMappingPojo);
			logger.debug("SegmentId= " + segSevSdMappingPojo + " Updated Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public List<String> fetchListOfServExpiryMappingByServId(int segServId) {
		logger.info("inside fetchListOfServExpiryMappingByServId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfServExpiryMappingByServIdQuery = "SELECT * FROM t_cc_service_expiry_mapping WHERE service_id ='"
					+ segServId + "'";
			SQLQuery query = session.createSQLQuery(fetchListOfServExpiryMappingByServIdQuery);
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

	public List<String> fetchListOfServExpiryMappingBySegmentId(int segmentId) {
		logger.info("inside fetchListOfServExpiryMappingByServId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfServExpiryMappingBySegmentIdQuery = "SELECT * FROM t_cc_service_expiry_mapping WHERE segment_id ='"
					+ segmentId + "'";
			SQLQuery query = session.createSQLQuery(fetchListOfServExpiryMappingBySegmentIdQuery);
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

	public List<String> fetchListOfServExpiryMapping(int segmentId, int segServId) {
		logger.info("inside fetchListOfServExpiryMappingByServId Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchListOfServExpiryMappingQuery = "SELECT * FROM t_cc_service_expiry_mapping WHERE segment_id ="
					+ segmentId + " AND service_id='" + segServId + "'";
			SQLQuery query = session.createSQLQuery(fetchListOfServExpiryMappingQuery);
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

	public List<String> fetchOfferIdList(int segmentId) {
		logger.info("inside fetchOfeerIdList Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchOfeerIdListQuery = "SELECT offer_id FROM t_cc_service_expiry_mapping WHERE segment_id='"
					+ segmentId + "'";
			SQLQuery query = session.createSQLQuery(fetchOfeerIdListQuery);
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
	
/*	public  Set<String> fetchExpiryOfferIdByServiceName(String serviceName) {
		logger.info("inside fetchAllThresholdBySegmentName Method");
		Set<String> setOfOfferIds = new HashSet<String>();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchOfferIdQuery = "select sem.offer_id from t_cc_service_expiry_mapping sem, t_cc_segment_services ss "
											+ "WHERE ss.segment_id = sem.segment_id AND ss.service_id = sem.service_id "
											+ "AND ss.service_name IN ("+serviceName+") AND ss.deletion_status='N' AND sem.deletion_status = 'N';";
			
			SQLQuery query = session.createSQLQuery(fetchOfferIdQuery);
			List<Object> offerIdList = query.list() != null ? query.list() : null;
			Iterator<Object> iterator = offerIdList != null ? offerIdList.iterator() : null;
			Object[] results = null;
			while (iterator != null && iterator.hasNext()) {
				results = (Object[]) iterator.next();
				for (int index = 0; index <= results.length-1; index++) {
					setOfOfferIds.add(results[index].toString());
				}
			}
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		}
		logger.info("setOfOfferIds: " + Arrays.asList(setOfOfferIds));
		return setOfOfferIds;
	}*/
	
	
	public  List<String> fetchExpiryOfferIdByServiceName(String serviceName) {
		logger.info("inside fetchAllThresholdBySegmentName Method");
		List<String> offerIdList = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchOfferIdQuery = "SELECT DISTINCT sem.offer_id from t_cc_service_expiry_mapping sem, t_cc_segment_services ss "
											+ "WHERE ss.segment_id = sem.segment_id AND ss.service_id = sem.service_id "
											+ "AND ss.service_name IN ("+serviceName+") AND ss.deletion_status='N' AND sem.deletion_status = 'N';";
			
			SQLQuery query = session.createSQLQuery(fetchOfferIdQuery);
			offerIdList = query.list() != null ? query.list() : null;
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		}
		logger.info("offerIdList: " + offerIdList);
		return offerIdList;
	}
	
	
	public String findSigleExpiryOfferIdByService(String serviceName) {
		logger.info("inside fetchAllThresholdBySegmentName Method");
		String offerId = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchOfferIdQuery = "select sem.offer_id from t_cc_service_expiry_mapping sem, t_cc_segment_services ss "
											+ "WHERE ss.segment_id = sem.segment_id AND ss.service_id = sem.service_id "
											+ "AND ss.service_name ='"+serviceName+"' AND ss.deletion_status='N' AND sem.deletion_status = 'N';";
			
			SQLQuery query = session.createSQLQuery(fetchOfferIdQuery);
			List<Object> offerIdList = query.list() != null ? query.list() : null;
			offerId = offerIdList != null ? Arrays.toString(offerIdList.toArray()) : null;
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException hbe) {
			logger.error("ERROR" + hbe);
		}
		return offerId;
	}
	
	

}
