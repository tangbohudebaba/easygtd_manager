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
		<title>编辑小区信息</title>
		<link href="../web/manager/css/community_center/community_center_redact.css" rel="stylesheet" />
		<script type="text/javascript">
		 $(function(){					
					$("#wuye").autocomplete({
						minLength: 1,
					    source: 'intro/searchwuyeAutocomplete.ac'
					});
					
					$("#juweihui").autocomplete({
						minLength: 1,
					    source: 'intro/searchjuweihuiAutocomplete.ac'
					});
				});
		</script>
	</head>
	<body>
		<ol class="breadcrumb">
			<li>全部小区</li>
			<li id="active_li"></li>
			<li>小区信息</li>
			<li class="active">编辑</li>
		</ol>
        <form class="form-horizontal" role="form">
			<div class="form-group">
				<label for="inputId" class="col-sm-2 control-label">
					小区ID
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="communityCenterId" id="inputId" value="${communityCenter.id}" readonly/>
				</div>
			</div>
            <div class="form-group">
				<label for="inputTell" class="col-sm-2 control-label">小区名<span style="color: red; font-size: x-large;">*</span></label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="name" id="inputTell" value="${communityCenter.name}"/>
				</div>
            </div>
            <div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" name="address" id="inputAddress" value="${communityCenter.address}"/>
				</div>
            </div>
            
            
          	<div id="server_juweihui_div" class="form-group" >
				<label for="inputjuweihui" class="col-sm-2 control-label">
					居委会
				</label>
				<div class="col-sm-4">
					<input id="juweihui" name="juweihui" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索居委会" value="${juweihui}"/>
				</div>
            </div>
            
            <div id="server_wuye_div" class="form-group" >
				<label for="inputwuye" class="col-sm-2 control-label">
					物业
				</label>
				<div class="col-sm-4">
					<input id="wuye" name="wuye" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索物业" value="${wuye}"/>
				</div>
            </div>
            
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">为小区创建位置坐标<span style="color: red; font-size: x-large;">*</span></label>
				<div class="col-sm-5">
				
				<input type="text" class="form-control" name="latlng" id="inputLatlng" placeholder="格式为：39.907546,116.396318" value="${communityCenter.latitude},${communityCenter.longitude}"/>
					<a href="http://api.map.soso.com/doc/tooles/picker.html" target="view_window">点击获取坐标</a>
				</div>
			</div>
			<button type="button" class="btn btn-primary" onClick="to_other_page('community_center/checkCommunityCenter.ac?id=${communityCenter.id}')">取消</button>
			<button type="button" class="btn btn-primary" onclick="editor_community_center();">提交</button>
        </form>
	</body>
</html>