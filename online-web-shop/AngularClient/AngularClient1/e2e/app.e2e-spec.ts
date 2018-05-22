import { AngularClient1Page } from './app.po';

describe('angular-client1 App', function() {
  let page: AngularClient1Page;

  beforeEach(() => {
    page = new AngularClient1Page();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
