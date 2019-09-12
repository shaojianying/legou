package com.legou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.utils.LegouResult;
import com.legou.search.service.ImportItemToSolrService;
//数据库数据导入solr索引库处理器
@Controller
public class ImportItemToSolrController {
	@Autowired
	ImportItemToSolrService importItemToSolrService;
		
	@RequestMapping("/index/item/import")
	@ResponseBody
	public LegouResult indexitemimport() {//数据库数据导入solr索引库
		LegouResult legouResult=importItemToSolrService.indexItemToSolr();//数据库数据导入solr索引库		
		return legouResult;
	}

}
