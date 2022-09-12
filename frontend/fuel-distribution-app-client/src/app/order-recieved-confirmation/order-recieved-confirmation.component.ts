import { Component, OnInit } from '@angular/core';
import { OrderModel } from '../order-model';
import { OrderService } from '../order.service';

@Component({
  selector: 'app-order-recieved-confirmation',
  templateUrl: './order-recieved-confirmation.component.html',
  styleUrls: ['./order-recieved-confirmation.component.css']
})
export class OrderRecievedConfirmationComponent implements OnInit {

  id!:string;
  orders!: OrderModel[];
  datas: any;
  constructor(private orderService: OrderService) { }

  ngOnInit(): void {}

  getOrderById() {
    this.orderService.getOrderById(this.id).subscribe(data => {
      this.datas = data;
      console.log(data);
    },
      error => console.log(error));
  }

  getOrderComplete(id:string){
    this.orderService.getOrderComplete(id,"Completed").subscribe(data =>{
      console.log(data);
      this.getOrderById();
    },
    error => console.log(error));
  }

}
