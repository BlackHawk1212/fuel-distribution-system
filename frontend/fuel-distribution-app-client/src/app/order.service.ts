import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OrderModel } from './order-model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private baseURL = "http://localhost:8081/services/orders";
  constructor(private httpClient:HttpClient) { }

  fetchAllOrders():Observable<OrderModel[]>{
    return this.httpClient.get<OrderModel[]>(`${this.baseURL}`);
  }

  submitOrder(order:OrderModel):Observable<Object>{
    return this.httpClient.post(`${this.baseURL}`,order);
  }

  

}
