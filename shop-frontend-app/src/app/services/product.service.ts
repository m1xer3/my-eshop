import { Injectable } from '@angular/core';
import {Product} from "../models/product";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Page} from "../models/page";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  public findAll() {
    return this.http.get<Page>('/api/v1/product/all').toPromise();
  }
}
