app.controller('CockpitController', function($scope, $location, AcarsService, ngDialog, $mdSidenav, $mdDialog, $log, $timeout, $rootScope, Idle) {

	$scope.flight = {};

	$scope.selectedMessage = {};
	
	/*
	 * Declare a property for UI-GRID
	 */
	$scope.acarsMessageGrid = {
		enableFiltering: true,
		enableRowSelection: true, //set this to allow row selection
		enableInlineRowEditing: true,
		enableRowHeaderSelection: false, //set this so ui-grid will display selection column
		multiSelect: false,
		enableSorting: true,
		enableColumnResizing: true, //enable column resizing, refer to http://ui-grid.info/docs/#/tutorial/204_column_resizing		
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

	/*
	 * List of functions that can be called from the front end HTML script.
	 */
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

	/*
	 * List of functions that are to be used internally within this script
	 */
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
	/*
	 * function that will be executed on the page load.
	 */
	getFlightData();

});
