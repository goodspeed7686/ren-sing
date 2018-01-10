app.controller('dailyCourseCtrl', ['$scope' , 'apiService' , '$uibModal' , 'alertService' , '$cookieStore', '$window',
		function ($scope , apiService , $uibModal , alertService, $cookieStore, $window) {
	
	$scope.loadEvents = function () {
		$scope.page = 0;
		$scope.queryFunction();
	};
	
	$scope.pageClick = function(page) {
		$scope.page += page;
		$scope.queryFunction();
	}
	
	$scope.queryFunction = function() {
		var data = [];
		data.push({
			page : $scope.page
		})
		apiService.getAPIwithObject("curriculum/queryDailyEvents",data)
        .then(function(result) {
        	$scope.header = result.data.header;
        	$scope.dayEvents = result.data.content;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
	}

	$scope.checkCourse = function (classTimeId, time, determinPage) {
		var today = new Date();
		var chooseDayStr = today.getFullYear() + "/" + (today.getMonth() + 1) + "/" + (today.getDate() + $scope.page);
		var chooseDateStr = chooseDayStr + " " + time;
		var chooseDay = new Date(chooseDateStr);
		var diff = chooseDay.getTime() - today.getTime();
		
		if (diff/1000/60/60/24 < 0) {
			alertService.waram("這堂課的選課時間已經超過了，請選擇未來時段的課程");
			return;
		}
		
		if (determinPage == 0) {
			swal({
	    		title: "簽到",
	    		text: "要開始上課摟!",
	    		type: "success",
	    		confirmButtonColor: "#DD6B55",
	    		confirmButtonText: "確定",
	    		confirmButtonClass: 'btn btn-success',
	    		buttonsStyling: false,
	    		reverseButtons: true
	    	}).then((result) => {
	    		if (result.value) {
	    			var data = [];
	    			data.push({
	    				time : classTimeId,
	    				date : chooseDayStr
	    			})
	    			apiService.getAPIwithObject("curriculum/signCourse",data)
	    	        .then(function(result) {
	    	        	$scope.queryFunction();
	    	        },
	    	        function(errResponse) {
	    	            console.error('Error while fetching Users');
	    	        });
	    		}
	    	});
		}
	}

	$scope.showDayCourse = function(ranges) {
		if (ranges) {
			$scope.ranges = ranges;
		}else if ($scope.ranges && $scope.ranges > 1) {
			$scope.ranges --;
			return false;
		}
		return true;
	}

}]);