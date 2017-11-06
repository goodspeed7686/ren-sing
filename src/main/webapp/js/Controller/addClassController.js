app.controller('addClassCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		$scope.title_name = $cookieStore.get("class_title_name");
		if ($scope.title_name == '編輯'){
			$scope.title = 1;
			$scope.addClass = $cookieStore.get("class_data");
		}else{
			$scope.title = 0;
			$scope.addClass = [];
		}
		
		apiService.getAPIwithObject("comProperties/classType",null)
        .then(function(result) {
        	$scope.typeList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
		
		apiService.getAPIwithObject("comProperties/classLevel",null)
        .then(function(result) {
        	$scope.levelList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
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