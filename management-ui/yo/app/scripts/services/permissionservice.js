'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.PermissionService
 * @description
 * # PermissionService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('PermissionService', function ($http, ConfigService) {

    // Public API here
    return {
      findAll : function (page, size) {
          return $http.get(ConfigService.serverUrl + "/api/system/permission?page=" + page + "&size=" + size);
        },
        save : function (data) {
          if (data.id) {
              return $http.put(ConfigService.serverUrl + "/api/system/permission/" + data.id, data);
          } else {
              return $http.post(ConfigService.serverUrl + "/api/system/permission", data);
          }
        },
        remove : function (id) {
          return $http.delete(ConfigService.serverUrl + "/api/system/permission/" + id);
        },
        findById : function (id) {
          return $http.get(ConfigService.serverUrl + "/api/system/permission/" + id);
        },
        search : function (param) {
          return $http.get(ConfigService.serverUrl + "/api/system/permission/search?param=" + param);
        },
        findByValue : function (value) {
          return $http.get(ConfigService.serverUrl + "/api/system/permission/byValue/" + value);
        },
        findByLabel : function (label) {
          return $http.get(ConfigService.serverUrl + "/api/system/permission/byLabel/" + label);
        },
        findWithoutPaging : function () {
          return $http.get(ConfigService.serverUrl + "/api/system/permission/withoutPaging");
        }
    };
  });
