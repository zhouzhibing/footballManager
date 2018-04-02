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
<form class="form-inline definewidth m20" action="../../../acts/slAct!toSearch" method="get" >    
      <button type="button" class="btn btn-success" id="addnew">新增数据库</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>数据库ID</th>
        <th>数据库url</th>
        <th>数据库账号</th>
        <th>数据库密码</th>
        <th>数据库类</th> 
        <th>描述</th> 
        <th>操作</th>
    </tr>
    </thead>
    	<%
//     		System.out.println("request.getAttribute(userList)  " + request.getAttribute("userList"));
//     		System.out.println("session.getAttribute(userList)  = " + session.getAttribute("userList"));
			
			ArrayList<SysDbList> slList = null;
			
			if(session.getAttribute(Key.DB_SERVER_LIST) != null)
			{
			 	slList = (ArrayList<SysDbList>)session.getAttribute(Key.DB_SERVER_LIST);
				session.removeAttribute(Key.DB_SERVER_LIST);
			 }
			else
				slList = (ArrayList<SysDbList>)session.getAttribute(Key.DB_SERVER_LIST);
			if(slList == null)
				return;
			for(SysDbList sl : slList){		
    	 %>
	     <tr>
            <td><%= sl.getId() %></td>
             <td><%= sl.getDbUrl() %></td>
            <td><%= sl.getDbUserName() %></td>
            <td><%= sl.getDbUserPwd() %></td>
            <td><%= sl.getDbClassName() %></td>
            <td><%= sl.getDbDesc() %></td>
            <td>
              <a href="../../../acts/dbAct!toEditSlPage?DbId=<%=sl.getId()%>">编辑</a>
              &nbsp;
              <a href="../../../acts/dbAct!toDelSl?DbId=<%=sl.getId()%>">删除</a>
              &nbsp;
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
	
	
	
	
</script>