'use strict';

describe('Controller: TvsettingCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var TvsettingCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TvsettingCtrl = $controller('TvsettingCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TvsettingCtrl.awesomeThings.length).toBe(3);
  });
});
