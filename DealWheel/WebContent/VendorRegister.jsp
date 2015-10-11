<%@page import="java.util.List"%>
<%@page import="services.LocationOfOperationController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register with us..</title>
<script src="jquery-1.11.3.min.js"></script>
<script>
$(document).ready(function(){
	alert("Hi");
});
</script>
</head>
<body>
<form action="${pageContext.request.contextPath}/VendorRegistration" method="post">
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
<% 
List<String> countries = new LocationOfOperationController().getCountryNames();
List<String> states = new LocationOfOperationController().getStateNamesForCountry(countries.get(0));
List<String> cities = new LocationOfOperationController().getCityNamesForCountryState(countries.get(0), "Karnataka");
%>

<tr><td>Country :</td><td><select name="country"  id="country">
	<option value="default">Select a Country</option>
	<%for(String c : countries){ %>
		<option value=<%=c %>><%=c %></option>
	<%} %></select></td></tr>
	
<tr><td>State :</td><td><select name="state"  id="state">
	<option value="default">Select a State</option>
	<%for(String s : states){ %>
		<option value=<%=s %>><%=s %></option>
	<%} %></select></td></tr>

<tr><td>City :</td><td><select name="city"  id="city">
	<option value="default">Select A City</option>
	<%for(String ct : cities){ %>
		<option value=<%=ct %>><%=ct %></option>
	<%} %></select></td></tr>
	
<tr><td>Pin Code</td><td><input type="text" id="pinCode" name="pinCode"></td></tr>
<tr><td colspan="2" align="center"><input type="submit" name="Register" value="Register"></td></tr>
</table>
<input type="hidden" name="password" value=<%= request.getParameter("password") %>>
</form>
</body>
</html>