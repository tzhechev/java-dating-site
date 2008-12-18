<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

</head>
<body>

<form action="../ChatServlet" method="post">
<table align="center">
	<tr>
	<td colspan="2">
	<iframe src="chatMessages.jsp" width="550" height="300"></iframe>
	</td>
	</tr>
	<tr >
		<td>
			<textarea name="message" cols="55" rows="5";></textarea>
			
		</td>
		<td align="left">
			<select name="receiver">
				<c:forEach var="name" items="${listNames}">
					<option value="${name}"><c:out value="${name}" /></option>
				</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td align="center">
			<input type="submit" value="Изпрати">
		</td>
	</tr>
</table>
</form><div align="center"><form method="link" action="./pages/homePersonal.jsp"><input
	type="submit" VALUE="Обратно към профил"></form>
</div>

<a href="homePersonal.jsp">Register</a>




</body>
</html>