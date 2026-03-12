<%-- 
    Document   : AddNewHomestay
    Created on : Mar 7, 2026, 3:15:56 PM
    Author     : khoin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADD NEW HOMESTAY</title>
    </head>
    <body>
        <h2>ADD NEW HOMESTAY PLEASE INPUT ALL THE INFORMATION BELOW</h2>
        <form action="mainController" method="POST">


            <p>User ID: ${sessionScope.LOGIN_INFO.userId}</p> </br>
            <input type="text" name="txtHomeId" value="" required=""/>
            <c:if test="${not empty requestScope.CREATE_ERROR}">
                <span style="color:red">${requestScope.CREATE_ERROR}</span>
            </c:if>

            <input type="text" name="txtDescription" value="" required=""/> </br>

            <input type="text" name="txtPrice" value="" required=""/> </br>
            <c:if test="${not empty requestScope.PRICE_ERROR}">
                <span style="color:red">${requestScope.PRICE_ERROR}</span>
            </c:if>

            <input type="text" name="txtAddress" value="" required=""/> </br>

            <input type="submit" value="btAction" name="Save" />
            <input type="submit" value="btAction" name="Cancle" />
        </form>
    </body>
</html>
