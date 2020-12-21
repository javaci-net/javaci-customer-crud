import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CustomerService, ICustomer} from '../customer.service';

@Component({
  selector: 'app-customer-form',
  templateUrl: './customer-form.component.html',
  styleUrls: ['./customer-form.component.css']
})
export class CustomerFormComponent implements OnInit {
  formText: string ='Add a new customer';
  opText: string = 'Add';
  customer:ICustomer = null;
  errorMessage ='';

  constructor(private router:Router, private route: ActivatedRoute, private customerService:CustomerService) { }

  ngOnInit(): void {
    this.route.params.subscribe(data=>{
      let { customerId=null} = data;
      if (customerId){
        this.formText='Update a customer.';
        this.opText ='Save'
        this.route.queryParamMap.subscribe(cusMap =>{
          this.customer ={
            id:Number(cusMap.get('id')),
            lastName: cusMap.get('lastName'),
            name: cusMap.get('name')
          }
        })
      }
      else {
        this.customer ={id:null, lastName:null, name:null}
      }
    })

  }

  handleForm(){
    if (this.customer.name && this.customer.lastName){
      this.errorMessage='';
      if (this.opText=='Add'){
        this.customerService.addCustomer(this.customer)
          .then(d => {
            return this.router.navigateByUrl('/home');
          })
          .catch(err => console.log(err))

      }else{
        console.log("update : ", this.customer)
        this.customerService.updateCustomer(this.customer)
          .then(data => {
            return this.router.navigateByUrl('/home');
          })
          .catch(err => console.log(err))
      }
    }else{
      this.errorMessage = `Name and Last name are both required!`
    }
  }

}
