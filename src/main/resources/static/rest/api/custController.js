var custApp = angular.module('docust', []);

custApp.controller('custController',function($scope,$http){

/////////////////
	// $scope.selectedRow = 0;
	// $scope.customer;
	// $scope.couponTypes=['RESTAURANTS', 'ELECTRICITY', 'FOOD', 'HEALTH', 'SPORTS', 'CAMPING', 'TRAVELLING'];

	$scope.hideAll=function(){
		$scope.WelcomePage = false;
		$scope.MainMenu = true;
		$scope.UpdateCustomer = false;
		$scope.ShowAllCoupons =false;
		$scope.ShowMyCoupons = false;
		$scope.ChoozePrice=false;
		$scope.ChoozeType=false;

	}

	$scope.hideAll();
	$scope.WelcomePage = true;
	$http.get('http://localhost:8080/rest/api/customer/customerDetailes/' + 4).then(function(response) {
		$scope.customer = response.data;
	},function(error){
		alert('operation failed',error.data);
	})

	$scope.hideAll();
	$scope.MainMenu = true;
	$scope.WelcomePage = true;



	$scope.getMyCoupons=function(){
		$scope.hideAll();
		$http.get('http://localhost:8080/rest/api/customer/allCoustomerCoupon/' + 4).then(function(response){
			$scope.coupons=response.data;
			if($scope.coupons == null){
				alert('not found');
				$scope.hideAll();
				$scope.WelcomePage = true;

			}else{
				$scope.hideAll();
				$scope.ShowMyCoupons=true;
			}
		},function(error){
			alert('operation failed',error.data);
		});

	}

	//////////////////////// FOR SALE
//////////////////////////////////////////////selectRow
	$scope.setClickedRow = function(index){
		$scope.selectedRow = index;
	}

	$scope.$watch('selectedRow', function() {
		console.log('Do Some processing');
	});
	////////////////////////////////////////
	$scope.getAllCoupons=function(){

		$scope.hideAll();
		$scope.ShowAllCoupons=true;
		$http.get('http://localhost:8080/rest/api/company/coupons').then(function(response){

			$scope.coupons=response.data;
			if($scope.coupons == null){
				alert('not found');
				$scope.hideAll();
				$scope.WelcomePage = true;

			}else{
				$scope.hideAll();
				$scope.ShowAllCoupons=true;
			}
		},function(error){
			alert('operation failed ' + error.data);
		});

	}
	/////////////////
		// $scope.selectedRow = 0;
		// $scope.customer;


	$scope.purchaseCoupon=function(){

		$scope.id=$scope.coupons[$scope.selectedRow].id;

		var couponId = $scope.id;

		alert("purchase coupon"+$scope.id);

			$http({
				method : 'PATCH',
				url : 'http://localhost:8080/rest/api/customer/customerCoupon/'+ couponId + '/' + 4
				}).then(function(response){
					window.alert("you purchased coupon" + $scope.id)
					$scope.hideAll();
					$scope.WelcomePage = true;
				},function(error){
					alert('operation failed ' + error.data);
				});



	}


		$scope.ShowCouponsByPrice=function(){
		$scope.price='';
		$scope.hideAll();
		$scope.ChoozePrice=true;
		$scope.getCouponsByPrice=function(){
		$http.get('http://localhost:8080/rest/api/customer/allCustomerCouponsByPrice/' + 4 + '/'+$scope.price).then(function(response){
			$scope.coupons=response.data;
			if(response.data == null){
				alert('not found');
				$scope.hideAll();
				$scope.WelcomePage = true;

			}else{
				$scope.hideAll();
				$scope.ShowAllCoupons=true;
				$scope.price='';
			}
		},function(error){
			alert('operation failed ',error.data);
		});
		}
		}

		$scope.ShowCouponsByType=function(){
			$scope.hideAll();
			$scope.ChoozeType=true;
			$scope.getCouponsByType=function(){
				$http.get('http://localhost:8080/rest/api/customer/allCustomerCouponByType/'+ 4 +'/' +$scope.type)
				.then(function(response){
					$scope.coupons=response.data;
					if($scope.coupons == null){
						alert('not found');
						$scope.hideAll();
						$scope.WelcomePage = true;
						$scope.type='';

					}else{
						$scope.hideAll();
						$scope.ShowAllCoupons=true;
						$scope.type='';
					}

			},function(error){
				alert('operation failed ',error.data);
			});
			}


				}


//////////////////////////////////////////////selectRow
		$scope.setClickedRow=function(index){
			$scope.selectedRow = index;
		}
		///////////////////////////////////////


		$scope.LogOut=function(){
			alert("Log Out");
			$http.get('http://localhost:8080/EKLWS/rest/login/off').then(
							function(response){

								window.location.assign("http://localhost:8080/EKLWS");
							},function(error){
								alert('operation failed ',error.data);
							});

		}

});
//////////////////////////////////////////////selectRow
// custApp.directive('arrowSelector',['$document',function($document){
// 	    return{
// 	        restrict:'A',
// 	        link:function(scope,elem,attrs,ctrl){
// 	            var elemFocus = false;
// 	            elem.on('mouseenter',function(){
// 	                elemFocus = true;
// 	            });
// 	            elem.on('mouseleave',function(){
// 	                elemFocus = false;
// 	            });
// 	            $document.bind('keydown',function(e){
// 	                if(elemFocus){
// 	                    if(e.keyCode == 38){
// 	                        console.log(scope.selectedRow);
// 	                        if(scope.selectedRow == 0){
// 	                            return;
// 	                        }
// 	                        scope.selectedRow--;
// 	                        scope.$apply();
// 	                        e.preventDefault();
// 	                    }
// 	                    if(e.keyCode == 40){
// 	                        if(scope.selectedRow == scope.coupons.length - 1){
// 	                            return;
// 	                        }
// 	                        scope.selectedRow++;
// 	                        scope.$apply();
// 	                        e.preventDefault();
// 	                    }
// 	                }
// 	            });
// 	        }
// 	    };
// 	}]);
