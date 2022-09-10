import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CheckOrdersComponent } from './check-orders.component';

describe('CheckOrdersComponent', () => {
  let component: CheckOrdersComponent;
  let fixture: ComponentFixture<CheckOrdersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckOrdersComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CheckOrdersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
