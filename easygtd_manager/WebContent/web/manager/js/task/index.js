
//全部待办列表
function all_pending_list(currentPage) {	
	var url = API_HOST + "/tasks?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page=" + per_page + "&page=" + currentPage;
	$.ajax({
		type: "GET",
		url: url,
		async:false,
		dataType:'json',
		success: function(obj){
			$("#all_pending_tb").empty();
			var result = obj.data.tasks;
			var total = obj.data.total;
			var checkAllBtn = result.length;
			var publish = 0,unpublish = 0;
			var htmlStr = template('task_list_show',obj.data);
			$("[name='checkAllBtn']").text("全部(" + checkAllBtn + ")");
			$("[name='publishBtn']").text("已发布(" + publish + ")");
			$("[name='unpublishBtn']").text("暂不发布(" + unpublish + ")");
			$("#all_pending_tb").append(htmlStr);
			$("#all_pending_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: all_pending_list
			});
		}
	});
}

//待办搜索引擎
function search_task(currentPage)
{
	var url=SEARCH_API_HOST+"/tasks/keywordSearch.ac";
	var search_task_name=$("[name='search_task_name']").val();
	$.ajax({
		type:"GET",
		async: false,
		url:url,
		dataType:"JSONP",
		jsonp: "callbackparam",
		data:{
			"keyword":search_task_name,
			"from":per_page*(currentPage-1),
			"size":per_page
		},
		success:function(obj)
		{
			$("#all_pending_tb").empty();
			var result = obj.data[0].result;
			var total = obj.data[0].total;
			var checkAllBtn = result.length;
			var publish = 0,unpublish = 0;
			var htmlStr = template('search_task_list_show',obj.data[0]);
			$("[name='checkAllBtn']").text("全部(" + checkAllBtn + ")");
			$("[name='publishBtn']").text("已发布(" + publish + ")");
			$("[name='unpublishBtn']").text("暂不发布(" + unpublish + ")");
			$("#all_pending_tb").append(htmlStr);
			$("#all_pending_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_task
			});
		}
	});
}