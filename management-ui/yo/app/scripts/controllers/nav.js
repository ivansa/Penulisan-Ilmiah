'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:NavCtrl
 * @description
 * # NavCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('NavCtrl', function ($q, $scope, $window, $location, $http, ConfigService, UserService) {
			$scope.login = function () {
                $window.location.href = ConfigService.authUrl;
            };
            
            $scope.logout = function () {
                $window.localStorage.removeItem('token');
                $window.location.href = $scope.logoutUrl + "&redirect=" + $location.absUrl();
            };
	  
			$scope.getTokenFromUrl = function () {
                var token;
                var hashParams = $location.path();
                if (!hashParams) {
                    return;
                }
                var eachParam = hashParams.substring(1).split('&');
                for (var i = 0; i < eachParam.length; i++) {
                    var param = eachParam[i].split('=');
                    if ('access_token' === param[0]) {
                        token = param[1];
                    }
                }
                if (token) {
                    $window.localStorage.setItem('token', token);
                    $scope.logoutUrl = ConfigService.logoutUrl + "?token=" + token;
                    $scope.redirectUrl = $location.absUrl();
                }
                $location.path('');
            };
            
             $scope.checkLogin = function () {
                // check apa ada token di URL
                if ($window.localStorage.getItem('token')) {
                    $scope.token = $window.localStorage.getItem('token');
                    $scope.logoutUrl = ConfigService.logoutUrl + "?token=" + $scope.token;
                    $scope.redirectUrl = $location.absUrl();
                    $scope.checkAuth();
                    return;
                }
                $scope.getTokenFromUrl();
                if ($window.localStorage.getItem('token')) {
                    $scope.token = $window.localStorage.getItem('token');
                    $scope.checkAuth();
                    return;
                }
                
                $scope.login();
            };
            
            
            $scope.checkUserLoggedIn = function () {
				UserService.getUserLoggedIn().success(function (data) {
                    if(data){
                        $scope.infoUser = {};
                        $scope.infoUser.user = data.username.toUpperCase();
                        $scope.infoUser.info = data.role.name.toUpperCase();
                    }
                });
			};
			
			$scope.authorities = [];
			$scope.checkAuth = function () {
				$http.get(ConfigService.authMe).success(function (data, status) {
					if (status === 200) {
                        if (data.authenticated) {
							angular.forEach(data.authorities, function (value, key) {
                                $scope.authorities.push(value.authority);
                            });
                            $scope.flagSystem = $scope.authorities.indexOf("ROLE_SYSTEM");
                            $scope.flagMaster = $scope.authorities.indexOf("ROLE_MASTER");
                            $scope.flagLoket = $scope.authorities.indexOf("ROLE_LOKET");
                            $scope.flagGenerate = $scope.authorities.indexOf("ROLE_GENERATE");
                            $scope.flagReport = $scope.authorities.indexOf("ROLE_REPORT"); 
                            //$scope.flagTV = $scope.authorities.indexOf("ROLE_SCREEN_TV"); 
                            $scope.flagTV = $scope.authorities.indexOf("ROLE_REPORT"); 
						} else {
                            $scope.logout();
                        }
                    } else {
                        $scope.logout();
                    }
                });
            };
            
            
            $scope.checkLogin();
			$scope.checkUserLoggedIn();
                            
  });
