<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Validation Success</title>
</head>
<body>
<h2>Welcome</h2><H3><%=request.getParameter("username") %></H3>
<br>
<h3><a href="${pageContext.request.contextPath}/LandingPage.jsp"> Try again</a></h3>
</body>
</html>