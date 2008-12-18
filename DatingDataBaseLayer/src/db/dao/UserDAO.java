package db.dao;

import java.sql.Blob;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

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
	public static Blob getUserPictureBlob(User usr){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(Picture.class)
		.add(Restrictions.eq("UserId", usr.getUserId()))
		.setProjection(Projections.property("Picture"));
		Blob blob = (Blob)crit.uniqueResult();
		return blob;
	}
	public static Picture getUserPicture(User usr){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(Picture.class)
		.add(Restrictions.eq("UserId", usr.getUserId()));
		Picture pic = (Picture)crit.uniqueResult();
		return pic;
	}
	@SuppressWarnings("unchecked")
	public static List<User> getTopTenMale(){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(User.class)
			.add(Restrictions.eq("Gender", "M"))
			.addOrder(Order.desc("ProfileVisits"))
			.setMaxResults(10);
		List<User> result = crit.list();
		return result;
	}
	@SuppressWarnings("unchecked")
	public static List<User> getTopTenFemale(){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(User.class)
			.add(Restrictions.eq("Gender", "F"))
			.addOrder(Order.desc("ProfileVisits"))
			.setMaxResults(10);
		List<User> result = crit.list();
		return result;
	}
	public static void incProfileVisits(User usr){
		Long pv = usr.getProfileVisits();
		usr.setProfileVisits(pv+1);
	}
	public static void incProfileVisits(String uname){
		User usr = getUserByName(uname);
		Long pv = usr.getProfileVisits();
		usr.setProfileVisits(pv+1);
	}
	@SuppressWarnings("unchecked")
	public static List<User> search(String fname, String city, String interest){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(User.class);
		crit.createAlias("City", "c", CriteriaSpecification.INNER_JOIN);
		crit.createAlias("Interests", "i", CriteriaSpecification.INNER_JOIN);
		Disjunction critDisj = Restrictions.disjunction();
		if (fname != null && !fname.equals("")){
			critDisj.add(Restrictions.ilike("FullName", fname, MatchMode.ANYWHERE));
		}
		if (city != null && !city.equals("")){
			critDisj.add(Restrictions.ilike("c.City", city, MatchMode.ANYWHERE));
		}
		if (interest != null && !interest.equals("")){
			critDisj.add(Restrictions.ilike("i.Interest", interest, MatchMode.ANYWHERE));
		}
		crit.add(critDisj);
		crit.addOrder(Order.desc("ProfileVisits"));
		List<User> result = crit.list(); 
		return result;

	}
}
