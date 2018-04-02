<%@ page language="java" import="java.util.*,web.data.entity.* , web.control.entity.*"  contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap.css" />
    <link rel="stylesheet" type="text/css" href="../Css/bootstrap-responsive.css" />
    <link rel="stylesheet" type="text/css" href="../Css/style.css" />
<body>
<iframe src="../../alert.jsp" hidden="hidden" style="width: 0px; height: 0px;"></iframe>
<div align="center">  
<form id="myForm" action="../../../acts/srvAct!doCmd?cmd=<%=Cmd.CMD_ADD_MAIL %>" method="post">
<!--  <form id="myForm" method="post">  -->
 <table class="table table-bordered table-hover definewidth m10">
    <tr>
     <td width="10%" class="tableleft">邮件标题</td>
     <td><input type="text" id="title" name="title" value=""/></td>
 </tr>
 <tr>
     <td class="tableleft">收件人</td>
     <td>
     	<div>
     		<span style="color: red;">"0"</span>：则给全服发送系统邮件。<br/>
     		<span style="color: red;">"单个角色ID"</span>：则给对应的角色ID，发送系统邮件。<br/>
     		<span style="color: red;">"给多个角色发送邮件"</span>：则角色ID以","（逗号）分开。如ID,ID,ID
     	</div>
     	<input type="text" id="revName" name="revName"value=""/><br/>
     </td>
 </tr>
 <tr>
     <td class="tableleft">邮件内容</td>
     <td>
  		<textarea id="content" name="content" rows="10" cols="20"></textarea>
     </td>
 </tr>
  <tr>
     <td class="tableleft">删除天数</td>
<!--      <td><input type="text" id="delDay" value=""/></td> -->
	<td>
<!-- 		<s:select list="#{'0':0,'1':1,'2':2,'3':3,'4':4,'5':5,'6':6,'7':7,'8':8,'9':9,'10':10}" name="mail.delDay" style="width: 210px;" value="1"> -->
<!-- 		</s:select> -->
		<input type="hidden" name="delDay"  value="1" />
		<select name="select" id = "delDay"  style="width: 210px;" >
			<option value="0" >0</option>
		  	<option selected="selected" value="1">1</option>
			<option value="2" >2</option>
			<option value="3" >3</option>
			<option value="4" >4</option>
			<option value="5" >5</option>
			<option value="6" >6</option>
			<option value="7" >7</option>
			<option value="8" >8</option>
			<option value="9" >9</option>
			<option value="10" >10</option>
	  	</select>
  </td>
 </tr>
 <tr>
     <td class="tableleft">附件</td>
<!--      <td> -->
<!--      		金币<input type="text" id="addGold" style="width: 50px;" /> -->
<!--      		钻石<input type="text" id="addDiamond" style="width: 50px;" /> -->
<!--      		物品<input type="text" id="addItemArr" style="width: 50px;" /> -->
<!--      </td> -->
     <td><input type="text" id="attach" name="attach" value="" />&nbsp;	1,2|3,4| 资源类型或物品ID,资源类型值或物品数量|资源类型或物品ID,资源类型值或物品数量| </td>
 </tr>
  <tr>
     <td class="tableleft">渠道</td>
     <td> <div id="divCheckBox">
     	<div style="width: 250px; float: left;">
    	<input name="checkall" id="checkall" value="-999999" type="checkbox" onclick="selectAll()"> 全选 <br/>
    	</div>
<%--     	<%  List <SysChannel> list = (List<SysChannel>)session.getAttribute("listChannel"); --%>
//     		if(list!=null )
//     		{
    			
//     			int channelIndex = 1;
    			
// 	    		System.out.print(list.size());
// 	    		for(SysChannel channel:list) 
<%-- 	    		{%>	 --%>
<!-- 	    			<div style="width: 250px; float: left;"> -->
<%-- 	    				<input name="checkValues" id="checkValues" value="<%=channel.getChannelId() %>" type="checkbox"><%=channel.getChannelName() %> --%>
<!-- 	    			</div> -->
<%--     				<%if(channelIndex % 10 == 0) --%>
<%--     				{%> --%>
<!--     					<br/> -->
<%--     			<%	} --%>
//     				channelIndex++;
//     			}
//     		}
<%--     	%> --%>
    </div> 
      <input type="hidden" name="checkName" id="checkName" value="-999999">
      <input type="hidden" name="mailJson" id="mailJson" value="">
    </td>
  
 </tr>
  <tr>
      <td class="tableleft"></td>
      <td>
          <button class="btn btn-primary" type="submit" onclick="return onSendMail()" >发送</button> &nbsp;&nbsp;
      </td>
  </tr>
</table>
</form> 
<script type="text/javascript">
	function onSendMail()
	{
		var title = document.getElementById("title").value;
		var revName = document.getElementById("revName").value;
		var content = document.getElementById("content").value;
		var attach = document.getElementById("attach").value;
		var delDayObj = document.getElementById("delDay");
		var delDay = delDayObj.options[delDayObj.selectedIndex].value; 
		if(title == "" || revName == "" || content == "")
		{
			alert("标题，收件人，内容，不能为空！");
			return false;
		}
		
		var boxarray = document.getElementsByName("checkValues");  
		var channelOkCount = 0;
		
		for(var i = 0; i < boxarray.length; i++) 
		{   
			if(boxarray[i].checked == true)
			{
				channelOkCount ++;
				break;
			}
		} 
		
// 		if(channelOkCount == 0)
// 		{
// 			alert("请选择一个渠道！");
// 			return false;
// 		}	
		document.getElementsByName("delDay")[0].value=delDay;
// 		document.getElementById("myForm").onsubmit();
		var mailJsonString ="{\"title\": \""+title+"\",\"revName\": \""+revName+"\",\"content\": \""+content+"\", \"attach\": \""+attach+"\",\"delDay\": \""+delDay+"\"}";
		document.getElementById("mailJson").value=mailJsonString;
	<%-- 	location.href = "/acts/srvAct!doCmd?cmd=<%=Cmd.CMD_ADD_MAIL%>"; --%>
		return true;
	}
	
	
	function selectAll() 
	{ 
		var ischecked = document.getElementById("checkall").checked; 
		if(ischecked) 
		{   
			checkallbox();  
		}else 
		{   
			discheckallbox();  
		}
	}
	//选中全部复选框 
	function checkallbox() 
	{  
		var boxarray = document.getElementsByName("checkValues");  
		for(var i = 0; i < boxarray.length; i++) 
		{   
			boxarray[i].checked = true;  
		} 
	}
	
	//取消选中全部复选框
	function discheckallbox() 
	{  
		var boxarray = document.getElementsByName("checkValues"); 
		for(var i = 0; i < boxarray.length; i++) 
		{   
			boxarray[i].checked = false; 
		} 
	}
</script>
</div>
</body>
</html>