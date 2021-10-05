
import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {AllCartDto} from "../models/all-cart-dto";
import {AddLineItemDto} from "../models/add-line-item-dto";
import {LineItem} from "../models/line-item";


@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient) { }

  public findAll() : Observable<AllCartDto> {
    return this.http.get<AllCartDto>('/api/v1/cart/all');
  }

  public addToCart(dto: AddLineItemDto) {
    return this.http.post('/api/v1/cart', dto);
  }

  public removeFromCart(dto: LineItem){
    return this.http.post('/api/v1/cart/delete',dto);
  }

  public decriseQty(dto: LineItem){
    return this.http.post('/api/v1/cart/decries',dto);
  }

  public clearCart(){
    return this.http.post('/api/v1/cart/deleteAll',null)
  }
}
