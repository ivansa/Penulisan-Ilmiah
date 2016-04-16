'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:UserCtrl
 * @description
 * # UserCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('UserCtrl', function ($scope, $timeout, UserService, RoleService, LoketService) {
     $scope.currentUser = {};
        $scope.listRole = [];
        $scope.listLoket = [];
        $scope.paging = {currentPage: 1};

        $scope.reloadPage = function () {
        	// Get Role
        	RoleService.findWithoutPaging().success(function (data) {
        		$scope.listRole = data;
        	});

        	// Get Loket
        	//LoketService.findAllWithoutPaging().success(function (data) {
	        //     $scope.listLoket = data;
	        //});

            UserService.findAll($scope.paging.currentPage - 1, 10).success(function (data) {
                $scope.users = data.content;

                $scope.paging.itemsPerPage = (data.size);
                $scope.paging.totalItems = data.totalElements;
                $scope.paging.currentPage = parseInt(data.number) + 1;
                $scope.paging.maxPage = data.totalPages;
            });
        };
        $scope.reloadPage();
		
		$scope.refreshPage = function(){
			$scope.searchUser = null;
			$scope.reloadPage();
		};
        
        $scope.showModalInputUser = function () {
            $("#modalAddUser").modal("show");
        };
        
        $scope.showDialogDelete = function (x) {
            $scope.username = x.username;
            $scope.idUser = x.id;
            $("#modalConfirmDelete").modal("show");
        };

        $scope.hideModalInputUser = function () {
            $("#modalAddUser").modal("hide");
            $scope.currentUser = {};
            $scope.validateUsername = false;
            $scope.validateEmail = false;
        };

        $scope.clearForm = function () {
            $scope.hideModalInputUser();
            $scope.reloadPage();
            $scope.searchUser = null;
            $("#modalConfirmDelete").modal("hide");
        };

        $scope.save = function () {
            // validate by username
            UserService.findByUsername($scope.currentUser.username).success(function (data) {
                if (data.length !== 0 && $scope.editUsername !== data.username) {
                    $scope.validateUsername = true;
                } else {
                    $scope.validateUsername = false;
                    // validate by email
                    UserService.findByEmail($scope.currentUser.email).success(function (dataEmail) {
                        if (dataEmail.length !== 0 && $scope.editEmail !== dataEmail.email) {
                            $scope.validateEmail = true;
                        } else {
                            $scope.validateEmail = false;
                            $scope.saveAction();
                        }
                    });
                }
            });
        };
        
        $scope.saveAction = function () {
            // save action
            UserService.save($scope.currentUser).success(function () {
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
            UserService.remove(id).success(function () {
                $scope.clearForm();
                $scope.showAlert();
                $scope.typeAlert = 'danger';
                $scope.messageAlert = 'Data have been deleted';
            });
        };
        
        $scope.edit = function (x) {
            $scope.editUsername = x.username;
            $scope.editEmail = x.email;
            
            UserService.findById(x.id).success(function (data) {
                $scope.currentUser = data;
                $scope.showModalInputUser();
            });
        };
        
        $scope.searchAction = function () {
            if (angular.isUndefined($scope.searchUser)) {
                $scope.reloadPage();
            } else {
                UserService.search($scope.searchUser).success(function (data) {
                    $scope.users = data;
                });
            }
        };

        $scope.showModalDetailUser = function (x) {
            $("#modalDetailUser").modal("show");
            $scope.listUserForDetail = {};
            $scope.listUserForDetail = x;
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
