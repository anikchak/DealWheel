/**
 * This file contains JS realted to Vendor Registration page
 */

$(document).ready(function() {
	$('#dob').datepicker({
		format : "dd/mm/yyyy",
		autoclose : "true",
		clearBtn : "true",
		title : "dob",
	});

	$('[data-toggle="tooltip"]').tooltip();

});

function checkRequiredFiledsCondition() {
	var emptyField = 'N';
	$('#fullnameMandate').hide();
	$("#fullName").css("border-color", "");
	$('#primaryContactMandate').hide();
	$("#primaryContact").css("border-color", "");
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

	if (emptyField == 'N') {
		//Submit form
	}
}

function resetFields() {
	$("#fullName").val('');
	$("input[value='Male']").removeAttr('checked');
	$("input[value='Female']").removeAttr('checked');
	$("#dob").val('');
	$("#primaryContact").val('');
	$("#secondaryContact").val('');
	$("#addr1").val('');
	$("#addr2").val('');
	$("#addr3").val('');
	$("#locality").val('');
	$("#city").val('');
	$("#state").val('');
	$("#pinCode").val('');
}