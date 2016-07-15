'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.ReportKuotaService
 * @description
 * # ReportKuotaService
 * Service in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('ReportKuotaService', function ($http, ConfigService) {
    // Public API here
    return {
      search: function (params) {
        return $http({
          'url': ConfigService.serverUrl + '/api/report/kuota',
          'method': 'GET',
          'params': params
        });
      }
    }
  });
