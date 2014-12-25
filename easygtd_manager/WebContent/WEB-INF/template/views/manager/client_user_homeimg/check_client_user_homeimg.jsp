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
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<title>查看图片详情</title>
<link href="../web/manager/css/common/check.css" rel="stylesheet" />
</head>
<body>
	<ol class="breadcrumb">
		<li>全部图片</li>
		<li class="active">查看图片详情</li>
	</ol>
	<button type="button" class="btn btn-primary" onClick="to_other_page('client_user_homeimg/all_client_user_homeimg.jhtml');">返回上一级</button>
	<button type="button" class="btn btn-primary" onClick="to_other_page('client_user_homeimg/compile.ac?id=${clientUserHomeimg.id}');">编辑</button>
	<button type="button" class="btn btn-primary" onclick="delete_client_user_homeimg();">删除</button>

	<form class="form-horizontal" role="form">

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				图片ID </label>
			<div class="col-sm-10" id="idDIV">${clientUserHomeimg.id}</div>
		</div>
		
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				图片 </label>
			<div class="col-sm-10" id="imgDIV"><img alt="暂无" src="<%=path%>/uploads/img/client_user_homeimg/${clientUserHomeimg.imgUuid}"></div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				详细页标题 </label>
			<div class="col-sm-10" id="titleDIV">${clientUserHomeimg.title}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				图片名称 </label>
			<div class="col-sm-10" id="phoneNumberDIV">${clientUserHomeimg.imgName}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				详情页主题</label>
			<div class="col-sm-10" id="addressDIV">${clientUserHomeimg.theme}</div>
		</div>
		

		
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				详情页的内容 </label>
			<div class="col-sm-10" id="contentDIV">${clientUserHomeimg.content}</div>
		</div>

	</form>
</body>
</html>