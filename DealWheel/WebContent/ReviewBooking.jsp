<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Review Booking</title>
<script>
var tickerVal = setInterval(function(){timeout()},1000);
var count=<%=MessageBundle.TICKERVALUE%>;
function timeout(){
	document.getElementById("tickerId").innerHTML = count;
	if(count==0){
		clearInterval(tickerVal);
		alert("<%=MessageBundle.SESSIONTIMEOUT%>");
		document.getElementById("logoutFormId").submit();
	}else{
	count--;
	}
}

function proceedWithPayment(){
	var paymentProceedDiv = document.getElementById("paymentOptionsDiv");
	if(paymentProceedDiv!=null){
		paymentProceedDiv.style.display = 'block';
	}
}
</script>
</head>
<body>
<%
//allow access only if session exists

String pagecontext = request.getContextPath();
String userName = null;
String sessionID = null;
long tempBookingId = 0;
session.setAttribute(GenericConstant.COMINGFROMPAGE, "ReviewBooking");
if(session.getAttribute("LoggedInUserDetailsObject")!=null){
List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
if(validUserDetails!=null & validUserDetails.size()>0){
	for(User u : validUserDetails){
		userName = u.getUserEmail();
	}
}
}
if(userName == null){
   response.sendRedirect(GenericConstant.NAV_TO_LOGIN_PAGE);
 }
	if(session.getAttribute(GenericConstant.SESSIONID)!=null)
    sessionID = (String) session.getAttribute(GenericConstant.SESSIONID);
	
	if(session.getAttribute(GenericConstant.TEMPBOOKINGSEQ)!=null){
	tempBookingId = (Long)session.getAttribute(GenericConstant.TEMPBOOKINGSEQ);
	}else{
		tempBookingId = -1;
		response.sendRedirect("/BookingError.jsp");
	}
	
	%>
<span style="float: right;"> Welcome <span style="font-size: 20px;font-weight: bold;color: BLUE;"><%=userName.toUpperCase() %></span></span>
<br>
<br>	
<h3>Hi <%=userName %>. Your Session ID=<%=sessionID %></h3>
<br>

<br>
<h3>Preview booking details</h3>
<form method="post" id="paymentOptionFormId" action="${pageContext.request.contextPath}/ConfirmationSummary">
<h3>Temp Booking Seq = <%=tempBookingId%></h3>
<%
String selectedVehicleDetails = null;
String details[]=null;
if(session.getAttribute(GenericConstant.SELECTEDVEHICLEDETAILS)!=null){
	selectedVehicleDetails = (String)session.getAttribute(GenericConstant.SELECTEDVEHICLEDETAILS);
	details = selectedVehicleDetails.split("\\$",-1);
}
%>
<span style="color: BLUE;font-weight: bold;font-size: 16px;">You have</span><span id="tickerId" style="color: RED;font-weight: bolder;font-size: 20px;"></span><span style="color: BLUE;font-weight: bold;font-size: 16px;">seconds to proceed with payments before session expires.</span>
<span><%=selectedVehicleDetails%></span><br>
<span style="font-weight: bold;font-size: 14px;">Selected Vehicle: </span><span><%=details[0]%>,<%=details[1]%></span><br>
<span style="font-weight: bold;font-size: 14px;">Selected Pickup Location: </span><span><%=details[2]%></span><br>
<span style="font-weight: bold;font-size: 14px;">Effective Vehicle Cost: </span><span><%=details[6]%></span><br>
<span style="font-weight: bold;font-size: 14px;">No. of Days: </span><span><%=details[5]%></span><br>
<span style="font-weight: bold;font-size: 14px;">Refundable Security Amount to be paid: </span><%=details[7]%></span><br>
<span style="font-weight: bold;font-size: 14px;">Total Cost Payable: </span><span><%=(Long.parseLong(details[6])+Long.parseLong(details[7])) %></span><br>

<br>
<input type="button" value="Proceed with Payment" onclick="proceedWithPayment()"/>
<div id="paymentOptionsDiv" style="display: none;">
Payment Successful<input type="radio" name="paymentOption" value="success" /><br>
Payment Unsuccessful<input type="radio" name="paymentOption" value="failure"/><br>
<br>
<input type="submit" value="Proceed"/>
</div>

</form>
<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
<br>
<input type="submit" value="Logout"/>
</form>
</body>
</html>