<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="security" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>LIBRARY</title>
</head>
<body>
<h2>Library - Example</h2>
<hr/>
<h3>
    Welcome !
    <security:authorize access="isAnonymous()">
        Guest
    </security:authorize>
    <!-- Print the logged in user name -->
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal"/>
    </security:authorize>
</h3>
<security:authorize access="isAnonymous()">
    Connection <a href="connect">User</a>
</security:authorize>
<security:authorize access="isAuthenticated()">

    <a href="mySpace">My Space</a><br>
    <a href="search">Search</a><br>
    <a href="logout">Logout</a>
</security:authorize>
</body>
</html>