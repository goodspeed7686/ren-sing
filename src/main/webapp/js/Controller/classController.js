app.controller('classCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){

	$scope.class = [];
	$scope.classQuery = [];
	
	$scope.tranToInsertClass = function (){
		data = [];
    	data.push({
    		'title_name' : '新增'
    	})
    	sharedProperties.setProperty(data);
        $window.location.href = '/ren-sing/#!/addClass';
	}
	
	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		
		var data = [];
		if ($cookieStore.get('role') != '0'){
//			$window.location.href = '/ren-sing/#!/addClass';
			data.push({
	    		'student_id': $cookieStore.get('person_id')
	    	});
		}
		
		data.push({
    		'orderForMasterQuery': ' '
    	});
		apiService.getAPIwithObject("class/query",data)
        .then(function(result) {
        	$scope.classList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
		
		apiService.getAPIwithObject("comProperties/classType",data)
        .then(function(result) {
        	$scope.typeList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };

    $scope.update = function(row){
    	data = [];
    	data.push({
    		'title_name' : '編輯',
    		'class' : row
    	})
    	sharedProperties.setProperty(data);
    	$window.location.href = '/ren-sing/#!/addClass';
    };
    
    $scope.query = function(){
    	apiService.getAPIwithObject("class/query",$scope.classQuery)
        .then(function(result) {
        	$scope.classList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };
}]);