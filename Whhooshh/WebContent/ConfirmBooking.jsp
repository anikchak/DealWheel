<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm Booking</title>
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
</script>
</head>
<body>
<%
//allow access only if session exists

String pagecontext = request.getContextPath();
String userName = null;
String sessionID = null;
int tempBookingId = 0;
if(session.getAttribute(GenericConstant.USERNAME) == null){
    
   RequestDispatcher rd = request.getRequestDispatcher(GenericConstant.NAV_TO_LOGIN_PAGE);
   rd.forward(request, response);
}
	if(session.getAttribute(GenericConstant.SESSIONID)!=null)
    sessionID = (String) session.getAttribute(GenericConstant.SESSIONID);
	
	if(session.getAttribute(GenericConstant.USERNAME)!=null)
    userName = (String) session.getAttribute(GenericConstant.USERNAME);
	
	if(session.getAttribute(GenericConstant.TEMPBOOKINGSEQ)!=null){
	tempBookingId = (Integer)session.getAttribute(GenericConstant.TEMPBOOKINGSEQ);
	}else{
		tempBookingId = -1;
	}
	%>
<h3>Hi <%=userName %>. Your Session ID=<%=sessionID %></h3>
<br>

<br>
<h3>Preview booking details</h3>
<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
<h3>Temp Booking Seq = <%=tempBookingId%></h3>
<input type="submit" value="Logout"/>
<br>
<span style="color: BLUE;font-weight: bold;font-size: 16px;">Time Left</span><span id="tickerId" style="color: RED;font-weight: bolder;font-size: 20px;"></span>

</form>
</body>
</html>