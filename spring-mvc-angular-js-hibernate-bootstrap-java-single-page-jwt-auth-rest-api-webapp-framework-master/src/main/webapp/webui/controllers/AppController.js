angular.module('App')
    .controller('AppController', AppController);

AppController.$inject = ['$location', '$scope', '$rootScope', 'AuthService', 'FlashMessage'];
function AppController($location, $scope, $rootScope, AuthService, FlashMessage) {
    var app = this;
    console.log("app controller");

    

    $rootScope.$on("CallLogoutMethod", function(){
        $scope.logout();
     });
    
    
    app.logout = function () {
        console.log('received the logout event for user: '+$scope.currentUser.email);
        AuthService.clearCredentials();
        $location.path('/');
    };
  
    
};