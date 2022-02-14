//instantiate controller and pass instance of HbbService

app.controller('CockpitController', function($scope, $location, AcarsService, ngDialog, $mdSidenav, $mdDialog, $log, $timeout, $rootScope, Idle) {

	$scope.flight = {};

	$scope.selectedMessage = {};
	/* declare properties for ui grid */

	/* 
	   to enable in-line editing
	   1) tag data attribute entry in columnDefs with "enableCellEdit: true"
	   2) include parameter enableInlineRowEditing : true in the grid definition
	   3) In the html <div> tag, make sure to include ui-grid-edit ui-grid-row-edit attributes
	   https://stackoverflow.com/questions/41446677/angularjs-ui-grid-enablecelledit-not-editing
	   https://embed.plnkr.co/6XplBZUjfJXpwNuqVw2j/
	*/

	$scope.acarsMessageGrid = {
		enableFiltering: true,
		enableRowSelection: true, //set this to allow row selection
		enableInlineRowEditing: true,
		enableRowHeaderSelection: false, //set this so ui-grid will display selection column
		multiSelect: false,
		enableSorting: true,
		enableColumnResizing: true, //enable column resizing, refer to http://ui-grid.info/docs/#/tutorial/204_column_resizing
		//enableCellEditOnFocus: true, //set this so you can do in line-editing			
		columnDefs: [
			{ name: 'UUID', enableCellEdit: false, field: 'uuid', width: "*", type: 'string' },
			{ name: 'MessageDirection', enableCellEdit: true, field: 'messageDirection', type: 'string', width: "150"}, 
			{ name: 'Display', enableCellEdit: false, field: 'messageDirection', width: "150",				 
			cellTemplate: 
			      '<div ng-if="row.entity.messageDirection == \'DOWNLINK\'"><img ng-src=\'resources/images/Img_Traffic_Downlink.png\' style=\'width:30%\' /></div>' +
			      '<div ng-if="row.entity.messageDirection == \'UPLINK\'"><img ng-src=\'resources/images/Img_Traffic_Uplink.png\' style=\'width:30%\' /></div>'
			},
			{ name: 'MessageType', enableCellEdit: true, field: 'messageType', type: 'string', width: "*"},
			{ name: 'MessageDate', enableCellEdit: true, field: 'messageDate', width: "*", type: 'date', cellFilter: 'date:\'dd-MMM-yyyy\'' },
			{ name: 'MessageTime', enableCellEdit: true, field: 'messageTime', width: "*", type: 'string' },
			{ name: 'RecipientCode', enableCellEdit: true, field: 'recipientCode', type: 'string', width: "*" },
			{ name: 'MessageStatus', enableCellEdit: false, field: 'messageStatus', type: 'string', width: "*" },
			{ name: 'MessageDescription', enableCellEdit: true, field: 'messageDescription', type: 'string', width: "250" },
			{ name: 'Action', displayName: 'Action', width: "250", enableCellEdit: false,
			    cellTemplate:
				  '<button type="button" class="add-button" ng-click="grid.appScope.addNewMessage(row)">ADD</button>' +
	        	  '<button type="button" class="delete-button" ng-click="grid.appScope.removeMessage(row)">REMOVE</button>' + 
	        	  '<button type="button" class="delete-button" ng-click="grid.appScope.sendAcarsMessage(row)">SEND ACARS</button>'
			}
		]
	};

	/* functions which can be called from UI */

	$scope.acarsMessageGrid.onRegisterApi = function(gridApi) {
		$scope.gridApi = gridApi;
		//trigger event when user select any data row on the parent grid

		gridApi.selection.on.rowSelectionChanged($scope, function(row) {
			// console.log("Selected data row= >" + row.entity.messageType);
			setInlineEditing(row);
			$scope.selectedMessage = row.entity;
		});
	};
	
	$scope.addNewMessage = function(row){
		var index = $scope.acarsMessageGrid.data.indexOf(row.entity);
		var newMessage = AcarsService.getNewMessage($scope.flight);
		$scope.acarsMessageGrid.data.splice(index + 1, 0 , newMessage);
	};
	
	$scope.removeMessage = function(row) {
		var index = $scope.acarsMessageGrid.data.indexOf(row.entity);
		$scope.acarsMessageGrid.data.splice(index, 1);
	};
	
	$scope.sendAcarsMessage = function(row) {
		row.entity.messageDeliveredTimeStamp = new Date();
		row.entity.messageDeliveredTimeStampUTCISO8601 = row.entity.messageDeliveredTimeStamp.toISOString();
		row.entity.messageStatus = "Sent";
		
		console.log("Current UTC time now =>" + row.entity.messageDeliveredTimeStamp.toISOString())
		AcarsService.sendAcarsMessage(row.entity);
	};	

	/* functions to be used internally within this script */


	function getFlightData() {
		AcarsService.getFlightData().then(function(response) {

			$scope.flight = response;
			$scope.acarsMessageGrid.data = response.acarsMessages;

		}, function(errResponse) {
		});

	};

	function setInlineEditing(row) {
		if (row.isSelected && row.inlineEdit) {
			row.inlineEdit.isEditModeOn = true;
			row.inlineEdit.enterEditMode();
		}
		if (!row.isSelected && row.inlineEdit) {
			console.log("In setInlineEditing = false");
			row.inlineEdit.isEditModeOn = false;
			row.inlineEdit.cancelEdit();
		}
	}
	/* execute default method on load */
	getFlightData();

});
