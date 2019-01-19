<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html >
<html>
<head>
    <title>SEARCH</title>
</head>
<body>
<h2>SEARCH Page</h2>
<hr/>
<h3>User dashboard </h3>
<security:authorize access="isAuthenticated()">
    <%-- <b>Welcome! <security:authentication property="principal.username" /></b>--%>
</security:authorize>
<br/>
<security:authorize access="isAuthenticated()">
    <a href="/">Home</a> | <a href="logout">Logout</a>
</security:authorize>
</body>
</html>