import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Stock } from '../models/stock.model';

@Injectable({
  providedIn: 'root'
})
export class StockService { 
  private url = 'http://localhost:8192/stocks/';

  constructor(private http : HttpClient) { }

  getAllOrders() : Observable<Stock[]> {
    return this.http.get<Stock[]>(this.url);
  }
}
