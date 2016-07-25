'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.HistoryService
 * @description
 * # HistoryService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('HistoryService', function ($http, ConfigService) {
   
    // Public API here
    return {
      savePasien: function (data) {
          return $http.post(ConfigService.serverUrl + "/api/transaksi/pasien/save", data);
      },
      saveHistoryPasien: function (data) {
          return $http.post(ConfigService.serverUrl + "/api/transaksi/history/pasien/save", data);
      },
      saveHisoryObat: function (data) {
          return $http.post(ConfigService.serverUrl + "/api/transaksi/history/obat/save", data);
      },
      findById: function (no) {
        return $http.get(ConfigService.serverUrl + "/api/transaksi/pasien/" + no);
      },
      lastHistoryPasien: function (id) {
        return $http.get(ConfigService.serverUrl + "/api/transaksi/history/pasien/" + id);
      },
      lastHistoryObat: function (id) {
        return $http.get(ConfigService.serverUrl + "/api/transaksi/history/obat/" + id);
      },
    };
  });
