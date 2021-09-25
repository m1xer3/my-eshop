import { Component, OnInit } from '@angular/core';
import {AllCartDto} from "../../models/all-cart-dto";
import {CartService} from "../../services/cart.service";
import {LineItem} from "../../models/line-item";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  content?: AllCartDto;

  constructor(private cartService: CartService) {
  }

  ngOnInit(): void {
    this.cartService.findAll().subscribe(
      res => {
        this.content = res;
      }
    )
  }

  removeLineItem(lineItem: LineItem): void{
    this.cartService.removeFromCart(lineItem).subscribe();
    window.location.reload();
  }

  decriesQty(lineItem: LineItem,qty: number): void{
    console.log(qty);
    alert(qty);
    alert(lineItem.qty);
    lineItem.qty=qty;
    alert(lineItem.qty);
    this.cartService.decriseQty(lineItem).subscribe();
    window.location.reload();
  }



}
