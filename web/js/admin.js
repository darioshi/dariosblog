/* 
* 获取ajax处理对象		
 * @returns {xmlhttp}
 */

function getXHR(){	
	var xmlhttp;
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;	
}
/*
 *发送给服务器
 */
function sendURL(url){	
	// 获取ajax
	var xmlhttp = getXHR();		
	xmlhttp.onreadystatechange = function() {
//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			//这里可以写 服务器返回解结果后的处理
//		}
	}
	xmlhttp.open("POST", url, true);
	xmlhttp.send();		
}

/**
 * 删除文章
 * @param article_id
 */
function delete_article(hod , article_id){
	//remove 视图
	var recorder = hod.parentNode.parentNode.parentNode;	
	var recorder_parent = recorder.parentNode;
	recorder_parent.removeChild(recorder);
	//send
	var url = "/Blog/AdminDataServlet?op=delete_article"+"&&article_id="+article_id;
	sendURL(url);
}

/**
 * 删除sort
 * @param hod
 * @param sort_id
 */
function delet_sort(hod,sort_id) {
    if (confirm("该分类下的所有文章将删除，确定删除吗？")) {
        //后台删除
        var url = "/Blog/AdminServlet?op=sort_delete" + "&sort_id=" + sort_id;
        var xmlhttp = getXHR();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var obj = JSON.parse(xmlhttp.response);
                if (obj.code == 1) {
                    //remove 视图
                    var recorder = hod.parentNode.parentNode.parentNode;
                    var recorder_parent = recorder.parentNode;
                    recorder_parent.removeChild(recorder);
                } else {
                    alert("删除失败");
                }
            }
        }
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
    }
}

/**
 * hod 点击的span参数
 * sort 分类名字
 * @param hod
 * @param sort
 */

var input; //保存input
var temp;//保存input的上一个值
function edit_sort(hod,sort_id){
	
	if(hod.innerHTML == "编辑"){
	
		input = document.getElementById("sort_"+sort_id);
		//保存input的原始值
		temp = input.value;					
		//允许input可以进行编辑
		input.removeAttribute("disabled");			
		
		//实现input的光标定位
		input.value="";	
		input.focus();
		input.value=temp;					
		//修改hod内容为保存		
		hod.innerHTML="保存";			
	}else{		
		//点击了保存
		hod.innerHTML="编辑";
        input = document.getElementById("sort_"+sort_id);
		if(input.value == temp) {
            input.setAttribute("disabled","disabled");
            return;
        }
		//提交修改请求
		var url = "/Blog/AdminServlet?op=sort_update"+"&sort_id="+sort_id+"&sort_name="+input.value ;
        var xmlhttp = getXHR();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var obj = JSON.parse(xmlhttp.response);
                if(obj.code == 1) {
                    //remove 视图
                    input.setAttribute("disabled","disabled");
                }else {
                    alert("更新失败");
                }
            }
        }
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
	}
}


/**
 * 删除tag
 * @param hod
 * @param tag_id
 */
function delet_tag(hod,tag_id){
    if (confirm("确定删除该标签吗？")) {
        //后台删除
        var url = "/Blog/AdminServlet?op=tag_delete" + "&tag_id=" + tag_id;
        var xmlhttp = getXHR();
        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var obj = JSON.parse(xmlhttp.response);
                if (obj.code == 1) {
                    //remove 视图
                    var recorder = hod.parentNode.parentNode.parentNode;
                    var recorder_parent = recorder.parentNode;
                    recorder_parent.removeChild(recorder);
                } else {
                    alert("删除失败");
                }
            }
        }
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
    }
}


/**
 * hod 点击的span参数
 * tag  标签名字
 * @param hod
 * @param sort
 */

var input_t; //保存input
var temp_t;//保存input的上一个值
function edit_tag(hod,tag_id){
	
	if(hod.innerHTML == "编辑"){

        input_t = document.getElementById("tag_"+tag_id);
		//保存input的原始值
		temp_t = input_t.value;					
		//允许input可以进行编辑
		input_t.removeAttribute("disabled");			
		
		//实现input的光标定位
		input_t.value="";	
		input_t.focus();
		input_t.value=temp_t;					
		//修改hod内容为保存		
		hod.innerHTML="保存";			
	}else{
        //点击了保存
        hod.innerHTML="编辑";
        input_t = document.getElementById("tag_"+tag_id);
        if(input_t.value == temp_t) {
            input_t.setAttribute("disabled","disabled");
            return;
        }
        //提交修改请求
        var url = "/Blog/AdminServlet?op=tag_update"+"&tag_id="+tag_id+"&tag_name="+input_t.value ;
        var xmlhttp = getXHR();
        xmlhttp.onreadystatechange = function() {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
                var obj = JSON.parse(xmlhttp.response);
                if(obj.code == 1) {
                    //remove 视图
                    input_t.setAttribute("disabled","disabled");
                }else {
                    alert("更新失败");
                }
            }
        }
        xmlhttp.open("POST", url, true);
        xmlhttp.send();
	}
}