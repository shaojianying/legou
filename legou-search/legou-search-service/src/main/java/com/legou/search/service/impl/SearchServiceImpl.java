package com.legou.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.legou.common.pojo.SearchItem;
import com.legou.search.dao.SerachDao;
import com.legou.search.service.SearchService;

//搜索数据实现类
@Service
public class SearchServiceImpl implements SearchService {	
	@Autowired
	SerachDao serachDao;
	@Override
	public List<SearchItem> selectSolr(String keyword) throws Exception{
		//创建一个空的搜索solr搜索条件
		SolrQuery query=new SolrQuery();
		//设置搜索条件关键字
		query.setQuery(keyword);
		//设置在哪一个数据库字段内容里找关键字
		query.set("df", "item_title");
		//设置查找条数
		query.setRows(100);
		//调用serachDao中的方法
			List<SearchItem> list= serachDao.search(query);
			return list;
		
	}
	
}
