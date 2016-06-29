<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>News List</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>

<div class="container">
    <div>
        <c:url var="news" value="/Controller" context="/${pageContext.request.contextPath}">
            <c:param name="command" value="get-news"/>
        </c:url>
        <a href="${news}">News Management</a>

    </div>
</div>



<div class="criteria">
    <form action="<c:url value="/Controller" context="/${pageContext.request.contextPath}"/>" method="get" >
        <input type="hidden" name="command" value="get-news">
        <input type="hidden" name="curPage" value="${curPage}">

        <h2>Authors</h2>

        <label>
            <select name="author">
                <c:forEach items="${authorList}" var="author">
                    <option value="${author.id}" >
                        ${author.name}
                    </option>
                </c:forEach>
                <option value="" selected>
                    none
                </option>
            </select>
        </label>


        <h2>Tags</h2>
        <label>
            <select name="tags" multiple="multiple" size="5">
                <c:forEach items="${tagList}" var="tag">
                    <option value="${tag.id}" }>
                        ${tag.name}
                    </option>
                </c:forEach>
                <option value="" selected>
                    none
                </option>
            </select>
        </label>

        <div>
            <input type="submit" value="Search" />
        </div>


        <div class="col-md-offset-2 col-md-10">
            <a href="${news}">Reset</a>
        </div>

    </form>
</div>

<div>
    <h2>Index</h2>

    <table class="table">
        <tr>
            <th>
                Title
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
                        ${newsTO.news.title}
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
                        ${newsTO.commentList.size()}
                </td>
                <%--<td>--%>
                    <%--<c:url var="details" value="/Controller" context="/${pageContext.request.contextPath}">--%>
                        <%--<c:param name="command" value="view-news"/>--%>
                        <%--<c:param name="newsId" value="${newsTO.news.id}"/>--%>
                        <%--<c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>--%>
                        <%--<c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>--%>
                        <%--<c:param name="curPage" value="${curPage}"/>--%>
                    <%--</c:url>--%>
                    <%--<a href="${details}">Details</a>--%>

                <%--</td>--%>
            </tr>
        </c:forEach>

    </table>
    <c:forEach var="i" begin="1" end="${pagesAmount}">

        <c:url var="getPage" value="/Controller" context="/${pageContext.request.contextPath}">
            <c:param name="command" value="get-news"/>
            <c:param name="curPage" value="${i}"/>
        </c:url>
        <a href="${getPage}">
            <c:if test="${i == curPage}">
                <b> ${i}</b>
            </c:if>
            <c:if test="${i != curPage}">
                ${i}
            </c:if>
        </a>
        |
    </c:forEach>
    <c:forEach var="i" begin="1" end="${pagesAmountCriteria}">
        <c:url var="getPage" value="/Controller" context="/${pageContext.request.contextPath}">
            <c:param name="command" value="get-news-criteria"/>
            <c:param name="curPage" value="${i}"/>
            <c:param name="author" value="${searchCriteria.getAuthorId()}"/>
            <c:param name="tags" value="${searchCriteria.getTagsId()}"/>
        </c:url>
        <a href="${getPage}">
            <c:if test="${i == curPage}">
                <b> ${i}</b>
            </c:if>
            <c:if test="${i != curPage}">
                ${i}
            </c:if>
        </a>
        |
    </c:forEach>

    <%--<div>--%>

        <%--<c:if test="${curPage != 1}">--%>
            <%--<c:url var="back" value="/Controller" context="/${pageContext.request.contextPath}">--%>
                <%--<c:param name="command" value="get-news"/>--%>
                <%--<c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>--%>
                <%--<c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>--%>
                <%--<c:param name="curPage" value="${curPage - 1}"/>--%>
            <%--</c:url>--%>
            <%--<a href="${back}"><- Back</a>--%>


        <%--</c:if>--%>

        <%--<c:forEach var="i" begin="1" end="${pages}">--%>

            <%--<c:url var="getPage" value="/Controller" context="/${pageContext.request.contextPath}">--%>
                <%--<c:param name="command" value="get-news"/>--%>
                <%--<c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>--%>
                <%--<c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>--%>
                <%--<c:param name="curPage" value="${i}"/>--%>
            <%--</c:url>--%>
            <%--<a href="${getPage}">--%>
                <%--<c:if test="${i == curPage}">--%>
                    <%--<b> ${i}</b>--%>
                <%--</c:if>--%>
                <%--<c:if test="${i != curPage}">--%>
                    <%--${i}--%>
                <%--</c:if>--%>
            <%--</a>--%>
            <%--|--%>
        <%--</c:forEach>--%>

        <%--<c:if test="${pages > curPage}">--%>
            <%--<c:url var="next" value="/Controller" context="/${pageContext.request.contextPath}">--%>
                <%--<c:param name="command" value="get-news"/>--%>
                <%--<c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>--%>
                <%--<c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>--%>
                <%--<c:param name="curPage" value="${curPage + 1}"/>--%>
            <%--</c:url>--%>
            <%--<a href="${next}">Next -></a>--%>
        <%--</c:if>--%>

    <%--</div>--%>

</div>
</body>

<footer>
    <div class="row text-center">
        &copy; - Ivan Dzinhala
    </div>
</footer>


</html>