package com.legou.content.service;

import java.util.List;

import com.legou.common.pojo.EasyUITreeNode;
import com.legou.common.utils.LegouResult;


public interface ContentCategoryService {

	List<EasyUITreeNode> getConteCategoryList(long id);

	LegouResult createcontent(Long parentId, String name);//创建一个新的页面结构

	void deleteById(long id);//删除页面结构

	void updateContentById(String name, long id);//通过id将新的name赋值给他



}
