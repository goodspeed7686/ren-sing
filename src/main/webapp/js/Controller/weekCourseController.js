app.controller('weekCourseCtrl', ['$scope' , 'apiService' , '$uibModal' , 'alertService' , '$cookieStore', '$window',
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
		apiService.getAPIwithObject("curriculum/queryWeekEvents",data)
        .then(function(result) {
        	$scope.header = result.data.header;
        	$scope.weeksEvents = result.data.content;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
	}
	
	$scope.checkCourse = function (classTimeId, time, day, determinPage) {
		var today = new Date();
		var date = getScopeValue(day);
		var chooseDayStr = today.getFullYear() + "/" + (today.getMonth() + 1) + "/" + date;
		var chooseDateStr = chooseDayStr + " " + time;
		var chooseDay = new Date(chooseDateStr);
		var diff = chooseDay.getTime() - today.getTime();
		
		if (diff < 0) {
			alertService.waram("這堂課的選課時間已經超過了，請選擇未來時段的課程");
			return;
		}
		
		if (determinPage == 0) {
			$scope.insertCourse(time, chooseDayStr, getDay(day));
		}else if (determinPage == "1") {
			alertService.waram("課程已被劃位，請選擇其他時段");
		}else if (determinPage == "2") {
			if (diff/1000/60/60/24 < 2) {
				swal({
		    		title: "Tips",
		    		text: "您欲於課程前⼀⽇取消課程，將依規定記1點是否確定取消課程？",
		    		type: "warning",
		    		showCancelButton: true,
		    		confirmButtonColor: "#DD6B55",
		    		confirmButtonText: "確定",
		    		confirmButtonClass: 'btn btn-success',
		    		cancelButtonClass: 'btn btn-danger',
		    		buttonsStyling: false,
		    		reverseButtons: true
		    	}).then((result) => {
		    		if (result.value) {
		    			$scope.cancleCourse(time, chooseDayStr, getDay(day));
		    		}
		    	});
			}else {
				$scope.cancleCourse(time, chooseDayStr, getDay(day));
			}
		}
	}
	
	$scope.insertCourse = function(time, date, day) {    	
    	swal({
    		title: date + "   " + day,
    		text: time + "   " + "聲音個別課",
    		type: "info",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "預約確認送出",
    		confirmButtonClass: 'btn btn-success',
    		cancelButtonClass: 'btn btn-danger',
    		buttonsStyling: false,
    		reverseButtons: true
    	}).then((result) => {
		    if (result.value) {
		    	data = [];
		    	data.push({
		    		time : time,
		    		date : date
		    	})
		    	apiService.getAPIwithObject("curriculum/insertCourse",data)
		        .then(function(result) {
		        	alertService.success("新增成功");
		        	$scope.queryFunction();
		        },
		        function(errResponse) {
		        	swal(
				        'Error',
				        'errResponse',
				        'error'
				    )
		        });
			    
		  // result.dismiss can be 'cancel', 'overlay',
		  // 'close', and 'timer'
		    }
    	});
    };
    
    $scope.cancleCourse = function(time, date, day) {
    	swal({
    		title: date + "   " + day,
    		text: time + "   " + "聲音個別課",
    		type: "warning",
    		showCancelButton: true,
    		confirmButtonColor: "#DD6B55",
    		confirmButtonText: "課程確認取消",
    		confirmButtonClass: 'btn btn-success',
    		cancelButtonClass: 'btn btn-danger',
    		buttonsStyling: false,
    		reverseButtons: true
    	}).then((result) => {
		    if (result.value) {
		    	data = [];
		    	data.push({
		    		time : time,
		    		date : date
		    	})
		    	apiService.getAPIwithObject("curriculum/deleteCourse",data)
		        .then(function(result) {
				    alertService.success("取消成功");
				    $scope.queryFunction();
		        },
		        function(errResponse) {
		        	swal(
				        'Error',
				        'errResponse',
				        'error'
				    )
		        });
		    }
    	});
    };
    
    function getScopeValue(day) {
    	if (day == 0)
    		return $scope.header.week0;
    	else if (day == 1)
    		return $scope.header.week1;
    	else if (day == 2)
    		return $scope.header.week2;
    	else if (day == 3)
    		return $scope.header.week3;
    	else if (day == 4)
    		return $scope.header.week4;
    	else if (day == 5)
    		return $scope.header.week5;
    	else if (day == 6)
    		return $scope.header.week6;
    };
    
    function getDay(day) {
    	if (day == 0)
    		return "Sun";
    	else if (day == 1)
    		return "Mon";
    	else if (day == 2)
    		return "Tue";
    	else if (day == 3)
    		return "Wed";
    	else if (day == 4)
    		return "Thus";
    	else if (day == 5)
    		return "Fri";
    	else if (day == 6)
    		return "Sat";
    };
	
}]);