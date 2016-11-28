<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad
  Date: 5/17/2016
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="news-create">
<sf:form modelAttribute="newsTORecord" method="post" action="/news/create">


    Title
    <sf:input path="news.title" value=""/>

    Brief
    <sf:textarea path="news.shortText" value=""/>

    Content
    <sf:textarea path="news.fullText" value=""/>

    <sf:select path="authorId">
        <sf:option disabled="true" selected="true" value="" label="--- Select Author ---"/>
        <c:forEach items="${authors}" var="author">
            <sf:option value="${author.id}">${author.name}</sf:option>
        </c:forEach>
    </sf:select>


    <sf:select path="tagIdList" multiple="true">
        <c:forEach items="${tags}" var="tag">
            <sf:option value="${tag.id}">${tag.name}</sf:option>
        </c:forEach>
    </sf:select>


    <input type="submit" name="command" value="Save"/>


</sf:form>
</div>