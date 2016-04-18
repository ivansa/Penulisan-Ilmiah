'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.PoliService
 * @description
 * # PoliService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('PoliService', function ($http, ConfigService) {
    // Public API here
    return {
      findAll : function (page, size) {
          return $http.get(ConfigService.serverUrl + "/api/master/poli?page=" + page + "&size=" + size);
        },
        save : function (data) {
          if (data.id) {
              return $http.put(ConfigService.serverUrl + "/api/master/poli/" + data.id, data);
          } else {
              return $http.post(ConfigService.serverUrl + "/api/master/poli", data);
          }
        },
        remove : function (id) {
          return $http.delete(ConfigService.serverUrl + "/api/master/poli/" + id);
        },
        findById : function (id) {
          return $http.get(ConfigService.serverUrl + "/api/master/poli/" + id);
        },
        findByCode : function (code) {
          return $http.get(ConfigService.serverUrl + "/api/master/poli/byCode/" + code);
        },
        findWithoutPaging : function () {
          return $http.get(ConfigService.serverUrl + "/api/master/poli/withoutPaging");
        }
    };
  });
