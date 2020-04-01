package com.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Customer;
import com.model.Item;
import com.model.Itemline;
import com.model.Orders;
import com.service.CustomerService;
import com.service.ItemService;
import com.service.ItemlineService;
import com.service.OrderService;

@Controller
public class HomepageController {
	CustomerService cService;
	@Autowired
	public void setcService(CustomerService cService) {
		this.cService = cService;
	}

//----------首頁，客戶資訊-------------------------------------------

	@GetMapping("/")
	public String homepage(Model model) {
		List<Customer> cus = cService.findAll();
		model.addAttribute("customers", cus);
		return "index";
	}


}