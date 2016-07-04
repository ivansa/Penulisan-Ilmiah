'use strict';

/**
 * @ngdoc function
 * @name managementUiApp.controller:TvscreenCtrl
 * @description
 * # TvscreenCtrl
 * Controller of the managementUiApp
 */
angular.module('managementUiApp')
  .controller('TvScreenCtrl', function ($scope, ScreenTvService) {
    $scope.currentCall = {};

    $scope.listCategory = [];
    $scope.getLoketByCategory = function () {
      ScreenTvService.getLoketByCategory().success(function (data) {
        $scope.listCategory = data.listCategory;
        if(data.call){
          $scope.currentCall = data.call;
          $scope.initAudio($scope.currentCall.terbilang);
        }
      });
    };

    $scope.getLoketByCategory();

    $scope.initAudio = function (msg) {
      $scope.arrayMsg = msg.split(' ');
      $scope.runAudio(0);
    };

    $scope.runAudio = function (x) {
      var audio = $('audio');
      audio[0].src = "audio/" + $scope.arrayMsg[x] + ".mp3";
      audio[0].load();
      audio[0].play();
      audio[0].addEventListener('ended', function (e) {
        if (x == $scope.arrayMsg.length) {
          audio[0].src = null;
        } else {
          $scope.runAudio(x + 1);
        }
      });
    };
    
    $scope.polling = function(){
      
    };
  });
