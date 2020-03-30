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
	OrderService oService;
	ItemlineService ilService;
	ItemService sService;

	@Autowired
	public void setcService(CustomerService cService) {
		this.cService = cService;
	}

	@Autowired
	public void setoService(OrderService oService) {
		this.oService = oService;
	}

	@Autowired
	public void setilService(ItemlineService ilService) {
		this.ilService = ilService;
	}

	@Autowired
	public void setsService(ItemService sService) {
		this.sService = sService;
	}
//------------Setting----------------------------------------------

//----------首頁，客戶資訊-------------------------------------------

	@GetMapping("/")
	public String homepage(Model model) {
		List<Customer> cus = cService.findAll();
		model.addAttribute("customers", cus);
		return "index";
	}
//------------依客戶列表查詢訂單內容----------------------------------

	@GetMapping("/order")
	public String getOrderById(@RequestParam("cid") Integer cid, Model model) {
		Customer cus = cService.queryCustomerById(cid);
		model.addAttribute("cid", cid);
		model.addAttribute("cOrder", cus.getOrderList());
		return "order";
	}
//-------------依訂單查詢訂單明細----------------------------------------------------------

	@GetMapping("/itemline")
	public String getOrderDetailOrder(@RequestParam("oid") Integer oid, Model model) {
		Orders order = oService.getOrderByOid(oid);
		model.addAttribute("states", "會員訂單明細");
		model.addAttribute("orderNo", oid);
		model.addAttribute("orderDetail", order.getOrderDetail());
		return "orderDetail";
	}

//---------刪除訂單內商品----------------------------------------

//	@GetMapping("/itemline/delete")
//	public String deleteItemInOrder(@RequestParam("seq") Integer seq,Model model) {
//		Orders order = ilService.deleteItem(seq);
//		model.addAttribute("states", "會員訂單明細");
//		model.addAttribute("orderNo", order.getOid());
//		model.addAttribute("orderDetail", order.getOrderDetail());
//		return "redirect:/orderDetail";
//	}

//-------------商品清單------------------------------------------------------------------
	@GetMapping("/items")
	public String getItemList(@RequestParam("cid") Integer cid, Model model) {
		List<Item> itemList = sService.getAllItem();
		model.addAttribute("cid", cid);
		model.addAttribute("itemList", itemList);
		return "itemList";
	}
	
//----------------新增訂單----------------------------------------------------------------
	@PostMapping("/items")
	public String addOrder(HttpServletRequest req, @RequestParam("cid") Integer cid, Model model) {
		List<Item> itemList = sService.getAllItem();
//		先新增一筆訂單，取得最新訂單的Bean
		Orders order = new Orders(null, (new Date()), 30, cService.queryCustomerById(cid));
		Orders newOrd = oService.addOrder(order);
//		新增訂單明細
		for (Item i : itemList) {
			String par = "qty" + i.getIid();
			Integer qty = Integer.parseInt(req.getParameter(par));
			if (qty == 0)
				continue;
			Itemline item = new Itemline(null, i, qty, newOrd);
			ilService.addOrderDetail(item);
		}

		model.addAttribute("states", "訂購成功");
		model.addAttribute("orderNo", newOrd.getOid());
		model.addAttribute("orderDetail", newOrd.getOrderDetail());
		return "orderDetail";
	}

}