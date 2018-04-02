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
	<link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
</head>

<body>
	<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
	<div align="left" style="margin: 20px;">
		请输入指定的日期进行统计计算，请输入日期，格式：年月日，如：20120112
		<form action="../../../acts/statAct!appintDateStatistics" method="get">
			<input id="date" type="text" name="date"><br/>
			<input type="submit" value="执行统计">
		</form>
	</div>
</body>

<script type="text/javascript">
	var myDate = new Date();
	var day = myDate.getDate();
		if(day < 10)day = "0" + day;
	var month = myDate.getMonth() + 1;
	if(month < 10)
		month = "0" + month;
	var date = myDate.getFullYear() + ""+  month + day;
	
	document.getElementById("date").value = date;
</script>
</html>