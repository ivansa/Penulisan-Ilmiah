'use strict';

/**
 * @ngdoc function
 * @name antrianUiApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the antrianUiApp
 */
angular.module('antrianUiApp')
  .controller('MainCtrl', function ($scope, MainService) {
    $scope.formStatus = "CATEGORY";
    $scope.listCategory = [];
    $scope.listPoli = [];
    $scope.listDokter = [];
    $scope.currentAntrian = {};
    $scope.codeCategory = null;

    $scope.reloadData = function () {
      MainService.findCategory().success(function (data) {
        $scope.listCategory = data;
      });
    };

    $scope.checkBod = function () {
      MainService.checkBod().success(function (data) {
        if (data.isBod) {
          $scope.title = "Select Category";
          $scope.formStatus = "CATEGORY";
          $scope.codeCategory = null;
          $scope.reloadData();
        } else {
          $scope.title = "Generate Kuota Belum Dijalankan";
        }
      });
    };
    $scope.checkBod();

    $scope.categorySelect = function (x) {
      if (x == "F") {
        //$scope.currentAntrian.category = 
      } else {

        $scope.codeCategory = x;
        $scope.formStatus = "POLI";
        $scope.title = "Select Poli";
        $scope.getDataPoli();
      }
    };

    $scope.getDataPoli = function () {
      MainService.findPoli().success(function (data) {
        $scope.listPoli = data;
      });
    };

    $scope.getDataDokter = function (x) {
      MainService.findDokterByIdPoli(x).success(function (data) {
        $scope.listDokter = data;
      });
    };

    $scope.poliSelect = function (id) {
      $scope.formStatus = "DOKTER";
      $scope.title = "Select Dokter";
      $scope.getDataDokter(id);
    };

    $scope.back = function () {
      if ($scope.formStatus == "DOKTER") {
        $scope.categorySelect($scope.codePoli);
      } else if ($scope.formStatus == "POLI") {
        $scope.checkBod();
      }
    };

    $scope.saveAntrian = function (x) {
      var param = {};
      param.idKuota = x;
      param.categoryCode = $scope.codeCategory;
      MainService.save(param).success(function (data) {
        $scope.print(data);
      });
    };

    $scope.print = function (x) {
      var popupWin = window.open('', 'printableArea');
      popupWin.document.write(
        '<html>'
        + '<head>'
        + '</head>'
        + '<body style="border:2px solid grey;width:400px;height:250px;padding-top:2em">'
        + '<p align="center">Rumah Sakit</p>'
        + '<p align="center">Dr. H. Marzoeki Mahdi</p>'
        + '<h1 style="text-align:center;color:blue">' + x.nomorAntrian + '</h1>'
        + '<p align="center">' + x.name + '<br />(' + x.description + ')</p>'
        + '<p align="center">Tiket Harap Disimpan</p>'
        + '</body>'
        + '</html>'
      );
      popupWin.document.close();
      setTimeout(function () {
        popupWin.print();
        popupWin.close();
        $scope.checkBod();
      }, 100);
    };
  });
