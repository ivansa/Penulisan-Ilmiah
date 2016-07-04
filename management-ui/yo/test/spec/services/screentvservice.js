'use strict';

describe('Service: ScreenTvService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var ScreenTvService;
  beforeEach(inject(function (_ScreenTvService_) {
    ScreenTvService = _ScreenTvService_;
  }));

  it('should do something', function () {
    expect(!!ScreenTvService).toBe(true);
  });

});
