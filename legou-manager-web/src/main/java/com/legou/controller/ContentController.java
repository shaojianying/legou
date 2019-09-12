package com.legou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.utils.LegouResult;
import com.legou.content.service.ContentService;
import com.legou.pojo.TbContent;
//广告位类容管理
@Controller
public class ContentController{
	@Autowired
	ContentService contentService;
	@RequestMapping("/content/save")
	@ResponseBody
	public LegouResult contentsave(TbContent tbContent) {//内容管理添加内容
		//页面传过来的值封装成TbContent对象
		LegouResult legouResult=contentService.savecontent(tbContent);
		return legouResult;
	}
	@RequestMapping("/content/query/list")
	public void contentquerylist() {//点击广告位 显示内容
		
	}
}

