<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/asset/css/login.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/login.js"></script>
<title>Login page</title>
</head>
<body>
<h1>Academic Assistant System</h1>
<div class="login" style="margin-top:30px;">
    <div class="header">
        <div class="switch" id="switch">
        	<a class="switch_btn_focus" id="switch_login" href="javascript:void(0);" tabindex="7">Login</a>
        </div>
    </div>    
    <div class="web_qr_login" id="web_qr_login" style="display: block;">    
       	<!--login-->
		<div class="web_login" id="web_login">
			<div class="login-box">
				<div class="login_form">
					<form action="#" name="loginform" accept-charset="utf-8" id="login_form" class="loginForm" method="post">
                		<div class="uinArea" id="uinArea">
							<label class="input-tips" for="u">Username:</label>
							<div class="inputOuter" id="uArea">
                    			<input type="text" id="username" name="username" class="inputstyle" />
                			</div>
                		</div>
						<div class="pwdArea" id="pwdArea">
							<label class="input-tips" for="p">Password:</label> 
							<div class="inputOuter" id="pArea">
								<input type="password" id="password" name="password" class="inputstyle" />
                			</div>
                		</div>
                		<div style="color:red;margin-bottom:5px;width:300px;text-align:center;" id="message"></div>
						<div style="width: 320px;margin: 0 auto;line-height:41px;text-align: center;">
							<input type="button" value="Login in" style="width:240px;" class="button_blue" id="loginButton"/>
						</div>
						<div style="margin-top:10px;height:20px;text-align:right;">
							No account yet? Click here <a href="${pageContext.request.contextPath}/welcome/register?role=2" style="color:blue;">Register</a>
						</div>
              		</form>
				</div>
			</div>
		</div>
	<!--login end-->
	</div>
</div>
</body>
</html>