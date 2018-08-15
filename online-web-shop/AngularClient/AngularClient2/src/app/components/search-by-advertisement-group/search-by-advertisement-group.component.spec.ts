import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SearchByAdvertisementGroupComponent } from './search-by-advertisement-group.component';

describe('SearchByAdvertisementGroupComponent', () => {
  let component: SearchByAdvertisementGroupComponent;
  let fixture: ComponentFixture<SearchByAdvertisementGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SearchByAdvertisementGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchByAdvertisementGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
