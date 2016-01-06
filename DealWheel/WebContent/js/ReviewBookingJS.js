/**
 * This file contains JS which are corresponding to ReviewBooking.jsp
 */

function modifySearchCriteriaInReview(){
		$("#modifyBtnId").hide();
		$("#reviewModifyDivId").show();
		$("#paymentBtnId").hide();
	}
	
	var value = 100;
	var progress = setInterval(function(){
		$('.progress-bar')
        	.css('width', value+'%')
        	.attr('aria-valuenow', value); 
		value = value-1;
		if(value<0){
		clearInterval(progress);
		 $("#sessionTimeOutModalId").modal({
			 backdrop: 'static',
			   keyboard: false
			 });
		}
		else if(value<11 && value>=0){
		$("#progressBarId").addClass('progress-bar-danger');
		}
		else if(value>=11 && value<51){
		$("#progressBarId").addClass('progress-bar-warning');
		}
	},1200);
	
	function sessionTimedOut(){
		$("#logoutFormId").submit();
	}
	
	function proceedWithPayment(){
		$("#orderLocationId").val($("#locationId").text());
		$("#confirmationSummaryFormId").submit();
	}