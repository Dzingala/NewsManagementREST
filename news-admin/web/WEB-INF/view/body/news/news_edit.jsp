<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 5/25/2016
  Time: 5:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="news-edit">
<sf:form modelAttribute="newsTORecord" method="post" action="/news/update">


    Title
    <sf:input path="news.title" value="${newsTORecord.news.title}"/>


    Brief
    <sf:textarea path="news.shortText" value="${newsTORecord.news.shortText}"/>

    Content
    <sf:textarea path="news.fullText" value="${newsTORecord.news.fullText}"/>

    <sf:select path="authorId">
        <c:forEach items="${authorList}" var="author">
            <sf:option value="${author.id}">${author.name}</sf:option>
        </c:forEach>
    </sf:select>


    <sf:select path="tagIdList" multiple="true">
        <c:forEach items="${tagList}" var="tag">
            <sf:option value="${tag.id}">${tag.name}</sf:option>
        </c:forEach>
    </sf:select>

    <input type="hidden" value="${newsTORecord.news.id}" name="id"/>

    <sf:input path="news.id" type="hidden" value="${newsTORecord.news.id}" />

    <sf:input path="news.creationDate" type="hidden" value="${newsTORecord.news.creationDate}" />

    <input type="submit" name="command" value="Update"/>


</sf:form>
<c:url var="back" value="/news"/>
<a href="${back}">back</a>
</div>
