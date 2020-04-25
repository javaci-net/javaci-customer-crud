package net.javaci.customerCrud.dao;

import java.util.List;

import net.javaci.customerCrud.model.Customer;

public interface CustomerDao {

	List<Customer> findAll();

	Customer insert(Customer newCustomer);

}
