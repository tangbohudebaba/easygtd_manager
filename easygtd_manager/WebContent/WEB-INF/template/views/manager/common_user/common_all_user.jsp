<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<title>普通用户</title>		
		<link href="../web/manager/css/user/all_user.css" rel="stylesheet" />
		<SCRIPT>
			//添加用户
			function add_common_user(){
				$(".container_right").load('pages/common_user/add_common_user.html');
			}
			//查看用户
			function show_common_user_info(){
				$(".container_right").load('pages/common_user/check_common_user.html');
			}
		</SCRIPT>
	</head>
	<body>
		<h3>用户管理</h3>
		<div class="search" style="margin-top:50px;">
			<button type="button" class="check_all " onclick="to_other_page('pages/user/all_user.html')">内部用户（1）</button>
			<button type="button" class="check_all active" onclick="to_other_page('pages/common_user/common_all_user.html');">普通用户（2）</button>
			<button type="button" class="check_all" onclick="to_other_page('pages/business_admin/business_all_admin.html');">商户管理员（1）</button>
			<div class="input-group">
				<input type="text" class="form-control" name="search_value">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button">搜索</button>
				</span>
			</div>			
		</div>
		<div class="row"></div>
		<div class="Bulk Operations">
			<button type="button" class="btn btn-primary btn-sm" onclick="add_common_user();">添加</button>	
			<div style="text-align:right;padding-bottom: 10px;"></div><!-- 分页层 -->
		</div>
		<table class="table table-hover table-condensed table-bordered">
			<thead>
				<tr>
					<th><label><input type='checkbox' onclick="checkBox(this,'commonID')"></label></th>
					<th>用户名</th>
					<th>姓名</th>
					<th>性别</th>
					<th>人员类型</th>
					<th>手机</th>
					<th>服务区域</th>
					<th>简介</th>
					<th>创建时间</th>
				</tr>
			</thead>
			<tr>
				<td><label><input type='checkbox' name="commonID"></label></td>
				<td><a href="#" onclick="show_common_user_info();"> 用户名</a></td>
				<td>张三</td>
				<td>男</td>
				<td>国信客服</td>
				<td>12345678909</td>
				<td>服务区域</td>
				<td>简介</td>
				<td>2014-2-25</td>
			</tr>	
		</table>
	</body>
</html>