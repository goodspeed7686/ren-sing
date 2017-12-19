//app.service('apiService',['$resource',function ($resource){
app.factory('apiService', ['$http', '$q' , '$cookieStore', function($http, $q, $cookieStore){
	
	var REST_SERVICE_URI = 'http://localhost:8080/ren-sing/';
	
	var factory = {
		getAPI : getAPI ,
		getAPIwithObject : getAPIwithObject,
		setCoockie : setCoockie
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
    
    function getAPIwithObject(uri,data) {
//    	var deferred = $q.defer();
//        $http.post(REST_SERVICE_URI + uri , ob)
//            .then(
//            function (response) {
//                deferred.resolve(response.data);
//            },
//            function(errResponse){
//                console.error('Error while fetching Users');
//                deferred.reject(errResponse);
//            }
//        );
//        return deferred.promise;
    	
    	var config = {
    	        method: "POST",
    	        url: REST_SERVICE_URI + uri,
    	        data: data,
    	        headers: {
    	            'Content-Type': 'application/json; charset=utf-8'
    	        }
    	    };
	    return $http(config);
    };
    
    function setCoockie(){
    	getAPIwithObject("session",null)
    	.then(function(cookie) {
    		$cookieStore.put("account",cookie.data.account);
        	$cookieStore.put("role",cookie.data.role);
        	$cookieStore.put("person_id",cookie.data.person_id);
        	$cookieStore.put("name",cookie.data.name);
        },
        function(errResponse){
            console.error('Error while fetching Users');
        })
    }

    Date.prototype.yyyymmdd = function() {
	  var mm = this.getMonth() + 1; // getMonth() is zero-based
	  var dd = this.getDate();

	  return [this.getFullYear(),
	          (mm>9 ? '' : '0') + mm,
	          (dd>9 ? '' : '0') + dd
	         ].join('');
	};
}])