'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:TransaksiLoketCtrl
 * @description
 * # TransaksiLoketCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('TransaksiLoketCtrl', function ($scope, $interval, TransaksiLoketService) {
    $scope.loket = {};
    $scope.nextAntrian = {};
    $scope.currentAntrian = {};
    $scope.pollingInterval = null;

    $scope.getLoket = function () {
      TransaksiLoketService.getLoketByUserActive().success(function (data) {
        $scope.loket = data;
        $scope.runPolling();
      });
    };

    $scope.getLoket();

    $scope.runPolling = function () {
      $scope.pollingInterval = $interval(function () {
        TransaksiLoketService.getAntrian($scope.loket.nomorLoket).success(function (data) {
          if ($scope.loket.kategori.code == "A") {
            $scope.nextAntrian = data.A.content[0];
          } else if ($scope.loket.kategori.code == "B") {
            $scope.nextAntrian = data.B.content[0];
            console.log(data.B.content[0]);
          } else {
            $scope.nextAntrian = data.F.content[0];
            console.log("a")
          }
          $scope.currentAntrian = data.current.content[0];
        });
      }, 5000);
    };
  });
