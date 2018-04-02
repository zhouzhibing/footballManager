<%@page import="web.control.UserControl"%>
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
    <% 
    	UserControl uc = (UserControl) session.getAttribute(Key.LOGIN_USER_CONTROL);
    	if(uc == null)
    		return ;
    	
		SysUser user = uc.getUser();
    	session.setAttribute(Key.EDIT_USER, user);
    	%>
</head>
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<form id="editFrom" action="../../../acts/userAct!toEditSaveSelfPage" method="post" class="definewidth m20" onsubmit="return checkSubmit()">
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
            <td><input type="text" name="user.fullName" readonly="readonly"  value="<%= user.getUserName()%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">邮箱</td>
            <td><input type="text" name="user.mail" value="<%= user.getMail()%>"/></td>
        </tr>
        <tr>
            <td class="tableleft">角色</td>
            <td><%= user.getJobStr()%></td>
        </tr>
        <tr>
            <td class="tableleft"></td>
            <td>
                <button type="submit" class="btn btn-primary" type="button" >保存</button> &nbsp;&nbsp;
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
    
    
    function onEnableState()    {    	document.getElementById("user.state").value=<%=StateType.ENABLE%>;    }    
    function onDisableState()       {        document.getElementById("user.state").value=<%=StateType.DISABLE%>;   }
    function onEnableJob()    {    	document.getElementById("user.job").value=<%=JobType.JOB_ADMIN%>;    }    
    function onDisableJob()    {    	document.getElementById("user.job").value=<%=JobType.JOB_NORMAL%>;    }

    
</script>