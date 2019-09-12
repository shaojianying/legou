package com.legou.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legou.content.service.ContentService;
import com.legou.pojo.TbContent;

@Controller
public class IndexController {
	@Autowired
	ContentService contentService;
	@RequestMapping("/index")//显示首页
	public String index(Model model) {
		//获得指定的位置广告类容*(大广告位)
		Long categoryId=(long)89;
		List<TbContent>list =contentService.getContentByCategoryId(categoryId);
		model.addAttribute("aD1List", list);
		return "index";
	}
}
