app.controller('calendarCtrl', ['$scope' , 'apiService' , '$uibModal' , 'alertService' , '$cookieStore',
		function ($scope , apiService , $uibModal , alertService, $cookieStore) {
    'use strict';

    $scope.changeMode = function (mode) {
        $scope.mode = mode;
    };

    $scope.today = function () {
        $scope.currentDate = new Date();
    };

    $scope.isToday = function () {
        var today = new Date(),
            currentCalendarDate = new Date($scope.currentDate);

        today.setHours(0, 0, 0, 0);
        currentCalendarDate.setHours(0, 0, 0, 0);
        return today.getTime() === currentCalendarDate.getTime();
    };

    $scope.loadEvents = function () {
    	apiService.setCoockie();
    	$scope.selectedDate = new Date();
    	
    	var date = new Date();
    	var y = date.getFullYear();
    	var M = date.getMonth();
    	var data = [];
    	if ($cookieStore.get('role') != 0) {
    		data.push({
        		'student_id': $cookieStore.get('person_id'),
        		'date_pre': y + '/' + M,
        		'date_now': y + '/' + (parseInt( M ) + 1),
        		'date_next': y + '/' + (parseInt( M ) + 2)
        	});
    	}else {
    		data.push({
        		'date_pre': y + '/' + M,
        		'date_now': y + '/' + (parseInt( M ) + 1),
        		'date_next': y + '/' + (parseInt( M ) + 2)
        	});
    	}
    	
        var events = [];
        apiService.getAPIwithObject("curriculum/query",data)
        .then(function(result) {
        	for(var i=0;i<result.data.length;i++){
        		events.push( dbDateFormat( result.data[i] ));
        	}
        	
        	$scope.eventSource = events;
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
        
        data = [];
    	data.push({
    		'student_id': $cookieStore.get('person_id'),
    		'type' : '0'
    	});
        apiService.getAPIwithObject("curriculum/queryRestClass",data)
        .then(function(result) {
        	$scope.showClassInfo = result.data[0];
        	$scope.student_name = $cookieStore.get('name');
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        });
    };

    $scope.onEventSelected = function (data) {
    	if (data.date){
    		
    		if (data.event)
    			$scope.currentEvent = data.event;
    		
    		$scope.selectedDate = data.date;
    		
    	}else if (data.event)
    		$scope.currentEvent = data.event;
    };
    
    $scope.cleanEvent = function () {
    	$scope.currentEvent = [];
    };
    
    $scope.insertCourse = function(currentEvent) {
    	
    	if (!currentEvent.date && !currentEvent.time){
    		var date = new Date(currentEvent.startTime);
        	var y = date.getFullYear();
        	var M = date.getMonth();
        	var d = date.getDate();
        	var h = date.getHours();
        	var m = date.getMinutes();
        	
        	currentEvent.date = y + "/" + M + "/" + d;
        	currentEvent.time = h + ":" + m;
    	}
    	
    	currentEvent.class_master_id = $scope.showClassInfo.class_master_id;
    	currentEvent.student_id = $scope.showClassInfo.student_id;
    	currentEvent.teacher_id = $scope.showClassInfo.teacher_id;
    	//個人課
    	currentEvent.range = 1;
    	
    	apiService.getAPIwithObject("curriculum/insert" , currentEvent)
        .then(function(result) {
        	
        },
        function(errResponse) {
            console.error('Error while fetching Users');
        })
    	
    }
    
    $scope.updateCourse = function(currentEvent) {
    	
    	if (currentEvent.startTime) {
    		var date = new Date(currentEvent.startTime);
        	var y = date.getFullYear();
        	var M = date.getMonth() + 1;
        	var d = date.getDate();
        	var h = date.getHours();
        	var m = date.getMinutes();
        	
        	currentEvent.date = y + "/" + M + "/" + d;
        	currentEvent.time = h + ":" + m;
    	}
    	
    	apiService.getAPIwithObject("curriculum/update" , currentEvent)
        .then(function(result) {
        	
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
    	
    }

	$scope.deleteCourse = function(currentEvent) {
		
		var data = [];
		
		data.push({
			class_detail_id: currentEvent.class_detail_id
		});
		
		apiService.getAPIwithObject("curriculum/delete" , currentEvent)
	    .then(function(result) {
	    	
	    },
	    function(errResponse){
	        console.error('Error while fetching Users');
	    })
		
	}

    $scope.onTimeSelected = function (selectedTime, events) {
//        console.log('Selected time: ' + selectedTime + ' hasEvents: ' + (events !== undefined && events.length !== 0));
    };
    
    function dbDateFormat(result) {
    	
    	var title = result.class_master_id;
    	var date = result.date;
    	var Y = date.split("/")[0];
    	var M = parseInt( date.split("/")[1] ) - 1;
    	var D = date.split("/")[2];
    	var intervalTime = result.interval_time;
    	var s_time = intervalTime.split("_")[0];
    	var sh = s_time.substring(0, 2);
    	var sm = s_time.substring(2);
    	var e_time = intervalTime.split("_")[1];
    	var eh = e_time.substring(0, 2);
    	var em = e_time.substring(2);
    	
    	return {
    		title: title,
    		class_master_id: result.class_master_id,
    		class_detail_id: result.class_detail_id,
    		startTime: new Date(Y , M , D , sh , sm),
    		endTime: new Date(Y , M , D , eh , em),
        	allDay: false,
//        	teacher: result.teacher_id,
//        	student: result.student_id,
        	teacher_id: result.teacher_id,
        	teacher: result.teacher_name,
        	student_id: result.student_id,
        	student: result.student_name,
        	song: result.song,
        	hw: result.hw,
        	teacher_note: result.teacher_note,
        	student_note: result.student_note,
        	type: result.type,
        	finish: result.finish
    	};
    	
    }
    
    $scope.teacherNoteOpen = function() {    
    	
        var modalInstance = $uibModal.open({
        	animation:true,
        	ariaLabelledBy: 'modal-title',
        	ariaDescribedBy: 'modal-body',
        	templateUrl: '/ren-sing/curriculum/popupNote',
        	size: 'sm', 
        	controller:'noteCtrl',
        	resolve: {
        		data: function () {
        			return {identity : 'teacher',
        					note : $scope.currentEvent.teacher_note};
        		}
        	}
        });
        modalInstance.result
        .then(function (result) {
        	$scope.currentEvent.teacher_note = result;
        	
        	var currentEvent = [];
        	currentEvent.push({
        		class_master_id: $scope.currentEvent.class_master_id,
        		class_detail_id: $scope.currentEvent.class_detail_id,
        		teacher_note: $scope.currentEvent.teacher_note
        	});
        	$scope.updateCourse(currentEvent);
		},
		function (result) {
			console.log('cancel');
			console.log(result);
		});

    }; 
    
    $scope.studentNoteOpen = function() {    
    	
        var modalInstance = $uibModal.open({
        	animation:true,
        	ariaLabelledBy: 'modal-title',
        	ariaDescribedBy: 'modal-body',
        	templateUrl: '/ren-sing/curriculum/popupNote',
        	size: 'sm', 
        	controller:'noteCtrl',
        	resolve: {
        		data: function () {
        			return {identity : 'student',
        					note : $scope.currentEvent.student_note};
        		}
        	}
        });
        modalInstance.result
        .then(function (result) {
        	$scope.currentEvent.student_note = result;
        	
        	var currentEvent = [];
        	currentEvent.push({
        		class_master_id: $scope.currentEvent.class_master_id,
        		class_detail_id: $scope.currentEvent.class_detail_id,
        		student_note: $scope.currentEvent.student_note
        	});
        	$scope.updateCourse(currentEvent);
		},
		function (result) {
			console.log('cancel');
			console.log(result);
		});

    };
    
    $scope.insertOpen = function() {    
    	
        var modalInstance = $uibModal.open({
        	animation:true,
        	ariaLabelledBy: 'modal-title',
        	ariaDescribedBy: 'modal-body',
        	templateUrl: '/ren-sing/curriculum/popupInsert',
        	size: 'md', 
        	controller:'insertCtrl',
        	resolve: {
        		data: function () {
        			return {date : $scope.selectedDate,
        				    teacher_id : $scope.showClassInfo.teacher_id,
        				    student_id : $scope.showClassInfo.student_id
        			};
        		}
        	}
        });
        modalInstance.result
        .then(function (result) {
        	$scope.insertCourse(result);
		},
		function (result) {
			console.log('cancel');
			console.log(result);
		});

    };
    
    $scope.history = function(){
    	
    	var modalInstance = $uibModal.open({
        	animation:true,
        	ariaLabelledBy: 'modal-title',
        	ariaDescribedBy: 'modal-body',
        	templateUrl: '/ren-sing/curriculum/history',
        	size: 'lg', 
        	controller:'historyCtrl',
        	resolve: {
        		data: function () {
//        			return {id : $scope.session.student_id};
        			return {id: '123564545'};
        		}
        	}
        });
        modalInstance.result
        .then(function (result) {
        	var currentEvent = [];
        	$scope.insertCourse(currentEvent);
		},
		function (result) {
			console.log('cancel');
			console.log(result);
		});
    	
    };
}]);

app.controller('noteCtrl', function($scope,$uibModalInstance,data){
	
	$scope.identity = data.identity;
	$scope.note = data.note;
	
	 $scope.enter = function() {
		 $uibModalInstance.close($scope.note);
	 };
	 $scope.cancel = function() {
		 $uibModalInstance.dismiss(456);
	 };
});

app.controller('insertCtrl', function($scope,$uibModalInstance,apiService,data){
	
//	$scope.teache = data.teacher;
	
	var date = new Date(data.date);
	var y = date.getFullYear();
	var M = date.getMonth() + 1;
	var d = date.getDate();
	var h = date.getHours();
	var m = date.getMinutes();
	
	$scope.selectYear = y;
	$scope.selectMounth = M;
	$scope.selectDay = d;
	
//	$scope.searchRestClass = function(){
//		apiService.getAPIwithObject("curriculum/queryBreak" , data)
//	    .then(function(result) {
//	    	$scope.timeList = result.data;
//	    	if (data.getTime)
//	    		$scope.selectTime = data.getTime;
//	    	else
//	    		$scope.selectTime = $scope.timeList[0].start_time;
//	    },
//	    function(errResponse){
//	        console.error('Error while fetching Users');
//	    })
//	};
	
	$scope.searchTime = function(){
		$scope.date = $scope.selectYear + "/" + $scope.selectMounth + "/" + $scope.selectDay;
		
		var subdata = [];
		subdata.push({
			date: $scope.date,
			teacher_id : data.teacher_id
		});
		apiService.getAPIwithObject("curriculum/queryBreak" , subdata)
	    .then(function(result) {
	    	$scope.timeList = result.data;
	    	if (data.getTime)
	    		$scope.selectTime = data.getTime;
	    	else
	    		$scope.selectTime = $scope.timeList[0].start_time;
	    },
	    function(errResponse){
	        console.error('Error while fetching Users');
	    })
	};
	
	$scope.searchName = function(){
		var subdata = [];
		subdata.push({
			person_id : data.teacher_id
		});
		apiService.getAPIwithObject("membership/queryPerson" , subdata)
	    .then(function(result) {
	    	$scope.teacher = result.data.name;
	    },
	    function(errResponse){
	        console.error('Error while fetching Users');
	    })
	};
	
	$scope.searchTime();
	$scope.searchName();
	
	function getYearList (){
		var yearList = [];
		//年
		for (var i=0;i<2;i++)
			yearList.push({val: parseInt(y)+i});
		
		$scope.yearList = yearList;
	};
	
	function getMounthList (){
		var mounthList = [];
		//月
		for (var i=1;i<=12;i++)
			mounthList.push({val:i});
		
		$scope.mounthList = mounthList;
	};
	
	function getDayList (year,mounth){
		
		var daysOfMounth = [31,28,31,30,31,30,31,31,30,31,30,31];
		
		var days;
		if (mounth == 2 && year%4 == 0 && year%100 != 0 && year%400 == 0){
			days = 29;
		}else
			days = daysOfMounth[mounth -1];
		
		var dayList = [];
		//日
		for (var i=1;i<=days;i++)
			dayList.push({val:i});
		
		$scope.dayList = dayList;
	};
	
	getYearList();
	getMounthList();
	getDayList(y,M);
	
	$scope.changeDate = function(year,mounth){
		getDayList (year,mounth);
//		$scope.searchTime();
	};
	
	$scope.enter = function() {
		$uibModalInstance.close({
			date : $scope.selectYear + "/" + $scope.selectMounth + "/" + $scope.selectDay,
			time : $scope.selectTime
		});
	};
	
	$scope.cancel = function() {
		$uibModalInstance.dismiss(456);
	};
});

app.controller('historyCtrl', function($scope,$uibModalInstance,apiService,data){
	
	$scope.identity = data.identity;
	$scope.note = data.note;
	
	$scope.historyQuery = function(){
		
		apiService.getAPIwithObject("curriculum/query" , data)
	    .then(function(result) {
	    	$scope.history = result.data;
	    },
	    function(errResponse){
	        console.error('Error while fetching Users');
	    })
		
	};
	
	$scope.historyQuery();
	
	$scope.historyClick = function(result){
		$scope.studentNote = result.student_note;
		$scope.teacherNote = result.teacher_note;
	};
	
	$scope.enter = function() {
		$uibModalInstance.close($scope.note);
	};
	$scope.cancel = function() {
		$uibModalInstance.dismiss(456);
	};
});