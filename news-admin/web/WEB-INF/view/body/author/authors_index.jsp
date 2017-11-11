<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div class="container body-content" id="authors-index">
    <h2>Authors</h2>
    <%--<p>--%>
        <%--<c:url var="authorCreation" value="/authors/create"/>--%>
        <%--<a href="${authorCreation}">Create New</a>--%>
    <%--</p>--%>
    <table class="table">
        <tr>
            <th>
                Name
            </th>
            <th>
                Status
            </th>
            <th></th>
        </tr>

        <c:forEach var="author" items="${authorList}">
            <tr>
                <td>
                        ${author.name}
                </td>
                <td>
                    <c:if test="${author.expired != null}">
                        Expired from ${author.expired}
                    </c:if>
                    <c:if test="${author.expired == null}">
                        Active
                    </c:if>
                </td>

                <td>
                    <sf:form modelAttribute="author" method="post" action="/authors/edit">
                        <sf:input type="text" size="40" path="name" value="${author.name}"/>
                        <sf:input type="hidden" path="id" value="${author.id}"/>
                        <input type="submit" name="command" value="update"/>
                    </sf:form>
                </td>
                <td>
                    <c:if test="${author.expired == null}">
                        <sf:form modelAttribute="author" method="post" action="/authors/delete">
                            <sf:input type="hidden" path="id" value="${author.id}"/>
                            <sf:input type="hidden" path="name" value="${author.name}"/>
                            <input type="submit" name="command" value="expire"/>
                        </sf:form>
                    </c:if>
                </td>
            </tr>
        </c:forEach>


    </table>

    <hr />
    <%--footer--%>
</div>
<sf:form modelAttribute="author" method="post" action="/authors/create">

    <sf:input path="name" type="text" value=""/>
    <input type="submit" name="command" value="add"/>

</sf:form>

<sf:form method="post" action="/logout">
    <input type="submit" value="Logout"/>
</sf:form>