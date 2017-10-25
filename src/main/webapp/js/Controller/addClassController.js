app.controller('addClassCtrl',['$scope', 'apiService', '$window',function ($scope,apiService,$window){

	$scope.addClass = [];

	$scope.insertClass = function(){
		
//		if (!$scope.user_id){
//			$('#user_id').focus();
//			return;
//		}
//			
//		if (!$scope.pwd){
//			$('#pwd').focus();
//			return;
//		}
		
		var data = [];
		data.push({
//			user_id : $scope.user_id,
//			password : $scope.pwd
		});
		
		apiService.getAPIwithObject("addClass",data)
        .then(function(result) {
        	
        },
        function(errResponse){
        	var str = errResponse.data.lastIndexOf(".html");
        	var sub = errResponse.data.substring(0,str+3);
        	var msg = /[^/]*$/.exec(sub)[0];
            
            alertService.open(msg.split(".")[0]);
        })
		
	};
	
	$scope.ibackToClass = function (){
    	$window.location.href = '/ren-sing/#!/class';
	}
}]);