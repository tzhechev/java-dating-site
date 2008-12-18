<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<table>
	<tr>
		<td>Login form</td>
	</tr>
	<tr>
		<td>
		<form action="../LoginServlet" method="post">
		<table>
			<tr>
				<td>User name</td>
				<td><input type="text" name="username" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Log in" /></td>
				<td><a href="register.jsp">Register</a></td>
			</tr>
		</table>
		</form>
		<form action="../InitializeCityAndGenderServlet" method="post">
		<table>
			<tr>
				<td>
				<td><input type="submit" value="Register" /></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<form action="../TopTenServlet" method="post">
		<table>
			<tr>
				<td><select name="gender">
					<option value="female"><c:out value="female" /></option>
					<option value="male"><c:out value="male" /></option>
				</select></td>
				<input type="submit" value="Search top 10" />
			</tr>
		</table>
		</form>
		</td>
	</tr>
</table>

</body>
</html>