<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<META Http-Equiv="Cache-Control" Content="no-cache"/>
<META Http-Equiv="Pragma" Content="no-cache"/>
<META Http-Equiv="Expires" Content="0"/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add New Location Of Operation</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/AddLocation" method="post">
<table>
<tr><td>City</td><td><input type="text" id="city" name="city"></td></tr>
<tr><td>State</td><td><input type="text" id="state" name="state"></td></tr>
<tr><td>Country</td><td><input type="text" id="country" name="country"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" name="Add" value="Add"></td></tr>
</table>
</form>
</body>
</html>