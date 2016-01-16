$(document).ready(function(){
	$("#Delete").click(function(){
		$("#identifier").val("Delete");
	});
	
	$("#Disable").click(function(){
		$("#identifier").val("Disable");
	});
	
	$("#Enable").click(function() {
		 var value=$(this).val();
		 alert(value);
		 $("#identifier").val(value);
		 $("#Form").submit();   
		});
});