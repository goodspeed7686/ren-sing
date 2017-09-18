app.controller('addAccCtrl',['$scope','apiService',function ($scope,apiService) {

	$scope.acc = [];
	
	$scope.insertAcc = function (){
		
		apiService.getAPIwithObject("insert",$scope.acc)
        .then(function(result) {
        	alertService.open("新增成功");
        },
        function(errResponse){
        	var str = errResponse.data.lastIndexOf(".html");
        	var sub = errResponse.data.substring(0,str+3);
        	var msg = /[^/]*$/.exec(sub)[0];
            
            alertService.open(msg.split(".")[0]);
        })
		
	}

}]);