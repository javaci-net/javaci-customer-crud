package net.javaci.customerCrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerDao customerDao;
	

	@GetMapping("/list")
	public String renderListPage(Model model) {
		model.addAttribute("customers", customerDao.findAll());
		return "list";
	}

	@GetMapping("/add")
	public String renderAddPage(Model model) {
		model.addAttribute("customer", new Customer());
		return "add";
	}

	@PostMapping("/add")
	public String handleAdd(@ModelAttribute Customer customer) {
		customerDao.save(customer);
		return "redirect:/customer/list";
	}

	@GetMapping("/update/{id}")
	public String renderUpdatePage(Model model, @PathVariable("id") Long id) {
		Customer customer = customerDao.findById(id).get();
		model.addAttribute("customer", customer);
		return "update";
	}

	@PostMapping("/update/{id}")
	public String handleUpdate(@PathVariable("id") Long id, @ModelAttribute Customer customer) {
		customerDao.save(customer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/delete/{id}")
	public String handleDelete(Model model, @PathVariable("id") Long id) {
		customerDao.deleteById(id);
		return "redirect:/customer/list";
	}

}
