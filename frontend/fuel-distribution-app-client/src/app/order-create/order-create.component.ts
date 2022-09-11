import { Component, OnInit } from '@angular/core';
import { OrderModel } from '../order-model';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order-create',
  templateUrl: './order-create.component.html',
  styleUrls: ['./order-create.component.css']
})
export class OrderCreateComponent implements OnInit {

  orderModel:OrderModel = new OrderModel();
  constructor(private orderService:OrderService,) { }

  ngOnInit(): void {}

  submitOrder(){
    this.orderService.submitOrder(this.orderModel).subscribe(data => {
      console.log(this.orderModel);
    },
    error => console.log(error))
  }

  realodPage(){
    location.reload();
  }

}
