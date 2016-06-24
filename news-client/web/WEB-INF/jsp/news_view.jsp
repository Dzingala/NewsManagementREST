<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>News List</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%--<link href="/resources/content/site.css" rel="stylesheet" type="text/css" />--%>
    <%--<link href="/resources/content/bootstrap.min.css" rel="stylesheet" type="text/css" />--%>
    <%--<link href="/resources/content/style.css" rel="stylesheet" type="text/css" />--%>
    <%--<script src="/resources/scripts/modernizr-2.6.2.js"></script>--%>
</head>
<body>
<!--<div class="row text-center">
    <div class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/news">News Management</a>

            </div>
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav">
                </ul>
            </div>
        </div>
    </div>
</div>-->

<div class="row">
    <div class="container body-content">
        <h2>Details</h2>

        <div>
            <h4>News</h4>
            <hr />
            <dl class="dl-horizontal">
                <dt>
                    Title
                </dt>

                <dd>
                    ${newsTO.news.title}
                </dd>

                <br/>

                <dt>
                    Full description
                </dt>

                <dd>
                    ${newsTO.news.fullText}
                </dd>

                <br/>

                <dt>
                    Creation Date
                </dt>

                <dd>
                    ${newsTO.news.creationDate}
                </dd>

                <br/>

                <dt>
                    Modification Date
                </dt>

                <dd>
                    ${newsTO.news.modificationDate}
                </dd>

                <br/>

                <dt>
                    Author
                </dt>

                <dd>
                    ${newsTO.author.name}
                </dd>

                <br/>

                <dt>
                    Tags
                </dt>

                <dd>
                    <c:forEach var="tag" items="${newsTO.tagList}">
                        ${tag.name}
                    </c:forEach>
                </dd>

                <br/>

                <c:if test="${newsTO.commentList.size() > 0}">
                    <dt>
                        Comments
                    </dt>

                    <dd>
                        <c:forEach var="com" items="${newsTO.commentList}">
                            <div>
                                Time: ${com.comCreationDate}  <br/>
                                    ${com.comText}
                                <br/>
                                <hr>
                            </div>
                        </c:forEach>

                    </dd>
                </c:if>


                <dt>
                    Add Comment
                </dt>

                <dd>
                    <form method="post" action="/Controller">
                        <input type="hidden" name="command" value="add-comment"/>
                        <input type="hidden" name="newsId" value="${newsId}"/>
                        <div class="form-group">
                            <div class="col-md-10">
                                <input type="textarea" class="form-control text-box single-line" type="text" maxlength="100" name="comText" style="max-width: none; display: block; width: 100%;"></sf:textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-2 col-md-10">
                                <input type="submit" value="Add" class="btn btn-default" />
                            </div>
                        </div>
                    </form>
                </dd>

            </dl>
        </div>
        <p>

        <form action="/Controller" method="get">
            <input type="hidden" name="command" value="get-news"/>
            <input type="hidden" name="authors" value="${searchCriteria.authorIdSetToQueryString()}"/>
            <input type="hidden" name="tags" value="${searchCriteria.tagIdSetToQueryString()}"/>
            <input type="hidden" name="curPage" value="${curPage}"/>
            <input type="submit" value="Back to List"/>
        </form>

        </p>
        <%--footer--%>
    </div>


    <div class="row text-center">
        &copy; - Ivan Dzinhala
    </div>
</div>

    <script src="/resources/scripts/jquery-1.10.2.min.js"></script>
    <script src="/resources/scripts/bootstrap.min.js"></script>

</body>
</html>