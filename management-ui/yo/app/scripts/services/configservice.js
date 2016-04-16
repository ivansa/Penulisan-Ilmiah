'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.ConfigService
 * @description
 * # ConfigService
 * Service in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('ConfigService', function ($location) {
    var proto = $location.protocol();
    var host = $location.host();
    var port = $location.port();
    var server = proto+'://'+host;
    var urlApi = server+":"+port+"/antrian-server";
    var urlApp = server+":"+port+"/#/";
    var urlAuth = server+":8080/auth-server/oauth/authorize?client_id=management&response_type=token&scope=write&redirect_uri="+urlApp;
    var urlLogout = server+":8080/auth-server/logout";
    var urlMe = server+":"+port+"/auth-server/me";
    return {
        server: server,
        serverUrl: urlApi,
        authUrl: urlAuth,
        logoutUrl: urlLogout,
        clientUrl: urlApp,
        authMe: urlMe
    };
  });
