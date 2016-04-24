'use strict';

describe('Service: DokterService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var DokterService;
  beforeEach(inject(function (_DokterService_) {
    DokterService = _DokterService_;
  }));

  it('should do something', function () {
    expect(!!DokterService).toBe(true);
  });

});
