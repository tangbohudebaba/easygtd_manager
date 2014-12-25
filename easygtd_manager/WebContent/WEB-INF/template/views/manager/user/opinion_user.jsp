<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
    <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>用户反馈</title>
		<link href="../web/manager/css/user/all_user.css" rel="stylesheet" />
	</head>
	<body>
		<h3>用户反馈</h3>
		<table id="tablewrap1" class="table table-striped" ></table>
	</body>
	<script type="text/javascript">
		$(function() { 
			var rows;
			$.ajax({
				async: false,
		   		type: "get",
		   		dataType: 'JSON',
		   		url: "user/getAllOpinion.ac",
				success: function(obj){
						 rows=obj;
						 var $table = $('#tablewrap1');
						 $table.datagrid({
						        columns:[[
						                  {title: "id", field: "id"},
						                  {title: "用户姓名", field: "userName"},
						                  {title: "反馈内容", field: "content"},
						                  {title: "反馈时间", field: "createdAt"}
						        ]]
						      }).datagrid("loadData", {rows: rows});
		   		}
			});
		});
	</script>
</html>