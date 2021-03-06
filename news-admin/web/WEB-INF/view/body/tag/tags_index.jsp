<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<div id="tags-index">
    <h2>Tags</h2>
    <table>
        <c:forEach var="tag" items="${tagList}">

            <tr>
                <td>
                    <a href="">edit</a>

                    <sf:form modelAttribute="tag" method="post" action="/tag/update">

                        <sf:input path="name" value="${tag.name}"/>
                        <sf:input type="hidden" path="id" value="${tag.id}"/>
                        <input type="submit" name="command" value="update"/>
                    </sf:form>

                    <a href="">cancel</a>

                    <sf:form modelAttribute="tag" method="delete" action="/tag/delete">
                        <sf:input path="id" type="hidden" value="${tag.id}"/>
                        <input type="submit" name="command" value="delete"/>
                    </sf:form>
                </td>
            </tr>
        </c:forEach>
    </table>

    <sf:form modelAttribute="tag" method="post" action="/tag/create">
        <sf:input path="name" type="text" value=""/>
        <input type="submit" name="command" value="add"/>
    </sf:form>
</div>
<c:url value="/logout" var="logoutUrl" />
<form action="${logoutUrl}" method="post" id="logoutForm">
    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}" />
    <div>
        <input name="submit" type="submit" value="Exit"/>
    </div>
</form>