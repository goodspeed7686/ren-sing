//app.service('apiService',['$resource',function ($resource){
app.factory('apiService', ['$http', '$q', function($http, $q){
	
	var REST_SERVICE_URI = 'http://localhost:8080/ren-sing/';
	
	var factory = {
		getAPI : getAPI
    };

    return factory;

    function getAPI(uri) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI + uri)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    };
    
    function getAPIwithObject(uri,ob) {
    	getAPI(uri + "/" + ob)
    };

}])