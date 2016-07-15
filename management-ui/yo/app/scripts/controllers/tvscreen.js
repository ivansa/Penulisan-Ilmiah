'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:TvscreenCtrl
 * @description
 * # TvscreenCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('TvScreenCtrl', function ($scope, $timeout, ScreenTvService, TransaksiLoketService, ConfigService) {
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
      var audioHtml = "<audio id='audio'' preload='auto'' tabindex='0' controls='' type='audio/mpeg'>";
      $("#audioPlayer").html(audioHtml);
      var audio = $('audio');

      audio[0].src = ConfigService.serverUrl + "/api/sound/" + $scope.arrayMsg[x];
      audio[0].load();
      audio[0].play();
      audio[0].addEventListener('ended', function (e) {
        var i = x + 1;
        if (i == $scope.arrayMsg.length) {
          $("#audioPlayer").html("-");
          $scope.setFinishedCall();
        } else {
          $timeout(function () {
            $scope.runAudio(x + 1);
          }, 150);
        }
      });
    };

    $scope.setFinishedCall = function () {
      ScreenTvService.setFinishedCall($scope.currentCall.id).success(function () {
        $timeout(function () {
          $scope.runPolling();
        }, 1000);
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
