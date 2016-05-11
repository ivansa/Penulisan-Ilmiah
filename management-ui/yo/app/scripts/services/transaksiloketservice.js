'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.TransaksiLoketService
 * @description
 * # TransaksiLoketService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('TransaksiLoketService', function ($http, ConfigService) {
    // Public API here
    return {
      getLoketByUserActive: function () {
        return $http.get(ConfigService.serverUrl + "/api/transaksi/loket/get/loket");
      },
      getAntrian: function (loketNumber) {
        return $http.get(ConfigService.serverUrl + "/api/transaksi/loket/get/antrian/" + loketNumber);
      },
    };
  });
