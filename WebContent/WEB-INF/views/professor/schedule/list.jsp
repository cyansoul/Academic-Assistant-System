<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/jquery-ui/js/jquery-1.10.2.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/common.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath }/asset/js/jquery-ui/css/ui-lightness/jquery-ui-1.10.4.custom.min.css">
<script src="${pageContext.request.contextPath }/asset/js/jquery-ui/js/jquery-ui-1.10.4.custom.min.js"></script>
<title>Subscribe</title>
<style type="text/css">
	.header{
		height:50px;
		width:100%;
		border-bottom:1px solid #eee;
		margin-bottom:10px;
		padding-left:20px;
		vertical-align: middle;
	}
	.search input{
		height:24px;
		font-size:16px;
	}
	.search a{
		text-decoration: none;
		cursor: pointer;
		display: inline-block;
		height:30px;
		width:100px;
		margin-left:20px;
		background-color:#e2e2e2;
		text-align:center;
		line-height:30px;
		color:#222;
		border:1px solid #e2e2e2;
	}
	.search a:hover {
		background-color:#c2c2c2;
		
	}
	body tr th{
		width:120px;
		vertical-align: middle;
		text-align:center;
		height:30px;
		background-color:#e2e2e2;
	}
	body tr td{
		text-align:center;
		font-size:14px;
		min-height:30px;
		padding:5px 3px;
	}
	body tr td label{
		font-size:24px;
		font-weight:bold;
	}
	body tr td div{
		margin-top:10px;
		height:inherit;
		text-align:center;
	}
	body tr td div a{
		display:block;
		width:60px;
		height:30px;
		background-color:#e2e2e2;
		text-align:center;
		line-height:30px;
		text-decoration: none;
		color:blue;
		margin:0 auto;
	}
	body tr td div a:hover{
		color:red;
	}
	.month{
		width:100%;
		height:40px;
		margin-bottom:0px;
		padding-left:20px;
	}
	.month a{
		display:inline-block;
		height:30px;
		width:80px;
		margin-right:20px;
	}
	.month a:hover{
		color:red;
	}
	#now_month{
		font-weight:bold;
		font-size:18px;
	}
	.professor-name{
		width:100%;
		height:30px;
		padding-left:20px;
	}
	.professor-name span{
		font-size:20px;
		font-weight:bold;
	}
	.body{
	}
	.no-data{
		width:100%;
		height:40px;
		font-size:20px;
		padding-left:20px;
		display:none;
	}
</style>
<script type="text/javascript">
	function loadBooking(){
		//获得日期
		var yearMonth = $("#now_month").text();
		var year = yearMonth.split("-")[0];
		var month = yearMonth.split("-")[1];
		var arr = getMonthArea(year,month);
		$.ajax({
			url:'${pageContext.request.contextPath }/professor/load_booking',
			type:'post',
			dataType:'json',
			data:arr,
			success:function(response){
				if(response){
					if(response.success==true){
						$("#tbody").html("");
						var data = response.data;
						var first = data[0];
						var tr = "<tr>";
						var length = parseInt(first.week)==0?7:parseInt(first.week);
						for(var i = 1;i<length;i++){
							tr += "<td></td>";
						}
						for(var i = 1 ; i <= data.length; i++){
							var day = data[i-1];
							if(day.status==true){
								tr += "<td><label>"+i+"</label><div><button onclick='detail("+day.scheduleId+")'>Detail</button></div></td>";
							}else{
								tr += "<td><label>"+i+"</label></td>";
							}
							if(parseInt(day.week)==0){
								if(i ==data.length){
									tr += "</tr>";
								}else{
									tr += "</tr><tr>";
								}
							}
						}
						var end = data[data.length-1];
						if(parseInt(end.week)!=0){
							for(var i = parseInt(end.week);i<7;i++){
								tr += "<td></td>";
							}
							tr += "</tr>";
						}
						$("#tbody").html(tr);
					}else{
						
					}
				}
			}
			
		});
	}
	var dialog,form,scheduleDate;
	//
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
	//上一月
	function preMonth(){
		var myDate = new Date();
		var nowMonth = myDate.getMonth()+1;
		var yearMonth = $("#now_month").text();
		var month = parseInt(yearMonth.split("-")[1]);
		var nowYM;
		var year = parseInt(yearMonth.split("-")[0]);
		if(month==1){
			year = year - 1;
			nowYM = year + "-12";
		}else{
			nowYM = year + "-" + (month-1);
		}
		$("#now_month").text(nowYM);
		loadBooking();
	}
	//下一个月
	function nextMonth(){
		var yearMonth = $("#now_month").text();
		var month = parseInt(yearMonth.split("-")[1]);
		var year = parseInt(yearMonth.split("-")[0]);
		var nowYM;
		if(month == 12){
			year = year + 1;
			nowYM = year + "-01";
		}else{
			nowYM = year + "-" + (month+1);
		}
		$("#now_month").text(nowYM);
		loadBooking();
	}
	var messageDialog;
	$(function(){
		loadBooking();
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
	
</script>
</head>
<body>
<div class="header">
	<h3>My Schedule</h3>
</div>
<div class="body">
	<div class="month">
		<a onclick="preMonth()" href="javascript:void(0);">Pre Month</a>
		<a onclick="nextMonth()" href="javascript:void(0);">Next Month</a>
		<span>Month: <font id="now_month" >2016-12</font></span>
	</div>
	<table border="1" cellpadding="0" cellspacing="0">
		<thead>
			<tr align="center">
				<th>Monday</th>
				<th>Tuesday</th>
				<th>Wednesday</th>
				<th>Thursday</th>
				<th>Friday</th>
				<th>Saturday</th>
				<th>Sunday</th>
			</tr>
		</thead>
		<tbody id="tbody">
		</tbody>
	</table>
</div>
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