package net.javaci.customerCrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import net.javaci.customerCrud.dao.CustomerDao;
import net.javaci.customerCrud.model.Customer;

@RestController
@RequestMapping("/api/customer-info")
public class CustomerInfoController {

	@Autowired
	private CustomerDao customerDao;
	
	@GetMapping("/list")
	@ResponseBody
	public List<Customer> listAll() {
		return customerDao.findAll();
	}
	
	@PostMapping("/add")
	@ResponseBody
	public Customer add(@RequestBody Customer newCustomer) {
		return customerDao.insert(newCustomer);
	}
	
}
