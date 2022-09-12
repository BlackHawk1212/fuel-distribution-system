import { Component, OnInit } from '@angular/core';
import { OrderModel } from '../order-model';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-check-orders',
  templateUrl: './check-orders.component.html',
  styleUrls: ['./check-orders.component.css']
})

export class CheckOrdersComponent implements OnInit {

  id!: string;
  scheduled!: string;
  orders!: OrderModel[];
  datas: any;

  constructor(private orderService: OrderService) { }

  ngOnInit(): void { }

  getOrderById() {
    this.orderService.getOrderById(this.id).subscribe(data => {
      this.datas = data;
      console.log(data);
    },
      error => console.log(error));
  }

}