import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Dispatch } from './dispatch';

@Injectable({
  providedIn: 'root'
})
export class DispatchService {

  private baseURL = "http://localhost:8084/services/orders";
  private searchURL = "http://localhost:8081/services/orders/search";
  constructor(private httpClient: HttpClient) { }

  getAllOrders(): Observable<Dispatch[]> {
    return this.httpClient.get<Dispatch[]>(`${this.baseURL}`);
  }

  getOrderById(id: number): Observable<Dispatch[]> {
    return this.httpClient.get<Dispatch[]>(`${this.searchURL}/${id}`);
  }

}
