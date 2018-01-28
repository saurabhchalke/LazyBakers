var app = angular.module('plunker', []);

app.controller('MainCtrl', function($scope) {
  $scope.user = {first:'John', last:'Smith'};
})
.directive('childScope', function() {
  return { scope: true, restrict:'AE' }
});