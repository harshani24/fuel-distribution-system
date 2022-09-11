import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DispatchComponent } from './components/dispatch/dispatch.component';
import { OrderListComponent } from './components/order-list/order-list.component';

const routes: Routes = [
  {path:'', redirectTo:'orders' , pathMatch : 'full'},
  {path:'orders',  component:OrderListComponent},
  {path:'dispatches' , component:DispatchComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
