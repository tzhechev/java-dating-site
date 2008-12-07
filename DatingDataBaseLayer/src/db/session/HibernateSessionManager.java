/**
 * Hibernate session manager class. Handles the Hibernate session factory,
 * session and transaction and stores the currently active session in the
 * context of the current thread.
 * 
 * How to use it:
 *
 * Session hbSession = SessionManager.openSession();
 * try {
 *     // Use the session here - retrieve data, modify data, etc.
 *     // ...
 *     SessionManager.commitTransaction();
 * } catch (Throwable ex) {
 *     SessionManager.rollbackTransaction();
 *     throw new RuntimeException(ex);
 * } finally {
 *     SessionManager.closeSession();		
 * }
 * 
 * (c) 2007 by Svetlin Nakov - http://www.nakov.com
 * National Academy for Software Development - http://academy.devbg.org 
 */
package db.session;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * 
 * @author Svetlin Nakov
 */
public final class HibernateSessionManager {
	/**
	 * Hides default constructor for this utility class.
	 */
	private HibernateSessionManager() {
	}

	/**
	 * The default batch size
	 */
	public static int batchSize = 0;

	// Explicit volatile definitions
	private static volatile SessionFactory mSessionFactory = null;


	/**
	 * Starts a new session and stores it in the context of the current thread.
	 */
	public static Session openSession() {
		// Start new Hibernate session
		SessionFactory sessionFactory = getSessionFactory();
		Session hbSession = sessionFactory.getCurrentSession();

		// Start new transaction for the session
		hbSession.beginTransaction();

		return hbSession;
	}

	/**
	 * Gets the currently active Hibernate session (for the current thread).
	 * Returns null if the current thread do not have an active session.
	 */
	public static Session getCurrentSession() {
		Session hbSession = getSessionFactory().getCurrentSession();
		if (!hbSession.getTransaction().isActive()) {
			hbSession.getTransaction().begin();
		}

		return hbSession;
	}

	/**
	 * Commits the active transaction and immediately starts a new one. The
	 * session remains active.
	 */
	public static void commitTransaction() {
		Session hbSession = getSessionFactory().getCurrentSession();

		if (hbSession.getTransaction().isActive()) {
			hbSession.getTransaction().commit();
		}
	}

	/**
	 * Rollbacks the active transaction.
	 */
	public static void rollbackTransaction() {
		Session hbSession = getSessionFactory().getCurrentSession();
		// Rollback the active transaction

		Transaction tran = hbSession.getTransaction();
		if (tran.isActive()) {
			tran.rollback();
		}
	}

	public static void closeSession() {
		Session hbSession = getSessionFactory().getCurrentSession();
		if ((hbSession != null)) {
			hbSession.close();
		}
	}

	/**
	 * Create a single instance of the session factory (on demand). Implements
	 * the classical thread-safe singleton pattern with volatile static member
	 * holding the instance (see
	 * http://en.wikipedia.org/wiki/Singleton_pattern#Java).
	 */
	private static SessionFactory getSessionFactory() {
		if (mSessionFactory == null) {
			synchronized (HibernateSessionManager.class) {
				if (mSessionFactory == null) {
					// Load the Hibernate settings from hibernate.cfg.xml
					Configuration cfg = new Configuration();
					cfg.configure();

					if (batchSize > 0) {
						cfg.setProperty("hibernate.jdbc.batch_size", String
								.valueOf(batchSize));
					}

					// Create the Hibernate SessionFactory instance
					mSessionFactory = cfg.buildSessionFactory();
				}
			}
		}
		return mSessionFactory;
	}

}
