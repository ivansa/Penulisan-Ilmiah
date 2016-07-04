'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:CategoryCtrl
 * @description
 * # CategoryCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('CategoryCtrl', function ($scope, $timeout, CategoryService) {
        $scope.currentCategory = {};
        $scope.paging = {currentPage: 1};

        $scope.reloadPage = function () {
            CategoryService.findAll($scope.paging.currentPage - 1, 10).success(function (data) {
                $scope.listCategory = data.content;

                $scope.paging.itemsPerPage = (data.size);
                $scope.paging.totalItems = data.totalElements;
                $scope.paging.currentPage = parseInt(data.number) + 1;
                $scope.paging.maxPage = data.totalPages;
            });
        };
        $scope.reloadPage();

        $scope.showModalInputCategory = function () {
            $("#modalAddCategory").modal("show");
        };
        
        $scope.showDialogDelete = function (x) {
            $scope.codeDelete = x.code;
            $scope.idCategory = x.id;
            $("#modalConfirmDelete").modal("show");
        };

        $scope.hideModalInputCategory = function () {
            $("#modalAddCategory").modal("hide");
            $scope.currentCategory = {};
            $scope.validateCode = false;
        };

        $scope.clearForm = function () {
            $scope.hideModalInputCategory();
            $scope.reloadPage();
            $("#modalConfirmDelete").modal("hide");
        };

        $scope.save = function () {
            // validate by code
            CategoryService.findByCode($scope.currentCategory.code).success(function (data) {
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
            CategoryService.save($scope.currentCategory).success(function () {
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
            CategoryService.remove(id).success(function () {
                $scope.clearForm();
                $scope.showAlert();
                $scope.typeAlert = 'danger';
                $scope.messageAlert = 'Data have been deleted';
            });
        };
        
        $scope.edit = function (x) {
            $scope.editCode = x.code;
            
            CategoryService.findById(x.id).success(function (data) {
                $scope.currentCategory = data;
                $scope.showModalInputCategory();
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
