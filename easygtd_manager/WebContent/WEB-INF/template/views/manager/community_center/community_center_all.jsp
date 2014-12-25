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
		<title>全部小区</title>		
		<link href="../web/manager/css/community_center/community_center_all.css" rel="stylesheet" />
		<!-- 列表模版 -->
		<script id="community_center_list_temp" type="text/html">
		</script>
	</head>
	<body>
		<h3>全部小区</h3>
		<div class="search" style="margin-top:50px;">
			<button type="button" class="check_all" name="checkAllBut"></button>
			<button type="button" class="check_all" name="publishedBut"></button>
			<button type="button" class="check_all" name="unpublishedBut"></button>
			<div class="input-group">
				<input type="text" class="form-control" name="search_value"/>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="search();">搜索</button>
				</span>
			</div>
		</div>
		<div class="row"></div>
		<div class="Bulk Operations">
			<div class="btn-group">
				<button type="button" class="btn btn-primary btn-sm" onClick="to_other_page('community_center/community_center_add.jhtml');">添加</button>
			</div>	
			<div id="community_center_page_div" style="text-align:right;padding-bottom: 10px;"></div>
		</div>
		
		<table id="table_community_center" class="table table-striped" ></table>
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
		   		url: "community_center/search.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#table_community_center');
						 $table.datagrid({
						        columns:[[
									{title: "id", field: "id"},
									{title: "小区名称", field: "name"},
									{title: "地址", field: "address"},
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
		   		url: "community_center/getAllCommunityCenter.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#table_community_center');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "小区名称", field: "name"},
						                  {title: "地址", field: "address"},
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		});
	</script>
</html>