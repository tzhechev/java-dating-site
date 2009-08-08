package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.UserDAO;
import db.entities.User;

/**
 * Servlet implementation class for Servlet: TopTenServlet
 *
 */
 public class TopTenServlet extends BaseTransactionalServlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public TopTenServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String gender = request.getParameter("gender");
		String check = request.getParameter("requestFromSearch");
//		System.out.println("AAAAAAAAAAAAAAAAAAAAa" + check);
		List<User> users = null;
		if((check != null) && check.equals("A")){
			String fullName = request.getParameter("fullName");
			String city = request.getParameter("city");
			String interests = request.getParameter("interests");
			users = UserDAO.search(fullName, city, interests);
		} else if (gender.equals("M")){
//			users = UserDAO.getAllUsers();
			users = UserDAO.getTopTenMale();
		} else {
//			users = UserDAO.getAllUsers();
			users = UserDAO.getTopTenFemale();
		}
		
		request.getSession().setAttribute("topTen", users);
		redirect(request, response, "./pages/viewTopTen.jsp");
	}
	
	protected void redirect(HttpServletRequest request,
			HttpServletResponse response, String location) {
		
	
			try {
				response.sendRedirect(location);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}