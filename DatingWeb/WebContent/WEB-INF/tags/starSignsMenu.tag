<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag body-content="empty" %>
<select name="starsign">
		<c:forEach var="starsign" items="${sessionScope.starsigns}" >
			<c:choose>
				<c:when test="${sessionScope.onlineUser.starsign.starsign == starsign.starsign}">	
					<option value="${starsign.starsign}" selected="selected">
						<c:out value="${starsign.starsign}"/>					
					</option>
				</c:when>
				<c:otherwise>
					<option value="${starsign.starsign}">
						<c:out value="${starsign.starsign}"/>
					</option>
				</c:otherwise>	
			</c:choose>
		</c:forEach>
	</select>