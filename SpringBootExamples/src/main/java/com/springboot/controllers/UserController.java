package com.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.domain.User;
import com.springboot.service.jpaservices.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	@RequestMapping({"/list","/"})
	public String listUsers(Model model) {
		model.addAttribute("users",userService.listAll());
		return "user/users";
	}
	
	@RequestMapping("/show/{id}")
	public String getUser(@PathVariable int id,Model model) {
		model.addAttribute("user",userService.getById(id));
		return "user/user";
	}
	
	@RequestMapping("/new")
	public String newUser(Model model) {
		model.addAttribute("user",new User());
		return "user/userform";
	}
	
	@RequestMapping(method=RequestMethod.POST)
	public String saveOrUpdateUser(User user) {
		User savedUser = userService.saveOrUpdate(user);
		return "redirect:/user/show/"+savedUser.getId();	
	}
	
	@RequestMapping(value="/edit/{id}")
	public String edit(@PathVariable int id,Model model) {
		model.addAttribute("user",userService.getById(id));
		return "user/userform";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		userService.delete(id);
		return "redirect:/user/list";
	}
}
