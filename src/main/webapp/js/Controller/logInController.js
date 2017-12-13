app.controller('logInCtrl', ['$scope' , 'apiService' , '$window' , 'alertService'
                              , function ($scope , apiService , $window , alertService) {
	
	$scope.logIn = function(){
		
		if (!$scope.user_id){
			$('#user_id').focus();
			return;
		}
			
		if (!$scope.pwd){
			$('#pwd').focus();
			return;
		}
		
		var data = [];
		data.push({
			account : $scope.user_id,
			password : $scope.pwd
		});
		
		apiService.getAPIwithObject("processLogin",data)
        .then(function(result) {
        	$window.location.href = '/ren-sing/';
        },
        function(errResponse){
        	var str = errResponse.data.lastIndexOf(".html");
        	var sub = errResponse.data.substring(0,str+3);
        	var msg = /[^/]*$/.exec(sub)[0];
        	alertService.error(msg.split(".")[0]);
//            alertService.open(msg.split(".")[0]);
        })
		
	};
	
}]);