//搜索 --小区
function search_community_center(currentPage) {
	var search_value = $("[name='search_value']").val();
	$.ajax({
   		type: "GET",
   		url: SEARCH_API_HOST + "/community_centers/keywordSearch.ac",
		async: false,
		dataType: 'JSONP',
		jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
		data:{
			"keyword":search_value,
			"from":per_page*(currentPage-1),
			"size":per_page
		},
		success: function(obj) {
			$("#community_center_list_tab").empty();
			var total = obj.data[0].total;
			var checkAll =  obj.data[0].result.length;
			var trStr = template('community_center_list_temp',obj.data[0]);
			$("[name='checkAllBut']").text("全部(" + checkAll + ")");
			$("[name='publishedBut']").text("已发布(0)");
			$("[name='unpublishedBut']").text("未发布(0)");
			$("#community_center_list_tab").append(trStr);
			$("#community_center_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_community_center
			});
   		}
	});	
}
//全部社区
function community_center_list(currentPage) {
	var url = API_HOST + "/community_centers?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page="+per_page+"&page=" + currentPage;	
	$.ajax_request("GET",url,null,function (obj){
		var total = obj.data.total;
		var checkAll = obj.data.community_centers.length;
		$("#community_center_list_tab").empty();
		var trStr = template('community_center_list_temp',obj.data);
		$("[name='checkAllBut']").text("全部(" + checkAll + ")");
		$("[name='publishedBut']").text("已发布(0)");
		$("[name='unpublishedBut']").text("未发布(0)");
		$("#community_center_list_tab").append(trStr);
		$("#community_center_page_div").paginate({
			total: total,//总数
			currentPage:currentPage,//当前页
			pageSize:per_page,
			callBack: community_center_list
		});	
	});
}
//点击查看信息
function to_show_community_center(type,centerID,centerName) {
	$(".container_right").load('pages/community_center/community_center_info.html',
		function (response,status) {
		 	if (status == "success") {	
				$("#active_li").text(centerName);
				$("[name='community_center_name']").val(centerName);
				$("[name='type']").val(type);
		 		show_community_center_info(centerID);
			}
		}); 
}
//community_center_info.html页面返回上一级
function community_center_details_back() {
	var type = $("[name='type']").val();
	var url='';
	if('all' == type){
		url='pages/community_center/community_center_all.html';
	}else if('attention' == type){
		url='pages/user_attention/attention_community/attention_community_all.html';
	}
	$(".container_right").load(url);
}
//查看社区信息 
 function show_community_center_info(centerID) {
	var url = API_HOST + "/community_centers/" + centerID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax_request("GET",url,null,function (obj){
		var mydata = obj.data;
		$("[name='community_center_id']").val(centerID);
		$("#centerName").text(mydata.name);
		$("#centerAddress").text(mydata.address);
		$("#estatePhone").text("目前没有值");
	});
 }
//去编辑社区页面
 function to_editor_community_center(){
	var centerID = $("[name='community_center_id']").val();
	var centerName = $("[name='community_center_name']").val();
	var type = $("[name='type']").val();
	$(".container_right").load('pages/community_center/community_center_redact.html',
		function (response,status) {
		 	if (status == "success") {
				$("#active_li").text(centerName);
				$("[name='community_center_id']").val(centerID);
				$("[name='community_center_name']").val(centerName);
				$("[name='type']").val(type);
		 		show_editor_community_center(centerID);
			}
		}); 
 }
//编辑社区页面
 function show_editor_community_center(centerID) {
	var url = API_HOST + "/community_centers/" + centerID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax_request("GET",url,null,function (obj){
		var mydata = obj.data;
		$("[name='name']").val(mydata.name);
		$("[name='address']").val(mydata.address);
		$("[name='estatePhone']").val("010 - 60245687");
	});
 }
 //更新小区信息
 function modify_community_center(url,submitType){
	var communityCenterId = $("[name='communityCenterId']").val();
	var name = $("[name='name']").val();//社区名称
	var address = $("[name='address']").val();//地址
	var wuye = $("[name='wuye']").val();//物业
	var juweihui = $("[name='juweihui']").val();//居委会
	//var estatePhone = $("[name='estatePhone']").val();//物业电话
	var latlng = $("[name='latlng']").val();
	if(!name){
		alert("请填写小区名称");
		return;
	}
	if(!latlng){
		alert("请为小区创建位置坐标");
		return ;
	}
	var data = {
			"id":communityCenterId,
			"name": name,
			"address":address,
			"wuye":wuye,
			"juweihui":juweihui,
			"latlng": latlng
	};
	$.ajax_request(submitType,url,data,function (obj){
		$.messager.alert(obj.msg);
		if(obj.code!=1){
			to_other_page('community_center/checkCommunityCenter.ac?id='+obj.id);
		}
	});
 }
//编辑社区信息
 function editor_community_center() {
	 var url = "community_center/update.ac";
		modify_community_center(url,"POST");	
 }
//提交社区信息
function submit_community_center() {
	var url = "community_center/save.ac";
	modify_community_center(url,"POST");	
}
 //删除小区信息
function delete_community_center () {
	var id = $("#communityCenterId").text();
	if(confirm("确认删除?")) {
		var url="community_center/delete.ac";
		var data ={
				"id":id,
		};
		$.ajax_request("get",url,data,function (obj){
			$.messager.alert(obj.msg);
			to_other_page('community_center/community_center_all.jhtml');
		});
	}	
}
