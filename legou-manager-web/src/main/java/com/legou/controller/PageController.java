package com.legou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
	//直接跳转到首页
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	//通用的页面跳转管理器
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		
		return page;
		
	}

}
