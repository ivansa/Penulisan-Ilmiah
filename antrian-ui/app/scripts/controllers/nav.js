'use strict';

/**
 * @ngdoc function
 * @name antrianUiApp.controller:NavCtrl
 * @description
 * # NavCtrl
 * Controller of the antrianUiApp
 */
angular.module('antrianUiApp')
  .controller('NavCtrl', function ($q, $scope, $window, $location, $http, $interval, ConfigService) {
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

    $scope.authorities = [];
    $scope.checkAuth = function () {
      $http.get(ConfigService.authMe).success(function (data, status) {
        if (status === 200) {
          if (data.authenticated) {
            angular.forEach(data.authorities, function (value, key) {
              $scope.authorities.push(value.authority);
            });
            $scope.FlagPendaftaran = $scope.authorities.indexOf("ROLE_REPORT");
            if(!$scope.FlagPendaftaran){
              $scope.logout();
            }
          } else {
            $scope.logout();
          }
        } else {
          $scope.logout();
        }
      });
    };
    
    $scope.intervalStatus = function(){
      $interval(
        function(){ 
           $scope.checkAuth(); 
        }, 60000);
    }

    $scope.checkLogin();
  });
