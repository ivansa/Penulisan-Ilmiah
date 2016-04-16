'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.PermissionService
 * @description
 * # PermissionService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('PermissionService', function () {
    // Service logic
    // ...

    var meaningOfLife = 42;

    // Public API here
    return {
      someMethod: function () {
        return meaningOfLife;
      }
    };
  });
