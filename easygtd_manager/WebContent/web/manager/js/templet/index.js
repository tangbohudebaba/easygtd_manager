//模版搜索
function search_templet(currentPage) {
	var search_value = $("[name='search_value']").val();
	$.ajax({
   		type: "GET",
   		url: SEARCH_API_HOST + "/events/keywordSearch.ac",
		dataType: 'JSONP',
		async: false,
		jsonp: "callbackparam",//服务端用于接收callback调用的function名的参数
		 data: {
			"keyword":search_value,
			"from":per_page*(currentPage-1),
			"size":per_page,
			"factor":"style must 1"
		}, 
		success: function(obj) {
			$("#templet_manage_tab").empty();
			var total = obj.data[0].total;	
			var trStr = template('load_templet_list_temp',obj.data[0]);
			$("#templet_manage_tab").append(trStr);
			$("#templet_manage_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: search_templet
			}); 
   		}
	});
}
//模版列表
function load_templet_list(currentPage) {
	$.ajax({
   		type: "GET",
   		url: API_HOST + "/events?style=dot&access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5&per_page=" + per_page + "&page=" + currentPage,
		async: false,
		dataType: 'json',
		success: function(obj) {
			$("#templet_manage_tab").empty();
			var total = 25;
			var trStr = template('load_templet_list_temp',obj.data);
			$("#templet_manage_tab").append(trStr);
			$("#templet_manage_page_div").paginate({
				total: total,//总数
				currentPage:currentPage,//当前页
				pageSize:per_page,
				callBack: load_templet_list
			});
   		}
	});	
}
//更新模版信息
function modify_templet(url,type) {
	var banner='/images/img.png';
 	var templet_name=$("[name='templet_name']").val();
	var templet_type=$("[name='templet_type']").val();
	var seat=$("[name='seat']").val();
	var smallage=$("[name='smallage']").val();
	var maxage=$("[name='maxage']").val();
	var content=$("[name='content']").val();
	var material=$("[name='material']").val();
	var intro=$("[name='intro']").val();
	$.ajax({
 		type: "" + type + "",
   		url: url,
		async: false,
		dataType: 'json',
		data:{
			 "event": {
		        "title": templet_name,
		        "start_time": "2014-01-24T09:27:40+08:00",
		        "end_time": "2014-01-25T10:27:40+08:00",
		        "content": content,
		        "intro": intro,
		        "status": 1,
		        "address": "西单",
		        "seat": seat,
		        "style": 1,
		        "banner": banner
             }
		},
		success: function(obj) {
			if(obj.status.message == 'Success') {
				//去查看页面
				to_show_templet(obj.data.id,obj.data.title);
			}
   		}
 	});
}
//创建模版
function submit_temple(){
 	var url= API_HOST + "/events?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
 	modify_templet(url,"POST");
}
//查看模版信息
function to_show_templet(templetID,temoletName){
	$(".container_right").load('pages/templet/check_templet.html',
		function (response,status) {
		 	if (status == "success"){
				$("[name='templetID']").val(templetID);
				$("[name='templetName']").val(temoletName);
				$(".breadcrumb .active").text(temoletName);
		 		show_templet_info(templetID);
		}
	}); 
}
//模版详细
function show_templet_info(templetID){
	$.ajax({
   		type: "GET",
		url: API_HOST + "/events/" + templetID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5",
   		dataType: 'json',
   		async: false,
  		success: function(obj) {
			var mydata = obj.data;
			$("#title").text(mydata.title);
			$("[name='templet_type'] option[value='" + mydata.style + "']").attr("selected",true); 
			$("#templet_type").text(mydata.style==1?'运动':(mydata.style==2?'休闲':'娱乐'));
			$("#seat").text(mydata.seat);
			$("#age").text("暂无数据");
			$("#content").text(mydata.content);
			$("#material").text("暂无数据");
			$("#intro").text(mydata.intro);
   		}
	});	
}
//去编辑模版
function to_edit_templet() {
	var templetID = $("[name='templetID']").val();
	var templetName = $("[name='templetName']").val();
	$(".container_right").load('pages/templet/compile_templet.html',
		function (response,status) {
		 	if (status == "success") {
				$("[name='templetID']").val(templetID);
				$("[name='templetName']").val(templetName);
				$(".breadcrumb .active").text(templetName);
		 		to_update_templet(templetID);
		}
	}); 
}
//去模版更新页面
function to_update_templet(templetID) {
	$.ajax({
   		type: "GET",
		url: API_HOST + "/events/" + templetID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5",
   		dataType: 'json',
   		async: false,
  		success: function(obj) {
			var mydata = obj.data;
			$("[name='templet_name']").val(mydata.title);
			$("[name='templet_type'] option[value='" + mydata.style + "']").attr("selected",true); 
			$("[name='seat'] option[value='" +mydata.seat+ "']").attr("selected",true); 
			$("[name='smallage']").val("1");
			$("[name='maxage']").val("10");
			$("[name='content']").val(mydata.content);
			$("[name='material']").val("暂无数据");
			$("[name='intro']").val(mydata.intro);
			//给material 添加click事件
			$("[name='material']").click(eventClick);
   		}
	});		
}
//更新页面取消
function to_check_page() {
	var templetID = $("[name='templetID']").val();
	var templetName = $("[name='templetName']").val();
	to_show_templet(templetID,templetName);
}
//修改模版信息
function  update_temple(){
	var templetID = $("[name='templetID']").val();
 	var url= API_HOST + "/events/" + templetID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
 	modify_templet(url,"PUT");
}
/**
 * 删除模版
 */
function delete_templet() {
	var templetID = $("[name='templetID']").val();
	var url = API_HOST + "/events/" + templetID + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	if(confirm("确认删除?")) {
		$.ajax({
   			type: "DELETE",
   			url: url,
   			dataType: 'json',
   			async: false,
  			success: function(obj) {
				if(obj.status.message == 'Success') {
					$(".container_right").load('pages/templet/templet_manage.html');
				}
   			}
		});	
	}			
}




  //图片上传预览
function previewImage(file)
{
    var MAXWIDTH  = 260;
    var MAXHEIGHT = 180;
    var div = document.getElementById('choose_photo');
    
	if (file.files && file.files[0])
	{
        div.innerHTML ='<img id=imghead>';
        var img = document.getElementById('imghead');
        img.onload = function(){
        var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
        img.width  =  rect.width;
        img.height =  rect.height;
//      img.style.marginLeft = rect.left+'px';
        img.style.marginTop = rect.top+'px';
	}

    var reader = new FileReader();
    reader.onload = function(evt){img.src = evt.target.result;}
    reader.readAsDataURL(file.files[0]);
   }else //兼容IE
   {
    var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
    file.select();
    var src = document.selection.createRange().text;
    div.innerHTML = '<img id=imghead>';
    var img = document.getElementById('imghead');
    img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
    var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
    status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
    div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
  }
}

function clacImgZoomParam( maxWidth, maxHeight, width, height ){
	var param = {top:0, left:0, width:width, height:height};
     
     if( width>maxWidth || height>maxHeight )
    {
        rateWidth = width / maxWidth;
        rateHeight = height / maxHeight;
         
	    if( rateWidth > rateHeight )
	        {
	            param.width =  maxWidth;
	            param.height = Math.round(height / rateWidth);
	        }else
	        {
	            param.width = Math.round(width / rateHeight);
	            param.height = maxHeight;
	        }
	    }
	     
    param.left = Math.round((maxWidth - param.width) / 2);
    param.top = Math.round((maxHeight - param.height) / 2);
    return param;
}

//上传图片
 function create_photo(){
 	var url= "" + API_HOST + "/attachments";
 	$.ajax({
 		type:"GET",
 		url: url,
   		dataType: 'json',
   		async: false,
   		data:{
   			"attachment": 
		    {
			 	"file":"/Users/gk/Downloads/test_image.jpg"
		    }
   		},
   		success:function(obj) {
   			alert(obj+"!!!!!!")
   		}

 	})
 }

 //添加任务事件
function eventClick(){   
	$('#write').append('<div class="divinclude"><input type="text" class="form-control"  name="material"><span class="glyphicon glyphicon-remove deletelist"  name="taskspan"></span><span class="glyphicon glyphicon-th-list addlist"  name="addspan"></span></div>');
	var taskitem=$($("[name='material']")[$("[name='material']").length-1]);
	taskitem.click(eventClick);
	var uu = taskitem.parent('.divinclude');
	$('.deletelist',uu).click(function(){
		$(this).parent(".divinclude").remove();
		taskitem.click(eventClick);
	});
};
