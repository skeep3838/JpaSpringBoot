package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.model.EmployeeOrdersJoinColumn;
import com.service.EmployeeService;
import com.service.OrderService;

@Controller
public class EmployeeController {
	EmployeeService employeeService;
	@Autowired
	private void setService(EmployeeService employeeService) {
		this.employeeService = employeeService;

	}
	
	OrderService orderService;
	@Autowired
	private void setService(OrderService orderService) {
		this.orderService = orderService;

	}
	
	@GetMapping("/emlpoyee/left")
	public String leftJoin(Model model) {
		List<EmployeeOrdersJoinColumn> left = employeeService.liftJoinColumn();
		model.addAttribute("ststus","Employee Left Join Orders");
		model.addAttribute("employee", left);
		return "employee";
	}
	@GetMapping("/emlpoyee/right")
	public String rightJoin(Model model) {
		List<EmployeeOrdersJoinColumn> right = orderService.rightJoinColumn();
		model.addAttribute("ststus","Employee Right Join Orders");
		model.addAttribute("employee", right);
		return "employee";
	}
	
	@GetMapping("/emlpoyee/inner")
	public String innerJoin(Model model) {
		List<EmployeeOrdersJoinColumn> inner = employeeService.innerJoinColumn();
		model.addAttribute("ststus","Employee Inner Join Orders");
		model.addAttribute("employee", inner);
		return "employee";
	}
	
	@GetMapping("/emlpoyee/fullOuter")
	public String fullOuterJoin(Model model) {
		List<EmployeeOrdersJoinColumn> outer = employeeService.liftJoinColumn();
		List<EmployeeOrdersJoinColumn> empNullOrder = orderService.empNullJoinColumn();
		Integer counter = outer.size();
		for(EmployeeOrdersJoinColumn e: empNullOrder) {
			counter+=1;
			e.setSeq(counter);
			outer.add(e);
		}
		model.addAttribute("ststus","Employee Full Outer Join Orders");
		model.addAttribute("employee", outer);
		return "employee";
	}
	
}
