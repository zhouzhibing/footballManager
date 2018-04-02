<%@ page language="java" import="java.util.*,web.data.entity.* , web.control.entity.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
 	#input {border-style: solid; border-left-style: none;}
 	.b1 {width: 227px;background: rgb(62, 73, 132); padding: 7px 10px; border-radius: 40px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;}
 	.b2 {cursor:hand; background: rgb(222, 73, 102); padding: 7px 10px; border-radius: 40px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;}
 	.box{ border: 0px solid red;}
</style>

</head>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<div align="center">  

<div align="left">
	<form id="myForm" action="../../../acts/srvAct!doCmd?cmd=<%=Cmd.CMD_EXECUTE_CMD %>" method="post" style="width: 230px">
		<table  class="table table-bordered table-hover definewidth m10">
			<tr>
				<td style="width:150px">
					<% 	String useId = (String)session.getAttribute(Key.USE_ID);%>
					<input id="btnUseId" name="useId" type="button"  style="cursor:hand;width: 310px;" class="b2" value="<%=useId==null?"设置操作角色ID":useId %>" onclick="onSetRoleId()">
		      	    <input type="hidden" id="useId" name="useId" value="<%=useId==null?"":useId %>">
				</td>
			</tr>
			<tr>
			     <td style="width:150px">请输入执行CMD</td>
			 </tr>
			 <tr>
			 	<td style="width:150px">
			  		<textarea id="content" name="content" rows="10" cols=100 style="width: 310px;"></textarea>
			 	</td>
			 </tr>
			   <tr>
			      <td>
			          <button class="btn btn-primary" type="submit" onclick="return onSendMail()"  style="width:310px" >发送</button>
			      </td>
			  </tr>
			   <tr>
			      <td>
			         	返回：<%= session.getAttribute(Key.EXECUTE_CMD_RESULT) %> 
			      </td>
			  </tr>
		</table>
	</form> 
</div>

<script type="text/javascript">
	var $ = function(id)	{return document.getElementById(id);	}
	var $value = function(id)	{return document.getElementById(id).value;	}
	var useId = <%=useId%> ; 
	
	function onSetRoleId()
	{
		var id = window.prompt("请输入要操作的角色ID", "");
		if(id != null && id != "")
		{
			useId = id;
			$("btnUseId").value = useId;
			$("useId").value = useId;
		}
	}

	function onSendMail()
	{
		var content = $("content").value;
		var useId = $("useId").value
		
		if(useId == "")
		{
			alert("ID都没有，请问你让我搞谁？");
			return false;
		}
		
		if(content == "")
		{
			alert("内容都没输入进去，已经证明你不爱我了！");
			return false;
		}
		
		return true;
	}
</script>
</div>
</body>
</html>