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
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>查看用户</title>
<link href="../web/manager/css/user/check_user.css" rel="stylesheet" />
</head>
<body>
	<ol class="breadcrumb">
		<li>全部用户</li>
		<li class="active">查看用户</li>
	</ol>
	<button type="button" class="btn btn-primary" onClick="to_other_page('user/all_user.jhtml');">返回上一级</button>
	<button type="button" class="btn btn-primary" onClick="to_other_page('user/compile.ac?id=${user.id}');">编辑</button>
	<button type="button" class="btn btn-primary" onclick="delete_user();">删除</button>

	<form class="form-horizontal" role="form">

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				用户ID </label>
			<div class="col-sm-10" id="idDIV">${user.id}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				真实姓名 </label>
			<div class="col-sm-10" id="nameDIV">${user.name}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				昵称 </label>
			<div class="col-sm-10" id="nicknameDIV">${user.nickname}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				性别 </label>
			<div class="col-sm-10" id="genderDIV">${user.gender}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				出生日期 </label>
			<div class="col-sm-10" id="birthdateDIV">${user.birthdate}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				联系电话 </label>
			<div class="col-sm-10" id="cellphoneDIV">${user.phoneNumber}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				简介 </label>
			<div class="col-sm-10" id="infoDIV">${user.bio}</div>
		</div>

		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label"> 用户类型
			</label>
			<div class="col-sm-10" id="employeeTypeDIV">${user.userTpyeName}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				邮件地址 </label>
			<div class="col-sm-10" id="emailDIV">${user.email}</div>
		</div>


	</form>
</body>
</html>