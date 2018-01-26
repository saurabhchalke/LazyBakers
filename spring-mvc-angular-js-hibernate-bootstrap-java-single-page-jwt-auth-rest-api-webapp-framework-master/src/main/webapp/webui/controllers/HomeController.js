angular.module('App')
    .controller('HomeController',  ['$anchorScroll', '$scope', '$location',	 HomeController]);

function HomeController($anchorScroll, $scope, $location) {
//    var home = this;
//    home.currentUser = null;
    
    $scope.gotoAnchor = function(x) {
    	
    	
    	console.log("In go to anchor " + x);
//        var newHash =  x;
//        if ($location.hash() !== newHash) {
          // set the $location.hash to `newHash` and
          // $anchorScroll will automatically scroll to it
          $location.hash(x);
//        } else {
          // call $anchorScroll() explicitly,
          // since $location.hash hasn't changed
          $anchorScroll();
//        }
    }
};