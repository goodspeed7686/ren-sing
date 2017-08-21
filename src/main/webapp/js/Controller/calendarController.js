app.controller('calendarCtrl', ['$scope' , 'apiService' , '$uibModal', function ($scope , apiService , $uibModal) {
    'use strict';
    
    $scope.changeMode = function (mode) {
        $scope.mode = mode;
    };

    $scope.today = function () {
        $scope.currentDate = new Date();
        $scope.selectedDate = new Date();
    };

    $scope.isToday = function () {
        var today = new Date(),
            currentCalendarDate = new Date($scope.currentDate);

        today.setHours(0, 0, 0, 0);
        currentCalendarDate.setHours(0, 0, 0, 0);
        return today.getTime() === currentCalendarDate.getTime();
    };

    $scope.loadEvents = function () {
        var events = [];
        apiService.getAPIwithObject("curriculum/query","")
        .then(function(result) {
        	for(var i=0;i<result.data.length;i++){
        		events.push( dbDateFormat( result.data[i] ));
        	}
        	
        	$scope.eventSource = events;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
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
    
    $scope.insertCourse = function(currentEvent){
    	
    	var date = new Date(currentEvent.startTime);
    	var y = date.getFullYear();
    	var M = date.getMonth();
    	var d = date.getDate();
    	var h = date.getHours();
    	var m = date.getMinutes();
    	
    	currentEvent.date = y + "/" + M + "/" + d;
    	currentEvent.time = h + ":" + m;
    	
    	apiService.getAPIwithObject("curriculum/insert" , currentEvent)
        .then(function(result) {
        	
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
    	
    }
    
    $scope.updateCourse = function(currentEvent){
    	
    	if (currentEvent.startTime){
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

	$scope.deleteCourse = function(currentEvent){
		
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
    
    function dbDateFormat(result){
    	
    	var title = result.class_master_id;
    	var date = result.date;
    	var Y = date.split("/")[0];
    	var M = parseInt( date.split("/")[1] ) - 1;
    	var D = date.split("/")[2];
    	var time = result.time;
    	var sh = time.split(":")[0];
    	var sm = time.split(":")[1];
    	var eh = 0;
    	var em = 0;
    	if (parseInt(sm) > 30){
    		eh = parseInt(sh) + 1;
    		em = parseInt(sm) - 30;
    	}else{
    		eh = sh;
    		em = parseInt(sm) + 30;
    	}
    	
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
        	templateUrl: '/ren-sing/pages/curriculum/calenderNote.html',
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
        	templateUrl: '/ren-sing/pages/curriculum/calenderNote.html',
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
        	templateUrl: '/ren-sing/pages/curriculum/curriculumInsert.html',
        	size: 'sm', 
        	controller:'insertCtrl',
        	resolve: {
        		data: function () {
        			return {date : $scope.selectedDate};
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
    
    $scope.history = function(){
    	
    	var modalInstance = $uibModal.open({
        	animation:true,
        	ariaLabelledBy: 'modal-title',
        	ariaDescribedBy: 'modal-body',
        	templateUrl: '/ren-sing/curriculum/history',
        	size: 'sm', 
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
	
	$scope.searchTime = function(){
		
		$scope.date = $scope.selectYear.val + "/" + $scope.selectMounth.val + "/" + $scope.selectDay.val;
		
		var data = [];
		data.push({
			date: $scope.date
		});
		apiService.getAPIwithObject("curriculum/queryBreak" , data)
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
	
	$scope.searchTime();
	
	function getYearList (){
		var yearList = [];
		//2�?
		for (var i=0;i<2;i++)
			yearList.push({val: parseInt(y)+i});
		
		$scope.yearList = yearList;
	};
	
	function getMounthList (){
		var mounthList = [];
		//12?��?
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
		//每個�??�天??
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
		$uibModalInstance.close($scope);
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