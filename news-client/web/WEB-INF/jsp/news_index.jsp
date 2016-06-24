<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>News List</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="/resources/content/site.css" rel="stylesheet" type="text/css" />
    <link href="/resources/content/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="/resources/content/style.css" rel="stylesheet" type="text/css" />
    <script src="/resources/scripts/modernizr-2.6.2.js"></script>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <c:url var="news" value="/Controller" context="/${pageContext.request.contextPath}">
                <c:param name="command" value="get-news"/>
            </c:url>
            <a class="navbar-brand" href="${news}">News Management</a>

        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
            </ul>
        </div>
    </div>
</div>



<!--<div class="criteria">
    <form action="<c:url value="/Controller" context="/${pageContext.request.contextPath}"/>" method="get" class="col-sm-4" style="position:relative; margin-left: 0;">
        <input type="hidden" name="command" value="get-news">
        <input type="hidden" name="curPage" value="${curPage}">

        <h2>Authors</h2>

        <c:forEach var="auth" items="${authorList}">
            <c:if test="${searchCriteria.getAuthorIdSet().contains(auth.id)}" >
                <input type="checkbox" name="authId" value="${auth.id}" checked/> ${auth.name} <br>
            </c:if>
            <c:if test="${!searchCriteria.getAuthorIdSet().contains(auth.id)}" >
                <input type="checkbox" name="authId" value="${auth.id}"/> ${auth.name} <br>
            </c:if>
        </c:forEach>


        <h2>Tags</h2>

        <c:forEach var="tag" items="${tagList}">
            <c:if test="${searchCriteria.getTagIdSet().contains(tag.id)}" >
                <input type="checkbox" name="tagId" value="${tag.id}" checked/> ${tag.name} <br>
            </c:if>
            <c:if test="${!searchCriteria.getTagIdSet().contains(tag.id)}" >
                <input type="checkbox" name="tagId" value="${tag.id}"/> ${tag.name} <br>
            </c:if>
        </c:forEach>



        <div class="col-md-offset-2 col-md-10">
            <input type="submit" value="Search" class="btn btn-default" />
        </div>


        <div class="col-md-offset-2 col-md-10">
            <a href="${news}">Reset</a>
        </div>

    </form>
</div>-->

<div class="container body-content">
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

        <c:forEach var="newsTO" items="${newsSet}">
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
                <td>
                    <c:url var="details" value="/Controller" context="/${pageContext.request.contextPath}">
                        <c:param name="command" value="view-news"/>
                        <c:param name="newsId" value="${newsTO.news.id}"/>
                        <c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>
                        <c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>
                        <c:param name="curPage" value="${curPage}"/>
                    </c:url>
                    <a href="${details}">Details</a>

                </td>
            </tr>
        </c:forEach>

    </table>


    <!--<div>

        <c:if test="${curPage != 1}">
            <c:url var="back" value="/Controller" context="/${pageContext.request.contextPath}">
                <c:param name="command" value="get-news"/>
                <c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>
                <c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>
                <c:param name="curPage" value="${curPage - 1}"/>
            </c:url>
            <a href="${back}"><- Back</a>


        </c:if>

        <c:forEach var="i" begin="1" end="${pages}">

            <c:url var="getPage" value="/Controller" context="/${pageContext.request.contextPath}">
                <c:param name="command" value="get-news"/>
                <c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>
                <c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>
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

        <c:if test="${pages > curPage}">
            <c:url var="next" value="/Controller" context="/${pageContext.request.contextPath}">
                <c:param name="command" value="get-news"/>
                <c:param name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>
                <c:param name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>
                <c:param name="curPage" value="${curPage + 1}"/>
            </c:url>
            <a href="${next}">Next -></a>
        </c:if>

    </div>-->

</div>
</body>

<footer>
    <div class="row text-center">
        &copy; - Ivan Dzinhala
    </div>
</footer>

<script src="/resources/scripts/jquery-1.10.2.min.js"></script>
<script src="/resources/scripts/bootstrap.min.js"></script>

</body>
</html>