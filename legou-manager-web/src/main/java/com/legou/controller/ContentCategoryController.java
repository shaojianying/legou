package com.legou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.legou.common.pojo.EasyUITreeNode;
import com.legou.common.utils.LegouResult;
import com.legou.content.service.ContentCategoryService;
//首页类目处理器
@Controller
public class ContentCategoryController {
	@Autowired
	ContentCategoryService contentCategoryService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<EasyUITreeNode> getContentCatgoryList(@RequestParam(name="id",defaultValue="0") long id){
		//通过传过来的父id 找到该父id下的所有结构对象
		List<EasyUITreeNode> contentList= contentCategoryService.getConteCategoryList(id);
		return contentList;
	}	

	@RequestMapping("/content/category/create")
	@ResponseBody
	public LegouResult categorycreate(Long parentId,String name) {//新增页面结构,页面传过来 新增页面结构的标题和该父类id
		LegouResult legour=	contentCategoryService.createcontent(parentId,name);
		return legour;
	}
	@RequestMapping("/content/category/delete/")//删除页面结构
	@ResponseBody
	public void deletecontent(long id) {
		contentCategoryService.deleteById(id);
	}
	@RequestMapping("/content/category/update")//页面结构重命名
	@ResponseBody
	public void categoryupdate(String name,long id) {
		contentCategoryService.updateContentById(name,id);
		
	}
}