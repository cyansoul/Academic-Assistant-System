<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/asset/css/register.css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/asset/js/register.js"></script>
<title>Register page</title>
<style>
	.focus{
		border:1px solid red;
		boxShadow:0 0 2px red;
	}
</style>
</head>
<body>
<h1>Academic Assistant System Register Page</h1>
<div class="login" style="margin-top:50px;">
    <div class="header">
        <div class="switch" id="switch">
			<a class="switch_btn" id="switch_register" href="javascript:void(0);" tabindex="8">Register</a>
        </div>
    </div>    
  <!--register-->
    <div class="qlogin" id="qlogin">
    	<div class="web_login">
    		<form enctype="multipart/form-data" method="post" name="form2" id="regUser" accept-charset="utf-8" action="${pageContext.request.contextPath }/welcome/doRegister">
    			<input type="hidden" value="${randomCode }" name="randomCode"/>
				<ul class="reg_form" id="reg-ul">
	                <li>
	                    <label for="user" class="input-tips2">Username:</label>
	                    <div class="inputOuter2">
							<input type="text" id="username" name="username" maxlength="50" class="inputstyle2 focus"/>
	                    </div>
	                </li>
	                <li>
	                	<label for="passwd" class="input-tips2">Password:</label>
	                    <div class="inputOuter2">
	                        <input type="password" id="password" name="password" maxlength="16" class="inputstyle2"/>
	                    </div>
	                </li>
	                <li>
	                	<label for="passwd2" class="input-tips2">Confirm password:</label>
	                    <div class="inputOuter2">
	                        <input type="password" id="repassword" name="repassword" maxlength="16" class="inputstyle2"/>
	                    </div>
	                </li>
	                <li>
	                 	<label for="email" class="input-tips2">Email:</label>
	                    <div class="inputOuter2">
	                        <input type="text" id="email" name="email" maxlength="50" class="inputstyle2"/>
	                    </div>
	                </li>
	                <li>
	                 	<label for="major" class="input-tips2">Major:</label>
	                    <div class="inputOuter2">
	                        <input type="text" id="major" name="major" maxlength="50" class="inputstyle2"/>
	                    </div>
	                </li>
	                <li>
	                 	<label for="sex" class="input-tips2">Gender:</label>
	                    <div class="inputOuter2">
	                    	<select id="sex" name="sex" class="inputstyle2">
	                    		<option value='man' selected>man</option>
	                    		<option value='woman'>woman</option>
	                    		<option value='unknow'>unknow</option>
	                    	</select>
	                    </div>
	                </li>
	                <li>
	                 	<label for="role" class="input-tips2">Role:</label>
	                    <div class="inputOuter2">
	                    	<select id="role" name="role" class="inputstyle2">
	                    		<option value='1' <c:if test="${role==1 }">selected</c:if>>student</option>
	                    		<option value='2' <c:if test="${role==2 }">selected</c:if>>professor</option>
	                    	</select>
	                    </div>
	                </li>
	                <li>
	                 	<label for="birthday" class="input-tips2">Birthday:</label>
	                    <div class="inputOuter2">
	                        <input type="date" id="birthday" name="birthday"  class="inputstyle2"/>
	                    </div>
	                </li>
	                <li>
	                 	<label for="photo" class="input-tips2">Photo:</label>
	                    <div class="inputOuter2">
	                        <input type="file" id="photo" name="file" class="inputstyle2"/>
	                    </div>
	                </li>
	                <li>
	                	<div style="width: 340px;margin: 5px auto;color:red;" id="message">${message}</div>
	                    <div class="inputArea" style="width: 240px;margin: 0 auto;line-height:41px;">
	                        <input type="button" id="reg" style="width:240px;" class="button_blue" value="submit" />
	                    </div>
	                </li>
	            </ul>
			</form>
	    </div>
    </div>
    <!--register end-->
</div>
</body>
</html>