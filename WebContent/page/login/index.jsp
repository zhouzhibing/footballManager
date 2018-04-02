<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="web.control.entity.Key"%>
<%@page import="web.msg.*"%>
<html lang="utf-8" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>Football Manager</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

		<link rel="shortcut icon" href="page/images/joy.ico" type="image/x-icon" />
        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="page/login/assets/css/reset.css">
        <link rel="stylesheet" href="page/login/assets/css/supersized.css">
        <link rel="stylesheet" href="page/login/assets/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

    </head>

    <body>
        <div class="page-container">
            <h1>您好，请登录！</h1>
            <form action="acts/userAct!doLogin" method="post" id="myform">
                <input type="text" name="username" class="username" placeholder="用户名">
                <input type="password" name="password" class="password" placeholder="密  码">
                <button type="submit">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>
        <div align="center">Collect from  <a href="http://www.baidu.com/s?wd=足球" target="_blank" title="足球">足球</a></div>

        <!-- Javascript -->
        <script src="page/login/assets/js/jquery-1.8.2.min.js"></script>
        <script src="page/login/assets/js/supersized.3.2.7.min.js"></script>
        <script src="page/login/assets/js/supersized-init.js"></script>
        <script src="page/login/assets/js/scripts.js"></script>
  		<script type="text/javascript">
  		var i$ = function(id)
  		{
  			return document.getElementById(id);
  		}
  		var n$ = function(id)
  		{
  			return document.getElementsByName(id);
  		}
  		var errMsg = "<%=session.getAttribute(Message.MSG)%>";
  		if(errMsg != "null")
  		{
  			alert("消息："+errMsg);
  			<%session.removeAttribute(Message.MSG);%>
  		}
  		
    
   	 </script>
    </body>

</html>

