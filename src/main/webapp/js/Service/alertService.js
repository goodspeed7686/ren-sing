app.service('alertService',['$uibModal',function ($uibModal){
	
	this.open = function(str){
		 var modalInstance = $uibModal.open({
        	animation:true,
        	ariaLabelledBy: 'modal-title',
        	ariaDescribedBy: 'modal-body',
        	templateUrl: '/ren-sing/page/alert.html',
        	size: 'md', 
        	controller:'alertCtrl',
        	resolve: {
        		data: function () {
        			return { msg : str };
        		}
        	}
        });
        modalInstance.result
        .then(function (result) {
        	
		});
	};
	
	this.success = function(str){
		swal(
		  'Success !',
		  str
		)
	};
	
	this.error = function(){
		swal(
		  'Error !',
		  'error'
		)
	};
	
	this.error = function(str){
		swal(
		  'Error !',
		  str
		)
	};
	
}]);

app.controller('alertCtrl', function($scope,$uibModalInstance,data){
	
	$scope.msg = data.msg;
	
	 $scope.cancel = function() {
		 $uibModalInstance.dismiss();
	 };
});