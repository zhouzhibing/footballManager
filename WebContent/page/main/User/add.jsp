<%@ page language="java" import="java.util.*,web.data.entity.*" pageEncoding="UTF-8"%>
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
<form action="../../../acts/userAct!toAddUser" method="post" class="definewidth m20" onsubmit="return checkSubmit()">
<table class="table table-bordered table-hover definewidth m10">
    <tr>
        <td width="10%" class="tableleft">登录名</td>
        <td><input type="text" name="user.userName"/></td>
    </tr>
    <tr>
        <td class="tableleft">密码</td>
        <td><input type="password" name="user.userPwd"/></td>
    </tr>
    <tr>
        <td class="tableleft">真实姓名</td>
        <td><input type="text" name="user.fullName"/></td>
    </tr>
    <tr>
        <td class="tableleft">邮箱</td>
        <td><input type="text" name="user.mail"/></td>
    </tr>
    <tr>
        <td class="tableleft">状态</td>
        <td>
           <input type="radio" name="user.state" value="1" checked/> 启&nbsp;&nbsp;&nbsp;用 
           <input type="radio" name="user.state" value="0"/> 禁用
        </td>
    </tr>
    <tr>
        <td class="tableleft">角色</td>
        <td>
        <input type="radio" name="user.job" value="1" /> 管理员
        <input type="radio" name="user.job" value="0" checked/> 普通
        </td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" >保存</button> &nbsp;&nbsp;
            <button type="button" class="btn btn-success" name="backid" id="backid">返回列表</button>
        </td>
    </tr>
</table>
</form>
</body>
</html>
<script>
    $(function () {       
		$('#backid').click(function(){
				window.location.href="../../../acts/userAct!toUserList";
		 });

    });
    
    function checkSubmit()
    {
    	var userName = document.getElementsByName("user.userName")[0].value;
    	var userPwd = document.getElementsByName("user.userPwd")[0].value;
    	var userFullName = document.getElementsByName("user.fullName")[0].value;
//     	alert(userName.length);
    	if(userName.length < 4 )
    	{
    		alert("用户名请输入长度大于4位");
    		return false;
    	}
    	if(userName == "" || userPwd == "" || userFullName == "")
    	{
    		alert("咱们能认真点吗？");
    		return false;
    	}
    	return true;
    	
    }
</script>