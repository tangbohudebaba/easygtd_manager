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
		<title>å¨é¨åæ·ç±»å</title>		
		<link href="../web/manager/css/business_entity/business_entity_own.css" rel="stylesheet" />
		<!-- åæ·ç±»ååè¡¨ -->
		<script id="business_type_list_show" type="text/html">
			<% for (var i = 0;i<business_categories.length;i++) {%>
			 <tr>
				<td><label><input type='checkbox' businessID='<%=business_categories[i].id%>' name='subBox'></label></td>
				<td><a href='#' onclick="to_business_category_check('<%=business_categories[i].id%>');" >
				<%=business_categories[i].name%></a></td>
				<td><%=business_categories[i].count_of_publicly_visible_business_entities%></td>
				<td><%=business_categories[i].count_of_publicly_invisible_business_entities%></td>
				<td>æ æ°æ®</td>
			</tr>
			<%}%>
		</script>

		<!-- æç´¢åæ·ç±»å -->
		<script id="search_business_type_show" type="text/html">
				<%for ( var i = 0; i < result.length; i++) {%>
					<tr>
					<td><label><input type='checkbox' businessID='<%=result[i].id%>' name='subBox'></label></td>
					<td><a href='#' onclick="to_business_category_check('<%=result[i].id%>');" >
					<%=result[i].name%>
					</a></td>
					<td>æ æ°æ®</td>
					<td>æ æ°æ®</td>
					<td>æ æ°æ®</td>
				</tr>
			<%}%>
		</script>

		<SCRIPT>
			//æ¾ç¤ºåè¡¨
			$(function(){
				 business_entity_own(1);
			});
			//æ·»å åæ·ç±»å
			function add_business_type(){
				$(".container_right").load('pages/business_category/add_business_type.html');
			};
		</SCRIPT>
	</head>
	<body>
		<h3>å¨é¨åæ·ç±»å</h3>
		<button type="button" class="btn btn-xs" onClick="add_business_type()">æ·»å ç±»å</button>	
		<div class="search">
			<button type="button" class="check_all"  name="checkAllBut"></button>
			<div class="input-group">
				<input type="text" class="form-control" name="search_business"/>
				<span class="input-group-btn">
					<button class="btn btn-default" type="button" onclick="search_business_type(1)">æç´¢</button>
				</span>
			</div>
		</div>	
		<div class="row"></div>
		<div class="Bulk Operations">	
			<button type="button" class="btn btn-primary btn-sm" onClick="delete__business_type()">å é¤</button>	
			<div id="business_entity_own_div" style="text-align:right;padding-bottom: 10px;"></div>
		</div>
		<table class="table table-hover table-condensed table-bordered">
			<thead>
				<tr>
					<th style="text-align:center"><input type="checkbox" onClick="checkBox(this,'subBox')">
					</th>			
					<th>ç±»åå</th>
					<th>å·²åå¸åæ·æ°é</th>
					<th>æªåå¸åæ·æ°é</th>
					<th>åå»ºæ¶é´</th>
				</tr>
			</thead>		
			<tbody id="business_entity_own_tb">
				
			</tbody>
		</table>
	</body>
</html>