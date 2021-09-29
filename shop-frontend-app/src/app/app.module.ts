import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { ProductComponent } from './components/product/product.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule} from "@angular/common/http";
import { CartComponent } from './components/cart/cart.component';
import { ProductInfoPageComponent } from './components/product-info-page/product-info-page.component';
import { LoginPageComponent } from './components/login-page/login-page.component';
import { OrderComponent } from './components/order/order.component';
import {UnauthorizedInterceptor} from "./helpers/unauthorized-interceptor";
import {LOGIN_URL} from "./components/login-page/login-page.component";
import {PRODUCT_URL} from "./components/product/product.component";
import {ORDERS_URL} from "./components/order/order.component";
import { RegisterComponent } from './components/register/register.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    ProductComponent,
    CartComponent,
    ProductInfoPageComponent,
    LoginPageComponent,
    OrderComponent,
    RegisterComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
