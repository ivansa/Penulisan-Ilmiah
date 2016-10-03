'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.UserService
 * @description
 * # UserService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('UserService', function ($http, ConfigService) {
    return {
		findAll : function (page, size) {
          return $http.get(ConfigService.serverUrl + "/api/system/user?page=" + page + "&size=" + size);
        },
        save : function (data) {
          if (data.id) {
              return $http.put(ConfigService.serverUrl + "/api/system/user/" + data.id, data);
          } else {
              return $http.post(ConfigService.serverUrl + "/api/system/user", data);
          }
        },
        remove : function (id) {
          return $http.delete(ConfigService.serverUrl + "/api/system/user/" + id);
        },
        findById : function (id) {
          return $http.get(ConfigService.serverUrl + "/api/system/user/" + id);
        },
        search : function (param) {
          return $http.get(ConfigService.serverUrl + "/api/system/user/search?param=" + param);
        },
        findByUsername : function (username) {
          return $http.get(ConfigService.serverUrl + "/api/system/user/byUsername/" + username);
        },
        findByEmail : function (email) {
          return $http.get(ConfigService.serverUrl + "/api/system/user/byEmail?email=" + email);
        },
        findByRole : function (role) {
          return $http.get(ConfigService.serverUrl + "/api/system/user/byRole/" + role);
        },
        getUserLoggedIn: function(){
                return $http.get(ConfigService.serverUrl + "/api/user/loggedIn");  
        }
     }
  });
