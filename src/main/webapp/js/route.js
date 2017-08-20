app.config(function ($routeProvider,$locationProvider) {

	$routeProvider
	
	.when('/',{

		templateUrl: 'curriculum',
		controller: 'calendarCtrl'

	})

	.when('/curriculum',{

		templateUrl: 'curriculum',
		controller: 'calendarCtrl'

	})
	
	.when('/membership',{

		templateUrl: 'membership',
		controller: 'membershipCtrl'

	})
	
	.when('/pets',{

		templateUrl: 'pets',
		controller: 'petsCtrl'

	});

//	.otherwise({redirectTo:'/curriculum'});	
	
	$locationProvider.html5Mode(true);
});