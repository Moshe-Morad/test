var compApp = angular.module('docompany', []);

compApp.controller('cmpControllers',function($rootScope, $scope, $http) {
						$scope.MainMenu = true;
						$scope.welcomePage = true;

						$scope.hideAllTables = function() {
						$scope.welcomePage = false;
						$scope.createCoupon = false;
						$scope.showTableAllCoupons = false;
						$scope.showCompanyDetailes = false;
						$scope.showTableCompany = false;
						$scope.addCoupon = false;
						$scope.showFindCoupon = false;
						$scope.showUpdateCoupon = false;
						$scope.showCouponByPrice = false;
						$scope.showCouponByPriceTable = false;
						$scope.showCouponByType = false;
						$scope.showCouponByTypeTable = false;
						$scope.showCouponByDate = false;
						$scope.showCouponByDateTable = false;
						$scope.showCouponById = false;
						$scope.showCouponByIdTable = false;
						$scope.showRemoveCoupon = false;
						$scope.showImage = false;
					}

					var id = $scope.login;



					$scope.hideAllTables();
					$scope.welcomePage = true;
					// $scope.MainMenu = true;
					$http.get('http://localhost:8080/rest/api/company.html/comanyDetailes/' + id)
					.then(function(response) {
								$scope.company = response.data;
								$scope.welcomePage = true;
							}, function(error) {
								alert('operation failed ', error.data);
							});

					// companyDetails
					$scope.getCompanyDetails = function() {
						$scope.hideAllTables();
						$scope.showCompanyDetails = true;
						$scope.showTableCompany = true;
						$http.get('http://localhost:8080//rest/api/company/comanyDetailes/' + id).then(function(response) {
									$scope.company = response.data;
									$scope.hideAllTables();
								}, function(error) {
									alert('operation failed ', error.data);
								});
					}

					// getAllCoups
					$scope.findAllCoupons = function() {
						$scope.hideAllTables();
						$scope.showTableActiveCoupon = true;
						/*
						 * $http.get("http://localhost:8080/EKLWS/rest/company/allcoupons").then(
						 * function(response) {
						 * //console.log(response.data.coupon); $scope.coupons =
						 * response.data; });
						 */
						$http.get('http://localhost:8080/rest/api/company/coupons').then(
										function(response) {
											$scope.couponsAct = response.data;
											$scope.welcomePage = true;
										},
										function(error) {
											alert('operation failed ',error.data);
										});
					}

					// COMPANY COUPONS
					$scope.companyCoupons = function() {
						$scope.hideAllTables();
						$scope.showCompanyCoupon = true;
						/*
						 * $http.get("http://localhost:8080/EKLWS/rest/company/allcoupons").then(
						 * function(response) {
						 * //console.log(response.data.coupon); $scope.coupons =
						 * response.data; });
						 */
						$http.get('http://localhost:8080/rest/api/company/companyCoupons/' + 3).then(
										function(response) {
											$scope.couponsAct = response.data;
											$scope.welcomePage = true;
										},
										function(error) {
											alert('operation failed ',error.data);
										});
					}


					// remove selected coupon
					$scope.coupon;
					$scope.deleteCoupon = function() {
						$scope.hideAllTables();
						$scope.showRemoveCoupon = true;
						$scope.submitRemove = function() {
							$http({
								method : 'DELETE',
								url : 'http://localhost:8080/rest/api/company/deletecoupon/'
								+ 3 + '/' + $scope.id
							}).then(
											function(response) {
												window.alert('deleted');
												$scope.hideAllTables();
												$scope.welcomePage = true;
												// $scope.resRemove = response.data;
												// $scope.getAllCoups();
											},function(error) {
												alert('operation failed ',error.data);
											});
						}
					}



					// createCoupon
					$scope.addNewCoupon = function() {
						$scope.hideAllTables();
						$scope.addCoupon = true;
						// $scope.couponWasCreated = null;

						// $scope.today = new Date();
						// $http.get('http://localhost:8080/rest/api/company/coupons').then(function(response) {
						// 			$scope.CouponType = response.data;
						// 		}, function(error) {
						// 			alert('operation failed ', error.data);
						// 		});

						$scope.submitNewCoupon = function() {
							// if ($scope.title == null
							// 		|| $scope.startDate == null
							// 		|| $scope.endDate == null
							// 		|| $scope.amount == null
							// 		|| $scope.type == null
							// 		|| $scope.price == null) {
							// 	alert("Fill all mandatory fields");
							// } else {

								var title = $scope.title;
								// var startDateMillis = new Date($scope.startDate).getTime();
								var startDateMillis = $scope.startDate;
								// var startDate = JSON.stringify(startDateMillis);
								var endDateMillis = $scope.endDate;
								// var endDate = JSON.stringify(endDateMillis);
								var amount = $scope.amount;
								var type = $scope.type;
								var message = $scope.message;
								var price = $scope.price;
								var image = $scope.image;

								var newCoupon = {
  								title: title,
	  							startDate : startDateMillis,
  								endDate : endDateMillis,
  								amount : amount,
  								type : type,
  								message : message,
  								price : price,
  								image : image
								}

								$http.post('http://localhost:8080/rest/api/company/newCoupon/' + 3, newCoupon)
								.then(function(response) {
														alert("added");
														$scope.welcomePage = true;
														$scope.addCoupon = false;
													// $scope.couponWasCreated = response.data;
												},function(error) {
													alert('operation failed '+ error.data);
												});
								// $scope.title = null;
								// $scope.startDate = null;
								// $scope.endDate = null;
								// $scope.amount = null;
								// $scope.type = null;
								// $scope.price = null;
								// $scope.message = null;
							// }
						}
					}

					// update coupon
					$scope.upDateCoupon = function() {
						$scope.hideAllTables();
						$scope.showFindCoupon = true;
						// $scope.today = new Date();
						// $scope.tomorrow = new Date();
						// $scope.tomorrow.setDate($scope.today.getDate() + 1);

						$scope.findCouponById = function() {
							$scope.couponWasUpdated = null;
							$http.get('http://localhost:8080/rest/api/company/coupon/'+ $scope.id).then(
											function(response) {
												$scope.showUpdateCoupon = true;
												$scope.coupon = response.data;
												$scope.endDate = $scope.coupon.endDate;
												//$scope.origEDate = new Date($scope.coupon.endDate);
												//$scope.eDate = $scope.origEDate;
												// $scope.amount = $scope.coupon.amount;
												// $scope.message = $scope.coupon.message;
												$scope.price = $scope.coupon.price;

												$scope.submitUpdatedCoupon = function() {
													if ($scope.eDate == null
															|| $scope.price == null) {
														alert("Fill all mandatory fields");
													} else {
														var eDateMillis = $scope.eDate;

														var couponDetails = {
															id : $scope.id,
															endDate : eDateMillis,
															price : $scope.price
														}

														$http.put('http://localhost:8080/rest/api/company/updateCoupon/'+ 3
																				,couponDetails).then(
																		function(response) {
																			$scope.couponWasUpdated = response.data;
																			$scope.coupon = null;
																			$scope.eDate = null;
																			$scope.amount = null;
																			$scope.message = null;
																			$scope.price = null;
																			$scope.id = null;
																			$scope.welcomePage = true;
																		},function(error) {
																			alert('operation failed '+ error.data);
																		})
														$scope.showUpdateCoupon = false;
													}
												}
											},
											function(error) {
												alert('operation failed '+ error.data);
											})
						}
						$scope.couponWasUpdated = null;
					}

					// find coupon by price
					$scope.coupon;
					$scope.findCouponByPriceLimit = function() {
						$scope.hideAllTables();
						$scope.showCouponByPrice = true;
						$scope.findByPrice = "";
						$scope.couponsToFind = function() {
							if ($scope.findByPrice == " ") {
								window.alert("Please enter price")
							} else {
								$scope.showCouponByPriceTable = true;
								$http.get('http://localhost:8080/rest/api/company/couponPrice/'
								+ 3 + '/' + $scope.findByPrice).then(function(response) {
											$scope.resByPrice = response.data;
											$scope.welcomePage = true;
										},function(error) {
											alert('operation failed ',error.data);
										})
							}
						}

					}

					// find coupon by type
					$scope.findCouponByType = function() {
						$scope.hideAllTables();
						$scope.showCouponByType = true;

						$http.get('http://localhost:8080/rest/api/company//typeCoupons').then(function(response) {
									$scope.couponTypes = response.data;
								}, function(error) {
									alert('operation failed ', error.data);
								});

						$scope.couponsToFind = function() {
							if ($scope.findByType == null) {
								alert("Please select type")
							} else {
								$scope.showCouponByTypeTable = true;
								$http.get('http://localhost:8080/rest/api/company/typeCoupons/'+ $scope.findByType).then(
										function(response) {
											$scope.resByType = response.data;
											$scope.welcomePage = true;
										},
										function(error) {
											alert('operation failed ',error.data);
										})
							}
						}

					}

					// find coupon by id
					$scope.findCouponById = function() {
						$scope.hideAllTables();
						$scope.showCouponById = true;

						$scope.couponsToFind = function() {
							if ($scope.findById == null) {
								alert("Please enter id")
							} else {
								$scope.findById;
								$scope.showCouponByIdTable = true;
								$http.get('http://localhost:8080/rest/api/company/coupon/' + $scope.findById).then(
										function(response) {
											$scope.resById = response.data;
											$scope.welcomePage = true;
										},
										function(error) {
											alert('operation failed ',error.data);
										})
							}
						}
					}

					// find coupon by date
					$scope.findCouponByEndDate = function() {
						$scope.hideAllTables();
						$scope.showCouponByDate = true;

						$scope.couponsToFind = function() {
							if ($scope.findByDate == null) {
								alert("Please enter date")
							} else {
								var endDate = $scope.findByDate;
								$scope.showCouponByDateTable = true;
								$http.get('http://localhost:8080/rest/api/company/couponEndDate/' + 3 + '/'+ endDate).then(
										function(response) {
											$scope.resByDate = response.data;
											$scope.welcomePage = true;
										},
										function(error) {
											alert('operation failed ',error.data);
										})
							}
						}
					}

					// Log off
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
