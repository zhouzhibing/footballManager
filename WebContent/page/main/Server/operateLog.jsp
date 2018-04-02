<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/dpl.css" rel="stylesheet">
  <link href="http://g.alicdn.com/bui/bui/1.1.21/css/bs3/bui.css" rel="stylesheet">
</head>
<body>
 <div class="demo-content">
   <div id="grid">
     
   </div>
  <script src="http://g.tbcdn.cn/fi/bui/jquery-1.8.1.min.js"></script>
  <script src="http://g.alicdn.com/bui/bui/1.1.21/bui.js"></script>
 
<!-- script start --> 
    <script type="text/javascript">
            var Grid = BUI.Grid,
            Data = BUI.Data;
            var Grid = Grid,
            Store = Data.Store,
          columns = [
            {title : 'url',dataIndex:'url', width:'20%'},
            {title : '参数',dataIndex:'para', width:'20%'},
            {title : '用户名',dataIndex:'userName',width:'10%'},
            {title : '模块',dataIndex:'moduleName',width:'10%'},
            {title : 'IP',dataIndex:'ip',width:'10%'},
            {title : '时间',dataIndex:'times',width:'15%'}
          ];
 
        /**
         * 自动发送的数据格式：
         *  1. start: 开始记录的起始数，如第 20 条,从0开始
         *  2. limit : 单页多少条记录
         *  3. pageIndex : 第几页，同start参数重复，可以选择其中一个使用
         *
         * 返回的数据格式：
         *  {
         *     "rows" : [{},{}], //数据集合
         *     "results" : 100, //记录总数
         *     "hasError" : false, //是否存在错误
         *     "error" : "" // 仅在 hasError : true 时使用
         *   }
         * 
         */
        var store = new Store({
            url : '<%=request.getContextPath()%>/acts/queryLog',
            autoLoad:true, //自动加载数据
            pageSize:10	// 配置分页数目
          }),
          grid = new Grid.Grid({
            render:'#grid',
            columns : columns,
            loadMask: true, //加载数据时显示屏蔽层
            store: store,
            // 底部工具栏
            bbar:{
                // pagingBar:表明包含分页栏
                pagingBar:true
            }
          });
 
        grid.render();
    </script>
<!-- script end -->
  </div>
</body>
</html>