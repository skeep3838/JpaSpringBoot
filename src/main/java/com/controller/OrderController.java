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
public class OrderController {
	OrderService orderService;

	@Autowired
	public void setoService(OrderService orderService) {
		this.orderService = orderService;
	}

	ItemlineService itemlineService;

	@Autowired
	public void setilService(ItemlineService itemlineService) {
		this.itemlineService = itemlineService;
	}

	ItemService itemService;

	@Autowired
	public void setsService(ItemService itemService) {
		this.itemService = itemService;
	}

	CustomerService customerService;

	@Autowired
	public void setcService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// ------------依客戶列表查詢訂單內容----------------------------------

	@GetMapping("/order")
	public String getOrderById(@RequestParam("cid") Integer cid, Model model) {
		Customer cus = customerService.queryCustomerById(cid);
		model.addAttribute("states", "會員訂單列表");
		model.addAttribute("cid", cid);
		model.addAttribute("cOrder", cus.getOrderList());
		return "order";
	}

	// ----------------新增訂單----------------------------------------------------------------
	@PostMapping("/items")
	public String addOrder(HttpServletRequest req, @RequestParam("cid") Integer cid, Model model) {
		List<Item> itemList = itemService.getAllItem();
//			先新增一筆訂單，取得最新訂單的Bean
		Orders order = new Orders(null, (new Date()), 30, customerService.queryCustomerById(cid));
		Integer newOrder = orderService.addOrder(order);
//			新增訂單明細
		for (Item i : itemList) {
			String par = "qty" + i.getIid();
			Integer qty = Integer.parseInt(req.getParameter(par));
			if (qty == 0)
				continue;
			Itemline item = new Itemline(null, i, qty, orderService.getOrderByOid(newOrder));
			itemlineService.addOrderDetail(item);
		}
//			Orders newOrderBean = oService.getOrderByOid(newOrder);
		model.addAttribute("states", "訂購成功");
		model.addAttribute("orderNo", newOrder);
//			model.addAttribute("orderDetail", newOrderBean.getOrderDetail());
		model.addAttribute("orderDetail", itemlineService.getOrderDetailByOrder(orderService.getOrderByOid(newOrder)));
		return "orderDetail";
	}
}
