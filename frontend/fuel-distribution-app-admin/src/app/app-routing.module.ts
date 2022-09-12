import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderDispatchComponent } from './order-dispatch/order-dispatch.component';
import { ViewAllOrdersComponent } from './view-all-orders/view-all-orders.component';

const routes: Routes = [
  { path: 'allorders', component: ViewAllOrdersComponent },
  { path: '', redirectTo: 'allorders', pathMatch: 'full' },
  { path: 'dispatch', component: OrderDispatchComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
