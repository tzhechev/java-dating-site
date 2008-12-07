package db.session;

import org.hibernate.Session;

import db.exceptions.DataAccessException;


public class Facade {
	
	/**
	 * The method opens and manage session and transaction during your
	 * communication with the db.
	 * 
	 * @param action
	 */
	public static void doInTransaction(TransactionAction action) {
		@SuppressWarnings("unused")
		Session session = HibernateSessionManager.openSession();
		try {
			action.execute();
			HibernateSessionManager.commitTransaction();
		} catch (DataAccessException e) {
			HibernateSessionManager.rollbackTransaction();
			throw e;
		} finally {
			HibernateSessionManager.closeSession();
		}
	}
}
