<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>任务管理</title>		
		<link href="../web/manager/css/wuye/all_wuye.css" rel="stylesheet" />
	</head>
	<body>
		<h3>任务管理</h3>
		<div class="search" style="margin-top:50px;">
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
			<div class="btn-group">
				<!-- <button type="button" class="btn btn-primary btn-sm" style="float:left;" onClick="to_other_page('wuye/add_wuye.jhtml')">添加</button> -->	
			</div>
			<div id="all_user_page_div" style="text-align:right;padding-bottom: 10px;"></div>
		</div>
		<table id="tablewrap1" class="table table-striped" ></table>
	</body>
	
	
	<script type="text/javascript">
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
		   		url: "wuye/search.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "标题", field: "title"},
						                  {title: "创建时间", field: "createdAt"},
						                  {title: "更新时间", field: "updatedAt"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		}
		
		$(function() { 
			var rows;
			$.ajax({
				async: false,
		   		type: "get",
		   		dataType: 'JSON',
		   		url: "wuye/getAllWuye.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "标题", field: "title"},
						                  {title: "创建时间", field: "createdAt"},
						                  {title: "更新时间", field: "updatedAt"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		});
		
	</script>
</html>