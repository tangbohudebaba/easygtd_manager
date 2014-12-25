//搜索加载用户列表
function search_user(currentPage) {
	var search_value = $("[name='search_value']").val();
	$.ajax({
   		type: "GET",
   		url: SEARCH_API_HOST+"/users/keywordSearch.ac",
		dataType: 'JSONP',
		async: false,
		jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
		data: {
			"keyword":search_value,
			"from":per_page*(currentPage-1),
			"size":per_page
		},
		success: function(obj) {
			$("#all_user_list_tb").empty();
			var total = obj.data[0].total;	
			var trStr = template('user_list_temp',obj.data[0]); 
			$("#all_user_list_tb").append(trStr);
			$("#all_user_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_user
			});
   		}
	});
}
//加载用户列表
function user_list(currentPage) {
	var url = API_HOST + "/users?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page="+per_page+"&page=" + currentPage;
	$.ajax_request("GET",url,null,function (obj){
		$("#all_user_list_tb").empty();
		var total = obj.data.total;
		var trStr = template('user_list_temp',obj.data); 
		$("#all_user_list_tb").append(trStr);
		$("#all_user_page_div").paginate({
			total: total,//总数
			currentPage:currentPage,//当前页
			pageSize:per_page,
			callBack: user_list
		});
	});
}
//添加、编辑用户页面使用 切换用户类型 
function changeEmployeeType(v) {
	if('5' == v ){
		$("#server_business_entities_div").show();
	}else{	
		$("#server_business_entities_div").hide();
	}
	
	if('6' == v){
		$("#server_area_div").show();
	}else{	
		$("#server_area_div").hide();
	}
	
	if('7' == v ){
		$("#server_juweihui_div").show();
	}else{	
		$("#server_juweihui_div").hide();
	}
	
	if('8' == v ){
		$("#server_wuye_div").show();
	}else{	
		$("#server_wuye_div").hide();
	}
	
}
//更新用户信息
function modify_user(url,submitType){
	var id = $("[name='id']").val();//昵称--
	var nickname = $("[name='nick_name']").val();//昵称--
	var realname = $("[name='realname']").val();//姓名--
	var gender = $("[name='gender']").val();//性别--
	var birthdate = $("[name='birthdate']").val();//生日--
	var cellphone = $("[name='cellphone']").val();//手机--
	var email = $("[name='email']").val();//邮箱--
	var info = $("[name='info']").val();//介绍--
	var employee_type = $("[name='employee_type']").val();//用户类型--
	var community_centerArry = $("input.community_centers");//小区--
	var business_entities = $("[name='business_entities']").val();//商户--
	var juweihui = $("[name='juweihui']").val();//居委会--
	var wuye = $("[name='wuye']").val();//物业--
	var reg_cellphone =/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
	var community_centers ="";
	community_centerArry.each(function(){
		community_centers=community_centers+"---"+$(this).val();
	});
	if(!cellphone){
		alert("请输入手机号码！");
		return;
	}
	 if(!reg_cellphone.test(cellphone)){
		alert("手机号码格式输入有误！");
		return;
	}
	/*if(email&&!reg_email.test(email)){
		alert("邮箱格式输入有误！");
		return;
	} */
	var data ={
			"id":id,
			"realname":realname,
			"gender":gender,
			"nickname":nickname,
			"birthdate":birthdate,
			"email":email,
			"info":info,
			"employee_type":employee_type,
			"community_centers":community_centers,
			"business_entities":business_entities,
			"cellphone":cellphone,
			"juweihui":juweihui,
			"wuye":wuye
	};
	$.ajax_request(submitType,url,data,function (obj){
		$.messager.alert(obj.msg);
		if(obj.code!=1){
			//to_other_page('user/checkUser.ac?id='+id);
		}
	
//		if(obj.status.message == 'Success') {
//    	 	to_show_user_info(type,obj.data.id,nickname);
//		}else {
//			alert(obj.status.message);
//		}
	}); 
}
//添加用户
function submit_user_info() {
	var url="user/save.ac";
	modify_user(url,"POST");	 
}

//更新用户
function update_user_info() {
	var url="user/update.ac";
	modify_user(url,"POST");	 
}
//去查看用户详细信息
function to_show_user_info(type,userID,nickname) {
	$(".container_right").load('pages/user/check_user.html',
		function (response,status) {
		 	if (status == "success"){
				$("[name='user_id']").val(userID);
				$("[name='nickname']").val(nickname);
				$("[name='type']").val(type);
				$(".breadcrumb .active").text(nickname);
		 		show_user_info(userID);
			}
		}); 
}
//返回上一级
function all_user(){
	$(".container_right").load(user/all_user.html);
};
/*function all_user(){
	var type=$("[name='type']").val();
	var url='';
	if('all'==type){
		url='pages/user/all_user.html';
	}else if('attention'==type){
		url='pages/user_attention/attention_user/attention_user_all.html';
	}
	$(".container_right").load(url);
};*/
//用户详细信息
function show_user_info(userID) {
	var url = API_HOST + "/users/" + userID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax_request("GET",url,null,function (obj){
		var mydata = obj.data;
		$("#employeeTypeDIV").text("国信客服");
		$("#nicknameDIV").text(mydata.nickname);
		$("#nameDIV").text(mydata.name);
		$("#genderDIV").text(mydata.gender=='female'?'女':'男');
		$("#birthdateDIV").text(mydata.brithdate);
		$("#addressDIV").text("目前没有");
		$("#cellphoneDIV").text(mydata.phone_number);
		$("#emailDIV").text(mydata.email);
		$("#infoDIV").text("暂无简介");
	}); 
}
//去编辑用户信息
function to_edit_user() {
	$(".container_right").load('../user/compile_user.html');
}
//编辑页面取消
function cancel_user() {
	var user_id = $("[name='user_id']").val();
	var nickname = $("[name='nickname']").val();
	var type = $("[name='type']").val();
	to_show_user_info(type,user_id,nickname);
}
//编辑页面
function edit_user_info_show(userID) {
	var url = API_HOST + "/users/" + userID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax_request("GET",url,null,function (obj){
		var mydata = obj.data;
		var employee_type = 'employee_of_nationsky';
		$("[name='employee_type'] option[value='" + employee_type + "']").attr("selected",true); 
		changeEmployeeType(employee_type);
		$("[name='nick_name']").val(mydata.nickname);
		$("[name='name']").val(mydata.name);
		$("[name='gender'] option[value='" + mydata.gender + "']").attr("selected",true); 
		$("[name='birthdate']").val(mydata.brithdate);
		$("[name='address']").val("目前没有");
		$("[name='cellphone']").val(mydata.phone_number);
		$("[name='email']").val(mydata.email);
		$("[name='info']").val("暂无简介");
	});
}
//更新用户信息
function submit_edit_user_info() {
	var user_id = $("[name='user_id']").val();
	var url=API_HOST + "/users/" + user_id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	modify_user(url,"PUT");	 
}
//删除用户
function delete_user () {
	
	var user_id = $("#idDIV").text();
	if(confirm("确认删除?")) {
		var url="user/delete.ac";
		var data ={
				"id":user_id,
		};
		$.ajax_request("get",url,data,function (obj){
			$.messager.alert(obj.msg);
			to_other_page('user/all_user.jhtml');
		});
	}			
}
