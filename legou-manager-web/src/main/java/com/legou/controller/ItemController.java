package com.legou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.pojo.EasyUIDataGridResult;
import com.legou.common.utils.LegouResult;
import com.legou.pojo.TbItem;
import com.legou.service.ItemService;
//商品处理器
@Controller
public class ItemController {
	@Autowired
	private ItemService itemService;
	//需要调用service查询
	@Autowired
	private ItemService ItemService;
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	public TbItem getItemById(@PathVariable long itemId) {
		TbItem item = ItemService.getItem(itemId);
		return item;	
	}
	
	@RequestMapping("/test")
	@ResponseBody
	public String Test() {
		return "hello test";
	}
	
	@RequestMapping("/item/list")
	@ResponseBody//返回json
	public EasyUIDataGridResult getItem(int page, int rows) {//获得所有商品信息
		return itemService.getItemList(page, rows);
	}
	@RequestMapping("/item/save")
	@ResponseBody
	public LegouResult save(TbItem tbItem,String desc) {//保存商品到数据库 并保存商品描述带商品描述表
		LegouResult legouResult =itemService.saveItemAndDesc(tbItem,desc);
		return legouResult;
		
	}
}