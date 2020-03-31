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
		model.addAttribute("states", "會員訂單列表");
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

	@GetMapping("/itemline/delete")
	public String deleteItemInOrder(@RequestParam("seq") Integer seq, 
									@RequestParam("oid") Integer oid, Model model) {
		Boolean delSeq = ilService.deleteItem(seq);
		Itemline item = ilService.getItemlineBySeq(seq);
		
		Customer customer = oService.getOrderByOid(oid).getCustomer();
//		此時刪掉最後一項，orderDetail不為null
		if (oService.getOrderByOid(oid).getOrderDetail()==null) {
			oService.deleteOrder(oid);
//			Customer customer2 = oService.getOrderByOid(oid).getCustomer();
			model.addAttribute("states", "訂單沒有商品，已刪除");
			model.addAttribute("cid", customer.getCid());
			model.addAttribute("cOrder", customer.getOrderList());
			return "redirect:/order?cid="+customer.getCid();
		} else {
			Orders order = oService.getOrderByOid(oid);
			model.addAttribute("states", "會員訂單明細");
			model.addAttribute("orderNo", order.getOid());
			model.addAttribute("orderDetail", order.getOrderDetail());
			return "redirect:/itemline?oid=" + order.getOid();
		}

	}

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
		Integer newOrder = oService.addOrder(order);
//		新增訂單明細
		for (Item i : itemList) {
			String par = "qty" + i.getIid();
			Integer qty = Integer.parseInt(req.getParameter(par));
			if (qty == 0)
				continue;
			Itemline item = new Itemline(null, i, qty, oService.getOrderByOid(newOrder));
			ilService.addOrderDetail(item);
		}
//		Orders newOrderBean = oService.getOrderByOid(newOrder);
		model.addAttribute("states", "訂購成功");
		model.addAttribute("orderNo", newOrder);
//		model.addAttribute("orderDetail", newOrderBean.getOrderDetail());
		model.addAttribute("orderDetail", ilService.getOrderDetailByOrder(oService.getOrderByOid(newOrder)));
		return "orderDetail";
	}

}