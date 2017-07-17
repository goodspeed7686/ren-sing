app.controller('calendarCtrl',
   function($scope, $compile, $timeout, uiCalendarConfig,apiService) {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();
	
	var clientId = "213964118310-3me1v0lo536l9pu72fosn5gun1h5kkth.apps.googleusercontent.com";
    var scopes = 'https://www.googleapis.com/auth/calendar';
 
    function handleAuthResult(authResult) {
        console.log(authResult);
        var authorizeButton = document.getElementById('authorize-button');
        if (authResult && !authResult.error) {
           // authorizeButton.style.visibility = 'hidden';
            makeApiCall();
        } else {
            authorizeButton.style.visibility = '';
            authorizeButton.onclick = handleAuthClick;
        }
    }
 
//    $scope.handleAuthClick=function (event) {
    $scope.handleAuthClick=function () {
        gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
        return false;
    }
 
    function makeApiCall() {
        gapi.client.load('calendar', 'v3', function() {
            var request = gapi.client.calendar.calendarList.list();
            request.execute(function(resp){
                $.each( resp.items, function( key, value ) {
                    console.log(resp.items[key].id);
                });
            });
            var request1 = gapi.client.calendar.events.list({
                'calendarId': 'primary',
                'timeMin': '2015-12-23T04:26:52.000Z'//Suppose that you want get data after 23 Dec 2014
             });
            request1.execute(function(resp){
                $.each( resp.items, function( key, value ) {
                    var title = resp.items[key].summary;
                    var startTime;
                    if (undefined == resp.items[key].start.dateTime)
                    	startTime = resp.items[key].start.date;//"2016-11-27
                    else
                    	startTime = resp.items[key].start.dateTime;//"2016-11-27T16:00:00+08:00"
                    
                    var sDate = googleDateFormat(startTime);
                    
                    var endTime;//"2016-11-27T17:00:00+08:00""
                    if (undefined == resp.items[key].end.dateTime)
                    	endTime = resp.items[key].end.date;//"2016-11-27
                    else
                    	endTime = resp.items[key].end.dateTime;//"2016-11-27T16:00:00+08:00"
                    var eDate = googleDateFormat(endTime);
                    	
                    $scope.eventSource.push({
                    	title: title,
                    	start: new Date(sDate.Y , sDate.M , sDate.D , sDate.h , sDate.m),
                    	end: new Date(eDate.Y , eDate.M , eDate.D , eDate.h , eDate.m),
                    	allDay: false
                    });
                });
            });
        });
    }
    
    function googleDateFormat(googleDate){
    	
    	var Y = googleDate.split("-")[0];
    	var M = googleDate.split("-")[1];
    	var D;
    	var h = "00";
    	var m = "00";
    	
    	//表示有時間
    	if (googleDate.split("-")[2].length > 3){
    		
    		D = googleDate.split("-")[2].split("T")[0];
    		
    		var time = googleDate.split("-")[2].split("T")[1].split("+")[0];
            h = time.split(":")[0];
            m = time.split(":")[1];
    		
    	}else{
    		
    		D = googleDate.split("-")[2];
    		
    	}
    	
    	return {
    		Y : Y,
    		M : M,
    		D : D,
    		h : h,
    		m : m
    	};
    	
    }
    
    $scope.eventSource = [];
    
    $scope.eventDB = function(){
    	var events = [];
    	apiService.getAPI("curriculum/query")
        .then(function(result) {
        	$scope.porsen = result;
        },
        function(errResponse){
            console.error('Error while fetching Users');
        }
    );
    }
    
    
//    $scope.handleAuthClick();

//    $scope.changeTo = 'Hungarian';
    /* event source that pulls from google.com */
//    $scope.eventSource = {
//            url: "http://www.google.com/calendar/feeds/usa__en%40holiday.calendar.google.com/public/basic",
//            className: 'gcal-event',           // an option!
//            currentTimezone: 'America/Chicago' // an option!
//    };
    /* event source that contains custom events on the scope */
    $scope.events = [
      {title: 'All Day Event',start: new Date(y, m, 1)},
      {title: 'Long Event',start: new Date(y, m, d - 5),end: new Date(y, m, d - 2)},
      {id: 999,title: 'Repeating Event',start: new Date(y, m, d - 3, 16, 0),allDay: false},
      {id: 999,title: 'Repeating Event',start: new Date(y, m, d + 4, 16, 0),allDay: false},
      {title: 'Birthday Party',start: new Date(y, m, d + 1, 19, 0),end: new Date(y, m, d + 1, 22, 30),allDay: false},
      {title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
    ];
    /* event source that calls a function on every view switch */
    $scope.eventsF = function (start, end, timezone, callback) {
      var s = new Date(start).getTime() / 1000;
      var e = new Date(end).getTime() / 1000;
      var m = new Date(start).getMonth();
      var events = [{title: 'Feed Me ' + m,start: s + (50000),end: s + (100000),allDay: false, className: ['customFeed']}];
      callback(events);
    };

    $scope.calEventsExt = {
       color: '#f00',
       textColor: 'yellow',
       events: [
          {type:'party',title: 'Lunch',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
          {type:'party',title: 'Lunch 2',start: new Date(y, m, d, 12, 0),end: new Date(y, m, d, 14, 0),allDay: false},
          {type:'party',title: 'Click for Google',start: new Date(y, m, 28),end: new Date(y, m, 29),url: 'http://google.com/'}
        ]
    };
    /* alert on eventClick */
    $scope.alertOnEventClick = function( date, jsEvent, view){
        $scope.alertMessage = (date.title + ' was clicked ');
    };
    /* alert on Drop */
     $scope.alertOnDrop = function(event, delta, revertFunc, jsEvent, ui, view){
       $scope.alertMessage = ('Event Dropped to make dayDelta ' + delta);
    };
    /* alert on Resize */
    $scope.alertOnResize = function(event, delta, revertFunc, jsEvent, ui, view ){
       $scope.alertMessage = ('Event Resized to make dayDelta ' + delta);
    };
    /* add and removes an event source of choice */
    $scope.addRemoveEventSource = function(sources,source) {
      var canAdd = 0;
      angular.forEach(sources,function(value, key){
        if(sources[key] === source){
          sources.splice(key,1);
          canAdd = 1;
        }
      });
      if(canAdd === 0){
        sources.push(source);
      }
    };
    /* add custom event*/
    $scope.addEvent = function() {
      $scope.events.push({
        title: 'Open Sesame',
        start: new Date(y, m, 28),
        end: new Date(y, m, 29),
        className: ['openSesame']
      });
    };
    /* remove event */
    $scope.remove = function(index) {
      $scope.events.splice(index,1);
    };
    /* Change View */
    $scope.changeView = function(view,calendar) {
      uiCalendarConfig.calendars[calendar].fullCalendar('changeView',view);
    };
    /* Change View */
    $scope.renderCalendar = function(calendar) {
      $timeout(function() {
        if(uiCalendarConfig.calendars[calendar]){
          uiCalendarConfig.calendars[calendar].fullCalendar('render');
        }
      });
    };
     /* Render Tooltip */
    $scope.eventRender = function( event, element, view ) {
        element.attr({'tooltip': event.title,
                      'tooltip-append-to-body': true});
        $compile(element)($scope);
    };
    /* config object */
    $scope.uiConfig = {
      calendar:{
        height: 450,
        editable: true,
        header:{
          left: 'title',
          center: '',
          right: 'today prev,next'
        },
        eventClick: $scope.alertOnEventClick,
        eventDrop: $scope.alertOnDrop,
        eventResize: $scope.alertOnResize,
        eventRender: $scope.eventRender
      }
    };

    $scope.changeLang = function() {
      if($scope.changeTo === 'Hungarian'){
        $scope.uiConfig.calendar.dayNames = ["Vasárnap", "Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek", "Szombat"];
        $scope.uiConfig.calendar.dayNamesShort = ["Vas", "Hét", "Kedd", "Sze", "Csüt", "Pén", "Szo"];
        $scope.changeTo= 'English';
      } else {
        $scope.uiConfig.calendar.dayNames = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
        $scope.uiConfig.calendar.dayNamesShort = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
        $scope.changeTo = 'Hungarian';
      }
    };
    /* event sources array*/
    $scope.eventSources = [$scope.eventSource,$scope.eventDB];
//    $scope.eventSources = [$scope.events, $scope.eventSource, $scope.eventsF];
//    $scope.eventSources2 = [$scope.calEventsExt, $scope.eventsF, $scope.events];
});