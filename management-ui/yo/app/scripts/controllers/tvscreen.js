'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:TvscreenCtrl
 * @description
 * # TvscreenCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('TvScreenCtrl', function ($scope, $timeout, ScreenTvService) {
    $scope.currentCall = {};
    $scope.antrian = {};

    $scope.listCategory = [];
    $scope.getLoketByCategory = function () {
      ScreenTvService.getLoketByCategory().success(function (data) {
        $scope.listCategory = data.listCategory;
        $scope.runPolling();
      });
    };

    $scope.getLoketByCategory();

    $scope.initAudio = function (msg) {
      $scope.arrayMsg = msg.split(' ');
      $scope.runAudio(0);
    };

    $scope.runAudio = function (x) {
      var audio = $('audio');
      audio[0].src = "antrian-server/sounds/" + $scope.arrayMsg[x] + ".mp3";
      audio[0].load();
      audio[0].play();
      audio[0].addEventListener('ended', function (e) {
        if (x == $scope.arrayMsg.length) {
          audio[0].src = null;
          $timeout(function () {
            $scope.runPolling();
          }, 1000);
        } else {
          $scope.runAudio(x + 1);
        }
      });
    };


    $scope.runPolling = function () {
      ScreenTvService.getListAntrian().success(function (data) {
        $scope.antrian = data;

        if (data.call) {
          $scope.currentCall = data.call;
          $scope.initAudio($scope.currentCall.terbilang);
        } else {
          $timeout(function () {
            $scope.runPolling();
          }, 1000);
        }
      });
    };
  });
