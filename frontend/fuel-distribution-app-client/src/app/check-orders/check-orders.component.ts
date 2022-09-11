import { Component, OnInit } from '@angular/core';
import { OrderModel } from '../order-model';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-check-orders',
  templateUrl: './check-orders.component.html',
  styleUrls: ['./check-orders.component.css']
})
export class CheckOrdersComponent implements OnInit {

  orders!: OrderModel[];
  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
    this.getAllOrders();
  }

  private getAllOrders(){
    this.orderService.fetchAllOrders().subscribe(data =>{
      this.orders = data;
      console.log(data);
    })
  }

}
