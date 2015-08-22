<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Confirm Booking</title>
</head>
<body>
<%
//allow access only if session exists

String pagecontext = request.getContextPath();
String userName = null;
String sessionID = null;
if(session.getAttribute("username") == null){
    
   RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
   rd.forward(request, response);
}

    sessionID = (String) session.getAttribute("sessionID");
    userName = (String) session.getAttribute("username");

%>
<h3>Hi <%=userName %>, Data pulled from Cookie. Your Session ID=<%=sessionID %></h3>
<br>

<br>
<h3>Preview booking details</h3>
<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
<input type="submit" value="Logout"/>
</form>
</body>
</html>