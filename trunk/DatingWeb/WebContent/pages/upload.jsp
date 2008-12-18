<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Picture upload</title>
</head>
<body>
<h2>
<c:if test="${not empty sessionScope.uploadStatus}">
<c:out value="${sessionScope.uploadStatus}"/>
</c:if>
</h2>
<form action="../Upload" method="post" enctype="multipart/form-data">
<input type="file" name="file"/><br/><br/>
<input type="submit" value="Upload"/>
</form>
<br/>
<a href="homePersonal.jsp"><button>Back to personal page</button></a>
</body>
</html>