'use strict';

/**
 * @ngdoc overview
 * @name managementUiApp
 * @description
 * # managementUiApp
 *
 * Main module of the application.
 */
angular
    .module('managementUiApp', [
        'ngAnimate',
        'ngAria',
        'ngCookies',
        'ngMessages',
        'ngResource',
        'ngRoute',
        'ngSanitize',
        'ngTouch',
        'ui.bootstrap',
        'ui.select',
        'ui.grid',
        'ui.grid.selection'
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
            })//================================ System
            .when('/system/permission', {
                templateUrl: 'views/system/permission.html',
                controller: 'PermissionCtrl',
                controllerAs: 'permission'
            })
            .when('/system/role', {
                templateUrl: 'views/system/role.html',
                controller: 'RoleCtrl',
                controllerAs: 'role'
            })
            .when('/system/tvSetting', {
                templateUrl: 'views/system/tvsetting.html',
                controller: 'TvSettingCtrl',
                controllerAs: 'tvSetting'
            })
            .when('/system/user', {
                templateUrl: 'views/system/user.html',
                controller: 'UserCtrl',
                controllerAs: 'user'
            })//================================= Master
            .when('/master/poli', {
                templateUrl: 'views/master/poli.html',
                controller: 'PoliCtrl',
                controllerAs: 'poli'
            })
            .when('/master/dokter', {
                templateUrl: 'views/master/dokter.html',
                controller: 'DokterCtrl',
                controllerAs: 'dokter'
            })
            .when('/master/loket', {
                templateUrl: 'views/master/loket.html',
                controller: 'LoketCtrl',
                controllerAs: 'loket'
            })
            .when('/master/category', {
                templateUrl: 'views/master/category.html',
                controller: 'CategoryCtrl',
                controllerAs: 'category'
            })
            //================================= Generate
            .when('/generate/kuota', {
                templateUrl: 'views/generate/kuota.html',
                controller: 'GenerateKuotaCtrl',
                controllerAs: 'generateKuota'
            })
            //================================= Loket
            .when('/transaksi/loket', {
              templateUrl: 'views/transaksi/loket.html',
              controller: 'TransaksiLoketCtrl',
              controllerAs: 'transaksiLoket'
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
    })
    .filter('propsFilter', function () {
        return function (items, props) {
            var out = [];

            if (angular.isArray(items)) {
                items.forEach(function (item) {
                    var itemMatches = false;

                    var keys = Object.keys(props);
                    for (var i = 0; i < keys.length; i++) {
                        var prop = keys[i];
                        var text = props[prop].toLowerCase();
                        if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
                            itemMatches = true;
                            break;
                        }
                    }

                    if (itemMatches) {
                        out.push(item);
                    }
                });
            } else {
                // Let the output be the input untouched
                out = items;
            }

            return out;
        };
    });
