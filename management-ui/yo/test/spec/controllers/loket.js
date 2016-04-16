'use strict';

describe('Controller: LoketCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var LoketCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    LoketCtrl = $controller('LoketCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(LoketCtrl.awesomeThings.length).toBe(3);
  });
});
