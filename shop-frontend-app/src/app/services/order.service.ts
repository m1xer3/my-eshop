import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Order} from "../models/order";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  public findOrdersByUser(userId:number) {
    return this.http.get<Order[]>(`/api/v1/order/all`);
  }

  public createOrder(){
    return this.http.post(`/api/v1/order/`, localStorage.getItem('current_user'));
  }
}
