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
		<title>查看小区信息</title>		
		<link href="../web/manager/css/community_center/community_center_info.css" rel="stylesheet" />
	</head>
	<body>
		<ol class="breadcrumb">
			<li>全部小区</li>
			<li id="active_li"></li>
			<li class="active">小区信息</li>
		</ol>
		<button type="button" class="btn btn-primary" onClick="to_other_page('community_center/community_center_all.jhtml');">返回上一级</button>
		<button type="button" class="btn btn-primary" onClick="to_other_page('community_center/compile.ac?id=${communityCenter.id}');">编辑</button>
		<button type="button" class="btn btn-primary" onclick="delete_community_center();">删除</button>
		<div class="clearfix"></div>
        <form class="form-horizontal" role="form">
		<input type="hidden" name="community_center_id"><!--小区ID-->
		<input type="hidden" name="community_center_name"><!--小区名称-->
		<input type="hidden" name="type">
		
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">
					小区ID </label>
				<div class="col-sm-10" id="communityCenterId">${communityCenter.id}</div>
			</div>
			
            <div class="form-group">
				<label for="inputTell" class="col-sm-2 control-label">小区名</label>
				<div class="col-sm-5" id="centerName">${communityCenter.name}</div>
            </div>
            
            <div class="form-group">
				<label for="inputTell" class="col-sm-2 control-label">物业</label>
				<div class="col-sm-5" id="wuye">${wuye}</div>
            </div>
            
             <div class="form-group">
				<label for="inputTell" class="col-sm-2 control-label">居委会</label>
				<div class="col-sm-5" id="juweihui">${juweihui}</div>
            </div>
            
            <div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-5" id="centerAddress">${communityCenter.address}</div>
            </div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">地理位置</label>
				
				<div class="col-sm-5"><a href="http://mo.amap.com/?q=${communityCenter.latitude},${communityCenter.longitude}&name=park&dev=0" target="view_window">点击查看</a>
				</div>
			</div>
        </form>        
	</body>
</html>