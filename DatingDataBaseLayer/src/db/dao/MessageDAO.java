package db.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import db.entities.Message;
import db.entities.User;
import db.session.HibernateSessionManager;

public class MessageDAO {
		
	@SuppressWarnings("unchecked")
	public static List<Message> getAllMessages(){
		Session hbSession = HibernateSessionManager.getCurrentSession();
//		Query query = hbSession.createQuery("from Message");
		Criteria crit = hbSession.createCriteria(Message.class);
		List<Message> messages = crit.list();
		return messages;
	}
	@SuppressWarnings("unchecked")
	public static List<Message> getMessagesFromUser(User user){
		Session hbSession = HibernateSessionManager.getCurrentSession();
//		Query query = hbSession.createQuery("from Message as msg where msg.FromUserId="+user.getUserId()+" order by msg.Time");
		Criteria crit = hbSession.createCriteria(Message.class)
			.add(Restrictions.eq("FromUserId", user.getUserId()))
			.addOrder(Order.asc("Time"));
		List<Message> messages = crit.list();
		return messages;
	}
	@SuppressWarnings("unchecked")
	public static List<Message> getMessagesToUser(User user){
		Session hbSession = HibernateSessionManager.getCurrentSession();
//		Query query = hbSession.createQuery("from Message as msg where msg.ToUserId="+user.getUserId()+" order by msg.Time");
		Criteria crit = hbSession.createCriteria(Message.class)
			.add(Restrictions.eq("ToUserId", user.getUserId()))
			.addOrder(Order.asc("Time"));
			
			
		List<Message> messages = crit.list();
		return messages;
	}
	public static void addMessage(User usr1, User usr2, String message){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Message nmsg = new Message();
		nmsg.setText(message);
		nmsg.setTime(Calendar.getInstance().getTime());
		nmsg.setFromUserId(usr1.getUserId());
		nmsg.setToUserId(usr2.getUserId());
		hbSession.save(nmsg);
	}
	public static void addMessage(String name1, String name2, String message){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Message nmsg = new Message();
		nmsg.setText(message);
		nmsg.setTime(Calendar.getInstance().getTime());
		User usr1 = UserDAO.getUserByName(name1);
		User usr2 = UserDAO.getUserByName(name2);
		nmsg.setFromUserId(usr1.getUserId());
		nmsg.setToUserId(usr2.getUserId());
		hbSession.save(nmsg);
	}
	public static void addMessage(User usr1, String name2, String message){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Message nmsg = new Message();
		nmsg.setText(message);
		nmsg.setTime(Calendar.getInstance().getTime());
		User usr2 = UserDAO.getUserByName(name2);
		nmsg.setFromUserId(usr1.getUserId());
		nmsg.setToUserId(usr2.getUserId());
		hbSession.save(nmsg);
	}
	public static List<Message> getConversation(String username1,String username2){
		User usr1 = UserDAO.getUserByName(username1);
		return getConversation(usr1, username2);
	}
	@SuppressWarnings("unchecked")
	public static List<Message> getConversation(User usr1,String username2){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		User usr2 = UserDAO.getUserByName(username2);
		Criteria crit = hbSession.createCriteria(Message.class)
			.add(Restrictions.or(
					Restrictions.and(
							Restrictions.eq("FromUserId", usr1.getUserId()),
							Restrictions.eq("ToUserId", usr2.getUserId())
					),
					Restrictions.and(
							Restrictions.eq("FromUserId", usr2.getUserId()),
							Restrictions.eq("ToUserId", usr1.getUserId())
					)
				)
			);
		List<Message> conversation = crit.list();
		return conversation;
	}

}
