//搜索--商户
function search_business_entity_list(currentPage) {
	var search_value = $("[name='search_value']").val();
	$.ajax({
		async: false,
   		type: "GET",
   		url: SEARCH_API_HOST + "/business/keywordSearch.ac",
		dataType: 'JSONP',
		jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
		data: {
			"keyword":search_value,
			"from":per_page*(currentPage-1),
			"size":per_page
		},
		success: function(obj){
			$("#business_entity_list_tb").empty();
			var total = obj.data[0].total;	
			var checkAll = obj.data[0].result.length;
			var trStr = template('business_entity_list_temp',obj.data[0]);	
			$("[name='checkAllBut']").text("全部(" + checkAll + ")");
			$("[name='publishedBut']").text("已发布(0)");
			$("[name='unpublishedBut']").text("未发布(0)");
			$("#business_entity_list_tb").append(trStr);	
			//添加分页
			$("#business_entity_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_business_entity
			});
   		}
	});	
}
// 加载列表--全部商户页面使用
function business_entity_list(currentPage) {
	var url = API_HOST + "/business_entities?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page=" + per_page + "&page=" + currentPage;
	$.ajax_request("GET",url,null,function (obj){
		$("#business_entity_list_tb").empty();
		var total =  obj.data.total;//数据总数
		var checkAll = obj.data.business_entities.length;
		var trStr = template('business_entity_list_temp',obj.data);
		$("[name='checkAllBut']").text("全部(" + checkAll + ")");
		$("[name='publishedBut']").text("已发布(0)");
		$("[name='unpublishedBut']").text("未发布(0)");
		$("#business_entity_list_tb").append(trStr);
		//添加分页
		$("#business_entity_page_div").paginate({
			total: total,//总数
			currentPage:currentPage,//当前页
			pageSize:per_page,
			callBack: business_entity_list
		});
	});	
}
//去商户信息页面
 function to_business_entity_info(type,id,name,business_category_id) {
	$(".container_right").load('pages/business_entity/business_entity_check.html',
		function (response,status) {
		 	if (status == "success") {
				$("[name='id']").val(id);
				$("[name='type']").val(type);
				$("[name='business_name']").val(name);
				$("[name='business_category_id']").val(business_category_id);
				$(".breadcrumb .active").text(name);
		 		business_entity_info(id,name,business_category_id);
			}
		}); 	
 }
 //查看页面 取消
 function business_entity_back(){
	var type=$("[name='type']").val();
	var url='';
	if('all'==type){
		url='pages/business_entity/business_entity_all.html';
	}else if('attention'==type){
		url='pages/user_attention/user_attention_all.html';
	}
	$(".container_right").load(url);
 }
//去编辑商户信息页面
function to_editor_business_entity() {
	var id = $("[name='id']").val();
	var business_name = $("[name='business_name']").val();
	var business_category_id = $("[name='business_category_id']").val();
	var type = $("[name='type']").val();
	$(".container_right").load('pages/business_entity/business_entity_redact.html',
		function (response,status) {
		 	if (status == "success") {
		 		var url = API_HOST + "/business_categories/" + business_category_id + "/business_entities/" + id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
				$.ajax_request("GET",url,null,function (obj){
					var mydata = obj.data;	
					$("#breadcrumb_li").text(business_name);
					$("[name='id']").val(id);
					$("[name='business_name']").val(business_name);
					$("[name='business_category_id']").val(mydata.business_category.id);
					$("[name='business_category_name']").val(mydata.business_category.name);
					$("[name='type']").val(type);
					$("[name='name']").val(mydata.name);
					$("[name='address']").val(mydata.address);
					$("[name='phone']").val(mydata.phone);
					$("[name='intro']").val(mydata.intro);
				});
			}
		}); 
}
//加载商户分类--添加、编辑商户页面使用
function business_category_list(business_category_list_tb) {
	var url = API_HOST + "/business_categories?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax_request("GET",url,null,function (obj){
		var mydata = obj.data.business_categories;
		var trStr = "";
		$("#" + business_category_list_tb + "");
		$("#" + business_category_list_tb + "").empty();
		$.each(mydata,function(i, item) {
			trStr += "<option value='" + item.id + "'>" + item.name + "</option>";
		});
		$("#" + business_category_list_tb + "").append(trStr); 
	});
}
// 取消发布
function cancel_publish() {
	var id = $("[name='id']").val();
	var business_category_id = $("[name='business_category_id']").val();
	var url = API_HOST + "/business_categories/" + business_category_id + "/business_entities/" + id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	var data={
    		"business_entity": {
        		"publicly_visible": true
    		}
		};
	$.ajax_request("PUT",url,data,function (obj){
		alert(obj.status.message);
	});
}
//更新商户
function modify_business_entity(url,submitType){
	var businessEntity_id = $("[name='businessEntity_id']").val();
	var businessCategoryId = $("[name='businessCategoryId']").val();
	var creatorNameAndPhone = $("[name='creatorNameAndPhone']").val();
	var name = $("[name='name']").val();
	var phone = $("[name='phone']").val();
	var address = $("[name='address']").val();
	var intro = $("[name='intro']").val();
	var latlng = $("[name='latlng']").val();
	if(!name){
		alert("请填写商户名称");
		return;
	}
	if(!latlng){
		alert("请为商户创建位置坐标");
		return ;
	}
	var data={
			"id":businessEntity_id,
			"businessCategoryId":businessCategoryId,
			"creatorNameAndPhone":creatorNameAndPhone,
			"name": name,
			"phone":phone,
			"address": address,
			"intro": intro,
			"latlng":latlng
		};
	$.ajax_request(submitType,url,data,function (obj){
		$.messager.alert(obj.msg);
		if(obj.code!=1){
			to_other_page('businessentities/checkBusinessEntities.ac?id='+obj.id);
		}
	});
}
//更新商户信息
function update_business_entity () {
	var url = "businessentities/update.ac";
	modify_business_entity(url,"POST");	
}
//提交商户信息--添加商户页面使用
function submit_business_entity () {
	var url = "businessentities/save.ac";
	modify_business_entity(url,"POST");		
}
//商户详细信息
function business_entity_info (id,name,business_category_id) {
	var url = API_HOST + "/business_categories/" + business_category_id + "/business_entities/" + id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax_request("GET",url,null,function (obj){
		var mydata = obj.data;
		$("#categoryDIV").text(mydata.business_category.name);
		$("#nameDIV").text(mydata.name);
		$("#addressDIV").text(mydata.address);
		$("#phoneDIV").text(mydata.phone);
		$("#introDIV").text(mydata.intro);
	});
}
//编辑页面取消
function business_entity_redact_back() {
	var id = $("[name='id']").val();
	var business_name = $("[name='business_name']").val();
	var business_category_id = $("[name='business_category_id']").val();
	var type = $("[name='type']").val();
	to_business_entity_info(type,id,business_name,business_category_id);
}
// 删除商户信息
function delete_business_entity () {
	var id = $("#idDIV").text();
	if(confirm("确认删除?")) {
		var url="businessentities/delete.ac";
		var data ={
				"id":id,
		};
		$.ajax_request("get",url,data,function (obj){
			$.messager.alert(obj.msg);
			to_other_page('business_entity/business_entity_all.jhtml');
		});
	}			
}
//搜索商户类型 添加和编辑页面使用
function search_Business_category (){
	var seach_value=$("[name='seach_value']").val();
	var childrenNode = $("#business_category_list_but").find("option");
	if (!!seach_value) {
		childrenNode.each(function() {
			var find = $(this).text().toLowerCase().indexOf(seach_value);
			if (find == -1) {
				$(this).remove();
			}
		});
	} 
	else {
		business_category_list('business_category_list_but');
	} 
}