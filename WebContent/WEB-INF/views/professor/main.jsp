<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Academic Assistant System</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/jquery-ui/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/asset/js/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
<script src="${pageContext.request.contextPath }/asset/js/jquery-ui/js/jquery-ui-1.10.4.custom.min.js"></script>
<style type="text/css">
*{margin:0;padding:0;font-size:14px}
	.header{
		height:60px;
		line-height:60px;
		font-size:22px;
		font-weight:bold;
		margin-left:20px;
	}
	a{text-decoration:none;}
	ul{
		list-style:none;
		margin-left:20px;
	}
	ul li{
		background-color: #eee;
		margin-top:10px;
		padding:10px;
	}
	ul li div{
		font-size:16px;
		margin-top:5px;
	}
	ul li div span{
		font-size:20px;
		display:inline-block;
		margin-left:10px;
	}
	ul li div a{
		display:inline-block;
		width:60px;
		height:30px;
		text-align:center;
		line-height:30px;
		background-color:#8b8b8b;
		margin-left:50px;
		border:1px solid #5b5b5b;
		color:#fff;
		cursor: pointer;
	}
	ul li div a:hover{
		background-color:#5b5b5b;
	}
	.read{
		background-color:#f2f2f2;
		color:#000;
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
function detail(scheduleId){
	$.ajax({
		url:"${pageContext.request.contextPath }/professor/query_schedule?id="+scheduleId,
		type:'post',
		dataType:'json',
		data:{},
		success:function(response){
			if(response){
				if(response.success){
					var data = response.data;
					$("#student").text(data.student.username);
					$("#context").text(data.context);
					$("#dateBooking").text(data.bookingDate);
					dialog.dialog( "open" );
				}else{
					$("#message_show").html(response.message);
					messageDialog.dialog("open");
				}
			}
		}
	});
}
</script>
</head>
<body>
<div class="header">Welcome :)</div>
	<ul>
		<c:forEach items="${list }" var="s">
		<li>
			<div>student：<span>${s.student.username }</span></div>
			<div>date:<span><fmt:formatDate pattern="yyyy-MM-dd"  value="${s.bookingDate }"/></span>
			<c:if test="${s.status==1 }">
				<a onclick="detail(${s.id})" class="read">detail</a></div>
			</c:if>
			<c:if test="${s.status==0 }">
				<a onclick="detail(${s.id})">detail</a></div>
			</c:if>
		</li>
		</c:forEach>
	</ul>
	
<div id="dialog" style="display:none;">
	<form >
		<label>student： </label><span id="student"></span><br/><br/>
		<label>date： </label><span id="dateBooking"></span><br/><br/>
		<label>Message： </label><br/><br/>
		<textarea style="margin: 0px; width: 311px; height: 120px;" id="context" readonly></textarea>
	</form>
</div>
<div id="message_dialog" style="display:none;">
	<p id="message_show"></p>
</div>
</body>
</html>