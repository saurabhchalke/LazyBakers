angular.module('App')
    .controller('HomeController',  ['$anchorScroll', '$scope', '$location', '$rootScope', 'AuthService', 'FlashMessage', HomeController]);

function HomeController($anchorScroll, $scope, $location, $rootScope, AuthService, FlashMessage) {
    
    $scope.gotoAnchor = function(x) {
    	
    	
    	console.log("In go to anchor " + x);
          $location.hash(x);

          $anchorScroll();
    }
    
    
    
    $scope.logout = function(){
    	console.log('received the logout event for user: '+$scope.currentUser.email);
        AuthService.clearCredentials();
        $location.path('/');
    };
};