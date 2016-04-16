'use strict';

describe('Service: LoketService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var LoketService;
  beforeEach(inject(function (_LoketService_) {
    LoketService = _LoketService_;
  }));

  it('should do something', function () {
    expect(!!LoketService).toBe(true);
  });

});
