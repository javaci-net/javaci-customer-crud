import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private API_BASE = 'http://localhost:8080/api';
  constructor(private httpClient: HttpClient) { }

  async getCustomers(): Promise<ICustomer[]>{
    const response = await this.httpClient.get<[ICustomer]>(`${this.API_BASE}/customer/list`).toPromise();
    return response;
  }

  async getCustomer(customerId: any): Promise<ICustomer> {
    const response = await this.httpClient.get<ICustomer>(`${this.API_BASE}/customer/${customerId}`).toPromise();
    return response;
  }

  async addCustomer(customer: ICustomer) : Promise<any> {
    const response = await this.httpClient.post(`${this.API_BASE}/customer/add`,{name:customer.name, lastName:customer.lastName}).toPromise();
    return response;
  }

  async updateCustomer(customer: ICustomer): Promise<any> {
    const response = await this.httpClient.put(`${this.API_BASE}/customer/update/${customer.id}`,{name:customer.name, lastName:customer.lastName}).toPromise();
    return response;
  }

  async deleteCustomer(id: Number):Promise<any> {
    const response = await this.httpClient.delete(`${this.API_BASE}/customer/delete/${id}`).toPromise();
    return response;
  }
}

export interface ICustomer{
  id: Number;
  name: string;
  lastName: string;
}
