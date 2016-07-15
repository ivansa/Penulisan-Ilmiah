'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.ReportAntrianService
 * @description
 * # ReportAntrianService
 * Service in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('ReportAntrianService', function ($http, ConfigService) {
    // Public API here
    return {
      search: function (params) {
        return $http({
          'url': ConfigService.serverUrl + '/api/report/antrian',
          'method': 'GET',
          'params': params
        });
      }
    }
  });
