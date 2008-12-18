<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ tag body-content="empty"%>
<select name="gender"> <c:choose>
	<c:when test='${sessionScope.onlineUser.gender == "F"}'>
		<option value="F" selected="selected">
		<c:out value="female" />
		</option>
		<option value="M"> <c:out value="male" /> </option>
	</c:when>
	<c:when test='${sessionScope.onlineUser.gender == "М"}'>
		<option value="F"> <c:out value="female" /> </option>
		<option value="М" selected="selected"> <c:out value="male" />
		</option>
	</c:when>
	<c:otherwise>
		<option value="F">
		<c:out value="female" />
		</option>
		<option value="M"> <c:out value="male" /> </option>
	</c:otherwise>
</c:choose> </select>