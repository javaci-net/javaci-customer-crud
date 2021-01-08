package net.javaci.customerCrud;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/api/customer")
public class CustomerApi {

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
		return customerDao.save(newCustomer);
	}
	
	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public boolean delete(@PathVariable("id") Long id) {
		customerDao.deleteById(id);
		return true;
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public Customer update(@PathVariable("id") Long id, @RequestBody Customer customer) {
		if (customerDao.findById(id) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
		}
		
		customer.setId(id);
		return customerDao.save(customer);
	}
}
