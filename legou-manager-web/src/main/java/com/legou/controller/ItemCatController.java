package com.legou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.pojo.EasyUITreeNode;
import com.legou.service.ItemCatService;

//商品类目处理器
@Controller
public class ItemCatController {
	@Autowired
	ItemCatService itemCatService;
	@RequestMapping("/item/cat/list")
	@ResponseBody
	public List<EasyUITreeNode> catlist(@RequestParam(name="id",defaultValue="0") long id) {//查找所有类目
		List<EasyUITreeNode> lists =itemCatService.getItemCatList(id);
		
		return lists;
	}
}
