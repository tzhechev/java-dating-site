package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.LockMode;
import org.hibernate.Session;

import db.dao.CityDAO;
import db.dao.StarsignDAO;
import db.dao.UserDAO;
import db.entities.City;
import db.entities.Starsign;
import db.entities.User;
import db.session.Facade;
import db.session.HibernateSessionManager;
import db.session.TransactionAction;

/**
 * Servlet implementation class for Servlet: LoginServlet
 * 
 */
public class LoginServlet extends BaseTransactionalServlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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

		String userName = request.getParameter("username");
		String password = request.getParameter("password");
		List<City> cities = CityDAO.getAllCities();
		List<Starsign> starsigns = StarsignDAO.getAllStarsigns();
		request.getSession().setAttribute("cities", cities);
		request.getSession().setAttribute("starsigns", starsigns);
		
		//				
		User user = UserDAO.getUserByName(userName);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				UserDAO.setUserOnline(user, true);
				request.getSession().setAttribute("onlineUser", user);
				HibernateSessionManager.getCurrentSession().lock(user, LockMode.NONE);
				request.getSession().setAttribute("onlineUserName",
						user.getName());
				redirect(request, response, "/DatingWeb/pages/homePersonal.jsp");
			} else {
				redirect(request, response, "/DatingWeb/pages/login.jsp");
			}
		} else {
			redirect(request, response, "/DatingWeb/pages/register.jsp");
							
		}

	}

	protected void redirect(HttpServletRequest request,
			HttpServletResponse response, String location) {
		
	
			try {
				response.sendRedirect(location);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		// request.getRequestDispatcher(location).forward(request, response);
		// } catch (ServletException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}