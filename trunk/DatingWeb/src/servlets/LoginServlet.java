package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.UserDAO;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;



/**
 * Servlet implementation class for Servlet: LoginServlet
 *
 */
 public class LoginServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		Facade.doInTransaction(new TransactionAction(){

			@Override
			public void execute() {
				// TODO Auto-generated method stub
				String userName = request.getParameter("username");
				String password = request.getParameter("password");
//				User user = new User();
//				UserDAOFactory fac = new UserDAOFactory();
				User user = UserDAO.getUserByName(userName);
				if (user != null) {
					if (user.getPassword().equals(password)){
						UserDAO.setUserOnline(user, true);
						request.getSession().setAttribute("onlineUserName", user.getName());
						redirect(request, response,"/pages/homePersonal.jsp" );
//						try {
//							request.getRequestDispatcher("/pages/homePersonal.jsp").
//							forward(request, response);
//						} catch (ServletException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					} else {
						redirect(request, response,"/pages/login.jsp" );
//						try {
//							request.getRequestDispatcher("/pages/login.jsp").
//							forward(request, response);
//						} catch (ServletException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					}
				} else {
					redirect(request, response,"/pages/register.jsp" );
//					try {
//						request.getRequestDispatcher("/pages/register.jsp").
//						forward(request, response);
//					} catch (ServletException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					} catch (IOException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
				}				
			}
			
		});

	} 
	protected void redirect(HttpServletRequest request,
			HttpServletResponse response, String location) {
		try {
			request.getRequestDispatcher(location).forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}