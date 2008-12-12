<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
	<center>
		<h1>Регистрация</h1>
	</center>
	
	
	<form action="../RegisterServlet" method="post">
		Потребителско име* <input type="text" name="username"/><br>
		Парола* <input type="password" name="password"/><br>
		Потвърди парола* <input type="text" name="passCofirm"/><br>
		
		
		Пълно име*<input type="text" name="fullName" value="${sessionScope.onlineUser.fullName }"/><br>
		Възраст* <input type="text" name="age" value="${sessionScope.onlineUser.age}"/><br>
		<input type="submit" value = "Запази Промени">
		</form>
</body>
</html>