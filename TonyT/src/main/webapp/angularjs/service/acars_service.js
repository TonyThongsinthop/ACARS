'use strict';
angular.module('tonytApp').factory('AcarsService', ['$http', '$q', '$location', '$timeout', function($http, $q, $location, $timeout) {

	//'http://localhost:8080/TonyT/'
	var REST_SERVICE_URI = $location.protocol() + "://" + $location.host() + ":" + $location.port() + getContextPath();

	var factory = {};

	var COLORS = Chart.defaults.global.colors; // use array of default color provided by angular chart

	function randomColor() {
		return COLORS[Math.floor(Math.random() * COLORS.length)];
	}

	//function to return context path e.g. /HBB
	function getContextPath() {
		return window.location.pathname.substring(0, window.location.pathname
			.indexOf("/", 2));
	}

	var factory = {};

	factory.getFlightData = function() {
		var deferred = $q.defer();
		console.log("perform GET to: " + REST_SERVICE_URI + "/flight/data");
		$http({
			url: REST_SERVICE_URI + "/flight/data",
			method: 'GET',
			headers: {
				"Content-Type": "application/json"
			}
		})
			.then(
				function(response) {
					deferred.resolve(response.data);
				},
				function(errResponse) {
					deferred.reject(errResponse);
				}
			);
		return deferred.promise;
	}
	
	factory.getNewMessage = function(flight){
    	var newMessage = 
    	{
    			 "uuid": crypto.randomUUID(),
    	       	 "airlineCode": flight.airlineCode,
    	       	 "flightNumber": flight.flightNumber,
    	       	 "departureAirport": flight.departureAirport,
    	       	 "arrivalAirport": flight.arrivalAirport,
    	       	 "originDate":  flight.originDate,
    	       	 "messageDirection": 'DOWNLINK',
    	       	 "messageType": null,
    	       	 "messageDate": new Date(),
    	       	 "messageTime" : "00:00",    	       	   	      
    	       	 "recipientCode": null,
    	       	 "messageStatus": null,
    	       	 "messageDescription": null,
    	       	 "messageDeliveredTimeStamp": null,
    	       	 "MessageDeliveredTimeStampUTCISO8601": null
    	};
    	return 	newMessage;
    }
    
    factory.sendAcarsMessage = function (acarsMessage) {
        var deferred = $q.defer(); 
        console.log ("perform POST to: " + REST_SERVICE_URI + "/flight/acars/send");
        $http({
        	url: REST_SERVICE_URI + "/flight/acars/send",        	
        	method: 'POST',
        	data: acarsMessage,
        	headers: {
        		"Content-Type": "application/json"
        	}})     
            .then(
            function (response) {            
                deferred.resolve(response.data);
                console.log("Success => " + response.data);              
            },
            function(errResponse){            	
                deferred.reject(errResponse);
                console.log("FAILURE => " + errResponse);                
            }
        );
        return deferred.promise;
    }


	// https://stackoverflow.com/questions/29780147/how-to-return-image-from-http-get-in-angularjs
	function _arrayBufferToBase64(buffer) {
		var binary = '';
		var bytes = new Uint8Array(buffer);
		var len = bytes.byteLength;
		for (var i = 0; i < len; i++) {
			binary += String.fromCharCode(bytes[i]);
		}
		return window.btoa(binary);
	}

	return factory;
}]);