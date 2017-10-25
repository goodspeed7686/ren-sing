app.controller('classCtrl',['$scope', 'apiService', '$window',function ($scope,apiService,$window) {

	$scope.class = [];
	
	$scope.tranToInsertClass = function (){
        	$window.location.href = '/ren-sing/#!/addClass';
	}

}]);