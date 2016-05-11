'use strict';

describe('Controller: TransaksiLoketCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var TransaksiLoketCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    TransaksiLoketCtrl = $controller('TransaksiLoketCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(TransaksiLoketCtrl.awesomeThings.length).toBe(3);
  });
});
