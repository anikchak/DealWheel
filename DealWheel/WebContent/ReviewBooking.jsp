<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<%@ page import="java.util.*" %>
<%@ page import="java.security.*" %>
<!DOCTYPE html">
<html>
<head>
<META Http-Equiv="Cache-Control" Content="no-cache"/>
<META Http-Equiv="Pragma" Content="no-cache"/>
<META Http-Equiv="Expires" Content="0"/>
<title>DealWheel: Booking Summary</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>

<style>
.navbar-default .navbar-nav>.open>a, .navbar-default .navbar-nav>.open>a:focus,
	.navbar-default .navbar-nav>.open>a:hover {
	background-color: #85b213;
}
</style>

</head>
<body>
<%
//allow access only if session exists

String pagecontext = request.getContextPath();
String userName = null;
String primaryContactNo = null;
String sessionID = null;
long tempBookingId = 0;
session.setAttribute("currentPage", "ReviewBooking");
if(session.getAttribute("LoggedInUserDetailsObject")!=null){
List<User> validUserDetails = (List<User>)session.getAttribute("LoggedInUserDetailsObject");
if(validUserDetails!=null & validUserDetails.size()>0){
	for(User u : validUserDetails){
		userName = u.getUserEmail();
		primaryContactNo = u.getUserPrimaryContact().toString();
	}
}
}

if(userName == null){
	response.sendRedirect(pageContext+"/BookingError.jsp");
}
%>

<!-- Wrap all page content here -->
	<div id="wrap" style="min-height: 85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<%
 			SimpleDateFormat format = new SimpleDateFormat(GenericConstant.DATEFORMAT);
 			long noOfDays = 0;
 			String sdtDay=null,sdtMonth=null,sdtYear=null,sdtDateNum=null,endtDay=null,endtMonth=null,endtYear=null,endtDateNum = null;
 			if (session.getAttribute(GenericConstant.FROMDATESTRING) != null && session.getAttribute(GenericConstant.TODATESTRING) != null) 
 			{
 				Date startDate = format.parse((String) session.getAttribute(GenericConstant.FROMDATESTRING));
 				Date endDate = format.parse((String) session.getAttribute(GenericConstant.TODATESTRING));
 				long duration = endDate.getTime() - startDate.getTime();
 				noOfDays = TimeUnit.MILLISECONDS.toDays(duration);
 				sdtDay = new SimpleDateFormat("EEE").format(startDate);
 				sdtMonth = new SimpleDateFormat("MMM").format(startDate);
 				sdtYear = new SimpleDateFormat("yy").format(startDate);
 				sdtDateNum = new SimpleDateFormat("dd").format(startDate);
 				endtDay = new SimpleDateFormat("EEE").format(endDate);
 				endtMonth = new SimpleDateFormat("MMM").format(endDate);
 				endtYear = new SimpleDateFormat("yy").format(endDate);
 				endtDateNum = new SimpleDateFormat("dd").format(endDate);
 			}
	
			String fetchSelectedVehicle = (String)request.getParameter("fetchVehicleSelected");
		    if(fetchSelectedVehicle!=null && !"".equalsIgnoreCase(fetchSelectedVehicle)){
			session.setAttribute("tempLockedVehicle", fetchSelectedVehicle);
			List<Object[]> vehicleDetailsList = (List<Object[]>)CommonUtility.fetchVehicleUsingTempBooking(fetchSelectedVehicle);
			if(vehicleDetailsList!=null && vehicleDetailsList.size()==1){
				Vehicle v = (Vehicle)((Object)vehicleDetailsList.get(0)[0]);
				ListedVehicle lv = (ListedVehicle)((Object)vehicleDetailsList.get(0)[1]);
				Bookingshistory bh = (Bookingshistory)((Object)vehicleDetailsList.get(0)[2]);
				Address a = (Address)((Object)vehicleDetailsList.get(0)[3]);
				User u = (User)((Object)vehicleDetailsList.get(0)[4]);
%>
<!-- Payment Changes Start -->
<%!
public boolean empty(String s)
	{
		if(s== null || s.trim().equals(""))
			return true;
		else
			return false;
	}
%>
<%!
	public String hashCal(String type,String str){
		byte[] hashseq=str.getBytes();
		StringBuffer hexString = new StringBuffer();
		try{
		MessageDigest algorithm = MessageDigest.getInstance(type);
		algorithm.reset();
		algorithm.update(hashseq);
		byte messageDigest[] = algorithm.digest();
            
		
		for (int i=0;i<messageDigest.length;i++) {
			String hex=Integer.toHexString(0xFF & messageDigest[i]);
			if(hex.length()==1) hexString.append("0");
			hexString.append(hex);
		}
			
		}catch(NoSuchAlgorithmException nsae){ }
		
		return hexString.toString();
	}
%>
<% 	
	String merchant_key="gtKFFx";
	String salt="eCwWELxi";
	String action1 ="";
	String base_url="https://test.payu.in";
	int error=0;
	String hashString="";
	
 	Enumeration paramNames = request.getParameterNames();
	Map<String,String> params= new HashMap<String,String>();
    	while(paramNames.hasMoreElements()) 
	{
      		String paramName = (String)paramNames.nextElement();
      
      		String paramValue = request.getParameter(paramName);
		params.put(paramName,paramValue);
	}
    String txnid=null;
    if(fetchSelectedVehicle!=null){
    	txnid=hashCal("SHA-256",fetchSelectedVehicle+"-"+(System.currentTimeMillis() / 1000L)).substring(0,20);
    }
	
	Long amount  = (noOfDays*v.getVhclPerDayCost());
	String hash="";
	String hashSequence = "key|txnid|amount|productinfo|firstname|email|udf1|udf2|udf3|udf4|udf5|udf6|udf7|udf8|udf9|udf10";
	params.put("key",merchant_key);
	if(txnid!=null){
	params.put("txnid",txnid);
	}
	params.put("amount",amount.toString());
	params.put("firstname",userName);
	params.put("email",u.getUserEmail());
	params.put("phone",u.getUserPrimaryContact().toString());
	params.put("productinfo",fetchSelectedVehicle);
	String surl = "http://"+request.getServerName()+":"+request.getServerPort()+"/"+"ConfirmationSummary";
	//Uncomment the below line when testing the flow from local
	//String surl = "http://"+request.getServerName()+":"+request.getServerPort()+"/DealWheel/ConfirmationSummary";
	params.put("surl",surl);
	params.put("furl",pagecontext+"/BookingError.jsp");
	params.put("udf1",session.getAttribute("selectedLocation").toString());
	
	String[] hashVarSeq=hashSequence.split("\\|");
			
			for(String part : hashVarSeq)
			{
				hashString= (empty(params.get(part)))?hashString.concat(""):hashString.concat(params.get(part));
				hashString=hashString.concat("|");
			}
			hashString=hashString.concat(salt);
			System.out.println("hasString="+hashString);
			 hash=hashCal("SHA-512",hashString);
			action1=base_url.concat("/_payment");
		
	System.out.println(action1);
%>
<!-- Payment Changes end -->
	<!-- Begin page content -->
		<div class="container" id="bookingSummaryMainBlockId">
			
			<div style="border:1px solid #85b213; border-radius:4px;">
				<div class="row text-center ">
				<br>
				<span style="color: #687074; font-weight:bold;text-transform:uppercase;font-size:17.5px;padding-top:20px;text-decoration:underline;">Booking Summary</span>
				<br/><br/>
				</div>
				<div class="row container text-center">
				<p style="color: #687074; text-transform:uppercase;font-size:12px;">Proceed with Booking before your vehicle zooms away</p> 
					<div class="progress" style="width:90%; margin-left: 5%;">
    				<div class="progress-bar progress-bar-success" role="progressbar" id="progressBarId" aria-valuenow="60" aria-valuemin="0" 
         			aria-valuemax="100" style="width: 100%;">
    			</div>
			</div>
				</div>
				<div class="row text-center">
				<%if(lv.getLvclImgUrl()!=null){
					%>
				<img class="" src="<%=lv.getLvclImgUrl() %>" alt="Logo" style="width:100px;height:100px;"/>	
					<%
				}else{
					%>
				<img class="" src="https://s3-us-west-2.amazonaws.com/dealwheel/EmptyImage.png" alt="Logo" style="width:100px;height:100px;"/>
				<%
				}
				%>
				<span style="color: #687074; font-weight:500;text-transform:uppercase;font-size:14px;" id="vehicleMakeId"><%=lv.getLvclMake() %></span>
				<span style="color: #687074; font-weight:bold;text-transform:uppercase;font-size:16.5px;" id="vehicleNameId"><%=lv.getLvclName() %></span>
				<br>
				<hr style="border-color:#85b213;width:50%;">
				</div>
				<div class="row text-center">
					<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:16px;"></span> 
    						<span style="color: #687074; font-weight:500;text-transform:uppercase;" id="startDateDetailsId">
    							<span style="font-size:13.5px;"><%=sdtDay%>, </span>
    							<span style="font-size:18px;font-weight:600;"><%=sdtDateNum%> </span>
    							<span style="font-size:16px;"><%=sdtMonth%>'<%=sdtYear%></span>
    						</span>
    					<span style="padding-left:15px;padding-right:15px;color: #687074;"> -- <span style="font-weight:bold;font-size:15px;">To</span> --  </span>
    				  	<span class="glyphicon glyphicon-calendar" style="color:#85b213;font-size:16px;"></span> 
    						<span style="color: #687074; font-weight:500;text-transform:uppercase;" id="endDateDetailsId">
    							<span style="font-size:13.5px;"><%=endtDay%>, </span>
    							<span style="font-size:18px;font-weight:600;"><%=endtDateNum%> </span>
    							<span style="font-size:16px;"><%=endtMonth%>'<%=endtYear%></span>
    						</span>
    				<hr style="border-color:#85b213;width:50%;">	
    			</div>
    			<div class="row text-center">
    				<span class="glyphicon glyphicon-map-marker" style="color:#85b213;font-size:18px;"></span> 
    				<span style="color: #687074;font-size:12.8px;text-transform:uppercase;" id="addressDetailsId">
    				<%=a.getAddrLine1() %>,
    				<%=((a.getAddrLine2()!=null && (!a.getAddrLine2().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine2())))?(a.getAddrLine2()+","):"") %>  
    				<%=((a.getAddrLine3()!=null && (!a.getAddrLine3().isEmpty() || "null".equalsIgnoreCase(a.getAddrLine3()) ))?(a.getAddrLine3()+","):"") %><br/>
    				<span style="font-weight:bold;"><%=a.getAddrLocality() %></span>, 
    				<span style="font-size:16px; font-weight:bold;"><%=a.getAddrCity() %></span> - <%=a.getAddrPinCode() %>, <br/>
    				<%=a.getAddrState() %>, 
    				<%=a.getAddrCountry() %> 
    				</span>
    			  <hr style="border-color:#85b213;width:50%;">
    			</div>
    			<div class="row " style="">
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Per Day Cost = </span><span id="perDayCostId" style="color:#687074;font-weight:bold;font-size:15.5px;"><span style='font-family:Arial;'>&#8377;</span><%=bh.getBkngPerDayCost() %></span><br>
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Refundable Security Deposit = </span><span id="securityDepositId" style="color:#687074;font-weight:bold;font-size:15.5px;"><span style='font-family:Arial;'>&#8377;</span><%=bh.getBkngSecurityDeposit() %></span><br>
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Number of Booking Days = </span><span id="noOfDaysId" style="color:#687074;font-weight:bold;font-size:15.5px;"><%=noOfDays %></span><br>
    				<span style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;margin-left:31%;">Payable Amount <span style="color: rgba(217, 83, 79, 1);font-size:12px;">(No. of Days * Per Day Cost)</span> = </span><span id="payableAmount" style="color:#687074;font-weight:bold;font-size:15.5px;"><span style='font-family:Arial;'>&#8377;</span><%=(noOfDays*bh.getBkngPerDayCost())%></span><br>
    				<hr style="border-color:#85b213;width:50%;">
    			</div>
    			<div class="row text-center" >
    		
					<button type="submit" class="btn btn-info btn-md" id="paymentBtnId" onclick="submitPayuForm()">Proceed to Payment</button>
					<button type="button" class="btn btn-info btn-md" id="modifyBtnId" style="background-color: rgba(217, 83, 79, 1);" onclick="modifySearchCriteriaInReview()">+ Modify Search</button>
    				<hr style="border-color:#fff;width:50%;">
    			</div>
			</div>
			<!-- Modify Search Criteria Starts-->
			<div id="reviewModifyDivId" style="display:none;">
				<br>
				<%@ include file="commonResources/SearchCriteriaLayout"%>
				<hr style="border-color:#fff;width:50%;">
			</div>
			<!-- Modify Search Criteria ends -->
		</div>
	<!-- Payment Changes Start -->	
	<form action="https://test.payu.in/_payment" method="post" name="payuForm" style="display:none;">
	  <input type="" name="key" value="<%= merchant_key %>" />
      <input type="" name="hash" value="<%= hash %>"/>
      <input type="" name="txnid" value="<%= txnid %>" />
      <input name="amount" value="<%= (empty(params.get("amount"))) ? "" : params.get("amount") %>" />
      <input name="firstname" id="firstname" value="<%= (empty(params.get("firstname"))) ? "" : params.get("firstname") %>" />
      <input name="email" id="email" value="<%= (empty(params.get("email"))) ? "" : params.get("email") %>" />
      <input name="phone" value="<%= (empty(params.get("phone"))) ? "" : params.get("phone") %>" />
      <input name="productinfo" value="<%= (empty(params.get("productinfo"))) ? "" : params.get("productinfo") %>" size="64" />
      <input name="surl" value="<%= (empty(params.get("surl"))) ? "" : params.get("surl") %>" size="64" />
      <input name="furl" value="<%= (empty(params.get("furl"))) ? "" : params.get("furl") %>" size="64" />
      <input name="udf1" value="<%= (empty(params.get("udf1"))) ? "" : params.get("udf1") %>" size="64" />
	</form>
	<!-- Payment Changes end -->
		
<%			   }
		}
%>

	<!-- Footer inclusion starts -->
	<%@ include file="commonResources/Footer"%>
	<!-- Footer inclusion ends -->
	
</div><!--/wrap-->
		

	<ul class="nav pull-right scroll-top">
		<li>
			<a href="#" title="Scroll to top">
				<span class="glyphicon glyphicon-menu-up"></span>
			</a>
		</li>
	</ul>
	
  <!-- Time out Modal starts-->
  <div class="modal fade text-center" id="sessionTimeOutModalId" role="dialog">
    <div class="modal-dialog">
     <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-body">
          <p style="color:#687074;font-weight:500;font-size:13.5px;text-transform:uppercase;">Oopss..!! Your session has timed out.</p>
          <button type="button" class="btn btn-info btn-md" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);" onclick="sessionTimedOut()">OK</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Time out Modal ends -->

 
  <!-- Logout form -->
	<form method="post" id="logoutFormId" action="${pageContext.request.contextPath}/Logout">
		<input type="hidden" value="<%=fetchSelectedVehicle%>" name="fetchSelectedVehicle" id="fetchSelectedVehicleId"></input>
	</form>
  <!-- Logout form ends -->
  
	<!-- Including Modal Windows for Signup and Login/Logout -->
	<%@ include file="commonResources/CommonModalDivBlocks"%>

	<script>
	var propCities = '<%= CommonUtility.getValuesFromProperties("activeCities")%>';
	function submitPayuForm(){
		alert("READ THIS...!!! Use Debit Card only Cardno: 5123456789012346 CVV: 123 expiry date: may-2017 and you can provide");
		var payuForm = document.forms.payuForm;
		payuForm.submit();
	}
	</script>
	<script src="js/ReviewBookingJS.js" type="text/javascript"></script>
	<!-- Including Common JS -->
	<script src="js/CommonJS.js" type="text/javascript"></script>
	
</body>
</html>