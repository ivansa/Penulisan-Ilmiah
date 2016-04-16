'use strict';

/**
 * @ngdoc service
 * @name managementUiApp.LoketService
 * @description
 * # LoketService
 * Factory in the managementUiApp.
 */
angular.module('managementUiApp')
  .factory('LoketService', function () {
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
