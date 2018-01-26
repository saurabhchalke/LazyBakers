angular.module('App')
    .controller('MenuController', ['$scope', '$rootScope', '$location', 'MenuService' , MenuController]);


function MenuController($scope, $rootScope, $location, MenuService) {
	
	
	
    (function initController() {
        
    	
    	//window.alert("This is executed before loading the view");
		console.log("This is executed before loading the view");
        //AuthService.clearCredentials();
		/*
		MenuService.getToppings(function(data){
			
			$scope.data = data;
			
		});*/
		
		$scope.data = "Siddy";
//		console.log(toppings);
		
    })();
	
	
	
	
	
}