<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top Ten Visited Users</title>
</head>
<body>
	<div align="center">Hello <c:out
	value="${sessionScope.onlineUserName}" /></div>
	
	<table>
	<c:forEach var="user" items="${sessionScope.topTen}" >
		<tr>
			<td>
				<c:out value="${user.name}"/>
			<td>
			<td>
				<c:out value="${user.fullName}"/>
			<td>
			<td>
				<c:out value="${user.email}"/>
			<td>
			<td>
				<c:out value="${user.gender}"/>
			<td>
		
		</tr>
		<tr>
			<td>
			<form action="../ViewPersonalInfo" method="post">
				<input type="text" value="${user.name}" name = "name"  style="display: none" />
				<input type="submit" value="Виж профила"/>
			</form>
			</td>
		</tr>
	</c:forEach>
	</table>

</body>
</html>