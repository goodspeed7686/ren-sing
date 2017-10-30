app.controller('addClassCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){
//	$scope.addClass = [];
	
	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		var data = sharedProperties.getProperty();
		$scope.title_name = data[0].title_name;
		if ($scope.title_name == '編輯'){
			$scope.title = 1;
		}else{
			$scope.title = 0;
		}
		$scope.addClass = data[0].class;
		
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
//        	var str = errResponse.data.lastIndexOf(".html");
//        	var sub = errResponse.data.substring(0,str+3);
//        	var msg = /[^/]*$/.exec(sub)[0];
//            
//            alertService.open(msg.split(".")[0]);
        })
		
	};
	
	$scope.ibackToClass = function (){
    	$window.location.href = '/ren-sing/#!/class';
	}
}]);