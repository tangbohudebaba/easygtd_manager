
//添加、编辑用户页面使用 切换用户类型 
function changeType(v) {
	if('detail' == v ){
		$("#content_div").show();
		$("#title_div").show();
	}else{	
		$("#content_div").hide();
		$("#title_div").hide();
	}
	
	if('url' == v ){
		$("#urlStr_div").show();
	}else{
		$("#urlStr_div").hide();
	}
}
//提交
function modify_client_user_homeimg(url,submitType){
	
	var id = $("[name='id']").val();//
	var theme = $("[name='theme']").val();//
	var sort = $("[name='sort']").val();
	var type = $("[name='type']").val();
	var title = $("[name='title']").val();
	var content = $("[name='content']").val();
	var urlStr = $("[name='urlStr']").val();
	var fileToUpload = $("[name='fileToUpload']").val();
	
	if(!fileToUpload&&url.indexOf("update")<=0){
		alert("请选择一张图片");
		return;
	}
	
	if(!theme){
		alert("请填写详情页主题");
		return;
	}
	
	if(!sort){
		alert("排序序号");
		return;
	}
	
	var regInteger = /^[1-9]\d*$/;
	if(!regInteger.test(sort)){
		alert("排序序号必须为整数");
		return;
	}
	
	var regImg = /(.*)(jpg|png|gif|ico)$/;//图片正则
	if(!regImg.test(fileToUpload)&&url.indexOf("update")<=0){
			alert("图片格式不正确");
			return;
	}
	
	var data ={
			"id":id,
			"title":title,
			"theme":theme,
			"sort":sort,
			"type":type,
			"urlStr":urlStr,
			"content":content
	};
	$.ajaxFileUpload({
		url : url,
		secureuri : false,
		fileElementId : 'fileToUpload',
		dataType : 'json',
		type : "POST",
		data : data,
		success : function(data, status) {
			$.messager.alert(data.msg);
			to_other_page('client_user_homeimg/checkHomeimg.ac?id='+data.id);
		}
	});
}
//添加
function submit_client_user_homeimg_info() {
	var url="client_user_homeimg/save.ac";
	modify_client_user_homeimg(url,"POST");	 
}

//更新
function update_client_user_homeimg_info() {
	var url="client_user_homeimg/update.ac";
	modify_client_user_homeimg(url,"POST");	 
}


//删除
function delete_client_user_homeimg () {
	
	var id = $("#idDIV").text();
	if(confirm("确认删除?")) {
		var url="client_user_homeimg/delete.ac";
		var data ={
				"id":id,
		};
		$.ajax_request("get",url,data,function (obj){
			$.messager.alert(obj.msg);
			to_other_page('client_user_homeimg/all_client_user_homeimg.jhtml');
		});
	}			
}
