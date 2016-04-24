'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.LoketService
 * @description
 * # LoketService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('LoketService', function ($http, ConfigService) {
    // Public API here
    return {
      findAll: function (page, size) {
        return $http.get(ConfigService.serverUrl + "/api/master/loket?page=" + page + "&size=" + size);
      },
      save: function (data) {
        if (data.id) {
          return $http.put(ConfigService.serverUrl + "/api/master/loket/" + data.id, data);
        } else {
          return $http.post(ConfigService.serverUrl + "/api/master/loket", data);
        }
      },
      remove: function (id) {
        return $http.delete(ConfigService.serverUrl + "/api/master/loket/" + id);
      },
      findById: function (id) {
        return $http.get(ConfigService.serverUrl + "/api/master/loket/" + id);
      },
      findByNumber: function (number) {
        return $http.get(ConfigService.serverUrl + "/api/master/loket/byNomor/" + number);
      },
      findWithoutPaging: function () {
        return $http.get(ConfigService.serverUrl + "/api/master/loket/withoutPaging");
      }
    };
  });
