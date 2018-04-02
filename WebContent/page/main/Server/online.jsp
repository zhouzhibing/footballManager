<%@page import="game.share.constant.Constant"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="web.data.mapper.SysOperateLogMapper"%>
<%@page import="freemarker.cache.StrongCacheStorage"%>
<%@ page language="java" import="java.util.*,web.data.entity.* , web.msg.*, web.control.entity.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://a.tbcdn.cn/s/bui/jquery-1.8.1.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
	<div align="center">
		<div style="background-image: url('../Images/online.jpg'); width: 800px; height: 542px;">
			
		</div>
		<br />
		<%
		JSONObject o = (JSONObject)session.getAttribute(Key.ONLINE_NUM);
		if(o == null)
		{
			session.setAttribute(Message.MSG , "游戏服务器返回失败! o : " + o );
			return;
		}
		int sumCount = o.getIntValue("sumCount");
		%>
		<h3> 当前在线总人数为：<%=sumCount%></h3>
		<hr/>
	</div>
	<script type="text/javascript">
$(function(){
	var name="<%=session.getAttribute(Key.CacheServerName)%>";
	$("#serverName",parent.document).html(name);
});
	</script>
</body>

</html>