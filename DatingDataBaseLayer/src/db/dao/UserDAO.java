package db.dao;

import java.util.List;
import db.entities.*;
import db.session.HibernateSessionManager;

import org.hibernate.Query;
import org.hibernate.Session;

public class UserDAO {
	public UserDAO(){
		
	}
	public User getUserByName(String name){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Query query = hbSession.createQuery("from User where user_name='"+name+"'");
		User usr = (User)query.uniqueResult();
		return usr;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Query allUsersQuery = hbSession.createQuery("from User");
		List<User> allUsers = allUsersQuery.list();
		return allUsers;
	}
}
