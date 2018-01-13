<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Academic Assistant System</title>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/asset/js/jquery-1.8.0.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/asset/css/backstage.css">
<script type="text/javascript">
	function delayGo(u, t) {
		setTimeout(function() {
			if (typeof u == 'string')
				location = u;
			else if (typeof u == 'function')
				u();
		}, (t == null ? 300 : t));
	}
</script>
<style type="text/css">
a {
	text-decoration: none;
}

.mList li {
	height: 30px;
	width: 100%;
	padding-left: 10px;
}

.mList li:hover {
	background-color: #fff;
}

.mList li:hover a {
	color: #000;
}
</style>
</head>

<body>
	<div class="head">
		<h3 class="head_text fl">Academic Assistant System</h3>
	</div>
	<div class="operation_user clearfix">
		<div class="link fr">
			<b>Welcome ${user.username } </b>&nbsp;&nbsp;&nbsp;&nbsp;<a href="./"
				class="icon icon_i">Home Page</a><span></span> <a
				href="javascript:void(0);" class="icon icon_j"
				onclick="if(document.referrer){delayGo(function(){history.go(1)});return false;}">Forward</a><span></span>
			<a href="javascript:void(0);"
				onclick="if(document.referrer){delayGo(function(){history.go(-1)});return false;}"
				class="icon icon_t">Back</a><span></span> <a
				href="javascript:void(0);" class="icon icon_n"
				onclick="if(document.referrer){delayGo(function(){history.go(0)});return false;}">Refresh</a><span></span>
			<a href="${pageContext.request.contextPath }/welcome/logout"
				class="icon icon_e">Logout</a>
		</div>
	</div>
	<div class="content clearfix">
		<div class="main">
			<!--right context-->
			<div class="cont">
				<div class="title">Academic Assistant System For Student</div>
				<iframe src="${pageContext.request.contextPath }/main"
					frameborder="0" name="mainFrame" width="100%" height="650"
					id="frame"></iframe>
			</div>
		</div>
		<!--left navigation bar-->
		<div class="menu">
			<div class="cont">
				<div class="title">Dashboard</div>
				<ul class="mList">
					<li><a href="${pageContext.request.contextPath }/student/list"
						target="mainFrame">Make Appointment</a></li>
					<li><a href="${pageContext.request.contextPath }/message/list"
						target="mainFrame">Message Board</a></li>
				</ul>
			</div>
		</div>

	</div>
	<script type="text/javascript">
		var wHeight = document.documentElement.clientHeight;
		$(".menu").height(wHeight - 91);
		$("#frame").height(wHeight - 131);
	</script>
</body>
</html>