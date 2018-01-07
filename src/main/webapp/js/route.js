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
	
	.when('/curriculum/queryWeekCourses',{

		templateUrl: 'curriculum/queryWeekCourses',
		controller: 'weekCourseCtrl'

	})
	
	.when('/curriculum/queryDailyCourses',{

		templateUrl: 'curriculum/queryDailyCourses',
		controller: 'dailyCourseCtrl'

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
	
	
	.when('/properties',{

		templateUrl: 'properties',
		controller: 'propertiesCtrl'

	})
	.when('/java',{

		templateUrl: 'java',
		

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