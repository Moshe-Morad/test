var admApp = angular.module('doadmin', []);

admApp.controller('admController', function($scope, $http) {
	$scope.MainMenu = true;
	$scope.WelcomePage = true;

	$scope.AddCompany = false;
	$scope.ShowAllCustomers = false;
	$scope.ShowAllCompanies = false;
	$scope.CompanyDetails = false;
	$scope.CustomerDetails = false;
	$scope.ShowCompany = false;
	$scope.ShowCustomer = false;
	$scope.AddCustomer = false;
	$scope.DeleteComp = false;
	$scope.UpdateComp = false;
	$scope.DeleteCust = false;
	$scope.UpdateCust = false;
	$scope.ShowContactPage = false;
	$scope.ShowAboutPage = false;
	$scope.listCoupons = false;

	$scope.hideAll = function() {

		$scope.WelcomePage = false;
		$scope.AddCompany = false;
		$scope.ShowAllCustomers = false;
		$scope.ShowAllCompanies = false;
		$scope.CompanyDetails = false;
		$scope.CustomerDetails = false;
		$scope.ShowCompany = false;
		$scope.ShowCustomer = false;
		$scope.AddCustomer = false;
		$scope.DeleteComp = false;
		$scope.UpdateComp = false;
		$scope.DeleteCust = false;
		$scope.UpdateCust = false;
		$scope.ShowContactPage = false;
		$scope.ShowAboutPage = false;
		$scope.listCoupons = false;
	}

	$scope.ShowWelcomePage = function() {

		$scope.hideAll();
		$scope.MainMenu = true;
		$scope.WelcomePage = true;

		$scope.name = '';
		$scope.password = '';
		$scope.email = '';
		$scope.id = '';
	}

										// <!-- FIND ALL COMPANIES-->
	$scope.findAllCompanies = function() {
		$scope.hideAll();
		$scope.ShowAllCompanies = true;

		$http.get("http://localhost:8080/rest/api/admin/companies").then(
				function(response) {
					$scope.companies = response.data;
				}, function(error) {
					alert('operation failed' + error.data);
				});
	}
	             // <!-- FIND COMPANY BY ID-->
	$scope.company;
	$scope.findCompanyById = function() {
		$scope.hideAll();
		$scope.CompanyDetails = true;
		$scope.id = '';
		$scope.executeForCompany = function() {
			if ($scope.id == '') {
				window.alert("You must enter ID")
			} else {
					$http.get(
							'http://localhost:8080/rest/api/admin/company/'
									+ $scope.id).then(function(response) {
						$scope.company = response.data;
						if ($scope.company.id == 0) {
							alert('company not found');
							$scope.hideAll();
							$scope.CompanyDetails = true;
						} else {
							$scope.ShowCompany = true;
							$scope.listCoupons = false;
							$scope.showCoupons = function() {
								if ($scope.company.coupons.length==0) {
									alert('coupons not found');
								} else {
									$scope.listCoupons = true;
									$scope.coupons = response.data.coupons;
								}
							}
						}
					},
					function(error) {
						alert('operation failed' + error.data);
					});
			}
		}
	}

	           	// <!-- DELETE COMPANY-->
	$scope.company;
	$scope.deleteCompany = function() {
		$scope.hideAll();
		$scope.DeleteComp = true;
		$scope.id = '';
		$scope.executeForCompany = function() {
			if ($scope.id == '') {
				window.alert("You must enter ID")
			}else {
					alert("do you want delete company?");

					$http({
						method : 'DELETE',
						url : 'http://localhost:8080/rest/api/admin/deletecompany/'
						+ $scope.id
					}).then(function(response) {
						window.alert('deleted');
						$scope.hideAll();
						$scope.WelcomePage = true;
						$scope.id = '';

					}, function(error) {
						alert('operation failed' + error.data);
					});
					}
					}
					}


								// <!-- UPDATE COMPANY-->
	$scope.companyToUpDate = function() {
		$scope.hideAll();
		$scope.UpdateComp = true;
		$scope.executeForCompany = function() {
			if ($scope.id == '') {
				window.alert("You must enter ID")
			}else {
					alert("do you want update company?");
					var id = $scope.id;
					var password = $scope.password;
					var email = $scope.email;

					var companyDetails = {
					id: id,
					email: email,
					password: password
				};
				$http.put('http://localhost:8080/rest/api/admin/updateCompany', companyDetails)
					.then(function (response){
							window.alert('updated');
							$scope.hideAll();
							$scope.WelcomePage = true;


					}, function(error) {
						alert('operation failed' + error.data);
					});
					}
					}
					}

		       	// <!-- ADD COMPANY-->
	$scope.addNewCompany = function() {
		$scope.hideAll();
		$scope.AddCompany = true;

		$scope.executeForCompany = function() {
			alert("do you want add company?")
			if ($scope.email == null || $scope.password == null
					|| $scope.name == null) {
				window.alert("all fields must be fill")
			} else {
				var compName = $scope.name;
				var email = $scope.email;
				var password = $scope.password;
				var addNewCompany = {
				compName: compName,
				email: email,
				password: password
			};
				$http.post(
						'http://localhost:8080/rest/api/admin/newCompany',addNewCompany)
						.then(function(response) {

					alert("added");
					$scope.WelcomePage = true;
					$scope.AddCompany = false;
				}, function(error) {
					alert('operation failed', error.data);
				});
			}
		};
	}

		// /////////////////////////for find all customers

	$scope.findAllCustomers = function() {
		$scope.hideAll();
		$scope.ShowAllCustomers = true;

		$http.get("http://localhost:8080/rest/api/admin/customers").then(
				function(response) {
					$scope.customers = response.data;

				}, function(error) {
					alert('operation failed' + error.data);
				});
	}

	// /////////////////////////for FindCustomerById
	$scope.customer;
	$scope.findCustomerById = function() {
		$scope.hideAll();
		$scope.CustomerDetails = true;
		$scope.id = '';
		$scope.executeForCustomer = function() {
			if ($scope.id == '') {
				window.alert("You must enter ID")
			} else {
				$http.get(
						'http://localhost:8080/rest/api/admin/customer/'
								+ $scope.id).then(function(response) {
					$scope.customer = response.data;
					if ($scope.customer.id == 0) {
						alert('customer not found');
						$scope.hideAll();
						$scope.CustomerDetails = true;
					} else {
						$scope.ShowCustomer = true;
						$scope.listCoupons = false;
						$scope.showCoupons = function() {
							if ($scope.company.coupons.length==0) {
								alert('coupons not found');
								} else {
									$scope.listCoupons = true;
									$scope.coupons = response.data.coupons;
								}
							}
						}
					},function(error) {
						alert('operation failed' + error.data);
					});
			}
		}
	}
	           	// <!-- DELETE CUSTOMER-->
	$scope.customer;
	$scope.deleteCustomer = function() {
		$scope.hideAll();
		$scope.DeleteCust = true;
		$scope.id = '';
		$scope.executeForCustomer = function() {
			if ($scope.id == '') {
				window.alert("You must enter ID")
			}else {
		alert("do you want delete customer?");
		$http({
			method : 'DELETE',
			url : 'http://localhost:8080/rest/api/admin/deletecustomer/'
			+ $scope.id
		}).then(function(response) {
			window.alert('deleted');
			$scope.hideAll();
			$scope.WelcomePage = true;
			$scope.id = '';
		}, function(error) {
			alert('operation failed' + error.data);
		});
		}
		}
		}

	           	// <!-- UPDATE CUSTOMER -->
	$scope.customerToUpDate = function() {
		$scope.hideAll();
		$scope.UpdateCust = true;
		$scope.executeForCustomer = function() {
			if ($scope.id == '') {
				window.alert("You must enter ID")
			}else {
				alert("do you want update customer");
				var id = $scope.id;
				var customerEmail = $scope.email;
				var customerPassword = $scope.password;

				var customerDetails = {
				id: id,
				customerEmail: customerEmail,
				customerPassword: customerPassword
			};
			$http.put('http://localhost:8080/rest/api/admin/updateCustomer', customerDetails)
				.then(function (response){
						window.alert('updated');
						$scope.hideAll();
						$scope.WelcomePage = true;

					}, function(error) {
						alert('operation failed' + error.data);
					});
					}
					}
					}

 					// <!-- ADD COMPANY-->
		$scope.addNewCustomer = function() {
			$scope.hideAll();
			$scope.AddCustomer = true;
			$scope.executeForCustomer = function() {
			alert("do you want add customer?")
			if ($scope.email == null || $scope.password == null
					|| $scope.name == null) {
				window.alert("all fields must be fill")
			} else {
				var customerName = $scope.name;
				var customerEmail = $scope.email;
				var customerPassword = $scope.password;
				var addNewCompany = {
				customerName: customerName,
				customerEmail: customerEmail,
				customerPassword: customerPassword
			};
			$http.post(
					'http://localhost:8080/rest/api/admin/newCustomer',addNewCompany)
					.then(function(response) {

				alert("added");
				$scope.WelcomePage = true;
				$scope.AddCompany = false;
			}, function(error) {
				alert('operation failed', error.data);
			});
		}
	};
}


	$scope.LogOut = function() {
		alert("Log Out");
		$http.get('http://localhost:8080/EKLWS/rest/login/off').then(
				function(response) {

					window.location.assign("http://localhost:8080/EKLWS");
				}, function(error) {
					alert('operation failed ' , error.data);
				});

	}
});
