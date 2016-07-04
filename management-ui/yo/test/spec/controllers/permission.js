'use strict';

describe('Controller: PermissionCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var PermissionCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    PermissionCtrl = $controller('PermissionCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(PermissionCtrl.awesomeThings.length).toBe(3);
  });
});
