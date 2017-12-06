app.controller('membershipCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){
	$scope.mem = [];
	$scope.memQuery = {};
	
	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		
		var data = [];
		if ($cookieStore.get('role') != '0'){
			data.push({
	    		'student_id': $cookieStore.get('person_id')
	    	});
		}else {
			data.push({
	    		'student_id': ''
	    	});
		}

		apiService.getAPIwithObject("membership/query",data)
        .then(function(result) {
        	$scope.memList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };
    
    $scope.tranToInsertMem = function () {
    	$cookieStore.put("mem_title_name", "新增");
    	$cookieStore.remove("mem_data");
        $window.location.href = '/ren-sing/#!/addMem';
	}

    $scope.update = function(row){
    	$cookieStore.put("mem_title_name", "編輯");
    	$cookieStore.put("mem_data", row);
    	$window.location.href = '/ren-sing/#!/addMem';
    };
    
    $scope.query = function(){
    	if ($cookieStore.get('role') != '0') {
    		$scope.memQuery.student_id = $cookieStore.get('person_id');
    	}
    	
    	apiService.getAPIwithObject("membership/query",$scope.memQuery)
        .then(function(result) {
        	$scope.memList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };

}]);