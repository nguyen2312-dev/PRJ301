<%-- 
    Document   : homestay
    Created on : Mar 6, 2026, 10:09:29 AM
    Author     : khoin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show List</title>
    </head>
    <body>
        <h1 style = "color: red">
            Welcome ${sessionScope.LOGIN_INFO.fullName}
        </h1>

        <c:set var = "result" value = "${requestScope.RESULT_LIST}"/>
        <c:if test = "${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Home ID</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Address</th>
                        <th>User ID</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="dto" items="${result}" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.homeId}</td>
                            <td>${dto.description}</td>
                            <td>${dto.price}</td>
                            <td>${dto.address}</td>
                            <td>${dto.userId}</td>
                            <td>
                                <font>
                                <c:url var="updateLink" value="mainController">
                                    <c:param name="btnAction" value="Update_Link" />
                                    <c:param name="txtHomeId" value="${dto.homeId}" />
                                    <c:param name="txtDescription" value="${dto.description}" />
                                    <c:param name="txtPrice" value="${dto.price}" />
                                    <c:param name="txtAddress" value="${dto.address}" />
                                </c:url>
                                <a href="${updateLink}">Update This Homestay</a>
                                </font>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
                <font>
                <c:url var="addNewLink" value="mainController">
                    <c:param name="btnAction" value="AddNew" />
                </c:url>
                <a href="${addNewLink}">Add New</a>
                </font>

                <%-- Total price of homestays for logged-in user --%>
                <c:set var="totalPrice" value="0" />
                <c:forEach var="dto" items="${result}">
                    <c:set var="totalPrice" value="${totalPrice + dto.price}" />
                </c:forEach>
                <p><strong>Total price (all homestays): <fmt:formatNumber value="${totalPrice}" 
                                  type="number" groupingUsed="true" maxFractionDigits="2"/> VND</strong></p>
            </table>
        </c:if>
    </body>
</html>
