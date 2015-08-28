<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>  
<%@ page import="java.util.Map.Entry" %>  
<%@ page import="java.util.Iterator" %> 
<%@ page import="services.utility.GenericConstant" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose your vehicle</title>
<script>
function selectedVehicle(rowId){
	var selectedPickUpLocation = null;
	var x = document.getElementById("vehicleName"+rowId);
	//alert(x);
	if(x!=null)
	alert(x.innerHTML);
	var selectedLocationId = document.getElementById("pickupLicationSelectId"+rowId);
	if(selectedLocationId!=null){
		alert(selectedLocationId.value);
		selectedPickUpLocation = selectedLocationId.options[selectedLocationId.selectedIndex].text +"$"+selectedLocationId.value; 
	}
	alert("Selected PickUp="+selectedPickUpLocation);
	var selectedVehicleDetailsId  = document.getElementById("selectedVehicleDetailsId");
	alert("0"+selectedVehicleDetailsId);
	if(selectedVehicleDetailsId!=null){
		//alert("1");
		selectedVehicleDetailsId.value = x.innerHTML+"$"+selectedPickUpLocation;
	//	alert("2");
	}
	alert("value="+selectedVehicleDetailsId.value);
	var bookingFormId = document.getElementById("bookingFormId");
	bookingFormId.submit();
}
</script>
</head>
<body>
<form method="post" id="bookingFormId" action="${pageContext.request.contextPath}/ConfirmBooking">
From:<input type="text" value='<%=session.getAttribute(GenericConstant.FROMDATESTRING)%>' readonly> To: <input type="text" value='<%=session.getAttribute(GenericConstant.TODATESTRING)%>' readonly>
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
		 String valueSplit[] = value.split(GenericConstant.DOLLARFORSPLIT,-1);
		 
%>
<tr>
<td id="vehicleName<%=count%>"><%=keySplit[0] %>, <%=keySplit[1] %></td>
<td>
<select id="pickupLicationSelectId<%=count%>">
<%for(int i=0; i<valueSplit.length;i++)
{
%>
<option value="<%=keySplit[2]%>" name="pickupLocation"><%=valueSplit[i] %></option>
<%} %>
</select>
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