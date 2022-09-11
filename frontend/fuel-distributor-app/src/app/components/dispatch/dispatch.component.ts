import { Component, OnInit } from '@angular/core';
import {Dispatch} from 'src/app/models/dispatch.model';
import {DispatchService} from 'src/app/services/dispatch.service';


@Component({
  selector: 'app-dispatch',
  templateUrl: './dispatch.component.html',
  styleUrls: ['./dispatch.component.css']
})
export class DispatchComponent implements OnInit {

  dispatches : Dispatch[] = [];

  constructor(private dispatchservice : DispatchService) { }

  ngOnInit(): void {
    this.getAllDispatches();
  }

  getAllDispatches() : void{
    console.log("Hii");
    this.dispatchservice.getAllDispatches().subscribe((dispatches: Dispatch[]) => this.dispatches = dispatches);
  }

  dispatchOrder(orderId : string) : void {
    this.dispatchservice.dispatch(orderId).subscribe( (dispatch :Dispatch) => console.log(dispatch));
    this.getAllDispatches();
  }

}
