'use strict';

describe('Service: ReportKuotaService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var ReportKuotaService;
  beforeEach(inject(function (_ReportKuotaService_) {
    ReportKuotaService = _ReportKuotaService_;
  }));

  it('should do something', function () {
    expect(!!ReportKuotaService).toBe(true);
  });

});
