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
public class CustomerController {

	private CustomerService customerService;
	
	@Autowired
	public void setCustomerService(CustomerService customerService) {
		this.customerService=customerService;
	}
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/customers")
	public String listCustomers(Model model) {
		model.addAttribute("customers",customerService.listCustomers());
		return "customers";
	}
	
	@RequestMapping("/customer/{id}")
	public String getCustomer(@PathVariable Integer id,Model model) {
		model.addAttribute("customer",customerService.getCustomerById(id));
		return "customer";
	}
	
	@RequestMapping(value="/customer/new")
	public String newCustomer(Model model) {
		model.addAttribute("customer",new Customer());
		return "customerform";
	}
	
	@RequestMapping(value="/customer",method=RequestMethod.POST) 
	public String saveOrUpdate(Customer customer) {
		Customer savedOrUpdatedCustomer = customerService.saveOrUpdate(customer);
		return "redirect:/customer/" + savedOrUpdatedCustomer.getId();
	}
	
	@RequestMapping("/customer/edit/{id}")
	public String edit(@PathVariable Integer id,Model model) {
		model.addAttribute("customer",customerService.getCustomerById(id));
		return "customerform";
	}
	
	@RequestMapping("/customer/delete/{id}")
	public String delete(@PathVariable Integer id) {
		customerService.delete(id);
		return "redirect:/customers";
	}
	
}
