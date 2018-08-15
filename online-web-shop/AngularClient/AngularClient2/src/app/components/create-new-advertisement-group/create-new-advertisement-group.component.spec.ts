import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateNewAdvertisementGroupComponent } from './create-new-advertisement-group.component';

describe('CreateNewAdvertisementGroupComponent', () => {
  let component: CreateNewAdvertisementGroupComponent;
  let fixture: ComponentFixture<CreateNewAdvertisementGroupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateNewAdvertisementGroupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateNewAdvertisementGroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
