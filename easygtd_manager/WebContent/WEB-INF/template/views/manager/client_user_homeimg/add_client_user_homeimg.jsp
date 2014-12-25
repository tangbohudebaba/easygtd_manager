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
		<title>添加图片</title>
		<link href="<%=path %>/web/manager/css/common/add.css" rel="stylesheet" />
		<link href="<%=path %>/web/common/bootstrap/css/ajaxfileupload.css" rel="stylesheet" />
		
		<script type="text/javascript">
			changeType("detail");
		</script>
	</head>
	<body>	
	<div id="friendlink" currentindex=""></div>
		<ol class="breadcrumb">
			<li>全部图片</li>
			<li class="active">添加图片</li>
		</ol>
        <form class="form-horizontal" role="form">
        
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					添加图片<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
				<input type="file" name="fileToUpload" id="fileToUpload" />
				</div>
			</div>
			
			<div id="theme_div" class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					详情页主题<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="theme" id="theme" placeholder="请输入详情页主题">
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					排序序号<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="sort" id="sort" placeholder="请输入图片排序序号(必须为整数，数值越小越排前面)">
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					图片动作类型
				</label>
				<div class="col-sm-4">
					<select name="type" class="form-control" onchange="changeType(this.value);">
						<option value='detail'>跳转到详情页面</option>
						<option value='wuye'>跳转到物业</option>
						<option value='juweihui'>跳转到居委会</option>
						<option value='url'>跳转到第三方网页</option>
					</select>
				</div>
			</div>
			<div id="urlStr_div" class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					第三方网页地址
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="urlStr" id="urlStr" placeholder="请输入第三方网页地址">
				</div>
			</div>
			
			<div id="title_div" class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					详情页文字标题
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="title" id="title" placeholder="请输入详情页文字标题">
				</div>
			</div>
			
			<div id="content_div" class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					详情页文字内容
				</label>
				<div class="col-sm-4">
					<textarea class="form-control" name="content" rows="3" id="content"></textarea>
				</div>
			</div>
			
			
        </form>
		<button type="button" class="btn btn-primary" onClick="to_other_page('client_user_homeimg/all_client_user_homeimg.jhtml');">取消</button>
		<button type="button" class="btn btn-primary" onClick="submit_client_user_homeimg_info();">提交</button>
	</body>
	
</html>