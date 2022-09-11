import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Order } from '../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private url = 'http://localhost:8191/orders/';

  constructor(private http : HttpClient) { }

  getAllOrders() : Observable<Order[]> {
    return this.http.get<Order[]>(this.url);
  }
}
 