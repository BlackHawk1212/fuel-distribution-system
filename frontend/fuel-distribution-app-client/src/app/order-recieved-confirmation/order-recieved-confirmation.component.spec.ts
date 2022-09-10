import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrderRecievedConfirmationComponent } from './order-recieved-confirmation.component';

describe('OrderRecievedConfirmationComponent', () => {
  let component: OrderRecievedConfirmationComponent;
  let fixture: ComponentFixture<OrderRecievedConfirmationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OrderRecievedConfirmationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrderRecievedConfirmationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
