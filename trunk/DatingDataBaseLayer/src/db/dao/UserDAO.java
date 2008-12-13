package db.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import db.entities.User;
import db.session.HibernateSessionManager;

public class UserDAO {
	public UserDAO(){
		
	}
	/**
	 * Returns a User object from the database.
	 * @param name User username.
	 * @return The User object or null if not found.
	 */
	public static User getUserByName(String name){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Query query = hbSession.createQuery("from User where user_name='"+name+"'");
		User usr = (User)query.uniqueResult();
		return usr;
	}
	
	/**
	 * Adds a User to the database.
	 * @param user The User object to be saved.
	 */
	public static void addUser(User user){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		user.setProfileVisits(0L);
		user.setOnline("N");
		hbSession.save(user);
	}
	
	/**
	 * Updates a User entry in the database.
	 * @param user The User object to be updated.
	 */
	public static void updateUser(User user){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		hbSession.update(user);
	}
	
	/**
	 * Sets the status of a User as online or offline.
	 * @param user The User object to be set.
	 * @param online The status.
	 */
	public static void setUserOnline(User user, boolean online){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		if (online) {
			user.setOnline("Y");
		}
		else {
			user.setOnline("N");
		}
		hbSession.update(user);
	}
	
	/**
	 * @return A List<User> of all users.
	 */
	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers() {
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Query allUsersQuery = hbSession.createQuery("from User");
		List<User> allUsers = allUsersQuery.list();
		return allUsers;
	}
}
