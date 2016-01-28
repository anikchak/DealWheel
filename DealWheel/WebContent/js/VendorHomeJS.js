/**
 * This file contain changes related to Vendor Home tabs
 */

// Changes for My Profile tab starts
$(document).ready(function() {
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
	}

}
function submitVehicleForm() {
	$("#listedVehicleActionFormId").submit();
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
	$("#yearOfManufacture").css("border-color", "");
	$('#perDayCost').val('');
	$('#perDayCostMandateAdd').hide();
	$("#perDayCost").css("border-color", "");
	$('#securityDeposit').val('');
	$('#securityDepositMandateAdd').hide();
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
	$("#pinCodeAdd").css("border-color", "");
	$('#pinCodeAdd').val('');
	$("#useDifferentAddress").removeAttr('checked');
	$("#differentAddrBlock").hide();

	$("#listVehiclesBlockId").show();
	$("#addVehicleBlockId").hide();

}

function validateData() {
	var emptyField = 'N';

	resetErrorMsgsVehicleDetails();
	$('#vehMakeMandateAdd').hide();
	$("#vehicleName").css("border-color", "");
	$('#vehNameMandateAdd').hide();
	$('#registrationNoMandateAdd').hide();
	$("#registrationNo").css("border-color", "");
	$('#yearOfManufactureMandateAdd').hide();
	$("#yearOfManufacture").css("border-color", "");
	$('#perDayCostMandateAdd').hide();
	$("#perDayCost").css("border-color", "");
	$('#securityDepositMandateAdd').hide();
	$("#securityDeposit").css("border-color", "");

	if ($("#vehicleManufacturer").val() == 'defaultMake'
			|| $("#vehicleManufacturer").val() == null) {
		$("#vehicleManufacturer").css("border-color", "red");
		$('#vehMakeMandateAdd').show();
		emptyField = 'Y';
	}
	if ($("#vehicleName").val() == 'defaultName'
			|| $("#vehicleName").val() == null) {
		$("#vehicleName").css("border-color", "red");
		$('#vehNameMandateAdd').show();
		emptyField = 'Y';
	}
	if ($("#registrationNo").val() == '' || $("#registrationNo").val() == null) {
		$("#registrationNo").css("border-color", "red");
		$('#registrationNoMandateAdd').show();
		emptyField = 'Y';
	}
	if ($("#yearOfManufacture").val() == ''
			|| $("#yearOfManufacture").val() == null) {
		$("#yearOfManufacture").css("border-color", "red");
		$('#yearOfManufactureMandateAdd').show();
		emptyField = 'Y';
	}
	if ($("#perDayCost").val() == '' || $("#perDayCost").val() == null) {
		$("#perDayCost").css("border-color", "red");
		$('#perDayCostMandateAdd').show();
		emptyField = 'Y';
	}
	if ($("#securityDeposit").val() == ''
			|| $("#securityDeposit").val() == null) {
		$("#securityDeposit").css("border-color", "red");
		$('#securityDepositMandateAdd').show();
		emptyField = 'Y';
	}

	var check = $("#useDifferentAddress").is(":checked");
	if (check) {
		if ($("#addr1Add").val() == '' || $("#addr1Add").val() == null) {
			$("#addr1Add").css("border-color", "red");
			$('#addr1MandateAdd').show();
			emptyField = 'Y';
		}
		if ($("#addr2Add").val() == '' || $("#addr2Add").val() == null) {
			$("#addr2Add").css("border-color", "red");
			$('#addr2MandateAdd').show();
			emptyField = 'Y';
		}
		if ($("#localityAdd").val() == '' || $("#localityAdd").val() == null) {
			$("#localityAdd").css("border-color", "red");
			$('#localityMandateAdd').show();
			emptyField = 'Y';
		}
		if ($("#cityAdd").val() == 'defaultCity' || $("#cityAdd").val() == null) {
			$("#cityAdd").css("border-color", "red");
			$('#cityMandateAdd').show();
			emptyField = 'Y';
		}
		if ($("#stateAdd").val() == '' || $("#stateAdd").val() == null) {
			$("#stateAdd").css("border-color", "red");
			$('#stateMandateAdd').show();
			emptyField = 'Y';
		}
		if ($("#pinCodeAdd").val() == '' || $("#pinCodeAdd").val() == null) {
			$("#pinCodeAdd").css("border-color", "red");
			$('#pinCodeMandateAdd').show();
			emptyField = 'Y';
		}
	}
	if (emptyField == 'N') {
		$('#addVehicleFormId').submit();
	}
}
function showDifferentAddrBlock() {
	var check = $("#useDifferentAddress").is(":checked");
	if (check) {
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
}

$(document).ready(function() {

	$('#useDifferentAddress').click(function() {
		$("#differentAddress").toggle(this.checked);
	});

	$('#vehicleManufacturer').change(function() {
		loadVehicleNames();
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
			$('#vehicleName').append(
					$('<option></option>').val("vehNameOther").html("Other"));
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
		$("#vendorBookingCancelForm").submit();
	}else{
		$("#confirmVendorCancelId").hide();
	}
}
//Changes for My Bookings tab ends
