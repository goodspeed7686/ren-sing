app.controller('addMbershipCtrl',['$scope', 'apiService', 'alertService', '$window', '$cookieStore',
	function ($scope, apiService, alertService, $window, $cookieStore) {

	$scope.loadEvents = function () {
		$scope.role = $cookieStore.get('role');
		$scope.title_name = $cookieStore.get("mem_title_name");
		$scope.pwdIsChanged = false;
		
		var data = [];
		if ($scope.title_name == '編輯') {
			$scope.mem = $cookieStore.get("mem_data");
			data.push({
				'person_id' : $scope.mem.person_id
			});
			apiService.getAPIwithObject("membership/queryMemAccount",data)
	        .then(function(result) {
	        	$scope.mem.account = result.data[0].account;
	        	$scope.mem.role = result.data[0].role;
	        },
	        function(errResponse){
	        	
	        });
		}else {
			$scope.mem = {};
			apiService.getAPIwithObject("membership/getNewAcc",null)
	        .then(function(result) {
	        	$scope.mem.account = result.data.account;
	        	$scope.mem.person_id = result.data.account;
	        },
	        function(errResponse){
	        	
	        });
		}
	};

	$scope.insertMem = function (){
		if (!validate()) {
			return;
		}
		if (isEmpty($scope.mem.password)) {
			alertService.info("請填寫密碼");
			return;
		}
		if (!pwdCheck()) {
			return;
		}
		apiService.getAPIwithObject("membership/insert",$scope.mem)
        .then(function(result) {
        	alertService.success("新增成功");
        	$scope.ibackToMem();
        },
        function(errResponse){
            alertService.error("系統錯誤");
        });
	};
	
	$scope.updateMem = function(){
		if (!validate() || !pwdCheck()) {
			return;
		}
		apiService.getAPIwithObject("membership/update",$scope.mem)
        .then(function(result) {
        	alertService.success("更新成功");
        	$scope.ibackToMem();
        },
        function(errResponse){
        	alertService.error(errResponse);
        });
	};
	
	$scope.ibackToMem = function (){
    	$window.location.href = '/ren-sing/#!/membership';
	};
	
	$scope.pwdChange = function (){
    	$scope.pwdIsChanged = true;
	};

	function validate() {
		if (isEmpty($scope.mem.name)) {
			alertService.info("請填寫名字");
			return false;
		}else if (isEmpty($scope.mem.id_number)) {
			alertService.info("請填寫身份證字號");
			return false;
		}else if (!idNumberCheck($scope.mem.id_number)) {
			alertService.info("身份證字號錯誤，請重新輸入");
			return false;
		}else if (isEmpty($scope.mem.role)) {
			alertService.info("請選擇角色");
			return false;
		}else if (isEmpty($scope.mem.sex)) {
			alertService.info("請選擇性別");
			return false;
		}else if (isEmpty($scope.mem.birthday)) {
			alertService.info("請填寫生日");
			return false;
		}else if (isEmpty($scope.mem.phone)) {
			alertService.info("請填寫手機號碼");
			return false;
		}else if (!phoneCheck($scope.mem.phone)) {
			alertService.info("手機號碼錯誤，請重新輸入");
			return false;
		}else if (isEmpty($scope.mem.email)) {
			alertService.info("請填寫Email");
			return false;
		}else if (!emailCheck($scope.mem.email)) {
			alertService.info("Email格式錯誤，請重新輸入");
			return false;
		}else {
			return true;
		}
	};
	
	function pwdCheck() {
		if ($scope.pwdIsChanged) {
			var pwd = $scope.mem.password;
			if (pwd.length > 0) {
				if ($scope.mem.password != $scope.mem.pwdCheck) {
					alertService.info("密碼與確認密碼不相同");
					return false;
				}else {
					return true;
				}
			}else {
				return true;
			}
		}else {
			return true;
		}
	}
	
	function isEmpty(value) {
	  return typeof value == 'string' && !value.trim() || typeof value == 'undefined' || value === null;
	};
	
	function idNumberCheck(value) {
		var a = new Array('A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'X', 'Y', 'W', 'Z', 'I', 'O');
	    var b = new Array(1, 9, 8, 7, 6, 5, 4, 3, 2, 1);
	    var c = new Array(2);
	    var d;
	    var e;
	    var f;
	    var g = 0;
	    var h = /^[a-z](1|2)\d{8}$/i;
	    if (value.search(h) == -1) {
	        return false;
	    }
	    else {
	        d = value.charAt(0).toUpperCase();
	        f = value.charAt(9);
	    }
	    for (var i = 0; i < 26; i++) {
	        if (d == a[i]) {
	            e = i + 10; //10
	            c[0] = Math.floor(e / 10); //1
	            c[1] = e - (c[0] * 10); //10-(1*10)
	            break;
	        }
	    }
	    for (var i = 0; i < b.length; i++) {
	        if (i < 2) {
	            g += c[i] * b[i];
	        }
	        else {
	            g += parseInt(value.charAt(i - 1)) * b[i];
	        }
	    }
	    if ((g % 10) == f) {
	        return true;
	    }
	    if ((10 - (g % 10)) != f) {
	        return false;
	    }
	    return true;
	};
	
	function phoneCheck(value) {
		if (value.match(/^\d+$/))
			return true;
		else
			return false;
	};
	
	function emailCheck(value) {
		if (value.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/))
			return true;
		else
			return false;
	};
}]);