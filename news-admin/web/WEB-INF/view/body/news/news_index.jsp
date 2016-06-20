<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div class="container body-content">
    <h2>News</h2>
    <sf:form modelAttribute="searchCriteria" method="get" action="/news">

        <sf:select path="tagsId" multiple="true">
            <c:forEach items="${tagList}" var="tag">
                <sf:option value="${tag.id}">${tag.name}</sf:option>
            </c:forEach>
        </sf:select>

        <sf:select path="authorId">
            <sf:option disabled="true" selected="true" value="" label="--- Select Author ---"/>
            <c:forEach items="${authorList}" var="author">
                <sf:option value="${author.id}">${author.name}</sf:option>
            </c:forEach>
        </sf:select>

        <input type="submit" name="command" value="Search"/>

    </sf:form>


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
                    <a href="/news/view/${newsTO.news.id}">
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
    <hr/>
    <div>
        <c:forEach var="i" begin="1" end="${pagesAmount}">
            <a href="/news/${i}">
                    ${i}
            </a>
        </c:forEach>
    </div>

    <c:url value="/logout" var="logoutUrl" />
    <form action="${logoutUrl}" method="post" id="logoutForm">
        <input type="hidden" name="${_csrf.parameterName}"
               value="${_csrf.token}" />
        <div>
            <input name="submit" type="submit" value="Exit"/>
        </div>
    </form>
</div>