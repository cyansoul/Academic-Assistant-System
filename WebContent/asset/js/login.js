$(document).ready(function() {
	$('#loginButton').click(function() {
		//判断用户名
		var username = $("#username").val();
		if(username.length==0){
			$("#message").text("username can not be blank!");
			$('#username').focus();
			return false;
		}
		//判断密码
		var password = $("#password").val();
		if(password.length==0){
			$("#message").text("password can not be blank!");
			$('#password').focus();
			return false;
		}
		$.ajax({
			type: "post",
			url: "../welcome/handleLogin",
			data: {username:username,password:password},
			dataType: 'json',
			success: function(response) {
				if(response){
					if(response.result=="success"){
						if(response.role==1){
							window.location.href="../student";
						}else{
							window.location.href="../professor";
						}
					}else{
						$("#message").text(response.message);
					}
				}
			}
		});
	});
});