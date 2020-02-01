<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1></h1>
        <h1>${message}</h1>
        <form method="POST" action="createUser" novalidate>

            <!-- Username input-->
            <div>
                <label for="username">Username</label>  
                <div>
                    <input id="username" name="username" type="text" placeholder="username" required maxlength="40" pattern="\w+">
                    <span>${errUsername}</span>
                </div>
            </div>

            <!-- Password input-->
            <div>
                <label for="password">Password</label>
                <div>
                    <input id="password" name="password" type="password" placeholder="password" required maxlength="40">
                    <span>${errPassword}</span>
                </div>
            </div>

            <!-- isActive Checkboxes -->
            <div>
                <label for="isActive">isActive</label>
                <div>
                    <label for="isActive-0">
                        <input type="checkbox" name="isActive" id="isActive" value="isActive">
                    </label>
                </div>
            </div>

            <!-- Age input-->
            <div>
                <label for="age">Age</label>  
                <div>
                    <input id="age" name="age" type="number" placeholder="Age" min="0" max="150" required>
                    <span>${errAge}</span>
                </div>
            </div>

            <!-- Submit input-->
            <div>
                <label for="submit">Submit</label>  
                <div>
                    <input id="submit" name="submit" type="submit">
                </div>
            </div>
        </form>
        <p>To view all users, please click <a href="<c:url value="/viewUsers"/>">here</a></p>
    </body>
</html>
