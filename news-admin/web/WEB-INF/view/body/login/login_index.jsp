<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<security:authorize access="isAnonymous()">
    <c:if test="${param.error != null}">
        <p>Invalid credentials. Something went wrong.</p>
    </c:if>
    <c:url var="loginUrl" value="/login"/>
    <form action="${loginUrl}" method="post">
        <p><label for="username">User:</label></p>
        <input type="text" id="username" name="username"/>

        <p><label for="password">Password:</label></p>
        <input type="password" id="password" name="password">

        <div>
            <input name="submit" type="submit"/>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>
</security:authorize>
<security:authorize access="!hasRole('ROLE_ADMIN')">
    text for not admins only.
</security:authorize>