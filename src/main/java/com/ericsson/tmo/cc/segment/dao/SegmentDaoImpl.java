/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.model.Segment;

/**
 * @author esivjan
 *
 */
public class SegmentDaoImpl extends AbstractDao implements SegmentDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final Logger logger = LoggerFactory.getLogger(SegmentDaoImpl.class);
	private Transaction transaction;
	public void createSegment(Segment segmentpojo) {
		logger.info("inside createSegment Method");
		try {
			Segment segmentsEntity = new Segment();
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

	public void deleteSegment(int segmentId) {
		logger.info("inside deleteSegment Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Segment segment = (Segment) session.load(Segment.class, segmentId);
			session.delete(segment);
			logger.debug("SegmentId= " + segmentId + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public void modifySegment(Segment segmentpojo) {
		logger.info("inside modifySegment Method");
		try {

			Session session = sessionFactory.openSession();
			session.beginTransaction();
			Segment segmentsEntity = (Segment) session.get(Segment.class, segmentpojo);
			logger.debug("segmentpojo= " + segmentpojo + " Updated Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
	}

	public List<String> fetchSegmentDetailsById(int segmentId) {

		logger.info("inside fetchSegmentDetailsById Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchSegmentDetailsByIdQuery = "SELECT * FROM t_cc_segments WHERE segment_id='" + segmentId + "'";
			SQLQuery query = session.createSQLQuery(fetchSegmentDetailsByIdQuery);
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

	public String fetchSegmentDetailsByName(int segmentId) {
		logger.info("inside fetchSegmentDetailsById Method");
		List<String> data= null;
		 Map row=null;
		 String segmentName=null;
		try {
			Session session = sessionFactory.openSession();
			transaction=session.beginTransaction();
			String fetchSegmentDetailsByNameQuery = "SELECT segment_name FROM  t_cc_segments WHERE segment_id='"
					+ segmentId + "'";
			SQLQuery query = session.createSQLQuery(fetchSegmentDetailsByNameQuery);
			 query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			
			  data = query.list();

	         for(Object object : data)
	         {
	             row = (Map)object;
	                    
	         }
	         segmentName=(String) row.get("segment_name");
			logger.info("segmentName::::::::::" + segmentName);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} 
		return 	segmentName	;

	}
	public static void main(String...ss)
	{
		SegmentDaoImpl spl= new SegmentDaoImpl();
		spl.fetchSegmentDetailsByName(1);
		
	}

}
