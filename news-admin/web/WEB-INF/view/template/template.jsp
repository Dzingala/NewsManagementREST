<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>

<html>
<head>
    <link rel="stylesheet" href="css/style.css" type="text/css" />
    <title><tiles:insertAttribute name="title"/></title>
</head>

<body>
	<div class="header">
		<tiles:insertAttribute name="header" />
	</div>
	<div class="menu">
		<tiles:insertAttribute name="menu" />
	</div>
	<div class="body">
		<tiles:insertAttribute name="content" />
	</div>
	<div class="footer">
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>
