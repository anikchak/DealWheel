	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Vendor</title>
<script src="js/jquery-1.11.3.min.js"></script>
<script>
$(document).ready(function(){
	$("#login_error").hide();
	$("#signup_error").hide();
	
	var preventFormSubmit = "N";
	
	$("#login").click(function(){
		preventFormSubmit = "N";
		validateLogin();
		$("#identifier").val("login");
	});
	
	$("#signup").click(function(){
		preventFormSubmit = "N";
		validateSignUp();
		$("#identifier").val("signup");
	});
	
	function validateLogin(){
		var loginEmail = $("input[name='loginEmail']").val();
		var loginPassword = $("input[name='loginPassword']").val();
		
		if(loginEmail == "" || loginPassword == ""){
			$("#login_error").text("Email or Password cannot be blank");
			$("#login_error").show();
			preventFormSubmit = "Y";
			return false;
		}
		
		if(loginEmail != null && loginEmail != ""){
			var emailValid = isValidEmailAddress(loginEmail);
			if(!emailValid){
				$("#login_error").text("Please enter correct email Id");
				$("#login_error").show();
				preventFormSubmit = "Y";
				return false;
			}
		}
	}
	
	function validateSignUp(){
		var signupEmail = $("input[name='email']").val();
		var signupPassword = $("input[name='password']").val();
		var signupConfirmPassword = $("input[name='confirmPassword']").val();
		
		if(signupEmail == "" || signupPassword == ""  || signupConfirmPassword == "" ){
			$("#signup_error").text("Email or Password cannot be blank");
			$("#signup_error").show();
			preventFormSubmit = "Y";
			return false;
		}
		
		if(signupEmail != null && signupEmail != ""){
			var emailValid = isValidEmailAddress(signupEmail);
			if(!emailValid){
				$("#signup_error").text("Please enter correct email Id");
				$("#signup_error").show();
				preventFormSubmit = "Y";
				return false;
			}
		}
		
		if(signupPassword != signupConfirmPassword){
			$("#signup_error").text("Passwords do not match");
			$("#signup_error").show();
			preventFormSubmit = "Y";
			return false;
		}
	}
	
	function isValidEmailAddress(emailAddress) {
	    var pattern = /^([a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+(\.[a-z\d!#$%&'*+\-\/=?^_`{|}~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]+)*|"((([ \t]*\r\n)?[ \t]+)?([\x01-\x08\x0b\x0c\x0e-\x1f\x7f\x21\x23-\x5b\x5d-\x7e\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|\\[\x01-\x09\x0b\x0c\x0d-\x7f\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))*(([ \t]*\r\n)?[ \t]+)?")@(([a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\d\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.)+([a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]|[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF][a-z\d\-._~\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]*[a-z\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])\.?$/i;
	    return pattern.test(emailAddress);
	};
	
	$("form").submit(function(e){
        if(preventFormSubmit == "Y")
        	e.preventDefault(e);
    });
	
});
</script>
</head>
<body>
<div align="center">
<form action="${pageContext.request.contextPath}/VendorLoginSignUp" method="post" id="loginFormId">
<div>
<table>
<tr><td colspan="2" align="center"><div>Vendor Login</div></td></tr>
<tr><td><div>Email</div></td><td><div><input type="text" name="loginEmail" id="loginEmail"></div></td></tr>
<tr><td><div>Password</div></td><td><div><input type="password" name="loginPassword"></div></td></tr>
<tr><td></td><td><div id="login_error" name="login_Error" style="color: red"></div></td></tr>
<tr><td colspan="2" align="center"><input type="submit" id="login" value="Log In"></td></tr>
</table>
</div>
<div align="center">
<table>
<tr><td colspan="2" align="center"><div>Sign Up Vendor!</div></td></tr>
<tr><td><div>Email</div></td><td><div><input type="text" name="email"></div></td></tr>
<tr><td><div>Password</div></td><td><div><input type="password" name="password"></div></td></tr>
<tr><td><div>Confirm Password</div></td><td><div><input type="password" name="confirmPassword"></div></td></tr>
<tr><td></td><td><div id="signup_error" name="signup_error" style="color: red"></div></td></tr>
<tr><td colspan="2" align="center"><input type="submit" id="signup" value="Sign Up"></td></tr>
</table>
</div>
<input type="hidden" name="identifier" id="identifier" />
</form>
</div>
</body>
</html>