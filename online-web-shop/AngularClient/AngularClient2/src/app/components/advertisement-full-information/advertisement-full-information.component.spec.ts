import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AdvertisementFullInformationComponent } from './advertisement-full-information.component';

describe('AdvertisementFullInformationComponent', () => {
  let component: AdvertisementFullInformationComponent;
  let fixture: ComponentFixture<AdvertisementFullInformationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AdvertisementFullInformationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AdvertisementFullInformationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
