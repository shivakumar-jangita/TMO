/**
 * 
 */
package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.tmo.cc.segment.dao.model.ConsistencyRecords;

/**
 * @author esivjan
 *
 */
public class ConsistencyDaoImpl extends AbstractDao implements ConsistencyDao {

	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	private static final Logger logger = LoggerFactory.getLogger(ConsistencyDaoImpl.class);

	public void createConsistencyDetails(ConsistencyRecords consistencyRecordsPojo) {

	}

	public void deleteConsistencyDetails(String exestatus) {

	}

	public void modifyConsistencyDetails(int segmentId) {

	}

	public List<String> fetchInconsistencyDataById(int segmentId) {

		return null;
	}
}
