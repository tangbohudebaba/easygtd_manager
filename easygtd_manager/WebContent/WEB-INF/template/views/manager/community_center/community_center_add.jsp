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
		<title>添加小区</title>
		<link href="../web/manager/css/community_center/community_center_add.css" rel="stylesheet" />
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
			<li class="active">添加小区</li>
		</ol>
        <form class="form-horizontal" role="form">
            <div class="form-group">
				<label for="inputTell" class="col-sm-2 control-label">小区名<span style="color: red; font-size: x-large;">*</span></label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="inputTell" name="name" placeholder="请输入正确的小区名" />
				</div>
            </div>
            <div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">地址</label>
				<div class="col-sm-5">
					<input type="text" class="form-control" id="inputAddress" name="address" placeholder="请输入小区地址" />
				</div>
            </div>
            
            <div id="server_wuye_div"  class="form-group" >
				<label for="inputwuye" class="col-sm-2 control-label">
					物业
				</label>
				<div class="col-sm-5">
					<input id="wuye" name="wuye" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索物业"/>
					<a href="javascript:newwuye();">新建一个物业</a>
				</div>
						
            </div>
            
            <div id="server_juweihui_div" class="form-group" >
				<label for="inputjuweihui" class="col-sm-2 control-label">
					居委会
				</label>
				<div class="col-sm-5">
					<input id="juweihui" name="juweihui" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索居委会"/>
					<a href="javascript:newjuweihui();">新建一个居委会</a>
				</div>
            </div>
            
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">为小区创建位置坐标<span style="color: red; font-size: x-large;">*</span></label>
				<div class="col-sm-5">
				
				<input type="text" class="form-control" name="latlng" id="inputLatlng" placeholder="格式为：39.907546,116.396318"/>
					<a href="http://api.map.soso.com/doc/tooles/picker.html" target="view_window">点击获取坐标</a>
				</div>
			</div>			
			<button type="button" class="btn btn-primary" onClick="to_other_page('community_center/community_center_all.jhtml')">取消</button>
			<button type="button" class="btn btn-primary" onClick="submit_community_center()">保存</button>			
		</form>
    </div>
	<div id="wuye_alert" style="width: 1000px;height: 400px;">
	</div>
	<div id="juweihui_alert" style="width: 1000px;height: 400px;">
	</div>
	</body>
		
	<script type="text/javascript">
	
		function newwuye(){
			$("#wuye_alert").load("wuye/add_wuye_alert.jhtml");
			$("#wuye_alert").dialog({
		         title:    "添加物业",
		       onClose: function() {
		    	   $(this).dialog("close"); 
		    	},
		        buttons: [
		           {
		               text: "取消"
		             , 'class': "btn-primary"
		             , click: function() {
		                 $(this).dialog("close");
		               }
		           },
		           {
		               text: "创建"
		             , 'class': "btn-success"
		             , click: function() {
		            	 var title = $("[name='title']").val();//名称--
		            	 var result = submit_wuye_info();
		            	 if(result!=false){
		            		 $(this).dialog("close");
		            		 $("#wuye").val(title);
		            	 }
		               }
		           }
		         ]
		     });
		}
		
		function newjuweihui(){
			$("#juweihui_alert").load("juweihui/add_juweihui_alert.jhtml");
			$("#juweihui_alert").dialog({
		         title:    "添加居委会",
		       onClose: function() {
		    	   $(this).dialog("close"); 
		    	},
		        buttons: [
		           {
		               text: "取消"
		             , 'class': "btn-primary"
		             , click: function() {
		                 $(this).dialog("close");
		               }
		           },
		           {
		               text: "创建"
		             , 'class': "btn-success"
		             , click: function() {
		            	 var title = $("[name='title']").val();//名称--
		            	 var result = submit_juweihui_info();
		            	 if(result!=false){
		            		 $(this).dialog("close");
		            		 $("#juweihui").val(title);
		            	 }
		               }
		           }
		         ]
		     });
		}
	
	</script>
</html>