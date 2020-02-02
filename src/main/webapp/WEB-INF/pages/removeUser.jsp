<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="message" var="transtales" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><fmt:message key="${message}" bundle="${transtales}"/></h1>
        <p>
            <fmt:message key="text.link.click" bundle="${transtales}"/> 
            <a href="<c:url value="/createUser"/>">
                <fmt:message key="text.link.here" bundle="${transtales}"/>
            </a> 
            <fmt:message key="text.link.newuser" bundle="${transtales}"/>
        </p>
        <p>
            <fmt:message key="text.link.click" bundle="${transtales}"/> 
            <a href="<c:url value="/viewUsers"/>">
                <fmt:message key="text.link.here" bundle="${transtales}"/>
            </a> 
            <fmt:message key="text.link.viewusers" bundle="${transtales}"/>
        </p>
    </body>
</html>
