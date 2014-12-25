<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>居委会管理</title>		
		<link href="../web/manager/css/juweihui/all_juweihui.css" rel="stylesheet" />
	</head>
	<body>
		<h3>居委会管理</h3>
		<div class="search" style="margin-top:50px;">
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
				<button type="button" class="btn btn-primary btn-sm" style="float:left;" onClick="to_other_page('juweihui/add_juweihui.jhtml')">添加</button>	
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
		   		url: "juweihui/search.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "名称", field: "title"},
						                  {title: "电话号码", field: "phoneNumber"},
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
		   		url: "juweihui/getAllJuweihui.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "名称", field: "title"},
						                  {title: "电话号码", field: "phoneNumber"},
						                  {title: "地址", field: "address"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
						 
		   		}
			});
		});
		
	</script>
</html>