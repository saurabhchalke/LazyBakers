angular.module('App')
.controller('AdminController', ['$scope','filterFilter', '$rootScope', '$location','$http','BackendCfg', AdminController]);


function AdminController($scope, $rootScope,filterFilter, $location, $http, BackendCfg) {



(function initController() {
    
	console.log("IN ADMIN CONT");
	//window.alert("This is executed before loading the view");
	
	
	
	console.log("This is executed before loading the view");
    //AuthService.clearCredentials();
	/*
	MenuService.getToppings(function(data){
		
		$scope.data = data;
		
	});*/
	
	

    
    $http.get(BackendCfg.url + '/api/topping').then(function(response) {
        //document.write(JSON.stringify(response));
    	
    	$scope.toppings = response.data;
    	console.log(response.data);
    });
	
	var topping = $scope.toppings;
	
	$.map( topping, function( json_object ) {
        json_object["selected"] = false;
  });

      


  $scope.toppings = topping;

  function getIdsOfSelectedToppings() {
      var ids = [];
      var data = $scope.toppings;
      for (var key in $scope.toppings) {
          if (data.hasOwnProperty(key)) {
        	  if(data[key].selected == true){
        		  ids.push(data[key].toppingId);
        	  }
          }
      }
      return ids;
  };
	
	
  $scope.updateTopping = function(){
	  	
	  	var selectedToppings = getIdsOfSelectedToppings();
	  	console.log(selectedToppings);
	  	
	  	$http.post(BackendCfg.url + '/api/topping').then(function(response) {
	    
	    	
	    	$scope.toppings = response.data;
	    	console.log(response.data);
	    });
	  	
	  	
  }
  
  

  
  $http.get(BackendCfg.url + '/api/base').then(function(response) {
      
  	
  	$scope.base = response.data;
  	
  	console.log(response.data);
  });
  
  var base = $scope.base;

  $.map( base, function( json_object ) {
      if(json_object["baseName"] == "Regular")
          json_object["selected"] = true;
      else  
        json_object["selected"] = false;
  });
  
  $scope.base = base;

  $scope.setChoiceForBase = function (baseID) {
       
          angular.forEach($scope.base, function(base){
                  if(base.baseId == baseID)
                      base.selected = true;
                  else
                      base.selected = false;
             
           });
  };


	
})();





}