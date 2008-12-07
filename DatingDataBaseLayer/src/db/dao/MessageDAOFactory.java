package db.dao;

public class MessageDAOFactory {
	private static MessageDAO messageDAO;
	public static MessageDAO getMessageDAO(){
		if (messageDAO == null) {
			messageDAO = new MessageDAO();
		}
		return messageDAO;
	}
}

