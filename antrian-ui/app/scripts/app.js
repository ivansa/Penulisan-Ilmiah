'use strict';

/**
 * @ngdoc overview
 * @name antrianUiApp
 * @description
 * # antrianUiApp
 *
 * Main module of the application.
 */
angular
  .module('antrianUiApp', [
    'ngAnimate',
    'ngAria',
    'ngCookies',
    'ngMessages',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl',
        controllerAs: 'main'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl',
        controllerAs: 'about'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  
    .service('APIInterceptor', function ($q, $location, $window, ConfigService) {
        var service = this;

        service.request = function (config) {
            var token = $window.localStorage.getItem('token');
            if (token) {
                config.headers = config.headers || {};
                config.headers.Authorization = 'Bearer ' + token;
            }
            return config;
        };

        service.responseError = function (response) {
            var errmsg = '<div style="width:100%;"><img src="images/error.png" width="72px" height="60px"/>&nbsp;&nbsp;&nbsp;&nbsp;';
            errmsg += '<span style="font-size:18px; font-weight: bold; color: red;">';

            if (response.status === 401) {
                errmsg += 'You are currently not logged !';
            } else if (response.status === 403) {
                errmsg += 'You do not have permission to access this !';
            } else {
                if (response.data.message) {
                    errmsg += response.data.message;
                } else if (response.data.errorMessage) {
                    errmsg += response.data.errorMessage;
                } else {
                    errmsg += "Unknown Error";
                }
            }

            errmsg += '</span></div>';

            bootbox.dialog({
                message: errmsg,
                title: "Error - " + response.status,
                buttons: {
                    main: {
                        label: "OK",
                        className: "btn-primary",
                        callback: function () {
                            if (response.status === 401) {
                                var token = $window.localStorage.getItem('token');
                                var logoutUrl = ConfigService.logoutUrl + '?token=' + token;
                                var redirectUrl = logoutUrl + '&redirect=' + $location.absUrl();
                                $window.localStorage.removeItem('token');
                                $window.location.href = redirectUrl;
                            }
                            $q.reject(response);
                        }
                    }
                }
            });
        };
    })
    .config(function ($httpProvider) {
        $httpProvider.interceptors.push('APIInterceptor');
    });
