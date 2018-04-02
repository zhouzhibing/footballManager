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
    <script type="text/javascript" src="../Js/jquery.sorted.js"></script>
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
<form action="../../../acts/dbAct!toAddSl" method="post" class="definewidth m20" >
<table class="table table-bordered table-hover definewidth m10">
 <tr>
        <td width="10%" class="tableleft">服务器地址</td>
        <td><input type="text" name="sysDbList.dbUrl"/></td>
    </tr>
    <tr>
        <td width="10%" class="tableleft">服务器账号</td>
        <td><input type="text" name="sysDbList.dbUserName"/></td>
    </tr>
    <tr>
        <td class="tableleft">服务器密码</td>
        <td><input type="text" name="sysDbList.dbUserPwd"/></td>
    </tr>
    <tr>
        <td class="tableleft">服务器类</td>
        <td><input type="text" name="sysDbList.dbClassName"/></td>
    </tr>
    <tr>
        <td class="tableleft">服务器描述</td>
        <td><input type="text" name="sysDbList.dbDesc"/></td>
    </tr>
    <tr>
        <td class="tableleft"></td>
        <td>
            <button type="submit" class="btn btn-primary" >添加</button> &nbsp;&nbsp;
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
				window.location.href="../../../acts/dbAct!toDbListPage";
		 });

    });
    

</script>