//商户类型列表
function business_entity_own(currentPage) {
	var url = API_HOST+ "/business_categories?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page=" + per_page + "&page=" + currentPage;
	$.ajax({
		url : url,
		async : false,
		dataType : 'json',
		success : function(obj) {
			$("#business_entity_own_tb").empty();
			var result = obj.data.business_categories;
			var total = obj.data.total;
			var checkAll = result.length;
			var htmlStr =  template('business_type_list_show',obj.data);
			$("[name='checkAllBut']").text("全部(" + checkAll + ")");
			$("#business_entity_own_tb").append(htmlStr);
			$("#business_entity_own_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: business_entity_own
			});
		}
	});
}

//添加商户类型
function submit_business_category() {
	var url = API_HOST + "/business_categories?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	var businesslist = [];
	 	$("input[name=inputnameitem]").each(function(){//遍历所有input元素，并且name=“Qty”
			var taskcontent=($(this).val());
			businesslist.push({'name':taskcontent});
		}); 
	$.ajax( {
		type : "POST",
		url : url,
		async : false,
		dataType : 'json',
		data : {
			"business_category" : businesslist
		},
		success : function(obj) {
			if(obj.status.message == 'Success'){
				business_check_info(obj.data.id,1)
			}
		}
	});
}

//编辑商户类型
function edit_business_type() {
	var business_check_id=$("[name='business_check_id']").val();
	var url = API_HOST + "/business_categories/"+ business_check_id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax( {
		type : "PUT",
		url : url,
		async : false,
		dataType : 'json',
		data : {
			"id" : business_check_id
		},
		success : function(obj) {
			alert("Data Saved: " + obj);
		}
	});
}
//删除商户类型
function delete__business_type() {
	var select_into=[];
	var htmlStr=$("[name='chooseID']:checked");
	htmlStr.each(function (){
		var business_id=$(this).attr("businessID");
		select_into.push({'id':business_id});
	});
	//var business_check_id=1;
	var url = API_HOST
			+ "/business_categories/"
			+ business_check_id
			+ "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	if (confirm("确认删除吗?")) {
		$.ajax( {
			type : "DELETE",
			url : url,
			async : false,
			dataType : 'json',
			success : function(obj) {
				if (obj.status.message == 'Success') {
					alert("Data Saved: " + obj);
				}
			}
		});
	}
}


//跳转商户类型
function to_business_category_check(business_check_id) {
	$(".container_right").load(
			'pages/business_category/check_business_type.html',
			function(response, status) {
				if (status == "success") {
					$("[name='business_check_id']").val(business_check_id);
					business_check_info(1);
				}
			});
}

//查看商户类型详细信息
function business_check_info(currentPage) {
	var business_check_id= $("[name='business_check_id']").val();
	var url = API_HOST
			+ "/business_categories/"
			+ business_check_id
			+ "/business_entities/?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page=" + per_page + "&page=" + currentPage;
	$.ajax({
		type : "GET",
		dataType : 'json',
		url : url,
		success : function(obj) {
			$("#check_business_type_tb").empty();
			var result = obj.data.business_entities;
			var total =  obj.data.total;
			var checkAll = result.length;
			var submitBut = 0, nosubmitBut = 0;
			var htmlStr = "";
			var creatorname = "";
			$.each(result,function(i, item) {
				creatorname = item.creator.name;
				if (!creatorname) {
					creatorname = "";
				}
				htmlStr += "<tr>"+ "<td style='text-align:center'><label><input type=\"checkbox\" name='subBox'></label></td>"
						+ "<td><a href=\"#\" onclick=\"to_business_entity_detail("+ item.id+ ","
						+ item.business_category.id+ ");\">"+ item.name+ "</a></td>"+ "<td>"
						+ item.business_category.name+ "</td>"+ "<td>"+ creatorname+ "</td>"
						+ "<td>"+ (result[i].publicly_visible == false ? '未发布'
								: '已发布') + "</td>"+ "</tr>";
				(result[i].publicly_visible == false ? nosubmitBut++
						: submitBut++);
				$("#catering").text(item.business_category.name)
			});
			$("[name='business_check_id']").val(business_check_id);
			$("[name='checkAllBut']").text("全部(" + checkAll + ")");
			$("[name='submitBut']").text("已发布(" + submitBut + ")");
			$("[name='nosubmitBut']").text("未发布(" + nosubmitBut + ")");
			$("#check_business_type_tb").append(htmlStr);
			$("#check_business_type_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: business_check_info
			});
		}
	});
}


//去商户信息页面
function to_business_entity_detail(id, business_category_id) {
	$(".container_right").load(
		'pages/business_entity/business_entity_check.html',
		function(response, status) {
			if (status == "success") {
				business_entity_detail(id, business_category_id);
		}
	});
}

//商户详细信息
function business_entity_detail(id, business_category_id, categoryDIV) {
	var url = API_HOST
			+ "/business_categories/"
			+ business_category_id
			+ "/business_entities/"
			+ id
			+ "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax( {
		type : "GET",
		dataType : 'json',
		url : url,
		success : function(obj) {
			var result = obj.data;
			$("[name='id']").val(id);
			$("[name='business_category_id']").val(business_category_id);
			$("#categoryDIV").text(
					$("#categoryDIV").data(
							"" + result.business_category.name + ""));
			$("#nameDIV").text(result.name);
			$("#addressDIV").text(result.address);
			$("#phoneDIV").text(result.phone);
		}
	});
}

//搜索商户类型
function search_business_type(currentPage)
{
	var url = SEARCH_API_HOST + "/business_categories/keywordSearch.ac";
	var search_business = $("[name='search_business']").val();
	$.ajax({
		type:"GET",
		async: false,
		url:url,
		dataType:"JSONP",
		jsonp: "callbackparam",
		data:{
			"keyword":search_business,
			"from":per_page*(currentPage-1),
			"size":per_page
			},
		success:function(obj)
		{
			$("#business_entity_own_tb").empty();
			var result = obj.data[0].result;
			var total = obj.data[0].total;
			var checkAll = result.length;
			var htmlStr = template('search_business_type_show',obj.data[0]);
			$("[name='checkAllBut']").text("全部(" + checkAll + ")");
			$("#business_entity_own_tb").append(htmlStr);
			$("#business_entity_own_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_business_type
			});
		}
	});
}

//搜索商户
function search_business_entity(currentPage) {
	var search_business_name = $("[name='search_business_name']").val();
	var business_check_id=$("[name='business_check_id']").val();
	var url=SEARCH_API_HOST + "/business/keywordSearch.ac";
	$.ajax({
		async: false,
   		type: "GET",
   		url: url,
		dataType: 'JSONP',
		jsonp: "callbackparam",
		data: {
			"keyword":search_business_name,
			"from":per_page*(currentPage-1),
			"size":per_page,
			"factor":"business_category_id must "+business_check_id+""
		},
		success: function(obj){
			$("#check_business_type_tb").empty();
			var res = obj.data[0].result;
			var total =  obj.data[0].total;
			var checkAll = res.length;
			var submitBut = 0, nosubmitBut = 0;
			var htmlStr = template('search_business_show',obj.data[0]);
			$("[name='checkAllBut']").text("全部(" + checkAll + ")");
			$("[name='submitBut']").text("已发布(" + submitBut + ")");
			$("[name='nosubmitBut']").text("未发布(" + nosubmitBut + ")");
			$("#check_business_type_tb").append(htmlStr);
			$("#check_business_type_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_business_entity
			});
   		}
	});	
}