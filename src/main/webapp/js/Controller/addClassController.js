app.controller('addClassCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		var data = sharedProperties.getProperty();
		if (data)
			$scope.title_name = data[0].title_name;
		if ($scope.title_name == '編輯'){
			$scope.title = 1;
			$scope.addClass = data[0].class;
		}else{
			$scope.title = 0;
			$scope.addClass = [];
		}
		
		apiService.getAPIwithObject("comProperties/classType",null)
        .then(function(result) {
        	$scope.typeList = result.data;
        	
        	if ($scope.title == 1) {
//        		$scope.addClass.class_type = $scope.addClass.type;
        	}
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
		
		apiService.getAPIwithObject("comProperties/classLevel",null)
        .then(function(result) {
        	$scope.levelList = result.data;
        	
        	if ($scope.title == 1) {
//        		$scope.addClass.level = $scope.addClass.level;
        	}
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
		
//		if ($cookieStore.get('role') != '0'){
//	        data = [];
//	    	data.push({
//	    		'student_id': $cookieStore.get('person_id'),
//	    		'restForMastQuery' : ' '
//	    	});
//	        apiService.getAPIwithObject("class/query",data)
//	        .then(function(result) {
//	        	$scope.addClass = result.data[0];
//	        },
//	        function(errResponse){
//	            console.error('Error while fetching Users');
//	        });
//		}else{
//			$scope.addClass.count = 0;
//		}
    };

	$scope.insertClass = function(){		
		apiService.getAPIwithObject("class/add",$scope.addClass)
        .then(function(result) {
        	
        },
        function(errResponse){
        	
        })
		
	};
	
	$scope.ibackToClass = function (){
    	$window.location.href = '/ren-sing/#!/class';
	}
}]);