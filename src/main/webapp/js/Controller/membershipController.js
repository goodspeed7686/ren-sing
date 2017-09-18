app.controller('membershipCtrl',['$scope','apiService','alertService',function ($scope,apiService,alertService) {

	$scope.mem = {};
	
	$scope.insertMem = function (){
		
		apiService.getAPIwithObject("membership/insert",$scope.mem)
        .then(function(result) {
        	alertService.open("�s�W���\");
        },
        function(errResponse){
        	var str = errResponse.data.lastIndexOf(".html");
        	var sub = errResponse.data.substring(0,str+3);
        	var msg = /[^/]*$/.exec(sub)[0];
            
            alertService.open(msg.split(".")[0]);
        })
		
	};

}]);