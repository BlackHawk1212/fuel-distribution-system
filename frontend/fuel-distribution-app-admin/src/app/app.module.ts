import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ViewAllOrdersComponent } from './view-all-orders/view-all-orders.component';
import { OrderDispatchComponent } from './order-dispatch/order-dispatch.component';

@NgModule({
  declarations: [
    AppComponent,
    ViewAllOrdersComponent,
    OrderDispatchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
