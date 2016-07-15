'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:TransaksiLoketCtrl
 * @description
 * # TransaksiLoketCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('TransaksiLoketCtrl', function ($scope, $timeout, TransaksiLoketService) {
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
      TransaksiLoketService.getAntrian($scope.loket.kategori.code, $scope.loket.nomorLoket).success(function (data) {
        if ($scope.loket.kategori.code == "A") {
          $scope.nextAntrian = data.A.content[0];
        } else if ($scope.loket.kategori.code == "B") {
          $scope.nextAntrian = data.B.content[0];
        } else {
          $scope.nextAntrian = data.F.content[0];
        }
        $scope.currentAntrian = data.current.content[0];
        $scope.statusCall = data.pemanggilan;
        $scope.totalAntrian = data.totalAntrian;
        $timeout(function () {
          $scope.runPolling();
        }, 1000);
      });
    };

    $scope.take = function () {
      TransaksiLoketService.takeAntrian($scope.nextAntrian.nomorAntrian, $scope.loket.nomorLoket).success(function (data) {
        if (data.number) {
          
          $scope.setPemanggilan(data);
        }
      });
    };

    $scope.setPemanggilan = function (x) {
      TransaksiLoketService.setCall(x.number, x.loket).success(function () {

      });
    };

    $scope.recall = function () {
      TransaksiLoketService.recall($scope.currentAntrian.nomorAntrian).success(function (data) {

      });
    };
  });
