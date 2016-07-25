'use strict';

describe('Service: HistoryService', function () {

  // load the service's module
  beforeEach(module('managementUiApp'));

  // instantiate service
  var HistoryService;
  beforeEach(inject(function (_HistoryService_) {
    HistoryService = _HistoryService_;
  }));

  it('should do something', function () {
    expect(!!HistoryService).toBe(true);
  });

});
