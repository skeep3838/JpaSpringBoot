package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Item;
import com.service.ItemService;

@Controller
public class ItemController {
	ItemService sService;

	@Autowired
	public void setsService(ItemService sService) {
		this.sService = sService;
	}

	// -------------商品清單------------------------------------------------------------------
	@GetMapping("/items")
	public String getItemList(@RequestParam("cid") Integer cid, Model model) {
		List<Item> itemList = sService.getAllItem();
		model.addAttribute("cid", cid);
		model.addAttribute("itemList", itemList);
		return "itemList";
	}
}
