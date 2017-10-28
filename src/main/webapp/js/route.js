app.config(function ($routeProvider,$locationProvider) {

	$routeProvider

	.when('/',{

		templateUrl: 'curriculum',
		controller: 'calendarCtrl'

	})
	
	.when('/logIn',{

		templateUrl: 'logIn',
		controller: 'logInCtrl'

	})

	.when('/curriculum',{

		templateUrl: 'curriculum',
		controller: 'calendarCtrl'

	})
	
	.when('/class',{

		templateUrl: 'class',
		controller: 'classCtrl'

	})
	
	.when('/addClass',{

		templateUrl: 'addClass',
		controller: 'addClassCtrl'

	})
	
	.when('/membership',{

		templateUrl: 'membership',
		controller: 'membershipCtrl'

	})
	
	.when('/addMem',{

		templateUrl: 'addMem',
		controller: 'addMbershipCtrl'

	})
	
	.when('/addAcc',{

		templateUrl: 'addAcc',
		controller: 'addAccCtrl'

	})
	
	.when('/pets',{

		templateUrl: 'pets',
		controller: 'petsCtrl'

	})

	.otherwise({redirectTo:'/curriculum'});	
	
//	$locationProvider.html5Mode(true);
});