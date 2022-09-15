import { Component, OnInit } from '@angular/core';
import { OrderAllocation } from 'src/app/models/order-allocation.model';
import { Stock } from 'src/app/models/stock.model';
import { StockService } from 'src/app/services/stock.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {
 
  stocks : Stock[] = [];
  orderAllocations : OrderAllocation[] = [];
  stock : boolean = false;
  orderAllocation : boolean = false;


  constructor(private stockService : StockService) { } 

  ngOnInit(): void {
    this.stockService.getAllStocks().subscribe((stocks : Stock[]) => this.stocks = stocks);
    this.stockService.getAllOrderAllocations().subscribe((orderAllocations : OrderAllocation[]) => this.orderAllocations = orderAllocations);
  }

  stockDetails() : void{
    this.stock = true;
    this.orderAllocation = false;
  }

  orderAllocationDetails() : void {
    this.stock = false;
    this.orderAllocation = true;
  }

  allDetails() : void {
    this.stock = true;
    this.orderAllocation = true;
  }


}
