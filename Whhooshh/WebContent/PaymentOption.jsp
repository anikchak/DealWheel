<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment</title>
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
<h1>Payment Integration page</h1>
<br>
As of now choose the anyone of the below option.
<br>
<form action="${pageContext.request.contextPath}/ConfirmationSummary" id="paymentOptionFormId" method="post">

Payment Successful<input type="radio" name="paymentOption" value="success"/><br>
Payment Unsuccessful<input type="radio" name="paymentOption" value="failure"/><br>
</form>
</body>
</html>