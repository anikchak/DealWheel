<%@page import="dao.VehicleDAOImpl"%>
<%@page import="model.Vehicle"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Vendor Listed Vehicles</title>
</head>
<body>
<%@ include file="VendorTabs.jsp" %>
<% List<Object[]> vehicleDetailList = new VehicleDAOImpl<Vehicle>().getVehicleDetailsForUserId(user.getUserId()); %>
<table border="1">
<th>Vehicle Name</th><th>Vehicle Manufacturer</th><th>Registration No</th><th>Year Of Manufacture</th><th>Per Day Cost(Rs)</th><th>Security Deposit(Rs)</th><th>Pickup Location</th>
<% for(Object[] vehicleDetail : vehicleDetailList ){ %>
<tr>
<td><%= (String)vehicleDetail[0] %></td>
<td><%= (String)vehicleDetail[1] %></td>
<td><%= (String)vehicleDetail[2] %></td>
<td><%= (String)vehicleDetail[3] %></td>
<td><%= (Integer)vehicleDetail[4] %></td>
<td><%= (Integer)vehicleDetail[5] %></td>
<td><table>
<tr><td><%=(String)vehicleDetail[6]+", " %></td><td><%=(String)vehicleDetail[7]+", " %></td><td><%=(String)vehicleDetail[8]+", " %></td></tr>
<tr><td><%=(String)vehicleDetail[9]+", " %></td><td><%=(String)vehicleDetail[10]+", " %></td><td><%=(String)vehicleDetail[11]+", " %></td></tr>
<tr><td><%=(String)vehicleDetail[12]+" - " %></td><td><%=(Integer)vehicleDetail[13] %></td><td></td></tr>
</table></td></tr>
<%} %>
</table>
</body>
</html>