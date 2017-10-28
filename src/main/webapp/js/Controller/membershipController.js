app.controller('membershipCtrl',['$scope','apiService','alertService','$window',function ($scope,apiService,alertService,$window) {

	$scope.tranToInsertMem = function (){
    	$window.location.href = '/ren-sing/#!/addMem';
	};

}]);