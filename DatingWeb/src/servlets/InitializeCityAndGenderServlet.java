package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.dao.CityDAO;
import db.dao.StarsignDAO;
import db.entities.City;
import db.entities.Starsign;

/**
 * Servlet implementation class for Servlet: InitializeCityAndGenderServlet
 *
 */
 public class InitializeCityAndGenderServlet extends BaseTransactionalServlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public InitializeCityAndGenderServlet() {
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
		
		List<City> cities = CityDAO.getAllCities();
		List<Starsign> starsigns = StarsignDAO.getAllStarsigns();
		request.getSession().setAttribute("cities", cities);
		request.getSession().setAttribute("starsigns", starsigns);
		request.getSession().setAttribute("male", "M");
		request.getSession().setAttribute("female", "F");
		String check = request.getParameter("requestFromSearch");
		if(check != null && check.equals("A")){
			redirect(request, response, "./pages/searchByCriteria.jsp");
		} else{
			redirect(request, response, "./pages/register.jsp");
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