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
		<title>编辑物业</title>		
		<link href="../web/manager/css/wuye/add_wuye.css" rel="stylesheet" />
		
		<script type="text/javascript">
			$(function(){
					$("#community_centers").autocomplete({
						minLength: 1,
					    source: 'community_center/searchAutocomplete.ac'
					});
			});
		  
		</script> 
	</head>
	<body>		
		<ol class="breadcrumb">
			<li>物业</li>
			<li class="active">编辑物业</li>
		</ol>
         <form class="form-horizontal" role="form">
        	<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					ID
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="id" id="id" readonly value="${wuye.id}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					名称<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="title" id="title" placeholder="请输入名称" value="${wuye.title}">
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					联系方式<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="请输入正确的联系电话" value="${wuye.phoneNumber}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					地址
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="address" id="address" placeholder="请输入正确的地址" value="${wuye.address}">
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">为物业创建位置坐标</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="latlng" id="inputLatlng" placeholder="格式为：39.907546,116.396318" value="${wuye.latitude},${wuye.longitude}"/>
					<a href="http://api.map.soso.com/doc/tooles/picker.html" target="view_window">点击获取坐标</a>
				</div>
			</div>	
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					简介
				</label>
				<div class="col-sm-4">
					<textarea class="form-control" name="content" rows="3" id="content" >${wuye.content}</textarea>
				</div>
			</div>
        </form>
		<button type="button" class="btn btn-primary" onClick="to_other_page('wuye/checkWuye.ac?id=${wuye.id}');">取消</button>
		<button type="button" class="btn btn-primary" onClick="update_wuye_info();">提交</button>
	</body>
</html>