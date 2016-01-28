<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="commonResources/CommonViewImports"%>
<%@page import="dao.VehicleDAOImpl"%>
<%@page import="dao.ListedVehicleDAOImpl"%>
<!DOCTYPE html>
<html>

<head>
<META Http-Equiv="Cache-Control" Content="no-cache"/>
<META Http-Equiv="Pragma" Content="no-cache"/>
<META Http-Equiv="Expires" Content="0"/>
<link href="css/LandingPageCSS.css" rel="stylesheet" type="text/css" />
<title>DealWheel: Welcome Vendor</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="commonResources/CommonJSCSSInclude"%>
<script type="text/javascript">
var pageContext = '<%=request.getContextPath()%>';

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
	String comingFromPage = (String)session.getAttribute("currentPage");
	if("ReviewBooking".equalsIgnoreCase(comingFromPage)){
		String lockedBookingId = (String)session.getAttribute("tempLockedVehicle");
		System.out.println("Landing Page-lockedBookingId: "+lockedBookingId);
		new CustomerControllerService().cleanBookingUsingTempBookingId(lockedBookingId);
	}
	session.setAttribute("currentPage","VendorHome");
	new CustomerControllerService().cleanBookings();
	%>
	<!-- Wrap all page content here -->
	<div id="wrap" style="min-height:85%;">
		<!-- Nav bar inclusion starts -->
		<%@ include file="commonResources/NavigationBar"%>
		<!-- Nav bar inclusion ends -->
		<!-- Begin page content -->
		<% 
		User user = (User)session.getAttribute(GenericConstant.USER_MODEL);
		Address address = (Address)session.getAttribute(GenericConstant.ADDRESS_MODEL);
		%>
		<div class="container">
		<br>
		<br>
		<h3 class="text-center" style="color:#687074;">Welcome <%=user.getUserName() %></h3>
		<br>
  			<ul class="nav nav-tabs">
  				<li><a data-toggle="tab" href="#myProfile" style="color:#687074;">My Profile</a></li>
    			<li class="active"><a data-toggle="tab" href="#vehicleDetails" style="color:#687074;">My Vehicle Details</a></li>
    			<li><a data-toggle="tab" href="#bookingHistory" style="color:#687074;">Booking History</a></li>
    			<li><a data-toggle="tab" href="#paymentHistory" style="color:#687074;">Payment History</a></li>
  			</ul>
  		<!-- My Profile starts -->	
		<div class="tab-content">
    	<div id="myProfile" class="tab-pane fade ">
    	<%@include file="commonResources/VendorMyProfile" %>
		</div>
    	<!-- My Profile ends -->
    	<!-- My Vehicle Details start -->
    	<div id="vehicleDetails" class="tab-pane fade in active">
    		<% 
    		List<Object[]> vehicleDetailList = new VehicleDAOImpl<Vehicle>().getVehicleDetailsForUserId(user.getUserId()); 
    		if(vehicleDetailList!=null && vehicleDetailList.size()>0){
    		%>
    		<br>
    		<div id="listVehiclesBlockId">
    		<div class="table-responsive">
    		<form action="${pageContext.request.contextPath}/DeleteVehicle" method="post" name="listedVehicleAction" id="listedVehicleActionFormId">
    		
      			<table class="table table-hover text-center">
      	 		<thead>
      	 			<tr>
      	 				<th class="text-center"><span style="color:#687074;font-size:12px;">Vehicle</span></th>
        				<th class="text-center"><span style="color:#687074;font-size:12px;">Registration#</span></th>
        				<th class="text-center"><span style="color:#687074;font-size:12px;">Manufacture Year</span></th>
        				<th class="text-center"><span style="color:#687074;font-size:12px;">Cost/Day</span></th>
        				<th class="text-center"><span style="color:#687074;font-size:12px;">Security Deposit</span></th>
        				<th class="text-center"><span style="color:#687074;font-size:12px;">Pickup/Dropff Location</span></th>
        				<th class="text-center"><span style="color:#687074;font-size:12px;">Vehicle Status</span></th>
        				<th class="text-center"><span style="color:#687074;font-size:12px;">Action</span></th>
      	 			</tr>
      	 		</thead>
      	 		<tbody>
      	 			<% 
      	 			for(Object[] vehicleDetail : vehicleDetailList ){ 
					%>
      	 			<tr>
      	 			<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= (String)vehicleDetail[1] %><br><%= (String)vehicleDetail[0] %></span></td>
      	 			<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= (String)vehicleDetail[2] %></span></td>
      	 			<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= (String)vehicleDetail[3] %></span></td>
      	 			<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= (Integer)vehicleDetail[4] %></span></td>
      	 			<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%= (Integer)vehicleDetail[5] %></span></td>
      	 			<td><span style="color:#687074;font-size:11px;text-transform:uppercase;"><%=(String)vehicleDetail[6]+", " %><%=(String)vehicleDetail[7]+", " %><br><%=(String)vehicleDetail[8]+", " %>
      	 				<%=(String)vehicleDetail[9]+", " %><br><%=(String)vehicleDetail[10]+", " %><%=(String)vehicleDetail[11]+", " %><br><%=(String)vehicleDetail[12]+" - " %><%=(Integer)vehicleDetail[13] %>
      	 				</span>
      	 			</td>
      	 			<td>
      	 			<% 
      	 			boolean isDisabled = (Boolean)vehicleDetail[15]; 
					   if(isDisabled){
					 %>
					 <span class="label label-danger" data-toggle="tooltip" title="This vehicle will not be shown to the customer dusring search.">Disabled</span>
					 <%
					   }else{
					 %>
					 <span class="label label-success">Available</span>
					 <%
					   }
					 %>
      	 			</td>
      	 			<td>
      	 				<% 
      	 				boolean isDisabledVeh = (Boolean)vehicleDetail[15]; 
					    if(isDisabledVeh){
					 	%>
					 	<div class="row">
      	 				<button class="btn btn-xs btn-success" data-toggle="tooltip" title="Use this to show the vehicle to the customer." onclick="vehicleAction('<%=(BigInteger)vehicleDetail[14] %>','Enable')">Enable</button>
      	 				<div class="row" style="padding:3px;">
      	 				<button type="button" class="btn btn-xs btn-danger"  data-toggle="tooltip" title="Use this to completely remove the vehicle record." onclick="vehicleAction('<%=(BigInteger)vehicleDetail[14] %>','Delete')">Remove</button>
      	 				</div>
      	 				</div>
      	 				<%
					   	}else{
					 	%>
      	 				<div class="row" style="padding:3px;">
      	 				<button type="button" class="btn btn-xs btn-warning" data-toggle="tooltip" title="Use this to temporarily hide the vehicle from getting shown to the customer" onclick="vehicleAction('<%=(BigInteger)vehicleDetail[14] %>','Disable')">Disable</button>
      	 				</div>
      	 				<div class="row" style="padding:3px;">
      	 				<button type="button" class="btn btn-xs btn-danger"  data-toggle="tooltip" title="Use this to completely remove the vehicle record." onclick="vehicleAction('<%=(BigInteger)vehicleDetail[14] %>','Delete')">Remove</button>
      	 				</div>
      	 				<%
      	 				}
      	 				%>
      	 			</td>
      	 			</tr>
      	 			<%
      	 			}
      	 			%>
      	 		</tbody>
      	 		</table>
      	 		<input type="hidden" id="selectedVehicleRecordId" name="selectedVehicleRecordId" />
      	 		<input type="hidden" id="opCode" name="opCode" />
      	 		</form>
      	 	</div>
    		<%    			
    		}else{
    		%>
    		<br>
    		<span style="color:rgba(217, 83, 79, 1);font-size:14px;text-transform:uppercase;">No vehicles added yet.</span>
    		<br>
    		<%	
    		}
    		%>
    		<br>
    		<button type="button" class="btn btn-info btn-md pull-right" onclick="openAddVehicleModal()" ><span class="glyphicon glyphicon-plus"></span> Add a new vehicle</button>
    		<br>
    	 </div>
    	 
    	<!-- Empty Error message pop-up starts-->
		<div class="modal fade text-center" id="alertDisableDeleteModalId" role="dialog">
   		 <div class="modal-dialog">
    		 <!-- Modal content-->
     		 <div class="modal-content">
        		<div class="modal-body">
          		<p style="color:#687074;font-weight:bold;font-size:14px;text-transform:uppercase;"><span id="deleteDisableSpanId"></span></p>
          		<button type="button" class="btn btn-info btn-sm" data-dismiss="modal" onclick="submitVehicleForm()" style="">Yes</button>
          		<button type="button" class="btn btn-info btn-sm" data-dismiss="modal" style="background-color: rgba(217, 83, 79, 1);">No</button>
        		</div>
      		</div>
    	 </div>
  		</div>
	<!-- Empty Error message pop-up ends -->
	
	<!-- Add Vehicle Block Starts -->

	<div class="panel-group vehicleDisplay" style="width:70%;display:none;" id="addVehicleBlockId">
    	<div class="panel panel-default ">
		 <div class="panel-heading">
      			<span style="font-weight: bold;font-size: 16px; color: #687074;">Add Vehicle Details</span>
      	 </div>
        <div class="panel-body">
        	<div class="row">
      			<p style="font-size: 12px;color: rgba(217, 83, 79, 1);float:right;margin-right:5px;font-weight: bold;">All fields marked (*) are mandatory.</p>
      		</div>
          <form role="form" action="${pageContext.request.contextPath}/AddVehicle" method="post" id="addVehicleFormId">
          	<div class="form-group row">
          	<div class="col-sm-2" style="">
          	<label for="" style="color: rgba(217, 83, 79, 1);font-size:12px;">Select Manufacturer*</label>
          	</div>
          	<div class="col-sm-4" style="">
          	
          	  <select class="form-control" name="vehicleManufacturer"  id="vehicleManufacturer">
          	  <%! 
					ListedVehicleDAOImpl<ListedVehicle> lvDAO = new ListedVehicleDAOImpl<ListedVehicle>();
					List<String>  vehicleManufacturers = lvDAO.getVehiclesMakers();
					List<String>   vehicleNames = new ArrayList<String>();
			%>
				<option value="defaultMake">Select Manufacturer</option>
			<%	 
					for(String vehicleManufacturer : vehicleManufacturers)
					{
			%>
          	 	<option value="<%=vehicleManufacturer %>"><%=vehicleManufacturer %></option>
    			<%
					}
				%>
				</select>
			   <span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none;" id="vehMakeMandateAdd">Field cannot be empty</span>		
			 </div>
			 <div class="col-sm-2" style="">
          	<label for="" style="color: rgba(217, 83, 79, 1);font-size:12px;">Select Vehicle*</label>
          	</div>
          	<div class="col-sm-3">
          	 <select class="form-control" name="vehicleName"  id="vehicleName">
          	 	<option value="defaultName">Select Vehicle</option>
          	  <% 
          		for(String vehicle : vehicleNames)	
					{
				%>
          	 	<option value="<%=vehicle %>"><%=vehicle %></option>
    			<%
					}
				%>
				</select>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none;" id="vehNameMandateAdd">Field cannot be empty</span>		
			 </div>
			 
			 </div>	
			 <div class="form-group row" >
      			<div class="col-sm-2" style="">
    			<label for="regNoAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Registration #*</label>
    			</div>
    			<div class="col-sm-4 ">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-barcode"></span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="registrationNo" name="registrationNo" value= "" placeholder="Registration No">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none" id="registrationNoMandateAdd">Field cannot be empty</span>
				</div>
				<div class="col-sm-2" style="">
				<label for="makeYearAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Year of Manufacture*</label>
    			</div>
    			<div class="col-sm-3">
    			<div class="input-group">
    				<span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
    				<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="yearOfManufacture" name="yearOfManufacture" value= "" placeholder="Year">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none" id="yearOfManufactureMandateAdd">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row" >
      			<div class="col-sm-2" style="">
    			<label for="perDayCostAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Per Day Cost*</label>
    			</div>
    			<div class="col-sm-4 ">
    			<div class="input-group">
					<span class="input-group-addon"><span style='font-family:Arial;'>&#8377;</span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="perDayCost" name="perDayCost" value= "" placeholder="Cost/Day (INR Only)">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none" id="perDayCostMandateAdd">Field cannot be empty</span>
				</div>
				<div class="col-sm-2" style="">
				<label for="depositAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Security Deposit*</label>
    			</div>
    			<div class="col-sm-3">
    			<div class="input-group">
    				<span class="input-group-addon"><span style='font-family:Arial;'>&#8377;</span></span>
    				<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="securityDeposit" name="securityDeposit" value= "" placeholder="Deposit (INR)">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none" id="securityDepositMandateAdd">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row" id="defaultPickupAddr">
      			<div class="col-sm-2" style="">
    			<label for="pickupAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Pickup Location*</span></label>
    			</div>
    			<div class="col-sm-10 ">
    			<div class="" >
					<span class=""><span class="glyphicon glyphicon-map-marker"></span> </span>
					<span class="" style="color:#687074;font-size:12px;text-transform:uppercase;">
						<%=address.getAddrLine1() %>, <%=address.getAddrLine2() %>, <%=address.getAddrLine3() %>, 
      	 				<%=address.getAddrLocality()%>, <%=address.getAddrCity() %>, <%=address.getAddrState() %>, 
      	 				<%=address.getAddrCountry() %> - <%=address.getAddrPinCode() %>
      	 			</span>
				</div>
				</div>
  				</div>
  				<div class="form-group row checkbox">
  					<div class="col-sm-12">
  					<label style="color:#687074;"><input type="checkbox" id="useDifferentAddress" name="useDifferentAddress" value="Yes" onclick="showDifferentAddrBlock()">Use different address for <span style="font-weight: bold;color: rgba(217, 83, 79, 1);">PICKUP/DROPOFF</span></label>
  					</div>
				</div>
      	 			
			   <div id="differentAddrBlock" style="display:none;">	 
			    <hr style="border-color:#85b213;">				
  				<div class="form-group row" >
      			<div class="col-sm-2" style="">
    			<label for="addr1Add" style="color: rgba(217, 83, 79, 1);font-size:12px;">Address Line 1*</span></label>
    			</div>
    			<div class="col-sm-10 ">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="addr1Add" name="addr1Add" value= "" placeholder="Flat/House No, Floor, Building, Company Name">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none" id="addr1MandateAdd">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row" >
      			<div class="col-sm-2" >
    			<label for="addr2Add" style="color: rgba(217, 83, 79, 1);font-size:12px;">Address Line 2*</label>
    			</div>
    			<div class="col-sm-10" >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="addr2Add" name="addr2Add" value= "" placeholder="Colony/Society, Street">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none;" id="addr2MandateAdd">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2">
    			<label for="addr3Add" style="color: rgba(217, 83, 79, 1);font-size:12px;">Address Line 3</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-list"></span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="addr3Add" name="addr3Add" value= "" placeholder="Address line 3 (optional)">
				</div>
				</div>
  				</div>
  				
  				<div class="form-group row" >
      			<div class="col-sm-2">
    			<label for="localityAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Locality*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="localityAdd" name="localityAdd" value="" placeholder="Locality">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none;" id="localityMandateAdd">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row" >
      			<div class="col-sm-2" >
    			<label for="cityAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Town/City*</label>
    			</div>
    			<div class="col-sm-10 ">
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<select class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" name="cityAdd"  id="cityAdd" onchange="selectState()">
          	 		<option value="defaultCity" selected="selected">Select City</option>
          	  		<% 
          	  		String serviceCities = CommonUtility.getValuesFromProperties("activeCities");
          	  		String showCities[] = serviceCities.split("#",-1);
          				for(String cities : showCities)	
						{
					%>
          	 			<option value="<%=cities %>"><%=cities %></option>
    				<%
						}
					%>
				</select>
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none;" id="cityMandateAdd">Field cannot be empty</span>
				
				</div>
  				</div>
  				
  				<div class="form-group row" >
      			<div class="col-sm-2">
    			<label for="stateAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">State*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-map-marker"></span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" name="stateAdd" id="stateAdd" value="" placeholder="State" readonly/>
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px;display:none;" id="stateMandateAdd">Field cannot be empty</span>
				</div>
  				</div>
  				
  				<div class="form-group row">
      			<div class="col-sm-2" >
    			<label for="pincodeAdd" style="color: rgba(217, 83, 79, 1);font-size:12px;">Pincode*</label>
    			</div>
    			<div class="col-sm-10 " >
    			<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-screenshot"></span></span>
					<input class="form-control" style= "font-size: 12px; color: #687074;text-transform: uppercase;" type="text" id="pinCodeAdd" name="pinCodeAdd" value="" placeholder="Pincode">
				</div>
				<span style="color: rgba(217, 83, 79, 1);font-size:10px; display:none;" id="pinCodeMandateAdd">Field cannot be empty</span>
				</div>
  				</div>
  			</div>
          </form>
        </div>
        <div class="text-center">
        <hr style="border-color:#85b213;">
          <button type="button" class="btn btn-info btn-sm " data-dismiss="modal" onclick="validateData()" style=""><span class="glyphicon glyphicon-plus"></span> Add</button>
          <button type="button" class="btn btn-info btn-sm " data-dismiss="modal" onclick="closeAddBlock()" style="background-color: rgba(217, 83, 79, 1);"><span class="glyphicon glyphicon-remove"></span> Close</button>
        </div>
        <br>
      </div>
    </div>
	
	<!-- Add Vehicle Modal Ends -->
    	</div>
    	<!-- My Vehicle Details end -->
    	<!-- My Booking History start -->
    	<div id="bookingHistory" class="tab-pane fade ">
    		Booking History
    	</div>
    	<!-- My Booking History end -->
    	<!-- My Payment History start -->
    	<div id="paymentHistory" class="tab-pane fade ">
    		Payment History
    	</div>
    	<!-- My Payment History end -->
    	</div>
		</div>
	</div>
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
	<script src="js/VendorHomeJS.js" type="text/javascript"></script>
	<style>
	  	.nav-tabs{border-bottom:1px solid #85b213}
		.nav-tabs > li.active > a, .nav-tabs > li.active > a:hover, .nav-tabs > li.active > a:focus{
		border-right:1px solid #85b213;
		border-left:1px solid #85b213;
		border-top:1px solid #85b213;
		}
		.panel-default{border-color:#85b213}
		.panel-default>.panel-heading{border-bottom:1px solid #85b213}
		.vehicleDisplay{padding-right:15px;padding-left:15px;margin-right:auto;margin-left:auto}@media (min-width:768px){.vehicleDisplay{width:750px}}@media (min-width:992px){.vehicleDisplay{width:750px}}@media (min-width:1200px){.vehicleDisplay{width:750px}}
	 </style>
	
	
 </body>
</html>