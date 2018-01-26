angular.module('App.Auth')
    .controller('LoginController', ['$scope', '$rootScope', '$location', 'AuthService', LoginController]);

function LoginController($scope, $rootScope, $location, AuthService) {
    var lc = this;

    (function initController() {
        // reset login status
        AuthService.clearCredentials();
    })();

    lc.login = function () {
        console.log('received the login event for user: ' + lc.user.email);
        lc.dataLoading = true;
        $rootScope.isSubmitted = true;
        AuthService.login(lc.user.email, lc.user.password, function(response) {
            if(response.code == 200) {
                AuthService.createJWTToken(response.result.user, response.result.token);
                AuthService.setCredentials();
                
                console.log("succesfullly logged in");
                $location.path('/app');
            } else {
            	
            	console.log("Invalid password");
                lc.error = response.result;
                lc.details = response.details;
                lc.dataLoading = false;
                $rootScope.isSubmitted = false;
            }
        });
    };
};