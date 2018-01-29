angular.module('App')
.controller('AdminController', ['$scope','filterFilter', '$rootScope', '$location','$http','BackendCfg', AdminController]);


function AdminController($scope, $rootScope,filterFilter, $location, $http, BackendCfg) {



(function initController() {
    
	console.log("IN ADMIN CONT");
	//window.alert("This is executed before loading the view");
	
	
	
	console.log("This is executed before loading the view");
    
	
	

	var topping;
    $http.get(BackendCfg.url + '/api/topping').then(function(response) {
    	
    	topping = response.data;
    	$.map( topping, function( json_object ) {
            json_object["selected"] = false;
    	});
    	$scope.toppings = topping;
    	$scope.toppings2 = topping;
    });
	
	
	
	

      


  
  
  function getIdsOfSelectedToppings(data) {
      var ids = [];
   
      for (var key in data) {
          if (data.hasOwnProperty(key)) {
        	  if(data[key].selected == true){
        		  ids.push(data[key].toppingId);
        	  }
          }
      }
      return ids;
  };
	
	
  $scope.updateTopping = function(){
	  	
	  	var selectedToppings = getIdsOfSelectedToppings($scope.toppings);
	  	console.log(selectedToppings);
	  	
	  	$http.post(BackendCfg.url + '/api/topping/update', selectedToppings).then(function(response){
	    	//console.log(response);
	  		
	  		window.alert("The toppingshave been updated!!");
	    });
	  	
	  	
  }
  
 
  
  
  var base;
  $http.get(BackendCfg.url + '/api/base').then(function(response) {
    
  	base = response.data;
  	$.map( base, function( json_object ) {
        if(json_object["baseName"] == "Regular")
            json_object["selected"] = true;
        else  
          json_object["selected"] = false;
    });
  	$scope.base = base;
  	console.log(base);
  });
  
  
  
  $scope.setChoiceForBase = function (baseID) {
       
          angular.forEach($scope.base, function(base){
                  if(base.baseId == baseID)
                      base.selected = true;
                  else
                      base.selected = false;
             
           });
  };
  
  

  $scope.pizzaName = "LB Special";
  $scope.pizzaValue = 199;
  $scope.addPizza = function(){
	  var baseId = 1;
	  angular.forEach($scope.base, function(_base){
	      if(_base.selected == true){
	          baseId = _base.baseId;
	      }
	   });
	  
	  var data = {
			  pizzaName:$scope.pizzaName, 
			  pizzaDesc:"A fabulous Pizza for all", 
			  customized: 0, 
			  price: $scope.pizzaValue, 
			  toppings: getIdsOfSelectedToppings($scope.toppings),
			  baseId: baseId, 
			  size: "Medium"
	  }
	  
	  	
	  $http.post(BackendCfg.url + '/api/pizza', data).then(function(response){
	    	console.log(response);
	    	
	  		window.alert("Pizza Added Successfully");
	    });
	  	
  }
  
})();





}