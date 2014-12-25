//更新待办信息
function update_item_add() {
	var task_item_check_id = $("[name='task_item_check_id']").val();
	var url=API_HOST + "/tasks/"+task_item_check_id+"?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	var title = $('#inputTitle').val();
	var endTime = $('#endTime').val();
	var task_attributes=[];
	var tasklist = [];
	var ids = $("#treeDemo2Ul").children();
	$.each(ids, function(i, item) {
		var assignee_info=$(this).text();
		var id=$(this).attr("task_assign");
		var assignee_id=$(this).attr("id");
		alert(id)
		task_attributes.push({'id':id,'assignee_id':assignee_id,'assignee_name':assignee_info});
	});
	alert(task_attributes)
	$("input[name=inputTaskitem]").each(function(){
		var taskcontent=($(this).val());
		var itemid=$(this).attr("itemid");
		tasklist.push({'id':itemid,'content':taskcontent });
	});
	var name = $("input[name='optionsRadios']:checked").val();
	name == "立即发布" ? true : false;
	$.ajax( {
		type : "PUT",
		async : false,
		dataType : 'json',
		url : url,
		data : {
			"task" : {
				"subject" : title,
				"assigner_id":"2",
				"end_time" : endTime,
				"publicly_visible" : name,
				"task_items_attributes" : tasklist,
				"task_receipts_attributes" : task_attributes
				}
			},
		success : function(obj) {
			if(obj.status.message == 'Success'){
				to_task_item_check(task_item_check_id,title)
			}
		}
	});

}
//添加待办
function task_item_add() {
	var title = $('#inputTitle').val();
	var endTime = $('#endTime').val();
	var url=API_HOST + "/tasks?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	var tasklist = [];
	$("input[name=inputTaskitem]").each(function(){//遍历所有input元素，并且name=“Qty”
		var taskcontent=($(this).val());
		tasklist.push({'content':taskcontent });
	});
	var ids = $("#treeDemo2Ul").children();
	var list = [];
	$.each(ids, function(i, item) {
		var user_id = $(this).attr("id");
		list.push({'assignee_id':user_id});

	});
	$.ajax( {
		type : "POST",
		async : false,
		dataType : 'json',
		url : url,
		data : {
			"task" : {
				"subject": title,
				"assigner_id":"2",
				"end_time":endTime,
				"task_items_attributes":tasklist,
				"task_receipts_attributes":list
			}
	    },
		success : function(obj) {
			if(obj.status.message == 'Success'){
				to_task_item_check(obj.data.id,obj.data.subject);
			}
		}
	});
}
//搜索用户
function searchUser() {
	var searchV = $("#searchable").val();
	var childrenNode = $("#treeDemoUl").children();
	if (!!searchV) {
		childrenNode.each(function() {
			var find = $(this).text().toLowerCase().indexOf(searchV);
			if (find == -1) {
				$(this).remove();
			}
		});
	} 
	else {
		show_All_User("treeDemoUl");
	}
}
//显示全部用户
function show_All_User(treeDemoUl) {
	var url = API_HOST + "/users?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax({
		type : "GET",
		url : url,
		async: false,
		dataType : 'json',
		success : function(obj) {
			$("#" + treeDemoUl + "").empty();
			var result = obj.data.users;
			console.log(result)
			var htmlStr = "";
			for ( var i = 0; i < result.length; i++) {
				if (result[i].name != null && result[i].name != "")
					htmlStr += "<li id=" + result[i].id + "  name='user_name'>"
							+ result[i].name + "</li>";
			}
			$("#" + treeDemoUl + "").append(htmlStr);
		}
	});
}
//用户全部清空
function clean_check_all() {
	var childNode = $("#treeDemo2Ul").children();
	$("#treeDemo2Ul").children().remove();
	childNode.prependTo("#treeDemoUl");
	$(".deleteCss").remove();
}
//用户全部选择
function choose_check_all() {
	var childNode = $("#treeDemoUl").children();
	if (childNode.length > 0 && 'checkCss' == childNode.className) {
		$(childNode).removeClass("checkCss");
	} else {
		$(childNode).addClass("checkCss");
	}
}
//去查看待办详细页
function to_task_item_check(task_item_check_id,task_name) {
	$(".container_right").load('pages/task_item/task_item_check.html',
		function(response, status) {
			if (status == "success") {
				$("[name='task_item_check_id']").val(task_item_check_id);
				$("[name='task_name']").val(task_name);
				$("#checked_name").text(task_name);
				task_item_check_info(task_item_check_id);
			}
	});
}
//查看待办
function task_item_check_info(task_item_check_id) {
	var url= API_HOST + "/tasks/" + task_item_check_id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax( {
		type : "GET",
		url :url,
		dataType : 'json',
		success : function(obj) {
			var result = obj.data;
			var task_receipts = result.task_receipts;
			var task_item=result.task_items;
			var htmlStr = "";
			var htmltext="";
			$.each(task_receipts, function(i, item) {
				htmlStr += "<tr id='assigner_list'>" + 
								"<td id=" + result.assigner_id + ">" + item.assignee_name + "</td>"+ 
								"<td>暂无数据</td>" +
							"<tr/>";
			});
			$.each(task_item,function(i,c){
			  htmltext  += "<tr style='width:100%' id="+c.id+" name='editname'><td>"+c.content+"</td><tr/>";
			});
			$("#task_item_check_tb").append(htmlStr);
			$("#contentDiv").append(htmltext);
			$("#submitdiv").text(result.assigner_name)
			$("#titleDiv").text(result.subject);
			var d = new Date(result.end_time);
			var year = d.getFullYear();
			var month = (d.getMonth() + 1)>=10?(d.getMonth() + 1):'0' + (d.getMonth() + 1);
			var day = d.getDate()>=10?d.getDate():'0' + d.getDate();
			$("#dateDiv").text( year + '-' + month + '-' + day);
		}
	});
}
//去编辑页面
function to_edit_task_item (){
	var task_item_check_id = $("[name='task_item_check_id']").val();
	var task_name = $("[name='task_name']").val();
	$(".container_right").load('pages/task_item/task_item_edit.html',
		function(response, status) {
			if (status == "success") {
				$("[name='task_item_check_id']").val(task_item_check_id);
				$("[name='task_name']").val(task_name);
				$("#checked_update_name").text(task_name);
				edit_task_item(task_item_check_id);
			}
	});
}
//编辑页面查询用户信息
function edit_All_User(treeDemo2Ul, treeDemoUl,task_item_check_id) {
	//选中用户
	var url = API_HOST + "/tasks/"+ task_item_check_id+ "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	var assigneelist, allList;
	var arr = [];
	$.ajax({
		type : "GET",
		url : url,
		dataType : 'json',
		success : function(obj) {
			$("#" + treeDemo2Ul).empty();
			var assigneelist = obj.data.task_receipts;
			var htmlStr = "";
			$.each(assigneelist,function(i,item) {
				htmlStr += "<li id=" + item.assignee_id + " task_assign=" + item.id + " name='assign_name'>"+ item.assignee_name + "<div class=\"deleteCss\"></div></li>";
				arr.push(item.assignee_id);
			});
			$("#" + treeDemo2Ul).append(htmlStr);
		}
	});
	var url1 =API_HOST + "/users?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	//可选用户·
	$.ajax({
		type : "GET",
		url : url1,
		async : false,
		dataType : 'json',
		success : function(obj) {
			$("#" + treeDemoUl + "").empty();
			var result = obj.data.users;
			var htmlStr = "";
			for ( var i = 0; i < result.length; i++) {
				if (!arrayindex(arr, result[i].id)) {
					htmlStr += "<li id=" + result[i].id + " name='user_name' onclick=\"clickNode("+ result[i].id + ")\">" + result[i].name + "</li>";
				}
			}
			$("#" + treeDemoUl + "").append(htmlStr);
		}
	});
}
function arrayindex(arr, x) {
	for ( var i = 0; i < arr.length; i++) {
		if (arr[i] == x) {
			return true;
		}
	}
	return false;
}
//编辑页面
function edit_task_item(task_item_check_id) {
	edit_All_User('treeDemo2Ul','treeDemoUl',task_item_check_id);
	var url=API_HOST + "/tasks/"+ task_item_check_id+ "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";	
	$.ajax( {
		type : "GET",
		url : url,
		dataType : 'json',
		success : function(obj) {
			var result = obj.data;
			// var task_receipts = result.task_receipts;
			var taskadd=result.task_items;
			var htmlStr = "";
			var htmltext="";
			$.each(taskadd,function(i,c){
				htmltext+="<div class='divinclude'><input type='text' class='form-control' itemid='"+c.id+"' name='inputTaskitem' placeholder='点击新的任务项' value='"+c.content+"'><span class='glyphicon glyphicon-remove deletelist'  name='taskspan' id='"+c.id+"' onclick='delete_task_content("+c.id+");'></span><span class='glyphicon glyphicon-th-list addlist'  name='addspan'></span></div>";
			});
			$("#write").append(htmltext);
			//给inputTaskitem 添加click事件
			$("[name='inputTaskitem']").click(eventClick);
			$("#task_item_check_tb").append(htmlStr);
			$("#inputTitle").val(result.subject);
			var d = new Date(result.end_time);
			var year = d.getFullYear();
			var month = (d.getMonth() + 1)>=10?(d.getMonth() + 1):'0' + (d.getMonth() + 1);
			var day = d.getDate()>=10?d.getDate():'0' + d.getDate();
			$("#endTime").val(year + '-' + month + '-' + day);
		}
	});
}
//编辑页面取消
function cancle_edit_task (){
	var task_item_check_id = $("[name='task_item_check_id']").val();
	var task_name = $("[name='task_name']").val();
	to_task_item_check(task_item_check_id,task_name);
}
//添加任务事件
function eventClick(){   
	$('#write').append('<div class="divinclude"><input type="text" class="form-control"  name="inputTaskitem"><span class="glyphicon glyphicon-remove deletelist"  name="taskspan"></span><span class="glyphicon glyphicon-th-list addlist"  name="addspan"></span></div>');
	var taskitem=$($("[name='inputTaskitem']")[$("[name='inputTaskitem']").length-1]);
	taskitem.click(eventClick);
	var uu = taskitem.parent('.divinclude');
	$('.deletelist',uu).click(function(){
		$(this).parent(".divinclude").remove();
		taskitem.click(eventClick);
	});
};
//删除待办信息
function delete_task_item() {
	var task_item_check_id = $("[name='task_item_check_id']").val();
	alert(task_item_check_id)
	var url = API_HOST + "/tasks/"+ task_item_check_id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	if (confirm("确认删除吗?")) {
		$.ajax({
			type : "DELETE",
			url : url,
			async : false,
			dataType : 'json',
			success : function(obj) {
				if (obj.status.message == 'Success') {
					$(".container_right").load('pages/task/all_pending.html');
				}
			}
		});
	}
}
//点击左边的节点，右边
function clickNode(thisID){
	var parentID=$("#"+thisID).parent().attr('id');//得到当前父级ID
	var classStr=$("#"+thisID).attr("class")
	if(classStr){
		$("#"+thisID).removeClass("checkCss");
	}else {
		$("#"+thisID).addClass("checkCss");
	}
}
//左右选择
function clickSome(treeDemoUl,treeDemo2Ul){
	var childrenNode=$("#"+treeDemoUl).children();
	if(childrenNode.length>0){	
		for(var i=0;i<childrenNode.length;i++){
			var childNode=childrenNode[i];
			if('checkCss'==childNode.className){
				$(childNode).removeClass("checkCss");
				$(childNode).prependTo("#"+treeDemo2Ul)
				var oDiv = document.createElement('div');
				oDiv.className = 'deleteCss';
				childNode.appendChild(oDiv);
			}	
		}
	}
	
}
//删除任务
function deleteNode(obj){
	if(obj.className == 'deleteCss'){
		$(obj).parent('li').attr("name","user_name");
		var task_assign=$(obj).parent('li').attr("task_assign");
		if(task_assign){//去后台删除人的时候用
			
		}
		$(obj).parent('li').removeAttr("task_assign");
		$(obj).parent('li').removeClass("checkCss");
		$('#treeDemoUl').prepend($(obj).parent('li'));
		$("[name='user_name']").click(function (){
			clickNode($(this).attr("id"));
		});
		$(obj).remove();
	}
};
//现在发布
function submit_task_item() {
	var task_item_check_id = $("[name='task_item_check_id']").val();
	var url = API_HOST +"/tasks/"+ task_item_check_id+ "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	$.ajax({
		type : "PUT",
		url : url,
		async : false,
		dataType : 'json',
		success : function(obj) {
			alert(obj.status.message)
		}
	});
}
//删除任务项
function delete_task_content(item_id)
{
	var task_item_check_id = $("[name='task_item_check_id']").val();
	var url = API_HOST + "/tasks/" + task_item_check_id + "?access_token=e3dc2b955021073a11568d818286c9d5e29a7ecc69bc29b6cedb85c96cf0c1f5";
	if (confirm("确认删除吗?")) {
		$.ajax({
			type:"put",
			url:url,
			async:false,
			dataType:'json',
			data:{
				   "task": 
  				    {
			        	"task_items_attributes": [
			            {
			                "id": item_id,
			                "_destroy":true
			            }
			        ]
			    }
			},
			success:function(obj){
				if (obj.status.message == 'Success') {
					var taskitem=$($("[name='inputTaskitem']")[$("[name='inputTaskitem']").length-1]);
					var parentDiv = taskitem.parent('.divinclude');
					parentDiv.remove();
				
				}
			}

		});

	}
}