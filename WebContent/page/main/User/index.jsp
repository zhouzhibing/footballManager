<%@ page language="java" import="java.util.*,web.data.entity.* , web.control.entity.*" pageEncoding="UTF-8"%>
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
</head>
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<form class="form-inline definewidth m20" action="../../../acts/userAct!toSearch" method="post" onsubmit="return checkSubmit()">    
    用户名称：
    <input type="text" name="searchName" id="username"class="abc input-default" placeholder="" value="">&nbsp;&nbsp;  
    <button type="submit" class="btn btn-primary">查询</button>&nbsp;&nbsp; <button type="button" class="btn btn-success" id="addnew">新增用户</button>
</form>
<table class="table table-bordered table-hover definewidth m10">
    <thead>
    <tr>
        <th>用户id</th>
        <th>用户名称</th>
        <th>真实姓名</th>
        <th>类型</th>
        <th>状态</th>
        <th>创建时间</th>
        <th>最后登录时间</th>
        <th>操作</th>
    </tr>
    </thead>
    	<%
			ArrayList<SysUser> userList = (ArrayList<SysUser>)session.getAttribute(Key.SEARCH_LIST);
			if(userList == null)
				return;
			
			for(SysUser u : userList){		
    	 %>
	     <tr>
	     
            <td><%= u.getId() %></td>
            <td><%= u.getUserName() %></td>
            <td><%= u.getFullName() %></td>
            <td><%= u.getJobStr() %></td>
            <td><%= u.getStateStr() %></td>
            <td><%= u.getCreateTime() %></td>
            <td><%= u.getLastLoginTime() %></td>
            <td>
                <a href="../../../acts/userAct!toEditUserPage?userId=<%=u.getId()%>">编辑</a>&nbsp;
              <%
              	if(!u.getUserName().equals("admin"))
              	{
              %>
               <a href="../../../acts/userAct!toDelUser?userId=<%=u.getId()%>" >删除</a>
           	<%
              	}
           %>
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