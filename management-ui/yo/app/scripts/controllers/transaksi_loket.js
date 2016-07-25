'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:TransaksiLoketCtrl
 * @description
 * # TransaksiLoketCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('TransaksiLoketCtrl', function ($scope, $timeout, TransaksiLoketService, HistoryService) {
    $scope.loket = {};
    $scope.nextAntrian = {};
    $scope.currentAntrian = {};
    $scope.pollingInterval = null;
    $scope.pageHistory = "SEARCH";
    $scope.titleHistory = "Form Pasien";

    $scope.getLoket = function () {
      TransaksiLoketService.getLoketByUserActive().success(function (data) {
        $scope.loket = data;
        $scope.runPolling();
      });
    };

    $scope.getLoket();

    $scope.createNew = function () {
      $scope.currentPasien = {};
      $scope.pageHistory = "PASIEN";
    };

    $scope.search = function () {
      HistoryService.findById($scope.currentSearch).success(function (data) {
        $scope.currentPasien = data;
        $scope.currentPasien.dateOfBirth = new Date(data.dateOfBirth);
        $scope.pageHistory = "PASIEN";
      });
    };

    $scope.clear = function () {
      $scope.currentPasien = {};
      $scope.currentHistory = {};
      $scope.pageHistory = "SEARCH";
      $scope.titleHistory = "Form Pasien";
      $scope.currentSearch = null;
      $("#modalHistoryObat").modal("hide");
      $("#modalHistoryPasien").modal("hide");
    };

    $scope.showListHistory = function () {
      if ($scope.loket.kategori.code == "F") {
        HistoryService.lastHistoryObat($scope.currentHistory.pasien.id).success(function (data) {
          $scope.listHistory = data;
          $("#modalHistoryObat").modal("show");
        });
      } else {
        HistoryService.lastHistoryPasien($scope.currentHistory.pasien.id).success(function (data) {
          $scope.listHistory = data;
          $("#modalHistoryPasien").modal("show");
        });
      }
    };

    $scope.saveHistory = function () {
      if ($scope.loket.kategori.code == "F") {
        HistoryService.saveHistoryObat($scope.currentHistory).success(function (data) {
          $scope.clear();
        });
      } else {
        HistoryService.saveHistoryPasien($scope.currentHistory).success(function (data) {
          $scope.clear();
        });
      }
      bootbox.alert("Data Berhasil Disimpan");
    };

    $scope.savePasein = function () {
      HistoryService.savePasien($scope.currentPasien).success(function (data) {
        $scope.currentHistory = {};
        $scope.currentHistory.pasien = data;
        //$scope.currentHistory.doctorName = $scope.currentAntrian.dokter.namaDokter;
        $scope.currentHistory.doctorName = "asdasd";

        if ($scope.loket.kategori.code == "F") {
          //Show Hitory Obat
          $scope.titleHistory = "Form History Obat";
          $scope.pageHistory = "HISTORY_OBAT";
        } else {
          //Show History Dokter
          $scope.titleHistory = "Form History Pasien";
          $scope.pageHistory = "HISTORY_PASIEN";
        }

      });
    };

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
