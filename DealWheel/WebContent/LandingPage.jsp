<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="services.CustomerControllerService" %>
<%@ page import="services.utility.MessageBundle" %>
<%@ page import="services.utility.GenericConstant" %>
<%@ page import="services.utility.CommonUtility" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deal Wheel</title>
<link href='//fonts.googleapis.com/css?family=Source+Sans+Pro' rel='stylesheet' type='text/css'>
  <link rel="stylesheet" href="static/css/bootstrap.min.css" />
  <link rel="stylesheet" href="static/css/bootstrap-theme.min.css" />
  <link rel="stylesheet" href="static/css/main.css" />
  <link href="static/css/jquery-ui.css" rel="stylesheet">   
</head>
<body>
	<div class="container-fluid main">
  	<header class="navbar navbar-fixed-top header">
  	  <div class="row">
  	  <div class="col-md-2">
  	  	<img src="static/images/logo.png" alt="Get Your Bike" height="100px" width="150px">
  	  </div>
      <div class="col-md-10">
      <div class="row">
      <div class="col-md-10"></div>
      <div class="col-md-2">
      <button type="button" class="btn btn-default dropdown-toggle" 
      	data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Account 
      	<span class="caret"></span></button>
        <ul class="dropdown-menu dropdown-menu-right">
          <li onclick="selectRadio(true)"><a href="#" data-toggle="modal" data-target="#signInModal" class="openPopup"
          	>Sign In</a></li>
          <li onclick="selectRadio(false)"><a href="#" data-toggle="modal" data-target="#signInModal" class="openPopup"
          	>Sign Up</a></li>
          <li><a href="#">Forgot Password</a></li>
        </ul>
        </div>
        </div>
        <div class="row">
    <div id="navbar-collapse" class="col-md-8" style="float: right">
      <ul class="nav navbar-nav ow">
        <li>
          <a href="index.html">How it Works</a>
        </li>
        <li>
          <a href="bold/index.html">Tariffs</a>
        </li>
        <li>
          <a href="docs/index.html">Policies</a>
        </li>
        <li>
          <a href="index.html">List with us</a>
        </li>
        <li>
          <a href="bold/index.html">Offers</a>
        </li>
        <li>
          <a href="docs/index.html">Contact Us</a>
        </li>
      </ul>
    </div>
  </div>
        </div>
    </div>
  	</header>
  	</div>
  	<div class="container main-content">
		<div class="row main-search">
			<div class="col-md-10 col-md-offset-1 home-search-container">
				<form class="form-inline" method="post" id="searchFormId" action="${pageContext.request.contextPath}/Search">
  					<div class="form-group">
    					<input type="date" class="form-control main-input" id="start_date" name="fromDate" placeholder="Start Date">
  					</div>
  					<div class="form-group">
    					<input type="date" class="form-control main-input" id="end_date" name="toDate" placeholder="End Date">
  					</div>
  					<button type="submit" class="btn btn-default main-input booking-button">Book</button>
				</form>
			</div>
		</div>
  	</div>
  		<!-- Modal -->
  <div class="modal fade" id="signInModal" role="dialog">
    <div class="modal-dialog">
    <%
String errormsg = (String)session.getAttribute("LOGIN_ERROR");
%>
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Sign In</h4>
        </div>
        <div class="modal-body">
          <form method="post" id="submitFormId" class="form-horizontal" 
          	action="${pageContext.request.contextPath}/Login">
          	<%if(errormsg!=null){ %>
				<span style="color: RED; font-weight:bold;font-size: 16px;"><%=errormsg %></span>
				<br/>
				<%
					session.removeAttribute(GenericConstant.LOGINERROR);
			} %>
          	<div class="col-sm-10-offset-2">
    			<label class="radio-inline">
  					<input type="radio" name="option" id="signIn" 
  						onclick="registeredUserFunc()" checked value="oldRegistration">Sign In
				</label>
				<label class="radio-inline">
  					<input type="radio" name="option" id="signUp" value="newRegistration"
  						onclick="newUserFunc()">Sign Up
				</label>
  			</div>
		 	<div class="form-group">
    			<label for="userId" class="col-sm-2 control-label">Username</label>
    			<div class="col-sm-10">
      				<input type="text" name="username" class="form-control" id="userId" placeholder="Username">
    			</div>
  			</div>
  			<div class="form-group">
    			<label for="pswdId" class="col-sm-2 control-label">Password</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control" 
      					id="pswdId" placeholder="Password" name="password">
    			</div>
  			</div>
  			<div class="form-group signUpContent">
    			<label for="pswdIdRe" class="col-sm-2 control-label">Password Retype</label>
    			<div class="col-sm-10">
      				<input type="password" class="form-control" 
      					id="confirmPswdId" placeholder="Password Retype" name="confirmPassword">
    			</div>
  			</div>
			
	</form>
        </div>
        <div class="modal-footer">
          <button type="submit" id="submitBtnId" class="btn btn-default" onclick="verifyPswd()">Login</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
	</div>
<%
new CustomerControllerService().cleanBookings();
session.setAttribute(GenericConstant.COMINGFROMPAGE, "LandingPage");
if(session.getAttribute("LoggedInUserDetailsObject")!=null){
List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
if(validUserDetails!=null & validUserDetails.size()>0){
	for(User u : validUserDetails){
%>
<span style="float: right;"> Welcome <span style="font-size: 20px;font-weight: bold;color: BLUE;"><%=u.getUserEmail().toUpperCase() %></span></span>

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
	<form method="post" id="searchFormId" action="${pageContext.request.contextPath}/Search">
	<div>
   		Space allocated for Search  - Enter Date here in yyyy-MMM-dd format
   		<br>
   		From Date:<input type="text" name="fromDate"/><br>
   		To Date: <input type="text" name="toDate"/><br>
   		<input type="submit" value="Search"/>
	</div>
	</form>
	
	<a href="${pageContext.request.contextPath}/Login.jsp">Login</a>
	<br>
	<a href="${pageContext.request.contextPath}/POC.jsp">POC Page</a>
	<br>
	<a href="${pageContext.request.contextPath}/VendorLoginSignUp.jsp">Vendor mySpace</a>
	<br>
	Log-in to view your bookings<br>
	<%if(session.getAttribute("LoggedInUserDetailsObject")!=null){
		%>
	 <a href="${pageContext.request.contextPath}/MyBookings">My Bookings</a>
	 <%} %>
	 
	 <script src="static/lib/jquery.js"></script>
    <script src="static/lib/bootstrap.min.js"></script>
    <script src="static/lib/jquery-ui.js"></script>
    <script src="static/js/main.js"></script>
</body>
</html>