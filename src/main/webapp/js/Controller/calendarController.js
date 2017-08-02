app.controller('calendarCtrl', ['$scope' , 'apiService' , '$uibModal', function ($scope , apiService , $uibModal) {
    'use strict';
   
//    $scope.currentEventShow = false;
    
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
        var events = [];
        apiService.getAPI("curriculum/query")
        .then(function(result) {
        	for(var i=0;i<result.length;i++){
        		events.push( dbDateFormat( result[i] ));
        	}
        	
        	$scope.eventSource = events;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
    };

    $scope.onEventSelected = function (event) {
    	$scope.currentEvent = event;
    };
    
    $scope.cleanEvent = function () {
    	$scope.currentEvent = [];
    };
    
    $scope.click = function () {
    	var data = [];
    	
    	data.push({
    		classDetailId : "1",
    		classMasterId : "2",
    		studentId : "2",
    		teacherId : "2",
    		song : "2",
    		date : "2",
    		time : "2",
    		hw : "ob",
    		teacherNote : "2",
    		studentNote : "2"
    	});
    	
    	var id = 1;
    	
    	apiService.getAPIwithObject("curriculum/insert" , data)
        .then(function(result) {
        	
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
    };
    
    $scope.updateCourse = function(currentEvent){
    	
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
    		startTime: new Date(Y , M , D , sh , sm),
    		endTime: new Date(Y , M , D , eh , em),
        	allDay: false,
        	teacher: result.teacher_id,
        	student: result.student_id,
//        	teacher_id: result.teacher_id,
//        	teacher: result.teacher_name,
//        	student_id: result.student_id,
//        	sudent: result.sudent_name,
        	song: result.song,
        	hw: result.hw,
        	teacher_note: result.teacher_note,
        	student_note: result.student_note
    	};
    	
    }
    
    $scope.studentNoteOpen = function() {    

        var modalInstance = $uibModal.open({
          animation:true,
          ariaLabelledBy: 'modal-title',
          ariaDescribedBy: 'modal-body',
          templateUrl: '/ren-sing/pages/calenderNote.html',
          size: 'sm', 
          controller:'modalCtrl',
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
        	
        	apiService.getAPIwithObject("curriculum/update" , $scope.currentEvent)
            .then(function(result) {
            	
            },
            function(errResponse){
                console.error('Error while fetching Users');
            })
		},
		function (result) {
			console.log('cancel');
			console.log(result);
		});

      }; 
}]);

app.controller('modalCtrl', function($scope,$uibModalInstance,data){
	
	$scope.identity = data.identity;
	$scope.note = data.note;
	
	 $scope.enter = function() {
		 $uibModalInstance.close($scope.note);
	 };
	 $scope.cancel = function() {
		 $uibModalInstance.dismiss(456);
	 };
});