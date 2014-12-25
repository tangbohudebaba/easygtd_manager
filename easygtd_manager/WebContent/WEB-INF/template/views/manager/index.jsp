<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<title>首页</title>		
		<link href="<%=path %>/web/common/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="<%=path %>/web/common/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet">
		<link href="<%=path %>/web/common/bootstrap/css/font-awesome.min.css" rel="stylesheet" />
		<link href="<%=path %>/web/common/bootstrap/css/datepicker.css" rel="stylesheet">
		<!-- <link href="../web/common/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet"> -->
		<link href="<%=path %>/web/common/bootstrap/css/prettify.css" rel="stylesheet">
		
		<link href="<%=path %>/web/manager/css/style.css" rel="stylesheet"/>
		<link href="<%=path %>/web/manager/css/paginate.css" rel="stylesheet" />
		
		<script src="<%=path %>/web/common/bootstrap/js/jquery-1.10.2.min.js"></script>	
		<script src="<%=path %>/web/common/bootstrap/js/bootstrap.min.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/prettify.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.bootstrap.js"></script>
		<!-- <script src="../common/bootstrap/js/site.js"></script> -->
		<script src="<%=path %>/web/common/bootstrap/js/jquery.messager.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/string.format.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.datagrid.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/copyright.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.dialog.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.tree.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/bootstrap-datepicker.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/bootstrap-typeahead.js"></script>
		<!-- <script src="../web/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
		<script src="../web/common/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
		<script src="<%=path %>/web/common/template.js"></script><!-- js模版 -->
	
		<script src="<%=path %>/web/manager/js/index.js"></script>
		<script src="<%=path %>/web/manager/js/changeTab.js"></script>
		<script src="<%=path %>/web/manager/js/task/index.js"></script><!-- 待办列表 -->
		<script src="<%=path %>/web/manager/js/business_entity/index.js"></script><!-- 商户 -->
		<script src="<%=path %>/web/manager/js/business_category/index.js"></script><!-- 商户类型 -->
		<script src="<%=path %>/web/manager/js/community_center/index.js"></script><!-- 小区 -->
		<script src="<%=path %>/web/manager/js/task_item/index.js"></script><!-- 待办 -->
		<script src="<%=path %>/web/manager/js/user/index.js"></script><!-- 用户 -->
		<script src="<%=path %>/web/manager/js/wuye/index.js"></script><!-- 用户 -->
		<script src="<%=path %>/web/manager/js/juweihui/index.js"></script><!-- 用户 -->
		<script src="<%=path %>/web/manager/js/activity/index.js"></script><!-- 活动 -->
		<script src="<%=path %>/web/manager/js/user_attention/index.js"></script><!-- 用户关注 -->
		<script src="<%=path %>/web/manager/js/templet/index.js"></script><!-- 模版 -->
		<script src="<%=path %>/web/manager/js/attend_activity/index.js"></script><!-- 参与的活动 -->
		<script src="<%=path %>/web/manager/js/paginate.js"></script><!--分页JS-->
		<script src="<%=path %>/web/common/bootstrap/js/ajaxfileupload.js"></script>
		<script src="<%=path %>/web/manager/js/client_user_homeimg/index.js"></script>
		
	</head>
	<body>		
		<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
			<ul class="nav navbar-nav navbar-header">
				<li>easyGTD数据管理平台</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li>您好，${sessionScope.systemuser.name}</li>
				<li><a id="a_login_id" href="<%=path %>/manager/login.jhtml"  onclick="loginLogout">登录</a></li>
				<li><a id="a_logout_id" href="<%=path %>/manager/logout.ac?systemuserId=${sessionScope.systemuser.id}">退出</a></li>
				
				<%-- <li class="dropdown">
					<a class="dropdown-toggle" data-toggle="dropdown" href="javascript:;">操作</a>
					
					<ul class="dropdown-menu" role="menu">
						<li><a href="<%=path %>/manager/login.jhtml">登录</a></li>
						<li><a href="<%=path %>/manager/logout.ac?systemuserId=${sessionScope.systemuser.id}">退出</a></li>
					</ul>
				</li> --%>
			</ul>
		</nav>	
		<div class="container">
			<div class="container_left">
				<ul class="nav nav-stacked">
					<li class="shite_space"></li>
					
					<%-- <li class="title">用户端首页图片组管理</li>
					<li url='<%=path %>/manager/client_user_homeimg/add_client_user_homeimg.jhtml'><a href="javascript:;">添加图片</a></li>
					<li url='<%=path %>/manager/client_user_homeimg/all_client_user_homeimg.jhtml'><a href="javascript:;">全部图片</a></li>
					
					<li class="title">行为统计管理</li>
					<li url='<%=path %>/manager/actionaccount/all_actionaccount.jhtml'><a href="javascript:;">下载量统计</a></li>
					
					<li class="title">App版本管理</li>
					<li url='<%=path %>/manager/juweihui/add_juweihui.jhtml'><a href="javascript:;">添加新版本</a></li>
					<li url='<%=path %>/manager/juweihui/all_juweihui.jhtml'><a href="javascript:;">全部版本</a></li>
					 --%>
					<li class="title">用户管理</li>
					<%-- <li url='<%=path %>/manager/user/add_user.jhtml'><a href="javascript:;">添加用户</a></li> --%>
					<li url='<%=path %>/manager/user/all_user.jhtml'><a href="javascript:;">全部用户</a></li>
					<!-- <li url='common_user/common_all_user.jhtml'><a href="javascript:;">普通用户</a></li> -->
					<li url='<%=path %>/manager/user/opinion_user.jhtml'><a href="javascript:;">用户反馈</a></li>
					<!-- <li url='pages/attend_activity/attend_activity_all.html'><a href="javascript:;">参与的活动</a></li> -->
			<!-- 		<li url='pages/user_attention/user_attention_all.html'><a href="javascript:;">用户关注</a></li> -->
					
					<%-- <li class="title">小区管理</li>
					<li url='<%=path %>/manager/community_center/community_center_add.jhtml'><a href="javascript:;">添加小区</a></li>
					<li url='<%=path %>/manager/community_center/community_center_all.jhtml'><a href="javascript:;">全部小区</a></li>
					 --%>
					
					<li class="title">任务管理</li>
					<%-- <li url='<%=path %>/manager/wuye/add_wuye.jhtml'><a href="javascript:;">添加物业</a></li>
					<li url='<%=path %>/manager/wuye/batchadd_wuye.jhtml'><a href="javascript:;">批量添加物业</a></li> --%>
					<li url='<%=path %>/manager/wuye/all_wuye.jhtml'><a href="javascript:;">全部任务</a></li>
					
					<%-- <li class="title">居委会管理</li>
					<li url='<%=path %>/manager/juweihui/add_juweihui.jhtml'><a href="javascript:;">添加居委会</a></li>
					<li url='<%=path %>/manager/juweihui/all_juweihui.jhtml'><a href="javascript:;">全部居委会</a></li>
					
					<li class="title">商户类型管理</li>
					<li url='pages/business_category/add_business_type.html'><a href="javascript:;">添加商户类型</a></li>
					<li url='pages/business_entity/business_entity_own.html'><a href="javascript:;">全部商户类型</a></li>
					
					<li class="title">商户管理</li>
					<li url='<%=path %>/manager/business_entity/business_entity_add.jhtml'><a href="javascript:;">添加商户</a></li>
					<li url='<%=path %>/manager/business_entity/business_entity_all.jhtml'><a href="javascript:;">全部商户</a></li>
					<!-- <li url='wait_check_business/wait_check_business_all.jhtml'><a href="javascript:;">待审核商户</a></li>
					<li url='pages/error_data/error_data_all.html'><a href="javascript:;">数据变动</a></li> -->
					<!-- <li><a href="#">已发布</a></li>
					<li><a href="#">待审核</a></li>
					<li><a href="#">草稿箱</a></li> -->
					
					<!-- <li><a href="#">已发布</a></li>
					<li><a href="#">待审核</a></li>	 -->
					<li class="title">活动管理</li>
					<li url='pages/activity/all_activity.html'><a href="javascript:;">全部活动</a></li>
					<li url='pages/activity/add_activity.html'><a href="javascript:;">添加活动</a></li>
					
					<li class="title">待办管理</li>	
					<li url='task_item/task_item_add.jhtml'><a href="javascript:;">添加待办</a></li>
					<li url='pages/task/all_pending.html'><a href="javascript:;">全部待办</a></li>
					 --%>
					<!-- <li class="title">模板管理</li>
					<li url='pages/templet/templet_manage.html'><a href="javascript:;">全部模板</a></li>
					<li url='pages/templet/create_templet.html'><a href="javascript:;">创建模板</a></li>
					<li url='pages/templet/templet_type.html'><a href="javascript:;">模板类型</a></li>	 -->
					
					<!-- <li url='pages/activity/activity_details.html'><a href="javascript:;">查看活动详情</a></li>	 -->					
				</ul>
			</div>
			<div class="container_right">
				<div class="home_page">
				<h3>欢迎使用easyGTD数据管理平台</h3>
				<h4>
					早上好，${sessionScope.systemuser.name}!
				</h4>
				<!-- <div>二维码</div> -->
				<div style="width: 80 ;height: 80 ;float: left;">
					<%-- <img src="<%=path %>/web/manager/images/yonghu.png" /> --%>
				</div>
				<div style="width: 80 ;height: 80 ;float: left;">
				<%-- 	<img src="<%=path %>/web/manager/images/shedai.png" /> --%>
				</div>
				
			<!-- 	<img src="../web/manager/images/shedai.png" /> -->
				</div>
			</div>
		</div>
	</body>
	
	<script type="text/javascript">
			var path='<%=path %>';
			if('${sessionScope.systemuser.name}'==null||'${sessionScope.systemuser.name}'==""){
				$("#a_logout_id").hide();
			}else{
				$("#a_login_id").hide();
			}
	</script>
</html>