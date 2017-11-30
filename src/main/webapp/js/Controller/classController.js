app.controller('classCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){

	$scope.class = [];
	$scope.classQuery = {};

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		
		var data = [];
		if ($cookieStore.get('role') != '0') {
//			$window.location.href = '/ren-sing/#!/addClass';
			data.push({
	    		'student_id': $cookieStore.get('person_id'),
	    		'orderForMasterQuery': ' '
	    	});
		}else {
			data.push({
	    		'orderForMasterQuery': ' '
	    	});
		}
		
		apiService.getAPIwithObject("class/query",data)
        .then(function(result) {
        	$scope.classList = result.data;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
		
		apiService.getAPIwithObject("comProperties/classType",data)
        .then(function(result) {
        	$scope.typeList = result.data;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
    };
    
    $scope.tranToInsertClass = function () {
    	$cookieStore.put("class_title_name", "新增");
    	$cookieStore.remove("class_data");
        $window.location.href = '/ren-sing/#!/addClass';
	}

    $scope.update = function(row) {
    	$cookieStore.put("class_title_name", "編輯");
    	$cookieStore.put("class_data", row);
    	$window.location.href = '/ren-sing/#!/addClass';
    };
    
    $scope.query = function() {
    	if ($cookieStore.get('role') != '0') {
    		$scope.classQuery.student_id = $cookieStore.get('person_id');
    	}
    	$scope.classQuery.orderForMasterQuery = ' ';
    	
    	apiService.getAPIwithObject("class/query",$scope.classQuery)
        .then(function(result) {
        	$scope.classList = result.data;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
    };
}]);