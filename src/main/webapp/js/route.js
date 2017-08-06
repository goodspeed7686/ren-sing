app.config(function ($routeProvider) {

	$routeProvider

	.when('/',{

		templateUrl: 'pages/curriculum/calendar.html',
		controller: 'calendarCtrl'

	})
	
	.when('/pets',{

		templateUrl: 'pages/pets.html',
		controller: 'petsCtrl'

	})

});