import { Component, OnInit } from '@angular/core';
import {CustomerService, ICustomer} from '../customer.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  customerList: ICustomer[] = [];
  constructor(private customerService: CustomerService) { }

  ngOnInit(): void {
    this.customerService.getCustomers().then(customers => {
      this.customerList = customers;
      console.log('Customers: ', this.customerList);
    });
  }

}
