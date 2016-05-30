<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<div class="container body-content">
    <h2>News</h2>
    <%--<p>--%>
    <%--<c:url var="authorCreation" value="/authors/create"/>--%>
    <%--<a href="${authorCreation}">Create New</a>--%>
    <%--</p>--%>
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

        <c:forEach var="newsTO" items="${newsTOList}">
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
                </td>
                <%--<td>--%>
                    <%--<c:url var="edit" value="/news/edit/${newsTO.news.id}">--%>
                        <%--<c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>--%>
                        <%--<c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>--%>
                        <%--<c:param name="curPage" value="${curPage}"/>--%>
                    <%--</c:url>--%>
                    <%--<security:authorize access="hasRole('ROLE_ADMIN')">--%>
                        <%--<a href="${edit}">Edit</a> |--%>
                    <%--</security:authorize>--%>

                    <%--<c:url var="details" value="/news/details/${newsTO.news.id}">--%>
                        <%--<c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>--%>
                        <%--<c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>--%>
                        <%--<c:param name="curPage" value="${curPage}"/>--%>
                    <%--</c:url>--%>
                    <%--<a href="${details}">Details</a>--%>

                    <%--<c:url var="delete" value="/news/delete/${newsTO.news.id}">--%>
                        <%--<c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>--%>
                        <%--<c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>--%>
                        <%--<c:param name="curPage" value="${curPage}"/>--%>
                    <%--</c:url>--%>
                    <%--<security:authorize access="hasRole('ROLE_ADMIN')">--%>
                        <%--| <a href="${delete}">Delete</a>--%>
                    <%--</security:authorize>--%>
                <%--</td>--%>
            </tr>
        </c:forEach>

    </table>
    <hr />
    <%--footer--%>
</div>