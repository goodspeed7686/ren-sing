app.controller('classCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){

	$scope.class = [];
	
	$scope.tranToInsertClass = function (){
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
    };

    $scope.update = function(row){
    	data = [];
    	data.push({
    		'title_name' : '½s¿è',
    		'class' : row
    	})
    	sharedProperties.setProperty(data);
    	$window.location.href = '/ren-sing/#!/addClass';
    };
}]);