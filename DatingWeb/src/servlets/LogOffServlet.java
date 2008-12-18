package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.exceptions.InvalidProfileDataException;
import db.dao.UserDAO;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;

/**
 * Servlet implementation class for Servlet: LogOffServlet
 * 
 */
public class LogOffServlet extends BaseTransactionalServlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LogOffServlet() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		User user = (User) request.getSession().getAttribute("onlineUser");
		UserDAO.setUserOnline(user, false);
		UserDAO.updateUser(user);
		request.getSession().invalidate();
		redirect(request, response, "./pages/login.jsp");

	}

	protected void redirect(HttpServletRequest request,
			HttpServletResponse response, String location) {
		try {
			response.sendRedirect(location);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}