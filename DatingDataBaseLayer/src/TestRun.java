import java.util.List;

import db.dao.CityDAO;
import db.dao.StarsignDAO;
import db.dao.UserDAO;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;



public class TestRun {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Facade.doInTransaction(new TransactionAction(){

			@Override
			public void execute() {
				System.out.println(StarsignDAO.getAllStarsigns());
				System.out.println(CityDAO.getAllCities());
				System.out.println("==================");
//				UserDAO.incProfileVisits("john");
				List<User> list = UserDAO.getTopTenMale();
				for (User u : list){
					System.out.println(u.getFullName() + " " + u.getProfileVisits());
				}
				System.out.println("==================");
				list = UserDAO.search("john", null, null);
				for (User u : list){
					System.out.println("Search1 result: " + u.getFullName() + " " + u.getProfileVisits());
				}
				list = UserDAO.search(null, "София", null);
				for (User u : list){
					System.out.println("Search2 result: "+u.getFullName() + " " + u.getProfileVisits());
				}
				list = UserDAO.search(null, "Бургас", "skydiving");
				for (User u : list){
					System.out.println("Search3 result: "+u.getFullName() + " " + u.getProfileVisits());
				}
				
				
				
				
			}
			
		});		
//		Facade.doInTransaction(new TransactionAction(){
//
//			@Override
//			public void execute() {
//				List<Message> convo = MessageDAO.getConversation("john", "jim");
//				for (Message m : convo){
//					System.out.println("Message "+m.getTime()+": "+m.getFromUserId() + " to "
//							+m.getToUserId() + " : " + m.getText() );
//				}
//				
//			}
//			
//		});
//		Facade.doInTransaction(new TransactionAction() {
//
//			public void execute() {
//				System.out.println(UserDAO.getAllUserNames());
//				List<User> findAll = UserDAO.getAllUsers();
//				System.out.println(findAll.get(0).getCity());
//				UserDAO.setUserCity(findAll.get(0), "Бургас");
//				UserDAO.updateUser(findAll.get(0));
//				System.out.println(findAll.get(0).getStarsign());
////				UserDAO.setUserCity(findAll.get(0), "Бургас");
////				UserDAO.updateUser(findAll.get(0));
//				System.out.println(findAll);
//				System.out.println("User0:" + findAll.get(0).getPicture());
//				System.out.println(findAll.get(0).getInterests());
//				System.out.println(findAll.get(0).getName());
//				System.out.println(findAll.get(1).getName());
//			}
//			
//		});
//		
//		Facade.doInTransaction(new TransactionAction() {
//
//			public void execute() {
//				List<Message> findAll = MessageDAO.getAllMessages();
//				System.out.println(findAll);
//			}
//			
//		});		
//		
//		
//		Facade.doInTransaction(new TransactionAction() {
//
//			public void execute() {
//				User usr1 = UserDAO.getUserByName("josh");
//				User usr2 = UserDAO.getUserByName("amy");
//				System.out.println(usr2.getFullName());
//				System.out.println(usr2.getPassword());
//				System.out.println(usr2.getPicture());
//				System.out.println(usr2.getInterests());
//				MessageDAO.addMessage(usr1,usr2,"Muhaha!");
//			}
//		});
//		Facade.doInTransaction(new TransactionAction() {
//
//			public void execute() {
//				User usr1 = UserDAO.getUserByName("josh");
//				List<Message> findAll = MessageDAO.getMessagesFromUser(usr1);
//				System.out.println(findAll);
//				for (Message m : findAll){
//					String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
//					SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
//					System.out.print(sdf.format(m.getTime())+" ");
//					
//					System.out.println(m.getText());
//				}
//			}
//		});
//		
//		Facade.doInTransaction(new TransactionAction() {
//
//			public void execute() {
//				User usr1 = UserDAO.getUserByName("josh");
//				UserDAO.setUserOnline(usr1,true);
//			}
//			
//		});
//		
		System.out.println("DONE!");
	}

}
