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

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String fullName = request.getParameter("fullName");
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");

//		System.out.println(password);
//		System.out.println("Before block!");
//		// try{
//		System.out.println("username - " + username);
//		System.out.println("username.trim - " + username.trim());
//		System.out.println("password - " + password);
//		System.out.println("password.trim - " + password.trim());
//		System.out.println("passwordRep - " + passwordRep);

		try {
			if ((username != null) && (!(username.trim().equals("")))) {
				if ((password != null) && (!(password.trim().equals("")))) {
						System.out.println("Entered block!");
						User user = new User();
						validateAndAddUserName(user, request);
						validateAndAddPassword(user, request, password);
						user.setFullName(fullName);
						if (gender.equals("male")) {
							user.setGender("M");
						} else {
							user.setGender("F");
						}
//						user.setCity(city);
						validateAndAddAge(user, request);
						System.out.println(user.getAge());
						UserDAO.addUser(user);
//					}
				}else {
					throw new InvalidProfileDataException("Въведете парола!");
				}
			} else {
				throw new InvalidProfileDataException("Въведете потребителско име!");
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

//		List<User> list = UserDAO.getAllUsers();
//		for (User item : list) {
//			System.out.println(item.getName());
//			System.out.println(item.getFullName());
//			System.out.println(item.getAge());
//		}

	}

	private void validateAndAddUserName(User user, HttpServletRequest request)
			throws InvalidProfileDataException {
		String username = request.getParameter("username");
		User us = UserDAO.getUserByName(username);
		if (us != null) {
			throw new InvalidProfileDataException(
					"Вече съществува потребител с такова потребителско име.");
		} else {
			user.setName(username);
		}

	}
	private void validateAndAddPassword(User user, HttpServletRequest request, String password)
			throws InvalidProfileDataException {
		String passwordRep = request.getParameter("passConfirm");
		String pattern = "[\\x21-\\x7E]*";  //Password consists of non-space ASCII characters
		if(!password.matches(pattern)){
			throw new InvalidProfileDataException(
			"Паролата трябва да съдържа само латински букви, цифри и небуквени символи. Не може да съдържа текст на кирилица.");
		} else if (!password.equals(passwordRep)){
			throw new InvalidProfileDataException("Повторението на паролата не съвпада.");
			
		} else {
			user.setPassword(password);
		}
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

}