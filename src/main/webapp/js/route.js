app.config(function ($routeProvider) {

	$routeProvider

	.when('/curriculum',{

		templateUrl: 'curriculum',
		controller: 'calendarCtrl'

	})
	
	.when('/pets',{

		templateUrl: 'pets',
		controller: 'petsCtrl'

	})

	.otherwise({redirectTo:'/curriculum'});	
});