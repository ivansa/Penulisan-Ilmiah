'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.RoleService
 * @description
 * # RoleService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('RoleService', function () {
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
