<%@page import="web.data.MysqlControl"%>
<%@page import="web.action.statistics.JournalPointStatistics"%>
<%@page import="game.tools.utils.DateTools"%>
<%@page import="game.tools.page.Page"%>
<%@page import="game.tools.utils.StringTools"%>
<%@page import="com.alibaba.fastjson.JSONObject"%>
<%@page import="web.data.football.entity.JournalPoint"%>
<%@page import="web.data.mapper.SysOperateLogMapper"%>
<%@page import="web.control.entity.JobType"%>
<%@page import="web.control.entity.Key"%>
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
    <script type="text/javascript" src="../Js/MyAjax.js"></script>

 

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
<div class="form-inline definewidth m20" >
	<div style="float: left;">	
		<form action="../../../acts/statAct!showJournalPointCountByType" method="get">
			<input type="hidden" name="clear" value="true"/> 
			<input type="hidden" name="next" value="true"/> 
			<select name="actionId" >
				<%
					ArrayList<JournalPointDesc> jpDesclist =  MysqlControl.getJOURNAL_POINT_DESCList();
					String searchActionId = (String)session.getAttribute(Key.JOURNAL_POINT_ACTIONID);
					
					System.out.println("jpDesclist = " + jpDesclist);
					
					if(jpDesclist == null)
						return ;
					
					for(JournalPointDesc desc : jpDesclist)
					{
							if(desc.getId().equals(searchActionId))
							{
								%>
									<option value="<%= desc.getId()%>" selected="selected"><%=desc.getActionDesc() %>(<%=desc.getId() %>)</option>
								<%	
							}
							else
							{
								%>
									<option value="<%= desc.getId()%>"><%=desc.getActionDesc() %>(<%=desc.getId() %>)</option>
								<%
							}
						
					}
				%>
			</select>
		    <input type="submit" class="btn btn-success" value="埋点类型查询" />
		</form>
	</div>
	<div style="float: left; margin-left: 50px;">
		<form action="../../../acts/statAct!exportXls" method="get">
	    	<input type="submit" class="btn" value="导出"  />
	    </form>
	</div>
</div>

<table class="table table-bordered table-hover definewidth m10">
	<tr>
      	<td id="stat" colspan="11" align="center"></td>
    </tr>
    <thead>
    <tr>
    	<th>序号</th>
        <th>日期</th>
        <th>埋点统计</th>
        <th>生成时间</th>
    </tr>
    </thead>
    	<%
		ArrayList<StatisticsJournalPointCount> jpcList = (ArrayList<StatisticsJournalPointCount>)session.getAttribute(Page.PAGE_NO_RESULT);
		
		if(jpcList != null)
		{
			
			for(StatisticsJournalPointCount sjpc : jpcList)
			{
    	 %>
		     <tr>
	            <td><%= sjpc.getId() %></td>
	            <td><%= sjpc.getDate() %></td>
	            <td><%= sjpc.getJpCount()%></td>
	            <td style="width: 150px;"><%= DateTools.getCurrentTimeString(sjpc.getTime()) %></td>
	        </tr>	
        <%	}
		} %>
      <tr>
      	<td colspan="11" align="center">
      		<%
      			Integer pageNo = (Integer)session.getAttribute(Page.PAGE_NO);
      			if(session.getAttribute(Key.JOURNAL_POINT_ACTIONID) != null)
      			{
      		%>
      			<a href="../../../acts/statAct!showJournalPointCountByType?next=false" >上一页</a>
      			[<%= pageNo %>]
      			<a href="../../../acts/statAct!showJournalPointCountByType?next=true" >下一页</a>
      		<%}else{%>
      			<a href="../../../acts/statAct!showJournalPointCount?next=false" >上一页</a>
      			[<%= pageNo %>]
      			<a href="../../../acts/statAct!showJournalPointCount?next=true" >下一页</a>
      		<%	} %>
      	</td>
      </tr>
</table>
</body>
<script>
// 	function exportXls()
// 	{
// 		MyAjax myAjax = new MyAjax();
// 		myAjax.send("../../../acts/statAct!exportXls" , function(){
// 		});
// 	}
</script>
</html>