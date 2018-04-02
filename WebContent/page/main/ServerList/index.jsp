<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="web.data.mapper.SysOperateLogMapper"%>
<%@page import="web.control.entity.JobType"%>
<%@page import="web.control.entity.Key"%>
<%@ page language="java" import="java.util.*,web.data.entity.*,web.control.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <title></title>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
    <script type="text/javascript" src="../Js/jquery.js"></script>
    <script type="text/javascript" src="../Js/bootstrap.js"></script>
    <script type="text/javascript" src="../Js/ckform.js"></script>
    <script type="text/javascript" src="../Js/common.js"></script>

 

    <style type="text/css">
        body {
            padding-bottom: 40px;
        }
        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }

		
    </style>
    <%
    	String uName = (String)session.getAttribute(Key.LOGIN_USER_NAME);
    		if(uName == null)
    			return;
    		
   		UserControl uc =  (UserControl)session.getAttribute(Key.LOGIN_USER_CONTROL);
   		SysUser loginUser = uc.getUser();
     %>
</head>
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<form class="form-inline definewidth m20" action="../../../acts/slAct!toSearch" method="get" onsubmit="return checkSubmit()">    
    服务器名称：
    <input type="text" name="searchName" class="abc input-default" placeholder="" value="" >&nbsp;&nbsp;  
  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; 
    <% if(loginUser.getJob() == JobType.JOB_ADMIN){ %>
      <button type="button" class="btn btn-success" id="addnew">新增服务器</button>
    <%} %>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>服务器ID</th>
        <th>服务器名称</th>
        <th>服务器IP</th>
        <th>服务器端口</th>
        <th>服务器DB</th> 
        <th>操作</th>
    </tr>
    </thead>
    	<%
//     		System.out.println("request.getAttribute(userList)  " + request.getAttribute("userList"));
//     		System.out.println("session  = " + JSONObject.toJSONString(session));
			
			ArrayList<SysServerList> slList = null;
			
			if(session.getAttribute(Key.SEARCH_SERVER_LIST) == null)
			{
				if(session.getAttribute(Key.SERVER_LIST) == null)
				{
					%>
						<script type="text/javascript">
							window.location.href="../../../acts/slAct!toServerListPage";
						</script>
					<%
				}
			}
			if(session.getAttribute(Key.SEARCH_SERVER_LIST) != null)
			{
			 	slList = (ArrayList<SysServerList>)session.getAttribute(Key.SEARCH_SERVER_LIST);
				session.removeAttribute(Key.SEARCH_SERVER_LIST);
			 }
			else
				slList = (ArrayList<SysServerList>)session.getAttribute(Key.SERVER_LIST);
			if(slList == null)
				return;
			for(SysServerList sl : slList){		
    	 %>
	     <tr>
            <td><%= sl.getId() %></td>
            <td><%= sl.getName() %></td>
            <td><%= sl.getIp() %></td>
            <td><%= sl.getPort() %></td>
            <td><%= sl.getDbId() %></td>
            <!--<td><%= sl.getStatus() %></td>  -->
            <td>
            	<% if(loginUser.getJob() == JobType.JOB_ADMIN){ %>
                <a href="../../../acts/slAct!toEditSlPage?slId=<%=sl.getId()%>">编辑</a>
                &nbsp;
                <a href="../../../acts/slAct!toDelSl?slId=<%=sl.getId()%>">删除</a>
                &nbsp;
                <%} %>
                <a href="../../../acts/slAct!toConnServer?slId=<%=sl.getId()%>">连接</a>
            </td>
        </tr>	
        <%} %>
        
        
</table>
</body>
</html>
<script>
    $(function () {
		$('#addnew').click(function(){
				window.location.href="add.jsp";
		 });
    });

	function del(id)
	{
		if(confirm("确定要删除吗？"))
		{
			var url = "index.html";
			window.location.href=url;		
		}
	}
	
	function checkSubmit()
	{
		var searchName = document.getElementsByName("searchName")[0].value;
		if(searchName == "")
		{
			alert("请输入要查询的用户名关键字！");
			return false;
		}
		return true;
	}
	
	
</script>