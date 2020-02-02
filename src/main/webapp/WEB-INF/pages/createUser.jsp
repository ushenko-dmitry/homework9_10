<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="message" var="transtales" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>
            <c:if test="${not empty message}">
                <fmt:message key="${message}" bundle="${transtales}"/>
            </c:if>
        </h1>
        <form method="POST" action="createUser">

            <!-- Username input-->
            <div>
                <label for="username"><fmt:message key="username" bundle="${transtales}"/></label>  
                <div>
                    <input id="username" name="username" type="text" placeholder="username" required maxlength="40" pattern="\w+">
                    <span>
                        <c:if test="${not empty errUsername}">
                            <fmt:message key="${errUsername}" bundle="${transtales}"/>
                        </c:if>
                    </span>
                </div>
            </div>

            <!-- Password input-->
            <div>
                <label for="password"><fmt:message key="password" bundle="${transtales}"/></label>
                <div>
                    <input id="password" name="password" type="password" placeholder="password" required maxlength="40">
                    <span>
                        <c:if test="${not empty errPassword}">
                            <fmt:message key="${errPassword}" bundle="${transtales}"/>
                        </c:if>
                    </span>
                </div>
            </div>

            <!-- isActive Checkboxes -->
            <div>
                <label for="isActive"><fmt:message key="isactive" bundle="${transtales}"/></label>
                <div>
                    <input type="checkbox" name="isActive" id="isActive" value="isActive">
                </div>
            </div>

            <!-- Age input-->
            <div>
                <label for="age"><fmt:message key="age" bundle="${transtales}"/></label>  
                <div>
                    <input id="age" name="age" type="number" placeholder="Age" min="0" max="150" required>
                    <span>
                        <c:if test="${not empty errAge}">
                            <fmt:message key="${errAge}" bundle="${transtales}"/>
                        </c:if>
                    </span>
                </div>
            </div>

            <!-- Submit input-->
            <div>
                <label for="submit"><fmt:message key="btn.value.submit" bundle="${transtales}"/></label>  
                <div>
                    <input id="submit" name="submit" type="submit" value="<fmt:message key="btn.value.submit" bundle="${transtales}"/>">
                </div>
            </div>
        </form>
        <p>
            <fmt:message key="text.link.click" bundle="${transtales}"/> 
            <a href="<c:url value="/viewUsers"/>">
                <fmt:message key="text.link.here" bundle="${transtales}"/>
            </a> 
            <fmt:message key="text.link.viewusers" bundle="${transtales}"/>
        </p>
    </body>
</html>
