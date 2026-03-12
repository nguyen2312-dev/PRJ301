<%-- 
    Document   : login
    Created on : Feb 24, 2026, 3:19:40 AM
    Author     : ThongNT2025
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1 style="color: orange">LOGIN PAGE</h1>
        <form action="mainController" method="POST">
            userId <input type="text" name="txtUserId" value="" required="true"/> <br/>
            password <input type="password" name="txtPassword" value="" required=""/> <br/>
            <input type="submit" value="Login" name="btnAction" />
            <input type="reset" value="Reset" /> <br/>
        </form>

        <c:set var = "invalid" value = "${requestScope.ERROR_MESSAGE}"/>
        <c:if test = "${not empty invalid}">
            <h2 style = "color: red">${invalid}</h2>
        </c:if>
    </body>
</html>
