package com.controller;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.model.Item;
import com.service.ItemService;

@Controller
public class ItemController {
	ItemService itemService;

	@Autowired
	public void setsService(ItemService itemService) {
		this.itemService = itemService;
	}

	// -------------商品清單------------------------------------------------------------------
	@GetMapping("/items")
	public String getItemList(@RequestParam("cid") Integer cid, @RequestParam("page") Integer page, Model model) {
//		Integer page = 1;

		Page<Item> itemPage = itemService.getAllItem(page);
		model.addAttribute("cid", cid);
		model.addAttribute("itemList", itemPage.getContent());
		model.addAttribute("totalPages", itemPage.getTotalPages());
		return "itemList";
	}

	@GetMapping(value = "/items/page/{page}", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getItemPage(@PathVariable("page") Integer page, Model model) throws JsonProcessingException {
		Page<Item> itemPage = itemService.getAllItem(page);
		ObjectMapper objectMapper = new ObjectMapper();
		String pageJson = objectMapper.writeValueAsString(itemPage.getContent());
		System.out.println(pageJson);

		return pageJson;
	}


}
