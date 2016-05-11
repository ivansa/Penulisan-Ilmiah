'use strict';

describe('Service: TransaksiLoketService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var TransaksiLoketService;
  beforeEach(inject(function (_TransaksiLoketService_) {
    TransaksiLoketService = _TransaksiLoketService_;
  }));

  it('should do something', function () {
    expect(!!TransaksiLoketService).toBe(true);
  });

});
