package servlets;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.MessageDAO;
import db.dao.UserDAO;
import db.entities.Message;
import db.entities.User;

/**
 * Servlet implementation class for Servlet: ChatServlet
 *
 */
 public class ChatServlet extends BaseTransactionalServlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public ChatServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		List<User> list = UserDAO.getAllUsers();
		List<String> list = UserDAO.getAllUserNames();
		request.getSession().setAttribute("listNames", list);
		redirect(request, response, "./pages/chatMain.jsp");
		String message = request.getParameter("message");
		String receiverName = request.getParameter("receiver");
		if(receiverName!=null && message !=null){
			Message msg = new Message();
			msg.setToUserId(UserDAO.getUserByName(receiverName).getUserId());
			msg.setTime(new Date());
			msg.setFromUserId(((User)request.getSession().getAttribute("onlineUser")).getUserId());
			msg.setText(message);
			MessageDAO.addMessage((User)request.getSession().getAttribute("onlineUser"), receiverName, message);
		}
		
		
		
	}   	  	    
}