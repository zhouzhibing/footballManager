<%@page import="web.control.entity.JobType"%>
<%@page import="web.control.entity.StateType"%>
<%@ page language="java" import="java.util.*,web.data.entity.* , web.tools.* , web.msg.*" pageEncoding="UTF-8"%>
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
    <% SysDbList sl = (SysDbList)session.getAttribute("editdb"); %>
</head>
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<form id="editFrom" action="../../../acts/dbAct!toEditSaveSl" method="post" class="definewidth m20" >
	    <table class="table table-bordered table-hover definewidth m10">
	       <tr>
	        <td width="10%" class="tableleft">数据库地址</td>
	        <input type="hidden" name="sysDbList.id" value="<%= sl.getId() %>" />
	        <td><input type="text" name="sysDbList.dbUrl" value="<%= sl.getDbUrl()%>"/></td>
	    </tr>
	    <tr>
	        <td class="tableleft">数据库账号</td>
	        <td><input type="text" name="sysDbList.dbUserName" value="<%= sl.getDbUserName()%>"/></td>
	    </tr>
	    <tr>
	        <td class="tableleft">数据库密码</td>
	        <td><input type="text" name="sysDbList.dbUserPwd" value="<%= sl.getDbUserPwd()%>"/></td>
	    </tr>
	    <tr>
	        <td class="tableleft">数据库类</td>
	        <td><input type="text" name="sysDbList.dbClassName" value="<%= sl.getDbClassName()%>"/></td>
	    </tr>
	    <tr>
	        <td class="tableleft">数据库描述</td>
	        <td><input type="text" name="sysDbList.dbDesc" value="<%= sl.getDbDesc()%>"/></td>
	    </tr>
	    <tr>
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
			
				window.location.href="../../../acts/dbAct!toDbListPage";
		 });
    });
    
    
 
</script>