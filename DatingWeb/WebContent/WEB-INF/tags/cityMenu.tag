<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>
<select name="city">
		<c:forEach var="city" items="${sessionScope.cities}">
			<c:choose>
				<c:when test="${sessionScope.onlineUser.city.city == city.city}">	
					<option value="${city.city}" selected="selected">
						<c:out value="${city.city}"/>					
					</option>
				</c:when>
				<c:otherwise>
					<option value="${city.city}">
						<c:out value="${city.city}"/>
					</option>
				</c:otherwise>	
			</c:choose>
		</c:forEach>
	</select>