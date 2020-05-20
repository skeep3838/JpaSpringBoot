package com.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.Customer;
import com.service.CustomerService;
@Controller
public class CustomerController {
	CustomerService customerService;
	@Autowired
	public void setcService(CustomerService cService) {
		this.customerService = cService;
	}

//----------首頁，客戶資訊-------------------------------------------

	@GetMapping("/")
	public String homepage(Model model) {
		List<Customer> cus = customerService.findAll();
		model.addAttribute("customers", cus);
		return "index";
	}
	
//	test3-------------------------------------------------------
	@GetMapping("/")
	public String test3(Model model) {
		List<Customer> cus = customerService.findAll();
		model.addAttribute("customers", cus);
		model.addAttribute("status", "test3");
		return "index";
	}


}