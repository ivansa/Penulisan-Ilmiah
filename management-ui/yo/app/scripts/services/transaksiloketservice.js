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
      takeAntrian: function (antrianNumber, loketNumber) {
        return $http.get(ConfigService.serverUrl + "/api/transaksi/loket/take?number=" + antrianNumber + "&loket=" + loketNumber);
      },
      setCall: function (antrianNumber, loketNumber) {
        return $http.get(ConfigService.serverUrl + "/api/pemanggilan/set?number=" + antrianNumber + "&loket=" + loketNumber);
      },
      recall: function (antrianNumber) {
        return $http.get(ConfigService.serverUrl + "/api/pemanggilan/recall?number=" + antrianNumber);
      },
    };
  });
