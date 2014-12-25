<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>用户端首页图片组管理</title>		
		<link href="../web/manager/css/common/all.css" rel="stylesheet" />
	</head>
	<body>
		<h3>用户端首页图片组管理</h3>
		
		<div class="row"></div>
		<div class="Bulk Operations" style="margin-top:15px;">
			<div class="btn-group">
				<button type="button" class="btn btn-primary btn-sm" style="float:left;" onClick="to_other_page('client_user_homeimg/add_client_user_homeimg.jhtml')">添加</button>	
			</div>
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
		   		url: "client_user_homeimg/getAllHomeimg.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "图片名称", field: "imgName"},
						                  {title: "详细页主题", field: "theme"},
						                  {title: "页眉文字", field: "theme"},
						                  {title: "图片排序序号", field: "sort"},
						                  {title: "动作类型", field: "type"},
						                  {title: "更新时间", field: "updatedAt"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		});
		
	</script>
</html>