'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:GenerateKuotaCtrl
 * @description
 * # GenerateKuotaCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('GenerateKuotaCtrl', function ($scope, $timeout, GenerateKuotaService) {
    $scope.generateDisabled = false;
    $scope.generate = function () {
      $scope.generateDisabled = true;
      GenerateKuotaService.generate().success(function () {
        $scope.showAlert();
        $scope.generateDisabled = false;
        $scope.typeAlert = 'success';
        $scope.messageAlert = 'Generate Kuata Successfuly';
      });
    };
    
    $scope.showAlert = function () {
      $scope.alert = true;
      $timeout(function () {
        $scope.alert = false;
      }, 2000);
    };
 
 });
