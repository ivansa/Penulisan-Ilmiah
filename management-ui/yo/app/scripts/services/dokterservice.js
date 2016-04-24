'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.DokterService
 * @description
 * # DokterService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('DokterService', function ($http, ConfigService) {
    // Public API here
    return {
      findAll: function (page, size) {
        return $http.get(ConfigService.serverUrl + "/api/master/dokter?page=" + page + "&size=" + size);
      },
      save: function (data) {
        if (data.id) {
          return $http.put(ConfigService.serverUrl + "/api/master/dokter/" + data.id, data);
        } else {
          return $http.post(ConfigService.serverUrl + "/api/master/dokter", data);
        }
      },
      remove: function (id) {
        return $http.delete(ConfigService.serverUrl + "/api/master/dokter/" + id);
      },
      findById: function (id) {
        return $http.get(ConfigService.serverUrl + "/api/master/dokter/" + id);
      },
      findByNip: function (nip) {
        return $http.get(ConfigService.serverUrl + "/api/master/dokter/byNip/" + nip);
      },
      findWithoutPaging: function () {
        return $http.get(ConfigService.serverUrl + "/api/master/dokter/withoutPaging");
      }
    };
  });
