'use strict';

describe('Service: PermissionService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var PermissionService;
  beforeEach(inject(function (_PermissionService_) {
    PermissionService = _PermissionService_;
  }));

  it('should do something', function () {
    expect(!!PermissionService).toBe(true);
  });

});
