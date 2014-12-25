<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../inc/top.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>全部商户</title>		
		<link href="../web/manager/css/business_entity/business_entity_all.css" rel="stylesheet" />	
		<script id="business_entity_list_temp" type="text/html">	
		</script>
	</head>
	<body>
		<h3>全部商户</h3> 
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
				<button type="button" class="btn btn-primary btn-sm" onClick="to_other_page('business_entity/business_entity_add.jhtml');">添加</button>
			</div>	
			<div id="business_entity_page_div" style="text-align:right;padding-bottom: 10px;"></div>
		</div>
		<table id="table_business_entity" class="table table-striped" ></table>
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
		   		url: "businessentities/search.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#table_business_entity');
						 $table.datagrid({
						        columns:[[
									{title: "id", field: "id"},
									{title: "名称", field: "name"},
									{title: "电话", field: "phone"},
									{title: "地址", field: "address"}
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
		   		url: "businessentities/getAllBusinessEntity.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#table_business_entity');
						 $table.datagrid({
						        columns:[[
									{title: "id", field: "id"},
									{title: "名称", field: "name"},
									{title: "电话", field: "phone"},
									{title: "地址", field: "address"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		});
	</script>
</html>
