'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:ReportAntrianHarianCtrl
 * @description
 * # ReportAntrianHarianCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('ReportAntrianHarianCtrl', function ($scope, ReportAntrianService) {
    $scope.paging = { currentPage: 1 };
		$scope.currentSeach = {};
		$scope.listData = [];
    
    $scope.nomorChanged = function () {
			if (!$scope.checkedNomor) {
				$scope.currentSearch.nomor = null;
			}
		};

		$scope.cancel = function () {
			$scope.currentSearch = {};
			$scope.checkedNomor = false;
			$scope.paging = { currentPage: 1 };
			$scope.listData = [];
		};
    
    $scope.search = function () {
      var params = {};
      params.tanggal = $scope.currentSearch.tanggal;
      if ($scope.currentSearch.nomor) {
        params.nomor = $scope.currentSearch.nomor;
      }

      params.size = 10;
      params.page = $scope.paging.currentPage - 1;

      ReportAntrianService.search(params).success(function (data) {
        $scope.listData = data.content;

        $scope.paging.itemsPerPage = (data.size);
        $scope.paging.totalItems = data.totalElements;
        $scope.paging.currentPage = parseInt(data.number) + 1;
        $scope.paging.maxPage = data.totalPages;
      });
    };
  });
