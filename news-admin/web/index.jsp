<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>SpringMVC Tutorials</title>
</head>

<body id="startPage">
<h3>Admin's functionality testing</h3>
<c:url var="page1" value="/page1"/>
<a href="page1">Page 1 (with the template content)</a><br>
<c:url var="page2" value="/page2"/>
<a href="page2">Page 2 (with a custom context)</a><br>
<c:url var="helloWorld" value="/helloWorld" />
<a href="${helloWorld}">helloworld</a><br>
<c:url var="authors" value="/authors"/>
<a href="${authors}">authors</a><br>
<c:url var="news" value="/news"/>
<a href="${news}">news</a><br>
<c:url var="tags" value="/tags"/>
<a href="${tags}">tags</a>
</body>
</html>
