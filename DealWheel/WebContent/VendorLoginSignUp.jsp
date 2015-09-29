	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Vendor</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/vendorLoginSignUp" method="post">
<table align="center" border="0">
<tr><td colspan="2" align="center">Vendor Login</td></tr>
<tr><td>Email</td><td><input type="text" name="loginEmail"></td></tr>
<tr><td>Password</td><td><input type="password" name="loginPassword"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" name="Log In" value="Log In"></td></tr>
<tr><td></td></tr>
<tr><td></td></tr>
<tr><td colspan="2" align="center">Sign Up Vendor!</td></tr>
<tr><td>Email</td><td><input type="text" name="email"></td></tr>
<tr><td>Password</td><td><input type="password" name="password"></td></tr>
<tr><td>Confirm Password</td><td><input type="password" name="confirmPassword"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" name="Sign Up" value="Sign Up"></td></tr>
</table>
<input type="hidden" name="identifier" id="identifier" value="login"/>
</form>
</body>
</html>