<%@page import="web.action.url.ActionURL"%>
<%@page import="web.control.UserControl"%>
<%@page import="web.data.entity.SysServerList"%>
<%@page import="web.data.entity.SysUser"%>
<%@page import="web.control.entity.*"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
	<title>football Manager</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	
	<link rel="shortcut icon" href="../images/joy.ico" type="image/x-icon" />
	<link href="assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
	<link href="assets/css/bui-min.css" rel="stylesheet" type="text/css" />
	<link href="assets/css/main-min.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
	//alert("script");
</script>

<body>
<%-- 	<s:debug></s:debug> --%>
	<iframe src="../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
	<div class="header">
		<div class="dl-title">
			<!--<img src="/chinapost/Public/assets/img/top.png">-->
		</div>
		<%
			UserControl uc = (UserControl) session.getAttribute(Key.LOGIN_USER_CONTROL);
			if(uc==null)
				return;
			SysUser loginUser = uc.getUser();
			SysServerList sl = (SysServerList) session.getAttribute(Key.IN_SERVER);
		%>
		<div class="dl-log">
			欢迎您，<span class="dl-log-user"><%=loginUser == null ? "无" : loginUser.getUserName()%>，</span>
			当前服务器:【<span id="serverName"><%=(sl==null?"未连接":sl.getName())%></span>】
			上次登录时间:<%=loginUser == null ? "无" : loginUser.getLastLoginTime()%>
			<a href="User/exit.jsp" title="退出系统" class="dl-log-quit">[退出]</a>
		</div>
	</div>
	<div class="content">
		<div class="dl-main-nav">
			<div class="dl-inform">
				<div class="dl-inform-title">
					<s class="dl-inform-icon dl-up"></s>
				</div>
			</div>
			<ul id="J_Nav" class="nav-list ks-clear">
				<li class="nav-item dl-selected">
					<div class="nav-item-inner nav-home">系统管理</div>
				</li>
				<li class="nav-item dl-selected">
					<div class="nav-item-inner nav-order">游戏服务管理</div>
				</li>
				<li class="nav-item dl-selected">
					<div class="nav-item-inner nav-order">统计查询</div>
				</li>
<!-- 				<li class="nav-item dl-selected"> -->
<!-- 					<div class="nav-item-inner nav-order">数据后台</div> -->
<!-- 				</li> -->
			</ul>
		</div>
		<ul id="J_NavContent" class="dl-tab-conten">

		</ul>
	</div>
	<script type="text/javascript" src="assets/js/jquery-1.8.1.min.js"></script>
	<script type="text/javascript" src="assets/js/bui-min.js"></script>
	<script type="text/javascript" src="assets/js/common/main-min.js"></script>
	<script type="text/javascript" src="assets/js/config-min.js"></script>
	<script>
	 
    function closeServer()
    {
    	alert("closeServer");
    }
    
    BUI.use('common/main',function(){
    var config = 
    [
        {
        	id:'0',
        	menu:
        	[
                {
                	text:'系统管理',
                	items:
                	[
                        <%if (loginUser.getJob() == JobType.JOB_ADMIN) {%>
				        {id:'01',text:'用户管理',href:'../../acts/userAct!toUserList'},
				        {id:'02',text:'操作日志',href:'Server/operateLog.jsp'},
				        {id:'03',text:'数据库详情',href:'../../acts/dbAct!toDbListPage'},
				        {id:'04',text:'配置信息',href:'Server/serverConfInfo.jsp'},
				        <%}%>
				        {id:'05',text:'编辑帐户',href:'User/editSelf.jsp'},
				        {id:'06',text:'游戏服务器',href:'../../acts/slAct!toServerListPage'}
        			]
                }
            ]
        },
        {
        	id:'1',
        	menu:
	       	[
	       	 	{
	       	 		text:'游戏服务管理',
	       	 		items:
	       	 		[
				        {id:'11',text:'在线人数',href:'../../acts/srvAct!doCmd?cmd=<%=Cmd.CMD_ONLINE%>'},
// 				        {id:'12',text:'执行CMD',href:'Server/executeCmd.jsp'},
				        {id:'13',text:'操作命令',href:'../../acts/srvAct!doCmd?cmd=<%=Cmd.CMD_ADD_RESOURCE%>'},
				        {id:'14',text:'查询信息',href:'Server/showInfo.jsp'},
// 				        {id:'15',text:'添加邮件',href:'Server/addMail.jsp'},
				        {id:'16',text:'行为查询',href:'../../acts/jpAct!journalpointList'},
				        {id:'17',text:'礼包管理',href:'../../acts/giftAct!showGiftList?clear=true'},
        			]
        	 	}
	        ]
        },
        {
        	id:'2',
        	menu:
          	[
                {
                	text:'统计查询',
                	items:
                	[
                		<%if (loginUser.getJob() == JobType.JOB_ADMIN) {%>
				        {id:'21',text:'查看统计事件',href:'../../acts/statAct!startupStatistics'},
				        {id:'22',text:'指定日期统计',href:'Server/apointStatistics.jsp'},
				        <%}%>
                		{id:'23',text:'埋点统计查询',href:'../../acts/statAct!showJournalPointCount?clear=true&next=true'},
        			]
                }
            ]
          },
          {
        	id:'3',
        	menu:
          	[
                 {
                	text:'数据后台',
                	items:
                 	[
						{id:'51',text:'服务器连接',href:'../../acts/docmdAct?urlAction=<%=ActionURL.TO_STATISTICS_DATA_JSP%>'}
         			]
                 }
            ]
         },
    ];
    new PageUtil.MainPage({
        modulesConfig : config
    });
    });
    
</script>
</body>
</html>