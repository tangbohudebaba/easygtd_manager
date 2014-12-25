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
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<title>查看小区详情</title>		
		<link href="../web/manager/css/community_center/community_center_details.css" rel="stylesheet" />
	</head>
	<SCRIPT>
			$(function (){
				var total = 25;//总数
				$("#longitude_latitude_scope_page").paginate({
					total: total//总数
				});
			});
		</SCRIPT>
	<body>
		<ol class="breadcrumb">
			<li>全部小区</li>
			<li class="active"></li>
		</ol>
		<button type="button" class="btn btn-xs" onclick="to_show_community_center();">查看信息</button>
		
		<input type="hidden" name="community_center_id"><!--小区ID-->
		<input type="hidden" name="community_center_name"><!--小区名称-->
		<input type="hidden" name="type">
		<div class="search">
			<button type="button" class="check_all">类型（4）</button>
			<button type="button" class="check_all">商户（4）</button>
			<button type="button" class="check_all">社区代表（1）</button>
			<div class="input-group">
				<input type="text" class="form-control" name="search_value"/>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="search_business_entity('longitude_latitude_scope_td','longitude_latitude_scope_page');">搜索</button>
				</span>
			</div>
		</div>	
		<div class="row"></div>
		<div class="Bulk Operations">	
			<div id="longitude_latitude_scope_page"  style="float:right;"></div><!-- 分页 -->		
		</div>
		<table class="table table-hover table-condensed table-bordered">
			<thead>
				<tr>
					<th style="text-align:center"><input type="checkbox" onclick="checkBox(this,'scope_business_id')"></th>							 
					<th>商户名</th>
					<th>商户类型</th>
					<th>创建人</th>
					<th>关联小区</th>
					<th>发布状态</th>
				</tr>
			</thead>		
			<tbody id="longitude_latitude_scope_td">
				
			</tbody>
		</table>
	</body>
</html>