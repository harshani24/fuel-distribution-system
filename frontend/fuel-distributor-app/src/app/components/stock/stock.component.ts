import { Component, OnInit } from '@angular/core';
import { Stock } from 'src/app/models/stock.model';
import { StockService } from 'src/app/services/stock.service';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {
 
  stocks? : Stock[] = [];

  constructor(private stockService : StockService) { } 

  ngOnInit(): void {
    this.stockService.getAllOrders().subscribe((stocks : Stock[]) => this.stocks = stocks);
  }

}
