/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.model.InconsistencyRecords;

/**
 * @author esivjan
 *
 */
public class InconsistencyDaoImpl extends AbstractDao implements InconsistencyDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final Logger logger = LoggerFactory.getLogger(InconsistencyDaoImpl.class);

	public void createInconsistencyDetails(InconsistencyRecords inconsistencyRecordsPojo) {
		try {
			logger.info("createInconsistencyDetails method is calling");
			InconsistencyRecords Entity = new InconsistencyRecords();
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.save(Entity);
			logger.info("InconsistencyRecords Created Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			logger.error("ERROR" + e);
		} finally {
			//sessionFactory.close();

		}

	}

	public void deleteInconsistencyDetails(String exestatus) {
		logger.info("deleteInconsistencyDetails method is calling");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			InconsistencyRecords segment = (InconsistencyRecords) session.load(InconsistencyRecords.class, exestatus);
			session.delete(segment);
			logger.info("ExecutionStatus= " + exestatus + " Deleted Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			//sessionFactory.close();

		}
	}

	public void modifyInconsistencyDetails(int segmentId) {
		logger.info("modifyInconsistencyDetails method is calling");
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			InconsistencyRecords segmentsEntity = (InconsistencyRecords) session.get(InconsistencyRecords.class,
					segmentId);
			segmentsEntity.setCreatedUser("NotificationCCuser");
			logger.info("SegmentId= " + segmentId + " Updated Successfully");
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			//sessionFactory.close();
		}
	}

	public List<String> fetchInconsistencyMsisdnData(int segmentId) {
		logger.info("inside fetchInconsistencyMsisdnData Method");
		List<String> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchMsisdnListQuery = "SELECT msisdn FROM t_cc_inconsistency_records WHERE segment_id='"+ segmentId 
					+ "' AND execution_status = 'N'" ;
			SQLQuery query = session.createSQLQuery(fetchMsisdnListQuery);
			results = query.list();
			logger.info("result" + results);
			session.getTransaction().commit();
			session.close();
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			//sessionFactory.close();
		}
		logger.info("results11: " + Arrays.asList(results.size()));
		return results;
	}

	
	
	public List<Integer> fetchUniqueSegmentIdList() {
		logger.info("inside fetchUniqueSegmentIdList Method");
		List<Integer> results = null;
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			String fetchSegmentIdQuery = "SELECT DISTINCT segment_id  FROM t_cc_inconsistency_records WHERE execution_status = 'N'";
			SQLQuery query = session.createSQLQuery(fetchSegmentIdQuery);
			query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			results = query.list();
			session.getTransaction().commit();
			session.close();
			
		} catch (Exception hbe) {
			logger.error("ERROR" + hbe);
		} finally {
			
		}
		logger.info("results: " + Arrays.asList(results.size()));
		return results;
	}
public static void main(String...sa)
{
	InconsistencyDaoImpl ic= new InconsistencyDaoImpl();
	ic.fetchInconsistencyMsisdnData(1);
}
	}
