<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register with us..</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/vendorRegistration" method="post">
<table align="center" border="0" >
<tr><td>User Name(e-mail)</td> <td><input type="text" id="email" name="email" value=<%= request.getParameter("email") %>></td></tr>
<tr><td colspan="2" align="center"> --------- Personal Details --------- </td></tr>
<tr><td>Full Name</td> <td><input type="text" id="fullName" name="fullName"></td></tr>
<tr><td>Gender</td> <td><input type="radio" name="gender" value="Male">Male</input><input type="radio" name="gender" value="Female">Female</input></td></tr>
<tr><td>Date of Birth</td> <td><input type="text" id="dob" name="dob"></td></tr>
<tr><td colspan="2" align="center"> --------- Contact Details --------- </td></tr>
<tr><td>Primary Contact No</td><td><input type="text" id="primaryContact" name="primaryContact"></td></tr>
<tr><td>Secondary Contact No</td><td><input type="text" id="secondaryContact" name="secondaryContact"></td></tr>
<tr><td colspan="2" align="center"> --------- Address Details --------- </td></tr>
<tr><td>Address Line 1</td><td><input type="text" id="addr1" name="addr1"></td></tr>
<tr><td>Address Line 2</td><td><input type="text" id="addr2" name="addr2"></td></tr>
<tr><td>Address Line 3</td><td><input type="text" id="addr3" name="addr3"></td></tr>
<tr><td>Locality</td><td><input type="text" id="locality" name="locality"></td></tr>
<tr><td>City</td><td><input type="text" id="city" name="city"></td></tr>
<tr><td>State</td><td><input type="text" id="state" name="state"></td></tr>
<tr><td>Country</td><td><input type="text" id="country" name="country"></td></tr>
<tr><td>Pin Code</td><td><input type="text" id="pinCode" name="pinCode"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" name="Register" value="Register"></td></tr>
</table>
<input type="hidden" name="password" value=<%= request.getParameter("password") %>>
</form>
</body>
</html>