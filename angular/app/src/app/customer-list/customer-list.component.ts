import {Component, Input, OnInit, TemplateRef} from '@angular/core';
import {CustomerService, ICustomer} from '../customer.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  @Input('customers') customers:ICustomer[] = [];
  selectedCustomer:ICustomer = null;
  modalRef: BsModalRef
  constructor(private modalService: BsModalService, private customerService:CustomerService) { }

  ngOnInit(): void {
  }

  delete(customer: ICustomer,template: TemplateRef<any>) {
    this.selectedCustomer = customer;
    this.modalRef = this.modalService.show(template, {class: 'modal-sm'});
  }

  confirmDelete() {
    this.modalRef.hide();
    this.customerService.deleteCustomer(this.selectedCustomer.id)
      .then(data =>{
        this.customers = this.customers.filter(c => c.id != this.selectedCustomer.id);
        this.selectedCustomer = null;
      })
      .catch(err => console.log(err))
  }

  cancelDelete() {
    this.modalRef.hide();
  }
}
