<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="message" var="transtales" />
<fmt:setBundle basename="constants" var="q"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View all users</title>
    </head>
    <body>
        <table border="1">
            <thead>
                <tr>
                    <td><fmt:message key="username" bundle="${transtales}"/></td>
                    <td><fmt:message key="password" bundle="${transtales}"/></td>
                    <td><fmt:message key="isactive" bundle="${transtales}"/></td>
                    <td><fmt:message key="age" bundle="${transtales}"/></td>
                    <td><fmt:message key="btn.value.remove" bundle="${transtales}"/></td>
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
                                <input type="submit" name="remove" value="<fmt:message key="btn.value.remove" bundle="${transtales}"/>"/>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <p><fmt:message key="text.link.click" bundle="${transtales}"/> <a href="<c:url value="/createUser"/>"><fmt:message key="text.link.here" bundle="${transtales}"/></a> <fmt:message key="text.link.newuser" bundle="${transtales}"/></p>
    </body>
</html>
