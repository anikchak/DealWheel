/**
 * This file contains all jquery/javascript functions related to Landing Page 
 */
$(document).ready(function() {
	$.get("http://ipinfo.io", function (response) {
		ip = response.ip;
		city = response.city;
		region = response.region;
		matchLocation();
	}, "jsonp");
});
function matchLocation(){
		var locationIdText = $('#locationId').text();
		var cityMatchFound = 0;
		if(' Location ' == locationIdText){
			
		if(city=='Bengaluru'){
			city = "Bangalore";
		}
		if(propCities!=null){
			var activeCities = propCities.split("#",-1);
			for(i =0; i<activeCities.length;i++)
				{
					if(activeCities[i]==city){
						cityMatchFound = 1;
						$('#locationId').text(" "+activeCities[i]+ " ");
						$('#selectedLocationId').val(activeCities[i]);
						setLocationToSession(activeCities[i]);
						break;
					}else{
						cityMatchFound = 0;
					}
				}
		}
		if(cityMatchFound==0){
			$("#chooseLocationDivId").modal();
		}
	}
		
	}