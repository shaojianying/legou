package com.legou.search.mapper;

import java.util.List;

import com.legou.common.pojo.SearchItem;

public interface ItemMapper {
	
	List<SearchItem> getItemList();//从mysql数据库中获得需要传入solr中的值

	SearchItem getItemById(long itemidLong);//通过itemid找商品
}
