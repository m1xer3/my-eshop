import {Component, Inject, OnInit, Renderer2} from '@angular/core';
import {Product} from "../../models/product";
import {ProductService} from "../../services/product.service";
import {DOCUMENT} from "@angular/common";
import {PictureService} from "../../services/picture.service";


@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit {

  products: Product[] = [];
  isError: boolean = false;

  constructor(private productService: ProductService,
              @Inject(DOCUMENT) private document: Document,
              private pictureService: PictureService) { }

  ngOnInit(): void {
    this.retrieveProducts();

    // this.loadScript('shop-frontend-app/src/scripts/simple-adaptive-slider.js').then(
    //   () => this.loadTextScript(`
    //       setTimeout(() => {
    //         $( "#promise-based" ).html( "PromiseBasedComponent..." )
    //       }, 2000);
    //   `)
    // );
  }

  // loadTextScript(text: string) {
  //   return new Promise<void>(resolve => {
  //     const script = this.renderer2.createElement('script');
  //     script.text = text;
  //     this.renderer2.appendChild(this.document.body, script);
  //     resolve();
  //   });
  // }
  //
  // loadScript(url: string) {
  //   return new Promise((resolve, reject) => {
  //     const script = this.renderer2.createElement('script');
  //     script.src = url;
  //     script.onload = resolve;
  //     script.onerror = reject;
  //     this.renderer2.appendChild(this.document.body, script);
  //   });
  // }

  private retrieveProducts() {
    this.productService.findAll()
      .then(res => {
        this.products = res.content;
      })
      .catch(err => {
        console.error(err);
        this.isError = true;
      })
  }

  public getPictures(product: Product){
    if (product.pictures!=null){
      var id = product.pictures[0];
      this.pictureService.getPicture(id);
    }

  }




}
