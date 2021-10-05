import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductComponent} from "./components/product/product.component";
import {CartComponent} from "./components/cart/cart.component";
import {OrderComponent} from "./components/order/order.component";
import {AuthGuard} from "./helpers/auth-guard";
import{PRODUCT_URL} from "./components/product/product.component";
import {ORDERS_URL} from "./components/order/order.component";
import {LOGIN_URL, LoginPageComponent} from "./components/login-page/login-page.component";
import {CART_URL} from "./components/cart/cart.component";
import {REGISTER_URL, RegisterComponent} from "./components/register/register.component";

const routes: Routes = [
  {path:"",pathMatch:"full",redirectTo:PRODUCT_URL},
  {path: PRODUCT_URL, component:ProductComponent},
  {path: CART_URL , component:CartComponent},
  {path: LOGIN_URL, component:LoginPageComponent},
  {path: REGISTER_URL, component:RegisterComponent},
  {path: ORDERS_URL, component:OrderComponent,canActivate: [AuthGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
