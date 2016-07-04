'use strict';

describe('Service: GenerateKuotaService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var GenerateKuotaService;
  beforeEach(inject(function (_GenerateKuotaService_) {
    GenerateKuotaService = _GenerateKuotaService_;
  }));

  it('should do something', function () {
    expect(!!GenerateKuotaService).toBe(true);
  });

});
