package db.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;

import db.entities.City;
import db.session.HibernateSessionManager;

public class CityDAO {
	@SuppressWarnings("unchecked")
	public static List<City> getAllCities(){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Criteria crit = hbSession.createCriteria(City.class);
		List<City> cities = crit.list();
		return cities;
	}
}
