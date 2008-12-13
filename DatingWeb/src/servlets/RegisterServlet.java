package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.UserDAO;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;

/**
 * Servlet implementation class for Servlet: RegisterServlet
 *
 */
 public class RegisterServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request,final HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Facade.doInTransaction(new TransactionAction(){

			@Override
			public void execute() {
				String username = request.getParameter("username");
				String password = request.getParameter("password");
				String passwordRep = request.getParameter("passConfirm");
				System.out.println(password);
				System.out.println("Before block!");
//				try{
				System.out.println("username - "+username);
				System.out.println("username.trim - "+username.trim());
				System.out.println("password - "+password);
				System.out.println("password.trim - "+password.trim());
				System.out.println("passwordRep - "+passwordRep);
					if((username!=null)&&(!(username.trim().equals("")))){
						if((password !=null) && (!(password.trim().equals("")))){
							if(password.equals(passwordRep)){
								System.out.println("Entered block!");
								User user=new User();
								user.setName(username);
								user.setPassword(password);
								user.setAge(Long.parseLong(request.getParameter("age")));
								System.out.println(user.getAge());
								UserDAO.addUser(user);
							}
						}
					}			
			}
			
		});

		List<User> list = UserDAO.getAllUsers();
		for(User item :list){
			System.out.println(item.getName());
			System.out.println(item.getAge());
		}
		
		
		
	}   	  	    
}