import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { HttpClientModule } from '@angular/common/http';
import { AppComponent } from './app.component';
import { OrderCreateComponent } from './order-create/order-create.component';
import { CheckOrdersComponent } from './check-orders/check-orders.component';
import { OrderRecievedConfirmationComponent } from './order-recieved-confirmation/order-recieved-confirmation.component';

@NgModule({
  declarations: [
    AppComponent,
    OrderCreateComponent,
    CheckOrdersComponent,
    OrderRecievedConfirmationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
