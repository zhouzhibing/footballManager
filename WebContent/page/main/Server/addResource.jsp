<%@page import="web.msg.Message"%>
<%@ page language="java" import="java.util.*,web.data.entity.* , web.control.entity.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">
 	#input {border-style: solid; border-left-style: none;}
 	.b1 {margin-bottom:10px;  width:300px; background: rgb(62, 73, 132); padding: 7px 10px; border-radius: 40px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;}
 	.b2 {cursor:hand; width:100px; background: rgb(222, 73, 102); padding: 7px 10px; border-radius: 40px; border: 1px solid rgb(26, 117, 152); border-image: none; color: rgb(255, 255, 255); font-weight: bold;}
 	.box{ border: 0px solid red;}
</style>
</head>
<body>
<% 	String useId = (String)session.getAttribute(Key.USE_ID);%>

<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px ; height: 0px;"></iframe>
<div style="width: 1500px;" class="box" >
	<div align="left" style="margin-left: 50px; margin-top: 20px;  float: left;" class="box">
		
		<input id="btnUseId" type="button"  style="cursor:hand;width: 300px;" class="b2" value="<%=useId==null?"设置操作角色ID":useId %>" onclick="onSetRoleId()"><br/><br/>
		
		<!-- 添加道具 -->
		<input id="addItemString" type="text" value="" placeholder="添加道具 格式：id=count,id=count" class="b1">
		<input type="button"  class="b2" value="添加道具" onclick="onAdd(<%= Resource.RES_ADD_ITEM%>,$value('addItemString'))"><br/>
		
		<!-- 扣除资源 -->
		<input id="costResId" type="text" placeholder="扣除资源ID" class="b1" style="width: 148px;" >
		<input id="costResCount" type="text" placeholder="扣除资源数量" class="b1"  style="width: 147px;">
		<input type="button"  class="b2" value="扣除资源 " onclick="onAdd(<%= Resource.RES_COST_RESOURCE%>,$value('costResId'),$value('costResCount'))"><br/>
		
		<!-- 扣除道具 -->
		<input id="costItemId" type="text" placeholder="扣除道具ID" class="b1" style="width: 148px;" >
		<input id="costItemCount" type="text" placeholder="扣除道具数量" class="b1"  style="width: 147px;">
		<input type="button"  class="b2" value="扣除道具 " onclick="onAdd(<%= Resource.RES_COST_ITEM%>,$value('costItemId'),$value('costItemCount'))"><br/>
		
		
		<!-- 踢下线 -->
		<input id="kickLine" type="text"  placeholder="踢下线的玩家名称" class="b1">
		<input type="button"  class="b2" value="踢下线" onclick="onAdd(<%= Resource.RES_TI_XIA_XIANG%>,$value('kickLine'))"><br/>
		
		<!-- 封号 -->
		<input id="fhName" type="text"  placeholder="封号玩家名称" class="b1" style="width: 97px;">
		<input id="fhTime" type="text"  placeholder="封号时长" class="b1" style="width: 97px;">
		<input id="fhForbid" type="text"  placeholder="forbid" class="b1" style="width: 97px;">
		<input type="button"  class="b2" value="封号" onclick="onAdd(<%= Resource.RES_FENG_HAO%>,$value('fhName'),$value('fhTime'),$value('fhForbid'))"><br/>
		
		<!-- 解封 -->
		<input id="jfName" type="text"  placeholder="解封的玩家名称" class="b1"  style="width: 148px;">
		<input id="jfGmName" type="text"  placeholder="gm_name" class="b1"  style="width: 147px;">
		<input type="button"  class="b2" value="解封" onclick="onAdd(<%= Resource.RES_JIE_FENG%>,$value('jfName'),$value('jfGmName'))"><br/>
		
		<!-- 禁言 -->
		<input id="notSpeakName" type="text" placeholder="禁言的名称" class="b1" style="width: 97px;" >
		<input id="notSpeakType" type="text" placeholder="forbid" value="1" class="b1"  style="width: 97px;">
		<input id="notSpeakTime" type="text" placeholder="禁言的时长" value="10" class="b1"  style="width: 97px;">
		<input type="button"  class="b2" value="禁言 " onclick="onAdd(<%= Resource.RES_FORBID_SPEAK%>,$value('notSpeakName'),$value('notSpeakType'),$value('notSpeakTime'))"><br/>
	
		<!-- 跑马灯  -->
		<br/>
		<textarea id="pmdContent" placeholder="跑马灯内容" class="b1" style="width: 300px; height: 100px" ></textarea><br/>
		<input id="pmdType" type="text" placeholder="type" class="b1"  style="width: 97px;">
		<input id="pmdPlay" type="text" placeholder="play" class="b1"  style="width: 97px;">
		<input id="pmdTimes" type="text" placeholder="times" class="b1"  style="width: 97px;">
		<input type="button"  class="b2" value="跑马灯 " onclick="onAdd(<%= Resource.RES_PAO_MA_DENG%>,$value('pmdContent'),$value('pmdType'),$value('pmdPlay'),$value('pmdTimes'))"><br/>
		
		<!-- 邮件  -->
		<br/>
		<input id="mailIds" type="text" placeholder="邮件收件ID 格式：id,id,id" class="b1" ><br/>
		<input id="mailTitle" type="text" placeholder="邮件标题" class="b1" ><br/>
		<textarea id="mailContent" placeholder="邮件内容" class="b1" style="width: 300px; height: 100px" ></textarea><br/>
		<input id="maileItems" type="text" placeholder="邮件物品 格式：id=count,id=count" class="b1">
		<input type="button"  class="b2" value="发送邮件 " onclick="onAdd(<%= Resource.RES_MAIL%>,$value('mailIds'),$value('mailTitle'),$value('mailContent'),$value('maileItems'))"><br/>
		
<!-- 		<span style="font-size: 12px;">[描述]</span><br/><br/> -->
	</div>
	<div align="left" style="margin-left: 50px; margin-top: 73px; float: left;" class="box">
		<!-- 设置玩家等级 -->
		<input id="setPlayLevel" type="text" value="" placeholder="指定的等级值" class="b1">
		<input type="button"  class="b2" value="设置等级" onclick="onAdd(<%= Resource.RES_SET_LEVEL%>,$value('setPlayLevel'))"><br/>
		<!-- 设置玩家体力 -->
		<input id="setPlayStamina" type="text" value="" placeholder="指定的体力值" class="b1">
		<input type="button"  class="b2" value="设置体力" onclick="onAdd(<%= Resource.RES_SET_STAMINA%>,$value('setPlayStamina'))"><br/>
		
	</div>
<!-- 	<div id="resourceName" class="box" style=" width:800px ; height: 800px; margin-left: 10px; margin-top:45px; overflow: scroll;;"> -->
<!-- 		<div style="float: left; width: 200px;" class="box"> -->
<!-- 		</div> -->
<!-- 		<div style="float: left;width: 200px ;" class="box"> -->
<!-- 		</div> -->
<!-- 	</div>  -->
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
		}
	}
	
	function onAdd(type)
	{
		var argsUrl = "";
		var offset = 1;
		
		for(var i = offset ,  index = 0 , size = arguments.length; i < size ; i ++ , index ++)
		{
			argsUrl += "&arg"+ index +"="+ arguments[i];
		}
		
// 		alert("argsUrl = " + argsUrl); 
		
// 		if(!arg)
// 			arg=0;
// 		if(useId == "" || useId == null )
// 		{
// 			alert("请输入要操作的角色ID : " + useId);
// 			return;
// 		}
			
// 		if(val == "" || val == null || type == "" || type == null)
// 		{
// 			alert("请输入内容");
// 			return;
// 		}
		
		location.href = "../../../acts/srvAct!doCmd?cmd=<%= Cmd.CMD_ADD_RESOURCE%>&useId="+useId+"&type="+type+argsUrl;
// 		location.href = "../../../acts/srvAct!doCmd?cmd="+cmd+"&type="+type+"&val="+val+"&arg="+arg+"&useId="+useId;
	
	}
</script>
</body>
</html>