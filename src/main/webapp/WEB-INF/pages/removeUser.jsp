<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>${message}</h1>
        <p>To create new user, please click <a href="${pageContext.request.contextPath}/createUser">here</a></p>
        <p>To view all users, please click <a href="${pageContext.request.contextPath}/viewUsers">here</a></p>
    </body>
</html>
