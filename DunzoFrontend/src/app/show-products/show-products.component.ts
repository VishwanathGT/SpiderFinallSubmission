import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ApiService } from '../api.service';

@Component({
  selector: 'app-show-products',
  templateUrl: './show-products.component.html',
  styleUrls: ['./show-products.component.scss']
})
export class ShowProductsComponent implements OnInit {
  title = 'DunzoCatalog';

  data = [
    // {
    //   shopName: 1,
    //   itemName: "Leanne Graham",
    //   price: "Bret",
    //   address: "Sincere@april.biz",
    //   phNo: "phnum"
    // },
    //     {
    //       id: 2,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 11,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     },
    //     {
    //       id: 4,
    //       name: "Leanne Graham",
    //       username: "Bret",
    //       email: "Sincere@april.biz"
    //     },
    //     {
    //       id: 5,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 6,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     },
    //     {
    //       id: 7,
    //       name: "Leanne Graham",
    //       username: "Bret",
    //       email: "Sincere@april.biz"
    //     },
    //     {
    //       id: 8,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 9,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     },
    //     {
    //       id: 10,
    //       name: "Leanne Graham",
    //       username: "Bret",
    //       email: "Sincere@april.biz"
    //     },
    //     {
    //       id: 12,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 11,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     },
    //     {
    //       id: 14,
    //       name: "Leanne Graham",
    //       username: "Bret",
    //       email: "Sincere@april.biz"
    //     },
    //     {
    //       id: 52,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 151,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     },
    //     {
    //       id: 165,
    //       name: "Leanne Graham",
    //       username: "Bret",
    //       email: "Sincere@april.biz"
    //     },
    //     {
    //       id: 652,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 161,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     },
    //     {
    //       id: 198,
    //       name: "Leanne Graham",
    //       username: "Bret",
    //       email: "Sincere@april.biz"
    //     },
    //     {
    //       id: 256,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 11564,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     },
    //     {
    //       id: 154,
    //       name: "Leanne Graham",
    //       username: "Bret",
    //       email: "Sincere@april.biz"
    //     },
    //     {
    //       id: 2987,
    //       name: "Ervin Howell",
    //       username: "Antonette",
    //       email: "Shanna@melissa.tv"
    //     },
    //  {
    //       id: 1145,
    //       name: "Nicholas DuBuque",
    //       username: "Nicholas.Stanton",
    //       email: "Rey.Padberg@rosamond.biz"
    //     }
  ];
  settings = {
    actions: {
      add: false,
      edit: false,
      delete: false
    },
    columns: {
      shopName: {
        title: 'Shop Name'
      },
      itemName: {
        title: 'Item Name'
      },
      price: {
        title: 'Price'
      },
      address: {
        title: 'Address'
      },
      phNo: {
        title: 'Phone Number'
      }
    },
    attr: {
      class: 'table table-bordered'
    },
  };
  showTable = false;

  constructor(private httpClient: HttpClient,
              private changeDetectorRef: ChangeDetectorRef,
              private apiService: ApiService) {

  }

  ngOnInit() {

    this.httpClient.get('http://10.105.24.243:8080/allitems').subscribe(
      (res: Array<any>) => {
        console.log(res);
        for (let i = 0; i < res.length; i++) {
          this.data.push(this.addDataModel(res[i].shopName, res[i].itemName, res[i].price, res[i].address, res[i].phNo));
        }

        this.changeDetectorRef.detectChanges();
        this.changeDetectorRef.markForCheck();
        this.showTable = true;
      },
      (err) => {
        console.log(err);
      }
    );
  }

  addDataModel(shopName, itemName, price, address, phNo) {
    return {
      shopName: shopName,
      itemName: itemName,
      price: price,
      address: address,
      phNo: phNo
    };
  }

}
