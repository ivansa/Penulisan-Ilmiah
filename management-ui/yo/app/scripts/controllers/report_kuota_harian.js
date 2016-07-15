'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:ReportKuotaHarianCtrl
 * @description
 * # ReportKuotaHarianCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('ReportKuotaHarianCtrl', function ($scope, ReportKuotaService) {
    $scope.paging = { currentPage: 1 };
    $scope.currentSeach = {};
    $scope.listData = [];

    $scope.dokterChanged = function () {
      if (!$scope.checkedDokter) {
        $scope.currentSearch.dokter = null;
      }
    };

    $scope.cancel = function () {
      $scope.currentSearch = {};
      $scope.checkedDokter = false;
      $scope.paging = { currentPage: 1 };
      $scope.listData = [];
    };

    $scope.search = function () {
      var params = {};
      params.tanggal = $scope.currentSearch.tanggal;
      if ($scope.currentSearch.dokter) {
        params.dokter = $scope.currentSearch.dokter;
      }

      params.size = 10;
      params.page = $scope.paging.currentPage - 1;

      ReportKuotaService.search(params).success(function (data) {
        $scope.listData = data.content;

        $scope.paging.itemsPerPage = (data.size);
        $scope.paging.totalItems = data.totalElements;
        $scope.paging.currentPage = parseInt(data.number) + 1;
        $scope.paging.maxPage = data.totalPages;
      });
    };
  });
