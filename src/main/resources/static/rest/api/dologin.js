angular.module('login',[]);

angular.module('login').controller('dologin',function($rootScope,$scope,$http){



	$scope.submit=function(){
	$http.get('http://localhost:8080/rest/api/login/' + $scope.username +"/" + $scope.password + "/" + $scope.cType).then(
		function(response){
			$scope.login = response.data;
			var companyId = $scope.login;
			switch ($scope.cType){


				case "COMPANY":
				window.location.href = 'http://localhost:8080/rest/api/company.html';
					// location.assign('http://localhost:8080/rest/api/company.html')
					break;

				case "CUSTOMER":
					location.assign("http://localhost:8080/FinalProject/customer.html")
					break;

				case "ADMIN":
					location.assign("http://localhost:8080/rest/api/admin.html")
					break;
				default:
					location.assign("http://localhost:8080/FinalProject/index.html")
		}

	},function(error){
		alert('operation failed' + error.data);
	});
	};

});
