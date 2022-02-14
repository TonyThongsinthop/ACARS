//Initiate angular application with 1..N modules added as array parameters
var app = angular.module('tonytApp',["ngMaterial","ngRoute", "ngDialog", "ngMessages","ui.layout","ui.grid","ui.grid.edit","ui.grid.selection","ui.grid.cellNav","ui.grid.validate", "ui.grid.resizeColumns", "ui.grid.treeView", "ui.grid.expandable", "chart.js", "ngIdle"])

app.config(function($routeProvider, $locationProvider, $qProvider, $mdAriaProvider,IdleProvider, KeepaliveProvider) {
	/*
	 * create time out configuration using ngIdle
	 * reference: https://hackedbychinese.github.io/ng-idle/
	 */
		
	 IdleProvider.idle(10*60); //allow inactive time for 10 minutes
	 IdleProvider.timeout(5); //once inactive time exceeds, wait for another 5 seconds before time out
	 KeepaliveProvider.interval(1*60); // 1 minute keep alive ping
	/*
	 * disable warning "Attribute " aria-label ", required for accessibility, is missing on node"
	 * reference: https://stackoverflow.com/questions/30666742/how-do-i-disable-ngaria-in-ngmaterial 
	 */
	$mdAriaProvider.disableWarnings();
	
	/*
	 * fixing bugs "Angular All slashes in URL changed to %2F"
	 * reference: https://stackoverflow.com/questions/38913371/angularjs-route-provider-changes-into-2f
	 */
	$qProvider.errorOnUnhandledRejections(false); //https://stackoverflow.com/questions/41063947/angular-1-6-0-possibly-unhandled-rejection-error	
	$locationProvider.hashPrefix('');
	
	/*
	 * declare routing here as opposed to letting JAVA controller to handle the routing
     * for more information, http://viralpatel.net/blogs/angularjs-routing-and-views-tutorial-with-example/
	 */	
    $routeProvider
    .when('/cockpitMain', {
    	templateUrl: 'angularjs/views/cockpitMain.html',    	
    })
    .when('/transactions/:userId/:budgetOwnerIndex', {
    	templateUrl: 'webinf/views/TransactionView.html',    	
    })
    .when('/dashboard/:userId', {
    	templateUrl: 'webinf/views/DashboardView.html',    	
    })  
    .when('/budgetOwner/:userId', {
    	templateUrl: 'webinf/views/BudgetOwnerView.html',    	
    })
    .when('/budgetCategory/:userId', {
    	templateUrl: 'webinf/views/BudgetCategoryView.html',    	
    })
    .when('/accountSettings/:userId', {
    	templateUrl: 'webinf/views/AccountSettingsView.html',    	
    })
    .when('/inviteFriend/:userId', {
    	templateUrl: 'webinf/views/InviteFriendView.html',    	
    })   
});

app.filter('positiveValueFilter', function () { 
    return function (value) {
        return "$" + Math.abs(value).toFixed(2).replace(/\d(?=(\d{3})+\.)/g, '$&,');
}
    
});