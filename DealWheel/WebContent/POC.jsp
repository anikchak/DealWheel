<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="services.CustomerControllerService" %>
<%@ page import="java.util.Map" %>  
<%@ page import="java.util.Map.Entry" %>  
<%@ page import="java.util.Iterator" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Some POC</title>
<script type="text/javascript">
function populateVehicleDetails(){
	var id = document.getElementById("listVehicleId");
	if(id!=null){
		var optionSelected = id.value;
		//options[id.selectedIndex].text;
		alert(optionSelected);
	}
}


var map;
function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -34.397, lng: 150.644},
    zoom: 8
  });
}

</script>

 <style type="text/css">
      html, body { height: 100%; margin: 0; padding: 0; }
      #map { height: 100%; }
    </style>
</head>
<body>
<select id="listVehicleId" onchange="populateVehicleDetails()">
<%
Map staticVehicleDetails=null;
if(session.getAttribute("staticVehicleDetails")!=null){
	staticVehicleDetails = (Map)session.getAttribute("staticVehicleDetails");
}
else{
	staticVehicleDetails = (Map)new CustomerControllerService().fetchStaticData();
	session.setAttribute("staticVehicleDetails", staticVehicleDetails);
}

if(staticVehicleDetails!=null){
%>
<option selected="selected" id="default"></option>
<%	
Iterator itr = staticVehicleDetails.entrySet().iterator();
while(itr.hasNext()){
	Map.Entry entry = (Entry)itr.next();
	
%>
<option value="<%=((String)entry.getKey()+'#'+(String)entry.getValue())%>"><%=(String)entry.getKey()%></option>
<%
}
}
%>
<option id="Other">Other</option>
</select>
<br>	<a href="${pageContext.request.contextPath}/LandingPage.jsp">Landing Page</a>

<div id="map"></div>

<script async defer
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCgpKmCXrt-BhLYP3wgtgRrLClxKd0M3_U&callback=initMap">
    </script>
</body>
</html>