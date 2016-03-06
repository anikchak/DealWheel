/**
 * This file contain changes related to Vendor Home tabs
 */

// Changes for My Profile tab starts
$(document).ready(function() {
	
	$('#delete_error').hide();
	$('#disable_error').hide();
	
	$("#primaryContact").keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$("#errmsgContact1").show().fadeOut(2000);
			return false;
		}
	});

	$("#secondaryContact").keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$("#errmsgContact2").show().fadeOut(2000);
			return false;
		}
	});

	$("#pinCode").keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$("#errmsgPin").show().fadeOut(2000);
			return false;
		}
	});
	
	$(".saveButton").hide();
});

//Changes for My Profile tab starts

function editFields() {
	$("#fullName").show();
	$("#fullNameSpan").hide();
	$("#primaryContact").show();
	$("#primaryContactSpan").hide();
	$("#secondaryContact").show();
	$("#secondaryContactSpan").hide();
	$("#addr1").show();
	$("#addr1Span").hide();
	$("#addr2").show();
	$("#addr2Span").hide();
	$("#addr3").show();
	$("#addr3Span").hide();
	$("#locality").show();
	$("#localitySpan").hide();
	$("#city").show();
	$("#citySpan").hide();
	$("#state").show();
	$("#stateSpan").hide();
	$("#pinCode").show();
	$("#pinCodeSpan").hide();
	$("#editBtn").hide();
	$("#saveBtn").removeAttr('disabled');
	$("#cancelBtn").show();
}

function cancelOperation(opCode) {

	resetErrorMessages();
	if (opCode == 'alert') {
		$("#cancelAlertModalId").modal({
			backdrop : 'static',
			keyboard : false
		});
	} else if (opCode == 'ok') {
		$("#fullName").val($("#fullNameSpan").text());
		$("#fullName").hide();
		$("#fullNameSpan").show();
		$("#primaryContact").val($("#primaryContactSpan").text());
		$("#primaryContact").hide();
		$("#primaryContactSpan").show();
		$("#secondaryContact").val($("#secondaryContactSpan").text());
		$("#secondaryContact").hide();
		$("#secondaryContactSpan").show();
		$("#addr1").val($("#addr1Span").text());
		$("#addr1").hide();
		$("#addr1Span").show();
		$("#addr2").val($("#addr2Span").text());
		$("#addr2").hide();
		$("#addr2Span").show();
		$("#addr3").val($("#addr3Span").text());
		$("#addr3").hide();
		$("#addr3Span").show();
		$("#locality").val($("#localitySpan").text());
		$("#locality").hide();
		$("#localitySpan").show();
		$("#city").val($("#citySpan").text());
		$("#city").hide();
		$("#citySpan").show();
		$("#state").val($("#stateSpan").text());
		$("#state").hide();
		$("#stateSpan").show();
		$("#pinCode").val($("#pinCodeSpan").text());
		$("#pinCode").hide();
		$("#pinCodeSpan").show();
		$("#editBtn").show();
		$("#saveBtn").attr('disabled', 'true');
		$("#cancelBtn").hide();
	}
}

function saveChanges() {
	var returnVal = checkForEmptyFields();
	if (returnVal == 'N') {
		// Submit Form
		$("#updateDetailsFormId").submit();
	}
}

function checkForEmptyFields() {
	resetErrorMessages();

	var emptyField = "N";
	if ($("#fullName").val() == '' || $("#fullName").val() == null) {
		$("#fullName").css("border-color", "red");
		$('#fullnameMandate').show();
		emptyField = 'Y';
	}
	if ($("#primaryContact").val() == '' || $("#primaryContact").val() == null) {
		$("#primaryContact").css("border-color", "red");
		$('#primaryContactMandate').show();
		emptyField = 'Y';
	}
	if ($("#primaryContact").val().length > 0
			&& $("#primaryContact").val().length < 10) {
		$("#primaryContact").css("border-color", "red");
		$('#incompleteContactNo1').show();
		emptyField = 'Y';
	}
	if ($("#secondaryContact").val().length > 0
			&& $("#secondaryContact").val().length < 10) {
		$("#secondaryContact").css("border-color", "red");
		$('#incompleteContactNo2').show();
		emptyField = 'Y';
	}
	if ($("#addr1").val() == '' || $("#addr1").val() == null) {
		$("#addr1").css("border-color", "red");
		$('#addr1Mandate').show();
		emptyField = 'Y';
	}
	if ($("#addr2").val() == '' || $("#addr2").val() == null) {
		$("#addr2").css("border-color", "red");
		$('#addr2Mandate').show();
		emptyField = 'Y';
	}
	if ($("#locality").val() == '' || $("#locality").val() == null) {
		$("#locality").css("border-color", "red");
		$('#localityMandate').show();
		emptyField = 'Y';
	}
	if ($("#city").val() == '' || $("#city").val() == null) {
		$("#city").css("border-color", "red");
		$('#cityMandate').show();
		emptyField = 'Y';
	}
	if ($("#state").val() == '' || $("#state").val() == null) {
		$("#state").css("border-color", "red");
		$('#stateMandate').show();
		emptyField = 'Y';
	}
	if ($("#pinCode").val() == '' || $("#pinCode").val() == null) {
		$("#pinCode").css("border-color", "red");
		$('#pinCodeMandate').show();
		emptyField = 'Y';
	}
	if ($("#pinCode").val().length > 0 && $("#pinCode").val().length < 6) {
		$("#pinCode").css("border-color", "red");
		$('#incompletePinCode').show();
		emptyField = 'Y';
	}
	return emptyField;
}

// Changes for My Profile tab ends

// Changes for My Vehicle Details Tab starts

function vehicleAction(vehId, action) {
	$("#selectedVehicleRecordId").val(vehId);
	$("#opCode").val(action);
	if (action == 'Disable') {
		$("#deleteDisableSpanId")
				.text(
						"This vehicle will be disabled and will not be visible to customer for booking. Proceed?");
		$("#alertDisableDeleteModalId").modal({
			backdrop : 'static',
			keyboard : false
		});
	} else if (action == 'Delete') {
		$("#deleteDisableSpanId")
				.text(
						"This record will be permanently deleted. Are you sure to delete this record ?");
		$("#alertDisableDeleteModalId").modal({
			backdrop : 'static',
			keyboard : false
		});
	} else if (action == 'Enable') {
		submitVehicleForm();
	} else if(action == 'ChangeCost'){
		
	} else if(action == 'ChangeDeposit'){
		
	}
}

function changeCostAndDepositButtons(vehicleId){
	$("#save"+vehicleId).show();
	$("#edit"+vehicleId).hide();
	$("#cost"+vehicleId).hide();
	$("#deposit"+vehicleId).hide();
	$("#changedCost"+vehicleId).val($("#cost"+vehicleId).text()).show();
	$("#changedDeposit"+vehicleId).val($("#deposit"+vehicleId).text()).show();
}

function changeCostAndDeposit(vehicleId){
	$("#save"+vehicleId).hide();
	$("#edit"+vehicleId).show();
	var formData = {};
	var cost = $("#changedCost"+vehicleId).val();
	var deposit = $("#changedDeposit"+vehicleId).val();
	var opCode = "Change";
	var  originalCost = $("#cost"+vehicleId).text();
	var originalDeposit = $("#deposit"+vehicleId).text();
	$("#changedCost"+vehicleId).hide();
	$("#changedDeposit"+vehicleId).hide();
	$("#cost"+vehicleId).text(cost).show();
	$("#deposit"+vehicleId).text(deposit).show();
	formData["changedCost"+vehicleId] = cost;
	formData["changedDeposit"+vehicleId] = deposit;
	formData["selectedVehicleRecordId"] = vehicleId;
	formData["opCode"] = opCode;
	$.ajax({url : 'DeleteVehicle',
		data : formData,
		type : 'POST',
		dataType : 'html',
		success : function(response) {
			if(response == "CHANGED"){
				$('#delete_disable_error').hide();
				$('#cannotDeleteDisableMsg').text("Cost & Deposit of the Vehicle edited successfully");
				$('#delete_disable_error').show();
			}else if(response == "NOTCHANGED"){
				$('#delete_disable_error').hide();
				$('#cannotDeleteDisableMsg').text("Cost & Deposit could not be updated. Please try again.");
				$('#delete_disable_error').show();
				$("#cost"+vehicleId).text(originalCost).show();
				$("#deposit"+vehicleId).text(originalDeposit).show();
			}else{
				$(location).attr('href',pageContext + response);
			}
		}
	});
}


function submitVehicleForm() {
	var selectedVehicleRecordId = $("#selectedVehicleRecordId").val();
	var opCode = $("#opCode").val();
	$.ajax({
		url : 'DeleteVehicle',
		data : {selectedVehicleRecordId:selectedVehicleRecordId, opCode:opCode},
		type : 'POST',
		dataType : 'html',
		success : function(response) {
			if(response == "BOOKINGEXISTDELETE"){
				$('#delete_disable_error').hide();
				$('#cannotDeleteDisableMsg').text("Future booking(s) exist for this vehicle. Cannot remove this vehicle.");
				$('#delete_disable_error').show();
			}else if(response == "BOOKINGEXISTDISABLE"){
				$('#delete_disable_error').hide();
				$('#cannotDeleteDisableMsg').text("Future booking(s) exist for this vehicle. Cannot disable this vehicle.");
				$('#delete_disable_error').show();
			}else{
				$(location).attr('href',pageContext + response);
			}
		},
		error : function(request, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function openAddVehicleModal() {
	$('#defaultPickupAddr').show();
	$("#listVehiclesBlockId").hide();
	$("#addVehicleBlockId").show();
}
function closeAddBlock() {

	$('#vehicleManufacturer').val('defaultMake');
	$("#vehicleManufacturer").css("border-color", "");
	$('#vehMakeMandateAdd').hide();
	$('#vehicleName').val('defaultName');
	$("#vehicleName").css("border-color", "");
	$('#vehNameMandateAdd').hide();
	$('#registrationNo').val('');
	$('#registrationNoMandateAdd').hide();
	$("#registrationNo").css("border-color", "");
	$('#yearOfManufacture').val('');
	$('#yearOfManufactureMandateAdd').hide();
	$("#YOMAdd").hide();
	$('#invalidYearAdd').hide();
	$("#yearOfManufacture").css("border-color", "");
	$('#perDayCost').val('');
	$('#perDayCostMandateAdd').hide();
	$("#PDCAdd").hide();
	$("#perDayCost").css("border-color", "");
	$('#securityDeposit').val('');
	$('#securityDepositMandateAdd').hide();
	$("#SDAdd").hide();
	$("#securityDeposit").css("border-color", "");
	$('#addr1MandateAdd').hide();
	$("#addr1Add").css("border-color", "");
	$('#addr1Add').val('');
	$('#addr2MandateAdd').hide();
	$("#addr2Add").css("border-color", "");
	$('#addr2Add').val('');
	$("#addr3Add").val('');
	$('#localityMandateAdd').hide();
	$("#localityAdd").css("border-color", "");
	$('#localityAdd').val('');
	$('#cityMandateAdd').hide();
	$("#cityAdd").css("border-color", "");
	$('#cityAdd').val('defaultCity');
	$('#stateMandateAdd').hide();
	$("#stateAdd").css("border-color", "");
	$('#stateAdd').val('');
	$('#pinCodeMandateAdd').hide();
	$('#add_vehicle_error').hide();
	$("#pinCodeAdd").hide();
	$("#pinCodeAdd").css("border-color", "");
	$('#pinCodeAdd').val('');
	$("#useDifferentAddress").removeAttr('checked');
	$("#differentAddrBlock").hide();

	$("#listVehiclesBlockId").show();
	$("#addVehicleBlockId").hide();

}

function validateData() {
	var emptyField = 'N';
	var vehicleManufacturer = $("#vehicleManufacturer").val();
	var vehicleName = $("#vehicleName").val();
	var registrationNo = $("#registrationNo").val();
	var yearOfManufacture = $("#yearOfManufacture").val();
	var perDayCost  = $("#perDayCost").val();
	var securityDeposit = $("#securityDeposit").val();
	var useDifferentAddress = $("#useDifferentAddress").is(":checked");
	var addr1Add = $("#addr1Add").val();
	var addr2Add = $("#addr2Add").val();
	var addr3Add = $("#addr3Add").val();
	var localityAdd = $("#localityAdd").val();
	var cityAdd = $("#cityAdd").val();
	var stateAdd = $("#stateAdd").val();
	var pinCodeAdd = $("#pinCodeAdd").val();
	
	resetErrorMsgsVehicleDetails();
	$('#vehMakeMandateAdd').hide();
	$("#vehicleName").css("border-color", "");
	$('#vehNameMandateAdd').hide();
	$('#registrationNoMandateAdd').hide();
	$("#registrationNo").css("border-color", "");
	$('#yearOfManufactureMandateAdd').hide();
	$('#invalidYearAdd').hide();
	$("#yearOfManufacture").css("border-color", "");
	$('#perDayCostMandateAdd').hide();
	$("#perDayCost").css("border-color", "");
	$('#securityDepositMandateAdd').hide();
	$("#securityDeposit").css("border-color", "");

	if (vehicleManufacturer == 'defaultMake' || vehicleManufacturer == null) {
		$("#vehicleManufacturer").css("border-color", "red");
		$('#vehMakeMandateAdd').show();
		emptyField = 'Y';
	}
	if (vehicleName == 'defaultName' || vehicleName == null) {
		$("#vehicleName").css("border-color", "red");
		$('#vehNameMandateAdd').show();
		emptyField = 'Y';
	}
	if (registrationNo == '' || registrationNo == null) {
		$("#registrationNo").css("border-color", "red");
		$('#registrationNoMandateAdd').show();
		emptyField = 'Y';
	}
	if (yearOfManufacture == ''|| yearOfManufacture == null) {
		$("#yearOfManufacture").css("border-color", "red");
		$('#yearOfManufactureMandateAdd').show();
		emptyField = 'Y';
	}
	if(yearOfManufacture.length > 5 ||(yearOfManufacture.length > 0 && yearOfManufacture.length < 4)){
		$("#yearOfManufacture").css("border-color", "red");
		$('#invalidYearAdd').show();
		emptyField = 'Y';
	}
	if (perDayCost == '' || perDayCost == null) {
		$("#perDayCost").css("border-color", "red");
		$('#perDayCostMandateAdd').show();
		emptyField = 'Y';
	}
	if (securityDeposit == '' || securityDeposit == null) {
		$("#securityDeposit").css("border-color", "red");
		$('#securityDepositMandateAdd').show();
		emptyField = 'Y';
	}

	if (useDifferentAddress) {
		if (addr1Add == '' || addr1Add == null) {
			$("#addr1Add").css("border-color", "red");
			$('#addr1MandateAdd').show();
			emptyField = 'Y';
		}
		if (addr2Add == '' || addr2Add == null) {
			$("#addr2Add").css("border-color", "red");
			$('#addr2MandateAdd').show();
			emptyField = 'Y';
		}
		if (localityAdd == '' || localityAdd == null) {
			$("#localityAdd").css("border-color", "red");
			$('#localityMandateAdd').show();
			emptyField = 'Y';
		}
		if (cityAdd == 'defaultCity' || cityAdd == null) {
			$("#cityAdd").css("border-color", "red");
			$('#cityMandateAdd').show();
			emptyField = 'Y';
		}
		if (stateAdd == '' || stateAdd == null) {
			$("#stateAdd").css("border-color", "red");
			$('#stateMandateAdd').show();
			emptyField = 'Y';
		}
		if (pinCodeAdd == '' || pinCodeAdd == null) {
			$("#pinCodeAdd").css("border-color", "red");
			$('#pinCodeMandateAdd').show();
			emptyField = 'Y';
		}
	}
	if (emptyField == 'N') {
		$.ajax({
			url : 'AddVehicle',
			data : {
				vehicleManufacturer : vehicleManufacturer,
				vehicleName : vehicleName,
				registrationNo : registrationNo,
				yearOfManufacture : yearOfManufacture,
				perDayCost  : perDayCost,
				securityDeposit : securityDeposit,
				useDifferentAddress : useDifferentAddress,
				addr1Add : addr1Add,
				addr2Add : addr2Add,
				addr3Add : addr3Add,
				localityAdd : localityAdd,
				cityAdd : cityAdd,
				stateAdd : stateAdd,
				pinCodeAdd : pinCodeAdd
			},
			type : 'POST',
			dataType : 'html',
			success : function(response) {
				if(response == "ADDVEHICLEERROR"){
					$('#add_vehicle_error').hide();
					$('#addVehicleErrorMsg').text("Ahoy there! Vehicle with same registration number already listed with us.");
					$('#add_vehicle_error').show();
				}else{
					$(location).attr('href',pageContext + response);
				}
			},
			error : function(request, textStatus, errorThrown) {
				alert(errorThrown);
			}
		});
	}
}
function showDifferentAddrBlock() {
	var useDifferentAddress = $("#useDifferentAddress").is(":checked");
	if (useDifferentAddress) {
		resetErrorMsgsVehicleDetails();
		$("#addr1Add").val('');
		$("#addr2Add").val('');
		$("#addr3Add").val('');
		$("#localityAdd").val('');
		$("#cityAdd").val('defaultCity');
		$("#stateAdd").val('');
		$("#pinCodeAdd").val('');
		$("#differentAddrBlock").show();
		$('#defaultPickupAddr').hide();
	} else {
		$("#differentAddrBlock").hide();
		$('#defaultPickupAddr').show();
	}
}

function resetErrorMsgsVehicleDetails() {
	$('#addr1MandateAdd').hide();
	$("#addr1Add").css("border-color", "");
	$('#addr2MandateAdd').hide();
	$("#addr2Add").css("border-color", "");
	$('#localityMandateAdd').hide();
	$("#localityAdd").css("border-color", "");
	$('#cityMandateAdd').hide();
	$("#cityAdd").css("border-color", "");
	$('#stateMandateAdd').hide();
	$("#stateAdd").css("border-color", "");
	$('#pinCodeMandateAdd').hide();
	$("#pinCodeAdd").css("border-color", "");
	$('#invalidYearAdd').hide();
	$('#add_vehicle_error').hide();
}

$(document).ready(function() {

	$('#useDifferentAddress').click(function() {
		$("#differentAddress").toggle(this.checked);
	});

	$('#vehicleManufacturer').change(function() {
		loadVehicleNames();
	});
	
	$("#yearOfManufacture").keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$("#YOMAdd").show().fadeOut(2000);
			return false;
		}
	});
	
	$("#perDayCost").keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$("#PDCAdd").show().fadeOut(2000);
			return false;
		}
	});
	
	$("#securityDeposit").keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$("#SDAdd").show().fadeOut(2000);
			return false;
		}
	});
	
	$("#pinCodeAdd").keypress(function(e) {
		if (e.which != 8 && e.which != 0 && (e.which < 48 || e.which > 57)) {
			$("#errmsgPinAdd").show().fadeOut(2000);
			return false;
		}
	});
});
function loadVehicleNames() {
	$('#vehicleName').empty();
	var dataToBeSent = {
		vehicleMake : $("#vehicleManufacturer").val()
	};
	var vehMake = $("#vehicleManufacturer").val();
	if (vehMake == "defaultMake") {
		$('#vehicleName').append(
				$('<option></option>').val("defaultName")
						.html("Select Vehicle"));
		return;
	} 
	$.ajax({
		url : 'LoadVehicleNames',
		data : dataToBeSent,
		type : 'POST',
		dataType : 'html',
		success : function(response) {
			var splitResult = response.split(":");
			$('#vehicleName').append(
					$('<option></option>').val("defaultName").html(
							"Select Vehicle"));
			for (var i = 0; i < splitResult.length; i++) {
				$('#vehicleName').append(
						$('<option></option>').val(splitResult[i]).html(
								splitResult[i]));
			}
		},
		error : function(request, textStatus, errorThrown) {
			alert(errorThrown);
		}
	});
}

function selectState(){
	var citySelected = $("#cityAdd").val();
	if(citySelected == 'Bengaluru'){
		$("#stateAdd").val('Karnataka');
	}else if(citySelected == 'Pune'){
		$("#stateAdd").val('maharashtra');
	}else if(citySelected == 'defaultCity'){
		$("#stateAdd").val('');
	}
}
//Changes for My Vehicle Details Tab ends

function resetErrorMessages() {
	$('#fullnameMandate').hide();
	$("#fullName").css("border-color", "");
	$('#primaryContactMandate').hide();
	$("#primaryContact").css("border-color", "");
	$("#incompleteContactNo1").hide();
	$("#incompleteContactNo2").hide();
	$('#addr1Mandate').hide();
	$("#addr1").css("border-color", "");
	$('#addr2Mandate').hide();
	$("#addr2").css("border-color", "");
	$('#localityMandate').hide();
	$("#locality").css("border-color", "");
	$('#cityMandate').hide();
	$("#city").css("border-color", "");
	$('#stateMandate').hide();
	$("#state").css("border-color", "");
	$('#pinCodeMandate').hide();
	$("#pinCode").css("border-color", "");
}

//Changes for My Bookings tab starts
function vendorCancellation(bookingId){
	$("#bookingToCancel").val(bookingId);
	$("#confirmVendorCancelId").modal({
		backdrop: 'static',
		keyboard: false
	});
}
function cancelVendorBooking(val){
	if(val=='YES'){
		var formData = $("#vendorBookingCancelForm").serialize();
		$.ajax({
	        url: "CancelBookingForVendor" ,
	        type: "post",
	        data : formData,
	        success: function(response){
	        	var respList = response.split(",");
	        	$.post("TriggerEmail",
	        			{
	        				emailType : "CANCEL_BOOKING_BY_VENDOR",
	        				emailAddress : respList[0],
	        				list : respList[1]+","+respList[2]+","+respList[3]+","+respList[4]				
	        			},
	        			function(responseText) {
	        				$.post("TriggerEmail",
	        	        			{
	        	        				emailType : "CANCEL_BOOKING_TO_USER",
	        	        				emailAddress : respList[5],
	        	        				list : respList[1]+","+respList[2]+","+respList[3]+","+respList[0]				
	        	        			});
	        			});
	        }
	      });
	}else{
		$("#confirmVendorCancelId").hide();
	}
}
//Changes for My Bookings tab ends

