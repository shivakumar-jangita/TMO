package com.ericsson.tmo.cc.segment.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ConsistencyCheckerDBDao {
        private static SessionFactory sessionFactory = null;
        private static ServiceRegistry serviceRegistry = null;
        private Session currentSession = null;
        private Transaction currentTransaction = null;
        
        static {
                try {
                        // Create the SessionFactory from hibernate.cfg.xml
                        Configuration configuration = new Configuration();
                        configuration.configure();
                        serviceRegistry = new ServiceRegistryBuilder().applySettings(
                                        configuration.getProperties()).buildServiceRegistry();
                        
                        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                } catch (Throwable ex) {
                        // Make sure you log the exception, as it might be swallowed
                        System.err.println("Initial SessionFactory creation failed." + ex);
                        throw new ExceptionInInitializerError(ex);
                }
        }

        public static SessionFactory getSessionFactory() {
                return sessionFactory;
        }

        public static void closeSessionFactory() {
        if (sessionFactory != null)
            sessionFactory.close();
    }
        
	public Session openCurrentSession() {

		currentSession = getSessionFactory().openSession();

		return currentSession;

	}

	public Session openCurrentSessionwithTransaction() {

		currentSession = getSessionFactory().openSession();

		currentTransaction = currentSession.beginTransaction();

		return currentSession;

	}

	public void closeCurrentSession() {

		currentSession.close();

	}

	public void closeCurrentSessionwithTransaction() {

		currentTransaction.commit();

		currentSession.close();
	}       
   
	public List retrievQuery(String hqlQuery) {

		List p = currentSession.createQuery(hqlQuery).list();
		return p;

	}  
	/*public List deleteQuery(String hqlQuery )
	{
		Query query = currentSession.createQuery("delete SegmentsEntity where segmentId = '3'");
		int result = query.executeUpdate();
		return p;
	}*/
	
}
