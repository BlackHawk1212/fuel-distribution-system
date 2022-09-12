import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OrderCreateComponent } from './order-create/order-create.component';
import { CheckOrdersComponent } from './check-orders/check-orders.component';
import { OrderRecievedConfirmationComponent } from './order-recieved-confirmation/order-recieved-confirmation.component';

const routes: Routes = [
  {path: '', redirectTo: 'createorder', pathMatch: 'full'},
  {path: 'createorder', component: OrderCreateComponent},
  {path: 'getorderscomplete', component: OrderRecievedConfirmationComponent},
  {path: 'getorders', component: CheckOrdersComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }