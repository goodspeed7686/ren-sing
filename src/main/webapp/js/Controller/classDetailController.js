app.controller('classDetailCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){

	$scope.classDetail = $cookieStore.get("class_data");
	$scope.classDetailQuery = {};
	$scope.paging = 1;

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		
		$scope.classDetailQuery.class_master_id = $scope.classDetail.class_master_id;
		$scope.setClassDetailQueryData();
		$scope.classDetailQueryFunction($scope.classDetailQuery);
		
		apiService.getAPIwithObject("comProperties/classType",$scope.classDetailQuery)
        .then(function(result) {
        	$scope.typeList = result.data;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
		
		$scope.queryCount($scope.classDetailQuery);
    };
    
    $scope.tranToInsertclassDetail = function () {
    	$cookieStore.put("class_title_name", "新增");
    	$cookieStore.remove("class_data");
        $window.location.href = '/ren-sing/#!/addClassDetail';
	}

    $scope.update = function(row) {
    	$cookieStore.put("class_title_name", "編輯");
    	$cookieStore.put("class_data", row);
    	$window.location.href = '/ren-sing/#!/addClassDetail';
    };

    $scope.query = function() {
    	$scope.setClassDetailQueryData();
    	$scope.classDetailQueryFunction($scope.classDetailQuery);
    	$scope.queryCount($scope.classDetailQuery);
    };
    
    $scope.changePage =  function(page) {
    	$scope.paging = page;
    	$scope.setClassDetailQueryData();
    	$scope.classDetailQuery.page = page;
    	$scope.classDetailQueryFunction($scope.classDetailQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.setClassDetailQueryData = function() {
    	$scope.classDetailQuery.order = 'date desc,interval_time desc';
    	$scope.classDetailQuery.page = 1;
    };
    
    $scope.classDetailQueryFunction = function(data) {
    	apiService.getAPIwithObject("class/queryDetail",data)
        .then(function(result) {
        	$scope.classDetailList = result.data;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
    };
    
    $scope.preClick = function() {
    	$scope.paging -= 1;
    	$scope.setClassDetailQueryData();
    	$scope.classDetailQuery.page = $scope.paging;
    	$scope.classDetailQueryFunction($scope.classDetailQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.nextClick = function() {
    	$scope.paging += 1;
    	$scope.setClassDetailQueryData();
    	$scope.classDetailQuery.page = $scope.paging;
    	$scope.classDetailQueryFunction($scope.classDetailQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.queryCount = function(data) {
    	apiService.getAPIwithObject("class/queryDetailCount",data)
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