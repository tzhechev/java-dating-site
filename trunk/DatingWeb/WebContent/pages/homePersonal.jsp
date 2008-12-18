<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<div align="left">
		<table>
		<th align="left">Hello <c:out value = "${onlineUserName}"/></th>
		<tbody>
		<tr>
			<td align="center">
					<form action="../ViewPersonalInfo" method="post">
						<input type="submit" value="View profile" />
					</form>
			</td>
			<td align="center">
					
					<form method="link" action="register.jsp">
						<input type="submit" VALUE="Change Profile">
					</form>
			</td>
			<td>
				<form action="../LogOffServlet" method="post">
						<input type="submit" value="Log off" />
				</form>
			</td>
			<td>
				<form action="../ChatServlet" method="post">
						<input type="submit" value="Start Chat" />
				</form>
			</td>
				
		</tr>
		</tbody>
		</table>

		</div>
</body>
</html>