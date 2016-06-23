<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 23.05.2016
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title></title>
  </head>
  <body>
  <c:url var="page" value="/Controller" context="/${pageContext.request.contextPath}">
      <c:param name="command" value="get-news"/>
  </c:url>
  <jsp:forward page="${page}"/>
  </body>
</html>
