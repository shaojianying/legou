package com.legou.search.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.legou.common.pojo.SearchItem;
@Repository
public class SerachDao {
	@Autowired
	SolrServer solrServer;
	public List<SearchItem> search(SolrQuery query) throws SolrServerException {
		//从spring容器中自动注入获得solr对象 调用查找方法并把查找条件放进去  获得结果集
		QueryResponse queryResponse =solrServer.query(query);
		//获得查找结果
		SolrDocumentList solrDocument =queryResponse.getResults();
		//创建一个新的集合
		List<SearchItem>list=new ArrayList<SearchItem>();
		//将从solr中查出的值遍历给list集合
		for (SolrDocument solrDocument2 : solrDocument) {
			//list集合以一个一个SearchItem实列的模式存放值
			SearchItem item=new SearchItem(); 
			item.setId((String) solrDocument2.get("id"));
			item.setCategory_name((String) solrDocument2.get("item_category_name"));
			item.setImage((String) solrDocument2.get("item_image"));
			item.setSell_point((String) solrDocument2.get("item_sell_point"));
			item.setPrice((long) solrDocument2.get("item_price"));
			item.setTitle((String) solrDocument2.get("item_title"));
			list.add(item);
		}
		//返回集合
		return list;
	}
}
