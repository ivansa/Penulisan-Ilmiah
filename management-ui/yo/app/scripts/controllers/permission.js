'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:PermissionCtrl
 * @description
 * # PermissionCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('PermissionCtrl', function ($scope, $timeout, PermissionService) {
		$scope.currentPermission = {};
        $scope.paging = {currentPage: 1};

        $scope.reloadPage = function () {
            PermissionService.findAll($scope.paging.currentPage - 1, 10).success(function (data) {
                $scope.permissions = data.content;

                $scope.paging.itemsPerPage = (data.size);
                $scope.paging.totalItems = data.totalElements;
                $scope.paging.currentPage = parseInt(data.number) + 1;
                $scope.paging.maxPage = data.totalPages;
            });
        };
        $scope.reloadPage();
        
        $scope.refreshPage = function(){
			$scope.searchPermission = null;
			$scope.reloadPage();
		};

        $scope.showModalInputPermission = function () {
            $("#modalAddPermission").modal("show");
        };
        
        $scope.showDialogDelete = function (x) {
            $scope.label = x.label;
            $scope.idPermission = x.id;
            $("#modalConfirmDelete").modal("show");
        };

        $scope.hideModalInputPermission = function () {
            $("#modalAddPermission").modal("hide");
            $scope.currentPermission = {};
            $scope.validateLabel = false;
            $scope.validateValue = false;
        };

        $scope.clearForm = function () {
            $scope.hideModalInputPermission();
            $scope.reloadPage();
            $scope.searchPermission = null;
            $("#modalConfirmDelete").modal("hide");
        };

        $scope.save = function () {
            // validate by label
            PermissionService.findByLabel($scope.currentPermission.label).success(function (data) {
                if (data.length !== 0 && $scope.editLabel !== data.label) {
                    $scope.validateLabel = true;
                } else {
                    $scope.validateLabel = false;
                    // validate by value
                    PermissionService.findByValue($scope.currentPermission.value).success(function (dataValue) {
                        if (dataValue.length !== 0 && $scope.editValue !== dataValue.value) {
                            $scope.validateValue = true;
                        } else {
                            $scope.validateValue = false;
                            $scope.saveAction();
                        }
                    });
                }
            });
        };
        
        $scope.saveAction = function () {
            // save action
            PermissionService.save($scope.currentPermission).success(function () {
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
            PermissionService.remove(id).success(function () {
                $scope.clearForm();
                $scope.showAlert();
                $scope.typeAlert = 'danger';
                $scope.messageAlert = 'Data have been deleted';
            });
        };
        
        $scope.edit = function (x) {
            $scope.editLabel = x.label;
            $scope.editValue = x.value;
            
            PermissionService.findById(x.id).success(function (data) {
                $scope.currentPermission = data;
                $scope.showModalInputPermission();
            });
        };
        
        $scope.searchAction = function () {
            if (angular.isUndefined($scope.searchPermission)) {
                $scope.reloadPage();
            } else {
                PermissionService.search($scope.searchPermission).success(function (data) {
                    $scope.permissions = data;
                });
            }
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
