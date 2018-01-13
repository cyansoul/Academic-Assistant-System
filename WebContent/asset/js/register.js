$(document).ready(function() {
	$('#reg').click(function() {
		$("#message").text("");
		//获得form表单中的参数,并判断
		//判断用户名
		var username = $("#username").val();
		if(username.length<4){
			$("#message").text("Username's length can not be less than 4!");
			$('#username').focus();
			return false;
		}
		//判断密码
		var password = $("#password").val();
		if(password.length<4){
			$("#message").text("Password's length can not be less than 4!");
			$('#password').focus();
			return false;
		}
		//判断确认密码
		var repassword = $("#repassword").val();
		if(repassword != password){
			$("#message").text("Confirm password is not equal to password!");
			$('#repassword').focus();
			return false;
		}
		//判断邮箱
		if(!isEmail("email","message")){
			$('#email').focus();
			return false;
		}
		//判断专业
		var major = $("#major").val();
		if(major.length==0){
			$("#message").text("Major can not be blank!");
			$('#major').focus();
			return false;
		}
		//判断生日
		var birthday = $("#birthday").val();
		if(birthday.length==0){
			$("#message").text("Birthday can not be blank!");
			$('#birthday').focus();
			return false;
		}
		//判断头像
		var photo = $("#photo").val();
		if(photo.length==0){
			$("#message").text("Photo can not be blank!");
			$('#photo').focus();
			return false;
		}
		$('#regUser').submit();
	});
	//验证邮箱
	function isEmail(fileId, infoId) {
		var card = $("#" + fileId).val();
		var reg = /[a-z0-9-]{1,30}@[a-z0-9-]{1,65}.[a-z]{3}/;
		if (reg.test(card) === false) {
			$("#" + infoId).text("Email is not valid!");
			return false;
		}
		return true;
	}
});