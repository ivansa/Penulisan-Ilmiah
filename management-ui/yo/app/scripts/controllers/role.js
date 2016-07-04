'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:RoleCtrl
 * @description
 * # RoleCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('RoleCtrl', function ($scope, uiGridConstants, $timeout, RoleService, PermissionService) {
		$scope.currentRole = {};
        $scope.paging = {currentPage: 1};
        
        $scope.reloadPage = function () {
            // Get Permission For UI Grid
            PermissionService.findWithoutPaging().success(function (data) {
                $scope.permissions = data;
            });
            
            RoleService.findAll($scope.paging.currentPage - 1, 10).success(function (data) {
                $scope.roles = data.content;

                $scope.paging.itemsPerPage = (data.size);
                $scope.paging.totalItems = data.totalElements;
                $scope.paging.currentPage = parseInt(data.number) + 1;
                $scope.paging.maxPage = data.totalPages;
            });
        };
        $scope.reloadPage();
        
        $scope.refreshPage = function(){
			$scope.searchRole = null;
			$scope.reloadPage();
		};
        
        $scope.showModalInputRole = function () {
            $("#modalAddRole").modal("show");
        };
        
        $scope.hideModalInputRole = function () {
            $("#modalAddRole").modal("hide");
            $scope.currentRole = {};
            $scope.gridApi.selection.clearSelectedRows();
            $scope.validateName = false;
        };
        
         $scope.gridOptions = {
             data: 'permissions',
             columnDefs: [{field: 'value', displayName: 'Permission Value'}],
             enableRowSelection: true,
             enableSelectAll: true,
             showGridFooter:true
         };
        
         $scope.gridOptions.onRegisterApi = function( gridApi ) {
             $scope.gridApi = gridApi;
         };
        
        $scope.save = function () {
            RoleService.findByName($scope.currentRole.name).success(function (data) {
                if (data.length !== 0 && $scope.editName !== data.name) {
                    $scope.validateName = true;
                } else {
                    $scope.currentRole.permissionSet = $scope.gridApi.selection.getSelectedRows();
                    RoleService.save($scope.currentRole).success(function () {
                        $scope.clearForm();
                        $scope.showAlert();
                        $scope.typeAlert = 'success';
                        $scope.messageAlert = 'Data have been saved';
                    });
                }
            });
        };
        
        $scope.showDialogDelete = function (x) {
            $scope.name = x.name;
            $scope.idRole = x.id;
            $("#modalConfirmDelete").modal("show");
        };
        
        $scope.remove = function (id) {
            RoleService.remove(id).success(function () {
                $scope.clearForm();
                $scope.showAlert();
                $scope.typeAlert = 'danger';
                $scope.messageAlert = 'Data have been deleted';
            });
        };
        
        $scope.clearForm = function () {
            $scope.hideModalInputRole();
            $scope.reloadPage();
            $scope.searchRole = null;
            $("#modalConfirmDelete").modal("hide");
        };
        
        $scope.showAlert = function () {
            $scope.alert = true;
            $timeout(function () {
                $scope.alert = false;
            }, 2000);
        };
        
        $scope.edit = function (x) {
            $scope.editName = x.name;
            
            // Clear All Permission on Grid
            $scope.gridApi.selection.clearSelectedRows();
            
            RoleService.findById(x.id).success(function (data) {
                $scope.currentRole = data;
                $scope.showModalInputRole();
                
                if ($scope.gridApi.grid.getVisibleRows().length === $scope.currentRole.permissionSet.length) {
                    $scope.gridApi.selection.selectAllRows();
                } else {
                    angular.forEach($scope.currentRole.permissionSet, function (value, i) {
                        var lanjut = true;
                        angular.forEach($scope.permissions, function (data, idx) {
                            if (lanjut && data.id === value.id) {
                                $scope.gridApi.selection.selectRow(data);
                                lanjut = false;
                            }
                        });
                    });
                }
            });
        };
        
        $scope.searchAction = function () {
            if (angular.isUndefined($scope.searchRole)) {
                $scope.reloadPage();
            } else {
                RoleService.search($scope.searchRole).success(function (data) {
                    $scope.roles = data;
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
