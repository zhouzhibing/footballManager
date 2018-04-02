<%@page import="web.msg.Message"%>
<%@page import="web.control.entity.Key" %>
<%@ page language="java"
	import="java.util.*,web.data.entity.* , web.control.entity.*"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	
</script>
</head>
<body>
	<div align="center">
		<%=session.getAttribute(Key.UP_CONF_TIME).toString()%>
	</div>

</body>
</html>