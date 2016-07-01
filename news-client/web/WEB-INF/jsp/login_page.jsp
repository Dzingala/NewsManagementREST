<%--
  Created by IntelliJ IDEA.
  User: Ivan_Dzinhala
  Date: 7/1/2016
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8"/>
</head>
<body>
<section class = "container">
    <div class = "login">
       head
        <form name="loginForm" method="POST" action="/Controller">
            <input type="hidden" name="command" value="login" />
            <p>
                <label>
                    login:<br/>
                    <input type="text"
                           name="login"
                           value=""
                           pattern="(?=.*[a-z]).{4,}"
                           required
                           title="Login title"
                    />
                </label>
            </p>
            <p>
                <label>
                    <br/>password:<br/>
                    <input type="password"
                           name="password"
                           value=""
                           title="passWarning"
                    />
                </label>
            </p>
            <br/>
            <p class = "submit">
                <input type="submit" value="loginButton"/>
            </p>
        </form>
    </div>
    <br/>
    no account?
    <hr/>
    <br/>

    <hr/>
</section>
</body>
</html>

