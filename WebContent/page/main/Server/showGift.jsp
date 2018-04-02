<%@page import="game.tools.utils.StringTools"%>
<%@page import="game.tools.utils.DateTools"%>
<%@page import="game.tools.page.Page"%>
<%@page import="game.share.constant.Constant"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="web.data.mapper.SysOperateLogMapper"%>
<%@page import="freemarker.cache.StrongCacheStorage"%>
<%@ page language="java" import="java.util.*,web.data.entity.* , web.msg.*, web.control.entity.*" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://a.tbcdn.cn/s/bui/jquery-1.8.1.min.js"></script>
<title>Insert title here</title><link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
<link rel="stylesheet" type="text/css" href="../Css/style.css" />
<style type="text/css">
	body {font-size:12px;}
 	input[type="text"]{width: 245px;}
</style>
</head>
<body>
<div align="left" style=" margin-top: 20px;">
	<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
	<div id="oper" style="display: none;">
		<div align="left" style="float: left; margin-right: 30px; margin-left: 40px;">
			<form action="../../../acts/giftAct!createGift" method="get" onsubmit="toGenerateGift()" >
				<input name="plaform" type="text" placeholder="礼包的平台"  /><br/>
				<input name="channel" type="text" placeholder="礼包的渠道"/><br/>
				<input name="item" type="text" placeholder="礼包的奖励物品ID，格式：ID=COUNT"/><br/>
				<input id="startTime" name="startTime" readonly="readonly" type="text" placeholder="礼包的开始时间"/><br/>
				<input id="endTime" name="endTime" readonly="readonly" type="text" placeholder="礼包的结束时间"/><br/>
				<select name="giftCount" style="width: 150px;" >
					<option value="10">10</option>
					<option value="500">500</option> 
					<option value="1000">1000</option> 
					<option value="3000">3000</option>
					<option value="5000">5000</option>
					<option value="8000">8000</option>
					<option value="10000">10000</option> 
				</select>生成的数量 <br/>
				<input id="btnCreate" type="submit" value="创建礼包" style="width: 250px;"/><br/>
			</form>
		</div>
		<div align="left" style="float: left;">
			<form action="../../../acts/giftAct!searchGift" method="get">
				<input name="giftNo" type="text" placeholder="查询的礼包码"  /><br/>
				<input id="btnSearch" type="submit" value="查询礼包" style="width: 250px;"/><br/>
			</form>
			<form action="../../../acts/giftAct!searchGift" method="get" style="margin-top: 15px;">
				<%
					String useString = (String)session.getAttribute("use");
					
					if(useString != null)
					{
						Boolean use = Boolean.parseBoolean(useString);
						if(use)
						{
							%>
								<input name="use" type="radio" value="true" checked="checked" style="float: left;"/><div style="float: left; width: 130px;">已使用</div>
								<input name="use" type="radio" value="false" style="float: left;"/>未使用<br/>
							<%
						}
						else
						{
							%>
								<input name="use" type="radio" value="true" style="float: left;"/><div style="float: left; width: 130px;">已使用</div>
								<input name="use" type="radio" value="false" checked="checked" style="float: left;"/>未使用<br/>
							<%	
						}
					}
					else
					{
						%>
							<input name="use" type="radio" value="true" style="float: left;"/><div style="float: left; width: 130px;">已使用</div>
							<input name="use" type="radio" value="false" style="float: left;"/>未使用<br/>
						<%
					}
				%>
				<input name="clear" type="hidden" value="true"/>
				<input name="next" type="hidden" value="true"/>
				<input id="btnUseSearch" type="submit" value="查询使用情况" style="width: 250px;"/><br/>
			</form>
			<form action="../../../acts/giftAct!searchGift" method="get" style="margin-top: 15px;">
				<%
					String startTimeString = (String)session.getAttribute("startTime");
					String endTimeString = (String)session.getAttribute("endTime");
				%>
				<input id="startTime1" name="startTime" readonly="readonly" type="text" placeholder="礼包的开始时间" value="<%= startTimeString==null?"":startTimeString %>"/><br/>
				<input id="endTime1" name="endTime" readonly="readonly" type="text" placeholder="礼包的结束时间" value="<%= endTimeString==null?"":endTimeString %>"/><br/>
				<input name="next" type="hidden" value="true"/>
				<input name="clear" type="hidden" value="true"/>
				<input id="btnUseSearch" type="submit" value="时间范围查询" style="width: 250px;"/><br/>
			</form>
		</div>
	</div>&emsp;
	<a id="operShow" href="javascript:operHandler()" style="margin-left: 25px;" >显示</a>
	<div style="clear: both;">
		<table class="table table-bordered table-hover definewidth m10">
			<tr>
				<th>ID</th>
				<th>礼包码</th>
				<th>礼包奖励</th>
				<th>生效时间</th>
				<th>失效时间</th>
				<th>是否使用</th>
			</tr>
		<%
			String useStrings = null;
		
			ArrayList<Gift> giftList = (ArrayList<Gift>)session.getAttribute(Page.PAGE_NO_RESULT);
			for(Gift gift : giftList)
			{
				if(StringTools.empty(gift.getUseTime()))
					useStrings = "未使用";
				else
					useStrings = gift.getUseTime();
				%>
					<tr>
						<td><%=gift.getId() %></td>
						<td><%=gift.getGiftNo() %></td>
						<td><%=gift.getItem() %></td>
						<td><%=DateTools.getCurrentTimeString(gift.getStartTime()) %></td>
						<td><%=DateTools.getCurrentTimeString(gift.getEndTime()) %></td>
						<td><%=useStrings %></td>
					</tr>
				<%
			}
		%>
		<tr>
	      	<td colspan="6" align="center">
	      		<%
	      			if(useString != null)
	      			{
	      				%>
	      					<a href="../../../acts/giftAct!searchGift?use=<%= useString%>&next=false" >上一页</a> &emsp;
							<input id = "jumpNo" type="text" value="<%= session.getAttribute(Page.PAGE_NO) %>" style="width:30px; text-align: center" />
							<a href="javascript:jumpPageNo(1)" >跳</a>&emsp;
							<a href="../../../acts/giftAct!searchGift?use=<%= useString%>&next=true" >下一页</a> &emsp;
	      				<%
	      			}
	      			else if(startTimeString != null && endTimeString != null)
	      			{
	      				%>
	      					<a href="../../../acts/giftAct!searchGift?startTime=<%= startTimeString%>&endTime=<%= endTimeString%>&next=false" >上一页</a> &emsp;
							<input id = "jumpNo" type="text" value="<%= session.getAttribute(Page.PAGE_NO) %>" style="width:30px; text-align: center" />
							<a href="javascript:jumpPageNo(2)" >跳</a>&emsp;
							<a href="../../../acts/giftAct!searchGift?startTime=<%= startTimeString%>&endTime=<%= endTimeString%>&next=true" >下一页</a> &emsp;
	      				<%
	      			}
	      			else
	      			{
	      				%>
	      					<a href="../../../acts/giftAct!showGiftList?next=false" >上一页</a> &emsp;
							<input id = "jumpNo" type="text" value="<%= session.getAttribute(Page.PAGE_NO) %>" style="width:30px; text-align: center" />
							<a href="javascript:jumpPageNo(3)" >跳</a>&emsp;
							<a href="../../../acts/giftAct!showGiftList?next=true" >下一页</a> &emsp;
	      				<%
	      			}
	      		%>
	      	</td>
	      </tr>
		</table>
	</div>
</div>	
</body>
<script src="../Js/layDate-v5.0.9/laydate/laydate.js"></script> <!-- 改成你的路径 -->
<script type="text/javascript">
	
	//执行一个laydate实例
	laydate.render({elem: '#startTime',type: 'datetime'});
	laydate.render({elem: '#startTime1',type: 'datetime'});
	
	laydate.render({elem: '#endTime',type: 'datetime'});
	laydate.render({elem: '#endTime1',type: 'datetime'});
	
	function operHandler()
	{
		var oper = document.getElementById('oper');
		var operShow = document.getElementById('operShow');
		var show = oper.style.display;
		
		if(show == "none")
		{
			oper.style.display = "";
			operShow.innerHTML = "隐藏"
		}
		else
		{
			oper.style.display = "none";
			operShow.innerHTML = "显示"			
		}
	}
	
	function jumpPageNo(type)
	{
		var jumpNo = document.getElementById('jumpNo').value
		
		if(type == 1)
		{
			window.location.href='../../../acts/giftAct!searchGift?use=<%= useString %>&next=false&jumpNo='+jumpNo
		}
		else if(type == 2)
		{
			window.location.href='../../../acts/giftAct!searchGift?startTime=<%= startTimeString%>&endTime=<%= endTimeString%>&next=false&jumpNo='+jumpNo
		}
		else
		{
			window.location.href='../../../acts/giftAct!showGiftList?jumpNo='+jumpNo	
		}
	}
	
	function toGenerateGift ()
	{
		var btnCreate = document.getElementById("btnCreate");
		
		if(btnCreate != null)
			btnCreate.disabled="disabled";
	}
	
</script>
</html>