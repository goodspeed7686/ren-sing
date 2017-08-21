app.controller('logInCtrl', ['$scope' , 'apiService' , function ($scope , apiService) {
	
	$scope.LogIn = function(){
		
		apiService.getAPIwithObject("processLogin",$scope)
        .then(function(result) {
        	
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
		
	};
	
}]);