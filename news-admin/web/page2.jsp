<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
${message}
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<br>overrided body
	</tiles:putAttribute>
</tiles:insertDefinition>