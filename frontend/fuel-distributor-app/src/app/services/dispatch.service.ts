import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { Dispatch } from '../models/dispatch.model';

@Injectable({
  providedIn: 'root'
})
export class DispatchService {

  private url = 'http://localhost:8194/dispatch/';

  constructor(private http: HttpClient) { }

  getAllDispatches() : Observable<Dispatch[]> {
     return this.http.get<Dispatch[]>(this.url);
  }
 
  dispatch(orderId : string) : Observable<Dispatch> {
    return this.http.post<Dispatch>(this.url, {orderId});
  }

}
 