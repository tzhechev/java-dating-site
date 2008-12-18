package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.exceptions.InvalidProfileDataException;

import db.dao.UserDAO;
import db.entities.User;
import db.session.Facade;
import db.session.TransactionAction;

/**
 * Servlet implementation class for Servlet: RegisterServlet
 * 
 */
public class RegisterServlet extends BaseTransactionalServlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		doPost(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");



//		User onlineUser = (User)request.getSession().getAttribute("onlineUser");
		User user = null;
		if (isOnline(request)){
			user = (User)request.getSession().getAttribute("onlineUser");
		}
		else {
			user = new User();
		}
		try {
			
						System.out.println("Entered block!");
						validateAndAddUserName(user, request);
						validateAndAddPassword(user, request);
						validateAndAddFullName(user, request);
						validateAndAddAge(user, request);
						validateAndAddGender(user, request);
						if(isOnline(request)){
							UserDAO.updateUser(user);
						} else{
						UserDAO.addUser(user);
						}
						validateAndAddCity(user, request);
						validateAndAddStarsign(user, request);
						validateAndAddInterests(user, request);
						UserDAO.updateUser(user);
						if(isOnline(request)){
							response.sendRedirect("./pages/homePersonal.jsp");
						} else {
								response.sendRedirect("./pages/login.jsp");
						}
		} catch (InvalidProfileDataException ex) {
			request.getSession().setAttribute("erroroMsgRegistration",
					ex.getMessage());
			try {
				response.sendRedirect("./pages/register.jsp");
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}


	}
	

	

	

	

	

	private boolean isOnline(HttpServletRequest request){
		User onlineUser = (User)request.getSession().getAttribute("onlineUser");
		
		if(onlineUser != null){
			return true;
		} else {
			return false;
		}
	}
	private void validateAndAddUserName(User user, HttpServletRequest request)
			throws InvalidProfileDataException {
		if (isOnline(request)) {
			return;
		}
		String username = request.getParameter("username");
		User us = UserDAO.getUserByName(username);
		if (!validateSpellingOfUserNameOrPassword(username)){
				throw new InvalidProfileDataException("Потребителското име трябва да съдържа само латински букви, цифри и небуквени символи. Не може да съдържа текст на кирилица.");
		}
		else if (us != null){
				throw new InvalidProfileDataException(
				"Вече съществува потребител с такова потребителско име.");
		}
		else {
			user.setName(username);
		}
	}
	private boolean validateSpellingOfUserNameOrPassword(String word){
		String pattern = "[\\x21-\\x7E]*"; //Password consists of non-space ASCII characters
		if(word == null || !word.matches(pattern)){
			return false;
		} else {
			return true;
		}
	}
	private void validateAndAddPassword(User user, HttpServletRequest request)
			throws InvalidProfileDataException {
		String password = request.getParameter("password");
		String passwordRep = request.getParameter("passConfirm");
		if(isOnline(request) && (password == null) && (passwordRep == null)){ //Online user does not want to change password
			return;
		}
		if(!password.equals(passwordRep)){
			throw new InvalidProfileDataException("Повторението на паролата не съвпада.");
		} else if (!validateSpellingOfUserNameOrPassword(password)){
			throw new InvalidProfileDataException(
			"Паролата трябва да съдържа само латински букви, цифри и небуквени символи. Не може да съдържа текст на кирилица.");
			
		} else {
			user.setPassword(password);
		}
	}
	private void validateAndAddFullName(User user, HttpServletRequest request) {
		String fullName = request.getParameter("fullName");
		user.setFullName(fullName);
		
	}
	private void validateAndAddAge(User user, HttpServletRequest request)
			throws InvalidProfileDataException {
		String ageStr = request.getParameter("age");
		try {
			long age = Long.parseLong(ageStr);
			user.setAge(age);
		} catch (NumberFormatException e) {
			throw new InvalidProfileDataException("Възрастта трябва да е число");
		}
	}
	private void validateAndAddGender(User user, HttpServletRequest request) {
		String gender = request.getParameter("gender");
		user.setGender(gender);
		
	}
	private void validateAndAddCity(User user, HttpServletRequest request) {
		String city = request.getParameter("city");
		UserDAO.setUserCity(user, city);
		
	}
	private void validateAndAddStarsign(User user, HttpServletRequest request) {
		String starsign = request.getParameter("starsign");
		System.out.println("SS: "+starsign);
		UserDAO.setUserStarsign(user, starsign);
		
	}
	
	private void validateAndAddInterests(User user, HttpServletRequest request) {
		String interests = request.getParameter("interests");
		System.out.println("IS: "+interests);
		UserDAO.setUserInterest(user, interests);
		
	}

}