//参与的活动
function load_attend_activity(currentPage) {
	var url = API_HOST + "/events?role=applicant&style=doc&access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page=" + per_page + "&page=" + currentPage;
	$.ajax_request("GET",url,null,function (obj){
		$("#attend_activity_tab").empty();
		var total = 15;
		var trStr = template('load_attend_activity_temp',obj.data);
		$("#attend_activity_tab").append(trStr);
		$("#attend_activity_page_div").paginate({
			total: total,//总数
			currentPage:currentPage,//当前页
			pageSize:per_page,
			callBack: load_attend_activity
		});
	});
}