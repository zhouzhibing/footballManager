<%@page import="web.action.url.ActionURL"%>
<%@page import="web.control.UserControl"%>
<%@page import="web.msg.Message"%>
<%@page import="web.control.entity.Key"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<script type="text/javascript">
	<%
		String uName = (String)session.getAttribute(Key.LOGIN_USER_NAME);
		session.removeAttribute(Key.LOGIN_USER_ID);
	 	session.removeAttribute(Key.IN_SERVER);
	 	session.removeAttribute(Key.EDIT_USER);
	 	session.removeAttribute(Key.SEARCH_LIST);
	 	session.removeAttribute(Message.MSG);
	 	UserControl userControl = (UserControl)session.getAttribute(Key.LOGIN_USER_CONTROL);
	 	if(userControl != null)
	 	{
	 		userControl.exit();
	 		session.removeAttribute(Key.LOGIN_USER_CONTROL);
	 	}
	 	
	 	session.invalidate();
	 	response.setStatus(302);
	 	response.sendRedirect(request.getContextPath());
	 %>
</script>
<body>
	
</body>
</html>