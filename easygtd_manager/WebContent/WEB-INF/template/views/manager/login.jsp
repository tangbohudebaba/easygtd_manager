<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../inc/top.inc.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black" />
		<title>首页</title>		
		<link href="<%=path %>/web/common/bootstrap/css/bootstrap.css" rel="stylesheet">
		<link href="<%=path %>/web/common/bootstrap/css/jquery-ui-1.10.0.custom.css" rel="stylesheet">
		<link href="<%=path %>/web/common/bootstrap/css/font-awesome.min.css" rel="stylesheet" />
		<link href="<%=path %>/web/common/bootstrap/css/datepicker.css" rel="stylesheet">
		<!-- <link href="../web/common/bootstrap/css/bootstrap-datetimepicker.min.css" rel="stylesheet"> -->
		<link href="<%=path %>/web/common/bootstrap/css/prettify.css" rel="stylesheet">
		
		<link href="<%=path %>/web/manager/css/style.css" rel="stylesheet"/>
		<link href="<%=path %>/web/manager/css/paginate.css" rel="stylesheet" />
		
		<script src="<%=path %>/web/common/bootstrap/js/jquery-1.10.2.min.js"></script>	
		<script src="<%=path %>/web/common/bootstrap/js/bootstrap.min.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery-ui-1.10.0.custom.min.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/prettify.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.bootstrap.js"></script>
		<!-- <script src="../common/bootstrap/js/site.js"></script> -->
		<script src="<%=path %>/web/common/bootstrap/js/jquery.messager.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/string.format.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.datagrid.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/copyright.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.dialog.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/jquery.tree.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/bootstrap-datepicker.js"></script>
		<script src="<%=path %>/web/common/bootstrap/js/bootstrap-typeahead.js"></script>
		<!-- <script src="../web/common/bootstrap/js/bootstrap-datetimepicker.min.js"></script>
		<script src="../web/common/bootstrap/js/locales/bootstrap-datetimepicker.zh-CN.js"></script> -->
		<script src="<%=path %>/web/common/template.js"></script><!-- js模版 -->
	
		<script src="<%=path %>/web/manager/js/index.js"></script>
		<script src="<%=path %>/web/manager/js/changeTab.js"></script>
		<script src="<%=path %>/web/manager/js/task/index.js"></script><!-- 待办列表 -->
		<script src="<%=path %>/web/manager/js/business_entity/index.js"></script><!-- 商户 -->
		<script src="<%=path %>/web/manager/js/business_category/index.js"></script><!-- 商户类型 -->
		<script src="<%=path %>/web/manager/js/community_center/index.js"></script><!-- 小区 -->
		<script src="<%=path %>/web/manager/js/task_item/index.js"></script><!-- 待办 -->
		<script src="<%=path %>/web/manager/js/user/index.js"></script><!-- 用户 -->
		<script src="<%=path %>/web/manager/js/wuye/index.js"></script><!-- 用户 -->
		<script src="<%=path %>/web/manager/js/juweihui/index.js"></script><!-- 用户 -->
		<script src="<%=path %>/web/manager/js/activity/index.js"></script><!-- 活动 -->
		<script src="<%=path %>/web/manager/js/user_attention/index.js"></script><!-- 用户关注 -->
		<script src="<%=path %>/web/manager/js/templet/index.js"></script><!-- 模版 -->
		<script src="<%=path %>/web/manager/js/attend_activity/index.js"></script><!-- 参与的活动 -->
		<script src="<%=path %>/web/manager/js/paginate.js"></script><!--分页JS-->
		
	</head>
<body style=" margin:0 auto; width:500px;">

<div style="background-image:url(<%=path %>/web/manager/images/admin_login.png); width:500px; height:200px; margin-top:130px;">
  <form id="form1" name="form1" method="post" action="<%=path %>/manager/login.ac">
  
    <table width="500" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="250">&nbsp;</td>
        <td colspan="2">&nbsp;</td>
      </tr>
      <tr>
        <td height="25"><div align="right" class="STYLE1">用户名：</div></td>
        <td height="25" colspan="2"><input type="text" name="username" style="height:25px; width:200px; font-size:15pt; font-weight:bold;" /></td>
      </tr>
       <tr>
        <td height="25">&nbsp;</td>
        <td height="25" colspan="2">${u:getNonNullText(message['username'],'<font color="red">','</font>')}</td>
      </tr>
      
      <tr>
        <td height="25"><div align="right" class="STYLE1">密&nbsp; 码：</div></td>
        <td height="25" colspan="2"><input type="password" name="password" style="height:25px; width:200px; font-size:15pt; font-weight:bold;" /></td>
      </tr>
      
      <tr>
        <td height="25">&nbsp;</td>
        <td height="25" colspan="2">${u:getNonNullText(message['password'],'<font color="red">','</font>')}</td>
      </tr>
      
      <tr>
        <td height="25">&nbsp;</td>
        <td height="25" colspan="2"><input type="submit" name="Submit" value="" style="width:130px; height:30px; background-image:url(<%=path %>/web/manager/images/admin_login_button.png); border:0; background-color: transparent; "/></td>
      </tr>
      
      <tr>
        <td height="25">&nbsp;</td>
        <td height="25" colspan="2">${u:getNonNullText(message['error'],'<font color="red">','</font>')}</td>
      </tr>
      
    </table>
   
  </form>
  
</div>
 
</body>

</html>


