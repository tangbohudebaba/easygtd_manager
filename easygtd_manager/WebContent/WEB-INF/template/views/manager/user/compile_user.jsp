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
		<title>添加用户</title>		
		<link href="../web/manager/css/user/add_user.css" rel="stylesheet" />
		<script type="text/javascript">
		

		var communityCenterNames = "${communityCenterNames}";
		var communityCenterNameArray = communityCenterNames.split("---");
		//alert(communityCenterNameArray.length);
		//alert(communityCenterNames);
		//alert(communityCenterNameArray[1]);
		for (var int = 0; i< communityCenterNameArray.length; i++) {
			if(i==0){
				$("#community_centers_1").val(communityCenterNameArray[i]);
			}else{
				$(".community_center_item_delete_btn").removeClass("disabled");
				var community_center_item_div_clone = $("#community_center_item_div").clone();
				community_center_item_div_clone.children("#community_centers_1").val(communityCenterNameArray[i]);
				community_center_item_div_clone.appendTo("#community_centers_group_div");
			}
		}
		
		
			var userTpyeName = "${user.userTpyeName}";
			var employee_type_select = document.getElementById("employee_type");  
			for(var i=0; i<employee_type_select.options.length; i++){
			    if(employee_type_select.options[i].innerHTML == userTpyeName){  
			    	employee_type_select.options[i].selected = true;  
			        break;  
			    }  
			}
			
			var gender = "${user.gender}";
			var gender_select = document.getElementById("gender");  
			for(var i=0; i<gender_select.options.length; i++){
			    if(gender_select.options[i].innerHTML == gender){  
			    	gender_select.options[i].selected = true;  
			        break;  
			    }  
			}
			
			//alert($("#employee_type").val());
			
			if(5 == $("#employee_type").val() ){
				$("#server_business_entities_div").show();
			}
			
			if(6 == $("#employee_type").val()){
				$("#server_area_div").show();
			}
			
			if(7 == $("#employee_type").val() ){
				$("#server_juweihui_div").show();
			}
			
			if(8 == $("#employee_type").val() ){
				$("#server_wuye_div").show();
			}
		
			$(function (){
				$("#dp3").datepicker({
					pickerPosition: "bottom-left",
				});
				var d = new Date(); 
				var year=d.getFullYear();
				var month=(d.getMonth() + 1)>=10?(d.getMonth() + 1):'0'+(d.getMonth() + 1);
				var day=d.getDate()>=10?d.getDate():'0'+d.getDate();
				$("[name='birthdate']").val(year+"-"+month+"-"+day);
			});
			
			  $('#server_business_entities_search_btn').click(function () {
			    var btn = $(this);
			    btn.button('loading');
			    $.ajax().always(function () {
			      btn.button('reset');
			    });
			  });
			  
			  
			$(function(){
				// Autocomplete
					//var business_entities = ["ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP", "Python", "Ruby", "Scala", "Scheme","烤鸭"];
					//var community_centers = ["ActionScript", "AppleScript", "Asp", "BASIC", "C", "C++", "Clojure", "COBOL", "ColdFusion", "Erlang", "Fortran", "Groovy", "Haskell", "Java", "JavaScript", "Lisp", "Perl", "PHP", "Python", "Ruby", "Scala", "Scheme","庆丰包子"];
					
					$("#business_entities").autocomplete({
						minLength: 1,
					    source: 'businessentities/searchAutocomplete.ac'
					});
					
					$(".community_centers").autocomplete({
						minLength: 1,
					    source: 'community_center/searchAutocomplete.ac'
					});
					
					$("#juweihui").autocomplete({
						minLength: 1,
					    source: 'intro/searchjuweihuiAutocomplete.ac'
					});
					
					$("#wuye").autocomplete({
						minLength: 1,
					    source: 'intro/searchwuyeAutocomplete.ac'
					});
			});
			  
			  function addCommunityCenterItem(){
					var inputArr= $(".community_center_item_div");
					if(inputArr.length=1){
						$(".community_center_item_delete_btn").removeClass("disabled");
					}
					var community_center_item_div_clone = $("#community_center_item_div").clone();
					community_center_item_div_clone.children("#community_centers_1").val("");
					community_center_item_div_clone.appendTo("#community_centers_group_div");
					$(".community_centers").autocomplete({
						minLength: 1,
					    source: 'community_center/searchAutocomplete.ac'
					});
				}
				
				function deleteCommunityCenterItem(deleteButtonElement){
					var inputArr= $(".community_center_item_div");
					if(inputArr.length==2){
						$(".community_center_item_delete_btn").addClass("disabled");
					}
					$(deleteButtonElement).parents("#community_center_item_div").remove();
				}
				
		</script> 
	</head>
	<body onload="show();">		
		<ol class="breadcrumb">
			<li>全部用户</li>
			<li class="active">编辑用户</li>
		</ol>
        <form class="form-horizontal" role="form">
        
        	<div class="form-group">
				<label for="inputId" class="col-sm-2 control-label">
					用户ID
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="id" id="inputId" value="${user.id}" readonly/>
				</div>
			</div>
            <div class="form-group">
				<label for="inputUserName" class="col-sm-2 control-label">
					用户类型 <span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<select  id="employee_type" name="employee_type" class="form-control"  onchange="changeEmployeeType(this.value)">
						<option value="1">系统管理员</option>
						<option value="2">普通用户</option>
						<option value="3">国信用户</option>
						<option value="4">中国人寿用户</option>
						<option value="5">商户管理员</option>
						<option value="6">社区代表</option>
						<option value="7">居委会管理员</option>
						<option value="8">物业管理员</option>
					</select>
				</div>
            </div>
            
            <div id="server_area_div" style="display:none;" class="form-group" >	
				<label for="inputUserName" class="col-sm-2 control-label">
					服务社区
					<button type="button" class="btn btn-primary btn-xs" onClick="addCommunityCenterItem()">再加一个社区</button>
				</label>
				<div class="col-sm-4" id="community_centers_group_div" >
					<div id="community_center_item_div" class="community_center_item_div">
						<input id="community_centers_1" name="community_centers" class="ui-autocomplete-input community_centers" autocomplete="off" placeholder="搜索社区" />
						<button type="button" class="btn btn-primary btn-xs disabled community_center_item_delete_btn" onClick="deleteCommunityCenterItem(this)">删除</button>
					</div>
				</div>
				
            </div>
            
			<%-- <div id="server_area_div" style="display:none;" class="form-group" >
				<label for="inputUserName" class="col-sm-2 control-label">
					服务社区
				</label>
				<div class="col-sm-4">
					<input id="community_centers" name="community_centers" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索社区" value="${communityCenters}" />
				</div>
            </div>
            --%>
            
            <div id="server_business_entities_div" style="display:none;" class="form-group" >
				<label for="inputUserName" class="col-sm-2 control-label">
					服务商户
				</label>
				<div class="col-sm-4">
					<input id="business_entities" name="business_entities" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索商户" value="${businessEntities}"/>
				</div>
            </div>
            
            <div id="server_juweihui_div" style="display:none;" class="form-group" >
				<label for="inputjuweihui" class="col-sm-2 control-label">
					居委会
				</label>
				<div class="col-sm-4">
					<input id="juweihui" name="juweihui" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索居委会" value="${juweihui}"/>
				</div>
            </div>
            
            <div id="server_wuye_div" style="display:none;" class="form-group" >
				<label for="inputwuye" class="col-sm-2 control-label">
					物业
				</label>
				<div class="col-sm-4">
					<input id="wuye" name="wuye" class="ui-autocomplete-input" autocomplete="off" placeholder="搜索物业" value="${wuye}"/>
				</div>
            </div>
            
			<div class="form-group">
				<label for="inputTell" class="col-sm-2 control-label">
					昵称
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="nick_name" id="inputNickname" placeholder="点击输入用户昵称" value="${user.nickname}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					真实姓名
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="realname" id="inputName" placeholder="请输入真实姓名" value="${user.name}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputUserName" class="col-sm-2 control-label">
					性别
				</label>
				<div class="col-sm-4" style="width: 9.333%;">
					<select id = "gender" name="gender" class="form-control" >
						<option value='男'>男</option>
						<option value='女'>女</option>
					</select>
				</div>
            </div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					出生日期
				</label>
				<div class="col-sm-4 input-append date" id="dp3" data-date="${user.birthdate}" data-date-format="yyyy-mm-dd">
					<input class="datepicker form-control" size="16" name="birthdate" type="text" readonly>
					<span class="add-on"><i><img src="../web/manager/images/calendar.jpg" /></i></span>
				</div>
			</div>

			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					手机号 <span style="color: red; font-size: x-large;">*</span>
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="cellphone" id="inputTel" placeholder="请输入正确的联系电话" value="${user.phoneNumber}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					邮件地址
				</label>
				<div class="col-sm-4">
					<input type="text" class="form-control" name="email" id="inputMail" placeholder="请输入正确的邮件地址" value="${user.email}">
				</div>
			</div>
			<div class="form-group">
				<label for="inputAddress" class="col-sm-2 control-label">
					简介
				</label>
				<div class="col-sm-4">
					<textarea class="form-control" name="info" rows="3" id="textareaID" >${user.bio}</textarea>
				</div>
			</div>
        </form>
		<button type="button" class="btn btn-primary" onClick="to_other_page('user/checkUser.ac?id=${user.id}');">取消</button>
		<button type="button" class="btn btn-primary" onClick="update_user_info();">提交</button>
	</body>
	
	<script type="text/javascript">
	
	
	</script>
</html>