app.controller('membershipCtrl',['$scope', 'apiService', '$window', '$cookieStore', 'sharedProperties',
	function ($scope,apiService,$window,$cookieStore,sharedProperties){
	$scope.mem = [];
	$scope.memQuery = {};
	
	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		
		$scope.setMemQueryData();
		$scope.memQueryFunction($scope.memQuery);
    	$scope.queryCount($scope.memQuery);
    	
    	apiService.getAPIwithObject("person/queryForRole",null)
        .then(function(result) {
        	$scope.teacherList = result.data;
        	var select2Json = [];
        	for (var i in result.data) {
        		select2Json.push({
        			id : result.data[i].person_id,
        			text : result.data[i].name
        		})
        	}
        	$('#test').select2({
            	data: select2Json,
            	placeholder:'請選擇',
            	allowClear:true,
            	tags: true, 
                tokenSeparators: [',', ' ']
            });  
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };
    
    $scope.tranToInsertMem = function () {
    	$cookieStore.put("mem_title_name", "新增");
    	$cookieStore.remove("mem_data");
        $window.location.href = '/ren-sing/#!/addMem';
	}

    $scope.update = function(row){
    	$cookieStore.put("mem_title_name", "編輯");
    	$cookieStore.put("mem_data", row);
    	$window.location.href = '/ren-sing/#!/addMem';
    };
    
    $scope.query = function(){
    	$scope.setMemQueryData();
    	$scope.memQueryFunction($scope.memQuery);
    	$scope.queryCount($scope.memQuery);
    };
    
    $scope.changePage =  function(page) {
    	$scope.paging = page;
    	$scope.setMemQueryData();
    	$scope.memQuery.page = page;
    	$scope.memQueryFunction($scope.memQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.setMemQueryData = function() {
    	if ($cookieStore.get('role') != '0') {
    		$scope.memQuery.student_id = $cookieStore.get('person_id');
    	}
    	$scope.memQuery.orderForMasterQuery = ' ';
    	$scope.memQuery.page = 1;
    };
    
    $scope.memQueryFunction = function(data) {
    	apiService.getAPIwithObject("membership/query",data)
        .then(function(result) {
        	$scope.memList = result.data;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        });
    };
    
    $scope.preClick = function() {
    	$scope.paging -= 1;
    	$scope.setMemQueryData();
    	$scope.memQuery.page = $scope.paging;
    	$scope.memQueryFunction($scope.memQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.nextClick = function() {
    	$scope.paging += 1;
    	$scope.setMemQueryData();
    	$scope.memQuery.page = $scope.paging;
    	$scope.memQueryFunction($scope.memQuery);
    	$scope.showPreAndNextPage($scope.paging);
    };
    
    $scope.queryCount = function(data) {
    	apiService.getAPIwithObject("membership/queryCount",data)
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