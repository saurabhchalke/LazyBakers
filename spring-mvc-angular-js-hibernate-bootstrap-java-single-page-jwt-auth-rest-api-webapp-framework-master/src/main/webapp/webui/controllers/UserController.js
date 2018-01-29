angular.module('App.Auth')
.controller('UserController', ['$scope','filterFilter', '$cookieStore', '$rootScope', '$location','$http','BackendCfg', 'AuthService', UserController]);


function UserController( $scope, $rootScope, AuthService,$cookieStore ,filterFilter, $location, $http, BackendCfg) {

	(function initController() {
		
		console.log("In user Control");
//		$scope.user = $scope.currentUser; 
		$scope.data = "Sidd";
		

		
		
	})();
	
	$scope.logout = function(){
    	console.log('received the logout event for user: ' + $scope.currentUser.email);
        
    	$rootScope.globals = {};
        $cookieStore.remove('globals');
        $http.defaults.headers.common.Authorization = '';
    	
    	
        $location.path('/');
    };
    
    
    $scope.saveChanges = function(){
    	console.log(currentUser.displayName);
    	console.log(currentUser.email);
    }
	
	
}