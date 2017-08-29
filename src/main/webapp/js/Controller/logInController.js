app.controller('logInCtrl', ['$scope' , 'apiService' , '$window' , function ($scope , apiService , $window) {
	
	$scope.logIn = function(){
		
		var data = [];
		data.push({
			user_id : $scope.user_id,
			pwd : $scope.pwd
		});
		
		apiService.getAPIwithObject("processLogin",data)
        .then(function(result) {
        	$window.location.href = '/ren-sing/';
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
		
	};
	
}]);