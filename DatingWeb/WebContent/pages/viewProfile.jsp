<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>

<style type ="text/css">
table{
background-color:#333333;
}

td{
background-color:#33FF33;
}
</style>

</head>
<body>
<div align="center">Hello <c:out
	value="${sessionScope.onlineUserName}" /></div>
<table align="center"  cellspacing="1" cellpadding="15">
	<tr>
		<td align="right">Име</td>
		<td><c:out value="${sessionScope.onlineUser.fullName}" /></td>
	</tr>
	<tr>
		<td align="right">Местоживеене</td>
		<td><c:out value="${sessionScope.onlineUser.city}" /></td>
	</tr>
	<tr>
		<td align="right">Пол</td>
		<td><c:out value="${sessionScope.onlineUser.gender}" /></td>
	</tr>
	<tr>
		<td align="right">Възраст</td>
		<td><c:out value="${sessionScope.onlineUser.age}" /></td>
	</tr>
	<tr>
		<td align="right">Зодия</td>
		<td><c:out value="${sessionScope.onlineUser.starsign}" /></td>
	</tr>
	<tr>
		<td align="right">Брой посещения на профила</td>
		<td><c:out value="${sessionScope.onlineUser.profileVisits}" /></td>
	</tr>
	<tr>
		<td align="right">Интереси</td>
		<td><c:out value="${sessionScope.onlineUser.interests.interest}" /></td>
	</tr>
</table>
<div align="center"><form method="link" action="pages/register.jsp"><input
	type="submit" VALUE="Change Profile"></form>
</div>
</body>
</html>