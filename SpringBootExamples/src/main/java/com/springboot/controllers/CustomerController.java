package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.domain.Customer;
import com.springboot.service.CustomerService;


@Controller
@RequestMapping("/customer")
public class CustomerController {

	private CustomerService customerService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@RequestMapping({"/list","/"})
	public String listCustomers(Model model) {
		model.addAttribute("customers",customerService.listAll());
		return "customer/customers";
	}
	
	@RequestMapping("/show/{id}")
	public String getCustomer(@PathVariable Integer id,Model model) {
		model.addAttribute("customer",customerService.getById(id));
		return "customer/customer";
	}
	
	@RequestMapping(value="/new")
	public String newCustomer(Model model) {
		model.addAttribute("customer",new Customer());
		return "customer/customerform";
	}
	
	@RequestMapping(method=RequestMethod.POST) 
	public String saveOrUpdate(Customer customer) {
		Customer savedOrUpdatedCustomer = customerService.saveOrUpdate(customer);
		return "redirect:/customer/show/" + savedOrUpdatedCustomer.getId();
	}
	
	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		model.addAttribute("customer",customerService.getById(id));
		return "customer/customerform";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		customerService.delete(id);
		return "redirect:/customer/list";
	}
	
}
