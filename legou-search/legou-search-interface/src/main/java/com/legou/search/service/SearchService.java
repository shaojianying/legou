package com.legou.search.service;

import java.util.List;

import com.legou.common.pojo.SearchItem;

//搜索solr库接口
public interface SearchService {
	
	public List<SearchItem> selectSolr(String keyword) throws Exception;//搜索栏获得搜索数据



}
