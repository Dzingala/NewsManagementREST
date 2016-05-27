<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 27.05.2016
  Time: 7:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<table>
    <c:forEach var="author" items="${authorList}">
        <tr>
            <td>

                <a href="${author.id}">edit</a>


                <sf:form modelAttribute="author" method="post" action="/author/update">

                    <%--<form method="post" action="/author/update">--%>
                    <sf:input type="text" size="40" path="name" value="${author.name}"/>
                    <sf:input type="hidden" path="id" value="${author.id}"/>
                    <input type="submit" name="command" value="update"/>
                    <%--</form>--%>
                </sf:form>

                <a href="">cancel</a>


                <c:if test="${author.expired eq null}">
                    <%--<a href="">expire</a>--%>

                    <sf:form modelAttribute="author" method="post" action="/author/delete">

                        <%--<form method="post" action="/author/delete">--%>
                        <%--<input type="hidden" name="authorToDelete" value="${author.id}"/>--%>
                        <sf:input type="hidden" path="id" value="${author.id}"/>
                        <input type="submit" name="command" value="expire"/>
                        <%--</form>--%>
                    </sf:form>
                </c:if>
                ${author.expired}
            </td>
        </tr>
    </c:forEach>

</table>
