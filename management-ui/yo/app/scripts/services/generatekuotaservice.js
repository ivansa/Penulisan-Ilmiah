'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.GenerateKuotaService
 * @description
 * # GenerateKuotaService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('GenerateKuotaService', function ($http, ConfigService) {
    // Public API here
    return {
      generate: function () {
        return $http.post(ConfigService.serverUrl + "/api/generate/kuota");
      }
    };
  });
