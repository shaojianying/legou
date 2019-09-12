package com.legou.service;

import java.util.List;

import com.legou.common.pojo.EasyUITreeNode;

//类目接口
public interface ItemCatService {
	public List<EasyUITreeNode> getItemCatList(long id); //获得所有类目
}
