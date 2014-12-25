//加载我关注的用户列表
function load_atention_user_all(currentPage){
	var url = API_HOST + "/users?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&type=following_users&per_page=" + per_page + "&page=" + currentPage;
	$.ajax_request("GET",url,null,function (obj){
		$("#attention_user_tab").empty();
		var total = obj.data.total;
		var trStr = template('load_atention_user_all_temp',obj.data);
		$("#attention_user_tab").append(trStr);
		$("#attention_user_page_div").paginate({
			total: total,//总数
			currentPage:currentPage,//当前页
			pageSize:per_page,
			callBack: load_atention_user_all
		});
	});
}
//搜索用户
function search_atention_user(currentPage){
	var search_value = $("[name='search_value']").val();
	$.ajax({
   		type: "GET",
   		url: SEARCH_API_HOST+"/users/keywordSearch.ac",
		async: false,
		dataType: 'JSONP',
		jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
		data: {
			"keyword":search_value,
			"from":per_page*(currentPage-1),
			"size":per_page
		},
		success: function(obj) {
			$("#attention_user_tab").empty();
			var total = obj.data[0].total;	
			var trStr = template('load_atention_user_all_temp',obj.data[0]);
			$("#attention_user_tab").append(trStr);
			$("#attention_user_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_atention_user
			});
   		}
	});
}
//加载列表--我关注的商户
function load_atention_business(currentPage) {
	var url = API_HOST + "/business_entities?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&type=bookmarked&per_page=" + per_page + "&page=" + currentPage;
	$.ajax_request("GET",url,null,function (obj){
		$("#business_attention_tab").empty();
		var total =  obj.data.total;//数据总数
		var trStr = template('load_atention_business_temp',obj.data);
		$("#business_attention_tab").append(trStr);
		//添加分页
		$("#business_attention_page_div").paginate({
			total: total,//总数
			currentPage:currentPage,//当前页
			pageSize:per_page,
			callBack: load_atention_business
		});
	});
}
//关注的商户搜索
function search_atention_business (currentPage){
	var search_value = $("[name='search_value']").val();
	$.ajax({
   		type: "GET",
   		url: SEARCH_API_HOST+"/business/keywordSearch.ac",
		async: false,
		dataType: 'JSONP',
		jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
		data: {
			"keyword":search_value,
			"from":per_page*(currentPage-1),
			"size":per_page
		},
		success: function(obj){
			$("#business_attention_tab").empty();
			var total = obj.data[0].total;	
			var trStr = template('load_atention_business_temp',obj.data[0]);
			$("#business_attention_tab").append(trStr);	
			//添加分页
			$("#business_attention_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_atention_business
			});
   		}
	});	
}
//关注的社区
function load_atention_community(currentPage) {
	var url = API_HOST + "/community_centers?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&type=bookmarked&per_page=" + per_page + "&page=" + currentPage;
	$.ajax_request("GET",url,null,function (obj){
		$("#community_attention_tab").empty();
		var total =  obj.data.total;//数据总数
		var trStr = template('load_atention_community_temp',obj.data);
		$("#community_attention_tab").append(trStr);
		//添加分页
		$("#community_attention_page_div").paginate({
			total: total,//总数
			currentPage:currentPage,//当前页
			pageSize:per_page,
			callBack: load_atention_community
		});
	});
}
//小区搜索
function search_atention_community (currentPage){
	var search_value = $("[name='search_value']").val();
	$.ajax({
   		type: "GET",
   		url: SEARCH_API_HOST+"/community_centers/keywordSearch.ac",
		async: false,
		dataType: 'JSONP',
		jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
		data:{
			"keyword":search_value,
			"from":per_page*(currentPage-1),
			"size":per_page
		},
		success: function(obj) {
			$("#community_attention_tab").empty();
			var total = obj.data[0].total;	
			var trStr = template('load_atention_community_temp',obj.data[0]);
			$("#community_attention_tab").append(trStr);
			//添加分页
			$("#community_attention_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_atention_community
			});
   		}
	});	
}