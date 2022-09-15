import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Stock } from '../models/stock.model';
import { OrderAllocation } from '../models/order-allocation.model';
import { Order } from '../models/order.model';

@Injectable({
  providedIn: 'root'
})
export class StockService { 
  private sUrl = 'http://localhost:8192/stocks/';
  private oUrl = 'http://localhost:8192/order-allocations/';

  constructor(private http : HttpClient) { }

  getAllStocks() : Observable<Stock[]> {
    return this.http.get<Stock[]>(this.sUrl);
  }

  getAllOrderAllocations() : Observable<OrderAllocation[]> {
    return this.http.get<OrderAllocation[]>(this.oUrl);
  }
}
