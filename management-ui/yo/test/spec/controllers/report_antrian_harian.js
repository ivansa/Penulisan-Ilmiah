'use strict';

describe('Controller: ReportAntrianHarianCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var ReportAntrianHarianCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ReportAntrianHarianCtrl = $controller('ReportAntrianHarianCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ReportAntrianHarianCtrl.awesomeThings.length).toBe(3);
  });
});
