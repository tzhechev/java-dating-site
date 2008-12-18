package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.MessageDAO;
import db.entities.Message;
import db.entities.User;

/**
 * Servlet implementation class for Servlet: ViewMessagesServlet
 *
 */
 public class ViewMessagesServlet extends BaseTransactionalServlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ViewMessagesServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		List<Message> list = MessageDAO.getAllMessages();
		request.getSession().setAttribute("messages", list);
		redirect(request, response, "./pages/chatMessages.jsp");
		
//		int interval = 5000; // 5 sec
//		while(true){
//			
//			Date timeToRun = new Date(System.currentTimeMillis() + interval);
//		    Timer timer = new Timer();
//		    
//		    timer.schedule(new TimerTask() {
//		            public void run() {
//		            	List<Message> list = MessageDAO.getMessagesToUser((User)request.getSession().getAttribute("onlineUser"));
//		        		for(Message m:list){
//		        			System.out.println("$^&*()KHG!!!!!!!!!!!");
//		        			System.out.println(m.getText());
//		        		}
//		        		request.getSession().setAttribute("messages", list);
//		        		
//		            }
//		        }, timeToRun);
//		}
	    
		
		
	}   	  	    
}