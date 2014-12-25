var maxPage=0;//总页数
var pageNum=10;//分页连接显示数
var callBackFun;//回调函数
var pager;//加载分页的对象
var pageArr;//页码数组
//分页插件
$.fn.paginate = function(options) {	
	// 将defaults 和 options 参数合并到{}
	var opts = $.extend({},$.fn.paginate.defaults,options);
	var currentPage = opts.currentPage;//当前页
	pager = $(this);//加载分页的对象
	pageNum = opts.pageNum;//每页显示数量
	callBackFun = opts.callBack;//回调函数
	if(opts.total>0) {//根据总数和每页显示数量计算总共页面
		maxPage = Math.ceil(opts.total / opts.pageSize);
		//设置分页
		setPager(currentPage);
	}
}
//首页 尾页
var toFirstorEndPage = function (curIndex) {
	if (callBackFun) {
		callBackFun(curIndex);
	}	
}
//上一页
var toPrePage = function (curIndex) {
	if (curIndex > 1) {
		curIndex --;
	}
	if (callBackFun) {
		callBackFun(curIndex);
	}
}
//下一页
var toNextPage = function (curIndex) {
	if (curIndex < pageArr[pageArr.length - 1]) {
		curIndex++;
	}
	if (callBackFun) {
		callBackFun(curIndex);
	}	
}
//中间页跳转
var toCurPage = function (curIndex) {
	if (callBackFun) {
		callBackFun(curIndex);
	}
}
//跳转到某页
var jumpPageFun =function () {
	var jumpPage=document.getElementById("jumpPage").value;
	var page = maxPage;
	var reg =/^[0-9]*[1-9][0-9]*$/;
	if(reg.test(jumpPage)){
		if(0 < jumpPage && pageArr.length >= jumpPage){
			page = jumpPage;		
		}
		if (callBackFun) {
			callBackFun(page);
		}
	}else{
		alert("请输入正整数！")
	}	
}
//设置分页
var setPager = function (curIndex) {
	var begin = 1;
	pageArr = new Array();
	if ( maxPage > pageNum ) {
		if(curIndex > 5){
			begin = curIndex - 5;
		}
	}
	for (var i = begin; i <= maxPage && i <begin + pageNum; i++) {
		pageArr.push(i);
	}
	var toFirstStr = "<ul class='paginate_ul'><li onclick='toFirstorEndPage(1);'>首页</li>";
	var toPreStr = "<li onclick='toPrePage(" + curIndex + ");'>上一页</li>";
	var toNextStr = "<li onclick='toNextPage(" +curIndex + ");'>下一页</li>";
	var toEndStr = "<li onclick='toFirstorEndPage(" + maxPage + ");'>尾页</li>";
	var jumpToStr= "<li class='jump_li'><input type='text' id='jumpPage' class='jump_text'>"+
					   "<input type='button' class='jump_button'  value='GO' onclick='jumpPageFun();'></li></ul>";
	var cenStr = "";
	for (var i = 0; i < pageArr.length; i++) {
		if (curIndex == pageArr[i]) {
			cenStr += "<li class='paginate_active_link' onclick='toCurPage(" + pageArr[i] + ");'>" + pageArr[i] + "</li>";
		} else {
			cenStr += "<li onclick='toCurPage(" + pageArr[i] + ");'>" + pageArr[i] + "</li>";
		}
	}
	pager.empty();
	pager.append(toFirstStr + toPreStr + cenStr + toNextStr + toEndStr + jumpToStr);
}
//定义默认
$.fn.paginate.defaults = {
	total:0,//总数
	pageNum:10,//分页链接接数(10个)
	pageSize:25,//每页显示数量
	currentPage:1,//当前页 	
	callBack: function(){
	}//回调函数
};