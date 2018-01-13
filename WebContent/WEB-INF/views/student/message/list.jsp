<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Message Board</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/asset/js/jquery-ui/js/jquery-1.10.2.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/asset/js/common.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/asset/js/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
<script
	src="${pageContext.request.contextPath }/asset/js/jquery-ui/js/jquery-ui-1.10.4.custom.min.js"></script>
<style type="text/css">
* {
	margin: 0;
	padding: 0;
	font-size: 14px
}

.header {
	height: 60px;
	line-height: 60px;
	font-size: 22px;
	font-weight: bold;
	margin-left: 20px;
}

.search input {
	height: 24px;
	font-size: 16px;
}

.search a {
	text-decoration: none;
	cursor: pointer;
	display: inline-block;
	height: 30px;
	width: 80px;
	margin-left: 20px;
	background-color: #e2e2e2;
	text-align: center;
	line-height: 30px;
	color: #222;
	border: 1px solid #e2e2e2;
}

.search a:hover {
	background-color: #c2c2c2;
}

a {
	text-decoration: none;
}

ul {
	list-style: none;
	margin-left: 20px;
	width: 80%;
	border-top: 1px solid #eee;
	border-bottom: 1px solid #eee;
}

ul li {
	background-color: #eee;
	margin-top: 10px;
	width: 100%;
	border-radius: 5px;
	border: 3px solid #eee;
}

ul li div {
	
}

ul li div span {
	font-size: 12px;
	display: inline-block;
	margin-left: 10px;
}

ul li div a {
	display: inline-block;
	text-align: center;
	margin-left: 50px;
	color: blue;
	cursor: pointer;
}

ul li div a:hover {
	color: red;
}

.page-div {
	width: 80%;
	padding-right: 10px;
	text-align: right;
	margin-top: 5px;
}

.page-div a {
	color: blue;
	cursor: pointer;
}

.page-div a:hover {
	color: red;
	text-decoration: underline;
}

.read {
	background-color: #f2f2f2;
	color: #000;
}

.message-header {
	height: 20px;
	line-height: 20px;
	width: inherit;
	border-bottom: 1px solid #eee;
}

.message-footer {
	height: 20px;
	line-height: 20px;
	width: inherit;
	border-top: 1px solid #eee;
}

ul li .message-context {
	background-color: #fff;
	padding: 10px 10px;
}

.board {
	width: inherit;
	margin: 20px 20px;
}

.board table {
	width: 60%;
	background-color: #eee;
	padding: 10px;
}

.board table tr {
	height: 40px;
}

.message-form {
	display: none;
}

.message-form h1 {
	height: 26px;
	font-size: 22px;
	padding-left: 60px;
	margin-top: 20px;
	margin-bottom: 5px;
}

.message-form input[type="button"], input[type="reset"] {
	height: 30px;
	width: 60px;
}
</style>
<script type="text/javascript">
var messageDialog,dialog,form;
$(function(){
	dialog = $("#dialog").dialog({
	      autoOpen: false,
	      height: 375,
	      width: 350,
	      modal: true,
	      title:'booking professor',
	      buttons: {
	        "OK": function() {
	          dialog.dialog( "close" );
	        }
	      },
	      close: function() {
	    	  form[ 0 ].reset();
	      }
    });
	form = dialog.find( "form" );
	messageDialog = $("#message_dialog").dialog({
      modal: true,
      autoOpen: false,
      buttons: {
        Ok: function() {
          $(this).dialog( "close" );
        }
      }
    });
});

// reply to messages
function reply(messageId){
	$.ajax({
		url:"${pageContext.request.contextPath }/message/query_message?id="+messageId,
		type:'post',
		dataType:'json',
		data:{},
		success:function(response){
			if(response){
				if(response.success){
					document.getElementById("messageForm").reset();
					var data = response.data;
					$("#accepterId").val(data.sender.id);
					$("#messageId").val(data.id);
					$("#accepter").val(data.sender.username);
					$("#title").val(data.title);
					$("#boardInfo").text("Reply "+data.sender.username+" Message Board");
					$("#messageForm").show();
				}else{
					$("#message_show").html(response.message);
					messageDialog.dialog("open");
				}
			}
		}
	});
}
function submitMessage(){
	var accepterId = $("#accepterId").val();
	if(accepterId==null||accepterId==""){
		$("#message_show").html("No Accpter");
		messageDialog.dialog("open");
		return false;
	}
	var title = $("#title").val();
	if(title==null||title==""){
		$("#message_show").html("title is blank!");
		messageDialog.dialog("open");
		return false;
	}
	var context = $("#context").val();
	if(context==null||context==""){
		$("#message_show").html("Context is blank!");
		messageDialog.dialog("open");
		return false;
	}
	var arr = {
		context:context,
		title:$("#title").val()
	};
	var url;
	if($("#messageId").val()==null||$("#messageId").val()==""){
		url = "${pageContext.request.contextPath }/message/add_message?accepter.id="+accepterId;
	}else{
		url = "${pageContext.request.contextPath }/message/add_message?parent.id="+$("#messageId").val()+"&accepter.id="+accepterId;
	}
	$.ajax({
		url:url,
		type:'post',
		dataType:'json',
		data:arr,
		success:function(response){
			if(response){
				if(response.success){
					document.getElementById("messageForm").reset();
					var data = response.data;
					$("#message_show").html("Submit success!");
					messageDialog.dialog("open");
				}else{
					$("#message_show").html(response.message);
					messageDialog.dialog("open");
				}
			}
		}
	});
}
function searchData(){
	var preName = $("#username").val();
	if(preName.length==0){
		$("#message_show").html("Please input a professor's name!");
		messageDialog.dialog("open");
		return false;
	}
	$("#messageForm").hide();
	$.ajax({
		url:'${pageContext.request.contextPath }/student/search_user?username='+encodeURI(preName),
		type:'post',
		dataType:'json',
		data:{},
		success:function(data){
			if(data){
				if(data.result=="success"){
					var accepter = data.user;
					$("#accepterId").val(accepter.id);
					$("#accepter").val(accepter.username);
					$("#boardInfo").text("Leave Message to "+accepter.username+"---Message Board");
					$("#messageForm").show();
				}else{
					$("#message_show").html(data.message);
					messageDialog.dialog("open");
				}
			}
		}
	});
}
</script>
</head>
<body>
	<div class="header">Message Board</div>
	<ul>
		<c:forEach items="${messageList }" var="message">
			<li>
				<div class="message-header">
					<span>sender: ${message.sender.username }</span><span>title:
						${message.title }</span>
				</div>
				<div class="message-context">${message.context }</div>
				<div class="message-footer">
					<span>date: <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"
							value="${message.buildDate }" /></span> <a href="javascript:;"
						onclick="reply(${message.id})">[reply]</a>
				</div>
			</li>
		</c:forEach>
	</ul>
	<div class="page-div">
		<c:if test="${page.currentPage==1 }">
			<a href="javascript:;" style="color: #000;">prePage</a>
			&emsp;
		</c:if>
		<c:if test="${page.currentPage!=1 }">
			<a
				href="${pageContext.request.contextPath }/message/list?currentPage=${page.currentPage-1 }&pageSize=5">prePage</a>
			&emsp;
		</c:if>
		<c:if test="${page.currentPage==page.total }">
			<a style="color: #000;">nextPage</a>
			&emsp;
		</c:if>
		<c:if test="${page.currentPage!=page.total }">
			<a
				href="${pageContext.request.contextPath }/message/list?currentPage=${page.currentPage+1 }&pageSize=5">nextPage</a>
			&emsp;
		</c:if>
		<span>${page.currentPage }/${page.total } Page</span>
	</div>
	<div class="board">
		<div class="search">
			<label>Search by username:</label> <input type="text"
				id="username" name="username" /> <a href="javascript:;"
				onclick="searchData();">Search</a>
		</div>
		<input type="hidden" id="accepterId" name="accepterId" />
		<form class="message-form" id="messageForm">
			<input type="hidden" id="messageId" name="messageId" />
			<h1 id="boardInfo">Message board</h1>
			<table>
				<tr>
					<td>To:</td>
					<td><input type="text" id="accepter" name="accepter" readonly /></td>
				</tr>
				<tr>
					<td>Title:</td>
					<td><input type="text" id="title" name="title" /></td>
				</tr>
				<tr>
					<td>Context:</td>
					<td><textarea rows="6" cols="60" name="context" id="context"></textarea>
					</td>
				</tr>
				<tr>
					<td></td>
					<td><input type="button" value="submit"
						onclick="submitMessage()" />&emsp;<input type="reset"
						value="reset" /></td>
				</tr>
			</table>
		</form>
	</div>
	<div id="dialog" style="display: none;">
		<form>
			<label>student： </label><span id="student"></span><br />
			<br /> <label>date： </label><span id="dateBooking"></span><br />
			<br /> <label>Message： </label><br />
			<br />
			<textarea style="margin: 0px; width: 311px; height: 120px;"
				id="context" readonly></textarea>
		</form>
	</div>
	<div id="message_dialog" style="display: none;">
		<p id="message_show"></p>
	</div>
</body>
</html>