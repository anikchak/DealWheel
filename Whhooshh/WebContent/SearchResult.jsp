<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>  
<%@ page import="java.util.Map.Entry" %>  
<%@ page import="java.util.Iterator" %> 
<%@ page import="services.utility.GenericConstant" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.concurrent.TimeUnit" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose your vehicle</title>
<script>
function selectedVehicle(rowId){
	var selectedPickUpLocation = null;
	var vehicleNameId = document.getElementById("vehicleNameSpan"+rowId);
	var vehicleMakerId = document.getElementById("vehicleMakerSpan"+rowId);
	var perDayCostId = document.getElementById("perDayCostSpanId"+rowId);
	var effectiveCostId = document.getElementById("effectiveCostSpanId"+rowId);
	var securityDepositId = document.getElementById("securityDepositSpanId"+rowId);
	var noOfDaysId = document.getElementById("noOfDaysSpanId");
	
	if(vehicleNameId!=null)
	var selectedLocationId = document.getElementById("pickupLicationSelectId"+rowId);
	
	if(selectedLocationId!=null){
		selectedPickUpLocation = selectedLocationId.options[selectedLocationId.selectedIndex].text +"$"+selectedLocationId.value; 
	}
	
	var selectedVehicleDetailsId  = document.getElementById("selectedVehicleDetailsId");
	if(selectedVehicleDetailsId!=null && perDayCostId!=null && effectiveCostId!=null && securityDepositId!=null && vehicleMakerId!=null && noOfDaysId!=null){
		selectedVehicleDetailsId.value = vehicleNameId.innerHTML+"$"+vehicleMakerId.innerHTML+"$"+selectedPickUpLocation+"$"
										+perDayCostId.innerHTML+"$"+noOfDaysId.innerHTML+"$"+effectiveCostId.innerHTML+"$"+securityDepositId.innerHTML;
	}else{
		return false;
	}
	
	//alert("value="+selectedVehicleDetailsId.value);
	var bookingFormId = document.getElementById("bookingFormId");
	bookingFormId.submit();
}
</script>
</head>
<body>
<%
session.setAttribute(GenericConstant.COMINGFROMPAGE, "SearchResult");
if(session.getAttribute("LoggedInUserDetailsObject")!=null){
List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
if(validUserDetails!=null & validUserDetails.size()>0){
	for(User u : validUserDetails){
%>
<span style="float: right;"> Welcome <span style="font-size: 20px;font-weight: bold;color: BLUE;"><%=u.getUserEmail().toUpperCase() %></span></span>
<input type="submit" value="Logout" style="float:right;"/>
<%		
	}
}
}
else{
%>
<span style="float: right;"> Welcome <span style="font-size: 20px;font-weight: bold;color: BLUE;font-family: Verdana;">Guest</span></span>
<%} %>	
<br>
<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
<br>
<input type="submit" value="Logout" style="float: right;"/>
</form>
<br>
<br>
<form method="post" id="bookingFormId" action="${pageContext.request.contextPath}/ConfirmBooking">
From:<input type="text" value='<%=session.getAttribute(GenericConstant.FROMDATESTRING)%>' readonly> To: <input type="text" value='<%=session.getAttribute(GenericConstant.TODATESTRING)%>' readonly>
<br>
Booking Tenure=<%
SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");
long noOfDays=0;
if(session.getAttribute(GenericConstant.FROMDATESTRING)!=null && session.getAttribute(GenericConstant.TODATESTRING)!=null){
	Date startDate = format.parse((String)session.getAttribute(GenericConstant.FROMDATESTRING));
	Date endDate = format.parse((String)session.getAttribute(GenericConstant.TODATESTRING));
	long duration  = endDate.getTime() - startDate.getTime();
	noOfDays = TimeUnit.MILLISECONDS.toDays(duration);
}
%>
<span id="noOfDaysSpanId" style="font-weight: bold; font-size: 20px;color: RED;"><%=noOfDays %></span>
<table border="1">
<%
Map displaySearchResultMap = (Map)session.getAttribute(GenericConstant.DISPLAYSEARCHRESULTMAP);
if(displaySearchResultMap!=null){
	Iterator itr = displaySearchResultMap.entrySet().iterator();
	int count=0;
	 while(itr.hasNext()){
		 Map.Entry entry = (Entry) itr.next();
		 String key = (String)entry.getKey();
		 String value = (String)entry.getValue();
		 String keySplit[] = key.split(GenericConstant.DOLLARFORSPLIT,-1);
		 String valueSplitDollar[] = value.split(GenericConstant.DOLLARFORSPLIT,-1);
		 
%>
<tr>
<td id="vehicleName<%=count%>"><span id="vehicleNameSpan<%=count%>"><%=keySplit[0] %></span>, <span id="vehicleMakerSpan<%=count%>"><%=keySplit[1] %></span></td>
<td>
<select id="pickupLicationSelectId<%=count%>">
<%for(int i=0; i<valueSplitDollar.length;i++)
{
	String valueSplitPercent[] = valueSplitDollar[i].split("%",-1);
%>
<option value="<%=valueSplitPercent[1]%>" name="pickupLocation"><%=valueSplitPercent[0] %></option>
<%} %>
</select>
</td>
<td>
Per Day Cost=<span id="perDayCostSpanId<%=count%>"><%=keySplit[2] %></span><br>
Effective cost=<span id="effectiveCostSpanId<%=count%>"><%=(noOfDays*Long.parseLong(keySplit[2])) %></span><br>
<span id="securityDepositSpanId<%=count%>" style="display: none;"><%=keySplit[3] %></span>
</td>
<td><input type="button" value ="Book" onclick="selectedVehicle(<%=count%>)"/></td>
</tr>
<%
count++;
	 }
}
%>
</table>
<input type="text" id="selectedVehicleDetailsId" name="selectedVehicleDetails" style="display:none;"/>
</form>
</body>
</html>