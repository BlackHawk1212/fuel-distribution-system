import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderModel } from './order-model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseURL = "http://localhost:8081/services/orders";
  constructor(private httpClient: HttpClient) { }

  fetchAllOrders(): Observable<OrderModel[]> {
    console.log(OrderModel);
    return this.httpClient.get<OrderModel[]>(`${this.baseURL}`);
  }

  submitOrder(order: OrderModel): Observable<Object> {
    console.log(order);
    return this.httpClient.post(`${this.baseURL}`, order);
  }

  getOrderById(id:string): Observable<OrderModel[]> {
    return this.httpClient.get<OrderModel[]>(`${this.baseURL}/${id}`);
  }

  getOrderComplete(id:string, reserved:string) {
    return this.httpClient.put(`${this.baseURL}/${id}/${status}`, "");
  }

}