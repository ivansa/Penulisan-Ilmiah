'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:PoliCtrl
 * @description
 * # PoliCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('PoliCtrl', function ($scope, $timeout, PoliService) {
        $scope.currentPoli = {};
        $scope.paging = {currentPage: 1};

        $scope.reloadPage = function () {
            PoliService.findAll($scope.paging.currentPage - 1, 10).success(function (data) {
                $scope.listPoli = data.content;

                $scope.paging.itemsPerPage = (data.size);
                $scope.paging.totalItems = data.totalElements;
                $scope.paging.currentPage = parseInt(data.number) + 1;
                $scope.paging.maxPage = data.totalPages;
            });
        };
        $scope.reloadPage();

        $scope.showModalInputPoli = function () {
            $("#modalAddPoli").modal("show");
        };
        
        $scope.showDialogDelete = function (x) {
            $scope.codeDelete = x.code;
            $scope.idPoli = x.id;
            $("#modalConfirmDelete").modal("show");
        };

        $scope.hideModalInputPoli = function () {
            $("#modalAddPoli").modal("hide");
            $scope.currentPoli = {};
            $scope.validateCode = false;
        };

        $scope.clearForm = function () {
            $scope.hideModalInputPoli();
            $scope.reloadPage();
            $("#modalConfirmDelete").modal("hide");
        };

        $scope.save = function () {
            // validate by code
            PoliService.findByCode($scope.currentPoli.code).success(function (data) {
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
            PoliService.save($scope.currentPoli).success(function () {
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
            PoliService.remove(id).success(function () {
                $scope.clearForm();
                $scope.showAlert();
                $scope.typeAlert = 'danger';
                $scope.messageAlert = 'Data have been deleted';
            });
        };
        
        $scope.edit = function (x) {
            $scope.editCode = x.code;
            
            PoliService.findById(x.id).success(function (data) {
                $scope.currentPoli = data;
                $scope.showModalInputPoli();
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

