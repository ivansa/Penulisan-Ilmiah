'use strict';

describe('Controller: TvscreenCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var TvscreenCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TvscreenCtrl = $controller('TvscreenCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TvscreenCtrl.awesomeThings.length).toBe(3);
  });
});
