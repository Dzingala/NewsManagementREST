<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 25.05.2016
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <c:forEach var="author" items="${authors}">
        <tr>
            <td>
                <a href="${author.id}">edit</a>
            </td>
            <td>
                ${author.name}
            </td>
        </tr>
    </c:forEach>
</table>
