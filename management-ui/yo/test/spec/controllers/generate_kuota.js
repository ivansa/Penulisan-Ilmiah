'use strict';

describe('Controller: GenerateKuotaCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var GenerateKuotaCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    GenerateKuotaCtrl = $controller('GenerateKuotaCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(GenerateKuotaCtrl.awesomeThings.length).toBe(3);
  });
});
