app.config(function ($routeProvider) {

	$routeProvider

	.when('/',{

		templateUrl: 'pages/pets.html',
		controller: 'petsController'

	})

});