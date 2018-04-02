<%@page import="game.tools.utils.StringTools"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="web.data.football.entity.JournalPoint"%>
<%@page import="web.data.mapper.SysOperateLogMapper"%>
<%@page import="web.control.entity.JobType"%>
<%@page import="web.control.entity.Key"%>
<%@page import="web.tools.properties.*"%>
<%@ page language="java" import="java.util.*,web.data.entity.*,web.control.*,web.control.entity.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<form class="form-inline definewidth m20" action="../../../acts/srvAct!journalpointList?pageNo=1" method="get" onsubmit="return checkSubmit()">
	
	开始时间：<input id="startDate" name="startDate" type="text" class="abc input-default" placeholder="" value="" >
<!--           结束时间：<input id="endDate" name="endDate" type="text" class="abc input-default" placeholder="" value="" >   -->
    <button type="submit" class="btn btn-primary">查询</button>
    <input type="button" class="btn btn-success" value="今天" onclick="toDay()" />
</form>
<table class="table table-bordered table-hover definewidth m10">
	<tr>
      	<td id="stat" colspan="17" align="center"></td>
    </tr>
    <thead>
    <tr>
    	<th>序号</th>
        <th>时间</th>
        <th>用户名</th>
        <th>用户ID</th>
        <th>用户等级</th>
        <th>类型</th> 
        <th>操作</th>
        <th>参数1</th>
        <th>参数2</th>
        <th>参数3</th>
        <th>参数4</th>
        <th>参数5</th>
        <th>参数6</th>
        <th>参数7</th>
        <th>参数8</th>
        <th>参数9</th>
        <th>参数10</th>
    </tr>
    </thead>
    	<%
			ArrayList<JournalPoint> jpList = (ArrayList<JournalPoint>)session.getAttribute(Key.JOURNALPOINT_LIST);
			
			if(jpList == null)
			{
				out.write("未查出结果！");
				return ;
			}
			
			HashMap<String , Integer> statmap = (HashMap<String , Integer>)session.getAttribute("statmap");
			if(statmap == null)
			{
				statmap = new HashMap<String , Integer>();
				session.setAttribute("statmap", statmap);
			}
			
			
			int curPageNo = (int)session.getAttribute(Key.JOURNALPOINT_LIST_PAGE_NO);
			int maxPageNo = (int)session.getAttribute(Key.JOURNALPOINT_LIST_MAX_PAGE_NO);
			
			for(int i = 0 , size = jpList.size(); i < size ; i ++)
			{
				JournalPoint jp = jpList.get(i);
				JournalPointDesc desc = UserControl.getJournalPointDesc(session,jp.getAction());
				
				if(curPageNo >= maxPageNo )
				{
					if(desc != null)
					{
						Integer count = statmap.get(desc.getActionDesc());
						if(count == null)
							count = 1;
						else
							count ++;
						
						statmap.put(desc.getActionDesc(), count);
					}
				}
    	 %>
		     <tr>
		      	<td><%= ((curPageNo * FootballProperties.getPAGE_SIZE())+ 1) + (i) %></td>
	            <td><%= jp.getTime() %></td>
	            <td><%= jp.getUsername() %></td>
	            <td><%= jp.getUserid() %></td>
	            <td><%= jp.getLevel() %></td>
	            <td><%= jp.getType() %></td>
	            <% if(desc != null){ %>
	            	<td title="<%=desc.getActionDesc() %>">(<%=jp.getAction() %>)<%=desc.getActionDesc() %></td>	
	            <%}else{ %>
		            <td title="无">(<%=jp.getAction() %>)无</td>
	            <%} %>
	            <td><%= jp.getPara1() %></td>
	            <td><%= jp.getPara2() %></td>
	            <td><%= jp.getPara3() %></td>
	            <td><%= jp.getPara4() %></td>
	            <td><%= jp.getPara5() %></td>
	            <td></td>
	            <td></td>
	            <td></td>
	            <td></td>
	            <td></td>
	        </tr>	
        <%} %>
      <tr>
      	<td colspan="17" align="center">
      		<%
      			Integer pageNo = (Integer)session.getAttribute(Key.JOURNALPOINT_LIST_PAGE_NO);
      		%>
      		<a href="../../../acts/srvAct!journalpointList?pageNo=<%=pageNo - 1 %>" >上一页</a>
      		[<%= curPageNo + 1 %>]
      		<a href="../../../acts/srvAct!journalpointList?pageNo=<%=pageNo + 1%>" >下一页</a>
      	</td>
      </tr>
</table>
</body>
<script>

	var $= function(id)	{ return document.getElementById(id);	}
	
	function initDateTime()
	{
		var startDate = "<%= session.getAttribute(Key.SEARCH_START_DATE)%>"
		if(startDate != "null")
			$("startDate").value  = startDate;
		else
			toDay();
	}
	
	//今天 
	function toDay()
	{
		var myDate = new Date();
// 		myDate.getYear();        //获取当前年份(2位)
// 		myDate.getFullYear();    //获取完整的年份(4位,1970-????)
// 		myDate.getMonth();       //获取当前月份(0-11,0代表1月)
// 		myDate.getDate();        //获取当前日(1-31)
// 		myDate.getDay();         //获取当前星期X(0-6,0代表星期天)
// 		myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)
// 		myDate.getHours();       //获取当前小时数(0-23)
// 		myDate.getMinutes();     //获取当前分钟数(0-59)
// 		myDate.getSeconds();     //获取当前秒数(0-59)
// 		myDate.getMilliseconds();    //获取当前毫秒数(0-999)
// 		myDate.toLocaleDateString();     //获取当前日期
		
		var day = myDate.getDate();
		if(day < 10)
			day = "0" + day;
		
		var month = myDate.getMonth() + 1;
		if(month < 10)
			month = "0" + month;
		
		var date = myDate.getFullYear() + ""+  month + day;
		$("startDate").value = date;
	}
	
	
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
	
	initDateTime();
	
	$("stat").innerHTML = "统计 : " + <%= JSONObject.toJSONString(JSONObject.toJSONString(statmap))%>
</script>
</html>