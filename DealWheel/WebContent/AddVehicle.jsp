<%@page import="services.LocationOfOperationController"%>
<%@page import="java.util.List"%>
<%@page import="services.AddVehicleController"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List New Vehicle</title>
</head>
<body>
<%@ include file="VendorTabs.jsp" %>
<%! String[]  vehicleNames = {"Pulsar 150","Pulsar 180","Bullet Classic","FZS","Discover","Passion","Thunderbird","CBZ"};
	String[]  vehicleManufacturers = {"Bajaj","Royal Enfield","Hero","Yamaha","Honda"};
	String perDayCost = "250";
	String securityDeposit = "2000";
%>
<form action="${pageContext.request.contextPath}/AddVehicle" method="post">
<table align="center">
<th>Add New Vehicle</th>
<br><br>
<tr>
<td>Select Vehicle</td><td><select name="vehicleName"  id="vehicleName"><%for(String vehicle : vehicleNames){ %><option value="<%=vehicle %>"><%=vehicle %></option><%} %></select></td>
<td>Manufacturer</td><td><select name="vehicleManufacturer"  id="vehicleManufacturer"><%for(String vehicleManufacturer : vehicleManufacturers){ %><option value="<%=vehicleManufacturer %>"><%=vehicleManufacturer %></option><%} %></select></td>
</tr>
<tr><td>Registration No</td><td><input type="text" name="registrationNo"></td><td>Year Of Manufacture</td><td><input type="text" name="yearOfManufacture"></td></tr>
<tr><td>Per Day Cost</td><td><input type="text" name="perDayCost" value="<%= perDayCost %>"></td><td>Security Deposit<td><input type="text" name="securityDeposit" value="<%= securityDeposit%>"></td></tr>
<tr><td colspan="4"><center>Pickup Location</center> </td></tr>
<tr><td colspan="3"><label><%=address.getAddrLine1() %></label></td><td colspan="2"><label><%=address.getAddrLine2() %></label></td></tr>
<tr><td colspan="3"><label><%=address.getAddrLine3() %></label></td><td colspan="2"><label><%=address.getAddrLocality()%></label></td></tr>
<tr><td colspan="3"><label><%=address.getAddrCity() %></label></td><td colspan="2"><label><%=address.getAddrState() %></label></td></tr>
<tr><td colspan="3"><label><%=address.getAddrCountry() %></label></td><td colspan="2"><label><%=address.getAddrPinCode() %></label></td></tr>
<br>
<tr><td colspan="2"><input type="checkbox" name="useDifferentAddress" value="Yes"> Use different address for pickup</td></tr>
<br>
<tr><td>Address Line 1</td><td><input type="text" id="addr1" name="addr1"></td></tr>
<tr><td>Address Line 2</td><td><input type="text" id="addr2" name="addr2"></td></tr>
<tr><td>Address Line 3</td><td><input type="text" id="addr3" name="addr3"></td></tr>
<tr><td>Locality</td><td><input type="text" id="locality" name="locality"></td></tr>
<% 
List<String> countries = new LocationOfOperationController().getCountryNames();
List<String> states = new LocationOfOperationController().getStateNamesForCountry(countries.get(0));
List<String> cities = new LocationOfOperationController().getCityNamesForCountryState(countries.get(0), "Maharashtra");
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
<tr><td colspan="2" align="center"><input type="submit" name="Add" value="Add"></td></tr>
</table>
</form>
</body>
</html>