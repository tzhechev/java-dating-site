<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="dr" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<style type="text/css">
textarea {
	overflow-y: scroll;
}
</style>
</head>
<body>
<div align="right"><c:if test="${onlineUser != null}">
<form action="../LogOffServlet" method="post">
						<input type="submit" value="Log off" />
					</form>
	<a href="../LogOffServlet">Log out</a>
</c:if></div>
<center>
<c:choose>
<c:when test="${onlineUser != null}">
<h1>Промяна на профил</h1>
</c:when>
<c:otherwise>
<h1>Регистрация</h1>
</c:otherwise>	
</c:choose>
</center>

<div style="color: red"><c:if
	test="${sessionScope.erroroMsgRegistration != null}">
	<blink><BIG>Error!<br>
	</BIG></blink>
</c:if> <c:out value="${sessionScope.erroroMsgRegistration}" /></div>
<form action="../RegisterServlet" method="post">
<table align="center" border="1" cellspacing="0" cellpadding="1">


	<tr>
		<td align="right">Потребителско име*</td>
		<td><c:choose>
				<c:when test="${onlineUser != null}"><input type="text" name="username"
			value="${sessionScope.onlineUser.name }" readonly="readonly" /></c:when>
			<c:otherwise>
			<input type="text" name="username"/>
			</c:otherwise>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td align="right">Парола*</td>
		<td><input type="password" name="password" /></td>
	</tr>
	<tr>
		<td align="right">Потвърди парола*</td>
		<td><input type="password" name="passConfirm" /></td>
	</tr>

	<tr>
		<td align="right">Пълно име*</td>
		<td><input type="text" name="fullName"
			value="${sessionScope.onlineUser.fullName }" /></td>
	<tr>
		<td align="right">Възраст*</td>
		<td><input type="text" name="age"
			value="${sessionScope.onlineUser.age}" /></td>
	<tr>
		<td align="right">Пол*</td>
		<td><dr:genderMenu /></td>
		
	</tr>
	<tr>
		
		<td align="right">Населено място</td>
		<td> <dr:cityMenu /></td>
	</tr>
	<tr>
		<td align="right">Зодия</td>
		<td><dr:starSignsMenu /></td>
		
	</tr>
	<tr>
		<td align="right">Интереси</td>
		<td><textarea name="interests" cols="36" rows="5";><c:out value="${sessionScope.onlineUser.interests.interest}" /></textarea></td>

	</tr>
</table>
<div align="center"><input type="submit" value="Запази Промени"></div>
</form>

</body>
</html>