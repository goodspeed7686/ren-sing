app.controller('membershipCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){
	$scope.mem = [];
	$scope.memQuery = [];
	
	$scope.tranToInsertmem = function (){
		data = [];
    	data.push({
    		'title_name' : '新增'
    	})
    	sharedProperties.setProperty(data);
        $window.location.href = '/ren-sing/#!/addmem';
	}
	
	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		
		var data = [];
		if ($cookieStore.get('role') != '0'){
//			$window.location.href = '/ren-sing/#!/addmem';
			data.push({
	    		'student_id': $cookieStore.get('person_id')
	    	});
		}
		
		data.push({
//    		'orderForMasterQuery': ' '
    	});
		apiService.getAPIwithObject("membership/query",data)
        .then(function(result) {
        	$scope.memList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };

    $scope.update = function(row){
    	data = [];
    	data.push({
    		'title_name' : '編輯',
    		'mem' : row
    	})
    	sharedProperties.setProperty(data);
    	$window.location.href = '/ren-sing/#!/addmem';
    };
    
    $scope.query = function(){
    	apiService.getAPIwithObject("membership/query",$scope.memQuery)
        .then(function(result) {
        	$scope.memList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };

}]);