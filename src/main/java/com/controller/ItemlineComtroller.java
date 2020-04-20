package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Customer;
import com.model.Orders;
import com.service.ItemlineService;
import com.service.OrderService;

@Controller
public class ItemlineComtroller {
	ItemlineService itemlineService;

	@Autowired
	public void setilService(ItemlineService itemlineService) {
		this.itemlineService = itemlineService;
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
		model.addAttribute("orderDetail", order.getOrders());
		return "orderDetail";
	}
	
	//更新訂單明細的內容------------------------------------------------------
	
	@PostMapping("/itemline/update")
	public String updateItemline(@RequestParam("seq") Integer seq, @RequestParam("qty") Integer qty, Model model) {
		itemlineService.updateItemQty(seq, qty);
		Integer oid = itemlineService.getItemlineBySeq(seq).getOrder().getOid();
		return "redirect:/itemline?oid=" + oid;
		
	}
	
	// ---------刪除訂單內商品----------------------------------------

	@GetMapping("/itemline/delete")
	public String deleteItemInOrder(@RequestParam("seq") Integer seq, @RequestParam("oid") Integer oid, Model model) {
		Boolean delSeq = itemlineService.deleteItem(seq);
//		Itemline item = ilService.getItemlineBySeq(seq);

		Customer customer = orderService.getOrderByOid(oid).getCustomer();
//			此時刪掉最後一項，orderDetail不為null
		if (orderService.getOrderByOid(oid).getOrders().isEmpty()) {
			orderService.deleteOrder(oid);
			model.addAttribute("states", "訂單沒有商品，已刪除");
			model.addAttribute("cid", customer.getCid());
			model.addAttribute("cOrder", customer.getOrderList());
			return "redirect:/order?cid=" + customer.getCid();
		} else {
			Orders order = orderService.getOrderByOid(oid);
			model.addAttribute("states", "會員訂單明細");
			model.addAttribute("orderNo", order.getOid());
			model.addAttribute("orderDetail", order.getOrders());
			return "redirect:/itemline?oid=" + order.getOid();
		}

	}
}
