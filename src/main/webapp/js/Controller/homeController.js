app.controller('homeCtrl', ['$scope' , 'apiService' , '$window' , 'alertService'
                              , function ($scope , apiService , $window , alertService) {
	
	$scope.logOut = function(){
		apiService.getAPIwithObject("logout","")
        .then(function(result) {
        	$window.location.href = '/ren-sing/';
        },
        function(errResponse){
        	
        })
	};
	
}]);