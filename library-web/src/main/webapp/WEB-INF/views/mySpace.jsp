<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="security" %>
<%@ taglib prefix="th" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>LIBRARY</title>
</head>
<body>
<h2>My Space</h2>
<hr/>
<h3>
    Welcome !
    <security:authorize access="isAnonymous()">
        Guest
    </security:authorize>
    <!-- Print the logged in user name -->
    <security:authorize access="isAuthenticated()">
        <security:authentication property="principal"/><br><br>
        Login: ${member.login}<br>
        Name: ${member.lastName}<br>
        Firstname: ${member.firstName}<br>
        Email: ${member.email}<br>
        Loans: ${member.loanList.size()}<br>
        DateJoin: ${member.dateJoin}<br>
        <br><br>
        Loans:  ${member.loanList} <br><br>

        <br><br>
        authenticated!
    </security:authorize>
</h3>
<security:authorize access="isAnonymous()">
    Login as <a href="result">User</a> or <a href="admin">Admin</a>
</security:authorize>
<security:authorize access="isAuthenticated()">
    My Profile
    <a href="logout">Logout</a>
</security:authorize>
</body>
</html>