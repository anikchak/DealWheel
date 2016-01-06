/**
 * This file contains all jquery/javascript functions related to Search Results Page 
 */

function selectedVehicle(selectedVehicleRow){
	var userInfo = $("#userPresent").val();
	 
	if(userInfo == "userAvailable"){
	if(selectedVehicleRow!=null){
		$("#selectedVehicleDetailsId").val(selectedVehicleRow);
		$("#bookingFormId").submit();
	}
	}else if(userInfo == "noUser"){
		//openLoginPopUp();
		$("#userNotLoggedInModalId").modal();
		//userNotLoggedInModalId
	}
}

