<%@page import="java.math.BigInteger"%>
<%@page import="services.utility.GenericConstant"%>
<%@page import="model.Address"%>
<%@page import="dao.AddressDAOImpl"%>
<%@page import="model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome Vendor to MySpace</title>
</head>
<body>
<%@ include file="VendorTabs.jsp" %>
<form action="${pageContext.request.contextPath}/VendorRegistration" method="post">
<table align="center" border="0" >
<th>Vendor Details</th>
<tr><td><label>Vendor Name</label></td><td><input type="text" name="fullName" value="<%= user.getUserName()%>"></td></tr>
<tr><td colspan="1"><label>Username</label></td><td><label><%=user.getUserEmail()%></label></td></tr>
<tr><td><label>Contacts</label></td><td><input type="text" name="primaryContact" value="<%=user.getUserPrimaryContact()%>"></td><td><input type="text" name="secondaryContact" value="<%=user.getUserSecondaryContact()%>"></td><td></td></tr>
<tr><td><label>Address</label></td><td><input type="text" name="addr1" value="<%= address.getAddrLine1()%>"></td><td></td><td></td></tr>
<tr><td><label></label></td><td><input type="text" name="addr2" value="<%=address.getAddrLine2()%>"></td><td></td><td></td></tr>
<tr><td><label></label></td><td><input type="text" name="addr3" value="<%=address.getAddrLine3()%>"></td><td></td><td></td></tr>
<tr><td><label>Locality</label></td><td><input type="text" name="locality" value="<%=address.getAddrLocality()%>"></td><td><input type="text" name="city" value="<%=address.getAddrCity()%>"></td><td></td></tr>
<tr><td><label>State</label></td><td><input type="text" name="state" value="<%=address.getAddrState()%>"></td><td><input type="text" name="pinCode" value="<%=address.getAddrPinCode()%>"></td><td></td></tr>
<tr><td></td><td><input type="submit" name="Save" value="Save"/></td><td><input type="button" name="Edit" value="Edit"/></td></tr>
</table>
</form>
</body>
</html>