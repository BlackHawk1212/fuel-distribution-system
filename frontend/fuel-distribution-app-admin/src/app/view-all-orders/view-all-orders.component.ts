import { Component, OnInit } from '@angular/core';
import { OrderModel } from '../order-model';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-view-all-orders',
  templateUrl: './view-all-orders.component.html',
  styleUrls: ['./view-all-orders.component.css']
})
export class ViewAllOrdersComponent implements OnInit {

  id!: string;
  scheduled!: string;
  orders!: OrderModel[];
  datas: any;

  constructor(private orderService: OrderService) { }

  ngOnInit(): void { }

  getOrderById() {
    this.orderService.getOrderById(this.id).subscribe(data => {
      this.orders = data;
      console.log(data);
    },
      error => console.log(error));
  }

}
