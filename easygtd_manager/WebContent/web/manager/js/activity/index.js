/**
 * 加载活动列表
 * @param {Object} currentPage 当前页
 */
function activity_list(currentPage) {
	var url = API_HOST + "/events?style=doc&access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page=" + per_page + "&page=" + currentPage;
	$.ajax({
   		type: "GET",
   		url: url,
		async: false,
		dataType: 'json',
		success: function(obj) {
			$("#all_activity_list_tb").empty();
			var mydata = obj.data.events;
			var total = 25;// obj.data.total;
			var trStr = "";
			$.each(mydata,function(i, item) {	
				var d = new Date(item.start_time);
				var year = d.getFullYear();
				var month = (d.getMonth() + 1)>10?(d.getMonth() + 1):'0' + (d.getMonth() + 1);
				var day = d.getDate()>10?d.getDate():'0' + d.getDate();
           		trStr += "<tr>" +
							"<td style='text-align:center'><label><input type='checkbox' name='activity_name'></label></td>" +
							"<td><a href=\"#\" onclick=\"to_activity_detail_check('all',"
								+ item.id
								+ ");\">"
								+ item.title
								+ "</a></td>"+
							"<td>待更新</td>"+
							"<td>待更新</td>"+
							"<td>"+ item.address +"</td>"+
							"<td>"+ item.seat +"</td>"+
							"<td>" +year+ "-" + month + "-" + day + "</td>"+
							"<td>" +item.creator+ "</td>"+
						"</tr>";
        	});
			$("#all_activity_list_tb").append(trStr);
			$("#all_activity_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: activity_list
			});
   		}
	});	 

}
  //活动详情页面
 function to_activity_detail_check(type,activity_detail_id) {
 	$(".container_right").load('pages/activity/activity_details.html');
 	var url = API_HOST+"/events/" + activity_detail_id + "/?style=doc&access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
 	$.ajax({
 		type: "GET",
   		url: url,
		async: false,
		dataType: 'json',
		success: function(obj) {
			var result=obj.data;
			var creatlist=result.creator;
			$("[name='activity_detail_id']").val(activity_detail_id);
			$("[name='type']").val(type);
			$("#activity_name_id").text(result.title)
			$("#user_templet").text(result.banner);
			$("#current_community").text("待更新");
			$("#nameDIV").text(result.title);
			$("#addressDIV").text(result.address);
			var d = new Date(result.start_time);
			var year = d.getFullYear();
			var month = (d.getMonth() + 1)>=10?(d.getMonth() + 1):'0' + (d.getMonth() + 1);
			var day = d.getDate()>=10?d.getDate():'0' + d.getDate();
			$("#activitytimeDIV").text(year + '-' + month + '-' + day);
			$("#creatorDIV").text(creatlist.name);
			$("#sexDIV").text(result.banner);
			$("#phoneDIV").text(creatlist.phone_number);
			$("#createtimeDIV").text(result.created_at);
   		}
 	});
 }
 //返回上一级
 function to_all_activity() {
	var type = $("[name='type']").val();
	var url='';
	if('all' == type){
		url='pages/activity/all_activity.html';
	}else if('attend' == type){
		url='pages/attend_activity/attend_activity_all.html';
	}
	$(".container_right").load(url);
 }
 //重构方法
 function modify_activity (url,submitType){
	var user_templet=$("[name='user_templet']").val();
 	var current_community=$("[name='current_community']").val();
 	var title=$("[name='activity_name']").val();
 	var address=$("[name='address']").val();
 	var activitytimeDIV=$("[name='activity_time']").val();
	var type=$("[name='type']").val();
	$.ajax({
 	   	type: "" + submitType + "",
   		url: url,
		async: false,
		dataType: 'json',
		data:{
			 "event": {
		        "title": title,
		        "start_time": "2014-01-24T09:27:40+08:00",
		        "end_time": "2014-01-25T10:27:40+08:00",
		        "content": "",
		        "intro": "",
		        "status": 1,
		        "address": address,
		        "seat": 60,
		        "style": 2,
		        "banner": "/desktop/skating.img"
             }
		},
		success: function(obj) {
			if(obj.status.message == 'Success') {
				//去查看页面
				to_activity_detail_check(type,obj.data.id);
			}
   		}
 	})
 }
  //添加活动
 function creat_activity() {
 	var url = API_HOST + "/events?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
 	modify_activity(url,"POST");
}

 //跳转编辑页面并显示数据
function to_edit_activity() {
	$(".container_right").load('pages/activity/edit_activity.html');
	var activity_detail_id=$("[name='activity_detail_id']").val();
	var type=$("[name='type']").val();
 	var url = API_HOST + "/events/"+activity_detail_id+"/?style=doc&access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
 	$.ajax({
 		type: "GET",
   		url: url,
		async: false,
		dataType: 'json',
		success: function(obj) {
		 var result=obj.data;
		 var creatlist=result.creator;
 		 $("[name ='split_class']").val("待更新");
	     $("#inputName").val(result.title);
	     $("#inputAddress").val(result.address);
	     $("#activity_time").val("待更新");
	     $("[name='activity_detail_id']").val(activity_detail_id);
		 $("[name='type']").val(type);
   		}
 	})
}

//编辑活动
function submit_edit_activity() {
	var activity_detail_id=$("[name='activity_detail_id']").val();
 	var url = API_HOST+"/events/"+activity_detail_id+"/?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	modify_activity(url,"PUT");
}

//删除活动
function delete_activity() {
  	var activity_detail_id=$("[name='activity_detail_id']").val();
  	var url = API_HOST+"/events/"+activity_detail_id+"/?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
  	if (confirm("确认删除吗?")) {
		$.ajax({
			type:"DELETE",
			url:url,
			async:false,
			dataType: 'json',
			success: function(obj) {
				if(obj.status.message == 'Success') {
					$(".container_right").load('pages/activity/all_activity.html');
				}
			}
		});
	}

}

//跳转到基本资料（本页）
function to_detail() {
	var activity_detail_id=$("[name='activity_detail_id']").val();
	var type=$("[name='type']").val();
	to_activity_detail_check(type,activity_detail_id);
}

//跳转到参与的用户
function to_partake() {
	var activity_detail_id=$("[name='activity_detail_id']").val();
	var type=$("[name='type']").val();
	$(".container_right").load('pages/activity/partake_user.html',
		function (response,status) {
		 	if (status == "success"){
				$("[name='activity_detail_id']").val(activity_detail_id);
				$("[name='type']").val(type);
		}
	}); 
}

//跳转到关注的用户 
function to_concern()
{
	var activity_detail_id=$("[name='activity_detail_id']").val();
	var type=$("[name='type']").val();
	$(".container_right").load('pages/activity/concern_user.html',
		function (response,status) {
		 	if (status == "success"){
				$("[name='activity_detail_id']").val(activity_detail_id);
				$("[name='type']").val(type);
		}
	}); 
}


//关注的用户
function concern_user_list(activity_detail_id)
{
    var url=API_HOST+"/api/v1/users/"+activity_detail_id+"/follow";
    $.ajax({
    	type:"POST",
    	url:url,
    	async: false,
		dataType: 'json',
		success:function(obj)
		{

		}

    })
}

//全部图片
function all_picture() {
	var url=API_HOST+"/attachments?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax({
		type: "GET",
		url: url,
		async: false,
		dataType: 'json',
		success: function(obj) {
			
		}
	});
}

//活动搜索引擎
function search_activity(currentPage)
{
   var url = SEARCH_API_HOST + "/events/keywordSearch.ac";
   var search_activity_name=$("[name='search_activity_name']").val();
   $.ajax({
   	   type:"GET",
   	   url:url,
   	   async:false,
   	   dataType:"JSONP",
   	   jsonp: "callbackparam",
   	   data:{
   	   		"keyword":search_activity_name,
			"from":per_page*(currentPage-1),
			"size":per_page,
			"factor":"style must 2"
   	   },
   	   success:function(obj)
   	   {
   	   	$("#all_activity_list_tb").empty();
			var mydata = obj.data[0].result;
			var total = 25;// obj.data[0].total;
			var trStr = "";
			$.each(mydata,function(i, item) {	
				var d = new Date(item.created_at);
				var year = d.getFullYear();
				var month = (d.getMonth() + 1)>10?(d.getMonth() + 1):'0' + (d.getMonth() + 1);
				var day = d.getDate()>10?d.getDate():'0' + d.getDate();
           		trStr += "<tr>" +
							"<td style='text-align:center'><label><input type='checkbox' name='activity_name'></label></td>" +
							"<td><a href=\"#\" onclick=\"to_activity_detail_check('all',"
								+ item.id
								+ ");\">"
								+ item.title
								+ "</a></td>"+
							"<td>无数据</td>"+
							"<td>无数据</td>"+
							"<td>无数据</td>"+
							"<td>无数据</td>"+
							"<td>" +year+ "-" + month + "-" + day + "</td>"+
							"<td>无数据</td>"+
						"</tr>";
        	});
			$("#all_activity_list_tb").append(trStr);
			$("#all_activity_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: activity_list
			});
   	   }
   });

}
