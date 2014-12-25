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
<title>查看居委会</title>
<link href="../web/manager/css/juweihui/check_juweihui.css" rel="stylesheet" />
</head>
<body>
	<ol class="breadcrumb">
		<li>全部居委会</li>
		<li class="active">查看居委会</li>
	</ol>
	<button type="button" class="btn btn-primary" onClick="to_other_page('juweihui/all_juweihui.jhtml');">返回上一级</button>
	<button type="button" class="btn btn-primary" onClick="to_other_page('juweihui/compile.ac?id=${juweihui.id}');">编辑</button>
	<button type="button" class="btn btn-primary" onclick="delete_juweihui();">删除</button>

	<form class="form-horizontal" role="form">

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				居委会ID </label>
			<div class="col-sm-10" id="idDIV">${juweihui.id}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				名称 </label>
			<div class="col-sm-10" id="titleDIV">${juweihui.title}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				联系方式 </label>
			<div class="col-sm-10" id="phoneNumberDIV">${juweihui.phoneNumber}</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				地址 </label>
			<div class="col-sm-10" id="addressDIV">${juweihui.address}</div>
		</div>
		
		<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">商户地理位置</label>
				<div class="col-sm-10"><a href="http://mo.amap.com/?q=${juweihui.latitude},${juweihui.longitude}&name=park&dev=0" target="view_window">点击查看</a>
				</div>
		</div>
		
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">
				简介 </label>
			<div class="col-sm-10" id="contentDIV">${juweihui.content}</div>
		</div>

	</form>
</body>
</html>