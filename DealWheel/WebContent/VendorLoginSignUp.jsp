	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Vendor</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/vendorLoginSignUp" method="post" id="loginFormId">
<table align="center" border="0">
<tr><td colspan="2" align="center">Vendor Login</td></tr>
<tr><td>Email</td><td><input type="text" name="loginEmail"></td></tr>
<tr><td>Password</td><td><input type="password" name="loginPassword"></td></tr>
<tr><td colspan="2" align="center"><input type="button" name="Log In" value="Log In" onclick="selectedUserOption('Log In')"></td></tr> <!-- Changes done by Aniket -->
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td colspan="2" align="center">Sign Up Vendor!</td></tr>
<tr><td>Email</td><td><input type="text" name="email"></td></tr>
<tr><td>Password</td><td><input type="password" name="password"></td></tr>
<tr><td>Confirm Password</td><td><input type="password" name="confirmPassword"></td></tr>
<tr><td colspan="2" align="center"><input type="button" name="Sign Up" value="Sign Up" onclick="selectedUserOption('Sign Up')"></td></tr><!-- Changes done by Aniket -->
</table>
<input type="hidden" name="identifier" id="identifier" />
</form>

<!-- Changes done by Aniket Start-->
<script>
function selectedUserOption(btnClicked){
	if(btnClicked=='Sign Up'){
		document.getElementById("identifier").value = "signup";
	}else if (btnClicked=='Log In'){
		document.getElementById("identifier").value = "login";
	}
	document.getElementById("loginFormId").submit();
}
</script>
<!--  Changes done by Aniket End -->
</body>
</html>