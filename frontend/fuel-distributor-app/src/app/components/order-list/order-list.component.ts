import { Component, OnInit } from '@angular/core';
import { Order } from 'src/app/models/order.model';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {
  
  orders? : Order[] = [];

  constructor(private orderService : OrderService) { }

  ngOnInit(): void {
    this.orderService.getAllOrders().subscribe( (orders : Order[]) => this.orders = orders);
  }

} 
