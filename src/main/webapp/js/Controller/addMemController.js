app.controller('addMembershipCtrl',['$scope','apiService','alertService','$window',function ($scope,apiService,alertService,$window) {

	$scope.mem = {};

	$scope.insertMem = function (){
		
		apiService.getAPIwithObject("membership/insert",$scope.mem)
        .then(function(result) {
        	alertService.open("新增成功");
        },
        function(errResponse){
        	var str = errResponse.data.lastIndexOf(".html");
        	var sub = errResponse.data.substring(0,str+3);
        	var msg = /[^/]*$/.exec(sub)[0];
            
            alertService.open(msg.split(".")[0]);
        })
		
	};

}]);