'use strict';

describe('Controller: ReportKuotaHarianCtrl', function () {

  // load the controller's module
  beforeEach(module('managementUiApp'));

  var ReportKuotaHarianCtrl,
    scope;

  // Initialize the controller and a mock scope
  beforeEach(inject(function ($controller, $rootScope) {
    scope = $rootScope.$new();
    ReportKuotaHarianCtrl = $controller('ReportKuotaHarianCtrl', {
      $scope: scope
      // place here mocked dependencies
    });
  }));

  it('should attach a list of awesomeThings to the scope', function () {
    expect(ReportKuotaHarianCtrl.awesomeThings.length).toBe(3);
  });
});
