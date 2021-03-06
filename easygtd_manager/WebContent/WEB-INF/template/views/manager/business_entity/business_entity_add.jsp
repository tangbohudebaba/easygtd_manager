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
		<title>添加新商户</title>
		<link href="../web/manager/css/business_entity/business_entity_add.css" rel="stylesheet" />
		<script type="text/javascript">
		$(function(){
				$("#creatorNameAndPhone").autocomplete({
					minLength: 1,
				    source: 'user/searchAutocomplete.ac'
				});
			});
	    </script>
	</head>
	<body>		
		<ol class="breadcrumb">
			<li>全部商户</li>
			<li class="active">添加商户</li>
		</ol>
		<form class="form-horizontal" role="form">
		<!-- <input type="hidden" name="type" value="all">区分是查询的所有的还是关注的 别删了 -->
			<!-- <div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label" style="margin-right:15px;">
					选择一个分类
				</label>
			    <input type="text" class="icon form-control"  name="business_category_name" autocomplete="off" placeholder="选择分类"/> 
			    <input type="hidden" name="business_category_id" />
				<span type="text" class="icon caret"></span>
			    <div class="shop">
			        <input type="text" name="seach_value" id="chose" placeholder="搜索" onkeyup="search_Business_category();"  onfocus="if(this.value=='输入关键词，回车搜索全国设计素材资源'){this.value='';}"/>   
			        <select size="5" id="business_category_list_but" ></select>
			    </div>	
			</div> 	 -->
			
			<div class="form-group">
				<label for="inputUserName" class="col-sm-2 control-label" >
					商户分类<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-10">
					<select name="businessCategoryId" class="form-control" id="business_category_list_but" >
						<option value='1'>宾馆</option>
						<option value='2'>公司企业</option>
						<option value='3'>餐饮</option>
						<option value='4'>生活服务</option>
						<option value='5'>交通设施</option>
						<option value='6'>购物</option>
						<option value='7'>休闲娱乐</option>
						<option value='8'>教育</option>
						<option value='9'>政府机构</option>
						<option value='10'>汽车服务</option>
						<option value='11'>金融</option>
						<option value='12'>医疗</option>
						<option value='13'>其他</option>
					</select>
				</div>
            </div>
			<div id="server_business_entities_div" class="form-group" >
				<label for="inputUserName" class="col-sm-2 control-label">
					商户创建者
				</label>
				<div class="col-sm-10">
					<input id="creatorNameAndPhone" name="creatorNameAndPhone" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索用户（名称，手机号，邮箱）"/>
				</div>
            </div>
			
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label">
					店名<span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="inputEmail3" name="name" placeholder="请输入正确店名" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">
					地址
				</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="inputPassword3" name="address" placeholder="请输入商户店址" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">
					联系电话
				</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="inputPassword3" name="phone" placeholder="请输入正确的联系电话" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label">
					简介
				</label>
				<div class="col-sm-10">
				    <input type="text" class="form-control" id="inputPassword3" name="intro" placeholder="请输入商户简介" />
				</div>
			</div>
			
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">为商户创建位置坐标<span style="color: red; font-size: x-large;">*</span></label>
				<div class="col-sm-10">
					<input type="text" class="form-control" name="latlng" id="inputLatlng" placeholder="格式为：39.907546,116.396318"/>
					<a href="http://api.map.soso.com/doc/tooles/picker.html" target="view_window">点击获取坐标</a>
				</div>
			</div>	
			
			<div class="footer_button">
				<button type="button" class="btn btn-primary" onClick="to_other_page('business_entity/business_entity_all.jhtml');">取消</button>
				<button type="button" class="btn btn-primary" onclick="submit_business_entity();">提交</button>
			</div> 
		</form>
	</body>
</html>