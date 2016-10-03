'use strict';

/**
 * @ngdoc service
 * @name antrianUiApp.MainService
 * @description
 * # MainService
 * Factory in the antrianUiApp.
 */
angular.module('antrianUiApp')
  .factory('MainService', function ($http, ConfigService) {
    // Public API here
    return {
      findDokterByIdPoli: function (idPoli) {
        return $http.get(ConfigService.serverUrl + "/api/pendaftaran/get/dokter/" + idPoli);
      },
      findCategory: function () {
        return $http.get(ConfigService.serverUrl + "/api/pendaftaran/get/category");
      },
      findPoli: function () {
        return $http.get(ConfigService.serverUrl + "/api/pendaftaran/get/poli");
      },
      checkBod: function () {
        return $http.get(ConfigService.serverUrl + "/api/pendaftaran/check/bod");
      },
      save: function (x) {
        return $http.post(ConfigService.serverUrl + "/api/pendaftaran/create", x);
      },
      findByNomorPasien: function (no) {
        return $http.get(ConfigService.serverUrl + "/api/transaksi/pasien/" + no);
      },
      savePasien: function (data) {
        return $http.post(ConfigService.serverUrl + "/api/transaksi/pasien/save", data);
      }
    };
  });
