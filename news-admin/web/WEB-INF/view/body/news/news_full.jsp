<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 5/17/2016
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="news-full">
<p>
    ${newsTO.news.title}
</p>

<p>
    (by ${newsTO.author.name})
</p>
<p>
    (from ${newsTO.news.creationDate})
</p>

<p>
    ${newsTO.news.fullText}
</p>

<p>
<c:forEach var="comment" items="${newsTO.commentList}">
    <span>${comment.creationDate}</span>

    <p>${comment.text}</p>

    <sf:form modelAttribute="comment" method="post" action="/comment/delete">
        <input type="hidden" value="${comment.newsId}" name="newsId" />
        <sf:input path="id" type="hidden" value="${comment.id}"  />
        <input type="submit" name="command" value="delete"/>
    </sf:form>

</c:forEach>
</p>

<p>
    <sf:form modelAttribute="comment" method="post" action="/comment/create">
        <sf:textarea path="text"  value=""/>
        <input type="hidden" value="${newsTO.news.id}" name="newsId" />
        <input type="submit" name="command" value="add"/>
    </sf:form>
</p>
</div>