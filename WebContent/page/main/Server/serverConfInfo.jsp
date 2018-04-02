<%@page import="web.tools.properties.FootballProperties"%>
<%@page import="game.share.constant.Constant"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="web.data.mapper.SysOperateLogMapper"%>
<%@page import="freemarker.cache.StrongCacheStorage"%>
<%@ page language="java" import="java.util.*,web.data.entity.* , web.msg.*, web.control.entity.*,java.lang.reflect.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="../Css/style.css" />
<script src="http://a.tbcdn.cn/s/bui/jquery-1.8.1.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	input{width: 500px;}
</style>
</head>
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<div align="left" style="margin: 50px;">
	<form action="../../../acts/srvAct!changeConfig">
		<table>
		<%
			Class clzss = FootballProperties.class;
			Field [] fieldArray = clzss.getDeclaredFields();
			for(int i = 0 ; i < fieldArray.length ; i ++)
			{
				Field f = fieldArray[i];
				f.setAccessible(true);
		%>
			<tr>
				<td><%=f.getName() %> : </td>
				<td><input type="text" name="<%=f.getName() %>" value="<%=f.get(null) %>"/></td>
			</tr>
		<%
			}
		%>
		<tr>
			<td colspan="2">
				<input type="submit" value="提交修改"/>
			</td>
		</tr>
		</table>
	</form>
</div>
</body>
</html>