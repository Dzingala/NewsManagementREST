<%--
  Created by IntelliJ IDEA.
  User: Ivan_Dzinhala
  Date: 7/1/2016
  Time: 12:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>tit</title>
</head>
<body>
<c:url var="page" value="/Controller" context="/${pageContext.request.contextPath}">
    <c:param name="command" value="get-news"/>
</c:url>
<jsp:forward page="${page}"/>
</body>
</html>
