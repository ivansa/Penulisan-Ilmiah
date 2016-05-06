'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:DokterCtrl
 * @description
 * # DokterCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('DokterCtrl', function ($scope, $timeout, DokterService, PoliService) {
    $scope.currentDokter = {};
    $scope.paging = { currentPage: 1 };


    $scope.reloadPage = function () {
      DokterService.findAll($scope.paging.currentPage - 1, 10).success(function (data) {
        $scope.listDokter = data.content;

        $scope.paging.itemsPerPage = (data.size);
        $scope.paging.totalItems = data.totalElements;
        $scope.paging.currentPage = parseInt(data.number) + 1;
        $scope.paging.maxPage = data.totalPages;
      });

      PoliService.findWithoutPaging().success(function (data) {
        $scope.listPoli = data;
      });
    };
    $scope.reloadPage();

    $scope.showModalInputDokter = function () {
      $("#modalAddDokter").modal("show");
    };

    $scope.showDialogDelete = function (x) {
      $scope.codeDelete = x.code;
      $scope.idDokter = x.id;
      $("#modalConfirmDelete").modal("show");
    };

    $scope.hideModalInputDokter = function () {
      $("#modalAddDokter").modal("hide");
      $scope.currentDokter = {};
      $scope.validateCode = false;
    };

    $scope.clearForm = function () {
      $scope.hideModalInputDokter();
      $scope.reloadPage();
      $("#modalConfirmDelete").modal("hide");
    };

    $scope.save = function () {
      // validate by code
      DokterService.findByCode($scope.currentDokter.code).success(function (data) {
        if (data.length !== 0 && $scope.editCode !== data.code) {
          $scope.validateCode = true;
        } else {
          $scope.validateCode = false;
          $scope.saveAction();
        }
      });
    };

    $scope.saveAction = function () {
      // save action
      DokterService.save($scope.currentDokter).success(function () {
        $scope.clearForm();
        $scope.showAlert();
        $scope.typeAlert = 'success';
        $scope.messageAlert = 'Data have been saved';
      });
    };

    $scope.showAlert = function () {
      $scope.alert = true;
      $timeout(function () {
        $scope.alert = false;
      }, 2000);
    };

    $scope.remove = function (id) {
      DokterService.remove(id).success(function () {
        $scope.clearForm();
        $scope.showAlert();
        $scope.typeAlert = 'danger';
        $scope.messageAlert = 'Data have been deleted';
      });
    };

    $scope.edit = function (x) {
      DokterService.findById(x.id).success(function (data) {
        $scope.currentDokter = data;
        $scope.editCode = x.code;
        $scope.showModalInputDokter();
      });
    };
  })
  .directive('ngEnter', function () {
    return function (scope, element, attrs) {
      element.bind("keydown keypress", function (event) {
        if (event.which === 13) {
          scope.$apply(function () {
            scope.$eval(attrs.ngEnter);
          });

          event.preventDefault();
        }
      });
    };
  });



