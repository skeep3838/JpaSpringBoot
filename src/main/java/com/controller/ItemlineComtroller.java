package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Customer;
import com.model.Orders;
import com.service.CustomerService;
import com.service.ItemlineService;
import com.service.OrderService;

@Controller
public class ItemlineComtroller {
	ItemlineService ilService;

	@Autowired
	public void setilService(ItemlineService ilService) {
		this.ilService = ilService;
	}

	OrderService orderService;

	@Autowired
	public void setoService(OrderService orderService) {
		this.orderService = orderService;
	}

	// -------------依訂單查詢訂單明細----------------------------------------------------------

	@GetMapping("/itemline")
	public String getOrderDetailOrder(@RequestParam("oid") Integer oid, Model model) {
		Orders order = orderService.getOrderByOid(oid);
		model.addAttribute("states", "會員訂單明細");
		model.addAttribute("orderNo", oid);
		model.addAttribute("orderDetail", order.getOrderDetail());
		return "orderDetail";
	}

	// ---------刪除訂單內商品----------------------------------------

	@GetMapping("/itemline/delete")
	public String deleteItemInOrder(@RequestParam("seq") Integer seq, @RequestParam("oid") Integer oid, Model model) {
		Boolean delSeq = ilService.deleteItem(seq);
//		Itemline item = ilService.getItemlineBySeq(seq);

		Customer customer = orderService.getOrderByOid(oid).getCustomer();
//			此時刪掉最後一項，orderDetail不為null
		if (orderService.getOrderByOid(oid).getOrderDetail().isEmpty()) {
			orderService.deleteOrder(oid);
			model.addAttribute("states", "訂單沒有商品，已刪除");
			model.addAttribute("cid", customer.getCid());
			model.addAttribute("cOrder", customer.getOrderList());
			return "redirect:/order?cid=" + customer.getCid();
		} else {
			Orders order = orderService.getOrderByOid(oid);
			model.addAttribute("states", "會員訂單明細");
			model.addAttribute("orderNo", order.getOid());
			model.addAttribute("orderDetail", order.getOrderDetail());
			return "redirect:/itemline?oid=" + order.getOid();
		}

	}
}
