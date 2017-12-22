app.controller('classCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){

	$scope.class = [];
	$scope.classQuery = {};
	$scope.paging = 1;

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		
		$scope.setClassQueryData();
		$scope.classQueryFunction($scope.classQuery);
		
		apiService.getAPIwithObject("comProperties/classType",$scope.classQuery)
        .then(function(result) {
        	$scope.typeList = result.data;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
		
		$scope.queryCount($scope.classQuery);
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
    	$scope.setClassQueryData();
    	$scope.classQueryFunction($scope.classQuery);
    	$scope.queryCount($scope.classQuery);
    };
    
    $scope.changePage =  function(page) {
    	$scope.paging = page;
    	$scope.setClassQueryData();
    	$scope.classQuery.page = page;
    	$scope.classQueryFunction($scope.classQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.setClassQueryData = function() {
    	if ($cookieStore.get('role') != '0') {
    		$scope.classQuery.student_id = $cookieStore.get('person_id');
    	}
    	$scope.classQuery.orderForMasterQuery = ' ';
    	$scope.classQuery.page = 1;
    };
    
    $scope.classQueryFunction = function(data) {
    	apiService.getAPIwithObject("class/query",data)
        .then(function(result) {
        	$scope.classList = result.data;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
    };
    
    $scope.preClick = function() {
    	$scope.paging -= 1;
    	$scope.setClassQueryData();
    	$scope.classQuery.page = $scope.paging;
    	$scope.classQueryFunction($scope.classQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.nextClick = function() {
    	$scope.paging += 1;
    	$scope.setClassQueryData();
    	$scope.classQuery.page = $scope.paging;
    	$scope.classQueryFunction($scope.classQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.queryCount = function(data) {
    	apiService.getAPIwithObject("class/queryCount",data)
        .then(function(result) {
        	$scope.pageSize = [];
        	var maxPages = result.data.count/10;
        	for (var i=0;i<maxPages;i++) {
        		$scope.pageSize.push({
        			'page' : i+1
        		});
        	}
        	$scope.showPreAndNextPage(1);
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
    };
    
    $scope.showPreAndNextPage = function(page) {
    	var maxPages = $scope.pageSize.length;
    	
    	if (maxPages == 1) {
    		$scope.theFirstPage = true;
    		$scope.theLastPage = true;
    	}else if (page == 1) {
    		$scope.theFirstPage = true;
    		$scope.theLastPage = false;
    	}else if (maxPages == page) {
    		$scope.theFirstPage = false;
    		$scope.theLastPage = true;
    	}
    }
}]);