'use strict';

describe('Controller: PoliCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var PoliCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PoliCtrl = $controller('PoliCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(PoliCtrl.awesomeThings.length).toBe(3);
  });
});
