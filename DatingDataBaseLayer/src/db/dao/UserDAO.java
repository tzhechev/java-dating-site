package db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.lob.SerializableBlob;

import db.entities.City;
import db.entities.Picture;
import db.entities.Starsign;
import db.entities.User;
import db.session.HibernateSessionManager;

public class UserDAO {
	public UserDAO(){
		
	}
	@SuppressWarnings("unchecked")
	public static List<String> getAllUserNames() {
		  Session hbSession = HibernateSessionManager.getCurrentSession();
//		  Query allUsersQuery = hbSession.createQuery("select Name from User");
		  
		  Criteria crit = hbSession.createCriteria(User.class)
		  	.setProjection(Projections.property("Name"));
		  List<String> allUsers = crit.list();
		  return allUsers;
		 }
	/**
	 * Returns a User object from the database.
	 * @param name User username.
	 * @return The User object or null if not found.
	 */
	public static User getUserByName(String name){
		Session hbSession = HibernateSessionManager.getCurrentSession();
//		Query query = hbSession.createQuery("from User where user_name='"+name+"'");
		Criteria crit = hbSession.createCriteria(User.class).add(Restrictions.eq("Name", name));
		User usr = (User)crit.uniqueResult();
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
		if (online) {
			user.setOnline("Y");
		}
		else {
			user.setOnline("N");
		}
	}
	
	/**
	 * @return A List<User> of all users.
	 */
	@SuppressWarnings("unchecked")
	public static List<User> getAllUsers() {
		Session hbSession = HibernateSessionManager.getCurrentSession();
//		Query allUsersQuery = hbSession.createQuery("from User");
		Criteria crit = hbSession.createCriteria(User.class);
		List<User> allUsers = crit.list();
		return allUsers;
	}
	public static void setUserCity(User usr,String city){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(City.class)
			.add(Restrictions.eq("City", city));
		City usrCity = (City)crit.uniqueResult();
		usr.setCity(usrCity);
	}
	public static void setUserStarsign(User usr,String starsign){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(Starsign.class)
			.add(Restrictions.eq("Starsign", starsign));
		Starsign usrStarsign = (Starsign)crit.uniqueResult();
		usr.setStarsign(usrStarsign);
	}
	public static SerializableBlob getUserPicture(User usr){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(Picture.class)
		.add(Restrictions.eq("UserId", usr.getUserId()))
		.setProjection(Projections.property("Picture"));
		SerializableBlob blob = (SerializableBlob)crit.uniqueResult();
		return blob;
		
	}
}
