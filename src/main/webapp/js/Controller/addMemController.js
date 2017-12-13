app.controller('addMbershipCtrl',['$scope', 'apiService', 'alertService', '$window', '$cookieStore',
	function ($scope, apiService, alertService, $window, $cookieStore) {

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		$scope.title_name = $cookieStore.get("mem_title_name");
		
		var data = [];
		if ($scope.title_name == '編輯') {
			$scope.mem = $cookieStore.get("mem_data");
			data.push({
				'person_id' : $scope.mem.person_id
			});
			apiService.getAPIwithObject("membership/queryMemAccount",data)
	        .then(function(result) {
	        	$scope.mem.account = result.data[0].account;
//	        	$scope.mem.password = "*********";
	        	$scope.mem.role = result.data[0].role;
	        },
	        function(errResponse){
	        	
	        })
		}else {
			$scope.mem = {};
			apiService.getAPIwithObject("membership/getNewAcc",null)
	        .then(function(result) {
	        	$scope.mem.account = result.data.account;
	        	$scope.men.person_id = result.data.account;
	        },
	        function(errResponse){
	        	
	        })
		}
		
	};

	$scope.insertMem = function (){
		
		apiService.getAPIwithObject("membership/insert",$scope.mem)
        .then(function(result) {
        	alertService.success("新增成功");
        	$scope.ibackToMem();
        },
        function(errResponse){
        	var str = errResponse.data.lastIndexOf(".html");
        	var sub = errResponse.data.substring(0,str+3);
        	var msg = /[^/]*$/.exec(sub)[0];
            
            alertService.error(msg.split(".")[0]);
        })
		
	};
	
	$scope.updateMem = function(){		
		apiService.getAPIwithObject("membership/update",$scope.mem)
        .then(function(result) {
        	alertService.success("更新成功");
        	$scope.ibackToMem();
        },
        function(errResponse){
        	alertService.error(errResponse);
        })
		
	};
	
	$scope.ibackToMem = function (){
    	$window.location.href = '/ren-sing/#!/membership';
	}

}]);