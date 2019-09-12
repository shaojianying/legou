package com.legou.search.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.legou.common.pojo.SearchItem;
import com.legou.search.service.SearchService;

//搜索solr库信息处理器
@Controller
public class SearchController {
	
	@Autowired
	SearchService searchService ;
	@RequestMapping("/search")//搜索solr库信息
	public String search(String keyword,Model model) throws Exception {
			//解决网址栏传过来的字符串乱码问题
			keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
		    System.out.println("keyword"+keyword);
			List<SearchItem>list=searchService.selectSolr(keyword);
			model.addAttribute("itemList", list);
		    return "search";

	}

}
