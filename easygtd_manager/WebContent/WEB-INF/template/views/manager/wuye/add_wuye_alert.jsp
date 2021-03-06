﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../inc/top.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
	<head>
		<title>添加物业</title>
		<link href="<%=path %>/web/manager/css/wuye/add_wuye.css" rel="stylesheet" />
	</head>
	<body>	
	<div id="friendlink" currentindex=""></div>
		<ol class="breadcrumb">
			<li class="active">添加物业</li>
		</ol>
        <form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					名称<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="title" id="title" placeholder="请输入名称">
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					联系方式<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="phoneNumber" id="phoneNumber" placeholder="请输入正确的联系电话">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					地址
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="address" id="address" placeholder="请输入正确的地址">
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">为物业创建位置坐标</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="latlng" id="inputLatlng" placeholder="格式为：39.907546,116.396318"/>
					<a href="http://api.map.soso.com/doc/tooles/picker.html" target="view_window">点击获取坐标</a>
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					简介
				</label>
				<div class="col-sm-4">
					<textarea class="form-control" name="content" rows="3" id="content"></textarea>
				</div>
			</div>
			
        </form>
	</body>
</html>