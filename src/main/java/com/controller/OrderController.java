package com.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Customer;
import com.model.Item;
import com.model.Itemline;
import com.model.Orders;
import com.model.obJson;
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

// -------------商品清單------------------------------------------------------------------
	@GetMapping("/items")
	public String getItemList(@RequestParam("cid") Integer cid, @RequestParam("page") Integer page,
			@RequestParam("sortItem") String sortItem, Model model) {
		Page<Item> itemPage = itemService.getAllItem(page, sortItem);
		model.addAttribute("cid", cid);
		model.addAttribute("itemList", itemPage.getContent());
		model.addAttribute("totalPages", itemPage.getTotalPages());
		model.addAttribute("sortItem", sortItem);
		return "itemList";
	}

// ----------------新增訂單----------------------------------------------------------------
	@PostMapping("/items")
	public String addOrder(@RequestParam("cid") Integer cid, @RequestParam("orderDetail") String orderDetail,
			Model model) {
		System.out.println(orderDetail);
		List<Itemline> itemline = new ArrayList<Itemline>();
//		把傳回來orderDetail的json字串轉為Object
		List<obJson> obJson = new ArrayList<obJson>();
//		轉jsonArray所需的物件，並對 orderDetail 做轉換
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			obJson = objectMapper.readValue(orderDetail, new TypeReference<List<obJson>>() {
			});
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			System.out.println("orderDetail 回傳規格不符");
		}

//		把轉好的物件塞進Itemline裡面
		for (obJson i : obJson) {
			Itemline item = new Itemline(null, itemService.getItemById(i.getIid()), i.getQty(), null);
			itemline.add(item);
		}
//		新增訂單	
		Orders order = new Orders((new Date()), 47, customerService.queryCustomerById(cid), itemline);
		Integer newOrder = orderService.addOrder(order);
//		秀出最新的訂單明細
		model.addAttribute("states", "訂購成功");
		model.addAttribute("orderNo", newOrder);
		model.addAttribute("orderDetail", itemlineService.getOrderDetailByOrder(orderService.getOrderByOid(newOrder)));

		return "orderDetail";
	}

//	----------刪除訂單------------------------------------------------------------
	@GetMapping("/items/deleteOrder")
	public String deleteOrder(@RequestParam("oid") Integer oid, @RequestParam("cid") Integer cid, Model model) {
		orderService.deleteOrder(oid);
		Customer cus = customerService.queryCustomerById(cid);
		model.addAttribute("states", "刪除訂單成功");
		model.addAttribute("cid", cid);
		model.addAttribute("cOrder", cus.getOrderList());
		return "redirect:/order?cid=" + cid;

	}

	// 查詢高於價格的商品筆數------------------------------------------------------

	@GetMapping("/item/price/{price}")
	@ResponseBody
	public String priceRangeItemCount(@PathVariable Integer price, Model model) {
		String itemCount = itemService.priceRangeEntity(price);
		return itemCount;

	}

// -------------商品清單------------------------------------------------------------------
//		@GetMapping("/items")
//		public String getItemOrderList(@RequestParam("cid") Integer cid, @RequestParam("page") Integer page, Model model) {
////			Integer page = 1;
//
//			Page<Item> itemPage = itemService.getAllItem(page);
//			model.addAttribute("cid", cid);
//			model.addAttribute("itemList", itemPage.getContent());
//			model.addAttribute("totalPages", itemPage.getTotalPages());
//			return "itemList";
//		}

}
