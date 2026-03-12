<%-- 
    Document   : update
    Created on : Mar 9, 2026, 1:29:34 PM
    Author     : khoin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>UPDATE PAGE</title>
                <style>
            /* Only the action controls */
            input[name="btnAction"][value="Update"],
            a.cancel-btn{
                display: inline-block;
                padding: 6px 14px;
                border: 1px solid #333;
                border-radius: 4px;
                background: #f5f5f5;
                color: #111;
                text-decoration: none;
                font: inherit;
                line-height: 1.2;
                vertical-align: middle;
                cursor: pointer;
            }

            a.cancel-btn:hover,
            input[name="btnAction"][value="Update"]:hover{
                background: #e9e9e9;
            }

            a.cancel-btn{
                margin-left: 10px;
            }
        </style>
    </head>
    <body>
        <form action="mainController" method="POST">
            <c:set var="error" value="${requestScope.HOMESTAY_ERR}"/>

            <p>User ID: <input type="text" name="txtUserID" value="${sessionScope.LOGIN_INFO.userId}" readonly /></p>

            <p>Home ID: <input type="text" name="txtHomeId" value="${param.txtHomeId}" readonly/> </p>

            <p>Description: 
                <input type="text" name="txtDescription" value="${param.txtDescription}" required="" placeholder="Description"/> </br>
                <c:if test="${not empty error.descriptionLengthErr}">
                    <font style="color: red">${error.descriptionLengthErr}</font>
                </c:if>
            </p>    

            <p>Price: 
                <input type="text" name="txtPrice" value="${param.txtPrice}" required="" placeholder="price"/> </br>
                <c:if test="${not empty error.priceErr}">
                    <font style="color: red">${error.priceErr}</font>
                </c:if>
            </p>

            <p>Address: 
                <input type="text" name="txtAddress" value="${param.txtAddress}" required="" placeholder="address"/> </br>
                <c:if test="${not empty error.addressLenghtErr}">
                    <font style="color: red">${error.addressLenghtErr}</font>
                </c:if>
            </p>

            <input type="submit" value="Update" name="btnAction" />

            <font>
            <c:url var="cancelLink" value="mainController">
                <c:param name="btnAction" value="Cancel" />
            </c:url>
            <a href="${cancelLink}" class="cancel-btn">Cancel</a>
            </font>
        </form>
    </body>
</html>
