/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.model.SegmentServices;

/**
 * @author esivjan
 *
 */
public class SegmentServiceDaoImpl extends AbstractDao implements SegmentServiceDao {
	private static final Logger logger = LoggerFactory.getLogger(SegmentServiceDaoImpl.class);
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

	public void createSegmentServices(SegmentServices segmentServicespojo) {
		logger.info("inside createSegmentServices Method");
		try {
			SegmentServices segmentsEntity = new SegmentServices();
			segmentsEntity.setSegmentId(1);
			segmentsEntity.setService("Citrix");
			segmentsEntity.setCreatedUser("TestUser");
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(segmentsEntity);
			logger.debug("SegmentService Created Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();

		}

	}

	public void deleteSegmentService(int serviceId) {
		logger.info("inside deleteSegmentService Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SegmentServices segment = (SegmentServices) session.load(SegmentServices.class, serviceId);
			session.delete(segment);
			logger.debug("serviceId= " + serviceId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();

		}
	}

	public void modifySegmentService(int serviceId) {
		logger.info("inside modifySegmentService Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			SegmentServices segmentsEntity = (SegmentServices) session.get(SegmentServices.class, serviceId);
			segmentsEntity.setService("megentha");
			segmentsEntity.setSegmentId(1);
			segmentsEntity.setDescription("Good to go");
			logger.debug("serviceId= " + serviceId + " Updated Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();

		}
	}

	public List<String> fetchSegmentServiceById(int servId) {
		logger.info("inside fetchSegmentServiceById Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchSegmentIdQuery = "SELECT * FROM  t_cc_segment_services WHERE service_id= '" + servId + "'";
			SQLQuery query = session.createSQLQuery(fetchSegmentIdQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();
		}
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;
	}

	public List<String> fetchSegmentServDtlsByName(String serviceName) {
		logger.info("inside fetchSegmentServDtlsByName Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchSegmentServDtlsByNameQuery = "SELECT * FROM  t_cc_segment_services WHERE service_name='"
					+ serviceName + "'";
			SQLQuery query = session.createSQLQuery(fetchSegmentServDtlsByNameQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();
		}
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;
	}

	public List<String> fetchSegmentServiceList(int segmentId) {

		logger.info("inside fetchSegmentServiceList Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchSegmentServiceListQuery = "SELECT service_name FROM t_cc_segment_services WHERE segment_id='"
					+ segmentId + "'";
			SQLQuery query = session.createSQLQuery(fetchSegmentServiceListQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();
		}
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;

	}

	@SuppressWarnings("unchecked")
	public List<String> fetchAddonServiceListBySegment(String segmentName) {

		logger.info("inside fetchSegmentServiceList Method");
		List<String> finalResult = new ArrayList<>();
		 List<String> list = new ArrayList<>();
		Map row=null;
		 String servicetName=null;
		
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchAddonServiceListQuery = "SELECT ss.service_name  FROM t_cc_segments s , t_cc_segment_services ss, t_cc_service_sdp_mapping sm "
					+ " WHERE s.segment_id = ss.segment_id AND s.segment_name='" + segmentName
					+ "' AND ss.service_id= sm.service_id AND sm.sdp_addon IS NOT NULL "
					+ " AND sm.deletion_status='N' AND ss.deletion_status = 'N' AND s.deletion_status='N'";
			SQLQuery query = session.createSQLQuery(fetchAddonServiceListQuery);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			finalResult = query.list();

	         for(Object object : finalResult)
	         {
	             row = (Map)object;
	                
	          servicetName=(String) row.get("service_name");
	           list.add(servicetName);
	           
		         logger.info("serviceName="+servicetName+"list:::"+list);
	         }
	        
	        // System.out.println(servicetName);}
			/*List<Object> serviceList = query.list() != null ? query.list() : null;
			Iterator<Object> iterator = serviceList != null ? serviceList.iterator() : null;
			Object[] results = null;
			while (iterator != null && iterator.hasNext()) {
				results = (Object[]) iterator.next();
				for (int index = 0; index <= results.length-1; index++) {
					finalResult.add(results[index].toString());
				}
			}
			logger.info("result" + results);*/
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();
		}
		logger.info("results11: " + Arrays.asList(finalResult.size()));
		return list;
	}


	public List<String> fetchMrcAddOnOfferListBySegment(String segmentName) {

		logger.info("inside fetchMrcAddOnOfferListBySegment Method");
		List<String> finalResult = new ArrayList<>();
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchMrcAddonOfferLisQuery = "SELECT  sm.sdp_mrc,sm.sdp_addon   FROM t_cc_segments s , "
					+ "t_cc_segment_services ss, t_cc_service_sdp_mapping sm WHERE s.segment_id = ss.segment_id "
					+ " AND s.segment_name='" + segmentName + "' AND ss.service_id= sm.service_id AND"
					+ "(sm.sdp_addon IS NOT NULL OR sm.sdp_mrc IS NOT NULL)"
					+ "AND sm.deletion_status='N' AND ss.deletion_status = 'N' AND s.deletion_status='N'";
			SQLQuery query = session.createSQLQuery(fetchMrcAddonOfferLisQuery);
			List<Object> offerIdList = query.list() != null ? query.list() : null;
			Iterator<Object> iterator = offerIdList != null ? offerIdList.iterator() : null;
			Object[] results = null;
			while (iterator != null && iterator.hasNext()) {
				results = (Object[]) iterator.next();
				for (int index = 0; index <= results.length-1; index++) {
					if(results[index]!= null)
					finalResult.add(results[index].toString());
				}
			}
			logger.info("finalResult :" + finalResult);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			// sessionFactory.close();
		}
		logger.info("results11: " + Arrays.asList(finalResult.size()));
		return finalResult;
	}

}
