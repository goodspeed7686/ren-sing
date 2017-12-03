app.controller('addClassCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties', 'alertService',
	function ($scope,apiService,$window,$cookieStore,sharedProperties,alertService){

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		$scope.title_name = $cookieStore.get("class_title_name");
		if ($scope.title_name == '編輯'){
			$scope.title = 1;
			$scope.addClass = $cookieStore.get("class_data");
		}else{
			$scope.title = 0;
			$scope.addClass = {};
		}
		$scope.selected = {};
		
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
		
		apiService.getAPIwithObject("comProperties/classPlace",null)
        .then(function(result) {
        	$scope.placeList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
		
		var param = [];
		param.push({
			'role' : '1'
		});
		apiService.getAPIwithObject("person/queryForRole",param)
        .then(function(result) {
        	$scope.teacherList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
		
		param = [];
		param.push({
			'role' : '2'
		});
		apiService.getAPIwithObject("person/queryForRole",param)
        .then(function(result) {
        	$scope.studentList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };

	$scope.insertClass = function(){		
		apiService.getAPIwithObject("class/insert",$scope.addClass)
        .then(function(result) {
        	alertService.success("新增成功");
        	$scope.ibackToClass();
        },
        function(errResponse){
        	alertService.error(errResponse);
        })
		
	};
	
	$scope.updateClass = function(){		
		apiService.getAPIwithObject("class/update",$scope.addClass)
        .then(function(result) {
        	alertService.success("更新成功");
        	$scope.ibackToClass();
        },
        function(errResponse){
        	alertService.error(errResponse);
        })
		
	};
	
	$scope.ibackToClass = function (){
    	$window.location.href = '/ren-sing/#!/class';
	}
}]);