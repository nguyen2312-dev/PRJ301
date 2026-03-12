<%-- 
    Document   : search
    Created on : Feb 24, 2026, 8:34:33 AM
    Author     : khoin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>
        <font style = "color: red">
        Welcome ${sessionScope.LOGIN_INFO.fullName}
        </font> 
        <h1>Welcome to search page</h1>


        <form action="mainController" method="POST">
            
            <input type="submit" value="Logout" name="btnAction" />
            <br/>
            
            <input type="text" name="txtSearchValue" 
                   value="${param.txtSearchValue}" />
            <input type="submit" value="Search" name="btnAction" />
        </form> <br/>

        <c:set var="searchValue" value="${param.txtSearchValue}"/>
        <c:if test="${not empty searchValue}">
            <c:set var="result" value="${requestScope.SEARCH_RESULT}"/>
            <c:if test="${not empty result}">

                <font style = "color: violet">
                Showing results for ${param.txtSearchValue}
                </font>

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
                        <form action="mainController" method="POST">
                            <tr>
                                <td>${counter.count}</td>

                                <td>${dto.homeID}</td>

                                <td>${dto.description}</td>

                                <td>${dto.price}</td>

                                <td>${dto.address}</td>

                                <td>${dto.userID}</td>

                                <td>
                                    <c:url var="deleteLink" value="mainController">
                                        <c:param name="btnAction" value="Delete"/>
                                        <c:param name="pk" value="${dto.homeID}"/>
                                        <c:param name="lastSearchValue" value="${param.txtSearchValue}"/>
                                    </c:url>
                                    <a href="${deleteLink}">Delete</a>
                                </td>
                            </tr>
                        </form>
                    </c:forEach>
                </tbody>
            </table>

        </c:if>
        <c:if test = "${empty result}">
            <h2>
                <font color = "red">
                No Record is Matched !!!
                </font>
            </h2>
        </c:if>
    </c:if>

</body>
</html>
