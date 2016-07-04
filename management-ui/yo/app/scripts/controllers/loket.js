'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:LoketCtrl
 * @description
 * # LoketCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('LoketCtrl', function ($scope, $timeout, LoketService, CategoryService) {
    $scope.currentLoket = {};
    $scope.paging = { currentPage: 1 };


    $scope.reloadPage = function () {
      LoketService.findAll($scope.paging.currentPage - 1, 10).success(function (data) {
        $scope.listLoket = data.content;

        $scope.paging.itemsPerPage = (data.size);
        $scope.paging.totalItems = data.totalElements;
        $scope.paging.currentPage = parseInt(data.number) + 1;
        $scope.paging.maxPage = data.totalPages;
      });

      CategoryService.findWithoutPaging().success(function (data) {
        $scope.listCategory = data;
      });
    };
    $scope.reloadPage();

    $scope.showModalInputLoket = function () {
      $("#modalAddLoket").modal("show");
    };

    $scope.showDialogDelete = function (x) {
      $scope.numberDelete = x.nomorLoket;
      $scope.idLoket = x.id;
      $("#modalConfirmDelete").modal("show");
    };

    $scope.hideModalInputLoket = function () {
      $("#modalAddLoket").modal("hide");
      $scope.currentLoket = {};
      $scope.validateNumber = false;
    };

    $scope.clearForm = function () {
      $scope.hideModalInputLoket();
      $scope.reloadPage();
      $("#modalConfirmDelete").modal("hide");
    };

    $scope.save = function () {
      // validate by code
      LoketService.findByNumber($scope.currentLoket.nomorLoket).success(function (data) {
        if (data.length !== 0 && $scope.editNumber !== data.nomorLoket) {
          $scope.validateNumber = true;
        } else {
          $scope.validateNumber = false;
          $scope.saveAction();
        }
      });
    };

    $scope.saveAction = function () {
      // save action
      LoketService.save($scope.currentLoket).success(function () {
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
      LoketService.remove(id).success(function () {
        $scope.clearForm();
        $scope.showAlert();
        $scope.typeAlert = 'danger';
        $scope.messageAlert = 'Data have been deleted';
      });
    };

    $scope.edit = function (x) {
      LoketService.findById(x.id).success(function (data) {
        $scope.currentLoket = data;
        $scope.editNumber = x.nomorLoket;
        $scope.showModalInputLoket();
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


