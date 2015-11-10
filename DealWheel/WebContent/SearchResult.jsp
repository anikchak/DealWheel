<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
 
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
<title>Choose your vehicle</title>
<script>
function selectedVehicle(rowId){
	alert(rowid);
	/*
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
	*/
}

</script>

<style>
body{
padding-top:0px;
}
</style>
</head>
<body>
<%@ include file="commonResources/NavigationBar"%>
	
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
SimpleDateFormat format = new SimpleDateFormat(GenericConstant.DATEFORMAT);
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
		// String keySplit[] = key.split(GenericConstant.DOLLARFORSPLIT,-1);
		// String valueSplitDollar[] = value.split(GenericConstant.DOLLARFORSPLIT,-1);
		 String valueSplitHash[] = value.split("#",-1);
		 
%>
<tr>
<td>
<div>
Vehicle Image URL= <%= valueSplitHash[0]%><br>
Vehicle Name = <%=valueSplitHash[1] %><br>
Vehicle Make = <%=valueSplitHash[2] %><br>
Per day cost= <%=valueSplitHash[3] %><br>
Security cost= <%=valueSplitHash[4] %><br>
Vendor Name= <%=valueSplitHash[5] %><br>
Pickup= <%=valueSplitHash[6] %><br>
Complete Address= <%=valueSplitHash[7] %><br>
<button type="button" onclick="selectedVehicle(<%=key%>)">Book</button>
</div>
</td>
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