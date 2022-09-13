import { Component, OnInit } from '@angular/core';
import {ProductServiceComponent} from "../product-service/product-service.component";
import {ActivatedRoute, Router} from "@angular/router";
import {Product} from "../model/product";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  product = new Product(null, "", 0);
  errorMsg = "";
  error: boolean | undefined;

  constructor(private productService: ProductServiceComponent,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit(): void {
    this.route.params.subscribe(param=>{
      this.productService.findById(param['id'])
        .subscribe(res=>{
          this.product = <Product>res;
          this.error = false;
          this.errorMsg = "";
        }, error => {
          console.log(error);
          this.error = true;
          this.errorMsg = error.error;

        })
    })
  }

  save() {
    this.productService.save(this.product)
      .subscribe(res=>{
        console.log(res);
        this.router.navigateByUrl('/products')
      }, error =>{
        console.log(error)
        this.error = true;
        this.errorMsg = error.error;
      });
  }
}
