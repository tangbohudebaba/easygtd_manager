<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>用户管理</title>		
		<link href="<%=path %>/web/manager/css/user/all_user.css" rel="stylesheet" />
	</head>
	
	<body>
	
	
	
		<h3>用户管理</h3>
		<div class="search" style="margin-top:50px;">
		当前系统用户数为:<div id ="userType_a_span"></div>
		
			<!-- <button type="button" class="check_all active" onclick="to_other_page('pages/user/all_user.html')">内部用户（1）</button>
			<button type="button" class="check_all" onclick="to_other_page('pages/common_user/common_all_user.html');">普通用户（2）</button>
			<button type="button" class="check_all" onclick="to_other_page('pages/business_admin/business_all_admin.html');">商户管理员（1）</button> -->
			<div class="input-group">
				<input type="text" class="form-control" name="search_value">
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="search();">搜索</button>
				</span>
			</div>			
		</div>
		<div class="row"></div>
		<div class="Bulk Operations" style="margin-top:15px;">
			<div class="btn-group" style="float: left;">
				<!-- <button type="button" class="btn btn-primary btn-sm" onClick="to_other_page('user/add_user.jhtml')">添加</button>	 -->
			</div>
		</div>
			<div id="all_user_page_div" style="text-align:right;padding-bottom: 10px;"></div>
		<table id="tablewrap1" class="table table-striped" ></table>
	</body>
	
	
	<script type="text/javascript">
		initUserInfo(0);
		
		function initUserInfo(userType){
			getUserTypeCount();
			findAll(userType);
		}
		
		function getUserTypeCount(){
			$.ajax({
				async: false,
		   		type: "get",
		   		dataType: 'text',
		   		url: path+"/manager/user/getUserTypeCount.ac",
				success: function(obj){
					$("#userType_a_span").text(obj);
		   		}
			});
		}
		
		function search(){
			var search_value = $("[name='search_value']").val();
			var rows;
			$.ajax({
				async: false,
		   		type: "get",
		   		dataType: 'JSON',
		   		data: {
					"term":search_value,
				},
		   		url:  path+"/manager/user/search.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  
						                  {title: "id", field: "id"},
						                  {title: "电话号码", field: "phone"},
						                  {title: "姓名", field: "name"},
						                  {title: "注册时间", field: "createdAt"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		}
		
		function findAll(userType){
			var rows;
			$.ajax({
				async: false,
		   		type: "get",
		   		dataType: 'JSON',
		   		data: {
					"userType":userType,
				},
		   		url: path+"/manager/user/getAllUsers.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  
						                  {title: "id", field: "id"},
						                  {title: "电话号码", field: "phone"},
						                  {title: "姓名", field: "name"},
						                  {title: "注册时间", field: "createdAt"}
						                  
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		}
		
	</script>
</html>