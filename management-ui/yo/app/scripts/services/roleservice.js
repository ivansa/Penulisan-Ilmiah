'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.RoleService
 * @description
 * # RoleService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('RoleService', function ($http, ConfigService) {

    // Public API here
    return {
			findAll : function (page, size) {
              return $http.get(ConfigService.serverUrl + "/api/system/role?page=" + page + "&size=" + size);
            },
            save : function (data) {
                if (data.id) {
                    return $http.put(ConfigService.serverUrl + "/api/system/role/" + data.id, data);
                } else {
                    return $http.post(ConfigService.serverUrl + "/api/system/role", data);
                }
            },
            remove : function (id) {
                return $http.delete(ConfigService.serverUrl + "/api/system/role/" + id);
            },
            findById : function (id) {
                return $http.get(ConfigService.serverUrl + "/api/system/role/" + id);
            },
            findByName : function (name) {
                return $http.get(ConfigService.serverUrl + "/api/system/role/byName/" + name);
            },
            search : function (param) {
                return $http.get(ConfigService.serverUrl + "/api/system/role/search?param=" + param);
            },
            findWithoutPaging : function () {
                return $http.get(ConfigService.serverUrl + "/api/system/role/withoutPaging");
            }
    };
  });
