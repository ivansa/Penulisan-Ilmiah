'use strict';

describe('Service: ReportAntrianService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var ReportAntrianService;
  beforeEach(inject(function (_ReportAntrianService_) {
    ReportAntrianService = _ReportAntrianService_;
  }));

  it('should do something', function () {
    expect(!!ReportAntrianService).toBe(true);
  });

});
