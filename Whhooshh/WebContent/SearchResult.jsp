<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.Map" %>  
<%@ page import="java.util.Map.Entry" %>  
<%@ page import="java.util.Iterator" %> 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form method="post" id="bookingFormId" action="${pageContext.request.contextPath}/ConfirmBooking">
<table border="1">
<%
Map displaySearchResultMap = (Map)session.getAttribute("displaySearchResultMap");
if(displaySearchResultMap!=null){
	Iterator itr = displaySearchResultMap.entrySet().iterator();
	 while(itr.hasNext()){
		 Map.Entry entry = (Entry) itr.next();
		 String key = (String)entry.getKey();
		 String value = (String)entry.getValue();
		 String keySplit[] = key.split("\\$",-1);
		 String valueSplit[] = value.split("\\$",-1);
%>
<tr>
<td><%=keySplit[0] %>, <%=keySplit[1] %></td>
<td>
<select>
<%for(int i=0; i<valueSplit.length;i++){ %>
<option value="<%=i%>"><%=valueSplit[i] %></option>
<%} %>
</select>
</td>
<td><input type="submit" value ="Book"/></td>
</tr>
<%
	 }
}
%>
</table>
</form>
</body>
</html>