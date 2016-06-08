<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container body-content">
    <h2>News</h2>
    <%--<p>--%>
    <%--<c:url var="authorCreation" value="/authors/create"/>--%>
    <%--<a href="${authorCreation}">Create New</a>--%>
    <%--</p>--%>
    <sf:form modelAttribute="newsTOList" method="post" action="/news/delete">
    <table class="table">
        <tr>
            <th>
                Title
            </th>
            <th>
                Author
            </th>
            <th>
                Short Description
            </th>
            <th>
                Modification Date
            </th>
            <th>
                Tags
            </th>
            <th>
                Comments
            </th>
            <th></th>
        </tr>

        <c:forEach var="newsTO" items="${newsList}">
            <tr>
                <td>
                    <a href="/news/${newsTO.news.id}">
                        ${newsTO.news.title}
                    </a>
                </td>
                <td>
                    ${newsTO.author.name}
                </td>
                <td>
                    ${newsTO.news.shortText}
                </td>
                <td>
                    ${newsTO.news.modificationDate}
                </td>
                <td>
                    <c:forEach var="tag" items="${newsTO.tagList}">
                        ${tag.name}
                    </c:forEach>
                </td>
                <td>
                    (${newsTO.commentList.size()})
                </td>
                <td>
                    <a href="/news/edit/${newsTO.news.id}">edit</a>
                    <input type="checkbox" name="newsToDelList" value="${newsTO.news.id}">

                </td>

            </tr>
        </c:forEach>

    </table>
    <input type="submit" name="command" value="Delete"/>
    </sf:form>
    <hr />
    <%--footer--%>
</div>