//更新居委会信息
function modify_juweihui(url,submitType){
	var id = $("[name='id']").val();//--
	var title = $("[name='title']").val();//名称--
	var phoneNumber = $("[name='phoneNumber']").val();//手机--
	var content = $("[name='content']").val();//介绍--
	var address = $("[name='address']").val();//地址--
	var latlng = $("[name='latlng']").val();//经纬度
	var reg_cellphone =/((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
	if(!title){
		alert("请输入居委会名称！");
		return false;
	}
	if(!phoneNumber){
		alert("请输入手机号码！");
		return false;
	}
	if(!reg_cellphone.test(phoneNumber)){
		alert("手机号码格式输入有误！");
		return false;
	}
	/*if(email&&!reg_email.test(email)){
		alert("邮箱格式输入有误！");
		return;
	} */
	var data ={
			"id":id,
			"title":title,
			"phoneNumber":phoneNumber,
			"content":content,
			"address":address,
			"latlng":latlng
	};
	$.ajax_request(submitType,url,data,function (obj){
		$.messager.alert(obj.msg);
		if(obj.code!=1){
			//to_other_page('juweihui/checkJuweihui.ac?id='+id);
		}
		return obj.id;
	}); 
}
//添加用户
function submit_juweihui_info() {
	var url="juweihui/save.ac";
	return modify_juweihui(url,"POST");	 
}

//更新用户
function update_juweihui_info() {
	var url="juweihui/update.ac";
	return modify_juweihui(url,"POST");	 
}

//删除用户
function delete_juweihui () {
	
	var id = $("#idDIV").text();
	if(confirm("确认删除?")) {
		var url="juweihui/delete.ac";
		var data ={
				"id":id,
		};
		$.ajax_request("get",url,data,function (obj){
			$.messager.alert(obj.msg);
			to_other_page('juweihui/all_juweihui.jhtml');
		});
	}			
}
