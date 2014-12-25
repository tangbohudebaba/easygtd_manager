//普通操作服务地址
var API_HOST = "http://192.168.1.10/api/v1";
// 搜索服务地址
var SEARCH_API_HOST = "http://218.247.15.110:80/commcan_search/v1";
// 每页显示数量
var per_page = 25;
$(function() {
	$(".container_left ul li").click(function() {
		index = $(this).index();

		var url = $(this).attr('url');

		if (url != undefined && url.length > 0) {
			$(".container_right").load(url, function() {
				window.scrollTo(0, 0);// 滚动顶部
				console.log('load sucess');
			});
		} else {

			alert('</li>' + $(this).text() + '</li>--可能没有设置url属性哦');
		}

	});
	// 封装ajax
	$.ajax_request = function(method, url, data, callbackFun) {
		$.ajax({
			type : method,
			url : url,
			async : false,
			dataType : 'json',
			data : data,
			success : function(obj) {
				if (callbackFun) {
					callbackFun(obj);
				}
			}
		});
	};
});
// 跳转去其他页面
function to_other_page(url) {
	$(".container_right").load(url);
}
// 全选与反选
function checkBox(theEle, checkedID) {
	$("[name='" + checkedID + "']").prop("checked", theEle.checked);
}
// 格式化日期
template.helper('formatDate', function(str) {
	if (str) {
		var d = new Date(str);
		var year = d.getFullYear();
		var month = (d.getMonth() + 1) >= 10 ? (d.getMonth() + 1) : '0'
				+ (d.getMonth() + 1);
		var day = d.getDate() >= 10 ? d.getDate() : '0' + d.getDate();
		return year + '-' + month + '-' + day;
	}
	return "";
});

$.ajaxSetup({
	contentType : "application/x-www-form-urlencoded;charset=utf-8",
	cache : false,
	global : true,
	error : function(XHR, TS) {
		var resText = XHR.responseText;
		if(resText!=null||resText!=""){
			alert(resText);
			//window.open('login.jhtml', '_top');
		}else{
			alert("error!");
		}
	}
});
