'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.ScreenTvService
 * @description
 * # ScreenTvService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('ScreenTvService', function ($http, ConfigService) {
    // Public API here
    return {
      getLoketByCategory: function () {
        return $http.get(ConfigService.serverUrl + "/api/screen/tv/get/loket/byCategory");
      }
    };
  });
