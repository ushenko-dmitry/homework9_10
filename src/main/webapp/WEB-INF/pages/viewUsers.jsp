<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>

        <table border="1">
            <thead>
                <tr>
                    <td>Username</td>
                    <td>Password</td>
                    <td>isActive</td>
                    <td>Age</td>
                    <td>Remove</td>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="user">
                    <tr>
                        <td>${user.username}</td>
                        <td>${user.password}</td>
                        <td>
                            <c:choose>
                                <c:when test="${user.isActive}">
                                    I am a superman
                                </c:when>
                                <c:otherwise>
                                    Staying at shadow
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${user.age}</td>
                        <td>
                            <form method="POST" action="removeUser">
                                <input type="hidden" name="userId" value="${user.id}"/>
                                <input type="submit" name="remove" value="Remove"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p>click <a href="${pageContext.request.contextPath}/createUser">here</a> to add new user</p>
    </body>
</html>
