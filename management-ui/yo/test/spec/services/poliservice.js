'use strict';

describe('Service: PoliService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var PoliService;
  beforeEach(inject(function (_PoliService_) {
    PoliService = _PoliService_;
  }));

  it('should do something', function () {
    expect(!!PoliService).toBe(true);
  });

});
