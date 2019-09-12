package com.legou.item.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legou.pojo.TbItem;
import com.legou.pojo.TbItemDesc;
import com.legou.service.ItemService;
//商品详细信息显示处理器
@Controller
public class ItemController {
	
	@Autowired
     ItemService itemService;
	@RequestMapping("/item/{itemid}")
	//路径参数   网址栏传过来的参数
	public String itemShow(@PathVariable  Long itemid,Model model) {
		TbItem tbitem=itemService.getItem(itemid);
		model.addAttribute("item", tbitem);
		 TbItemDesc tbItemDesc = itemService.getItemDescById(itemid);
		 model.addAttribute("itemDesc", tbItemDesc);
		 return "item";
	}
}
