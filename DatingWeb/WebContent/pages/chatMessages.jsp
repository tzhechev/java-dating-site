<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body><script type="text/javascript">
function reFresh() {
 document.myform.submit();
}
window.setInterval("reFresh()",5000);
</script>

<form name="myform" action="../ViewMessagesServlet" method="post">
<c:forEach var="message" items="${messages}">
			<tr>
				<td><c:out value="${message.time}" />&nbsp;</td>
				<td><c:out value="${message.text}" />
				</td>
			</tr><br>
		</c:forEach>
</form>
</body>
</html>