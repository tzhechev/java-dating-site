package db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import db.entities.Starsign;
import db.session.HibernateSessionManager;

public class StarsignDAO {
	@SuppressWarnings("unchecked")
	public static List<Starsign> getAllStarsigns(){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(Starsign.class);
		List<Starsign> cities = crit.list();
		return cities;
	}
}
