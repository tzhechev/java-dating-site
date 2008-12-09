package db.dao;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import db.entities.Message;
import db.entities.User;
import db.session.HibernateSessionManager;

public class MessageDAO {
		
	@SuppressWarnings("unchecked")
	public static List<Message> getAllMessages(){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Query query = hbSession.createQuery("from Message");
		List<Message> messages = query.list();
		return messages;
	}
	@SuppressWarnings("unchecked")
	public static List<Message> getMessagesFromUser(User user){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Query query = hbSession.createQuery("from Message as msg where msg.FromUserId="+user.getUserId()+" order by msg.Time");
		List<Message> messages = query.list();
		return messages;
	}
	@SuppressWarnings("unchecked")
	public static List<Message> getMessagesToUser(User user){
		Session hbSession = HibernateSessionManager.getCurrentSession();
		Query query = hbSession.createQuery("from Message as msg where msg.ToUserId="+user.getUserId()+" order by msg.Time");
		List<Message> messages = query.list();
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
	
}
