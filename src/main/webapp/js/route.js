app.config(function ($routeProvider) {

	$routeProvider

	.when('/',{

//		templateUrl: 'pages/calendar.html',
//		controller: 'calendarCtrl'
		templateUrl: 'pages/index.html',
		controller: 'CalendarDemoCtrl'

	})
	
	.when('/pets',{

		templateUrl: 'pages/pets.html',
		controller: 'petsCtrl'

	})

});