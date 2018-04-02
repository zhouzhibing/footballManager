<%@page import="web.control.entity.Key"%>
<%@page import="web.msg.Message"%>
<%@page import="web.control.entity.JobType"%>
<%@page import="web.control.entity.StateType"%>
<%@ page language="java" import="java.util.*,web.data.entity.* , web.tools.*" pageEncoding="UTF-8"%>
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
    <% SysUser user = (SysUser)session.getAttribute(Key.EDIT_USER); %>
</head>
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<form id="editFrom" action="../../../acts/userAct!toEditSaveUserPage" method="post" class="definewidth m20" onsubmit="return checkSubmit()">
<input type="hidden" name="id" value="{$user.id}" />
    <table class="table table-bordered table-hover definewidth m10">
        <tr>
            <td width="10%" class="tableleft">登录名</td>
            <input type="hidden" name="user.id" value="<%= user.getId() %>" />
            <input type="hidden" id="user.state" name="user.state" value="<%= user.getState() %>" />
            <input type="hidden" id="user.job" name="user.job" value="<%= user.getJob() %>" />
            <td><input type="text" name="user.userName" readonly="readonly" value="<%= user.getUserName()%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">密码</td>
            <td><input id="userPwd" type="password" name="user.userPwd" value="<%= Key.DEFAULT_PWD %>"/></td>
        </tr>
        <tr>
            <td class="tableleft">真实姓名</td>
            <td><input type="text" name="user.fullName" value="<%= user.getUserName()%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="text" name="user.mail" value="<%= user.getMail()%>"/></td>
        </tr>
        <% if(!user.getUserName().equals(Key.ADMIN)){ %>
        <tr>
            <td class="tableleft">状态</td>
            <td>
            	<% if(user.getState() == StateType.ENABLE) {%>
                	<input type="radio" name="status" value="0" checked="checked" onclick="onEnableState()"/> 启&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;
                 	<input type="radio" name="status" value="1" onclick="onDisableState()"/> 禁用
                <%}else{ %>
	                <input id="enable" type="radio" name="status" value="1" onclick="onEnableState()"/> 启&nbsp;&nbsp;&nbsp;用&nbsp;&nbsp;
	                <input type="radio" name="status" value="1"  checked="checked" onclick="onDisableState()"/> 禁用
                <%} %>
            </td>
        </tr>
        <tr>
            <td class="tableleft">权限</td>
            <td>
            	<% if(user.getJob() == JobType.JOB_ADMIN) {%>
                	<input type="radio" name="job" value="0" checked="checked" onclick="onEnableJob()"/> <%=JobType.JOB_ADMIN_STR %>&nbsp;&nbsp;
                 	<input type="radio" name="job" value="1" onclick="onDisableJob()"/> <%=JobType.JOB_NORMAL_STR%>
                <%}else{ %>
	                <input id="enable" type="radio" name="job" value="1" onclick="onEnableJob()"/> <%=JobType.JOB_ADMIN_STR %>&nbsp;&nbsp;
	                <input type="radio" name="job" value="1"  checked="checked" onclick="onDisableJob()"/> <%=JobType.JOB_NORMAL_STR%>
                <%} %>
            </td>
        </tr>
        <%} %>
        <tr>
            <td class="tableleft">角色</td>
            <td><%= user.getJobStr()%></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button" >保存</button> &nbsp;&nbsp;
                <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
<script type="text/javascript">
    $(function () {       
		$('#backid').click(function(){
			
				window.location.href="../../../acts/userAct!toUserList";
		 });
    });
    
    
    function checkSubmit()
	{	
		var loginName ="<%= (String)session.getAttribute(Key.LOGIN_USER_NAME)%>"
        var editName = "<%= ((SysUser)session.getAttribute(Key.EDIT_USER)).getUserName()%>"
        var state = document.getElementById("user.state").value;
//         alert("loginName = " + loginName + " editName = " + editName + " state = " + state);
        if(loginName == editName && state == <%= StateType.DISABLE%>)
        {
        	if(alert("认真点好吗，不能自己给自己禁用掉。"))
        		return true;
        	else
        		return false;
        }
		return true;
	}
    
    function onEnableState()    {    	document.getElementById("user.state").value=<%=StateType.ENABLE%>;    }    
    function onDisableState()       {        document.getElementById("user.state").value=<%=StateType.DISABLE%>;   }
    function onEnableJob()    {    	document.getElementById("user.job").value=<%=JobType.JOB_ADMIN%>;    }    
    function onDisableJob()    {    	document.getElementById("user.job").value=<%=JobType.JOB_NORMAL%>;    }

    
</script>