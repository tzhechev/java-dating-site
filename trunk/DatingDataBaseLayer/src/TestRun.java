import java.text.SimpleDateFormat;
import java.util.List;

import db.dao.MessageDAO;
import db.dao.UserDAO;
import db.entities.Message;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;



public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Facade.doInTransaction(new TransactionAction() {

			public void execute() {
				List<User> findAll = UserDAO.getAllUsers();
				System.out.println(findAll);
				System.out.println(findAll.get(0).getPicture());
				System.out.println(findAll.get(0).getInterests());
				System.out.println(findAll.get(0).getName());
				System.out.println(findAll.get(1).getName());
			}
			
		});
		
		Facade.doInTransaction(new TransactionAction() {

			public void execute() {
				List<Message> findAll = MessageDAO.getAllMessages();
				System.out.println(findAll);
			}
			
		});		
		
		
		Facade.doInTransaction(new TransactionAction() {

			public void execute() {
				User usr1 = UserDAO.getUserByName("josh");
				User usr2 = UserDAO.getUserByName("мая");
				System.out.println(usr2.getFullName());
				System.out.println(usr2.getPassword());
				System.out.println(usr2.getPicture());
				System.out.println(usr2.getInterests());
				MessageDAO.addMessage(usr1,usr2,"Muhaha!");
				
			}
			
		});
		Facade.doInTransaction(new TransactionAction() {

			public void execute() {
				User usr1 = UserDAO.getUserByName("josh");
				List<Message> findAll = MessageDAO.getMessagesFromUser(usr1);
				System.out.println(findAll);
				for (Message m : findAll){
					String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
					System.out.print(sdf.format(m.getTime())+" ");
					
					System.out.println(m.getText());
				}
			}
			
		});		
		
		System.out.println("DONE!");
	}

}
