
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<%@ page import="java.math.BigInteger"  %>
<%@ page import="java.text.DateFormat"  %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>

<head>
<link href="css/LandingPageCSS.css" rel="stylesheet" type="text/css" />
<title>DealWheel: My profile</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
<script type="text/javascript">
var pageContext = '<%=request.getContextPath()%>';
function ButtonClickHandler(val){
	if(val=='FROMEDIT'){
		$("#BeforeEdit").hide();
		$("#OnEdit").show();
		$("#ConfirmMessage").hide();
	}else{
		$("#BeforeEdit").show();
		$("#OnEdit").hide();
		
	}
}



     function OnSubmit(){ 
    	 
    
    	 var pass = document.getElementById("Password").value
         var confPass = document.getElementById("RePassword").value
         if(pass!=null && pass != confPass) {
             $("#email_validation").hide();
             $("#ProfileLoginErrorMsgSpan1").text("Password Not Matching!!!!");
             $("#email_validation").show();
             return;
         }
         
    	 var pContact = document.getElementById("PContact1").value;
    	 var phoneno = /^([0|\+[0-9]{1,5})?([7-9][0-9]{9})$/;
    	 if(!phoneno.test(pContact)){
             $("#email_validation").hide();
             $("#ProfileLoginErrorMsgSpan1").text("Not a Valid Phone Number");
             $("#email_validation").show();
             return;
         } 
    	 
    	 
    	 document.getElementById("ConfirmUpdateBooking").submit(); 
    	 
    	 
    	 
    	 
     }

</script>
<script src="js/LandingPageJS.js" type="text/javascript"></script>

<style>
.navbar-default .navbar-nav > .open > a, .navbar-default .navbar-nav > .open > a:focus, .navbar-default .navbar-nav > .open > a:hover
	{
	background-color:#85b213;
	}

</style>
</head>

<body>
	<% 
	response.setHeader("Cache-Control","no-cache"); //HTTP 1.1
	response.setHeader("Pragma","no-cache"); //HTTP 1.0
	response.setDateHeader ("Expires", 0);
	//prevents caching at the proxy server
	String comingFromPage = (String)session.getAttribute("currentPage");
	if("ReviewBooking".equalsIgnoreCase(comingFromPage)){
		String lockedBookingId = (String)session.getAttribute("tempLockedVehicle");
		System.out.println("Landing Page-lockedBookingId: "+lockedBookingId);
		new CustomerControllerService().cleanBookingUsingTempBookingId(lockedBookingId);
	}
	session.setAttribute("currentPage","MyProfile");
	new CustomerControllerService().cleanBookings();
	
	String userName = null;
	BigInteger userId = null;
	List<Object[]> mydetails = null;
	User userdetails = null;
	LoginDetail logindetail = null;
	
	if(session.getAttribute("LoggedInUserDetailsObject")!=null){
	List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
	if(validUserDetails!=null & validUserDetails.size()>0){
		for(User u : validUserDetails){
			userName = u.getUserEmail();
			userId = new BigInteger(u.getUserId());
		}
		
		System.out.println("UserId in MyProfile "+userId);
	}
	}
	
	if(userName == null){
		response.sendRedirect(pageContext+"/BookingError.jsp");
	}
	else{
		mydetails = (List<Object[]>)CommonUtility.fetchMyProfile(userId);
		
		for(Object[] o: mydetails){
			userdetails = (User) o[0];
			logindetail = (LoginDetail) o[1];
			
		}
			System.out.println(userdetails.getUserName());
		if(mydetails==null || userdetails==null||userdetails.getUserName() == null){
			response.sendRedirect(pageContext+"/BookingError.jsp");
		}
	}
	
	

		
	
	%>
	<!-- Wrap all page content here -->
	<form method="post" action="${pageContext.request.contextPath}/MyProfile" id="ConfirmUpdateBooking">
	<div id="wrap" style="min-height:85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Begin page content -->
<div class="container">



			


<%	if ("Yes".equals((String) request.getSession().getAttribute("SetConfirmSuccess")))
{
	request.getSession().setAttribute("SetConfirmSuccess", "No");

%>
 <div class="container" id="ConfirmMessage">
<div class="alert alert-success">
<h5 style="color: #687074;font-size:14.5px; font-weight:600;"><strong>Success!</strong> Profile Data Saved Successfully </h5>
</div>
</div>
<%} %>


 <div class="container" id="BeforeEdit">
<div class="panel panel-default">
<div id="custom_id" class="panel-heading">
 <div class="row"> 
<div class="col-xs-6">
<h3 class="panel-title" style="color: #687074;font-size:14.5px; font-weight:600;">Personal Details</h3>
</div>
<div class="col-xs-6">
<p class="panel-title pull-right"><button type="button" class="btn btn-info btn-md"  onclick="ButtonClickHandler('FROMEDIT')"><span class="glyphicon glyphicon glyphicon-pencil" ></span> Edit</button></p>
</div>
</div>
</div>
<div class="panel-body" >
<form class="form-horizontal">
<div  class="row form-group">
<div class="col-xs-2"></div>
  <label for="Name" class="col-xs-2 control-label" style="color: #687074;">User Name </label>
    <div class="col-xs-6">
  <span class="control-label" style="color: #687074;font-size:14.5px; font-weight:600;"><%=userdetails.getUserName()%></span>
  </div>
  <div class="col-xs-2"></div>
</div>
<div class="row form-group">
<div class="col-xs-2"></div>
  <label for="Email" class="col-xs-2 control-label" style="color: #687074;">Email </label>
   <div class="col-xs-6">
  <span class="control-label" style="color: #687074;font-size:14.5px; font-weight:600;"><%=userdetails.getUserEmail()%></span>
  </div>
    <div class="col-xs-2"></div>
</div>
<div class="row form-group">
<div class="col-xs-2"></div>
  <label for="PContact" class="col-xs-2 control-label" style="color: #687074;">Password</label>
   <div class="col-xs-6">
  <span class="control-label password" style="color: #687074;font-size:14.5px; font-weight:600;">**********</span>
   </div>
     <div class="col-xs-2"></div>
</div>
<div class="row form-group">
  <div class="col-xs-2"></div>
  <label for="SContact" class="col-xs-2 control-label" style="color: #687074;">Primary Contact</label>
   <div class="col-xs-6">
  <span class="control-label" style="color: #687074;font-size:14.5px; font-weight:600;"><%=userdetails.getUserPrimaryContact()%></span>
  </div>
    <div class="col-xs-2"></div>
</div>
<div class="row form-group">
  <div class="col-xs-2"></div>
  <label for="Gender" class="col-xs-2 control-label" style="color: #687074;">Gender</label>
   <div class="col-xs-6">
  <span class="control-label" style="color: #687074;font-size:14.5px; font-weight:600;"><%=userdetails.getUserGender()%></span>
  </div>
    <div class="col-xs-2"></div>
</div>
</form>
</div>
</div>
</div>

 <div class="container" id="OnEdit" style="display:none;padding:10px">
<div class="panel panel-default"  style="border-color:#85b213;">
<div id="custom_id" class="panel-heading">
<h3 class="panel-title">Personal Details</h3>
</div>
<div class="panel-body">
<div class="form-horizontal">
<input type="hidden" name="UserId" id = "UserId" value = "<%=userId%>"> 
<div class="form-group text-center" id="email_validation" name="email_validation" style="display:none;">
       <label style="font-weight:bold;font-size:14px;" class="label label-danger"><span id="ProfileLoginErrorMsgSpan1">Not a valid Email! </span></label>
</div>
<div  class="row form-group">
  <div class="col-xs-2"></div>
  <label for="Name" class="col-xs-2 control-label" style="color: #687074;font-size:14.5px; font-weight:600;">User Name </label>
    <div class="col-xs-6">
  <input type="text" class="form-control" id="Name" value = "<%=userdetails.getUserName()%>" name="Name">
  </div>
    <div class="col-xs-2"></div>
</div>
<div class="row form-group">
  <div class="col-xs-2"></div>
  <label for="Email" class="col-xs-2 control-label" style="color: #687074;font-size:14.5px; font-weight:600;">Email </label>
   <div class="col-xs-6">
  <span class="control-label"><%=userdetails.getUserEmail()%></span>
  </div>
    <div class="col-xs-2"></div>
</div>
<form id="form" action="" method="post">
<div class="row form-group">
  <div class="col-xs-2"></div>
  <label for="Password" class="col-xs-2 control-label" style="color: #687074;font-size:14.5px; font-weight:600;">Password</label>
   <div class="col-xs-6">
  <input type="password" class="form-control" id="Password" name="Password" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">
  </div>
    <div class="col-xs-2"></div>
</div>
<div class="row form-group">
  <div class="col-xs-2"></div>
  <label for="ConfirmPassword" class="col-xs-2 control-label" style="color: #687074;font-size:14.5px; font-weight:600;">Confirm Password</label>
  
   <div class="col-xs-6">
  <input type="password" class="form-control" id="RePassword" name="RePassword" placeholder="&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;&#9679;">
  </div>
    <div class="col-xs-2"></div>
</div>
</form>
<div class="row form-group">
  <div class="col-xs-2"></div>
  <label for="SContact" class="col-xs-2 control-label" style="color: #687074;font-size:14.5px; font-weight:600;">Primary Contact</label>
   <div class="col-xs-6">
  <input type="number" class="form-control" id="PContact1" value = "<%=userdetails.getUserPrimaryContact()%>" name="PContact1">
  </div>
    <div class="col-xs-2"></div>
</div>
<div class="row form-group">
  <div class="col-xs-2"></div>
  <label for="Gender" class="col-xs-2 control-label" style="color: #687074;font-size:14.5px; font-weight:600;">Gender</label>
   <div class="col-xs-6">
    <%if ("Male".equals(userdetails.getUserGender())) {%>
    <INPUT TYPE="radio" VALUE="Male" name="Gender" checked>
    Male
    <%}
    else {%>
    <INPUT TYPE="radio" VALUE="Male" name="Gender">
    Male
    <%} %>
    <BR>
    <%if ("Female".equals(userdetails.getUserGender())) {%>
    <INPUT TYPE="radio" VALUE="Female" name="Gender" checked>
    Female
    <%}
    
    else {%>
     <INPUT TYPE="radio" VALUE="Female" name="Gender">
     Female
     <%} %>
     <% 
     
     %>
  
  </div>
    <div class="col-xs-2"></div>
</div>
</div>
</div>
</div>
<div class="text-center">
  <div class="form-group ">
 <div class="row"> 
 <div class="col-sm-4"></div>
 <div class="col-sm-4">
<button type="button" class="btn btn-info btn-md" onclick = "OnSubmit()"><span class="glyphicon glyphicon-floppy-disk"></span> Save</button>
 <div class="col-sm-1"></div>
  <button type="button" class="btn btn-info btn-md" style="background-color: rgba(217, 83, 79, 1);" onclick = "ButtonClickHandler('FROMCANCEL')"><span class="glyphicon glyphicon-remove"></span> Cancel</button>
 </div>
 <div class="col-sm-4">
</div>
  </div>
</div>
  		</div>
	</div>
	</form>
	<!--/wrap-->
	<!-- Footer inclusion starts -->
	<%@ include file="commonResources/Footer"%>
	<!-- Footer inclusion ends -->
	<ul class="nav pull-right scroll-top">
		<li><a href="#" title="Scroll to top"><span
				class="glyphicon glyphicon-menu-up"></span></a></li>
	</ul>

	<!-- Including Modal Windows for Signup and Login -->
	<%@ include file="commonResources/CommonModalDivBlocks"%>

	<!-- Including Common JS -->
	<script>
	var propCities = '<%= CommonUtility.getValuesFromProperties("activeCities")%>';
	</script>
	<script src="js/CommonJS.js" type="text/javascript"></script>
	
	
 </body>
</html>