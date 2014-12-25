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
		<title>查看商户</title>
		<link href="../web/manager/css/business_entity/business_entity_check.css" rel="stylesheet" />
	</head>
	<body>
		<ol class="breadcrumb">
			<li>全部商户</li>
			<li class="active">查看商户</li>
		</ol>
		<button type="button" class="btn btn-primary" onClick="to_other_page('business_entity/business_entity_all.jhtml');">返回上一级</button>
		<button type="button" class="btn btn-primary" onClick="to_other_page('businessentities/compile.ac?id=${businessEntity.id}');">编辑</button>
		<button type="button" class="btn btn-primary" onClick="delete_business_entity();">删除</button>
		<!-- <button type="button" class="btn btn-primary" onClick="cancel_publish();">取消发布</button> -->
		<!-- <input type="hidden" name="type"> --><!--区分是查询的所有的还是关注的 别删了 -->
		<div class="clearfix"></div>
        <form class="form-horizontal" role="form">
        	<div class="form-group">
				<label for="inputId" class="col-sm-2 control-label">商户ID</label>
				<div class="col-sm-5" id="idDIV">${businessEntity.id}</div>
            </div>
            <div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">商户分类</label>
				<div class="col-sm-5" id="categoryDIV">${businessCategory.name}</div>
            </div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">店名</label>
				<div class="col-sm-5" id="nameDIV">${businessEntity.name}</div>
            </div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-5" id="addressDIV">${businessEntity.address}</div>
            </div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">联系电话</label>
				<div class="col-sm-5" id="phoneDIV">${businessEntity.phone}</div>
            </div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">简介</label>
				<div class="col-sm-5" id="introDIV">${businessEntity.intro}</div>
            </div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">商户地理位置</label>
				<div class="col-sm-5"><a href="http://mo.amap.com/?q=${businessEntity.latitude},${businessEntity.longitude}&name=park&dev=0" target="view_window">点击查看</a>
			</div>
        </form>      
	</body>
</html>