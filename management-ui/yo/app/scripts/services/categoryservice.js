'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.CategoryService
 * @description
 * # CategoryService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('CategoryService', function ($http, ConfigService) {
    // Public API here
    return {
      findAll : function (page, size) {
          return $http.get(ConfigService.serverUrl + "/api/master/category?page=" + page + "&size=" + size);
        },
        save : function (data) {
          if (data.id) {
              return $http.put(ConfigService.serverUrl + "/api/master/category/" + data.id, data);
          } else {
              return $http.post(ConfigService.serverUrl + "/api/master/category", data);
          }
        },
        remove : function (id) {
          return $http.delete(ConfigService.serverUrl + "/api/master/category/" + id);
        },
        findById : function (id) {
          return $http.get(ConfigService.serverUrl + "/api/master/category/" + id);
        },
        findByCode : function (code) {
          return $http.get(ConfigService.serverUrl + "/api/master/category/byCode/" + code);
        },
        findWithoutPaging : function () {
          return $http.get(ConfigService.serverUrl + "/api/master/category/withoutPaging");
        }
    };
  });
