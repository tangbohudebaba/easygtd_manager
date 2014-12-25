<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>行为统计管理</title>		
		<link href="../web/manager/css/actionaccount/all_actionaccount.css" rel="stylesheet" />
	</head>
	<body>
		<h3>下载量统计</h3>
		<div class="row"></div>
		<div class="Bulk Operations" style="margin-top:15px;">
			<div id="all_user_page_div" style="text-align:right;padding-bottom: 10px;"></div>
		</div>
		<table id="tablewrap1" class="table table-striped" ></table>
	</body>
	
	<script type="text/javascript">
	
		$(function() { 
			var rows;
			$.ajax({
				async: false,
		   		type: "get",
		   		dataType: 'JSON',
		   		url: "actionaccount/getAllDownloadCount.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "设备类型", field: "facilityType"},
						                  {title: "客户端类型", field: "clientType"},
						                  {title: "下载量", field: "count"},
						                  {title: "统计开始时间", field: "createdAt"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
		   		}
			});
		});
	
	</script>
</html>