angular.module('App')
	.service('MenuService',['$http', '$cookieStore', '$rootScope', 'BackendCfg',
		
		function($http, $cookieStore, $rootScope, $timeout, BackendCfg){
			
			var service = this;
			
			
			service.getToppings = function(callback){
				console.log("In get All toppings");
				
				var res = $http.get(BackendCfg.url + '/api/****/getAllToppings');
                
				res.then(
					//success
					function (data, status, headers, config) {
	                    callback(data);
	                },
	                //failure
	                function(data, status, headers, config) {
						console.log("Error");
					}
                
				);
				
				res.error();
				
				
			};
			
		}
		
		]);